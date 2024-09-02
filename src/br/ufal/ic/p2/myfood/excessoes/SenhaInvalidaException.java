package br.ufal.ic.p2.myfood.excessoes;

public class SenhaInvalidaException extends Exception {
    public SenhaInvalidaException() {
        super("Senha invalida");
    }
}
