package br.ufal.myfood.exceptions;

public class EmpresaNaoEncontradaException extends RuntimeException {
    public EmpresaNaoEncontradaException(String message) {
        super(message);
    }
}
