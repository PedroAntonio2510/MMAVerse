package br.com.mmaverse.service;

import br.com.mmaverse.entity.Contender;
import br.com.mmaverse.entity.Gym;
import br.com.mmaverse.exception.EntityNotFoundException;
import br.com.mmaverse.exception.InvalidCpfException;
import br.com.mmaverse.repository.ContenderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

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
        if (repository.existsByCpf(contender.getCpf())) {
            throw new InvalidCpfException("CPF already exists");
        }
        contender.setAge(getAge(contender.getBirthDate()));
        contender.setGyms(findGym(contender.getGyms()));
        return repository.save(contender);
    }

    public Contender findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contender not found with id: " + id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Contender not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public Contender update(Long id, Contender updateContender) {
        Contender contender = findById(id);
        List<Gym> gyms = findGym(updateContender.getGyms());

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

        return repository.save(contender);
    }

    public Integer getAge(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears();
    }

    public List<Gym> findGym(List<Gym> gyms) {
        List<Gym> gymsFound = new ArrayList<>();
        gyms.forEach(gym -> gymsFound.add(gymService.findById(gym.getId())));
        return gymsFound;
    }
}
