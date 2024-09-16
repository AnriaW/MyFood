package br.ufal.ic.p2.myfood.persistencia;

import br.ufal.ic.p2.myfood.models.Pedido;
import br.ufal.ic.p2.myfood.utils.TratarXML;
import br.ufal.ic.p2.myfood.utils.AuxPersistencia;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class PersistenciaPedido implements AuxPersistencia<Pedido> {


    private List<Pedido> listaPedidos = new ArrayList<>();
    private TratarXML xml = new TratarXML();
    private final String file = "src/br/ufal/ic/p2/myfood/xml/usuarios.xml";

    @Override
    public void iniciar() {
        listaPedidos = xml.TirarSerieXML(listaPedidos, file);
    }

    @Override
    public void salvar(Pedido pedido) {
        listaPedidos.add(pedido);
        xml.AdicionarSerieXML(listaPedidos, file);
    }

    @Override
    public void remover(int id) {
        listaPedidos.removeIf(pedido -> pedido.getNumero() == id);
        xml.AdicionarSerieXML(listaPedidos, file);
    }

    @Override
    public void limpar() {
        if (listaPedidos != null) {
            listaPedidos.clear();
        }

        xml.DeletarDadosNoXML(file);
    }

    @Override
    public void editar(Pedido novo) {

    }


    @Override
    public void atualizar(){
        xml.AdicionarSerieXML(listaPedidos, file);
    }


    @Override
    public Pedido buscar(int id) {
        for (Pedido pedido : listaPedidos) {
            if (pedido.getNumero() == id) {
                return pedido;
            }
        }
        return null;
    }

    @Override
    public List<Pedido> listar() {
        return listaPedidos;
    }
}