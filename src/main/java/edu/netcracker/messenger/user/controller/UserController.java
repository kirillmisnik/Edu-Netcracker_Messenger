package edu.netcracker.messenger.user.controller;

import edu.netcracker.messenger.chat.PersonalChat;
import edu.netcracker.messenger.chat.PersonalChatRepository;
import edu.netcracker.messenger.user.AccountType;
import edu.netcracker.messenger.user.User;
import edu.netcracker.messenger.user.exceptions.UserNotFoundException;
import edu.netcracker.messenger.user.UserRepository;
import edu.netcracker.messenger.user.views.UserPrivateView;
import edu.netcracker.messenger.user.views.UserPublicView;
import edu.netcracker.messenger.user.views.UserView;
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
@RequestMapping("user")
public class UserController {

    /**
     * User JPA repository.
     */
    private final UserRepository userRepository;

    /**
     * Personal chat JPA repository.
     */
    private final PersonalChatRepository chatRepository;

    UserController(UserRepository userRepository, PersonalChatRepository chatRepository) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
    }

    /**
     * Returns a list of all registered users.
     * @param loggedInUser logged in user
     * @return list of users
     */
    @GetMapping("/all")
    public @ResponseBody List<UserView> allUsers(Principal loggedInUser) {
        throwIfUserNotExists();
        List<UserView> users = new ArrayList<>();
        if (userRepository.findByUsername(loggedInUser.getName()).getAccountType().equals(AccountType.ADMIN)) {
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
     * @param loggedInUser logged in user
     * @param id user id
     * @return user information
     */
    @GetMapping("/{id}")
    public @ResponseBody
    UserView getUserInfo(Principal loggedInUser, @PathVariable Long id) {
        throwIfUserNotExists(id);
        if (hasPermission(loggedInUser, id)) {
            return new UserPrivateView(userRepository.findById(id).get());
        }
        return new UserPublicView(userRepository.findById(id).get());
    }

    /**
     * Deletes user.
     * @param id user id
     * @return user id
     */
    @DeleteMapping("/{id}")
    public @ResponseBody Long deleteUser(Principal loggedInUser, @PathVariable Long id) {
        throwIfUserNotExists(id);
        if (!hasPermission(loggedInUser, id)) {
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
        User userById = userRepository.findByUsername(loggedInUser.getName());
        if (!userById.getAccountType().equals(AccountType.ADMIN)) {
            throw new AccessDeniedException("You don't have permission to block users.");
        }
        userById.setAccountType(AccountType.BLOCKED);
        return id;
    }

    /**
     * Returns a list of all chats available to user.
     * @param loggedInUser logged in user
     * @param id user id
     * @return list of chats
     */
    @GetMapping("/{id}/chats")
    public @ResponseBody
    List<PersonalChat> getUserChats(Principal loggedInUser, @PathVariable Long id) {
        throwIfUserNotExists(id);
        if (!hasPermission(loggedInUser, id)) {
            throw new AccessDeniedException(
                    String.format("You don't have permission to access chat(s) of the user with id: %d", id));
        }
        return chatRepository.findByUserId(id);
    }

    /**
     * Checks if the user with the given id exists.
     * @param id user id
     * @throws UserNotFoundException user is not found
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
    private void throwIfUserNotExists() throws UserNotFoundException {
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
    private boolean hasPermission(Principal loggedInUser, Long id) {
        return userRepository.findByUsername(loggedInUser.getName()).getId().equals(id) ||
                userRepository.findByUsername(loggedInUser.getName()).getAccountType().equals(AccountType.ADMIN);
    }
}