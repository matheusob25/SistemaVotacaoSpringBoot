package com.matheusob25.sistemavotacaospringboot.repositories;

import com.matheusob25.sistemavotacaospringboot.entities.Eleitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EleitorRepository extends JpaRepository<Eleitor, Long> {

}
