package br.ufal.ic.p2.myfood.persistencia;

import br.ufal.ic.p2.myfood.modelo.Usuario;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PersistenciaUsuario {

    private static final String CAMINHO_XML = "src/br/ufal/ic/p2/myfood/xml/usuarios.xml";
    private Map<Integer, Usuario> usuarios = new HashMap<>();

    public PersistenciaUsuario() {
        carregar();
    }

    public void adicionarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        salvar();
    }

    public Usuario obterUsuario(int id) {
        return usuarios.get(id);
    }

    public void removerUsuario(int id) {
        usuarios.remove(id);
        salvar();
    }

    public void salvar() {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(CAMINHO_XML))) {
            encoder.writeObject(usuarios);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void carregar() {
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(CAMINHO_XML))) {
            usuarios = (Map<Integer, Usuario>) decoder.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
