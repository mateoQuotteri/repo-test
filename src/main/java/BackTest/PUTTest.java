package BackTest;

import com.google.gson.JsonObject;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class PUTTest {

    private String API_KEY = "reqres-free-v1";

    @Test
    public void PutTest_01() {
        JsonObject requestBody = new JsonObject();
        requestBody.addProperty("name", "SPACE");
        requestBody.addProperty("job", "CAMBIADO POR RESTASSURED");

        String jsonBody = requestBody.toString(); // Convertir a String

        given()
                .contentType("application/json")
                .header("X-API-Key", API_KEY)
                .body(jsonBody)
                .put("https://reqres.in/api/users/5")
                .then()
                .statusCode(200)
                .log().status();
    }

    // USamos  HashMap en lugar de JsonObject:
    @Test
    public void PutTest_02() {
        // Método alternativo usando HashMap en lugar de JsonObject
        java.util.Map<String, String> requestBody = new java.util.HashMap<>();
        requestBody.put("name", "SPACE");
        requestBody.put("job", "CAMBIADO POR RESTASSURED");

        Response responseBody = given()
                .contentType("application/json")
                .header("X-API-Key", API_KEY)
                .body(requestBody)
                .put("https://reqres.in/api/users/55");

        // Verificaciones
        String nombreModificado = responseBody.jsonPath().getString("name");
        String trabajoModificado = responseBody.jsonPath().getString("job");

        Assertions.assertEquals(200, responseBody.statusCode());
        Assertions.assertEquals("SPACE", nombreModificado);
        Assertions.assertEquals("CAMBIADO POR RESTASSURED", trabajoModificado);

        // Verificar la fecha de actualización
        responseBody.then().body("updatedAt", org.hamcrest.Matchers.containsString("202"));

        // Imprimir información
        System.out.println("TEST 2 - EL NOMBRE MODIFICADO ES: " + nombreModificado);
        System.out.println("TEST 2 - EL TRABAJO MODIFICADO ES: " + trabajoModificado);
        System.out.println("TEST 2 - EL CÓDIGO DE RESPUESTA ES: " + responseBody.statusCode());
        System.out.println("TEST 2 - EL SERVICIO SE TARDÓ " + responseBody.getTime() + " MILISEGUNDOS EN RESPONDER");
    }
}