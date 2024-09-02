package br.ufal.ic.p2.myfood.persistencia;

import br.ufal.ic.p2.myfood.modelo.Usuario;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private static final String FILE_NAME = "src/br/ufal/ic/p2/myfood/xml/usuarios.xml";
    private List<Usuario> usuarios;

    public UsuarioDAO() {
        this.usuarios = carregarUsuarios();
    }

    public void salvarUsuarios() {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(FILE_NAME)))) {
            encoder.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> carregarUsuarios() {
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FILE_NAME)))) {
            return (List<Usuario>) decoder.readObject();
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void adicionarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
        salvarUsuarios();
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarios.stream().filter(u -> u.getEmail().equals(email)).findFirst().orElse(null);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }
}
