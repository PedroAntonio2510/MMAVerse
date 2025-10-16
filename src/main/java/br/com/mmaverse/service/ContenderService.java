package br.com.mmaverse.service;

import br.com.mmaverse.entity.Contender;
import br.com.mmaverse.entity.Gym;
import br.com.mmaverse.entity.Ranking;
import br.com.mmaverse.exception.ResourceNotFoundException;
import br.com.mmaverse.exception.InvalidCpfException;
import br.com.mmaverse.repository.ContenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContenderService {
    
    private final ContenderRepository repository;
    private final GymService gymService;
    private RankingService rankingService;

    public ContenderService(ContenderRepository repository, GymService gymService, RankingService rankingService) {
        this.repository = repository;
        this.gymService = gymService;
        this.rankingService = rankingService;
    }

    @Autowired
    public void setRankingService(RankingService rankingService) {
        this.rankingService = rankingService;
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
        contender.setRanking(findRanking(contender.getRanking()));
        return repository.save(contender);
    }

    public Contender findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contender not found with id: " + id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Contender not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public Contender update(Long id, Contender updateContender) {
        Contender contender = findById(id);
        List<Gym> gyms = findGym(updateContender.getGyms());
        Ranking ranking = findRanking(updateContender.getRanking());

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
        contender.setRanking(ranking);
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

    private Ranking findRanking(Ranking ranking) {
        if (ranking == null || ranking.getId() == null) {
            return null;
        }
        return rankingService.findById(ranking.getId());
    }
}
