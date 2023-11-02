import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


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
                .log().body() // Returning body json data; log().all(): everythıng that goes and come
                .statusCode(200) // Is the assertion status code 200?
        ;
    }

    @Test
    public void contentTypeTest() {

        given()
                // Preparation section is empty

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body() // Returning body json data; log().all(): everythıng that goes and come
                .statusCode(200) // Is the assertion status code 200?
                .contentType(ContentType.JSON) // Is the type of returned data JSON?
        ;
    }

    @Test
    public void checkCountryInResponseBody(){

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .log().body()
                .statusCode(200)  // Assertion --> Is the assertion status code 200?
                .contentType(ContentType.JSON) // Assertion --> Is the type of returned data JSON?
                .body("country", equalTo("United States")) // Assertion --> Is body's country variable equal to "United States"?
        ;
    }
}
