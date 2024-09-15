package br.ufal.ic.p2.myfood.excessoes;

public class EmpresaNaoCadastradaException extends Exception {
    public EmpresaNaoCadastradaException(String empresaNaoCadastrada) {
        super("Empresa nao cadastrada");
    }
}
