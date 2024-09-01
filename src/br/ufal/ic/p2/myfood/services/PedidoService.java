package br.ufal.ic.p2.myfood.services;

import br.ufal.ic.p2.myfood.models.Empresa;
import br.ufal.ic.p2.myfood.models.Pedido;
import br.ufal.ic.p2.myfood.models.Produto;
import br.ufal.ic.p2.myfood.models.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PedidoService {
    private Map<Integer, Pedido> pedidos = new HashMap<>();
    private Map<Usuario, Map<Empresa, Pedido>> pedidosPorCliente = new HashMap<>();
    private int numeroPedidoCounter = 0;

    public Pedido criarPedido(Usuario cliente, Empresa empresa) {
        if (pedidosPorCliente.containsKey(cliente) && pedidosPorCliente.get(cliente).containsKey(empresa)) {
            Pedido pedidoExistente = pedidosPorCliente.get(cliente).get(empresa);
            if ("aberto".equals(pedidoExistente.getEstado())) {
                throw new IllegalStateException("Não é permitido ter dois pedidos em aberto para a mesma empresa");
            }
        }
        Pedido pedido = new Pedido(numeroPedidoCounter++, cliente, empresa);
        pedidos.put(pedido.getNumero(), pedido);
        pedidosPorCliente.computeIfAbsent(cliente, k -> new HashMap<>()).put(empresa, pedido);
        return pedido;
    }

    public Pedido getPedido(int numero) {
        return pedidos.get(numero);
    }

    public void adicionarProduto(int numero, Produto produto) {
        Pedido pedido = pedidos.get(numero);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        if (!"aberto".equals(pedido.getEstado())) {
            throw new IllegalStateException("Não é possível adicionar produtos a um pedido fechado");
        }
        if (!produto.getEmpresa().equals(pedido.getEmpresa())) {
            throw new IllegalArgumentException("O produto não pertence a essa empresa");
        }
        pedido.addProduto(produto);
    }

    public void removerProduto(int numero, String nomeProduto) {
        Pedido pedido = pedidos.get(numero);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        if (!"aberto".equals(pedido.getEstado())) {
            throw new IllegalStateException("Não é possível remover produtos de um pedido fechado");
        }
        Produto produto = pedido.getProdutos().stream().filter(p -> p.getNome().equals(nomeProduto)).findFirst().orElse(null);
        if (produto != null) {
            pedido.removeProduto(produto);
        }
    }

    public void fecharPedido(int numero) {
        Pedido pedido = pedidos.get(numero);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        pedido.setEstado("fechado");
    }
}