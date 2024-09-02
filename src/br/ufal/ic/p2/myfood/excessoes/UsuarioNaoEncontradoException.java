package br.ufal.ic.p2.myfood.excessoes;

public class UsuarioNaoEncontradoException extends Exception {
    public UsuarioNaoEncontradoException() {
        super("Usuario nao Encontrado");
    }
}