package br.com.mmaverse.service;

import br.com.mmaverse.entity.Event;
import br.com.mmaverse.entity.Organization;
import br.com.mmaverse.entity.Streaming;
import br.com.mmaverse.exception.EntityNotFoundException;
import br.com.mmaverse.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    
    private final EventRepository repository;
    private final StreamingService streamingService;
    private final OrganizationService organizationService;

    public EventService(EventRepository repository, StreamingService streamingService, OrganizationService organizationService) {
        this.repository = repository;
        this.streamingService = streamingService;
        this.organizationService = organizationService;
    }

    public List<Event> findAll() {
        return repository.findAll();
    }

    public Event save(Event event) {
        event.setStreaming(findStreamings(event.getStreaming()));
        event.setOrganization(findOrganization(event.getOrganization().getId()));
        return repository.save(event);
    }

    public Event findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Event not found with id: " + id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Event not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public Event update(Long id, Event updateEvent) {
        Event event = findById(id);
        List<Streaming> streamings = findStreamings(updateEvent.getStreaming());
        Organization organization = findOrganization(updateEvent.getOrganization().getId());

        event.setName(updateEvent.getName());
        event.setLocation(updateEvent.getLocation());
        event.setStartDate(updateEvent.getStartDate());
        event.setStreaming(streamings);
        event.setOrganization(organization);

        return repository.save(event);
    }

    public List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> streamingsFound.add(streamingService.findById(streaming.getId())));
        return streamingsFound;
    }

    public Organization findOrganization(Long id) {
        return organizationService.findById(id);
    }
}