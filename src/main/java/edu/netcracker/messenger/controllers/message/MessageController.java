package edu.netcracker.messenger.controllers.message;

import edu.netcracker.messenger.models.chat.exceptions.ChatNotFoundException;
import edu.netcracker.messenger.models.message.exceptions.MessageNotFoundException;
import edu.netcracker.messenger.models.message.Message;
import edu.netcracker.messenger.models.message.MessageRepository;
import edu.netcracker.messenger.models.user.AccountType;
import edu.netcracker.messenger.models.user.User;
import edu.netcracker.messenger.models.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;

@Controller
@RequestMapping("api/message")
public class MessageController {

    private final MessageRepository messageRepository;

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
        if (hasAccessToMessage(loggedInUser, messageId)) {
            throw new AccessDeniedException("You don't have permission to mark this message as read");
        }
        for (Message message : messageRepository.findPreviousMessages(messageId)) {
            if (message.getReadDate() == null) {
                message.markAsRead();
                messageRepository.save(message);
            }
        }
        return messageId;
    }

    @DeleteMapping("{messageId}")
    public @ResponseBody Long deleteMessage(Principal loggedInUser, @PathVariable Long messageId)
            throws AccessDeniedException {
        throwIfMessageNotExists(messageId);
        if (hasAccessToMessage(loggedInUser, messageId)) {
            throw new AccessDeniedException("You don't have permission to delete this message");
        }
        messageRepository.deleteById(messageId);
        return messageId;
    }

    private void throwIfMessageNotExists(Long messageId) throws MessageNotFoundException {
        if (messageRepository.findById(messageId).isEmpty()) {
            throw new MessageNotFoundException(messageId);
        }
    }

    private boolean hasAccessToMessage(Principal loggedInUser, Long messageId) {
        User user = userRepository.getByUsername(loggedInUser.getName());
        Message message = messageRepository.getById(messageId);
        return !user.getId().equals(message.getSenderId()) && !user.getAccountType().equals(AccountType.ADMIN);
    }
}
