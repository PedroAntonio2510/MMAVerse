package br.com.mmaverse.repository;

import br.com.mmaverse.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
