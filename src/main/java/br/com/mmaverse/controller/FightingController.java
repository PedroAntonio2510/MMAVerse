package br.com.mmaverse.controller;

import br.com.mmaverse.dto.FightingDTO;
import br.com.mmaverse.dto.FightingResponseDTO;
import br.com.mmaverse.entity.Fighting;
import br.com.mmaverse.mapper.FightingMapper;
import br.com.mmaverse.service.FightingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mmaverse/fighting")
public class FightingController {

    private final FightingService fightingService;

    public FightingController(FightingService fightingService) {
        this.fightingService = fightingService;
    }

    @PostMapping
    public ResponseEntity<FightingResponseDTO> save(@RequestBody FightingDTO request) {
        Fighting savedFighting = fightingService.save(FightingMapper.toFighting(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(FightingMapper.toResponse(savedFighting ));
    }

    @GetMapping
    public ResponseEntity<List<Fighting>> findAll() {
        return ResponseEntity.ok(fightingService.findAll());
    }

}
