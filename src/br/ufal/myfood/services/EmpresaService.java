package br.ufal.myfood.services;

import br.ufal.myfood.models.Empresa;
import br.ufal.myfood.models.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpresaService {
    private Map<Integer, Empresa> empresas = new HashMap<>();
    private Map<Usuario, List<Empresa>> empresasPorDono = new HashMap<>();
    private int idCounter = 0;

    public Empresa criarEmpresa(String nome, String endereco, String tipoCozinha, String tipoEmpresa, Usuario dono) {
        Empresa empresa = new Empresa(nome, endereco, tipoCozinha, tipoEmpresa, dono);
        int id = idCounter++;
        empresas.put(id, empresa);
        empresasPorDono.computeIfAbsent(dono, k -> new ArrayList<>()).add(empresa);
        return empresa;
    }

    public Empresa getEmpresa(int id) {
        return empresas.get(id);
    }

    public List<Empresa> getEmpresasDoUsuario(Usuario dono) {
        return empresasPorDono.getOrDefault(dono, new ArrayList<>());
    }
}
