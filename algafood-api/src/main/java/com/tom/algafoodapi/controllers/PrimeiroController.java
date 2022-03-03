package com.tom.algafoodapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/teste")
public class PrimeiroController {

    @GetMapping(value = "/hello")
    public String helloWorld(){
        System.out.println("Teste_devTools  EDITED TOW");
        return "Hello World!!!";
    }
    
}