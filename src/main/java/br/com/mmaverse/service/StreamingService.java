package br.com.mmaverse.service;

import br.com.mmaverse.entity.Streaming;
import br.com.mmaverse.repository.StreamingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Streaming> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Streaming> update(Long id, Streaming updateStreaming) {
        Optional<Streaming> optStreaming = repository.findById(id);
        if (optStreaming.isPresent()) {
            Streaming streaming = optStreaming.get();
            streaming.setName(updateStreaming.getName());
            repository.save(streaming);

            return Optional.of(streaming);
        }
        return Optional.empty();
    }
}
