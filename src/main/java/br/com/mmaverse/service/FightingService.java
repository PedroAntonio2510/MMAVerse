package br.com.mmaverse.service;

import br.com.mmaverse.entity.Contender;
import br.com.mmaverse.entity.Event;
import br.com.mmaverse.entity.Fighting;
import br.com.mmaverse.repository.FightingRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

        fighting.setEvent(eventFound);
        fighting.setContenderBlueCorner(contenderBlueCornerFound);
        fighting.setContenderRedCorner(contenderRedCornerFound);
        fighting.setWinner(null);
        return fightingRepository.save(fighting);
    }

    public List<Fighting> findAll() {
        return fightingRepository.findAll();
    }

    public Optional<Fighting> findById(Long id) {
        return fightingRepository.findById(id);
    }

    public void delete(Long id) {
        fightingRepository.deleteById(id);
    }

    public Event getEvent(Long id) {
        Optional<Event> event = eventService.findById(id);
        return event.orElseThrow(() -> new IllegalArgumentException("Event not found"));
    }

    public Contender getContender(Long id) {
        Optional<Contender> contender = contenderService.findById(id);
        return  contender.orElseThrow(() -> new IllegalArgumentException("Contender not found"));
    }

}
