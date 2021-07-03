package edu.netcracker.messenger.user.controller;

import edu.netcracker.messenger.user.User;
import edu.netcracker.messenger.user.exceptions.UserNotFoundException;
import edu.netcracker.messenger.user.UserRepository;
import edu.netcracker.messenger.user.views.UserPrivateView;
import edu.netcracker.messenger.user.views.UserPublicView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody List<UserPublicView> allUsers() throws UserNotFoundException {
        if (repository.findAll().isEmpty()) {
            throw new UserNotFoundException();
        }
        List<UserPublicView> users = new ArrayList<>();
        for (User user : repository.findAll()) {
            users.add(new UserPublicView(user));
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
    UserPrivateView getUserInfo(@PathVariable Long id) throws UserNotFoundException {
        if (repository.findById(id).isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return new UserPrivateView(repository.findById(id).get());
    }

    /**
     * Удаляет пользователя по id.
     * @param id id пользователя
     * @return id удаленного пользователя в случае успеха
     * @throws UserNotFoundException пользователь с даннм id не найден
     */
    @DeleteMapping("/{id}")
    public @ResponseBody Long deleteUser(@PathVariable Long id) throws UserNotFoundException {
        if (repository.findById(id).isEmpty()) {
            throw new UserNotFoundException(id);
        }
        repository.deleteById(id);
        return id;
    }

    /**
     * Выполняет вход пользователя по id.
     * @param id id пользователя
     * @return подтверждение входа
     */
    @PostMapping("/login/{id}")
    public @ResponseBody String loginUser(@PathVariable Long id) {
        return String.format("Session started with user %d", id);
    }

    /**
     * Возвращает список id всех чатов, доступных пользователю.
     * @param id id пользователя
     * @return список id чатов
     */
    @GetMapping("/{id}/chats")
    public @ResponseBody String getChatsFromUser(@PathVariable Long id){
        return String.format("All user %d chats", id);
    }
}