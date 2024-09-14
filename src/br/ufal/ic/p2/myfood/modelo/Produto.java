package br.ufal.ic.p2.myfood.modelo;

public class Produto {
    private int idCounterProduto = 1;
    private int id;
    private String nome;
    private float valor;
    private String categoria;
    private int IDono;

    public Produto{
    }

    public Produto(String nome, float valor, String categoria,int IDono) {
        this.id = idCounterProduto++;
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
        this.IDono = IDono;
    }

    public int getId() {
        return id;
    }

    public int setId(int id) {
        return this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
