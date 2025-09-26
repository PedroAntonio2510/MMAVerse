package br.com.mmaverse.dto;

import lombok.Builder;

@Builder
public record FightingResponseDTO(
        Long id,
        EventDTO event,
        ContenderResponseDTO contenderRedCorner,
        ContenderResponseDTO contenderBlueCorner,
        String contenderWinner,
        String methodOfVictory,
        Integer endRound,
        String endTime
) {
}
