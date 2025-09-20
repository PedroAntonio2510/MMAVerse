package br.com.mmaverse.service;

import br.com.mmaverse.entity.Contender;
import br.com.mmaverse.entity.Gym;
import br.com.mmaverse.repository.ContenderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContenderService {
    
    private final ContenderRepository repository;
    private final GymService gymService;

    public ContenderService(ContenderRepository repository, GymService gymService) {
        this.repository = repository;
        this.gymService = gymService;
    }

    public List<Contender> findAll() {
        return repository.findAll();
    }

    public Contender save(Contender contender) {
        contender.setAge(getAge(contender.getBirthDate()));
        contender.setGyms(findGym(contender.getGyms()));
        return repository.save(contender);
    }

    public Optional<Contender> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Contender> update(Long id, Contender updateContender) {
        Optional<Contender> optContender = repository.findById(id);
        if (optContender.isPresent()) {
            List<Gym> gyms = findGym(updateContender.getGyms());

            Contender contender = optContender.get();
            contender.setName(updateContender.getName());
            contender.setNickname(updateContender.getNickname());
            contender.setCpf(updateContender.getCpf());
            contender.setBirthDate(updateContender.getBirthDate());
            contender.setAge(getAge(updateContender.getBirthDate()));
            contender.setWeightClass(updateContender.getWeightClass());
            contender.setHeight(updateContender.getHeight());
            contender.setReach(updateContender.getReach());
            contender.setWin(updateContender.getWin());
            contender.setLose(updateContender.getLose());
            contender.setGyms(gyms);

            repository.save(contender);
            return Optional.of(contender);
        }
        return Optional.empty();
    }

    public Integer getAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public List<Gym> findGym(List<Gym> gyms) {
        List<Gym> gymsFound = new ArrayList<>();
        gyms.forEach(gym -> gymService.findById(gym.getId())
                .ifPresent(gymsFound::add));
        return gymsFound;
    }
}
