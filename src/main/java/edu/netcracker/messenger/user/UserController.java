package edu.netcracker.messenger.user;

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
     * @return пользователь
     */
    @PutMapping()
    public @ResponseBody User registerUser(@RequestBody User newUser) {
        return repository.save(newUser);
    }

    /**
     * Возвращает список всех зарегистрированных пользователей.
     * @return зарегистрированные пользователи
     */
    @GetMapping()
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
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        repository.deleteById(id);
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