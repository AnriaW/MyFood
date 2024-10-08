package br.ufal.ic.p2.myfood.persistence;

import br.ufal.ic.p2.myfood.utils.Persistencia;
import br.ufal.ic.p2.myfood.models.Empresa;

import java.util.ArrayList;
import java.util.List;

public class PersistenciaEmpresa implements Persistencia<Empresa> {

    private List<Empresa> comp_list = new ArrayList<>();
    private TratarXML controle = new TratarXML();
    private final String arquivo = "xml/empresas.xml";


    @Override
    public void iniciar() {
        comp_list = controle.DesserializarXML(comp_list, arquivo);
    }

    @Override
    public void salvar(Empresa modelo) {
        comp_list.add(modelo);
        controle.SerializarXML(comp_list, arquivo);
    }

    @Override
    public void remover(int id){
        comp_list.removeIf(comp -> comp.getId() == id);
        controle.SerializarXML(comp_list, arquivo);
    }

    @Override
    public void limpar() {
     
        if (comp_list != null) {
            comp_list.clear();
        }

      
        controle.ApagarDadosXML(arquivo);
    }

    @Override
    public void editar(Empresa nova_empresa){

    }

    @Override
    public Empresa buscar(int id) {
        for (Empresa comp : comp_list) {
            if (comp.getId() == id) {
                return comp;
            }
        }
        return null;
    }

    @Override
    public List<Empresa> listar() {
        return comp_list;
    }

    @Override
    public void atualizar() {
        controle.SerializarXML(comp_list, arquivo);
    }
}
