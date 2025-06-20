package org.example.controller;

import io.javalin.Javalin;
import org.example.model.Tarefa;
import org.example.service.TarefaService;

public class TarefaController {
    private final TarefaService service;

    public TarefaController(Javalin app) {
        this.service = new TarefaService();

        app.post("/tarefas", ctx -> {
            Tarefa tarefa = ctx.bodyAsClass(Tarefa.class);
            if (tarefa.getId() == null || tarefa.getId().isEmpty()) {
                ctx.status(400).result("ID da tarefa é obrigatório");
                return;
            }

            if (service.criar(tarefa)) {
                ctx.status(201).result("Tarefa criada");
            } else {
                ctx.status(409).result("Tarefa com este ID já existe");
            }
        });

        app.get("/tarefas", ctx -> ctx.json(service.listar()));

        app.get("/tarefas/{id}", ctx -> {
            String id = ctx.pathParam("id");
            service.buscarPorId(id)
                    .ifPresentOrElse(ctx::json, () -> ctx.status(404).result("Tarefa não encontrada"));
        });
    }
}
