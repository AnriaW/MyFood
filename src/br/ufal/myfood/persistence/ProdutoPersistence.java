package br.ufal.myfood.persistence;

import br.ufal.myfood.models.Produto;

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
