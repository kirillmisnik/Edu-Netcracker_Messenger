package edu.netcracker.messenger.entities.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long>  {

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    User findByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE phone_number = ?1", nativeQuery = true)
    User findByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    User findByEmail(String email);

//    @Query(value = "SELECT * FROM users JOIN chat_members ON users.user_id = chat_members.user_id WHERE chat_members.chat_id = ?1", nativeQuery = true)
//    Set<User> getChatUsers(Long chatId);

//    /**
//     * Finds all chat members excluding given user.
//     * @param userId user id
//     * @param chatId chat id
//     * @return set of users
//     */
//    @Query(value = "SELECT * FROM users JOIN chat_members ON users.user_id = chat_members.user_id WHERE chat_members.chat_id = ?2 AND users.user_id != ?1", nativeQuery = true)
//    Set<User> findRecipients(Long userId, Long chatId);
}