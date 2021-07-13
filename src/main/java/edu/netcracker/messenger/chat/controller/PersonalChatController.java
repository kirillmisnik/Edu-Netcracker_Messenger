package edu.netcracker.messenger.chat.controller;

import edu.netcracker.messenger.chat.PersonalChat;
import edu.netcracker.messenger.chat.PersonalChatRepository;
import edu.netcracker.messenger.chat.exceptions.ChatNotFoundException;
import edu.netcracker.messenger.chat.message.Message;
import edu.netcracker.messenger.chat.message.MessageRepository;
import edu.netcracker.messenger.chat.message.view.MessageBody;
import edu.netcracker.messenger.user.User;
import edu.netcracker.messenger.user.UserRepository;
import edu.netcracker.messenger.user.exceptions.UserNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Controller for handling requests related to personal messages.
 */
@Controller
@RequestMapping("chat/personal")
public class PersonalChatController {

    /**
     * Personal chat JPA repository.
     */
    private final PersonalChatRepository chatRepository;

    /**
     * Message JPA repository.
     */
    private final MessageRepository messageRepository;

    /**
     * User JPA repository.
     */
    private final UserRepository userRepository;

    public PersonalChatController(PersonalChatRepository chatRepository, MessageRepository messageRepository,
                                  UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a personal chat with user (if it does not already exists).
     * @param user logged in user
     * @param id user id
     * @return created chat id
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
     * Returns chat information. The information depends on the user who makes the request.
     * @param user logged in user
     * @param chatId chat id
     * @return chat information
     */
    @GetMapping("/{chatId}")
    public @ResponseBody PersonalChat getChatInfo(Principal user, @PathVariable Long chatId) {
        throwIfChatNotExists(chatId);
        return chatRepository.findById(chatId).get();
    }

    /**
     * Возвращает все сообщения чата.
     * @param chatId id чата
     * @return список сообщений чата
     */
    @GetMapping("/{chatId}/messages/all")
    public @ResponseBody
    List<Message> getChatMessages(Principal user, @PathVariable Long chatId) {
        throwIfChatNotExists(chatId);
        return messageRepository.findMessages(chatId);
    }

    /**
     * Возвращает последнее сообщение чата.
     * @param chatId id чата
     * @return последнее сообщение
     */
    @GetMapping("/{chatId}/latest")
    public @ResponseBody Message getNewMessage(Principal user, @PathVariable Long chatId) {
        throwIfChatNotExists(chatId);
        return messageRepository.latestInChat(chatId);
    }

    @GetMapping("/{chatId}/messages")
    public @ResponseBody List<Message> getChatMessagesByPage(Principal user, @PathVariable Long chatId,
                                                             @RequestParam(name = "page_size", required = false) Integer pageSize,
                                                             @RequestParam(name = "page_num", required = false) Integer pageNum) {
        throwIfChatNotExists(chatId);
        return messageRepository.findMessagesByPage(chatId, pageSize == null ? 10 : pageSize, pageNum == null ? 1 : pageNum);
    }

    /**
     * Удаляет чат.
     * @param chatId id чата
     * @return подтверждение удаления
     */
    @DeleteMapping("/{chatId}")
    public @ResponseBody Long deleteChat(Principal user, @PathVariable Long chatId) {
        throwIfChatNotExists(chatId);
        messageRepository.deleteAll(messageRepository.findMessages(chatId));
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
        throwIfChatNotExists(chatId);
        Message message = new Message(chatId, userRepository.findByUsername(user.getName()).getId(),
                messageBody.getText());
        messageRepository.save(message);

        return message.getMessageId();
    }

    /**
     * Проверяет, существует ли чат с заданным id.
     * @param id id чата
     * @throws ChatNotFoundException чат не найден
     */
    private void throwIfChatNotExists(Long id) throws ChatNotFoundException {
        if (chatRepository.findById(id).isEmpty()) {
            throw new ChatNotFoundException(id);
        }
    }
}
