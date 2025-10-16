package br.com.mmaverse.dto;

import br.com.mmaverse.entity.Contender;
import com.fasterxml.jackson.annotation.JsonProperty;
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
