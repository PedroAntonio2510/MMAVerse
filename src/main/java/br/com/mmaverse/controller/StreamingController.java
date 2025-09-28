package br.com.mmaverse.controller;

import br.com.mmaverse.dto.StreamingDTO;
import br.com.mmaverse.entity.Streaming;
import br.com.mmaverse.mapper.StreamingMapper;
import br.com.mmaverse.service.StreamingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mmaverse/streaming")
public class StreamingController {

    private final StreamingService streamingService;

    public StreamingController(StreamingService streamingService) {
        this.streamingService = streamingService;
    }

    @GetMapping
    public ResponseEntity<List<Streaming>> findAll() {
        return ResponseEntity.ok(streamingService.findAll());
    }

    @PostMapping
    public ResponseEntity<Streaming> save(@RequestBody @Valid StreamingDTO streaming) {
        Streaming savedStreaming = streamingService.save(StreamingMapper.toStreaming(streaming));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStreaming);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Streaming> update(@PathVariable Long id, @RequestBody @Valid StreamingDTO request) {
        return streamingService.update(id, StreamingMapper.toStreaming(request))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Streaming> findById(@PathVariable Long id) {
        return streamingService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        streamingService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
