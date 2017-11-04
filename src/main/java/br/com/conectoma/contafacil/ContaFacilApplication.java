package br.com.conectoma.contafacil;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContaFacilApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ContaFacilApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
	}
}
