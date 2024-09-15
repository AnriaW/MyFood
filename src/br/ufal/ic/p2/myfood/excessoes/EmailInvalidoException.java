package br.ufal.ic.p2.myfood.excessoes;

public class EmailInvalidoException extends Exception {
    public EmailInvalidoException(String emailInvalido) {
        super("Email invalido");
    }
}
