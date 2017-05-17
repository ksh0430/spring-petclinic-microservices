package org.springframework.samples.petclinic.customers.web;


import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.customers.model.Owner;
import org.springframework.samples.petclinic.customers.model.OwnerRepository;
import org.springframework.samples.petclinic.customers.model.Pet;
import org.springframework.samples.petclinic.customers.model.PetRepository;
import org.springframework.samples.petclinic.customers.model.PetType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author msoh
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
public class PetResourceVerifyTest {

    @Autowired
    PetRepository petRepository;

    @Test 
    public void shouldAddPetWithProperElement() {
        
        Owner owner = new Owner();
        owner.setFirstName("George");
        owner.setLastName("Bush");
        
        Pet pet = new Pet();
        pet.setName("testDog");
        pet.setId(8);
        
        PetType petType = new PetType();
        petType.setId(6);
        pet.setType(petType);
        
        owner.addPet(pet);
        List<Pet> pList = owner.getPets();
        
        assertEquals(asList(pet), (pList));
    }
 
    @Test
    public void shouldGetAPet() throws Exception {
      Pet pet = petRepository.findOne(2);
      assertThat(petRepository.findAll()).contains(pet);
      Pet pet3 = petRepository.findOne(3);
      assertThat(petRepository.findAll()).contains(pet3);
      Pet pet4 = petRepository.findOne(4);
      assertThat(petRepository.findAll()).contains(pet4);
      Pet pet5 = petRepository.findOne(5);
      assertThat(petRepository.findAll()).contains(pet5);
    }
}
