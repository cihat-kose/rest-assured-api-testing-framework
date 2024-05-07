import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class _03_APITestExtract {

    @Test
    public void extractingJSONPath() {

        String countryName =

                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .extract().path("country") // Make PATH i country value EXTRACT
                ;


        System.out.println("country = " + countryName);
        Assert.assertEquals(countryName, "United States"); // Is the received value equal to this?
    }


    @Test
    public void extractingJSONPath2() {

        /**
         * Soru: "http://api.zippopotam.us/us/90210"  endpoint inden dönen place dizisinin ilk elemanının
         * state değerinin "California" olduğunu testNG Assertion ile doğrulayınız
         * ------------------------------------------------------------------------------------------------------------
         * Question: The first element of the place array returned from the endpoint "http://api.zippopotam.us/us/90210"
         * verify that the state value is "California" with testNG Assertion
         */


        String stateName =

                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")


                        .then()
                        .log().body()
                        .extract().path("places[0].state");

        System.out.println("State name = " + stateName);
        Assert.assertEquals(stateName, "California");
    }

    @Test
    public void extractingJSONPath3() {

        /**
         * Soru: "http://api.zippopotam.us/us/90210"  endpoint inden dönen place dizisinin ilk elemanının
         * place name değerinin "Beverly Hills" olduğunu testNG Assertion ile doğrulayınız
         * ------------------------------------------------------------------------------------------------------------
         * Question: The first element of the place array returned from the endpoint "http://api.zippopotam.us/us/90210"
         * verify that the place name value is "Beverly Hills" with testNG Assertion
         */

        String placeName =

                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .extract().path("places[0].'place name'")  // places[0]["place name"] alternative
                ;


        System.out.println("Place name = " + placeName);
        Assert.assertEquals(placeName, "Beverly Hills");
    }

    @Test
    public void extractingJSONPath4() {

        /**
         * Soru: "https://gorest.co.in/public/v1/users"  endpoint inden dönen limit bilgisinin 10 olduğunu testNG ile doğrulayınız.
         * ------------------------------------------------------------------------------------------------------------
         * Question: Verify with testNG that the limit information returned from the "https://gorest.co.in/public/v1/users" endpoint is 10.
         */

        int limit =

                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .log().body()
                        .extract().path("meta.pagination.limit");

        System.out.println("limit = " + limit);
        Assert.assertEquals(limit, 10, "The number of limits does not equal 10!");
    }

    @Test
    public void extractingJSONPath5() {

        List<Integer> IDs =

                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .log().body()
                        .extract().path("data.id");
        ;

        System.out.println("IDs = " + IDs);
    }

    @Test
    public void extractingJSONPath6() {

        // After the above test, print all the names returned at the end of the same endpoint.

        List<String> names =

                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .extract().path("data.name");
        ;

        System.out.println("names = " + names);
    }

    @Test
    public void extractingJSONPathResponseAll() {

        Response incomingData =

                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .log().body()
                        .extract().response();

        List<Integer> iDs = incomingData.path("data.id");
        List<String> names = incomingData.path("data.name");
        int limit = incomingData.path("meta.pagination.limit");

        System.out.println("iDs = " + iDs);
        System.out.println("names = " + names);
        System.out.println("limit = " + limit);

        Assert.assertTrue(names.contains("Chidaatma Gandhi"));
        Assert.assertTrue(iDs.contains(5673026));
        Assert.assertEquals(limit, 10);
    }
}










