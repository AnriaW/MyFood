package br.ufal.ic.p2.myfood.excessoes;

public class NomeInvalidoException extends Exception {
    public NomeInvalidoException(String nomeInvalido) {
        super("Nome invalido");
    }
}
