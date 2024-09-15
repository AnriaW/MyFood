package br.ufal.ic.p2.myfood.excessoes;

public class ProdutoJaExisteException extends RuntimeException {
    public ProdutoJaExisteException(String message) {
        super(message);
    }
}
