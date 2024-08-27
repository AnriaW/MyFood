package main.model;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {
    private List<Usuario> usuarios;
    private static final String FILE_NAME = "usuarios.xml";
    private int nextId = 1;

    public UsuarioManager() {
        usuarios = new ArrayList<>();
        carregarUsuarios();
    }

    public void zerarSistema() {
        usuarios.clear();
        salvarUsuarios();
        nextId = 1;
    }

    public Usuario criarUsuario(String nome, String email, String senha, String endereco) throws Exception {
        validarDadosUsuario(nome, email, senha, endereco, null);

        if (emailJaCadastrado(email)) {
            throw new Exception("Conta com esse email ja existe");
        }

        Usuario usuario = new Usuario(nextId++, nome, email, senha, endereco, "Cliente");
        usuarios.add(usuario);
        salvarUsuarios();
        return usuario;
    }

    public Usuario criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        validarDadosUsuario(nome, email, senha, endereco, cpf);

        if (emailJaCadastrado(email)) {
            throw new Exception("Conta com esse email ja existe");
        }

        Usuario usuario = new Usuario(nextId++, nome, email, senha, endereco, cpf, "Dono de Restaurante");
        usuarios.add(usuario);
        salvarUsuarios();
        return usuario;
    }

    public int login(String email, String senha) throws Exception {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email) && u.getSenha().equals(senha)) {
                return u.getId();
            }
        }
        throw new Exception("Login ou senha invalidos");
    }

    public String getAtributoUsuario(int id, String atributo) throws Exception {
        Usuario usuario = getUsuarioById(id);
        if (usuario == null) {
            throw new Exception("Usuario nao cadastrado.");
        }

        switch (atributo) {
            case "nome":
                return usuario.getNome();
            case "email":
                return usuario.getEmail();
            case "endereco":
                return usuario.getEndereco();
            case "cpf":
                return usuario.getCpf();
            default:
                throw new Exception("Atributo invalido");
        }
    }

    private Usuario getUsuarioById(int id) {
        return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    private boolean emailJaCadastrado(String email) {
        return usuarios.stream().anyMatch(u -> u.getEmail().equals(email));
    }

    private void validarDadosUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        if (nome == null || nome.isEmpty()) throw new Exception("Nome invalido");
        if (email == null || email.isEmpty() || !email.contains("@")) throw new Exception("Email invalido");
        if (senha == null || senha.isEmpty()) throw new Exception("Senha invalido");
        if (endereco == null || endereco.isEmpty()) throw new Exception("Endereco invalido");
        if (cpf != null && (cpf.length() != 14 || !cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}"))) {
            throw new Exception("CPF invalido");
        }
    }

    private void salvarUsuarios() {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(FILE_NAME)))) {
            encoder.writeObject(usuarios);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void carregarUsuarios() {
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(FILE_NAME)))) {
            usuarios = (List<Usuario>) decoder.readObject();
            nextId = usuarios.size() > 0 ? usuarios.get(usuarios.size() - 1).getId() + 1 : 1;
        } catch (FileNotFoundException e) {
            usuarios = new ArrayList<>();
        }
    }

    public void encerrarSistema() {
        salvarUsuarios();
    }
}
