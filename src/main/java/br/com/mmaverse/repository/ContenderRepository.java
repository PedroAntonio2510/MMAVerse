package br.com.mmaverse.repository;

import br.com.mmaverse.entity.Contender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContenderRepository extends JpaRepository<Contender, Long> {
    boolean existsByCpf(String cpf);
}
