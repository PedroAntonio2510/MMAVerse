package br.com.mmaverse.service;

import br.com.mmaverse.entity.Gym;
import br.com.mmaverse.exception.EntityNotFoundException;
import br.com.mmaverse.repository.GymRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GymService {

    private final GymRepository repository;

    public GymService(GymRepository repository) {
        this.repository = repository;
    }

    public List<Gym> findAll() {
        return repository.findAll();
    }

    public Gym save(Gym gym) {
        return repository.save(gym);
    }

    public Gym findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Gym not found with id: " + id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Gym not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public Gym update(Long id, Gym updateGym) {
        Gym gym = findById(id);
        return repository.save(Gym.builder()
                .id(gym.getId())
                .name(updateGym.getName())
                .location(updateGym.getLocation())
                .foundation(updateGym.getFoundation())
                .build());
    }
}
