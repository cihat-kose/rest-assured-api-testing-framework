import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class _02_APITestExtract {
    @Test
    public void extractingJsonPath(){

        String countryName=
                given()
                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .extract().path("country") // PATH i country olan değeri EXTRACT yap
                ;


        System.out.println("country = " + countryName);
        Assert.assertEquals(countryName,"United States"); // alınan değer buna eşit mi
    }




}










