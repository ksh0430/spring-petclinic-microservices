package org.springframework.samples.petclinic.visits;

import static com.jayway.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

/**
 * @author msoh
 */

public class VisitFunctionalTest {
	

    @BeforeClass
    public static void setup() {

        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/owners";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://visits-service.mybluemix.net"; 
        }
        RestAssured.baseURI = baseHost;

    }

    @Test
    public void makeSureThatVisitserviceIsValid() {
        given().when().get("/6/pets/7/visits").then().statusCode(200);
    }
    
    @Test
    public void shoudBeInvalidWithWrongPath() {
        given().when().get("/service/visits").then().statusCode(404);
    }

}
