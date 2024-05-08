import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class APITest {
    @Test
    public void firstTestScenario() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        // Bu satır, RestAssured kütüphanesini kullanarak belirtilen URL’ye bir HTTP GET isteği gönderir
        // ve bu isteğin cevabını temsil eden bir Response nesnesini döndürür.

        Assert.assertEquals(response.getStatusCode(), 200);
        // response.getStatusCode(): Bu, HTTP cevabının durum kodunu alır.
        // Örneğin, 200 OK, 404 Not Found gibi durum kodları olabilir.

        // Assert.assertEquals(response.getStatusCode(), 200):
        // Bu satırda, alınan cevabın durum kodunun 200 olduğunu doğrulamak için bir
        // assert (doğrulama) ifadesi kullanılır.Eğer durum kodu 200 değilse, test başarısız olacaktır.
    }

    @Test
    public void firstBddTestScenario() {

        given()
                // Bu, RestAssured’in BDD tarzında kullanımına aittir.
                // Bu bölüm, test senaryosunun başlangıç koşullarını (given) belirtir.

                .when()
                // Bu bölüm, test senaryosundaki eylemi (when) belirtir.
                // Yani, bir HTTP GET isteği yapılır.

                .get("https://reqres.in/api/users?page=2")
                // Bu satır, belirtilen URL’ye bir HTTP GET isteği yapar.

                .then()
                // Bu bölüm, test senaryosunun beklenen sonuçlarını (then) belirtir.

                .statusCode(200)
                // Bu satır, HTTP cevabının durum kodunun 200 (OK) olması gerektiğini belirtir.

                .log().body();
        // Response gövdesini loglamak için kullanılır.
        // Bu sayede gerçekleşen HTTP cevabını daha ayrıntılı bir şekilde inceleyebilirsiniz.
        // Bu bölümü geliştirme ve hata ayıklama aşamasında kullanmak faydalı olabilir.
        // RestAssured kütüphanesinde body dışında headers, cookie, all gibi
        // methodlarda bulunmaktadır ihtiyacınıza göre bu seçeneklerden de faydalanabilirsiniz.
    }

    @Test
    public void createUserTest() {
        baseURI = "https://reqres.in/api";
        // Bu satır, testin yapılacağı API’nin temel URI’sini belirler.

        HashMap<String, Object> data = new HashMap<>();
        // Bu satır, atılacak request için body data oluşturmak amacıyla kullanıldı,

        data.put("name", "Kaan");
        data.put("job", "Tester");

        given()
                .body(data)

                .when()
                .post("/users")
                // Bu satır, belirtilen URL’e bir HTTP POST isteği yapar.

                .then()
                .statusCode(201)
                .log().body();
    }
}


