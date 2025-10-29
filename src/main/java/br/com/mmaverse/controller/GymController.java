package br.com.mmaverse.controller;

import br.com.mmaverse.dto.GymDTO;
import br.com.mmaverse.dto.GymResponseDTO;
import br.com.mmaverse.entity.Gym;
import br.com.mmaverse.mapper.GymMapper;
import br.com.mmaverse.service.GymService;
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
@RequestMapping("/mmaverse/gym")
@Tag(name = "Gyms")
public class GymController {

    private final GymService gymService;

    public GymController(GymService gymService) {
        this.gymService = gymService;
    }

    @Operation(summary = "Find all gyms", description = "Returns a list of all gyms.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    @GetMapping
    public ResponseEntity<List<GymResponseDTO>> findAll() {
        List<GymResponseDTO> gyms = gymService.findAll().stream().map(GymMapper::toGymResponse).toList();
        return ResponseEntity.ok(gyms);
    }

    @Operation(summary = "Save a gym", description = "Saves a new gym.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Gym created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Gym> save(@RequestBody @Valid GymDTO Gym) {
        Gym savedGym = gymService.save(GymMapper.toGym(Gym));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGym);
    }

    @Operation(summary = "Update a gym", description = "Updates an existing gym.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Gym updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Gym not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<GymResponseDTO> update(@PathVariable Long id, @RequestBody @Valid GymDTO request) {
        Gym updateGym = gymService.update(id, GymMapper.toGym(request));
        return ResponseEntity.ok(GymMapper.toGymResponse(updateGym));
    }

    @Operation(summary = "Find a gym by ID", description = "Returns a single gym.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved gym"),
        @ApiResponse(responseCode = "404", description = "Gym not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<GymResponseDTO> findById(@PathVariable Long id) {
        Gym gymFound = gymService.findById(id);
        return ResponseEntity.ok(GymMapper.toGymResponse(gymFound));
    }

    @Operation(summary = "Delete a gym", description = "Deletes a gym.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Gym deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Gym not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        gymService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
