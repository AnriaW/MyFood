package br.ufal.ic.p2.myfood.excessoes;

public class UsuarioNaoCadastradoException extends Exception {
    public UsuarioNaoCadastradoException(String s) {
        super("Usuario nao cadastrado.");
    }
}
