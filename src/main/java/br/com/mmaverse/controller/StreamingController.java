package br.com.mmaverse.controller;

import br.com.mmaverse.dto.StreamingDTO;
import br.com.mmaverse.entity.Streaming;
import br.com.mmaverse.mapper.StreamingMapper;
import br.com.mmaverse.service.StreamingService;
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
@RequestMapping("/mmaverse/streaming")
@Tag(name = "Streamings")
public class StreamingController {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @Operation(summary = "Find all streamings", description = "Returns a list of all streamings.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    @GetMapping
    public ResponseEntity<List<Streaming>> findAll() {
        return ResponseEntity.ok(streamingService.findAll());
    }

    @Operation(summary = "Save a streaming", description = "Saves a new streaming.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Streaming created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Streaming> save(@RequestBody @Valid StreamingDTO streaming) {
        Streaming savedStreaming = streamingService.save(StreamingMapper.toStreaming(streaming));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStreaming);
    }

    @Operation(summary = "Update a streaming", description = "Updates an existing streaming.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Streaming updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Streaming not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Streaming> update(@PathVariable Long id, @RequestBody @Valid StreamingDTO request) {
        return streamingService.update(id, StreamingMapper.toStreaming(request))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Find a streaming by ID", description = "Returns a single streaming.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved streaming"),
        @ApiResponse(responseCode = "404", description = "Streaming not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Streaming> findById(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a streaming", description = "Deletes a streaming.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Streaming deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Streaming not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        streamingService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
