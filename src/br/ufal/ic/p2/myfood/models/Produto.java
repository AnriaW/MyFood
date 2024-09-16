package br.ufal.ic.p2.myfood.models;

public class Produto {
    private int idCounterProduto = 1;
    private int id;
    private String nome;
    private float valor;
    private String categoria;
    private int Id_dono;

    public Produto() {
    }

    public Produto(String nome, float valor, String categoria,int Id_dono) {
        this.id = idCounterProduto++;
        this.nome = nome;
        this.valor = valor;
        this.categoria = categoria;
        this.Id_dono = Id_dono;
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
    public int getId_dono(){
        return Id_dono;
    }
    public void setId_dono(int Id_dono) {
        this.Id_dono = Id_dono;
    }

    public String toString(){
        return nome;
    }
}
