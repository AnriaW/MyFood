package br.ufal.ic.p2.myfood.excessoes;

public class CPFInvalidoException extends Exception {
    public CPFInvalidoException(String cpfInvalido) {
        super("CPF invalido");
    }
}
