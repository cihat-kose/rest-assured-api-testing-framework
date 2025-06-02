import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
    Your endpoint:
    https://l9njuzrhf3.execute-api.eu-west-1.amazonaws.com/prod/user
    Instead of Barear Token
    Api Key -> key , value
    key: x-api-key
    value:GwMco9Tpstd5vbzBzlzW9I7hr6E1D7w2zEIrhOra
 */

public class _12_APIKeyTest {
    @Test
    public void apiKeyTest() {

        given()
                .header("x-api-key", "GwMco9Tpstd5vbzBzlzW9I7hr6E1D7w2zEIrhOra")

                .when()
                .get("https://l9njuzrhf3.execute-api.eu-west-1.amazonaws.com/prod/user")

                .then()
                .log().body()
        ;
    }

    /**
        Google developer weather api key
        https://developers.google.com/maps/documentation/geocoding/get-api-key
     */


    /**
        Postmande query parameter istiyorsa Api key seçildikten sonra Eklenme türü seçilebilir.
        Header veya query parameter hali hangisi isteniyorsa ona göre yapılıyor
        --------------------------------------------------------------------------------------------------
        If Postman requires a query parameter, the Addition type can be selected after selecting the Api key.
        Header or query parameter status is made according to whichever is desired.
     */

}
