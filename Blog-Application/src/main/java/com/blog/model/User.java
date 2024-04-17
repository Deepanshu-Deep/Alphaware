package com.blog.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Username is required")
    private String username;

    @Email(message = "Email is not valid")
    @NotEmpty
    private String email;
    
    @NotEmpty(message = "Password is required")
    @Pattern(regexp = "^.{5,}$", message = "Password must contain at least 5 characters")
    private String password;
    
    

}
