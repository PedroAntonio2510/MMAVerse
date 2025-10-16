package br.com.mmaverse.dto;

import java.util.Set;

public record RankingDTO(
        String name,
        String category,
        Long organizationId,
        Set<Long> contenders
) {
}
