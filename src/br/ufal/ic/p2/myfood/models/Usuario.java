package br.ufal.ic.p2.myfood.models;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private String endereco;
    private String cpf;

    public Usuario(String nome, String email, String senha, String endereco, String cpf) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.cpf = cpf;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public static class UsuarioManager {
        private List<Usuario> usuarios;
        private static final String FILE_NAME = "usuarios.xml";
        private int nextId = 1;

        public UsuarioManager() {
            usuarios = new ArrayList<>();
            carregarUsuarios();
        }

        public Usuario criarUsuario(String nome, String email, String senha, String endereco, String tipo) {
            Usuario usuario = new Usuario(nextId++, nome, email, senha, endereco, tipo);
            usuarios.add(usuario);
            salvarUsuarios();
            return usuario;
        }

        public Usuario getUsuarioById(int id) {
            return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
        }

        public Usuario login(String email, String senha) {
            return usuarios.stream()
                    .filter(u -> u.getEmail().equals(email) && u.getSenha().equals(senha))
                    .findFirst()
                    .orElse(null);
        }

        public List<Usuario> listarUsuarios() {
            return new ArrayList<>(usuarios);
        }

        public void editarUsuario(int id, String nome, String email, String senha, String endereco, String tipo) {
            Usuario usuario = getUsuarioById(id);
            if (usuario != null) {
                usuario.setNome(nome);
                usuario.setEmail(email);
                usuario.setSenha(senha);
                usuario.setEndereco(endereco);
                usuario.setTipo(tipo);
                salvarUsuarios();
            }
        }

        public void removerUsuario(int id) {
            usuarios.removeIf(u -> u.getId() == id);
            salvarUsuarios();
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
    }
}
