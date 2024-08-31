package br.ufal.myfood.persistence;

import br.ufal.myfood.models.Empresa;

import java.util.HashMap;
import java.util.Map;

public class EmpresaPersistence {
    private Map<Integer, Empresa> empresas = new HashMap<>();
    private int idCounter = 0;

    public void salvar(Empresa empresa) {
        int id = idCounter++;
        empresas.put(id, empresa);
        empresa.setId(id);
    }

    public Empresa buscar(int id) {
        return empresas.get(id);
    }
}
