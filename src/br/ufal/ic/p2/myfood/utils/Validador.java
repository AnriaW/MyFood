package br.ufal.ic.p2.myfood.utils;

import br.ufal.ic.p2.myfood.excessoes.*;

public class Validador {
    public static void validarNome(String nome) throws NomeInvalidoException {
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException();
        }
    }

    public static void validarEmail(String email) throws EmailInvalidoException {
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new EmailInvalidoException();
        }
    }

    public static void validarSenha(String senha) throws SenhaInvalidaException {
        if (senha == null || senha.trim().isEmpty()) {
            throw new SenhaInvalidaException();
        }
    }

    public static void validarEndereco(String endereco) throws EnderecoInvalidoException {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new EnderecoInvalidoException();
        }
    }

    public static void validarCpf(String cpf) throws CPFInvalidoException {
        if (cpf == null || cpf.trim().isEmpty() || cpf.length() != 14) { // Considerando "000.000.000-00"
            throw new CPFInvalidoException();
        }
    }

    public static void validarNomeEmpresa(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Nome inválido");
        }
        if (nome.length() > 100) {
            throw new RuntimeException("Nome muito longo");
        }
    }

    public static void validarEnderecoEmpresa(String endereco) {
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new RuntimeException("Endereço inválido");
        }
        if (endereco.length() > 100) {
            throw new RuntimeException("Endereço muito longo");
        }
    }

    public static void validarTipoCozinha(String tipoCozinha) {
        if (tipoCozinha == null || tipoCozinha.trim().isEmpty()) {
            throw new RuntimeException("Tipo de cozinha inválido");
        }
        if (tipoCozinha.length() > 50) {
            throw new RuntimeException("Tipo de cozinha muito longo");
        }
    }

    public static void validarNomeProduto(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Nome inválido.");
        }
    }

    public static void validarValor(float valor) {
        if (valor <= 0) {
            throw new RuntimeException("Valor inválido.");
        }
    }

    public static void validarCategoria(String categoria) {
        if (categoria == null || categoria.trim().isEmpty()) {
            throw new RuntimeException("Categoria inválida.");
        }
    }
}
