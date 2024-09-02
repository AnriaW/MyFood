package br.ufal.ic.p2.myfood.servico;

import br.ufal.ic.p2.myfood.modelo.Empresa;
import br.ufal.ic.p2.myfood.modelo.Pedido;
import br.ufal.ic.p2.myfood.modelo.Usuario;
import br.ufal.ic.p2.myfood.persistencia.PersistenciaPedido;

import java.util.List;

public class PedidoServico {
    private PersistenciaPedido persistenciaPedido;

    public PedidoServico() {
        this.persistenciaPedido = new PersistenciaPedido();
    }

    public Pedido criarPedido(Usuario cliente, Empresa empresa) {
        Pedido pedido = new Pedido(cliente, empresa);
        persistenciaPedido.salvarPedido(pedido);
        return pedido;
    }

    public Pedido buscarPedido(int numero) {
        return persistenciaPedido.buscarPedido(numero);
    }

    public void adicionarProdutoAoPedido(int numero, String produto, double preco) {
        Pedido pedido = buscarPedido(numero);
        if (pedido != null && "aberto".equals(pedido.getEstado())) {
            pedido.adicionarProduto(produto, preco);
            persistenciaPedido.atualizarPedido(pedido);
        }
    }

    public void fecharPedido(int numero) {
        Pedido pedido = buscarPedido(numero);
        if (pedido != null) {
            pedido.setEstado("preparando");
            persistenciaPedido.atualizarPedido(pedido);
        }
    }

    public void removerProdutoDoPedido(int numero, String produto, double preco) {
        Pedido pedido = buscarPedido(numero);
        if (pedido != null && "aberto".equals(pedido.getEstado())) {
            pedido.removerProduto(produto);
            persistenciaPedido.atualizarPedido(pedido);
        }
    }

    public List<Pedido> listarPedidos() {
        return persistenciaPedido.listarPedidos();
    }
}
