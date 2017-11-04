package br.com.conectoma.contafacil.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.conectoma.contafacil.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
	
	

}
