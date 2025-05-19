package BackTest;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class POSTTest {

    private String API_KEY = "reqres-free-v1";
    @Test
    public void PostTest_01() {
        JsonObject request = new JsonObject();
        request.addProperty("name", "space");
        request.addProperty("job", "leader");

        given()
                .contentType(ContentType.JSON)
                .header("X-API-Key", API_KEY)
                .body(request.toString())
                .post("https://reqres.in/api/users")
                .then().statusCode(201)
                .log().status()
                .log().body();
    }
}
