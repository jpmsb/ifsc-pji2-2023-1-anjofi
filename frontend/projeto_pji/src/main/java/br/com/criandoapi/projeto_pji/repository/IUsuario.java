package br.com.criandoapi.projeto_pji.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.criandoapi.projeto_pji.model.Usuario;

public interface IUsuario extends JpaRepository<Usuario, Integer>{

    @Query("SELECT u FROM Usuario u WHERE u.nome = :nome")
    Usuario findByNome(@Param("nome") String nome);

    
    
}
