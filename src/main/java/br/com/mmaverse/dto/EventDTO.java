package br.com.mmaverse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EventDTO(
        String name,
        String location,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy-H:mm")
        LocalDateTime startDate,
        List<Long> streamings,
        Long organization) {
}
