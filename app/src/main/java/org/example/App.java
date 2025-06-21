package org.example;

import io.javalin.Javalin;
import org.example.controller.TarefaController;
import org.example.controller.UsuarioController;
import java.util.Map;


public class App {
    // Criar e configurar a aplicação Javalin
    public static Javalin createApp() {
        Javalin app = Javalin.create();

        // Rota GET simples de teste, responde com o texto "Hello, Javalin!"
        app.get("/hello", ctx -> ctx.result("Hello, Javalin!"));

        // rota GET para verificar o status da aplicação
        app.get("/status", ctx -> {
            ctx.json(Map.of("status", "ok", "timestamp", java.time.Instant.now().toString()));
        });

        // rota POST que retorna o mesmo conteúdo enviado no corpo da requisição
        app.post("/echo", ctx -> {
            ctx.json(ctx.bodyAsClass(Map.class));
        });

        // Rota GET que recebe um nome como parâmetro e retorna uma saudação personalizada
        app.get("/saudacao/{nome}", ctx -> {
            ctx.json(Map.of("mensagem", "Olá, " + ctx.pathParam("nome") + "!"));
        });

        // Inicializa os controladores responsáveis por definir as rotas de tarefas e usuários
        new TarefaController(app);
        new UsuarioController(app);

        return app;
    }

    // método principal que inicia o servidor na porta 7000
    public static void main(String[] args) {
        createApp().start(7000);
    }
}
