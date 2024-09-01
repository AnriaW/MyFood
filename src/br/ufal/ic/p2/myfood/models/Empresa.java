package br.ufal.ic.p2.myfood.models;

public class Empresa {
    private String nome;
    private String endereco;
    private String tipoCozinha;
    private String tipoEmpresa;
    private Usuario dono;
    private int id;

    public Empresa(String nome, String endereco, String tipoCozinha, String tipoEmpresa, Usuario dono) {
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.tipoEmpresa = tipoEmpresa;
        this.dono = dono;
    }

    // Getters and Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTipoCozinha() {
        return tipoCozinha;
    }

    public void setTipoCozinha(String tipoCozinha) {
        this.tipoCozinha = tipoCozinha;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }

    public Usuario getDono() {
        return dono;
    }

    public void setDono(Usuario dono) {
        this.dono = dono;
    }

    public void setId(int id) {
        this.id = id;
    }
}