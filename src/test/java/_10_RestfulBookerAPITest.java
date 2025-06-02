import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _10_RestfulBookerAPITest {

    private int bookingId;
    private String token;

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test(priority = 1, description = "Checks if the /ping endpoint responds with status 201")
    public void healthCheck() {
        given()
                .when().get("/ping")
                .then()
                .statusCode(201);
    }

    @Test(priority = 2, description = "Creates a new booking and verifies the response content")
    public void createBooking() {
        String requestBody = """
                {
                    "firstname": "Elias",
                    "lastname": "Harrington",
                    "totalprice": 180,
                    "depositpaid": true,
                    "bookingdates": {
                        "checkin": "2024-03-01",
                        "checkout": "2024-03-05"
                    },
                    "additionalneeds": "Dinner"
                }
                """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/booking")
                .then()
                .statusCode(200)
                .body("booking.firstname", equalTo("Elias"))
                .body("booking.lastname", equalTo("Harrington"))
                .extract().response();

        bookingId = response.path("bookingid");
    }

    @Test(priority = 3, description = "Retrieves the created booking by ID and verifies its contents")
    public void getBookingById() {
        given()
                .when().get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Elias"))
                .body("lastname", equalTo("Harrington"));
    }

    @Test(priority = 4, description = "Retrieves all booking IDs to ensure the booking list is not empty")
    public void getBookingIds() {
        given()
                .when().get("/booking")
                .then()
                .statusCode(200)
                .body("bookingid", not(empty()));
    }

    @Test(priority = 5, description = "Authenticates with admin credentials and retrieves access token")
    public void createAuthToken() {
        String authBody = """
                {
                    "username": "admin",
                    "password": "password123"
                }
                """;

        Response response = given()
                .contentType(ContentType.JSON)
                .body(authBody)
                .when().post("/auth")
                .then()
                .statusCode(200)
                .extract().response();

        token = response.path("token");
    }

    @Test(priority = 6, description = "Updates the booking fully using PUT and verifies new data")
    public void updateBooking() {
        String updatedBody = """
                {
                    "firstname": "Caspian",
                    "lastname": "Montague",
                    "totalprice": 250,
                    "depositpaid": false,
                    "bookingdates": {
                        "checkin": "2024-03-02",
                        "checkout": "2024-03-06"
                    },
                    "additionalneeds": "Breakfast"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(updatedBody)
                .when().put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Caspian"))
                .body("lastname", equalTo("Montague"));
    }

    @Test(priority = 7, description = "Performs a partial update (PATCH) on firstname and verifies it")
    public void partialUpdateBooking() {
        String updatedBody = """
                {
                    "firstname": "Lucian"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(updatedBody)
                .when().patch("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Lucian"));
    }

    @Test(priority = 8, description = "Deletes the booking by ID using the auth token")
    public void deleteBooking() {
        given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .when().delete("/booking/" + bookingId)
                .then()
                .statusCode(201);
    }
}
