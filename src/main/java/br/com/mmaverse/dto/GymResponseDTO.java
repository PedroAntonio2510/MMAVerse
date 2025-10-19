package br.com.mmaverse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Builder
@Schema(name = "GymResponseDTO", description = "Data transfer object for a gym")
public record GymResponseDTO(
        @Schema(description = "Id of the gym")
        Long id,

        @Schema(description = "Name of the gym", example = "American Top Team")
        String name,

        @Schema(description = "Location of the gym", example = "Coconut Creek, Florida, USA")
        String location,

        @Schema(description = "Year the gym was founded", example = "2001")
        Integer foundation,

        @Schema(description = "Contenders that represents the gym")
        List<ContenderResponseDTO> contenders
) {
}
