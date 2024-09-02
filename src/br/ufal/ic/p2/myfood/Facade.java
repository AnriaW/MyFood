package br.ufal.ic.p2.myfood;

import br.ufal.ic.p2.myfood.Gerenciamento;

public class Facade {
    private Gerenciamento gerenciamento;

    public Facade() {
        this.gerenciamento = new Gerenciamento();
    }

    public void zerarSistema() {
        gerenciamento.zerarSistema();
    }

    public void encerrarSistema() {
        gerenciamento.encerrarSistema();
    }

    public int criarProduto(int empresaId, String nome, float valor, String categoria) throws Exception {
        return gerenciamento.criarProduto(empresaId, nome, valor, categoria);
    }

    public void editarProduto(int id, String nome, float valor, String categoria) throws Exception {
        try {
            gerenciamento.editarProduto(id, nome, valor, categoria);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getProduto(String nome, int empresaId, String atributo) {
        return gerenciamento.getProduto(nome, empresaId, atributo);
    }

    public String listarProdutos(int empresaId) throws Exception {
        return gerenciamento.listarProdutos(empresaId);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco) throws Exception {
        gerenciamento.criarUsuario(nome, email, senha, endereco);
    }

    public void criarUsuario(String nome, String email, String senha, String endereco, String cpf) throws Exception {
        gerenciamento.criarUsuario(nome, email, senha, endereco, cpf);
    }

    public int login(String email, String senha) throws Exception {
        return gerenciamento.login(email, senha);
    }

    public String getAtributoUsuario(int id, String atributo) throws Exception {
        return gerenciamento.getAtributoUsuario(id, atributo);
    }

    public void criarEmpresa(String nome, String endereco) throws Exception {
        gerenciamento.criarEmpresa(nome, endereco);
    }

    public String getAtributoEmpresa(int id, String atributo) {
        return gerenciamento.getAtributoEmpresa(id, atributo);
    }

    public void criarPedido(int clienteId, int empresaId) {
        gerenciamento.criarPedido(clienteId, empresaId);
    }

    public void adicionarProduto(int pedidoNumero, int produtoId) {
        gerenciamento.adicionarProduto(pedidoNumero, produtoId);
    }

    public String getPedidos(int numero, String atributo) {
        return gerenciamento.getPedidos(numero, atributo);
    }

    public void fecharPedido(int numero) throws Exception {
        gerenciamento.fecharPedido(numero);
    }

    public void removerProduto(int pedidoNumero, String produtoNome) throws Exception {
        gerenciamento.removerProduto(pedidoNumero, produtoNome);
    }
}
