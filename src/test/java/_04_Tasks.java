import io.restassured.http.ContentType;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class _04_Tasks {
    @Test
    public void task01() {

        /**
         * Task 1
         * create a request to https://jsonplaceholder.typicode.com/todos/2
         * expect status 200
         * expect content type JSON
         * expect title in response body to be "quis ut nam facilis et officia qui"
         */

        given()

                .when()
                .get("https://jsonplaceholder.typicode.com/todos/2")

                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("title", equalTo("quis ut nam facilis et officia qui"));
    }

    @Test
    public void task02() {

        /**
         * Task 2
         * create a request to https://jsonplaceholder.typicode.com/todos/2
         * expect status 200
         * expect content type JSON
         *a) expect response completed status to be false(hamcrest)
         *b) extract completed field and testNG assertion(testNG)
         */

        boolean isCompleted =

                given()

                        .when()
                        .get("https://jsonplaceholder.typicode.com/todos/2")

                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON)
                        .body("completed", equalTo(false)) // Assertion with hamcrest
                        .extract().path("completed");

        Assert.assertFalse(isCompleted); // Assertion with TestNG
    }
}
