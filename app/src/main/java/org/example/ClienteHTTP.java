package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClienteHTTP {

    // Realizando a requisição POST para cadastrar uma nova tarefa
    public static void postTarefa() throws IOException {
        // Criação de JSON com os dados da tarefa
        String json = """
            {
              "id": "http-task-001",
              "descricao": "Tarefa via HttpURLConnection",
              "concluida": false
            }
        """;

        // URL de tarefas
        URL url = new URL("http://localhost:7000/tarefas");
        // Abrindo um HTTP com o método POST
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes());
        }

        System.out.println("POST Status: " + conn.getResponseCode());
    }

    // Método responsável por fazer uma requisição GET para buscar todas as tarefas.
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

    // Método responsável por buscar uma tarefa específica pelo ID
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

    // Método responsável por cadastrar um novo usuário via POST
    public static void postUsuario() throws IOException {
        String json = """
            {
              "nome": "Maria",
              "email": "maria@email.com",
              "idade": 25
            }
        """;

        // Definindo a URL do endpoint de usuários
        URL url = new URL("http://localhost:7000/usuarios");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);

        // Envia os dados do usuário no corpo da requisição
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.getBytes());
        }

        System.out.println("POST Usuario Status: " + conn.getResponseCode());
    }

    // Método principal que executa os métodos definidos acima
    public static void main(String[] args) throws IOException {
        // Criação dos quatro clientes HttpURLConnection
        postTarefa(); //Cria uma nova tarefa
        getTodasTarefas(); // busca todas as tarefas
        getTarefaPorId("http-task-001"); // Busca a tarefa criada pelo ID
        postUsuario(); // Cadastra um novo usuário
    }
}
