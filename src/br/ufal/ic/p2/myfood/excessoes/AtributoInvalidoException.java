package br.ufal.ic.p2.myfood.excessoes;

public class AtributoInvalidoException extends Exception {
    public AtributoInvalidoException(String produtoInvalido) {
        super("Atributo invalido");
    }
}
