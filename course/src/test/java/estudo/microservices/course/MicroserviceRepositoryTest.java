package estudo.microservices.course;

import estudo.microservices.core.model.Microservice;
import estudo.microservices.core.repository.MicroserviceRepository;
import static  org.assertj.core.api.Assertions.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;
import java.util.Optional;

/**
 * AutoConfigureTestDatabase --> when decided that the database isn't h2 but of the system
 */

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MicroserviceRepositoryTest {

    @Autowired
    private MicroserviceRepository microserviceRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void createShouldPersistData() {
        String nome = "jUnit";
        Microservice microservice = this.createMicroservice(nome);
        this.microserviceRepository.save(microservice);
        assertThat(microservice.getId()).isNotNull();
        assertThat(microservice.getNome()).isEqualTo(nome);
    }

    @Test
    public void deleteShouldRemoveData() {
        String nome = "jUnit";
        Microservice microservice = this.createMicroservice(nome);
        this.microserviceRepository.save(microservice);
        this.microserviceRepository.delete(microservice);
        Optional<Microservice> findMicroservice = microserviceRepository.findById(microservice.getId());
        assertThat(findMicroservice.isEmpty() ? null : findMicroservice.get()).isNull();
    }

    @Test
    public void updateShouldChangeAndPersistData() {
        String nome = "Mockito";
        Microservice microservice = this.createMicroservice("jUnit");
        this.microserviceRepository.save(microservice);
        microservice.setNome(nome);
        this.microserviceRepository.save(microservice);
        Optional<Microservice> findMicroservice = microserviceRepository.findById(microservice.getId());
        assertThat(findMicroservice.isPresent() ? findMicroservice.get().getNome() : "").isEqualTo(nome);
    }

    @Test
    public void createWhenNomeIsNullShouldThrowConstraintViolationException() {
        thrown.expect(ConstraintViolationException.class);
        thrown.expectMessage("The field 'nome' is mandatory");
        this.createMicroservice(null);
    }

    public Microservice createMicroservice(String nome) {
        return this.microserviceRepository.save(new Microservice(nome));
    }
}
