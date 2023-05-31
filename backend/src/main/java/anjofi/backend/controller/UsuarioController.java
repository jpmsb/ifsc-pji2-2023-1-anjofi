package anjofi.backend.controller;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;
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
import anjofi.backend.exceptions.UsuarioNaoEncontradoException;


@RestController

@RequestMapping("/usuarios")

public class UsuarioController {
    
    //private final HashMap<Integer, Usuario> agenda = new HashMap();
    private final AtomicLong contador = new AtomicLong();


    @GetMapping
    public HashMap<Integer, Usuario> exibirUsuarios(){
       return Operacao.exibirUsuarios();
    }
        

    @GetMapping("/{id}")
    public Usuario obterUsuario(@PathVariable long id){
        Operacao.validarSenha(id);
        throw new UsuarioNaoEncontradoException(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Usuario adicionarUsuario(@RequestBody Usuario p){
        Usuario n = new Usuario(contador.incrementAndGet(), p.getNome(), p.getEmail(), p.getSenha());
        Operacao.adicionarUsuario(n);
        //System.out.println("chegou JOAOOOO");
        return n;
    }

    @PutMapping("/{id}")
    public Usuario atualizarUsuario(@RequestBody Usuario usuario, @PathVariable long id){
        int a = (int)id;
        return usuario;
    }

    @DeleteMapping("/{id}")
    public void excluirUsuario(@PathVariable long id){
    
    }

    @ControllerAdvice
    class UsuarioNaoEncontrado {
        @ResponseBody
        @ExceptionHandler(UsuarioNaoEncontradoException.class)
        
        @ResponseStatus(HttpStatus.NOT_FOUND)
        String pessoaNaoEncontrado(UsuarioNaoEncontradoException p){
            return p.getMessage();
        }
    }

}