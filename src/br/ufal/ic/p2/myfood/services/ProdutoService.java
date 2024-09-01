package br.ufal.ic.p2.myfood.services;

import br.ufal.ic.p2.myfood.models.Empresa;
import br.ufal.ic.p2.myfood.models.Produto;

import java.util.HashMap;
import java.util.Map;

public class ProdutoService {
    private Map<Integer, Produto> produtos = new HashMap<>();
    private Map<Empresa, Map<String, Produto>> produtosPorEmpresa = new HashMap<>();
    private int idCounter = 0;

    public Produto criarProduto(Empresa empresa, String nome, double valor, String categoria) {
        if (!produtosPorEmpresa.containsKey(empresa)) {
            produtosPorEmpresa.put(empresa, new HashMap<>());
        }
        Map<String, Produto> produtosDaEmpresa = produtosPorEmpresa.get(empresa);
        if (produtosDaEmpresa.containsKey(nome)) {
            throw new IllegalArgumentException("Já existe um produto com esse nome para essa empresa");
        }
        Produto produto = new Produto(nome, valor, categoria, empresa);
        int id = idCounter++;
        produtos.put(id, produto);
        produtosDaEmpresa.put(nome, produto);
        return produto;
    }

    public Produto getProduto(Empresa empresa, String nome) {
        return produtosPorEmpresa.getOrDefault(empresa, new HashMap<>()).get(nome);
    }

    public void editarProduto(Produto produto, String nome, double valor, String categoria) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        produto.setNome(nome);
        produto.setValor(valor);
        produto.setCategoria(categoria);
    }
}
