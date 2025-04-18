package campus;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Cookies;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.annotations.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _09_CountryTest {

    Faker randomGenerator = new Faker();
    RequestSpecification reqSpec;
    String countryID = "";
    String randomCountryName = "";
    String randomCountryCode = "";

    @BeforeClass
    public void Setup() {

        baseURI = "https://test.mersys.io/";

        Map<String, String> userCredential = new HashMap<>();
        userCredential.put("username", "Campus25");
        userCredential.put("password", "Campus.2524");
        userCredential.put("rememberMe", "true");

        Cookies cookies =

                given()
                        .body(userCredential)
                        .contentType(ContentType.JSON)

                        .when()
                        .post("/auth/login")

                        .then()
                        //.log().all()
                        .statusCode(200)
                        .extract().response().getDetailedCookies();
        ;

        reqSpec = new RequestSpecBuilder()
                .addCookies(cookies)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void createCountry() {

        randomCountryName = randomGenerator.address().country() + randomGenerator.address().countryCode();
        randomCountryCode = randomGenerator.address().countryCode();

        Map<String, String> newCountry = new HashMap<>();
        newCountry.put("name", randomCountryName);
        newCountry.put("code", randomCountryCode);

        countryID =

                given()
                        .spec(reqSpec)
                        .body(newCountry)
                        //.log().all()
                        .when()
                        .post("school-service/api/countries")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");
        ;
    }

    @Test(dependsOnMethods = "createCountry")
    public void createCountryNegative() {

        Map<String, String> newCountry = new HashMap<>();
        newCountry.put("name", randomCountryName);
        newCountry.put("code", randomCountryCode);

        given()
                .spec(reqSpec)
                .body(newCountry)

                .when()
                .post("school-service/api/countries")

                .then()
                .log().body()
                .statusCode(400)
                .body("message", containsString("already"))
        ;
    }

    @Test(dependsOnMethods = "createCountryNegative")
    public void updateCountry() {

        String newCountryName = "Updated Country" + randomGenerator.number().digits(5);
        Map<String, String> updateCountry = new HashMap<>();
        updateCountry.put("id", countryID);
        updateCountry.put("name", newCountryName);
        updateCountry.put("code", "12345");

        given()
                .spec(reqSpec)
                .body(updateCountry)

                .when()
                .put("school-service/api/countries")

                .then()
                .log().body()
                .statusCode(200)
                .body("name", equalTo(newCountryName))
        ;
    }

    @Test(dependsOnMethods = "updateCountry")
    public void deleteCountry() {

        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/countries/" + countryID)

                .then()
                .log().body()
                .statusCode(200)
        ;
    }

    @Test(dependsOnMethods = "deleteCountry")
    public void deleteCountryNegative() {

        given()
                .spec(reqSpec)
                .when()
                .delete("/school-service/api/countries/" + countryID)

                .then()
                .log().body()
                .statusCode(400)
                .body("message", equalTo("Country not found"))
        ;
    }

    /** The following section is our options when we need to send translate. */
    @Test
    public void createCountryAllParameter() {

        randomCountryName = randomGenerator.address().country() + randomGenerator.address().countryCode();
        randomCountryCode = randomGenerator.address().countryCode();

       //  Object[] arr = new Object[1];
        Map<String, Object> newCountry = new HashMap<>();
        newCountry.put("name", randomCountryName);
        newCountry.put("code", randomCountryCode);
        newCountry.put("translateName", new Object[1]); // arr

        given()
                .spec(reqSpec)
                .body(newCountry)
                //.log().all()
                .when()
                .post("school-service/api/countries")

                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id");
        ;
    }

    @Test
    public void createCountryAllParameterClass() {

        randomCountryName = randomGenerator.address().country() + randomGenerator.address().countryCode();
        randomCountryCode = randomGenerator.address().countryCode();

        Country newCountry = new Country();
        newCountry.name = randomCountryName;
        newCountry.code = randomCountryCode;
        newCountry.translateName = new Object[1];

        given()
                .spec(reqSpec)
                .body(newCountry)
                //.log().all()

                .when()
                .post("school-service/api/countries")

                .then()
                .log().body()
                .statusCode(201)
                .extract().path("id");
        ;
    }
}
