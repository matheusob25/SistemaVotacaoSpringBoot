package com.matheusob25.sistemavotacaospringboot.services;

import com.matheusob25.sistemavotacaospringboot.entities.Eleitor;
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
import com.matheusob25.sistemavotacaospringboot.repositories.EleitorRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class EleitorService {

    @Autowired
    private EleitorRepository eleitorRepo;
    @Autowired
    private PoliticoService politicoService;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Eleitor> listarEleitores(){
        return eleitorRepo.findAll();
    }
    public Eleitor buscarEleitorPorId(Long id){
        Optional<Eleitor> eleitor = eleitorRepo.findById(id);
        return eleitor.orElseThrow(() -> new ResourceNotFoundException(id));
    }
    public Eleitor inserirEleitor(Eleitor eleitor){
        eleitor.setSenha(passwordEncoder.encode(eleitor.getSenha()));
        return eleitorRepo.save(eleitor);
    }
    public Eleitor alterarEleitor(Long id, Eleitor eleitorNovo){
     try {
         Eleitor eleitor = eleitorRepo.getReferenceById(id);
         alterarDados(eleitor, eleitorNovo);
         return eleitorRepo.save(eleitor);
     }catch (EntityNotFoundException e){
         throw new ResourceNotFoundException(id);
     }

    }
    private void alterarDados(Eleitor eleitorAntigo, Eleitor eleitorNovo){
        eleitorAntigo.setNome(eleitorNovo.getNome());
        eleitorAntigo.setEmail(eleitorNovo.getEmail());
        eleitorAntigo.setSenha(eleitorNovo.getSenha());
    }
    public void removerEleitorPorId(Long id){
        try {
            eleitorRepo.deleteById(id);
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());

        }
    }

    public void votarPolitico(Long id, Long idPolitico){
        Eleitor eleitor = buscarEleitorPorId(id);
        Politico politico = politicoService.encontrarPoliticoPorId(idPolitico);
        if (!eleitor.getPoliticos().contains(politico) && !politico.getEleitores().contains(eleitor)){
            if (eleitor.getPoliticos().size() == 0){
                eleitor.getPoliticos().add(politico);
                politico.getEleitores().add(eleitor);
                inserirEleitor(eleitor);
                politicoService.inserirPolitico(politico);
            } else if (eleitor.getPoliticos().size() > 0){
                for (Politico p : eleitor.getPoliticos()){
                    if (p.getCargo() == politico.getCargo()){
                        eleitor.getPoliticos().remove(p);
                        eleitor.getPoliticos().add(politico);
                        p.getEleitores().remove(eleitor);
                        politico.getEleitores().add(eleitor);
                        inserirEleitor(eleitor);
                        politicoService.inserirPolitico(politico);
                    }else{
                        eleitor.getPoliticos().add(politico);
                        politico.getEleitores().add(eleitor);
                        inserirEleitor(eleitor);
                        politicoService.inserirPolitico(politico);
                    }
                }
            }
        }else{
            throw new IllegalStateException("Politico já foi votado");
        }

    }


}
