package br.com.mmaverse.service;

import br.com.mmaverse.entity.Organization;
import br.com.mmaverse.exception.ResourceNotFoundException;
import br.com.mmaverse.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {

    private final OrganizationRepository repository;

    public OrganizationService(OrganizationRepository repository) {
        this.repository = repository;
    }
    
    public List<Organization> findAll() {
        return repository.findAll();
    }

    public Organization save(Organization Organization) {
        return repository.save(Organization);
    }

    public Organization findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Organization not found with id: " + id));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Organization not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public Organization update(Long id, Organization updateOrganization) {
        Organization organization = findById(id);
        organization.setName(updateOrganization.getName());
        organization.setCnpj(updateOrganization.getCnpj());
        return repository.save(organization);
    }
}
