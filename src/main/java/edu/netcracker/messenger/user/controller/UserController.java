package edu.netcracker.messenger.user.controller;

import edu.netcracker.messenger.user.User;
import edu.netcracker.messenger.user.UserNotFoundException;
import edu.netcracker.messenger.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Регистрирует пользователя.
     * @return id пользователя
     */
    @PutMapping()
    public @ResponseBody
    Long registerUser(@RequestBody User newUser) {
        repository.save(newUser);
        return newUser.getId();
    }

    /**
     * Возвращает список всех зарегистрированных пользователей.
     * @return зарегистрированные пользователи
     */
    @GetMapping("/all")
    public @ResponseBody List<User> allUsers() {
        return repository.findAll();
    }

    /**
     * Возвращает информацию о пользователе по id.
     * @param id id пользователя
     * @return информацию о пользователе
     */
    @GetMapping("/{id}")
    public @ResponseBody User getUserInfo(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Удаляет пользователя по id.
     * @param id id пользователя
     * @return id удаленного пользователя в случае успеха
     */
    @DeleteMapping("/{id}")
    public @ResponseBody Long deleteUser(@PathVariable Long id) {
        if (!repository.existsById(id)) {
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
    @PostMapping("/{id}")
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