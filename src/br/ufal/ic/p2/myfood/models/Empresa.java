package br.ufal.ic.p2.myfood.models;

import java.util.List;
import java.util.ArrayList;

public class Empresa {
    private static int idCounterEmpresa = 1;
    private int id;
    private String nome;
    private String endereco;
    private Dono dono;
    private List<Produto> ListaProdutos;

    public Empresa(){
    }

    public Empresa(String nome, String endereco, Dono dono) {
        this.id = idCounterEmpresa++;
        this.nome = nome;
        this.endereco = endereco;
        this.dono = dono;
        ListaProdutos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco){
        this.endereco= endereco;
    }

    public Dono getDono() {
        return dono;
    }
    public void setDono(Dono dono){
        this.dono = dono;
    }

    public List<Produto> getProd_list(){
        return ListaProdutos;
    }
    public void setProd_list(List<Produto> ListaProdutos){
        this.ListaProdutos = ListaProdutos;
    }

    public void addListaProdutos(Produto produto){
        this.ListaProdutos.add(produto);
    }

    public String getAtributo(String atributo) {
        if (atributo.equals("id")) {
            return String.valueOf(id);
        } else if (atributo.equals("nome")) {
            return nome;
        } else if (atributo.equals("endereco")) {
            return endereco;
        }
        return null;
    }

    @Override
    public String toString(){
        return "[" + nome + ", " + endereco + "]";
    }
}
