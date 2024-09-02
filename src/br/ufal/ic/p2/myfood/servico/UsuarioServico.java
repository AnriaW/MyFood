package br.ufal.ic.p2.myfood.servico;

import br.ufal.ic.p2.myfood.modelo.*;
import br.ufal.ic.p2.myfood.persistencia.UsuarioDAO;
import br.ufal.ic.p2.myfood.utils.Validador;
import br.ufal.ic.p2.myfood.excessoes.*;

public class UsuarioServico {
    private UsuarioDAO usuarioDAO;

    public UsuarioServico() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws EmailJaCadastradoException, NomeInvalidoException, EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException {
        Validador.validarNome(nome);
        Validador.validarEmail(email);
        Validador.validarSenha(senha);
        Validador.validarEndereco(endereco);

        if (usuarioDAO.buscarUsuarioPorEmail(email) != null) {
            throw new EmailJaCadastradoException();
        }

        int proximoIdUsuario = 0;
        Cliente cliente = new Cliente(nome, email, senha, endereco, proximoIdUsuario);
        usuarioDAO.adicionarUsuario(cliente);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws CPFInvalidoException, EmailJaCadastradoException, NomeInvalidoException, EmailInvalidoException, SenhaInvalidaException, EnderecoInvalidoException {
        Validador.validarNome(nome);
        Validador.validarEmail(email);
        Validador.validarSenha(senha);
        Validador.validarEndereco(endereco);
        Validador.validarCpf(cpf);

        if (usuarioDAO.buscarUsuarioPorEmail(email) != null) {
            throw new EmailJaCadastradoException();
        }

        DonoRestaurante donoRestaurante = new DonoRestaurante(nome, email, senha, endereco, cpf);
        usuarioDAO.adicionarUsuario(donoRestaurante);
    }
}
