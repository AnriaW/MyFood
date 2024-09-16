package br.ufal.ic.p2.myfood.utils;

import java.util.List;

public interface AuxPersistencia<T> {
     void iniciar();
     void salvar(T modelo);
     void remover(int id);
     void limpar();
     void editar(T novo);
     T buscar(int id);
     List<T> listar();
     void atualizar();
}
