package br.com.mmaverse.controller;

import br.com.mmaverse.dto.GymDTO;
import br.com.mmaverse.entity.Gym;
import br.com.mmaverse.mapper.GymMapper;
import br.com.mmaverse.service.GymService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mmaverse/gym")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @GetMapping
    public ResponseEntity<List<Gym>> findAll() {
        return ResponseEntity.ok(gymService.findAll());
    }

    @PostMapping
    public ResponseEntity<Gym> save(@RequestBody GymDTO Gym) {
        Gym savedGym = gymService.save(GymMapper.toGym(Gym));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGym);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gym> update(@PathVariable Long id, @RequestBody GymDTO request) {
        return gymService.update(id, GymMapper.toGym(request))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Gym> findById(@PathVariable Long id) {
        return gymService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gymService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
