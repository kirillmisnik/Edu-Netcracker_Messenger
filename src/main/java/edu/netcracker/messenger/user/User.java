package edu.netcracker.messenger.user;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private byte[] password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "bio")
    private String bio;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "account_type_id")
    @JsonIgnore
    private int accountTypeId = 2;

    @Column(name = "account_creation_date")
    @JsonIgnore
    private final LocalDateTime accountCreationDate = LocalDateTime.now();

    @Column(name = "last_login_date")
    @JsonIgnore
    private LocalDateTime lastLoginDate;

    @Column(name = "last_online_date")
    @JsonIgnore
    private LocalDateTime lastOnlineDate;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public byte[] getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBio() {
        return bio;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public LocalDateTime getAccountCreationDate() {
        return accountCreationDate;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public LocalDateTime getLastOnlineDate() {
        return lastOnlineDate;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public void setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public void setLastOnlineDate(LocalDateTime lastOnlineDate) {
        this.lastOnlineDate = lastOnlineDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return accountTypeId == user.accountTypeId && Objects.equals(id, user.id) && Objects.equals(username, user.username) && Arrays.equals(password, user.password) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(bio, user.bio) && Objects.equals(phoneNumber, user.phoneNumber) && Objects.equals(email, user.email) && Objects.equals(accountCreationDate, user.accountCreationDate) && Objects.equals(lastLoginDate, user.lastLoginDate) && Objects.equals(lastOnlineDate, user.lastOnlineDate);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, username, firstName, lastName, bio, phoneNumber, email, accountTypeId, accountCreationDate, lastLoginDate, lastOnlineDate);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password=" + Arrays.toString(password) +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", bio='" + bio + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", accountTypeId=" + accountTypeId +
                ", accountCreationDate=" + accountCreationDate +
                ", lastLoginDate=" + lastLoginDate +
                ", lastOnlineDate=" + lastOnlineDate +
                '}';
    }
}
