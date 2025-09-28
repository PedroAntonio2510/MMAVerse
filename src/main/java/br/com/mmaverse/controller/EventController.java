package br.com.mmaverse.controller;

import br.com.mmaverse.dto.EventDTO;
import br.com.mmaverse.entity.Event;
import br.com.mmaverse.mapper.EventMapper;
import br.com.mmaverse.service.EventService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("mmaverse/event")
@Tag(name = "Events")
public class EventController {
    
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Find all events", description = "Returns a list of all events.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    @GetMapping
    public ResponseEntity<List<Event>> findAll() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @Operation(summary = "Save an event", description = "Saves a new event.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Event created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Event> save(@RequestBody @Valid EventDTO event) {
        Event savedEvent = eventService.save(EventMapper.toEntity(event));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    @Operation(summary = "Update an event", description = "Updates an existing event.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Event updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable Long id,
                                        @RequestBody @Valid EventDTO request) {
        return eventService.update(id, EventMapper.toEntity(request))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Find an event by ID", description = "Returns a single event.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved event"),
        @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Event> findById(@PathVariable Long id) {
        return eventService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an event", description = "Deletes an event.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Event deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Event not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
