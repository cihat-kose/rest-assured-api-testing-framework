import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class _02_APITestExtract {

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
         * Soru : "http://api.zippopotam.us/us/90210"  endpoint inden dönen place dizisinin ilk elemanının
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
                .extract().path("places[0].state")

        ;

        System.out.println("State name = " + stateName);
        Assert.assertEquals(stateName, "California");
    }



}










