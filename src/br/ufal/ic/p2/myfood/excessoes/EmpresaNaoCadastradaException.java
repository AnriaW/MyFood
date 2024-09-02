package br.ufal.ic.p2.myfood.excessoes;

public class EmpresaNaoCadastradaException extends Exception {
    public EmpresaNaoCadastradaException() {
        super("Empresa nao cadastrada");
    }
}
