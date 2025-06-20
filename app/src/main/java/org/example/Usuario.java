package org.example;

public class Usuario {
    public String nome;
    public String email;
    public int idade;

    public Usuario() {} // Construtor padrão para Jackson

    public Usuario(String nome, String email, int idade) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
    }
}
