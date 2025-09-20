package br.com.mmaverse.repository;

import br.com.mmaverse.entity.Contender;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContenderRepository extends JpaRepository<Contender, Long> {
}
