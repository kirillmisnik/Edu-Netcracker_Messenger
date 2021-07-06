package edu.netcracker.messenger.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.netcracker.messenger.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "personal_chats")
public class PersonalChat {

    @Id
    @Column(name = "chat_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long chatId;

    @Column(name = "chat_members_id")
    private Long chatMembersId;

    @Column(name = "chat_type_id")
    private Long chatTypeId;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "chat_name")
    private String chatName;
}
