package org.example;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testHelloEndpoint() {
        Javalin app = App.createApp();

        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/hello");
            assertEquals(200, response.code());
            assertEquals("Hello, Javalin!", response.body().string());
        });
    }

    @Test
    public void createTaskTest() {
        Javalin app = App.createApp();

        String json = """
            {
                "id": "tarefa-test-001",
                "descricao": "Criar tarefa de teste",
                "concluida": false
            }
            """;

        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/tarefas", json);
            assertEquals(201, response.code());
        });
    }

    @Test
    public void findTaskByIdTest() {
        Javalin app = App.createApp();

        String json = """
            {
                "id": "tarefa-test-002",
                "descricao": "Buscar tarefa por ID",
                "concluida": true
            }
            """;

        JavalinTest.test(app, (server, client) -> {
            client.post("/tarefas", json);
            var response = client.get("/tarefas/tarefa-test-002");

            assertEquals(200, response.code());
            assertTrue(response.body().string().contains("Buscar tarefa por ID"));
        });
    }

    @Test
    public void listTasksTest() {
        Javalin app = App.createApp();

        String json1 = """
            {
                "id": "tarefa-list-001",
                "descricao": "Tarefa 1",
                "concluida": false
            }
            """;
        String json2 = """
            {
                "id": "tarefa-list-002",
                "descricao": "Tarefa 2",
                "concluida": true
            }
            """;

        JavalinTest.test(app, (server, client) -> {
            client.post("/tarefas", json1);
            client.post("/tarefas", json2);

            var response = client.get("/tarefas");
            String responseBody = response.body().string();

            assertEquals(200, response.code());
            assertTrue(responseBody.contains("Tarefa 1"));
            assertTrue(responseBody.contains("Tarefa 2"));
        });
    }
}
