package com.tom.algofoodapi;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.tom.algafoodapi.AlgafoodApiApplication;
import com.tom.algafoodapi.domain.model.Cozinha;
import com.tom.algafoodapi.domain.repository.CozinhaRepository;
import com.tom.algafoodapi.services.CozinhaService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CozinhaService.class)
public class CozinhaServiceTest {
    
    // @Autowired
    // private CozinhaService cozinhaService;
    // @Autowired
    // private CozinhaRepository cozinhaRepository;
    
    @Test
    public void verifcaPersistenciaEntidadeCozinha(){
        
        // Cozinha cozinha =  new Cozinha();
        // cozinha.setNome("Chinesa");

        // Cozinha novaCozinha = cozinhaService.getRepository().save(cozinha);

        // assertNotNull(novaCozinha);
    }
}
