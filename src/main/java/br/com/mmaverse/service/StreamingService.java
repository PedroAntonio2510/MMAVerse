package br.com.mmaverse.service;

import br.com.mmaverse.entity.Streaming;
import br.com.mmaverse.exception.ResourceNotFoundException;
import br.com.mmaverse.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StreamingService {

    private final StreamingRepository repository;

    public StreamingService(StreamingRepository repository) {
        this.repository = repository;
    }

    public List<Streaming> findAll() {
        return repository.findAll();
    }

    public Streaming save(Streaming streaming) {
        return repository.save(streaming);
    }

    public Streaming findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Streaming not found with id: " + id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Streaming not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public Streaming update(Long id, Streaming updateStreaming) {
        Streaming streaming = findById(id);
        streaming.setName(updateStreaming.getName());
        return repository.save(streaming);
    }
}
