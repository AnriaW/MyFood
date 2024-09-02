package br.ufal.ic.p2.myfood.modelo;

public class Cliente extends Usuario {
    public Cliente(String nome, String email, String senha, String endereco, int proximoIdUsuario) {
        super(proximoIdUsuario++, nome, email, senha, endereco);
    }

    @Override
    public String getTipoUsuario() {
        return "Cliente";
    }
}
