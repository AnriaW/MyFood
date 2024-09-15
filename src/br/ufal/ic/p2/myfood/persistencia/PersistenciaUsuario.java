package br.ufal.ic.p2.myfood.persistencia;

import br.ufal.ic.p2.myfood.modelo.Usuario;
import br.ufal.ic.p2.myfood.utils.TratarXML;
import br.ufal.ic.p2.myfood.utils.AuxPersistencia;

import java.util.List;
import java.util.ArrayList;

public class PersistenciaUsuario implements AuxPersistencia<Usuario> {


    private List<Usuario> listaUsuarios = new ArrayList<>();
    private TratarXML xml = new TratarXML();
    private final String file = "src/br/ufal/ic/p2/myfood/xml/usuarios.xml";

    @Override
    public void iniciar() {
        listaUsuarios = xml.TirarSerieXML(listaUsuarios, file);
    }

    @Override
    public void salvar(Usuario usuario) {
        listaUsuarios.add(usuario);
        xml.AdicionarSerieXML(listaUsuarios, file);
    }

    @Override
    public void remover(int id) {
        listaUsuarios.removeIf(usuario -> usuario.getId() == id);
        xml.AdicionarSerieXML(listaUsuarios, file);
    }

    @Override
    public void limpar() {
        if (listaUsuarios != null) {
            listaUsuarios.clear();
        }

        xml.DeletarDadosNoXML(file);
    }

    @Override
    public void editar(Usuario novo) {

    }


    @Override
    public void atualizar(){
        xml.AdicionarSerieXML(listaUsuarios, file);
    }

    @Override
    public Usuario buscar(int id) {
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getId() == id) {
                return usuario;
            }
        }
        //System.out.println("N achou o usuaio... pq?");
        return null;
    }

    @Override
    public List<Usuario> listar() {
        return listaUsuarios;
    }
}