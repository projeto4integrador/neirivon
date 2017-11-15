package br.com.conectoma.contafacil;

import static org.springframework.http.HttpMethod.GET;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.com.conectoma.contafacil.domain.Cliente;
import br.com.conectoma.contafacil.domain.enums.TipoCliente;
import br.com.conectoma.contafacil.repositories.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteEndpointTokenTest {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@MockBean
	private ClienteRepository clienteRepository;

	@Autowired
	private MockMvc mockMvc;
	
	private HttpEntity<Void> clientHeader;
	private HttpEntity<Void> adminHeader;
	private HttpEntity<Void> wrongHeader;
	
	@Before
	public void configClientdHeaders() {
		String str = "{\"email\":\"neirivon@gmail.com\",\"senha\":\"jedi\"}";
		HttpHeaders headers = restTemplate.postForEntity("/login", str, String.class).getHeaders();
		this.adminHeader = new HttpEntity<>(headers);
	}

	@Before
	public void configAdmindHeaders() {
		String str = "{\"email\":\"neirivon@hotmail.com\",\"senha\":\"jedi\"}";
		HttpHeaders headers = restTemplate.postForEntity("/login", str, String.class).getHeaders();
		this.adminHeader = new HttpEntity<>(headers);
	}

	@Before
	public void configWrongHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "1111");
		this.wrongHeader = new HttpEntity<>(headers);
	}

	@Before
	public void setup() {
		Cliente cliente = new Cliente(1L, "Neirivon Elias Cardoso", "neirivon@gmail.com", "80761917691",
				TipoCliente.PESSOAFISICA, pe.encode("jedi"));
		BDDMockito.when(clienteRepository.findOne(cliente.getId())).thenReturn(cliente);
	}
/*
	@Test
	public void listClientesWhenTokenIsIncorrectShouldReturnStatusCode403() {
		ResponseEntity<String> response = restTemplate.exchange("/clientes/1", GET, wrongHeader, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(403);
	}
	*/
	
	@Test
    public void getClientesByIdWhenTokenIsIncorrectShouldReturnStatusCode403() {
        ResponseEntity<String> response = restTemplate.exchange("/clientes/1",GET,wrongHeader, String.class);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(403);
    }
}
