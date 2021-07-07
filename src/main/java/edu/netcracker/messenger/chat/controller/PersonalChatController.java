package edu.netcracker.messenger.chat.controller;

import edu.netcracker.messenger.chat.PersonalChat;
import edu.netcracker.messenger.chat.PersonalChatRepository;
import edu.netcracker.messenger.chat.exceptions.ChatNotFoundException;
import edu.netcracker.messenger.chat.message.Message;
import edu.netcracker.messenger.chat.message.MessageRepository;
import edu.netcracker.messenger.chat.message.view.MessageBody;
import edu.netcracker.messenger.chat.message.view.MessageView;
import edu.netcracker.messenger.user.User;
import edu.netcracker.messenger.user.UserRepository;
import edu.netcracker.messenger.user.exceptions.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("chat/personal")
public class PersonalChatController {

    private final PersonalChatRepository chatRepository;

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    @PersistenceContext
    EntityManager entityManager;

    public PersonalChatController(PersonalChatRepository chatRepository,
                                  MessageRepository messageRepository,
                                  UserRepository userRepository) {
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
        if (userRepository.findById(id).isEmpty()) {
            throw new UserNotFoundException(id);
        }
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
    public @ResponseBody PersonalChat getChatMessages(@PathVariable Long chatId) {
        if (chatRepository.findById(chatId).isEmpty()) {
            throw new ChatNotFoundException(chatId);
        }
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
        if (chatRepository.findById(chatId).isEmpty()) {
            throw new ChatNotFoundException(chatId);
        }
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
    public @ResponseBody Long deleteChat(@PathVariable Long chatId) {
        if (chatRepository.findById(chatId).isEmpty()) {
            throw new ChatNotFoundException(chatId);
        }
        messageRepository.deleteAll(messageRepository.findByChatId(chatId));
        chatRepository.deleteById(chatId);
        return chatId;
    }

    /**
     * Добавляет сообщение в чат.
     * @param chatId id чата
     * @return подтверждение отправки
     */
    @PostMapping("/{chatId}")
    public @ResponseBody
    Long sendMessage(Principal user, @PathVariable Long chatId, @RequestBody MessageBody messageBody) {
        if (chatRepository.findById(chatId).isEmpty()) {
            throw new ChatNotFoundException(chatId);
        }
        Message message = new Message(chatId, userRepository.findByUsername(user.getName()).getId(),
                messageBody.getText());
        messageRepository.save(message);

        return message.getMessageId();
    }
}
