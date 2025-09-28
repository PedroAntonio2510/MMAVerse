package br.com.mmaverse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Schema(name = "FightingDTO", description = "Data transfer object for a fighting")
public record FightingDTO(
        @Schema(description = "ID of the event", example = "1")
        @NotNull(message = "Event id is necessary")
        Long event,

        @Schema(description = "ID of the contender in the red corner", example = "1")
        @NotNull(message = "Fighter Id is necessary")
        Long contenderRedCorner,

        @Schema(description = "ID of the contender in the blue corner", example = "2")
        @NotNull(message = "Fighter Id is necessary")
        Long contenderBlueCorner,

        @Schema(description = "ID of the winner contender", example = "1")
        Long contenderWinner,

        @Schema(description = "Method of victory", example = "KO")
        String methodOfVictory,

        @Schema(description = "Round the fighting ended", example = "3")
        Integer endRound,

        @Schema(description = "Time the fighting ended", example = "4:59")
        String endTime
) {
}
