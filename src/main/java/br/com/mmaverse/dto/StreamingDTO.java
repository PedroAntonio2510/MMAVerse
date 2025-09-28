package br.com.mmaverse.dto;

import jakarta.validation.constraints.NotBlank;

public record StreamingDTO(
        @NotBlank(message = "Name is necessary")
        String name
) {
}
