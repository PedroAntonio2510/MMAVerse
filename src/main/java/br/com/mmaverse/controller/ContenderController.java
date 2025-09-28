package br.com.mmaverse.controller;

import br.com.mmaverse.dto.ContenderDTO;
import br.com.mmaverse.dto.ContenderResponseDTO;
import br.com.mmaverse.entity.Contender;
import br.com.mmaverse.mapper.ContenderMapper;
import br.com.mmaverse.service.ContenderService;
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
@RequestMapping("/mmaverse/contender")
@Tag(name = "Contenders")
public class ContenderController {

    private final ContenderService contenderService;

    public ContenderController(ContenderService contenderService) {
        this.contenderService = contenderService;
    }

    @Operation(summary = "Save a contender", description = "Saves a new contender.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Contender created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<ContenderResponseDTO> save(@RequestBody @Valid ContenderDTO request) {
        Contender savedContender = contenderService.save(ContenderMapper.toContender(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(ContenderMapper.toResponse(savedContender));
    }

    @Operation(summary = "Find all contenders", description = "Returns a list of all contenders.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    @GetMapping
    public ResponseEntity<List<ContenderResponseDTO>> findAll() {
        List<ContenderResponseDTO> contenders = contenderService.findAll().stream().map(ContenderMapper::toResponse).toList();
        return ResponseEntity.ok(contenders);
    }

    @Operation(summary = "Update a contender", description = "Updates an existing contender.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Contender updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Contender not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ContenderResponseDTO> update(@PathVariable Long id,
                                                       @RequestBody @Valid ContenderDTO request) {
        Contender updatedContender = contenderService.update(id, ContenderMapper.toContender(request));
        return ResponseEntity.ok(ContenderMapper.toResponse(updatedContender));
    }

    @Operation(summary = "Find a contender by ID", description = "Returns a single contender.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved contender"),
        @ApiResponse(responseCode = "404", description = "Contender not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Contender> findById(@PathVariable Long id) {
        return ResponseEntity.ok(contenderService.findById(id));
    }

    @Operation(summary = "Delete a contender", description = "Deletes a contender.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Contender deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Contender not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contenderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
