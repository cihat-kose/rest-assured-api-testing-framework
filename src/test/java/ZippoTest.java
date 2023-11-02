import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ZippoTest {

    @Test
    public void test01() {

        given()
                // Preparatory procedures codes

                .when()
                // Endpoint (url), giving the method and sending the request

                .then()
               // Assertion, test, data operations
        ;
    }

    @Test
    public void statusCodeTest() {

        given()
                // Preparation section is empty

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body() // Returning body json data, log().all(): everythıng that goes and come
                .statusCode(200) // Is the assertion status code 200?
        ;
    }
}
