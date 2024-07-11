package com.matheusob25.sistemavotacaospringboot.resources;

import com.matheusob25.sistemavotacaospringboot.entities.Eleitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.matheusob25.sistemavotacaospringboot.services.EleitorService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
    @PostMapping
    public ResponseEntity<Eleitor> adicionarEleitor(@RequestBody Eleitor eleitor){
        eleitor = eleitorService.inserirEleitor(eleitor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(eleitor.getId()).toUri();
        return ResponseEntity.created(uri).body(eleitor);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Eleitor> atualizarEleitor(@PathVariable Long id, @RequestBody Eleitor eleitor){
        eleitor = eleitorService.alterarEleitor(id, eleitor);
        return ResponseEntity.ok().body(eleitor);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> removerEleitorPorId(@PathVariable Long id){
        eleitorService.removerEleitorPorId(id);
        return ResponseEntity.noContent().build();
    }

}
