package org.example.spring_jpa.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserDto {
    @NotBlank(message = "First Name is mandatory")
    private String firstname;
    @NotBlank(message = "First Name is mandatory")
    private String lastname;

    @NotBlank(message = "Name is mandatory")
    private String username;


    @NotNull(message = "Password cannot be null")
    @Size(min = 9 , max = 20 , message = "Password musth be between 8 and 20 characters")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$",
                        message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character")
    private String password;

    @NotNull(message = "Please fill email")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Please fill phone number")
    @Pattern(regexp = "^(\\+84|0)\\d{9}$", message = "Invalid phone number format")
    private String phone;

    private String role;
}
