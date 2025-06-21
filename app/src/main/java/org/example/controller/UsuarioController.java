package org.example.controller;

import io.javalin.Javalin;
import org.example.model.Usuario;
import org.example.service.UsuarioService;

public class UsuarioController {
    // Instância do serviço responsável pelas regras de negócio
    private final UsuarioService service;


    // Construtor do controller: define as rotas HTTP relacionadas a "usuários"
    public UsuarioController(Javalin app) {
        this.service = new UsuarioService();

        // Rota POST /usuarios, Cria um novo usuário
        app.post("/usuarios", ctx -> {
            Usuario user = ctx.bodyAsClass(Usuario.class);

            // Validação: o campo "e-mail" é obrigatório
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

        // Rota GET /usuarios, retorna todos os usuários
        app.get("/usuarios", ctx -> ctx.json(service.listar()));

        // Rota GET /usuarios/{email}, busca um usuário pelo seu email
        app.get("/usuarios/{email}", ctx -> {
            String email = ctx.pathParam("email");

            // Busca o usuário e responde com JSON ou erro 404
            service.buscarPorEmail(email)
                    .ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Usuário não encontrado"));
        });
    }
}
