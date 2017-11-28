package br.com.conectoma.contafacil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.conectoma.contafacil.services.S3Service;

@SpringBootApplication
public class ContaFacilApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(ContaFacilApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		s3Service.uploadFile("/home/neirivon/Imagens/LanchoneteSeoJorge.jpg");
	}
}
