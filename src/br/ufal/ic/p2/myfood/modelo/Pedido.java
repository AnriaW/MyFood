package br.ufal.ic.p2.myfood.modelo;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int contador = 1; // Gera o número único do pedido
    private int numero;
    private String cliente;
    private String empresa;
    private String estado;
    private List<Produto> produtos;
    private double valor;

    public Pedido(String cliente, String empresa) {
        this.numero = contador++;
        this.cliente = cliente;
        this.empresa = empresa;
        this.estado = "Aberto"; // Estado inicial do pedido
        this.produtos = new ArrayList<>();
        this.valor = 0.0;
    }

    // Getters e Setters
    public int getNumero() { return numero; }
    public String getCliente() { return cliente; }
    public String getEmpresa() { return empresa; }
    public String getEstado() { return estado; }
    public void setEstado() { this.estado = estado; }
    public List<Produto> getProdutos() { return produtos; }
    public double getValor() { return valor; }
    public void setEstado(String estado) { this.estado = estado; }

    // TODO: tratar o preço
    // opções
    // int valor = 440/100;
    // Decimal valor = 4.40;
    public void adicionarProduto(Produto produto) throws Exception {
        if(estado.equals("Preparando")) {
            throw new Exception("Nao se pode adicionar ao pedido fechado");
        }

        produtos.add(produto);
//        valor += preco;
    }

    // TODO: tratamento de valor, mesma coisa citada no método acima
    public void removerProduto(String produto) throws Exception {
        if(estado.equals("Preparando")) {
            throw new Exception("Nao se pode remover do pedido fechado");
        }

        if (produtos.remove(produto)) {
            valor -= valor;
        }
    }

    public void fechar() {
        this.estado = "Preparando";
    }

}
