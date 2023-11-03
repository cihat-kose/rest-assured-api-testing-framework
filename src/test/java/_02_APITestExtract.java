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




}










