package anjofi.backend.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import anjofi.backend.entities.Usuario;
import anjofi.backend.exceptions.UsuarioExistenteException;
import anjofi.backend.exceptions.UsuarioNaoEncontradoException;

@RestController

@RequestMapping("/dispositivos")

public class Dispositivo {
    @GetMapping()
    public void arduino (lastReceivedData){

        
    }

}
