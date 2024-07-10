package com.matheusob25.sistemavotacaospringboot.services;

import com.matheusob25.sistemavotacaospringboot.entities.Eleitor;
import com.matheusob25.sistemavotacaospringboot.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.matheusob25.sistemavotacaospringboot.repositories.EleitorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EleitorService {

    @Autowired
    private EleitorRepository eleitorRepo;

    public List<Eleitor> listarEleitores(){
        return eleitorRepo.findAll();
    }
    public Eleitor buscarEleitorPorId(Long id){
        Optional<Eleitor> eleitor = eleitorRepo.findById(id);
        return eleitor.orElseThrow(() -> new ResourceNotFoundException(id));
    }

}
