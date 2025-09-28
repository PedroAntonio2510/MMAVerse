package br.com.mmaverse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "StreamingDTO", description = "Data transfer object for a streaming service")
public record StreamingDTO(
        @Schema(description = "Name of the streaming service", example = "UFC Fight Pass")
        @NotBlank(message = "Name is necessary")
        String name
) {
}
