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
    public void checkCountryInResponseBody() {

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

    @Test
    public void checkStateInResponseBody() {

        /** Soru : "http://api.zippopotam.us/us/90210"  endpoint inden dönen
         place dizisinin ilk elemanının state değerinin "California" olduğunu doğrulayınız
         ------------------------------------------------------------------------------------
         Question: Returning from "http://api.zippopotam.us/us/90210" endpoint
         Verify that the state value of the first element of the place array is "California" */

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .statusCode(200)
                .body("places[0].state", equalTo("California"))
        ;
    }

    @Test
    public void checkHasItem() {

        /**
         * Soru : "http://api.zippopotam.us/tr/01000"  endpointinin dönen
         * place dizisinin herhangi bir elemanında "Dörtağaç Köyü" değerinin
         * olduğunu doğrulayınız
         * -------------------------------------------------------------------
         * Question: Returning endpoint "http://api.zippopotam.us/tr/01000"
         * The value of "Dörtağaç Village" in any element of the place array
         * Verify that it is
         */

        given()

                .when()
                .get("http://api.zippopotam.us/tr/01000")

                .then()
                //.log().body()
                .body("places.'place name'", hasItem("Dörtağaç Köyü"))
                .statusCode(200)
        ;
    }

    @Test
    public void bodyArrayHasSizeTest() {

        /**
         * Soru : "http://api.zippopotam.us/us/90210" (endpoint) in dönen
         * place dizisinin dizi uzunluğunun 1 olduğunu doğrulayınız.
         * ----------------------------------------------------------------
         * Question: Returning from "http://api.zippopotam.us/us/90210" (endpoint)
         * Verify that the string length of the place array is 1.
         */

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .body("places", hasSize(1)); // Is the item size of places equal to 1?
        ;
    }

    @Test
    public void bodyArrayHasSizeTest2(){
        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .body("places.size()", equalTo(1)); // places ın item size 1 e eşit mi
        ;
    }
}
