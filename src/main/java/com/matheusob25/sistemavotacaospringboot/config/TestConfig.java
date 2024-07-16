package com.matheusob25.sistemavotacaospringboot.config;

import com.matheusob25.sistemavotacaospringboot.entities.Eleitor;
import com.matheusob25.sistemavotacaospringboot.entities.Politico;
import com.matheusob25.sistemavotacaospringboot.entities.enums.Cargo;
import com.matheusob25.sistemavotacaospringboot.repositories.EleitorRepository;
import com.matheusob25.sistemavotacaospringboot.repositories.PoliticoRepository;
import com.matheusob25.sistemavotacaospringboot.services.EleitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    EleitorRepository eleitorRepository;
    @Autowired
    PoliticoRepository politicoRepository;

    @Autowired
    EleitorService eleitorService;

    @Override
    public void run(String... args) throws Exception {


        Eleitor e1 = new Eleitor(null, "junior","junior@gmail.com","123456");
        Eleitor e2 = new Eleitor(null, "gabriel","gabriel@gmail.com","123456");
        Eleitor e3 = new Eleitor(null, "julia","julia@gmail.com","123456");
        eleitorRepository.saveAll(Arrays.asList(e1, e2, e3));

        Politico p1 = new Politico(null, "Rodinei do maracujá", "Não sou corrupto, eu acho", Cargo.PREFEITO);
        Politico p2 = new Politico(null, "jorge cena", "vou tentar não roubar", Cargo.PREFEITO);
        Politico p3 = new Politico(null, "lula", "companheiros e companheiras", Cargo.PRESIDENTE);
        politicoRepository.saveAll(Arrays.asList(p1, p2,p3));


    }
}
