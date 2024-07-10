package com.matheusob25.sistemavotacaospringboot.services;

import com.matheusob25.sistemavotacaospringboot.entities.Politico;
import com.matheusob25.sistemavotacaospringboot.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.matheusob25.sistemavotacaospringboot.repositories.PoliticoRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PoliticoService {

    @Autowired
    private PoliticoRepository PoliticoRepo;

    public List<Politico> listarPoliticos() {
        return PoliticoRepo.findAll();
    }
    public Politico encontrarPoliticoPorId(Long id) {
        Optional<Politico> Politico = PoliticoRepo.findById(id);
        return Politico.orElseThrow(()-> new ResourceNotFoundException(id));
    }
}
