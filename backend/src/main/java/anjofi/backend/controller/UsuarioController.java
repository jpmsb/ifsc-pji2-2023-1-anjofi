package anjofi.backend.controller;

import java.util.HashMap;

import javax.sound.midi.SysexMessage;

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

@RequestMapping("/usuarios")

public class UsuarioController {
    
    
    @GetMapping
    public HashMap<String, Usuario> exibirUsuarios(){
       return Operacao.exibirUsuarios();
    }
        
    @GetMapping("/{id}")
    public Usuario obterUsuario(@PathVariable String id){
        return Operacao.exibirUsuario(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus adicionarUsuario(@RequestBody Usuario p){
        Usuario n = new Usuario(p.getId(), p.getNome(), p.getEmail(), p.getSenha());

        if(Operacao.adicionarUsuario(n)== true){
            return HttpStatus.OK;
        }else{
            return HttpStatus.I_AM_A_TEAPOT;
        }
       
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@RequestBody Usuario usuario, @PathVariable String id){
        return usuario;
    }

    @DeleteMapping("/{id}")
    public String excluirUsuario(@PathVariable String id){
        return Operacao.excluirUsuario(id);
        
    }

    @ControllerAdvice
    class UsuarioNaoEncontrado {
        @ResponseBody
        @ExceptionHandler(UsuarioNaoEncontradoException.class)
        
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String usuarioNaoEncontrado(UsuarioNaoEncontradoException p){
            return p.getMessage();
        }
    }
    @ControllerAdvice
    class UsuarioExistente{
        @ResponseBody
        @ExceptionHandler(UsuarioExistenteException.class)

        @ResponseStatus(HttpStatus.NOT_FOUND)
        String usuarioExistente(UsuarioExistenteException id){
            return id.getMessage();
        }


    }
    

}