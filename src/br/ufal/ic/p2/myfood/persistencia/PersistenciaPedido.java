package br.ufal.ic.p2.myfood.persistencia;

import br.ufal.ic.p2.myfood.modelo.Pedido;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaPedido {
    private final String caminhoArquivo = "src/br/ufal/ic/p2/myfood/xml/pedidos.xml";

    public void salvarPedido(Pedido pedido) {
        List<Pedido> pedidos = listarPedidos();
        pedidos.add(pedido);
        salvarPedidosNoArquivo(pedidos);
    }

    public Pedido buscarPedido(int numero) {
        List<Pedido> pedidos = listarPedidos();
        for (Pedido pedido : pedidos) {
            if (pedido.getNumero() == numero) {
                return pedido;
            }
        }
        return null;
    }

    public void atualizarPedido(Pedido pedidoAtualizado) {
        List<Pedido> pedidos = listarPedidos();
        for (int i = 0; i < pedidos.size(); i++) {
            if (pedidos.get(i).getNumero() == pedidoAtualizado.getNumero()) {
                pedidos.set(i, pedidoAtualizado);
                salvarPedidosNoArquivo(pedidos);
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public List<Pedido> listarPedidos() {
        try (XMLDecoder decoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(caminhoArquivo)))) {
            return (List<Pedido>) decoder.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        }
    }

    private void salvarPedidosNoArquivo(List<Pedido> pedidos) {
        try (XMLEncoder encoder = new XMLEncoder(new BufferedOutputStream(new FileOutputStream(caminhoArquivo)))) {
            encoder.writeObject(pedidos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}