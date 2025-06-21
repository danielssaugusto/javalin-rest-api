package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Tarefa;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class TarefaService {
    // Arquivo onde as tarefas serão armazenadas
    private static final String ARQUIVO_TAREFAS = "tarefas.json";

    // Instância do Jackson para serializar e desserializar JSON
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Lista que armazena as tarefas em memória.
    private List<Tarefa> tarefas;

    // Construtor: ao criar uma instância da classe, carrega as tarefas do arquivo
    public TarefaService() {
        this.tarefas = carregarTarefas();
    }

    // Método que lê o arquivo JSON e converte para uma lista de objetos Tarefa
    private List<Tarefa> carregarTarefas() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(ARQUIVO_TAREFAS));
            return objectMapper.readValue(jsonData, new TypeReference<List<Tarefa>>() {});
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    // Método que salva a lista de tarefas no arquivo JSON
    private void salvarTarefas() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(Paths.get(ARQUIVO_TAREFAS).toFile(), tarefas);
        } catch (IOException e) {
            System.err.println("Erro ao salvar tarefas: " + e.getMessage());
        }
    }

    // Retorna todas as tarefas em memória
    public List<Tarefa> listar() {
        return tarefas;
    }

    // Busca uma tarefa específica pelo ID.
    public Optional<Tarefa> buscarPorId(String id) {
        return tarefas.stream().filter(t -> t.getId().equals(id)).findFirst();
    }

    //Cria uma nova tarefa, se o ID ainda não existir
    public boolean criar(Tarefa tarefa) {
        boolean existe = tarefas.stream().anyMatch(t -> t.getId().equals(tarefa.getId()));
        if (existe) return false;

        tarefas.add(tarefa);
        salvarTarefas();
        return true;
    }
}
