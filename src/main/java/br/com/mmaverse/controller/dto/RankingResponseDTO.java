package br.com.mmaverse.controller.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

@Builder
public record RankingResponseDTO(
        Long id,
        String name,
        String category,
        OrganizationDTO organization,
        Set<ContenderResponseDTO> contenders,
        LocalDateTime updatedAt
) {
}
