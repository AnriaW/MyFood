package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.excessoes.*;

public class Facade {
    private final Gerenciamento gerenciamento = new Gerenciamento();

    public void zerarSistema() {
        gerenciamento.zerarSistema();
    }

    public void encerrarSistema() {
        gerenciamento.encerrarSistema();
    }

    // User Story 1

    public String getAtributoUsuario(int id, String atributo) throws UsuarioNaoCadastradoException {
        return gerenciamento.getAtributoUsuario(id, atributo);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws EmailInvalidoException, NomeInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, EmailJaCadastradoException {
        gerenciamento.criarUsuario(nome, email, senha, endereco);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws EmailInvalidoException, NomeInvalidoException, CPFInvalidoException, EnderecoInvalidoException, SenhaInvalidaException, EmailJaCadastradoException {
        gerenciamento.criarUsuario(nome, email, senha, endereco, cpf);
    }

    public int login(String email, String senha) throws UsuarioNaoEncontradoException {
        return gerenciamento.login(email, senha);
    }

    // User Story 2

    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String tipoCozinha) throws EmpresaJaExisteException, NomeInvalidoException, EnderecoInvalidoException, UsuarioNaoPodeCriarEmpresaException {
        return gerenciamento.criarEmpresa(tipoEmpresa, dono, nome, endereco, tipoCozinha);
    }

    public String getEmpresasDoUsuario(int dono) throws UsuarioNaoPodeCriarEmpresaException {
        return gerenciamento.getEmpresasDoUsuario(dono);
    }

    public String getAtributoEmpresa(int empresa, String atributo) throws AtributoInvalidoException, EmpresaNaoCadastradaException {
        return gerenciamento.getAtributoEmpresa(empresa, atributo);
    }

    public int getIdEmpresa(int idDono, String nome, int indice) throws  IndiceInvalidoException, NomeInvalidoException, NaoRegistradoException, UsuarioNaoPodeCriarEmpresaException {
        return gerenciamento.getIdEmpresa(idDono, nome, indice);
    }

    // User Story 3

    public int criarProduto(int id_empresa, String nome, float valor, String categoria) throws NomeInvalidoException, CategoriaInvalidaException, ValorInvalidoException {
        return gerenciamento.criarProduto(id_empresa, nome, valor, categoria);
    }

    public void editarProduto(int produto, String nome, float valor, String categoria) throws NomeInvalidoException, CategoriaInvalidaException, ValorInvalidoException {
        gerenciamento.editarProduto(produto, nome, valor, categoria);
    }

    public String getProduto(String nome, int empresa, String atributo) throws AtributoInvalidoException, NaoRegistradoException {
        return gerenciamento.getProduto(nome, empresa, atributo);
    }

    public String listarProdutos(int empresa) throws NaoRegistradoException {
        return gerenciamento.listarProdutos(empresa);
    }

    // User Story 4

    public int criarPedido(int cliente, int empresa) throws PedidoVazioException, UsuarioNaoPodeCriarEmpresaException {
        return gerenciamento.criarPedido(cliente, empresa);
    }

    public int getNumeroPedido(int cleinte, int empresa, int indice) {
        return gerenciamento.getNumeroPedido(cleinte, empresa, indice);
    }

    public void adicionarProduto(int pedido, int produto) throws NaoRegistradoException, AlterarPedidoFechadoException, PedidoNaoAbertoException {
        gerenciamento.adicionarProduto(pedido, produto);
    }

    public String getPedidos(int pedido, String atributo) throws AtributoInvalidoException, NaoRegistradoException {
        return gerenciamento.getPedidos(pedido, atributo);
    }

    public void fecharPedido(int pedido) throws NaoRegistradoException, PedidoNaoAbertoException {
        gerenciamento.fecharPedido(pedido);
    }

    public void removerProduto(int pedido, String produto) throws AtributoInvalidoException, NaoRegistradoException, AlterarPedidoFechadoException, PedidoNaoAbertoException {
        gerenciamento.removerProduto(pedido, produto);
    }

}