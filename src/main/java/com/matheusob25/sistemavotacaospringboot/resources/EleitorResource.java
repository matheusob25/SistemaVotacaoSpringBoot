package com.matheusob25.sistemavotacaospringboot.resources;

import com.matheusob25.sistemavotacaospringboot.entities.Eleitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.matheusob25.sistemavotacaospringboot.services.EleitorService;

import java.util.List;

@RestController
@RequestMapping(value = "/eleitores")
public class EleitorResource {

    @Autowired
    private EleitorService eleitorService;

    @GetMapping
    public ResponseEntity<List<Eleitor>> listarEleitores(){
        List<Eleitor> eleitores = eleitorService.listarEleitores();
        return ResponseEntity.ok().body(eleitores);
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Eleitor> buscarEleitorPorId(@PathVariable Long id){
        Eleitor eleitor = eleitorService.buscarEleitorPorId(id);
        return ResponseEntity.ok().body(eleitor);
    }
}
