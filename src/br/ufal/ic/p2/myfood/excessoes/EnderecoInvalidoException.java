package br.ufal.ic.p2.myfood.excessoes;

public class EnderecoInvalidoException extends Exception {
    public EnderecoInvalidoException(String enderecoInvalido) {
        super("Endereco invalido");
    }
}
