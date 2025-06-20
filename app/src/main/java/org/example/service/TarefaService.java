package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Tarefa;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class TarefaService {
    private static final String ARQUIVO_TAREFAS = "tarefas.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private List<Tarefa> tarefas;

    public TarefaService() {
        this.tarefas = carregarTarefas();
    }

    private List<Tarefa> carregarTarefas() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(ARQUIVO_TAREFAS));
            return objectMapper.readValue(jsonData, new TypeReference<List<Tarefa>>() {});
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvarTarefas() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(Paths.get(ARQUIVO_TAREFAS).toFile(), tarefas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    public List<Tarefa> listar() {
        return tarefas;
    }

    public Optional<Tarefa> buscarPorId(String id) {
        return tarefas.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    public boolean criar(Tarefa tarefa) {
        boolean existe = tarefas.stream().anyMatch(t -> t.getId().equals(tarefa.getId()));
        if (existe) return false;

        tarefas.add(tarefa);
        salvarTarefas();
        return true;
    }
}
