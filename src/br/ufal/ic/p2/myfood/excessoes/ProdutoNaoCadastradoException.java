package br.ufal.ic.p2.myfood.excessoes;

public class ProdutoNaoCadastradoException extends RuntimeException {
    public ProdutoNaoCadastradoException(String message) {
        super(message);
    }
}
