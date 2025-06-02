import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class _11_ReqresUserTest {

    @Test
    public void firstTestScenario() {
        // This line sends an HTTP GET request to the given URL using RestAssured,
        // and returns a Response object representing the HTTP response.
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");

        // response.getStatusCode(): retrieves the status code from the HTTP response.
        // For example: 200 OK, 404 Not Found, etc.

        // Assert.assertEquals(response.getStatusCode(), 200):
        // Verifies that the status code is 200 (OK). If not, the test fails.
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void firstBddTestScenario() {

        given()
                // BDD-style usage of RestAssured.
                // This section defines the preconditions for the test.

                .when()
                // This section defines the action (sending an HTTP GET request).

                .get("https://reqres.in/api/users?page=2")
                // Sends a GET request to the given URL.

                .then()
                // This section defines the expected results or assertions.

                .statusCode(200)
                // Verifies that the HTTP response status code is 200 (OK).

                .log().body();
        // Logs the entire response body.
        // Useful for debugging and understanding the API structure during development.
        // RestAssured also provides .log().headers(), .cookies(), .all() etc., which can be used based on the needs.
    }

    @Test
    public void createUserTest() {
        baseURI = "https://reqres.in/api";
        // Defines the base URI for the API.

        HashMap<String, Object> data = new HashMap<>();
        // Used to build the request body as a key-value pair.

        data.put("name", "Kaan");
        data.put("job", "Tester");

        given()
                .body(data)
                // Attaches the request body to the HTTP request.

                .when()
                .post("/users")
                // Sends a POST request to the specified endpoint to create a new user.

                .then()
                .statusCode(201)
                // Expects a 201 Created status code as a result of successful creation.

                .log().body();
        // Logs the response body to view the created user info, including ID and timestamp.
    }
}