package br.ufal.ic.p2.myfood.excessoes;

public class CPFInvalidoException extends Exception {
    public CPFInvalidoException() {
        super("CPF invalido");
    }
}
