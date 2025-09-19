package br.com.mmaverse.controller;

import br.com.mmaverse.entity.Organization;
import br.com.mmaverse.service.OrganizationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mmaverse/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @GetMapping
    public ResponseEntity<List<Organization>> findAll() {
        return ResponseEntity.ok(organizationService.findAll());
    }

    @PostMapping
    public ResponseEntity<Organization> save(@RequestBody Organization Organization) {
        Organization savedOrganization = organizationService.save(Organization);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrganization);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organization> findById(@PathVariable Long id) {
        return organizationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        organizationService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
