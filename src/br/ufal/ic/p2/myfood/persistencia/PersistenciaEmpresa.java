package br.ufal.ic.p2.myfood.persistencia;

import br.ufal.ic.p2.myfood.models.Empresa;
import br.ufal.ic.p2.myfood.utils.TratarXML;
import br.ufal.ic.p2.myfood.utils.AuxPersistencia;

import java.util.List;
import java.util.ArrayList;

public class PersistenciaEmpresa implements AuxPersistencia<Empresa> {


    private List<Empresa> listaEmpresas = new ArrayList<>();
    private TratarXML xml = new TratarXML();
    private final String file = "src/br/ufal/ic/p2/myfood/xml/usuarios.xml";

    @Override
    public void iniciar() {
        listaEmpresas = xml.TirarSerieXML(listaEmpresas, file);
    }

    @Override
    public void salvar(Empresa empresa) {
        listaEmpresas.add(empresa);
        xml.AdicionarSerieXML(listaEmpresas, file);
    }

    @Override
    public void remover(int id) {
        listaEmpresas.removeIf(empresa -> empresa.getId() == id);
        xml.AdicionarSerieXML(listaEmpresas, file);
    }

    @Override
    public void limpar() {
        if (listaEmpresas != null) {
            listaEmpresas.clear();
        }

        xml.DeletarDadosNoXML(file);
    }

    @Override
    public void editar(Empresa novo) {

    }


    @Override
    public void atualizar(){
        xml.AdicionarSerieXML(listaEmpresas, file);
    }


    @Override
    public Empresa buscar(int id) {
        for (Empresa empresa : listaEmpresas) {
            if (empresa.getId() == id) {
                return empresa;
            }
        }
        return null;
    }

    @Override
    public List<Empresa> listar() {
        return listaEmpresas;
    }
}