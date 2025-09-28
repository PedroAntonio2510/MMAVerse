package br.com.mmaverse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record EventDTO(
        @NotEmpty(message = "Name is necessary")
        String name,

        @NotEmpty(message = "Location is necessary")
        String location,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy-H:mm")
        @NotNull(message = "Date is necessary")
        LocalDateTime startDate,

        @NotEmpty(message = "Streamings are necessary")
        List<Long> streamings,

        @NotNull(message = "Organization is necessary")
        Long organization) {
}
