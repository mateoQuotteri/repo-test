package BackTest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class DELETETest {
    private String API_KEY = "reqres-free-v1";

    @BeforeAll
    public static void setup() {
        // Configuración básica de RestAssured
        RestAssured.baseURI = "https://reqres.in";
        RestAssured.basePath = "/api";
    }

    @Test
    public void DeleteTest_02() {

        String deleteUser = "2";

        Response responseBody = given()
                .log().all()
                .header("X-API-Key", API_KEY)
                .delete("/users/" + deleteUser);

        responseBody.then().log().all();

        Assert.assertEquals(responseBody.statusCode(), 204);
        System.out.println("EL CÓDIGO DE RESPUESTA " + responseBody.statusCode() + " NOS ASEGURA QUE SE HAYA DADO DE BAJA EL USUARIO: " + deleteUser);
        System.out.println("EL SERVICIO SE TARDÓ " + responseBody.getTime() + " MILISEGUNDOS EN RESPONDER");
    }

    @Test
    public void DeleteTest_01() {
        Response responseBody = given()
                .log().all()
                .header("X-API-Key", API_KEY)
                .delete("/users/2");

        responseBody.then().log().all();  // Registrar todos los detalles de la respuesta

        Assert.assertEquals(responseBody.statusCode(), 204);
        System.out.println("EL CÓDIGO DE RESPUESTA ES: " + responseBody.statusCode() + " QUE ASEGURA QUE SE HAYA DADO DE BAJA EL USUARIO");
        System.out.println("EL SERVICIO SE TARDÓ " + responseBody.getTime() + " MILISEGUNDOS EN RESPONDER");
    }
}