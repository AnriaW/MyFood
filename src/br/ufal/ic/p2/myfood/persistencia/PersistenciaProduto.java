package br.ufal.ic.p2.myfood.persistencia;

import br.ufal.ic.p2.myfood.models.Produto;
import br.ufal.ic.p2.myfood.utils.TratarXML;
import br.ufal.ic.p2.myfood.utils.AuxPersistencia;

import java.util.List;
import java.util.ArrayList;

public class PersistenciaProduto implements AuxPersistencia<Produto> {


    private List<Produto> listaProdutos = new ArrayList<>();
    private TratarXML xml = new TratarXML();
    private final String file = "src/br/ufal/ic/p2/myfood/xml/usuarios.xml";

    @Override
    public void iniciar() {
        listaProdutos = xml.TirarSerieXML(listaProdutos, file);
    }

    @Override
    public void salvar(Produto produto) {
        listaProdutos.add(produto);
        xml.AdicionarSerieXML(listaProdutos, file);
    }

    @Override
    public void remover(int id) {
        listaProdutos.removeIf(produto -> produto.getId() == id);
        xml.AdicionarSerieXML(listaProdutos, file);
    }

    @Override
    public void limpar() {
        if (listaProdutos != null) {
            listaProdutos.clear();
        }

        xml.DeletarDadosNoXML(file);
    }

    @Override
    public void editar(Produto novo) {

    }


    @Override
    public void atualizar(){
        xml.AdicionarSerieXML(listaProdutos, file);
    }
    @Override
    public Produto buscar(int id) {
        for (Produto produto : listaProdutos) {
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    @Override
    public List<Produto> listar() {
        return listaProdutos;
    }
}