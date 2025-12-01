package br.com.mmaverse.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Schema(name = "EventFightCardDTO", description = "Data transfer object for an event's fight card")
public record EventFightCardDTO(
        @Schema(description = "ID of the event", example = "1")
        Long id,

        @Schema(description = "Name of the event", example = "UFC 293")
        String name,

        @Schema(description = "Location of the event", example = "Las Vegas, USA")
        String location,

        @Schema(description = "Start date and time of the event", example = "28/09/2025-20:00")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy-H:mm")
        LocalDateTime startDate,

        @Schema(description = "Organization of the event")
        OrganizationDTO organization,

        @Schema(description = "List of fights on the card")
        List<FightingResponseDTO> fights
) {
}
