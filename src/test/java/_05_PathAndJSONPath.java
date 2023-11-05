import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class _05_PathAndJSONPath {

    @Test
    public void extractingPath() {

        String postCode =

                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().path("'post code'");

        System.out.println("postCode = " + postCode);
    }

    @Test
    public void extractingJSONPath() {

        int postCode =

                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().jsonPath().getInt ("'post code'");

        System.out.println("postCode = " + postCode);
    }
}
