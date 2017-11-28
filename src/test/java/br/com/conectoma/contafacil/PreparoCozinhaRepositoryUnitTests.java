package br.com.conectoma.contafacil;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.conectoma.contafacil.domain.PreparoCozinha;
import br.com.conectoma.contafacil.repositories.PreparoCozinhaRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PreparoCozinhaRepositoryUnitTests {
	
	@Autowired
	private PreparoCozinhaRepository preparoCozinhaRepository;
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createShouldPersistData() {
		
		PreparoCozinha pc1 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		PreparoCozinha pc2 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		PreparoCozinha pc3 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		
		this.preparoCozinhaRepository.save(Arrays.asList(pc1, pc2, pc3));
		
		Assertions.assertThat(pc1.getId()).isNotNull();
		Assertions.assertThat(pc1.getObservacao()).isEqualTo("Sem coentro");
		Assertions.assertThat(pc1.getHora()).isEqualTo("18:00");
		Assertions.assertThat(pc1.getEstado()).isTrue();
		
	}
	
	@Test
	public void deleteShouldRemoveData() {
		
		PreparoCozinha pc1 = new PreparoCozinha(null, "Sem coentro", "18:00", true);
		this.preparoCozinhaRepository.save(pc1);
		preparoCozinhaRepository.delete(pc1);
		assertThat(preparoCozinhaRepository.findOne(pc1.getId())).isNull();
		
	}
	
	@Test
	public void updateShouldChangeAndPersistData() {
		
		PreparoCozinha pc1 = new PreparoCozinha(null, "Como milho e batata", "19:00", false);
		this.preparoCozinhaRepository.save(pc1);
		pc1.setObservacao("tira o coentro dessa comida");
		pc1.setHora("19:30");
		pc1.setEstado(true);
		
		this.preparoCozinhaRepository.save(pc1);
		pc1 = this.preparoCozinhaRepository.findOne(pc1.getId());
		
		Assertions.assertThat(pc1.getObservacao()).isEqualTo("tira o coentro dessa comida");
		Assertions.assertThat(pc1.getHora()).isEqualTo("19:30");
		Assertions.assertThat(pc1.getEstado()).isTrue();
		
	}
	
	
	@Test
	public void findByObservacaoIgnoreCaseContainingShouldIgnoreCase() {
		
		PreparoCozinha pc1 = new PreparoCozinha(null, "COMO milho e batata", "19:00", false);
		PreparoCozinha pc2 = new PreparoCozinha(null, "como milho e batata", "19:10", true);
		this.preparoCozinhaRepository.save(pc1);
		this.preparoCozinhaRepository.save(pc2);
		
		List<PreparoCozinha> preparoCozinhaList = preparoCozinhaRepository.findByObservacaoIgnoreCaseContaining("como milho e batata");
		
		assertThat(preparoCozinhaList.size()).isEqualTo(2);
	}
	
	@Test
	public void createWhenHoraIsNullShouldThrowConstraintViolationException() {
		
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("O campo hora não pode ser vazio");
		
		this.preparoCozinhaRepository.save(new PreparoCozinha());
	}

}
