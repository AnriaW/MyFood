package br.ufal.ic.p2.myfood.excessoes;

public class EmpresaJaExisteException extends Exception {
    public EmpresaJaExisteException() {
        super("Empresa com esse nome ja existe");
    }
}
