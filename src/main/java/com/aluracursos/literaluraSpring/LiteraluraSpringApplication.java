package com.aluracursos.literaluraSpring;

import com.aluracursos.literaluraSpring.principal.Principal;
import com.aluracursos.literaluraSpring.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraSpringApplication implements CommandLineRunner {

	//Autoinyeccion de dependencias (para usar interface SerieRepository que creamos)
	@Autowired
	private LibroRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraSpringApplication.class, args);
	}

	//	@Override
	public void run(String... args) throws Exception {

		// Unificando y haciendo uso de la clase 'Principal2'
		Principal principal = new Principal(repository);
		principal.muestraElMenu();
	}

}
