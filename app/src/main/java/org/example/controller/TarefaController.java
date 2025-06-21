package org.example.controller;

import io.javalin.Javalin;
import org.example.model.Tarefa;
import org.example.service.TarefaService;

public class TarefaController {
    private final TarefaService service;

    public TarefaController(Javalin app) {

        // Define as rotas relacionadas a tarefas
        this.service = new TarefaService();

        // Rota POST /tarefas, cria uma nova tarefa
        app.post("/tarefas", ctx -> {
            Tarefa tarefa = ctx.bodyAsClass(Tarefa.class);

            // Validação: o campo "id" é obrigatório
            if (tarefa.getId() == null || tarefa.getId().isEmpty()) {
                ctx.status(400).result("ID da tarefa é obrigatório");
                return;
            }
            // Tenta criar a tarefa usando o serviço
            if (service.criar(tarefa)) {
                ctx.status(201).result("Tarefa criada"); //sucesso
            } else {
                ctx.status(409).result("Tarefa com este ID já existe"); //conflito de ID
            }
        });

        // Retorna todas as tarefas cadastradas
        app.get("/tarefas", ctx -> ctx.json(service.listar()));

        // Busca uma tarefa específica pelo ID
        app.get("/tarefas/{id}", ctx -> {
            String id = ctx.pathParam("id");

            // Busca a tarefa e responde com JSON ou erro 404 se não encontrar
            service.buscarPorId(id)
                    // Retorna a tarefa como JSON se encontrar
                    .ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Tarefa não encontrada"));
        });
    }
}
