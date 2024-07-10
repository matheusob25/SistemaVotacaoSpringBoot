package com.matheusob25.sistemavotacaospringboot.resources;


import com.matheusob25.sistemavotacaospringboot.entities.Politico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.matheusob25.sistemavotacaospringboot.services.PoliticoService;

import java.util.List;

@RestController
@RequestMapping(value = "/politicos")
public class PoliticoResource {

    @Autowired
    private PoliticoService politicoService;

    @GetMapping
    public ResponseEntity<List<Politico>> listarPoliticos() {
        List<Politico> politicos = politicoService.listarPoliticos();
        return ResponseEntity.ok().body(politicos);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Politico> buscarPoliticoPorId(@PathVariable Long id) {
        Politico politico = politicoService.encontrarPoliticoPorId(id);
        return ResponseEntity.ok().body(politico);
    }


}
