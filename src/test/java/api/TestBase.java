package test.java.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;


public class TestBase {
    public static final String AUTH = "";
    public String apiUri = "https://swapi.co/api";
    public RequestSpecBuilder apiRequestSpecBuilder = new RequestSpecBuilder();
    public RequestSpecification apiRequestSpec;

    @BeforeClass
    public void baseSetUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        //NoAuth
//        apiRequestSpecBuilder.addHeader("Authorization", AUTH);
        apiRequestSpecBuilder.addHeader("Content-Type", "application/json; charset=ISO-8859-1");
        apiRequestSpecBuilder.setRelaxedHTTPSValidation();
        apiRequestSpec = apiRequestSpecBuilder.build();
    }
}
