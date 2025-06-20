package org.example;

import io.javalin.Javalin;
import org.example.controller.TarefaController;
import org.example.controller.UsuarioController;
import java.util.Map;


public class App {
    public static Javalin createApp() {
        Javalin app = Javalin.create();

        app.get("/hello", ctx -> ctx.result("Hello, Javalin!"));

        app.get("/status", ctx -> {
            ctx.json(Map.of("status", "ok", "timestamp", java.time.Instant.now().toString()));
        });

        app.post("/echo", ctx -> {
            ctx.json(ctx.bodyAsClass(Map.class));
        });

        app.get("/saudacao/{nome}", ctx -> {
            ctx.json(Map.of("mensagem", "Olá, " + ctx.pathParam("nome") + "!"));
        });

        new TarefaController(app);
        new UsuarioController(app);

        return app;
    }

    public static void main(String[] args) {
        createApp().start(7000);
    }
}
