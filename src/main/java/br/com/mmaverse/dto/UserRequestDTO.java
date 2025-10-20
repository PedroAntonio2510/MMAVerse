package br.com.mmaverse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(
        @Email(message = "Email not valid")
        @NotBlank(message = "Email is necessary")
        String email,
        @NotBlank(message = "Password is necessary")
        String password
) {
}
