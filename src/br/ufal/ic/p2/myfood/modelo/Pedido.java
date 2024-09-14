package br.ufal.ic.p2.myfood.modelo;

import java.util.ArrayList;
import java.util.List;
import br.ufal.ic.p2.myfood.excessoes.PedidoNaoAbertoException;
import br.ufal.ic.p2.myfood.excessoes.AlterarPedidoFechadoException;

public class Pedido {
    private static int IdCounterPedido = 1; // Gera o número único do pedido
    private int numero;
    private Usuario cliente;
    private Empresa empresa;
    private String estado;
    private List<Produto> listaProdutos  = new ArrayList<>();
    private float total;

    public Pedido(){
    }

    public Pedido(String cliente, String empresa) {
        this.numero = IdCounterPedido++;
        this.cliente = cliente;
        this.empresa = empresa;
        this.estado = "aberto";
    }

    // Getters e Setters
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero){
        this.numero = numero;
    }

    public String getCliente() {
        return cliente;
    }
    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public String getEmpresa() {
        return empresa;
    }
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void changeEstado() throws PedidoNaoAbertoException {
        if (this.estado.equals("aberto")){
            this.estado = "preparando";
        }
        else{
            throw new PedidoNaoAbertoException("Este pedido nao esta aberto");
        }
    }

    public List<Produto> getProdutos() {
        return listaProdutos;
    }
    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total){
        this.total = total;
    }

    public void addListaProdutos(Produto produto) throws AlterarPedidoFechadoException {
        if (estado.equals("preparando")) {
            throw new AlterarPedidoFechadoException("Nao se pode adicionar no pedido fechado");
        }

        listaProdutos.add(produto);
        this.total+=produto.getValor();
    }
    public void removerListaProdutos(Produto produto) throws AlterarPedidoFechadoException {
        if(estado.equals("preparando")) {
            throw new AlterarPedidoFechadoException("Nao se pode remover do pedido fechado");
        }
        if (listaProdutos.remove(produto)) {
            total -= produto.getValor();
        }
    }

    public String toString(){
        return "id = " + numero + ", Cliente" + cliente.getNome() + ", Empresa = " + empresa.getNome() + ", Estado = " + estado + "\n"
                + "Produtos de pedido: \n" + listaProdutos + "\n\n";
    }

}
