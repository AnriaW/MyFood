package br.ufal.ic.p2.myfood.modelo;

public class Empresa {
    protected int id;
    protected String nome;
    protected String endereco;
    protected String tipoCozinha;
    protected int donoId;

    public Empresa(int id, String nome, String endereco, int donoId) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipoCozinha = tipoCozinha;
        this.donoId = donoId;
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
}
