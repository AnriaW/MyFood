package br.ufal.myfood.exceptions;

public class CategoriaInvalidaException extends RuntimeException {
    public CategoriaInvalidaException(String message) {
        super(message);
    }
}