package org.springframework.samples.petclinic.customers.web;

import static com.jayway.restassured.RestAssured.given;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;

/**
 * @author msoh
 */

public class CustomerFunctionalTest {

    @BeforeClass
    public static void setup() {

        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/owners";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://customers-service.mybluemix.net"; 
        }
        RestAssured.baseURI = baseHost;

    }

    @Test
    public void makeSureThatVetserviceIsValid() {
        given().when().get().then().statusCode(200);
    }
    
    @Test
    public void makeSureThatCustomerIsValid() {
        given().when().get("/6").then().statusCode(200);
    }
    
    @Test
    public void makeSureThatCustomerPetIsValid() {
        given().when().get("/6/pets/7").then().statusCode(200);
    }
    
    @Test
    public void shoudBeInvalidWithWrongPath() {
        given().when().get("/service/owners").then().statusCode(404);
    }
}
