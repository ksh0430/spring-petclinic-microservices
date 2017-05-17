
package org.springframework.samples.petclinic.vets.web;

import static org.junit.Assert.assertEquals;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.vets.model.Vet;
import org.springframework.samples.petclinic.vets.model.VetRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author msoh
 */
@RunWith(SpringRunner.class)
@WebMvcTest(VetResource.class)
@ActiveProfiles("test")
public class VetResourceTypeTest {

    @MockBean
    private VetRepository vetRepository;

    @Test
    public void shouldGetAListOfVetsInJSonFormat() throws Exception {
    	List<Vet> vList = vetRepository.findAll();
    	assertEquals(String.class, vList.get(0).getFirstName().getClass());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void shouldSaveStringForNameFormat(){
    	
    }
    
}