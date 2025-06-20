package org.example.controller;

import io.javalin.Javalin;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

public class UsuarioController {
    private final UsuarioService service;

    public UsuarioController(Javalin app) {
        this.service = new UsuarioService();

        app.post("/usuarios", ctx -> {
            Usuario user = ctx.bodyAsClass(Usuario.class);
            if (user.getEmail() == null || user.getEmail().isEmpty()) {
                ctx.status(400).result("Email do usuário é obrigatório");
                return;
            }

            if (service.criar(user)) {
                ctx.status(201).result("Usuário criado");
            } else {
                ctx.status(409).result("Usuário com este email já existe");
            }
        });

        app.get("/usuarios", ctx -> ctx.json(service.listar()));

        app.get("/usuarios/{email}", ctx -> {
            String email = ctx.pathParam("email");
            service.buscarPorEmail(email)
                    .ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Usuário não encontrado"));
        });
    }
}
