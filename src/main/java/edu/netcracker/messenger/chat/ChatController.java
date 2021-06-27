package edu.netcracker.messenger.chat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("chat")
public class ChatController {

    /**
     * Создает чат.
     * @param id id адресата
     * @return id созданного чата
     */
    @PostMapping("/create/{id}")
    public @ResponseBody
    String createChat(@PathVariable Long id){
        return String.format("Created chat with user %d", id);
    }

    /**
     * Возвращает сообщения чата.
     * @param chatId id чата
     * @return очередь сообщений чата
     */
    @GetMapping("/{chatId}")
    public @ResponseBody String getChatMessages(@PathVariable Long chatId){
        return String.format("All messages from chat %d", chatId);
    }

    /**
     * Удаляет чат.
     * @param chatId id чата
     * @return подтверждение удаления
     */
    @DeleteMapping("/{chatId}")
    public @ResponseBody String deleteChat(@PathVariable Long chatId){
        return String.format("Chat %d was deleted", chatId);
    }

    /**
     * Добавляет сообщение в чат.
     * @param chatId id чата
     * @return подтверждение отправки
     */
    @PostMapping("/{chatId}/message")
    public @ResponseBody
    String sendMessage(@PathVariable Long chatId){
        return String.format("Message sent to chat %d", chatId);
    }
}
