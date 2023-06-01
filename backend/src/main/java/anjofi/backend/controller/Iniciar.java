package anjofi.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

@RequestMapping("/iniciar")

public class Iniciar {

    @GetMapping
    public String iniciar(){
        Operacao.iniciar();
        return "lista inicializada";
    }
    
}
