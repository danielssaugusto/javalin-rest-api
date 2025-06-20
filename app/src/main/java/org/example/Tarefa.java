package org.example;

public class Tarefa {
    public String id;
    public String descricao;
    public boolean concluida;

    public Tarefa() {}  // Construtor vazio para Jackson

    public Tarefa(String id, String descricao, boolean concluida) {
        this.id = id;
        this.descricao = descricao;
        this.concluida = concluida;
    }
}
