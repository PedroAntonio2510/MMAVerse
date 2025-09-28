package br.com.mmaverse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Schema(name = "EventDTO", description = "Data transfer object for an event")
public record EventDTO(
        @Schema(description = "Name of the event", example = "UFC 293")
        @NotEmpty(message = "Name is necessary")
        String name,

        @Schema(description = "Location of the event", example = "Las Vegas, USA")
        @NotEmpty(message = "Location is necessary")
        String location,

        @Schema(description = "Start date and time of the event", example = "28/09/2025-20:00")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy-H:mm")
        @NotNull(message = "Date is necessary")
        LocalDateTime startDate,

        @Schema(description = "List of streaming service IDs", example = "[1, 2]")
        @NotEmpty(message = "Streamings are necessary")
        List<Long> streamings,

        @Schema(description = "ID of the organization", example = "1")
        @NotNull(message = "Organization is necessary")
        Long organization) {
}
