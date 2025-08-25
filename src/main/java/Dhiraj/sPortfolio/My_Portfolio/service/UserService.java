package Dhiraj.sPortfolio.My_Portfolio.service;

import Dhiraj.sPortfolio.My_Portfolio.entity.Role;
import Dhiraj.sPortfolio.My_Portfolio.entity.User;
import Dhiraj.sPortfolio.My_Portfolio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createAdmin(String username, String rawPassword) {
        if (userRepository.existsByUsername(username)) {
            throw new RuntimeException("User already exists");
        }
        User user = User.builder()
                .username(username)
                .password(passwordEncoder.encode(rawPassword))
                .role(Role.ADMIN)
                .build();
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
