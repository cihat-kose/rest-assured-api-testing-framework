import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.datafaker.Faker;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class _08_GoRestCommentTest {

    // GoRest Comment API Test
    Faker randomGenerator =new Faker();
    int commentID=0;
    RequestSpecification reqSpec;

    @BeforeClass
    public void setup(){

        baseURI="https://gorest.co.in/public/v2/comments";

        reqSpec = new RequestSpecBuilder()
                .addHeader("Authorization","Bearer 1fcbe157c72a51f2477a4f42494d197421e70f8e05028ba4e1e2b2c74f503392")
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void createComment(){
//        {
//                "post_id": 79177,
//                "name": "Omana Kaur",
//                "email": "omana_kaur@crooks.example",
//                "body": "Pariatur et consectetur."
//        }

        String fullName= randomGenerator.name().fullName();
        String email= randomGenerator.internet().emailAddress();
        String body= randomGenerator.lorem().paragraph();

        Map<String,String> newComment=new HashMap<>();
        newComment.put("post_id", "121605");
        newComment.put("name", fullName);
        newComment.put("email", email);
        newComment.put("body", body);

        commentID=
        given()
                .spec(reqSpec)
                .body(newComment)

                .when()
                .post("")

                .then()
                .log().body()
                .statusCode(201)
                .contentType(ContentType.JSON)
                .extract().path("id");
        ;
    }

    @Test(dependsOnMethods ="createComment" )
    public void getCommentById(){

        given()
                .spec(reqSpec)

                .when()
                .get(""+commentID)

                .then()
                .log().body()
                .contentType(ContentType.JSON)
                .body("id", equalTo(commentID))
        ;
    }

    @Test(dependsOnMethods ="getCommentById" )
    public void commentUpdate(){

        Map<String,String> updateComment=new HashMap<>();
        updateComment.put("name", "Kerem Said");

        given()
                .spec(reqSpec)
                .body(updateComment)

                .when()
                .put(""+commentID)

                .then()
                .log().body()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(commentID))
                .body("name", equalTo("Kerem Said"))
        ;
    }

    @Test(dependsOnMethods ="commentUpdate" )
    public void deleteComment(){

        given()
                .spec(reqSpec)

                .when()
                .delete(""+commentID)

                .then()
                .log().body()
                .statusCode(204)
        ;
    }

    @Test(dependsOnMethods ="deleteComment" )
    public void deleteCommentNegative(){

        given()
                .spec(reqSpec)

                .when()
                .delete(""+commentID)

                .then()
                .log().body()
                .statusCode(404)
        ;
    }
}
