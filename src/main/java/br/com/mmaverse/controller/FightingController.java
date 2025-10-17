package br.com.mmaverse.controller;

import br.com.mmaverse.dto.FightingDTO;
import br.com.mmaverse.dto.FightingResponseDTO;
import br.com.mmaverse.entity.Contender;
import br.com.mmaverse.entity.Fighting;
import br.com.mmaverse.mapper.FightingMapper;
import br.com.mmaverse.service.FightingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mmaverse/fighting")
@Tag(name = "Fightings")
public class FightingController {

    private final FightingService fightingService;

    public FightingController(FightingService fightingService) {
        this.fightingService = fightingService;
    }

    @Operation(summary = "Save a fighting", description = "Saves a new fighting.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Fighting created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<FightingResponseDTO> save(@RequestBody @Valid FightingDTO request) {
        Fighting savedFighting = fightingService.save(FightingMapper.toFighting(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(FightingMapper.toResponse(savedFighting ));
    }

    @Operation(summary = "Find all fightings", description = "Returns a list of all fightings.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    @GetMapping
    public ResponseEntity<List<Fighting>> findAll() {
        return ResponseEntity.ok(fightingService.findAll());
    }

    @Operation(summary = "Update a fighting", description = "Updates an existing fighting.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Fighting updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Fighting not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<FightingResponseDTO> update(@PathVariable Long id,
                                                @RequestBody @Valid FightingDTO request) {
        return ResponseEntity.ok(FightingMapper.toResponse(fightingService.update(id, FightingMapper.toFighting(request))));
    }
}