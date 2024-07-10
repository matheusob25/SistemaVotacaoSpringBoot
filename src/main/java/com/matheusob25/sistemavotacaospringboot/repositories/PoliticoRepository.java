package com.matheusob25.sistemavotacaospringboot.repositories;

import com.matheusob25.sistemavotacaospringboot.entities.Politico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PoliticoRepository extends JpaRepository<Politico, Long> {
}
