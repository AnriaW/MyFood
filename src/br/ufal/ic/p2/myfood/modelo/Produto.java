package br.ufal.ic.p2.myfood.modelo;

public class Produto {
    private int idCounterProduto = 1;
    private int id;
    private String nome;
    private float valor;
    private String categoria;
    private int IDono;

    public Produto() {
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

    public float getValor(){
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
    public int getIDono(){
        return IDono;
    }
    public void setIDono() {
        this.IDono = IDono;
    }

    public String toString(){
        return nome;
    }
}
