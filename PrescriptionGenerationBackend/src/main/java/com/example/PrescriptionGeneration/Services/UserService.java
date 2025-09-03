package com.example.PrescriptionGeneration.Services;

import com.example.PrescriptionGeneration.Model.User;
import com.example.PrescriptionGeneration.Repository.UserRepository;
import com.example.PrescriptionGeneration.Security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        
        if(userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }


    // Login using email
    public String loginUser(String email, String rawPassword, JwtUtil jwtUtil) {
        // Fetches user by email:
        Optional<User> optionalUser = userRepository.findByEmail(email);

        // If empty user not found login not possible
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        // User found, now check password
        User user = optionalUser.get();

        // Checks password using BCryptPasswordEncoder.matches(rawPassword, hashedPassword)
        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        // Generate JWT token using email as subject
        // This token will be used to access any  secured api endpoint
        return jwtUtil.generateToken(user.getEmail());
    }

}
