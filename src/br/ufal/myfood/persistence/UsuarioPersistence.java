package br.ufal.myfood.persistence;

import br.ufal.myfood.models.Usuario;

import java.util.HashMap;
import java.util.Map;

public class UsuarioPersistence {
    private Map<String, Usuario> usuarios = new HashMap<>();

    public void salvar(Usuario usuario) {
        usuarios.put(usuario.getEmail(), usuario);
    }

    public Usuario buscar(String email) {
        return usuarios.get(email);
    }
}
