package br.com.mmaverse.mapper;

import br.com.mmaverse.dto.EventDTO;
import br.com.mmaverse.entity.Event;
import br.com.mmaverse.entity.Organization;
import br.com.mmaverse.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class EventMapper {

    public static Event toEntity(EventDTO eventDTO) {
        List<Streaming> streamings = eventDTO.streamings().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        Organization organization = Organization.builder().id(eventDTO.organization()).build();

        return Event.builder()
                .name(eventDTO.name())
                .location(eventDTO.location())
                .startDate(eventDTO.startDate())
                .streaming(streamings)
                .organization(organization)
                .build();
    }

    public static EventDTO toDTO(Event event) {
        List<Long> streamingIds = event.getStreaming().stream()
                .map(Streaming::getId)
                .toList();

        Long organizationId = event.getOrganization().getId();

        return EventDTO.builder()
                .name(event.getName())
                .location(event.getLocation())
                .startDate(event.getStartDate())
                .streamings(streamingIds)
                .organization(organizationId)
                .build();
    }
}