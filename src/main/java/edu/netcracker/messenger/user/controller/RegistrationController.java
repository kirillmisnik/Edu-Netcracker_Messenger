package edu.netcracker.messenger.user.controller;

import edu.netcracker.messenger.user.User;
import edu.netcracker.messenger.user.UserRepository;
import edu.netcracker.messenger.user.exceptions.UserAlreadyExistsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("registration")
public class RegistrationController {
    private final UserRepository repository;

    RegistrationController(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Регистрирует пользователя.
     * @return id пользователя
     * @throws UserAlreadyExistsException пользователь уже существует
     */
    @PostMapping
    public @ResponseBody
    Long registerUser(@RequestBody User newUser) throws UserAlreadyExistsException {
        Map<String, String> errors = new TreeMap<>();
        if (repository.findByUsername(newUser.getUsername()) != null) {
            errors.put("username", newUser.getUsername());
        }
        if (repository.findByPhoneNumber(newUser.getPhoneNumber()) != null) {
            errors.put("phone number", newUser.getPhoneNumber());
        }
        if (repository.findByEmail(newUser.getEmail()) != null) {
            errors.put("email", newUser.getEmail());
        }
        if (!errors.isEmpty()) {
            throw new UserAlreadyExistsException(errors);
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        repository.save(newUser);
        return newUser.getId();
    }
}
