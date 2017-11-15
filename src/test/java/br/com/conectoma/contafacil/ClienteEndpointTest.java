package br.com.conectoma.contafacil;

import static java.util.Arrays.asList;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.conectoma.contafacil.domain.Cliente;
import br.com.conectoma.contafacil.domain.enums.TipoCliente;
import br.com.conectoma.contafacil.repositories.ClienteRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ClienteEndpointTest {
	
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

	@TestConfiguration
	static class SecurityConfig {

		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().basicAuthorization("neirivon@hotmail.com", "jedi");
		}
	}

	@Test
	public void listClientesWhenUsernameAndPasswordAreIncorrectShouldReturnStatusCode401() {
		restTemplate = restTemplate.withBasicAuth("1", "1");
		ResponseEntity<String> response = restTemplate.getForEntity("/clientes", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
	}

	@Test
	public void listClientesWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
		List<Cliente> clientes = asList(new Cliente(1L, "Neirivon Elias Cardoso", "neirivon@gmail.com", "80761917691", TipoCliente.PESSOAFISICA, pe.encode("jedi")),
				new Cliente(2L, "Celio Elias Cardoso", "celio@gmail.com", "80761917691", TipoCliente.PESSOAFISICA, pe.encode("jedi")));
		BDDMockito.when(clienteRepository.findAll()).thenReturn(clientes);
		ResponseEntity<String> response = restTemplate.getForEntity("/v1/protected/students/", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void getClientesByIdWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
		ResponseEntity<Cliente> response = restTemplate.getForEntity("/clientes/{id}", Cliente.class, 1L);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void getClientesByIdWhenUsernameAndPasswordAreCorrectAndClienteDoesNotExistShouldReturnStatusCode404() {
		ResponseEntity<Cliente> response = restTemplate.getForEntity("/clientes/{id}", Cliente.class, -1);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void deleteWhenUserHasRoleAdminAndClienteExistsShouldReturnStatusCode200() {
		BDDMockito.doNothing().when(clienteRepository).delete(1L);
		ResponseEntity<String> exchange = restTemplate.exchange("/clientes/{id}", HttpMethod.DELETE, null, String.class,
				1L);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	@WithMockUser(username = "xx", password = "xx", roles = { "CLIENTE", "ADMIN" })
	public void deleteWhenUserHasRoleAdminAndClienteDoesNotExistShouldReturnStatusCode404() throws Exception {
		BDDMockito.doNothing().when(clienteRepository).delete(1L);
		// ResponseEntity<String> exchange =
		// restTemplate.exchange("/clientes/{id}", DELETE, null, String.class,
		// -1L);
		// Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
		mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/{id}", -1L))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	@WithMockUser(username = "xx", password = "xx", roles = { "CLIENTE" })
	public void deleteWhenUserDoesNotHaveRoleAdminShouldReturnStatusCode403() throws Exception {
		BDDMockito.doNothing().when(clienteRepository).delete(1L);
		// ResponseEntity<String> exchange =
		// restTemplate.exchange("/clientes/{id}", DELETE, null, String.class,
		// -1L);
		// Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
		mockMvc.perform(MockMvcRequestBuilders.delete("/clientes/{id}", -1L))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
	}

	@Test
	public void createWhenNameIsNullShouldReturnStatusCode400BadRequest() throws Exception {
		Cliente cliente = new Cliente(3L, "Neirivon Elias Cardoso", "neirivon@gmail.com", "80761917691", TipoCliente.PESSOAFISICA, pe.encode("jedi"));
		BDDMockito.when(clienteRepository.save(cliente)).thenReturn(cliente);
		ResponseEntity<String> response = restTemplate.postForEntity("/clientes/", cliente, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
		Assertions.assertThat(response.getBody()).contains("fieldMessage", "O campo nome do cliente é obrigatório");
	}

	@Test
	public void createShouldPersistDataAndReturnStatusCode201() throws Exception {
		Cliente student = new Cliente(3L, "Neirivon Elias Cardoso", "neirivon@gmail.com", "80761917691", TipoCliente.PESSOAFISICA, pe.encode("jedi"));
		BDDMockito.when(clienteRepository.save(student)).thenReturn(student);
		ResponseEntity<Cliente> response = restTemplate.postForEntity("/clientes/", student, Cliente.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
		Assertions.assertThat(response.getBody().getId()).isNotNull();
	}

}
