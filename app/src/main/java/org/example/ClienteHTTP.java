package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClienteHTTP {

    public static void postTarefa() throws IOException {
        String json = """
            {
              "id": "http-task-001",
              "descricao": "Tarefa via HttpURLConnection",
              "concluida": false
            }
        """;

        URL url = new URL("http://localhost:7000/tarefas");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes());
        }

        System.out.println("POST Status: " + conn.getResponseCode());
    }

    public static void getTodasTarefas() throws IOException {
        URL url = new URL("http://localhost:7000/tarefas");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            System.out.println("GET Todas Tarefas:");
            reader.lines().forEach(System.out::println);
        }
    }

    public static void getTarefaPorId(String id) throws IOException {
        URL url = new URL("http://localhost:7000/tarefas/" + id);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(conn.getInputStream()))) {
            System.out.println("GET Tarefa por ID:");
            reader.lines().forEach(System.out::println);
        }
    }

    public static void postUsuario() throws IOException {
        String json = """
            {
              "nome": "Maria",
              "email": "maria@email.com",
              "idade": 25
            }
        """;

        URL url = new URL("http://localhost:7000/usuarios");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes());
        }

        System.out.println("POST Usuario Status: " + conn.getResponseCode());
    }

    public static void main(String[] args) throws IOException {
        postTarefa();
        getTodasTarefas();
        getTarefaPorId("http-task-001");
        postUsuario();
    }
}
