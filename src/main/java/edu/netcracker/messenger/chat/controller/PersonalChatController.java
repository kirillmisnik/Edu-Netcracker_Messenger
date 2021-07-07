package edu.netcracker.messenger.chat.controller;

import edu.netcracker.messenger.chat.PersonalChat;
import edu.netcracker.messenger.chat.PersonalChatRepository;
import edu.netcracker.messenger.chat.message.Message;
import edu.netcracker.messenger.chat.message.MessageRepository;
import edu.netcracker.messenger.chat.message.view.MessageView;
import edu.netcracker.messenger.user.User;
import edu.netcracker.messenger.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("chat/personal")
public class PersonalChatController {

    private final PersonalChatRepository chatRepository;

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    public PersonalChatController(PersonalChatRepository chatRepository, MessageRepository messageRepository, UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    /**
     * Создает чат.
     * @param id id адресата
     * @return id созданного чата
     */
    @PostMapping("/create/{id}")
    public @ResponseBody
    PersonalChat createChat(Principal user, @PathVariable Long id) {
        User sender = userRepository.findByUsername(user.getName());
        User recipient = userRepository.findById(id).get();
        PersonalChat chat = new PersonalChat(sender.getId(), id, recipient.getUsername());
        chatRepository.save(chat);
        return chat;
    }

    /**
     * Возвращает сообщения чата.
     * @param chatId id чата
     * @return очередь сообщений чата
     */
    @GetMapping("/{chatId}")
    public @ResponseBody PersonalChat getChatMessages(@PathVariable Long chatId){
        return chatRepository.findById(chatId).get();
    }

    /**
     * Возвращает сообщения чата.
     * @param chatId id чата
     * @return очередь сообщений чата
     */
    @GetMapping("/{chatId}/messages")
    public @ResponseBody
    List<MessageView> getMessages(@PathVariable Long chatId) {
        List<MessageView> messages = new ArrayList<>();
        for (Message message : messageRepository.findByChatId(chatId)) {
            messages.add(new MessageView(message.getSenderId(), message.getText(), message.getAttachmentId(),
                    message.getAttachmentFilename(), message.getReadDate()));
        }
        return messages;
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
    @PostMapping("/{chatId}")
    public @ResponseBody
    String sendMessage(@PathVariable Long chatId){
        return String.format("Message sent to chat %d", chatId);
    }
}
