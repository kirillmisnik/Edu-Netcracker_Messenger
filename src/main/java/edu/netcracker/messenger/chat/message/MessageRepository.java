package edu.netcracker.messenger.chat.message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM messages WHERE chat_id = ?1", nativeQuery = true)
    List<Message> findByChatId(Long chatId);

    @Query(value = "SELECT * FROM messages WHERE chat_id = ?1 ORDER BY creation_date DESC LIMIT 1", nativeQuery = true)
    Message latestInChat(Long chatId);

    @Query(value = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY creation_date) AS RowNum, * FROM messages WHERE creation_date <= (SELECT creation_date FROM messages WHERE message_id = ?1)) as RowConstrainedResult WHERE RowNum >= 1 AND RowNum < 10 ORDER BY RowNum", nativeQuery = true)
    List<Message> findByMessageId(Long messageId);

    @Query(value = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY creation_date) AS RowNum, * FROM messages WHERE creation_date <= (SELECT creation_date FROM messages WHERE message_id = ?1)) as RowConstrainedResult WHERE RowNum >= 1 AND RowNum < ?2+1 ORDER BY RowNum", nativeQuery = true)
    List<Message> findByMessageId(Long messageId, Long pageSize);

    @Query(value = "SELECT * FROM (SELECT ROW_NUMBER() OVER (ORDER BY creation_date) AS RowNum, * FROM messages WHERE creation_date <= (SELECT creation_date FROM messages WHERE message_id = ?1)) as RowConstrainedResult WHERE RowNum >= ?2*?3 AND RowNum < ?2*?3+?2 ORDER BY RowNum", nativeQuery = true)
    List<Message> findByMessageId(Long messageId, Long pageSize, Long pageNum);
}
