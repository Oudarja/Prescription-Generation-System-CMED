package com.example.PrescriptionGeneration.Model;
import jakarta.persistence.*;
// import java.time.LocalDateTime;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    // private String role = "ASSISTANT"; // default role
    // private LocalDateTime createdAt = LocalDateTime.now();

    
    // Getters and setters
    public String getPassword() {
        return password;
    }
    public void setPassword(String encode) {
        this.password = encode;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
    this.username = username;
 }

public void setEmail(String email) {
    this.email = email;
}


}
