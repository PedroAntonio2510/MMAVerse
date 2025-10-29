package br.com.mmaverse.controller;

import br.com.mmaverse.dto.RankingDTO;
import br.com.mmaverse.dto.RankingResponseDTO;
import br.com.mmaverse.entity.Ranking;
import br.com.mmaverse.mapper.RankingMapper;
import br.com.mmaverse.service.RankingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mmaverse/ranking")
@Tag(name = "Rankings")
public class RankingController {

    private final RankingService rankingService;

    public RankingController(RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @Operation(summary = "Save a ranking", description = "Saves a new ranking.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ranking created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<RankingResponseDTO> save(@RequestBody @Valid RankingDTO request) {
        Ranking savedRanking = rankingService.save(RankingMapper.toRanking(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(RankingMapper.toResponse(savedRanking));
    }

    @Operation(summary = "Find all rankings", description = "Returns a list of all rankings.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    @GetMapping
    public ResponseEntity<List<RankingResponseDTO>> findAll() {
        List<RankingResponseDTO> rankings = rankingService.findAll().stream().map(RankingMapper::toResponse).toList();
        return ResponseEntity.ok(rankings);
    }

    @Operation(summary = "Update a ranking", description = "Updates an existing ranking.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ranking updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Ranking not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<RankingResponseDTO> update(@PathVariable Long id,
                                                       @RequestBody @Valid RankingDTO request) {
        Ranking updatedRanking = rankingService.update(id, RankingMapper.toRanking(request));
        return ResponseEntity.ok(RankingMapper.toResponse(updatedRanking));
    }

    @Operation(summary = "Find a ranking by ID", description = "Returns a single ranking.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved ranking"),
            @ApiResponse(responseCode = "404", description = "Ranking not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<RankingResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(RankingMapper.toResponse(rankingService.findById(id)));
    }

    @Operation(summary = "Delete a ranking", description = "Deletes a ranking.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Ranking deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Ranking not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rankingService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{rankingId}/contender/{contenderId}")
    public ResponseEntity<RankingResponseDTO> addContender(@PathVariable Long rankingId,
                                                           @PathVariable Long contenderId) {
        Ranking ranking = rankingService.addContender(rankingId, contenderId);
        return ResponseEntity.ok(RankingMapper.toResponse(ranking));
    }

    @DeleteMapping("/{rankingId}/contender/{contenderId}")
    public ResponseEntity<RankingResponseDTO> removeContender(@PathVariable Long rankingId,
                                                              @PathVariable Long contenderId) {
        Ranking ranking = rankingService.removeContender(rankingId, contenderId);
        return  ResponseEntity.ok(RankingMapper.toResponse(ranking));
    }
}
