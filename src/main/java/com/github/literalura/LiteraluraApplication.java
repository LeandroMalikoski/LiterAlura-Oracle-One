package com.github.literalura;

import com.github.literalura.main.Main;
import com.github.literalura.repository.AutorRepository;
import com.github.literalura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Principal;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private Main main;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		main.Menu();
	}
}
