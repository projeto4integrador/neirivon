package br.com.conectoma.contafacil;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.conectoma.contafacil.repositories.PreparoCozinhaRepository;

@RunWith(SpringRunner.class)
//A cada execução será utilizada uma porta aleatória
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PreparoCozinhaEndpointTests {
	
	@Autowired
	private TestRestTemplate restTemplate;
	@LocalServerPort
	private int port;
	@MockBean
	private PreparoCozinhaRepository preparoCozinhaRepository;
	@Autowired
	private MockMvc mockMvc;
	
	@TestConfiguration
	static class Config {
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			
			return new RestTemplateBuilder().basicAuthorization("sa", "");
			
		}
	}

}
