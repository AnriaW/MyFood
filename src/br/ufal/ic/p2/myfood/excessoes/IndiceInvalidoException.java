package br.ufal.ic.p2.myfood.excessoes;

public class IndiceInvalidoException extends Exception {
    public IndiceInvalidoException(String indiceInvalido) {
        super("Indice invalido");
    }
}
