package br.ufal.ic.p2.myfood.models;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private int numero;
    private Usuario cliente;
    private Empresa empresa;
    private String estado; // aberto, preparando, fechado
    private List<Produto> produtos;

    public Pedido(int numero, Usuario cliente, Empresa empresa) {
        this.numero = numero;
        this.cliente = cliente;
        this.empresa = empresa;
        this.estado = "aberto";
        this.produtos = new ArrayList<>();
    }

    // Getters and Setters
    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public void removeProduto(Produto produto) {
        this.produtos.remove(produto);
    }

    public double getValor() {
        return produtos.stream().mapToDouble(Produto::getValor).sum();
    }
}