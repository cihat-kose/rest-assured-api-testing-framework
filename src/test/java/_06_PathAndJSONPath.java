import io.restassured.response.Response;
import model.Location;
import model.Place;
import model.User;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class _06_PathAndJSONPath {

    @Test
    public void extractingPath() {

        String postCode =

                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().path("'post code'");

        System.out.println("postCode = " + postCode);
    }

    @Test
    public void extractingJSONPath() {

        int postCode =

                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().jsonPath().getInt("'post code'");

        System.out.println("postCode = " + postCode);
    }

    @Test
    public void getZipCode() {

        Response response =

                given()

                        .when()
                        .get("http://api.zippopotam.us/us/90210")

                        .then()
                        .log().body()
                        .extract().response();

        Location locationPathAs = response.as(Location.class);
        System.out.println("locationPathAs = " + locationPathAs);

        List<Place> places = response.jsonPath().getList("places", Place.class);
        System.out.println("places = " + places);

        /**
         In the previous examples (as) corresponding to the entire structure for Class transformations
         By typing all the necessary classes, we converted them and got the elements we wanted.

         Here (JSONPath) allows us to convert the data in between into a class and get it as a list.
         We used JSONPath, which allows Thus, data was received with a single class without the need for other classes.

         path: gives data directly without allowing class or type conversion. like List<String>
         jsonPath: Allows class conversion and type conversion and gives the data in the format we want.

         -----------------------------------------------------------------------------------------------
         Daha önceki örneklerde (as) Class dönüşümleri için tüm yapıya karşılık gelen
         gereken tüm classları yazarak dönüştürüp istediğimiz elemanlara ulaşıyorduk.

         Burada ise(JSONPath) aradaki bir veriyi classa dönüştürerek bir list olarak almamıza
         imkan veren JSONPath i kullandık. Böylece  diğer classlara gerek kalmadan tek class ile veri alınmış oldu

         path     : class veya tip dönüşümüne imkan vermeden direkt veriyi verir. List<String> gibi
         jsonPath : class dönüşümüne ve tip dönüşümüne izin vererek, veriyi istediğimiz formatta verir.
         */
    }

    @Test
    public void getUsersV1() {

        /**
         Print only the "data" part returned from the https://gorest.co.in/public/v1/users endpoint using POJO conversion.
         -------------------------------------------------------------------------------
         https://gorest.co.in/public/v1/users  endpointinden dönen sadece "data" kısmını POJO dönüşümü ile alarak yazdırınız.
         */

        List<User> userData =

                given()

                        .when()
                        .get("https://gorest.co.in/public/v1/users")

                        .then()
                        .log().body()
                        .extract().jsonPath().getList("data", User.class);

        System.out.println("userData.get(0).getEmail() = " + userData.get(0).getEmail());

        for (User user : userData) {
            System.out.println("user = " + user);
        }
    }
}
