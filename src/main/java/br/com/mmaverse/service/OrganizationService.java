package br.com.mmaverse.service;

import br.com.mmaverse.entity.Organization;
import br.com.mmaverse.entity.Streaming;
import br.com.mmaverse.repository.OrganizationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Optional<Organization> findById(Long id) {
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Organization> update(Long id, Organization updateOrganization) {
        Optional<Organization> optOrganization = repository.findById(id);
        if (optOrganization.isPresent()) {
            Organization organization = optOrganization.get();
            organization.setName(updateOrganization.getName());
            organization.setCnpj(updateOrganization.getCnpj());
            repository.save(organization);

            return Optional.of(organization);
        }
        return Optional.empty();
    }
}
