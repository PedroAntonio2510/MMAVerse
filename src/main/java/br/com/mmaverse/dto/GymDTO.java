package br.com.mmaverse.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record GymDTO(
        @NotBlank(message = "Name is necessary")
        String name,
        String location,
        Integer foundation
) {
}
