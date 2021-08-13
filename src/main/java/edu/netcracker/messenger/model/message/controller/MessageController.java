package edu.netcracker.messenger.model.message.controller;

import edu.netcracker.messenger.model.chat.exceptions.ChatNotFoundException;
import edu.netcracker.messenger.model.chat.exceptions.MessageNotFoundException;
import edu.netcracker.messenger.model.message.Message;
import edu.netcracker.messenger.model.message.MessageRepository;
import edu.netcracker.messenger.model.user.AccountType;
import edu.netcracker.messenger.model.user.User;
import edu.netcracker.messenger.model.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

@Controller
//@CrossOrigin("http://localhost:3000")
@RequestMapping("api/1.0/message")
public class MessageController {

    /**
     * Message JPA repository.
     */
    private final MessageRepository messageRepository;

    /**
     * User JPA repository.
     */
    private final UserRepository userRepository;

    public MessageController(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    /**
     * Marks all messages as read.
     * @param loggedInUser logged in user
     * @param messageId message id
     * @return message id
     */
    @PostMapping("{messageId}/read")
    public @ResponseBody
    Long markMessageAsRead(Principal loggedInUser, @PathVariable Long messageId) throws AccessDeniedException {
        throwIfMessageNotExists(messageId);
        if (!hasAccessToMessage(loggedInUser, messageId)) {
            throw new AccessDeniedException("You don't have permission to mark this message as read");
        }
        for (Message message : messageRepository.findPreviousMessages(messageId)) {
            if (message.getReadDate() == null) {
                message.setReadDate();
                messageRepository.save(message);
            }
        }
        return messageId;
    }

    @DeleteMapping("{messageId}")
    public @ResponseBody Long deleteMessage(Principal loggedInUser, @PathVariable Long messageId)
            throws AccessDeniedException {
        throwIfMessageNotExists(messageId);
        if (!hasAccessToMessage(loggedInUser, messageId)) {
            throw new AccessDeniedException("You don't have permission to delete this message");
        }
        messageRepository.deleteById(messageId);
        return messageId;
    }

    /**
     * Проверяет, существует ли message с заданным id.
     * @param messageId id чата
     * @throws ChatNotFoundException чат не найден
     */
    private void throwIfMessageNotExists(Long messageId) throws MessageNotFoundException {
        if (messageRepository.findById(messageId).isEmpty()) {
            throw new MessageNotFoundException(messageId);
        }
    }

    private boolean hasAccessToMessage(Principal loggedInUser, Long messageId) {
        User user = userRepository.findByUsername(loggedInUser.getName());
        Message message = messageRepository.findById(messageId).get();
        return user.getId().equals(message.getSenderId()) || user.getAccountType().equals(AccountType.ADMIN);
    }
}
