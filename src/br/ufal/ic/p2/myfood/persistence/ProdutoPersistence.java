package br.ufal.ic.p2.myfood.persistence;

import br.ufal.ic.p2.myfood.models.Produto;

import java.util.HashMap;
import java.util.Map;

public class ProdutoPersistence {
    private Map<Integer, Produto> produtos = new HashMap<>();
    private int idCounter = 0;

    public void salvar(Produto produto) {
        int id = idCounter++;
        produtos.put(id, produto);
        produto.setId(id);
    }

    public Produto buscar(int id) {
        return produtos.get(id);
    }
}
