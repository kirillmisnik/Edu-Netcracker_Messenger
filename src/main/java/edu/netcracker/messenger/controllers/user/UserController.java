package edu.netcracker.messenger.controllers.user;

import edu.netcracker.messenger.models.chat.Chat;
import edu.netcracker.messenger.views.chat.ChatListView;
import edu.netcracker.messenger.models.message.Message;
import edu.netcracker.messenger.models.message.MessageRepository;
import edu.netcracker.messenger.models.user.AccountType;
import edu.netcracker.messenger.models.user.User;
import edu.netcracker.messenger.models.user.exceptions.UserNotFoundException;
import edu.netcracker.messenger.models.user.UserRepository;
import edu.netcracker.messenger.views.user.UserPrivateView;
import edu.netcracker.messenger.views.user.UserPublicView;
import edu.netcracker.messenger.views.user.UserView;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for handling requests related to users.
 */
@Controller
@RequestMapping("api/user")
public class UserController {

    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    UserController(UserRepository userRepository,
                   MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    /**
     * Returns a list of all registered users.
     * @param loggedInUser logged in user
     * @return list of users
     */
    @GetMapping("/all")
    public @ResponseBody List<UserView> allUsers(Principal loggedInUser) {
        throwIfNoUserExists();
        List<UserView> users = new ArrayList<>();
        if (userRepository.getByUsername(loggedInUser.getName()).getAccountType().equals(AccountType.ADMIN)) {
            for (User user : userRepository.findAll()) {
                users.add(new UserPrivateView(user));
            }
        } else {
            for (User user : userRepository.findAll()) {
                users.add(new UserPublicView(user));
            }
        }
        return users;
    }

    /**
     * Returns user information.
     * @param principal logged in user
     * @param id user id
     * @return user information
     */
    @GetMapping("/{id}")
    public @ResponseBody
    UserView getUserInfo(Principal principal, @PathVariable Long id) {
        throwIfUserNotExists(id);
        if (hasPermission(loggedInUser(principal), id)) {
            return new UserPrivateView(userRepository.getById(id));
        }
        return new UserPublicView(userRepository.getById(id));
    }

    /**
     * Deletes user and all personal chats with this user.
     * @param id user id
     * @return user id
     */
    @DeleteMapping("/{id}")
    public @ResponseBody Long deleteUser(Principal principal, @PathVariable Long id) {
        throwIfUserNotExists(id);
        if (!hasPermission(loggedInUser(principal), id)) {
            throw new AccessDeniedException(
                    String.format("You don't have permission to delete user with id: %d", id));
        }
        userRepository.deleteById(id);
        return id;
    }

    /**
     * Blocks user.
     * @param loggedInUser logged in user
     * @param id user id
     * @return user id
     */
    @PostMapping("/{id}/block")
    public @ResponseBody Long blockUser(Principal loggedInUser, @PathVariable Long id) {
        throwIfUserNotExists(id);
        User userById = userRepository.getByUsername(loggedInUser.getName());
        if (!userById.getAccountType().equals(AccountType.ADMIN)) {
            throw new AccessDeniedException("You don't have permission to block users.");
        }
        userById.setAccountType(AccountType.BLOCKED);
        return id;
    }

    /**
     * Returns a list of all chats available to user.
     * @param principal logged in user
     * @param id user id
     * @return list of chats
     */
    @GetMapping("/{id}/chats")
    public @ResponseBody
    List<ChatListView> getUserChats(Principal principal, @PathVariable Long id) {
        throwIfUserNotExists(id);
        if (!hasPermission(loggedInUser(principal), id)) {
            throw new AccessDeniedException(
                    String.format("You don't have permission to access chat(s) of the user with id: %d", id));
        }

        List<ChatListView> chatList = new ArrayList<>();
        for (Chat chat : loggedInUser(principal).getChats()) {
            Message lastMessage = messageRepository.latestInChat(chat.getId());
            chatList.add(new ChatListView(chat.getId(), chat.getChatName(), chat.getChatPictureId(),
                    lastMessage != null ? lastMessage.getText() : null));
        }
        return chatList;
    }

    /**
     * Returns the logged in user from principal as a User entity.
     * @param principal logged in user
     * @return logged in user (as User)
     */
    private User loggedInUser(Principal principal) {
        return userRepository.getByUsername(principal.getName());
    }

    /**
     * Checks if the user with the given id exists.
     * @param id user id
     * @throws UserNotFoundException user is not found with provided id
     */
    private void throwIfUserNotExists(Long id) throws UserNotFoundException {
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException(id);
        }
    }

    /**
     * Checks if at least one user exists.
     * @throws UserNotFoundException no user found
     */
    private void throwIfNoUserExists() throws UserNotFoundException {
        if (userRepository.findAll().isEmpty()) {
            throw new UserNotFoundException();
        }
    }

    /**
     * Checks if the logged in user has access to information available by id.
     * @param loggedInUser logged in user
     * @param id user id
     * @return true - has access, false - does not have
     */
    private boolean hasPermission(User loggedInUser, Long id) {
        return loggedInUser.getId().equals(id) || loggedInUser.getAccountType().equals(AccountType.ADMIN);
    }
}