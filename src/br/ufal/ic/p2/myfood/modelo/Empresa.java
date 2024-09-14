package br.ufal.ic.p2.myfood.modelo;

import java.util.List;
import java.util.ArrayList;

public class Empresa {
    private static int idCounterEmpresa = 1;
    private int id;
    private String nome;
    private String endereco;
    private Dono dono;
    private List<Produto> produtos;
    private List<Cliente> clientes;
    private int donoId;
    private final String PrefixoEmpresa = "e";


    public Empresa(int id, String nome, String endereco, int donoId) {
        this.id = Integer.parseInt(PrefixoEmpresa+ (id));
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.donoId = DonoRestaurante.getId();
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getTipoCozinha() { return tipoCozinha; }
    public int getDonoId() { return donoId; }
    public int getDono() {
        return donoId;
    }
    public void setDono(int dono) {
        this.donoId = dono;
    }
    public List<Produto> getProdutos() { return this.produtos; }
    public List<Cliente> getClientes() { return this.clientes; }
    public Produto getProdutoEspecifico(String nome) {
        for (Produto produto : this.produtos) {
            if (produto.getNome().equals(nome)) {
                return produto;
            }
        }
        return null;
    }
}
