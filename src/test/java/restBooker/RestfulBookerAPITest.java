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
    @DisplayName("01 - Sağlık Kontrolü")
    public void healthCheck() {
        given()
                .when().get("/ping")
                .then()
                .statusCode(201);
    }

    @Test
    @Order(2)
    @DisplayName("02 - Yeni Rezervasyon Oluşturma")
    public void createBooking() {
        String requestBody = "{\n" +
                "    \"firstname\": \"John\",\n" +
                "    \"lastname\": \"Doe\",\n" +
                "    \"totalprice\": 150,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2024-03-01\",\n" +
                "        \"checkout\": \"2024-03-05\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/booking")
                .then()
                .statusCode(200)
                .body("booking.firstname", equalTo("John"))
                .body("booking.lastname", equalTo("Doe"))
                .extract().response();

        bookingId = response.path("bookingid");
    }

    @Test
    @Order(3)
    @DisplayName("03 - Rezervasyon Bilgilerini Getirme")
    public void getBookingById() {
        given()
                .when().get("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("John"))
                .body("lastname", equalTo("Doe"));
    }

    @Test
    @Order(4)
    @DisplayName("04 - Rezervasyon Listesini Getirme")
    public void getBookingIds() {
        given()
                .when().get("/booking")
                .then()
                .statusCode(200)
                .body("bookingid", not(empty()));
    }

    @Test
    @Order(5)
    @DisplayName("05 - Kullanıcı İçin Token Oluşturma")
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
    @DisplayName("06 - Rezervasyon Güncelleme (PUT)")
    public void updateBooking() {
        String updatedBody = "{\n" +
                "    \"firstname\": \"Jane\",\n" +
                "    \"lastname\": \"Smith\",\n" +
                "    \"totalprice\": 200,\n" +
                "    \"depositpaid\": false,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2024-03-02\",\n" +
                "        \"checkout\": \"2024-03-06\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Lunch\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(updatedBody)
                .when().put("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Jane"))
                .body("lastname", equalTo("Smith"));
    }

    @Test
    @Order(7)
    @DisplayName("07 - Rezervasyon Güncelleme (PATCH)")
    public void partialUpdateBooking() {
        String updatedBody = "{\n" +
                "    \"firstname\": \"Alice\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .body(updatedBody)
                .when().patch("/booking/" + bookingId)
                .then()
                .statusCode(200)
                .body("firstname", equalTo("Alice"));
    }

    @Test
    @Order(8)
    @DisplayName("08 - Rezervasyon Silme")
    public void deleteBooking() {
        given()
                .contentType(ContentType.JSON)
                .cookie("token", token)
                .when().delete("/booking/" + bookingId)
                .then()
                .statusCode(201);
    }
}
