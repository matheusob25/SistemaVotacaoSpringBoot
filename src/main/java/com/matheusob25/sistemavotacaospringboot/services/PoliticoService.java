package com.matheusob25.sistemavotacaospringboot.services;

import com.matheusob25.sistemavotacaospringboot.entities.Politico;
import com.matheusob25.sistemavotacaospringboot.services.exceptions.DatabaseException;
import com.matheusob25.sistemavotacaospringboot.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.matheusob25.sistemavotacaospringboot.repositories.PoliticoRepository;

import java.util.List;
import java.util.Optional;


@Service
public class PoliticoService {

    @Autowired
    private PoliticoRepository politicoRepo;

    public List<Politico> listarPoliticos() {
        return politicoRepo.findAll();
    }
    public Politico encontrarPoliticoPorId(Long id) {
        Optional<Politico> Politico = politicoRepo.findById(id);
        return Politico.orElseThrow(()-> new ResourceNotFoundException(id));
    }
    public Politico inserirPolitico(Politico politico) {
        return politicoRepo.save(politico);
    }
    public Politico alterarPolitico(Long id,Politico politico) {
        try {
            Politico p1 = politicoRepo.getReferenceById(id);
            alterarDados(p1,politico);
            return politicoRepo.save(p1);
        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
    private void alterarDados(Politico politicoAntigo, Politico politicoNovo) {
        politicoAntigo.setNome(politicoNovo.getNome());
        politicoAntigo.setDescricao(politicoNovo.getDescricao());
    }
    public void removerPoliticoPorId(Long id){
        try {
            politicoRepo.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
    }
}
