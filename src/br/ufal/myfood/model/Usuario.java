package main.model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;
    private String cpf; // apenas para Dono de Restaurante
    private String tipo; // "Cliente" ou "Dono de Restaurante"

    public Usuario(int id, String nome, String email, String senha, String endereco, String tipo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
        this.tipo = tipo;
        this.cpf = null; // Será nulo se o tipo for "Cliente"
    }

    public Usuario(int id, String nome, String email, String senha, String endereco, String cpf, String tipo) {
        this(id, nome, email, senha, endereco, tipo);
        this.cpf = cpf; // Será preenchido se o tipo for "Dono de Restaurante"
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTipo() {
        return tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", endereco='" + endereco + '\'' +
                ", tipo='" + tipo + '\'' +
                ", cpf='" + cpf + '\'' +
                '}';
    }
}