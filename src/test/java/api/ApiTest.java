package test.java.api;

import main.java.People;
import main.java.Planet;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;

public class ApiTest extends TestBase {

    private People luke;
    private Planet planet;

    @Test
    public void findLuke() {
        luke = given().
                spec(apiRequestSpec).
                when().
                get(apiUri + "/people/1/").
                then().
                statusCode(200).
                body("name", equalTo("Luke Skywalker")).
                extract().response().as(People.class);
    }

    @Test(dependsOnMethods = "findLuke")
    public void findLukesPlanet() {
        planet = given().
                when().
                get(luke.getHomeworld()).
                then().
                statusCode(200).
                body("name", equalTo("Tatooine")).
                body("population", equalTo("200000")).
                extract().response().as(Planet.class);
    }

    @Test(dependsOnMethods = "findLukesPlanet")
    public void checkFilm() {
        given().
                when().
                get(planet.getFilms().get(0)).
                then().
                statusCode(200).
                body("title", equalTo("Attack of the Clones")).
                body("planets", hasItem(planet.getUrl())).
                body("characters", hasItem(luke.getUrl()));
    }
}
