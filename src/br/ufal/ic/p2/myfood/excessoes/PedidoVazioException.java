package br.ufal.ic.p2.myfood.excessoes;

public class PedidoVazioException extends RuntimeException {
    public PedidoVazioException(String PedidoEstaVazio) {
        super("Pedido Vazio");
    }
}
