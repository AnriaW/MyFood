package br.ufal.ic.p2.myfood.excessoes;

public class UsuarioNaoPodeCriarEmpresaException extends Exception {
    public UsuarioNaoPodeCriarEmpresaException(String s) {
        super("Usuario nao pode criar uma empresa");
    }
}
