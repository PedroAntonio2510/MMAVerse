package br.com.mmaverse.service;

import br.com.mmaverse.entity.Organization;
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
}
