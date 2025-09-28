package br.com.mmaverse.controller;

import br.com.mmaverse.dto.OrganizationDTO;
import br.com.mmaverse.entity.Organization;
import br.com.mmaverse.mapper.OrganizationMapper;
import br.com.mmaverse.service.OrganizationService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mmaverse/organization")
@Tag(name = "Organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Operation(summary = "Find all organizations", description = "Returns a list of all organizations.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list")
    })
    @GetMapping
    public ResponseEntity<List<Organization>> findAll() {
        return ResponseEntity.ok(organizationService.findAll());
    }

    @Operation(summary = "Save an organization", description = "Saves a new organization.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Organization created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<Organization> save(@RequestBody @Valid OrganizationDTO request) {
        Organization savedOrganization = organizationService.save(OrganizationMapper.toOrganization(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrganization);
    }

    @Operation(summary = "Update an organization", description = "Updates an existing organization.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Organization updated successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Organization not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Organization> update(@PathVariable Long id, @RequestBody @Valid OrganizationDTO request) {
        return organizationService.update(id, OrganizationMapper.toOrganization(request))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Find an organization by ID", description = "Returns a single organization.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved organization"),
        @ApiResponse(responseCode = "404", description = "Organization not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Organization> findById(@PathVariable Long id) {
        return organizationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an organization", description = "Deletes an organization.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Organization deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Organization not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        organizationService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
