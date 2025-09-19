package br.com.mmaverse.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EventDTO(
        String name,
        String location,
        LocalDateTime startDate,
        List<Long> streamings,
        Long organization) {
}
