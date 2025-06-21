package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Usuario;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class UsuarioService {

    // Arquivo onde os dados dos usuários serão aramazenados
    private static final String ARQUIVO_USUARIOS = "usuarios.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private List<Usuario> usuarios;

    // Construtor: ao instanciar a classe, carrega os usuários do arquivo
    public UsuarioService() {
        this.usuarios = carregarUsuarios();
    }

    // Método privado que tenta ler o arquivo de usuários e converter para uma lista de objetos Usuario
    private List<Usuario> carregarUsuarios() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(ARQUIVO_USUARIOS));
            return objectMapper.readValue(jsonData, new TypeReference<List<Usuario>>() {});
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    // Método privado que salva a lista de usuários no arquivo JSON
    private void salvarUsuarios() {
        try {

            // Escreve os dados formatados
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(Paths.get(ARQUIVO_USUARIOS).toFile(), usuarios);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    // Retorna a lista completa de usuários

    public List<Usuario> listar() {
        return usuarios;
    }

    // Busca um usuário pelo e-mail
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarios.stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    // Cria um novo usuário, caso o e-mail ainda não esteja cadastrado
    public boolean criar(Usuario user) {
        // Verifica se já existe algum usuário com o mesmo e-mail
        boolean existe = usuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()));
        if (existe) return false;

        usuarios.add(user);
        salvarUsuarios();
        return true;
    }
}
