package br.com.mmaverse.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record FightingDTO(
        @NotNull(message = "Event id is necessary")
        Long event,

        @NotNull(message = "Fighter Id is necessary")
        Long contenderRedCorner,

        @NotNull(message = "Fighter Id is necessary")
        Long contenderBlueCorner,
        Long contenderWinner,
        String methodOfVictory,
        Integer endRound,
        String endTime
) {
}
