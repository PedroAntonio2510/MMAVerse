package br.com.mmaverse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
@Schema(name = "FightingResponseDTO", description = "Data transfer object for a fighting response")
public record FightingResponseDTO(
        @Schema(description = "ID of the fighting", example = "1")
        Long id,

        @Schema(description = "Event details")
        EventDTO event,

        @Schema(description = "Details of the contender in the red corner")
        ContenderResponseDTO contenderRedCorner,

        @Schema(description = "Details of the contender in the blue corner")
        ContenderResponseDTO contenderBlueCorner,

        @Schema(description = "Name of the winner contender", example = "Jon Jones")
        String contenderWinner,

        @Schema(description = "Method of victory", example = "KO")
        String methodOfVictory,

        @Schema(description = "Round the fighting ended", example = "3")
        Integer endRound,

        @Schema(description = "Time the fighting ended", example = "4:59")
        String endTime
) {
}
