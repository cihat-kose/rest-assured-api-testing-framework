import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;


public class _01_APITestIntro {

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
    public void bodyArrayHasSizeTest2() {

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
                .body("places.size()", equalTo(1)); // places ın item size 1 e eşit mi
        ;
    }

    @Test
    public void combiningTest() {

        given()

                .when()
                .get("http://api.zippopotam.us/us/90210")

                .then()
                .statusCode(200)
                .body("places", hasSize(1))
                .body("places[0].state", equalTo("California"))
                .body("places[0].'place name'", equalTo("Beverly Hills"))
        ;
    }

    @Test
    public void pathParamTest() {

        given()

                .pathParam("country", "us")
                .pathParam("zipCode", 90210)
                .log().uri() // Before the request link works

                .when()
                .get("http://api.zippopotam.us/{country}/{zipCode}")

                .then()
                .statusCode(200)
        ;
    }

    @Test
    public void queryParamTest() {

        // https://gorest.co.in/public/v1/users?page=1

        given()

                .param("page", 1) // It is added to the link as ?page=1 // It can also be used with queryParam
                .log().uri() // Before the request link works

                .when()
                .get("https://gorest.co.in/public/v1/users") // ?page=1
                // .get("https://gorest.co.in/public/v1/users?page=1")

                .then()
                .statusCode(200)
                .log().body()
        ;
    }

    @Test
    public void queryParamTest2() {

        /**
         https://gorest.co.in/public/v1/users?page=3
         linkinden 1 den 10 a kadar sayfaları çağırdığınızda response'daki donen page degerlerinin
         çağrılan page nosu ile aynı olup olmadığını kontrol ediniz.
         ----------------------------------------------------------------------
         https://gorest.co.in/public/v1/users?page=3
         When you call pages 1 to 10 from the link, the returned page values in the response
         Check whether it is the same as the called page number.
         */

        for (int i = 1; i <= 10; i++) {

            given()

                    .param("page", i)
                    .log().uri()

                    .when()
                    .get("https://gorest.co.in/public/v1/users")
                    //.get("https://gorest.co.in/public/v1/users?page=i")

                    .then()
                    .statusCode(200)
                    //.log().body()
                    .body("meta.pagination.page", equalTo(i))
            ;
        }
    }
}
