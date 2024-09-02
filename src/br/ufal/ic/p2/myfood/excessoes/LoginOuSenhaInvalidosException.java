package br.ufal.ic.p2.myfood.excessoes;

public class LoginOuSenhaInvalidosException extends Exception {
    public LoginOuSenhaInvalidosException() {
        super("Login ou senha invalidos");
    }
}
