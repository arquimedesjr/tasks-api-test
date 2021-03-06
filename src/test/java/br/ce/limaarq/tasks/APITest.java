package br.ce.limaarq.tasks;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.BeforeClass;
import org.junit.Test;

public class APITest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8001/tasks-backend";
    }

    @Test
    public void deveRetornarTarefa() {
        RestAssured.given()
                .when()
                .get("/todo")
                .then()
        ;
    }

    @Test
    public void deveAdicionarTarefaComSucesso() {
        String json = "{\"task\":\"Teste via Api\",\"dueDate\":\"2020-12-30\"}";

        RestAssured.given()
                .body(json)
                .contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(201)

        ;
    }

    @Test
    public void naoDeveAdicionarTarefaInvalida() {
        String json = "{\"task\":\"Teste via Api\",\"dueDate\":\"2010-12-30\"}";

        RestAssured.given()
                .body(json)
                .contentType(ContentType.JSON)
                .when()
                .post("/todo")
                .then()
                .statusCode(400)
                .body("message", CoreMatchers.is("Due date must not be in past"))

        ;
    }


}
