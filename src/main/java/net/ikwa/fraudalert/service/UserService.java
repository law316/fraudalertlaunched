package net.ikwa.fraudalert.service;

import net.ikwa.fraudalert.model.UserModel;
import net.ikwa.fraudalert.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public void saveUser(UserModel user) {
        if (user.getFirstName() == null || user.getFirstName().isEmpty() ||
                user.getLastName() == null || user.getLastName().isEmpty() ||
                user.getEmail() == null || user.getEmail().isEmpty() ||
                user.getPassword() == null || user.getPassword().isEmpty() ||
                user.getPhone() == null || user.getPhone().isEmpty() ||
                user.getCountry() == null || user.getCountry().isEmpty() ||
                user.getGender() == null || user.getGender().isEmpty()) {
            throw new RuntimeException("All fields must be completed");
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            throw new RuntimeException("Passwords do not match");
        }

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        System.out.println("Saving user: " + user.getEmail());
        userRepository.save(user);
        System.out.println("Saved successfully!");

    }

}