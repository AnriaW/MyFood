package br.ufal.ic.p2.myfood.modelo;

import java.io.Serializable;

public class Usuario implements Serializable{
    private static int idCounterUsuario = 1;
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String endereco;


    public Usuario(){
        //Para serializar (lembrar disso no futuro)
    }

    public Usuario(String nome, String email, String senha, String endereco) {
        this.id = idCounterUsuario++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.endereco = endereco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public void setEndereco() {
        this.endereco = endereco;
    }

    // Metodo para obter qual o tipo de atributo
    public String getAtributo(String atributo) {
        if (atributo.equals("id")) {
            return String.valueOf(id);
        } else if (atributo.equals("nome")) {
            return nome;
        } else if (atributo.equals("email")) {
            return email;
        } else if (atributo.equals("senha")) {
            return senha;
        } else if (atributo.equals("endereco")) {
            return endereco;
        }
        return null; // Adicionei um retorno padrão caso o atributo não seja encontrado
    }

    @Override
    public String toString(){
        return "Usuario [id="+id+", nome="+nome+", email="+email+", senha="+senha+", endereco="+endereco+"]";
    }
}
