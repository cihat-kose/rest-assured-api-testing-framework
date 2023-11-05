import model.Location;
import model.Place;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class _03_APITestPOJO { // POJO = JSON Object
    @Test
    public void extractJsonAll_POJO() {

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

    @Test
    public void extractPOJO_Question() {

        Location locationObject =

                given()

                        .when()
                        .get("http://api.zippopotam.us/tr/01000")

                        .then()
                        .extract().body().as(Location.class)
                ;

        for(Place place:locationObject.getPlaces()){
            if(place.getPlacename().equalsIgnoreCase("Dörtağaç Köyü")){
                System.out.println("place = " + place);
            }
        }
        System.out.println("locationObject.getPlaces().get(2) = " + locationObject.getPlaces().get(2));
    }

    // http://api.zippopotam.us/tr/01000  endpointinden dönen verilerden "Dörtağaç Köyü" ait bilgileri yazdırınız
}
