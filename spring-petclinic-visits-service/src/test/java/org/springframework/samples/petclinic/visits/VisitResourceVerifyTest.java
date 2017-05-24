package org.springframework.samples.petclinic.visits;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.samples.petclinic.visits.model.Visit;
import org.springframework.samples.petclinic.visits.model.VisitRepository;
import org.springframework.samples.petclinic.visits.web.VisitResource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")

public class VisitResourceVerifyTest {

    @Autowired
    private VisitRepository visitRepository;
    
    @MockBean
    private VisitResource visitResource;
    
    @Test(expected = java.lang.IndexOutOfBoundsException.class)
    public void testEmptyList() {
    	Visit visit = visitRepository.findOne(3000);
        asList(visit).get(1);
    }
    
    @Test
    public void shouldGetAVisit() throws Exception {
    	Visit visit = visitRepository.findOne(3000);
    	assertThat(visitRepository.findAll()).doesNotContain(visit);
    }
    
    @Test(expected = NullPointerException.class)
    public void createVisitWithNull() throws Exception {
    	Visit visit = null;
    	Integer petId = null;
    	visitResource.create(visit, petId);
    }
    
    
    @Test(expected = NullPointerException.class)
    public void createVisitWithNull2() throws Exception {
    	Visit visit = null;
    	Integer petId = null;
    	visitResource.create(visit, petId);
    }
    
}
