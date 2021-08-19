package edu.netcracker.messenger.models.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>  {

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    User getByUsername(String username);

    @Query(value = "SELECT * FROM users WHERE phone_number = ?1", nativeQuery = true)
    User findByPhoneNumber(String phoneNumber);

    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    User findByEmail(String email);

}