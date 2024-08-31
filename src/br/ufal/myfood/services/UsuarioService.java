package br.ufal.myfood.services;

import br.ufal.myfood.models.Usuario;

import java.util.HashMap;
import java.util.Map;

public class UsuarioService {
    private Map<String, Usuario> usuarios = new HashMap<>();

    public Usuario criarUsuario(String nome, String email, String senha, String endereco, String cpf) {
        Usuario usuario = new Usuario(nome, email, senha, endereco, cpf);
        usuarios.put(email, usuario);
        return usuario;
    }

    public Usuario login(String email, String senha) {
        Usuario usuario = usuarios.get(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }
}