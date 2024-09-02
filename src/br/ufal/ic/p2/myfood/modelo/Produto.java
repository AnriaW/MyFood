package br.ufal.ic.p2.myfood.modelo;

public class Produto {
    private int id;
    private Empresa empresa;
    private String nome;
    private float valor;
    private String categoria;



    public Produto(int id, String nome, double preco, Empresa empresa) {
        this.id = id;
        this.empresa = empresa;
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
