package br.com.criandoapi.projeto_pji;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ProjetoPjiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetoPjiApplication.class, args);
	}

}
