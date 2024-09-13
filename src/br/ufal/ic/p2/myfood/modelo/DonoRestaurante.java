package br.ufal.ic.p2.myfood.modelo;

public class DonoRestaurante extends Usuario {
    private String cpf = null;

    public DonoRestaurante(int proximoIdUsuario, String nome, String email, String senha, String endereco) {
        super(proximoIdUsuario++, nome, email, senha, endereco);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    @Override
    public String getTipoUsuario() {
        return "DonoRestaurante";
    }
}
