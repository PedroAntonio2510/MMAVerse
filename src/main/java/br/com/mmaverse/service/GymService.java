package br.com.mmaverse.service;

import br.com.mmaverse.entity.Gym;
import br.com.mmaverse.repository.GymRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Gym> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Gym> update(Long id, Gym updateGym) {
        Optional<Gym> optGym = repository.findById(id);
        if (optGym.isPresent()) {
            Gym gym = optGym.get();

            gym.setName(updateGym.getName());
            gym.setLocation(updateGym.getLocation());
            gym.setFoundation(updateGym.getFoundation());
            repository.save(gym);

            return Optional.of(gym);
        }
        return Optional.empty();
    }
}
