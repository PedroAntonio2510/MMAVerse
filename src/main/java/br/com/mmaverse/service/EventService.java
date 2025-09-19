package br.com.mmaverse.service;

import br.com.mmaverse.entity.Event;
import br.com.mmaverse.entity.Organization;
import br.com.mmaverse.entity.Streaming;
import br.com.mmaverse.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Event> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Streaming> findStreamings(List<Streaming> streamings) {
        List<Streaming> streamingsFound = new ArrayList<>();
        streamings.forEach(streaming -> streamingService.findById(streaming.getId())
                .ifPresent(streamingsFound::add));
        return streamingsFound;
    }

    public Organization findOrganization(Long id) {
        Optional<Organization> organizationFound = organizationService.findById(id);
        return organizationFound.orElseThrow(() -> new IllegalArgumentException("Organization not found"));
    }
}
