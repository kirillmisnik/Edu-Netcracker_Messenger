package edu.netcracker.messenger.user.controller;

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

@Controller
@RequestMapping("user")
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Возвращает список всех зарегистрированных пользователей.
     * @return зарегистрированные пользователи
     * @throws UserNotFoundException пользователей не найдено
     */
    @GetMapping("/all")
    public @ResponseBody List<UserView> allUsers(Principal principal) throws UserNotFoundException {
        if (repository.findAll().isEmpty()) {
            throw new UserNotFoundException();
        }
        List<UserView> users = new ArrayList<>();
        if (repository.findByUsername(principal.getName()).getAccountType().equals(AccountType.ADMIN)) {
            for (User user : repository.findAll()) {
                users.add(new UserPrivateView(user));
            }
        } else {
            for (User user : repository.findAll()) {
                users.add(new UserPublicView(user));
            }
        }
        return users;
    }

    /**
     * Возвращает информацию о пользователе по id.
     * @param id id пользователя
     * @return информацию о пользователе
     * @throws UserNotFoundException пользователь с даннм id не найден
     */
    @GetMapping("/{id}")
    public @ResponseBody
    UserView getUserInfo(Principal principal, @PathVariable Long id) throws UserNotFoundException {
        if (repository.findById(id).isEmpty()) {
            throw new UserNotFoundException(id);
        }
        if (repository.findByUsername(principal.getName()).getId().equals(id) ||
                repository.findByUsername(principal.getName()).getAccountType().equals(AccountType.ADMIN))  {
            return new UserPrivateView(repository.findById(id).get());
        }
        return new UserPublicView(repository.findById(id).get());
    }

    /**
     * Удаляет пользователя по id.
     * @param id id пользователя
     * @return id удаленного пользователя в случае успеха
     * @throws UserNotFoundException пользователь с даннм id не найден
     * @throws AccessDeniedException нет прав на удаление пользователя
     */
    @DeleteMapping("/{id}")
    public @ResponseBody Long deleteUser(Principal principal, @PathVariable Long id)
            throws UserNotFoundException, AccessDeniedException {
        if (repository.findById(id).isEmpty()) {
            throw new UserNotFoundException(id);
        }
        if (!repository.findByUsername(principal.getName()).getId().equals(id) &&
                !repository.findByUsername(principal.getName()).getAccountType().equals(AccountType.ADMIN))  {
            throw new AccessDeniedException(String.format("You don't have permission to delete user with id: %d", id));
        }
        repository.deleteById(id);
        return id;
    }

    /**
     * Возвращает список id всех чатов, доступных пользователю.
     * @param id id пользователя
     * @return список id чатов
     */
    @GetMapping("/{id}/chats")
    public @ResponseBody String getUserChats(@PathVariable Long id) {

        return String.format("All user %d chats", id);
    }
}