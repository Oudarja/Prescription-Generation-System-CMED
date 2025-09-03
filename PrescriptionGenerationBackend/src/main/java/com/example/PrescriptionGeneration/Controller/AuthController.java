package com.example.PrescriptionGeneration.Controller;

import com.example.PrescriptionGeneration.Model.User;
import com.example.PrescriptionGeneration.Security.JwtUtil;
import com.example.PrescriptionGeneration.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.PrescriptionGeneration.Dto.LoginRequest;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController 
{
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword(), jwtUtil);
    }

}
