package br.com.mmaverse.service;

import br.com.mmaverse.entity.User;
import br.com.mmaverse.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        findByEmail(user.getEmail())
                .ifPresent(u -> {throw new IllegalArgumentException("An account with this email already exists");});
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
