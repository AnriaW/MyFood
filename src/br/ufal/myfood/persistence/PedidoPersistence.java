package br.ufal.myfood.persistence;

import br.ufal.myfood.models.Pedido;

import java.util.HashMap;
import java.util.Map;

public class PedidoPersistence {
    private Map<Integer, Pedido> pedidos = new HashMap<>();
    private int numeroCounter = 0;

    public void salvar(Pedido pedido) {
        int numero = numeroCounter++;
        pedidos.put(numero, pedido);
        pedido.setNumero(numero);
    }

    public Pedido buscar(int numero) {
        return pedidos.get(numero);
    }
}