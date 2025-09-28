package br.com.mmaverse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
@Schema(name = "GymDTO", description = "Data transfer object for a gym")
public record GymDTO(
        @Schema(description = "Name of the gym", example = "American Top Team")
        @NotBlank(message = "Name is necessary")
        String name,

        @Schema(description = "Location of the gym", example = "Coconut Creek, Florida, USA")
        String location,

        @Schema(description = "Year the gym was founded", example = "2001")
        Integer foundation
) {
}
