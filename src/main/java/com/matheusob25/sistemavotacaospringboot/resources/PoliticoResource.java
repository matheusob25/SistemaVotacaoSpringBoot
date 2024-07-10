package com.matheusob25.sistemavotacaospringboot.resources;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.matheusob25.sistemavotacaospringboot.services.PoliticoService;

@RestController
@RequestMapping(value = "/politicos")
public class PoliticoResource {

    private PoliticoService politicoService;



}
