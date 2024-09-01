package br.ufal.ic.p2.myfood.persistence;

import br.ufal.ic.p2.myfood.models.Usuario;

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
