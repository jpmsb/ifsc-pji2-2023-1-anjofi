package anjofi.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import anjofi.backend.entities.Usuario;


@RestController
@RequestMapping("/login")
public class LoginController {
    
    @PostMapping()
    public HttpStatus login(@RequestBody Usuario p){
        
        // Usuario aux = Operacao.exibirUsuario(p.getId());
        // System.out.println(aux.getId());   
        return HttpStatus.OK;
    
        

        // if(!aux.getId().equals(p.getId())||aux.getId().isEmpty()){
        //     return HttpStatus.I_AM_A_TEAPOT;
        // }else{
        //     String auxSenha = criptografar(p.getSenha());
        //     System.out.println(auxSenha);
        //     System.out.println(aux.getSenha());
        //     if(aux.getSenha().equals(auxSenha)){
        //         System.out.println("acertou miseravi");
                
        //          return HttpStatus.OK;
        //     }else{
        //         System.out.println("senha inválida, boca moleeee");
        //         return HttpStatus.I_AM_A_TEAPOT;

        //     }
        // }
       
    }

    public static String criptografar(String texto) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(texto.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }


}
