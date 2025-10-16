package br.com.mmaverse.repository;

import br.com.mmaverse.entity.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
}
