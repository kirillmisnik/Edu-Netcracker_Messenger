package edu.netcracker.messenger.chat.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM messages WHERE chat_id = ?1", nativeQuery = true)
    List<Message> findByChatId(Long chatId);
}
