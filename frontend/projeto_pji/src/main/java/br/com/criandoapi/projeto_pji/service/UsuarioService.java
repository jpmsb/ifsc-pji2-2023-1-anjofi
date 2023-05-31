package br.com.criandoapi.projeto_pji.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.criandoapi.projeto_pji.model.Usuario;
import br.com.criandoapi.projeto_pji.repository.IUsuario;

@Service
public class UsuarioService {
	private IUsuario repository; 
	private PasswordEncoder passwordEncoder;
	
	public UsuarioService(IUsuario repository) {
		this.repository = repository;
		this.passwordEncoder = new BCryptPasswordEncoder();
		
	}
	
	public List<Usuario> listarUsuario() {
		List<Usuario> lista = repository.findAll();
		return lista;
	}
	
	public Usuario criarUsuario(Usuario usuario) {
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario usuarioNovo = repository.save(usuario);
		return usuarioNovo;		
	}
	
	public Usuario editarUsuario(Usuario usuario) {
		String encoder = this.passwordEncoder.encode(usuario.getSenha());
		usuario.setSenha(encoder);
		Usuario usuarioNovo = repository.save(usuario);
		return usuarioNovo;		
	}
	
	public Boolean excluirUsuario(String nome) {
        Usuario usuarioEncontrado = repository.findByNome(nome);
        
        if (usuarioEncontrado == null) {
            // Usuário não encontrado pelo nome
            return false;
        }
        
        repository.deleteById(usuarioEncontrado.getId());
        return true;
    }

    public Boolean validarSenha(Usuario usuario) {

		String senha = repository.findByNome(usuario.getNome()).getSenha();
		Boolean valid = passwordEncoder.matches(usuario.getSenha(), senha);
		return valid;
	}



}
