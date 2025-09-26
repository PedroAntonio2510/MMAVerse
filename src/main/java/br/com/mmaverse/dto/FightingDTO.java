package br.com.mmaverse.dto;

public record FightingDTO(
        Long event,
        Long contenderRedCorner,
        Long contenderBlueCorner,
        Long contenderWinner,
        String methodOfVictory,
        Integer endRound,
        String endTime
) {
}
