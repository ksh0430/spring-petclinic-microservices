package org.springframework.samples.petclinic.vets.web;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

import static com.jayway.restassured.RestAssured.given;

/**
 * @author msoh
 */

public class VetFunctionalTest {
	
    @BeforeClass
    public static void setup() {

        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/vets";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://vets-service.mybluemix.net";
        }
        RestAssured.baseURI = baseHost;

    }

    @Test
    public void makeSureThatVetserviceIsValid() {
        given().when().get().then().statusCode(200);
    }
    
    @Test
    public void shoudBeInvalidWithWrongPath() {
        given().when().get("/service/vets").then().statusCode(404);
    }
    
}
