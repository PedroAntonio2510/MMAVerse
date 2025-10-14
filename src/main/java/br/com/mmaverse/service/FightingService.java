package br.com.mmaverse.service;

import br.com.mmaverse.entity.Contender;
import br.com.mmaverse.entity.Event;
import br.com.mmaverse.entity.Fighting;
import br.com.mmaverse.exception.ResourceNotFoundException;
import br.com.mmaverse.repository.FightingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FightingService {

    private final FightingRepository fightingRepository;
    private final EventService eventService;
    private final ContenderService contenderService;

    public FightingService(FightingRepository fightingRepository,
                           EventService eventService,
                           ContenderService contenderService) {
        this.fightingRepository = fightingRepository;
        this.eventService = eventService;
        this.contenderService = contenderService;
    }

    public Fighting save(Fighting fighting) {
        Event eventFound = getEvent(fighting.getEvent().getId());
        Contender contenderBlueCornerFound = getContender(fighting.getContenderBlueCorner().getId());
        Contender contenderRedCornerFound = getContender(fighting.getContenderRedCorner().getId());

        if (fighting.getWinner().getId() != null) {
            fighting.setWinner(getContender(fighting.getWinner().getId()));
        }

        fighting.setEvent(eventFound);
        fighting.setContenderBlueCorner(contenderBlueCornerFound);
        fighting.setContenderRedCorner(contenderRedCornerFound);
        return fightingRepository.save(fighting);
    }

    public List<Fighting> findAll() {
        return fightingRepository.findAll();
    }

    public Fighting findById(Long id) {
        return fightingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fighting not found with id: " + id));
    }

    public void delete(Long id) {
        if (!fightingRepository.existsById(id)) {
            throw new ResourceNotFoundException("Fighting not found with id: " + id);
        }
        fightingRepository.deleteById(id);
    }

    public Fighting update(Long id, Fighting updateFighting) {
        Fighting fighting = findById(id);
        Contender contenderRedCorner = getContender(updateFighting.getContenderRedCorner().getId());
        Contender contenderBlueCorner = getContender(updateFighting.getContenderBlueCorner().getId());
        Contender contenderWinner = getContenderWinner(updateFighting.getWinner().getId());
        Event eventUpdate = getEvent(updateFighting.getEvent().getId());

        fighting.setContenderRedCorner(contenderRedCorner);
        fighting.setContenderBlueCorner(contenderBlueCorner);
        fighting.setWinner(contenderWinner);
        fighting.setEvent(eventUpdate);
        fighting.setMethodOfVictory(updateFighting.getMethodOfVictory());
        fighting.setEndRound(updateFighting.getEndRound());
        fighting.setEndTime(updateFighting.getEndTime());

        return fightingRepository.save(fighting);
    }

    public Event getEvent(Long id) {
        return eventService.findById(id);
    }

    public Contender getContender(Long id) {
        return contenderService.findById(id);
    }

    public Contender getContenderWinner(Long id) {
        if (id == null) {
            return null;
        }
        return getContender(id);
    }
}