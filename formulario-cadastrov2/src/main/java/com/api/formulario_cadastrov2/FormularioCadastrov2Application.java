package com.api.formulario_cadastrov2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FormularioCadastrov2Application {

	public static void main(String[] args) {
		SpringApplication.run(FormularioCadastrov2Application.class, args);
	}

	@GetMapping("/")
	public String index() {
		return "Ola Mundo";
	}

}
