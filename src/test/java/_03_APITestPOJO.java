import model.Location;
import model.Place;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _03_APITestPOJO { // POJO = JSON Object
    @Test
    public void extractJSONAll() {

        Location locationObject =

                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .extract().body().as(Location.class);

        System.out.println("locationObject = " + locationObject);
        System.out.println("locationObject.getCountry() = " + locationObject.getCountry());
        System.out.println("locationObject.getPlaces() = " + locationObject.getPlaces());

        for (Place place : locationObject.getPlaces()) {
            System.out.println("place = " + place);
        }
    }
}
