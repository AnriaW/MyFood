package br.ufal.ic.p2.myfood.excessoes;

public class EmailJaCadastradoException extends Exception {
    public EmailJaCadastradoException() {
        super("Conta com esse email ja existe");
    }
}
