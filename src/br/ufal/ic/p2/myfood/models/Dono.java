package br.ufal.ic.p2.myfood.models;

import java.util.ArrayList;
import java.util.List;

public class Dono extends Usuario{
    private String cpf;
    private List<Empresa> ListaEmpresas;

    public Dono(){
    }

    public Dono(String nome, String email, String senha, String endereco, String cpf){
        super(nome, email, senha, endereco);
        this.cpf = cpf;
        this.ListaEmpresas = new ArrayList<Empresa>();
    }

    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Empresa> getComp_list(){
        return ListaEmpresas;
    }
    public void setComp_list(List<Empresa> ListaEmpresas){
        this.ListaEmpresas = ListaEmpresas;
    }

    public void addListaEmpresa(Empresa empresa){
        this.ListaEmpresas.add(empresa);
    }

    @Override
    public String getAtributo(String atributo){
        if (atributo.equals(cpf)){
            return cpf;
        }
        return super.getAtributo(atributo);
    }
}
