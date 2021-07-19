package edu.netcracker.messenger.entities.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ChatRepository extends JpaRepository<Chat, Long> {

    @Query(value = "SELECT * FROM chats JOIN chat_members ON chats.chat_id = chat_members.chat_id WHERE chat_members.user_id = ?1", nativeQuery = true)
    List<Chat> findByUserId(Long userId);

    @Query(value = "SELECT COUNT(*) FROM chats JOIN chat_members ON chats.chat_id = chat_members.chat_id WHERE (chat_members.user_id = ?1 OR chat_members.user_id = ?2) AND chats.chat_type = 'PERSONAL' GROUP BY chats.chat_id HAVING COUNT(*) = 2", nativeQuery = true)
    Long findPersonalChat(Long firstUserId, Long secondUserId);
}
