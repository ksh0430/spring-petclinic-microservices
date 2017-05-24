
package org.springframework.samples.petclinic.vets.web;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.samples.petclinic.vets.model.VetRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 ** @author msoh
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class VetResourceVerifyTest {

	@Autowired
    private VetRepository vetRepository;

    @Test(expected = NullPointerException.class)
    public void checkVetWithNonValidId() throws Exception {
    	Vet vet = vetRepository.findOne(100);
    	vet.getFirstName();
    }
    
    
    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void testEmptyList() {
        Vet vet = vetRepository.findOne(3000);
        asList(vet).get(1);
    }
    
    @Test
    public void shouldGetAVet() throws Exception {
      Vet vet = vetRepository.findOne(3000);
      assertThat(vetRepository.findAll()).doesNotContain(vet);
    }
    
}