package test.java.api;

import org.testng.annotations.Test;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class ApiTest extends TestBase {

    private String homeworld;
    private ArrayList<String> films;

    @Test
    public void findLuke() {
        homeworld = given().
                spec(apiRequestSpec).
                log().all().
                when().
                get(apiUri + "/people/1/").
                then().
                statusCode(200).
                body("name", equalTo("Luke Skywalker")).
                log().all().
                extract().
                path("homeworld");
    }

    @Test(dependsOnMethods = "findLuke")
    public void checkLukesPlanet() {
        films = given().
                log().all().
                when().
                get(homeworld).
                then().
                statusCode(200).
                body("name", equalTo("Tatooine")).
                body("population", equalTo("200000")).
                log().all().
                extract().
                path("films");
    }

    @Test(dependsOnMethods = "checkLukesPlanet")
    public void checkFilm() {
        given().
                log().all().
                when().
                get(films.get(0)).
                then().
                statusCode(200).
                body("title", equalTo("Attack of the Clones")).
                body("characters", hasItem(apiUri+"/people/1/")).
                body("planets", hasItem(apiUri+"/planets/1/")).
                log().all();
    }
}
