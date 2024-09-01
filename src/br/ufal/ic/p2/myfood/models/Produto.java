package br.ufal.ic.p2.myfood.models;

public class Produto {
    private String nome;
    private double valor;
    private String categoria;
    private Empresa empresa;
    private int id;

    public Produto(String nome, double valor, String categoria, Empresa empresa) {
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
        this.empresa = empresa;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}