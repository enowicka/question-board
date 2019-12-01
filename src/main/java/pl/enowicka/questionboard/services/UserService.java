package pl.enowicka.questionboard.services;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.enowicka.questionboard.dtos.UserRegistration;
import pl.enowicka.questionboard.model.Role;
import pl.enowicka.questionboard.model.User;
import pl.enowicka.questionboard.repositories.UserRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService( UserRepository userRepository, PasswordEncoder passwordEncoder ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean checkIfMailIsTaken(String email) {
        return userRepository.checkIfEmailExists(email);
    }

    public boolean checkIfNameIsTaken(String name) {
        return userRepository.checkIfNameExists(name);
    }

    public void createUser(UserRegistration registration) {
        User user = new User();
        user.setName(registration.getName());
        user.setEmail(registration.getEmail());
        user.setRole(Role.USER);
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(registration.getPassword()));
        userRepository.save(user);
    }
}
