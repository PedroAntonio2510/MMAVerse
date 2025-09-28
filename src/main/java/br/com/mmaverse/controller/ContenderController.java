package br.com.mmaverse.controller;

import br.com.mmaverse.dto.ContenderDTO;
import br.com.mmaverse.dto.ContenderResponseDTO;
import br.com.mmaverse.entity.Contender;
import br.com.mmaverse.mapper.ContenderMapper;
import br.com.mmaverse.service.ContenderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mmaverse/contender")
public class ContenderController {

    private final ContenderService contenderService;

    public ContenderController(ContenderService contenderService) {
        this.contenderService = contenderService;
    }

    @PostMapping
    public ResponseEntity<ContenderResponseDTO> save(@RequestBody @Valid ContenderDTO request) {
        Contender savedContender = contenderService.save(ContenderMapper.toContender(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(ContenderMapper.toResponse(savedContender));
    }

    @GetMapping
    public ResponseEntity<List<ContenderResponseDTO>> findAll() {
        List<ContenderResponseDTO> contenders = contenderService.findAll().stream().map(ContenderMapper::toResponse).toList();
        return ResponseEntity.ok(contenders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContenderResponseDTO> update(@PathVariable Long id,
                                                       @RequestBody @Valid ContenderDTO request) {
        return contenderService.update(id, ContenderMapper.toContender(request))
                .map(contender -> ResponseEntity.ok(ContenderMapper.toResponse(contender)))
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Contender> findById(@PathVariable Long id) {
        return contenderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contenderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
