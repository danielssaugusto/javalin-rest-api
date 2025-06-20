package org.example.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Usuario;

import java.io.IOException;
import java.nio.file.*;
import java.util.*;

public class UsuarioService {
    private static final String ARQUIVO_USUARIOS = "usuarios.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private List<Usuario> usuarios;

    public UsuarioService() {
        this.usuarios = carregarUsuarios();
    }

    private List<Usuario> carregarUsuarios() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(ARQUIVO_USUARIOS));
            return objectMapper.readValue(jsonData, new TypeReference<List<Usuario>>() {});
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    private void salvarUsuarios() {
        try {
            objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(Paths.get(ARQUIVO_USUARIOS).toFile(), usuarios);
        } catch (IOException e) {
            System.err.println("Erro ao salvar usuários: " + e.getMessage());
        }
    }

    public List<Usuario> listar() {
        return usuarios;
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarios.stream().filter(u -> u.getEmail().equalsIgnoreCase(email)).findFirst();
    }

    public boolean criar(Usuario user) {
        boolean existe = usuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()));
        if (existe) return false;

        usuarios.add(user);
        salvarUsuarios();
        return true;
    }
}
