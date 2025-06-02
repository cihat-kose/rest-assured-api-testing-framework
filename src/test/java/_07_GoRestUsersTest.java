import model.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.annotations.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _07_GoRestUsersTest {
    Faker randomGenerator = new Faker();
    int userID = 0;
    RequestSpecification reqSpec;

    @BeforeClass
    public void setup() {

        baseURI = "https://gorest.co.in/public/v2/users";

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization", "Bearer 1fcbe157c72a51f2477a4f42494d197421e70f8e05028ba4e1e2b2c74f503392")
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test(enabled = false)
    public void createUserJSON() {

        String randomFullName = randomGenerator.name().fullName();
        String randomEmail = randomGenerator.internet().emailAddress();

        userID =

                given()
                        .header("Authorization", "Bearer 1fcbe157c72a51f2477a4f42494d197421e70f8e05028ba4e1e2b2c74f503392")
                        .body("{\"name\":\"" + randomFullName + "\", \"gender\":\"male\", \"email\":\"" + randomEmail + "\", \"status\":\"active\"}")
                        .contentType(ContentType.JSON)

                        .when()
                        .post("https://gorest.co.in/public/v2/users")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");
        ;
        System.out.println("userID = " + userID);
    }

    @Test
    public void createUserMAP() {

        String randomFullName = randomGenerator.name().fullName();
        String randomEmail = randomGenerator.internet().emailAddress();

        Map<String, String> newUser = new HashMap<>();
        newUser.put("name", randomFullName);
        newUser.put("gender", "male");
        newUser.put("email", randomEmail);
        newUser.put("status", "active");

        userID =

                given()
                        .spec(reqSpec)
                        .body(newUser)
                        .contentType(ContentType.JSON)

                        .when()
                        .post("")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");
        ;
        System.out.println("userID = " + userID);
    }

    @Test(enabled = false)
    public void createUserClass() {

        String randomFullName = randomGenerator.name().fullName();
        String randomEmail = randomGenerator.internet().emailAddress();

        User newUser = new User();
        newUser.name = randomFullName;
        newUser.email = randomEmail;
        newUser.gender = "male";
        newUser.status = "active";

        userID =

                given()
                        .header("Authorization", "Bearer 28605c0029e01366e56921feb365376892ddde09e0eb36e1aa40203896f50cd8")
                        .body(newUser)
                        .contentType(ContentType.JSON)

                        .when()
                        .post("https://gorest.co.in/public/v2/users")

                        .then()
                        .log().body()
                        .statusCode(201)
                        .extract().path("id");
        ;
        System.out.println("userID = " + userID);
    }

    @Test(dependsOnMethods = "createUserMAP")
    public void getUserById() {

        given()
                .spec(reqSpec)

                .when()
                .get("" + userID)

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(userID))
        ;
    }

    @Test(dependsOnMethods = "getUserById")
    public void updateUser() {

        Map<String, String> updateUser = new HashMap<>();
        updateUser.put("name", "Kerem Said");

        given()
                .spec(reqSpec)
                .body(updateUser)

                .when()
                .put("" + userID)

                .then()
                .log().body()
                .statusCode(200)
                .body("id", equalTo(userID))
                .body("name", equalTo("Kerem Said"))
        ;
    }

    // Test the user delete API
    @Test(dependsOnMethods = "updateUser")
    public void deleteUser() {

        given()
                .spec(reqSpec)

                .when()
                .delete("" + userID)

                .then()
                //.log().all()
                .statusCode(204)
        ;
    }

    // Perform the user delete negative API test
    @Test(dependsOnMethods = "deleteUser")
    public void deleteUserNegative() {

        given()
                .spec(reqSpec)

                .when()
                .delete("" + userID)

                .then()
                //.log().all()
                .statusCode(404)
        ;
    }
}
