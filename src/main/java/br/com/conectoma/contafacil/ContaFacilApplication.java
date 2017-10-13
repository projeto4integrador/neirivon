package br.com.conectoma.contafacil;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.conectoma.contafacil.domain.PreparoCozinha;
import br.com.conectoma.contafacil.repositories.PreparoCozinhaRepository;

@SpringBootApplication
public class ContaFacilApplication implements CommandLineRunner {

	@Autowired
	private PreparoCozinhaRepository preparoCozinhaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ContaFacilApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		
		PreparoCozinha pc1 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		PreparoCozinha pc2 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		PreparoCozinha pc3 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		
		preparoCozinhaRepository.save(Arrays.asList(pc1, pc2, pc3));
	}
}
