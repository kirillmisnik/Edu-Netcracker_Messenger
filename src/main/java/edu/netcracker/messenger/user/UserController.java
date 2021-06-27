package edu.netcracker.messenger.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    /**
     * Регистрирует пользователя и выделяет ему id.
     * @return id пользователя
     */
    @PutMapping("/{id}")
    public @ResponseBody String registerUser(@PathVariable Long id) {
        return String.format("User %d was created", id);
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
     * Возвращает информацию о пользователе по id.
     * @param id id пользователя
     * @return информацию о пользователе
     */
    @GetMapping("/{id}")
    public @ResponseBody String getUserInfo(@PathVariable Long id) {
        return String.format("User %d info", id);
    }

    /**
     * Удаляет пользователя по id.
     * @param id id пользователя
     * @return подтверждение об удалении
     */
    @DeleteMapping("/{id}")
    public @ResponseBody String deleteUser(@PathVariable Long id) {
        return String.format("User %d was deleted", id);
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