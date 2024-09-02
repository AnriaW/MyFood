package br.ufal.ic.p2.myfood.servico;

import br.ufal.ic.p2.myfood.modelo.Produto;
import br.ufal.ic.p2.myfood.persistencia.PersistenciaProduto;
import br.ufal.ic.p2.myfood.utils.Validador;

import java.util.HashMap;
import java.util.Map;

public class ProdutoServico {
    private Map<Integer, Produto> produtos;
    private int contadorId;

    public ProdutoServico() {
        this.produtos = PersistenciaProduto.carregarProdutos();
        this.contadorId = produtos.size() + 1;
    }

    public int criarProduto(int empresa, String nome, float valor, String categoria) {
        Validador.validarNomeProduto(nome);
        Validador.validarValor(valor);
        Validador.validarCategoria(categoria);

        Produto novoProduto = new Produto(contadorId++, nome, valor, empresa);
        produtos.put(novoProduto.getId(), novoProduto);
        PersistenciaProduto.salvarProdutos(produtos);
        return novoProduto.getId();
    }

    public void editarProduto(int id, String nome, float valor, String categoria) {
        Validador.validarNomeProduto(nome);
        Validador.validarValor(valor);
        Validador.validarCategoria(categoria);

        Produto produto = produtos.get(id);
        if (produto == null) {
            throw new RuntimeException("Produto não cadastrado.");
        }

        produto.setNome(nome);
        produto.setValor(valor);
        produto.setCategoria(categoria);
        PersistenciaProduto.salvarProdutos(produtos);
    }

    public String getProduto(String nome, int empresa, String atributo) {
        for (Produto produto : produtos.values()) {
            if (produto.getNome() == nome && produto.getEmpresa() == empresa) {
                switch (atributo) {
                    case "nome":
                        return produto.getNome();
                    case "valor":
                        return String.valueOf(produto.getValor());
                    case "categoria":
                        return produto.getCategoria();
                    default:
                        throw new RuntimeException("Atributo inválido.");
                }
            }
        }
        throw new RuntimeException("Produto não cadastrado.");
    }

    public String listarProdutos(int empresa) {
        StringBuilder sb = new StringBuilder();
        for (Produto produto : produtos.values()) {
            if (produto.getEmpresa() == empresa) {
                sb.append(produto.getNome()).append(", ");
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "";
    }
}
