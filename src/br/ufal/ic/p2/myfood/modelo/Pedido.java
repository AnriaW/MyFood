package br.ufal.ic.p2.myfood.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contador = 1; // Gera o número único do pedido
    private int numero;
    private String cliente;
    private String empresa;
    private String estado;
    private List<String> produtos;
    private double valor;

    public Pedido(Usuario cliente, Empresa empresa) {
        this.numero = contador++;
        this.cliente = cliente;
        this.empresa = empresa;
        this.estado = "aberto"; // Estado inicial do pedido
        this.produtos = new ArrayList<>();
        this.valor = 0.0;
    }

    // Getters e Setters
    public int getNumero() { return numero; }
    public String getCliente() { return cliente; }
    public String getEmpresa() { return empresa; }
    public String getEstado() { return estado; }
    public List<String> getProdutos() { return produtos; }
    public double getValor() { return valor; }

    public void setEstado(String estado) { this.estado = estado; }

    public void adicionarProduto(String produto, double preco) {
        produtos.add(produto);
        valor += preco;
    }

    public void removerProduto(String produto) {
        if (produtos.remove(produto)) {
            valor -= valor;
        }
    }
}
