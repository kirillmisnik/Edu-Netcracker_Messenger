package edu.netcracker.messenger.model.chat.controller;

import edu.netcracker.messenger.model.chat.*;
import edu.netcracker.messenger.model.chat.exceptions.ChatNotFoundException;
import edu.netcracker.messenger.model.chat.exceptions.PersonalChatAlreadyExists;
import edu.netcracker.messenger.model.chat.view.ChatBodyView;
import edu.netcracker.messenger.model.message.Message;
import edu.netcracker.messenger.model.message.MessageRepository;
import edu.netcracker.messenger.model.message.view.MessageBody;
import edu.netcracker.messenger.model.user.User;
import edu.netcracker.messenger.model.user.UserRepository;
import edu.netcracker.messenger.model.user.exceptions.UserNotFoundException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

/**
 * Controller for handling requests related to chats.
 */
@Controller
//@CrossOrigin("http://localhost:3000")
@RequestMapping("api/1.0/chat")
public class ChatController {

    private final ChatRepository chatRepository;

    private final MessageRepository messageRepository;

    private final UserRepository userRepository;

    public ChatController(ChatRepository chatRepository,
                          MessageRepository messageRepository,
                          UserRepository userRepository) {
        this.chatRepository = chatRepository;
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public @ResponseBody
    Chat createChat(Principal loggedInUser, @RequestBody ChatBodyView chatBody) {
        List<Long> errors = new ArrayList<>();
        for (Long userId : chatBody.getChatMembersId()) {
            if (userRepository.findById(userId).isEmpty()) {
                errors.add(userId);
            }
        }
        if (!errors.isEmpty()) {
            throw new UserNotFoundException(errors);
        }
        List<Long> chatMembers = new ArrayList<>(
                Collections.singletonList(userRepository.findByUsername(loggedInUser.getName()).getId()));
        chatMembers.addAll(chatBody.getChatMembersId());
        Chat chat = new Chat(chatMembers);
        if (chat.getChatType().equals(ChatType.GROUP)) {
            if (chatBody.getChatName() != null) {
                chat.setChatName(chatBody.getChatName());
            }
            if (chatBody.getChatPictureId() != null) {
                chat.setChatPictureId(chatBody.getChatPictureId());
            }
        }
        checkIfPersonalChatExists(chat);
        chatRepository.save(chat);
        return chat;
    }

    /**
     * Returns chat information. The information depends on the user who makes the request.
     * @param loggedInUser logged in user
     * @param chatId chat id
     * @return chat information
     */
    @GetMapping("/{chatId}")
    public @ResponseBody
    Chat getChatInfo(Principal loggedInUser, @PathVariable Long chatId) {
//        throwIfChatHasErrors(loggedInUser, chatId);
        Chat chat = chatRepository.findById(chatId).get();
        if (chat.getChatType().equals(ChatType.PERSONAL)) {
            List<Long> chatMembers = chat.getChatMembersId();
            chatMembers.remove(0);
            User recipient = userRepository.findById(chatMembers.get(0)).get();
            chat.setChatName(String.format("%s %s", recipient.getFirstName(), recipient.getLastName()));
            chat.setChatPictureId(recipient.getProfilePictureId());
        }
        return chat;
    }

    /**
     * Возвращает все сообщения чата.
     * @param chatId id чата
     * @return список сообщений чата
     */
    @GetMapping("/{chatId}/messages/all")
    public @ResponseBody
    List<Message> getChatMessages(Principal loggedInUser, @PathVariable Long chatId) {
//        throwIfChatHasErrors(loggedInUser, chatId);
        return messageRepository.findMessages(chatId);
    }

    @GetMapping("/{chatId}/messages")
    public @ResponseBody List<Message> getChatMessagesByPage(Principal loggedInUser, @PathVariable Long chatId,
                                                             @RequestParam(name = "page_size", required = false) Integer pageSize,
                                                             @RequestParam(name = "page_num", required = false) Integer pageNum) {
        throwIfChatHasErrors(loggedInUser, chatId);
        return messageRepository.findMessagesByPage(chatId, pageSize == null ? 10 : pageSize, pageNum == null ? 1 : pageNum);
    }

    /**
     * Возвращает последнее сообщение чата.
     * @param chatId id чата
     * @return последнее сообщение
     */
    @GetMapping("/{chatId}/latest")
    public @ResponseBody Message getNewMessage(Principal loggedInUser, @PathVariable Long chatId) {
//        throwIfChatHasErrors(loggedInUser, chatId);
        return messageRepository.latestInChat(chatId);
    }

    /**
     * Удаляет чат.
     * @param chatId id чата
     * @return подтверждение удаления
     */
    @DeleteMapping("/{chatId}")
    public @ResponseBody Long deleteChat(Principal loggedInUser, @PathVariable Long chatId) {
        throwIfChatHasErrors(loggedInUser, chatId);
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
    Long sendMessage(Principal loggedInUser, @PathVariable Long chatId, @RequestBody MessageBody messageBody) {
//        throwIfChatHasErrors(loggedInUser, chatId);
//        Message message = new Message(chatId, userRepository.findByUsername(loggedInUser.getName()).getId(),
        Message message = new Message(chatId, userRepository.findByUsername("kirillmisnik").getId(),
                messageBody.getText());
        messageRepository.save(message);
        return message.getMessageId();
    }

    private Long getId(Principal loggedInUser) {
        return userRepository.findByUsername(loggedInUser.getName()).getId();
    }

    private void checkIfPersonalChatExists(Chat chat) throws PersonalChatAlreadyExists {
        List<Long> chatMembers = chat.getChatMembersId();
        if (chatRepository.findPersonalChat(chatMembers.get(0), chatMembers.get(1)) != null &&
                chat.getChatType().equals(ChatType.PERSONAL)) {
            throw new PersonalChatAlreadyExists(chatMembers.get(1));
        }
    }

    private void throwIfChatHasErrors(Principal loggedInUser, Long chatId) throws ChatNotFoundException,
            AccessDeniedException {
        if (chatRepository.findById(chatId).isEmpty()) {
            throw new ChatNotFoundException(chatId);
        }
        if (!chatRepository.findById(chatId).get().getChatMembersId().contains(getId(loggedInUser))) {
            throw new AccessDeniedException("You don't have permission to chat with id: " + chatId);
        }
    }
}
