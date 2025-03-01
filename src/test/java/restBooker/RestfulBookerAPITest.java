package restBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RestfulBookerAPITest {

    private static int bookingId;
    private static String token;

    @BeforeAll
    public void setup() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
    }

    @Test
    @Order(1)
    @DisplayName("01 - Health Check")
    public void healthCheck() {
        given()
                .when().get("/ping")
                .then()
                .statusCode(201);
    }

    @Test
    @Order(2)
    @DisplayName("02 - Create a New Booking")
    public void createBooking() {
        String requestBody = "{\n" +
                "    \"firstname\": \"Elias\",\n" +
                "    \"lastname\": \"Harrington\",\n" +
                "    \"totalprice\": 180,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2024-03-01\",\n" +
                "        \"checkout\": \"2024-03-05\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Dinner\"\n" +
                "}";

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

    @Test
    @Order(3)
    @DisplayName("03 - Retrieve Booking Details")
    public void getBookingById() {
        given()
                .when().get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Elias"))
                .body("lastname", equalTo("Harrington"));
    }

    @Test
    @Order(4)
    @DisplayName("04 - Retrieve All Bookings")
    public void getBookingIds() {
        given()
                .when().get("/booking")
                .then()
                .statusCode(200)
                .body("bookingid", not(empty()));
    }

    @Test
    @Order(5)
    @DisplayName("05 - Generate Authentication Token")
    public void createAuthToken() {
        String authBody = "{\n" +
                "    \"username\": \"admin\",\n" +
                "    \"password\": \"password123\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(authBody)
                .when().post("/auth")
                .then()
                .statusCode(200)
                .extract().response();

        token = response.path("token");
    }

    @Test
    @Order(6)
    @DisplayName("06 - Update Booking (PUT)")
    public void updateBooking() {
        String updatedBody = "{\n" +
                "    \"firstname\": \"Caspian\",\n" +
                "    \"lastname\": \"Montague\",\n" +
                "    \"totalprice\": 250,\n" +
                "    \"depositpaid\": false,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2024-03-02\",\n" +
                "        \"checkout\": \"2024-03-06\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

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

    @Test
    @Order(7)
    @DisplayName("07 - Partial Update Booking (PATCH)")
    public void partialUpdateBooking() {
        String updatedBody = "{\n" +
                "    \"firstname\": \"Lucian\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(updatedBody)
                .when().patch("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Lucian"));
    }

    @Test
    @Order(8)
    @DisplayName("08 - Delete Booking")
    public void deleteBooking() {
        given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .when().delete("/booking/" + bookingId)
                .then()
                .statusCode(201);
    }
}
