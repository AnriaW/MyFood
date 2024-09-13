package br.ufal.ic.p2.myfood.modelo;

public abstract class Usuario {
    private static int idCounterUsuario = 0; // Para gerar IDs únicos automaticamente
    private static int id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;
    private static final String PrefixoUsuario = "id";

    public Usuario(int i, String nome, String email, String senha, String endereco) {
        this.id = Integer.parseInt(PrefixoUsuario+(++idCounterUsuario));
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public static int getId() {
        return id;
    }

    public String getNome() {
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
