package edu.netcracker.messenger.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonalChatRepository extends JpaRepository<PersonalChat, Long> {
    @Query(value = "SELECT DISTINCT * FROM personal_chats WHERE sender_id = ?1 OR recipient_id = ?1", nativeQuery = true)
    List<PersonalChat> findByUserId(Long userId);
}
