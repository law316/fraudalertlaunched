package net.ikwa.fraudalert.service;

import net.ikwa.fraudalert.model.UserModel;
import net.ikwa.fraudalert.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserModel login(String username, String password) {


        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new RuntimeException("Invalid username or password");
        }

        Optional<UserModel> user = userRepository.findByEmail(username);
        if (user.isPresent()) {
            UserModel userModel1 = user.get();
            if (passwordEncoder.matches(password, userModel1.getPassword())) {
                return userModel1;
            } else {
                throw new RuntimeException("Invalid password");
            }
        }

        // ✅ Added return or exception for missing case
        throw new RuntimeException("User not found");
    }
}

