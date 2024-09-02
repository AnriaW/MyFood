package br.ufal.ic.p2.myfood.modelo;

public abstract class Usuario {
    private static int idCounter = 0; // Para gerar IDs únicos automaticamente
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;

    public Usuario(int i, String nome, String email, String senha, String endereco) {
        this.id = ++idCounter;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public String getNome(String nome) {
        return this.nome;
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

    // Método abstrato para obter o tipo do usuário
    public abstract String getTipoUsuario();
}
