package br.ufal.ic.p2.myfood.servico;

import br.ufal.ic.p2.myfood.modelo.Empresa;
import br.ufal.ic.p2.myfood.persistencia.PersistenciaEmpresa;
import br.ufal.ic.p2.myfood.utils.Validador;

import java.util.Map;

public class EmpresaServico {
    private Map<Integer, Empresa> empresas;
    private int contadorId;

    public EmpresaServico() {
        this.empresas = PersistenciaEmpresa.carregarEmpresas();
        this.contadorId = empresas.size() + 1;
    }

    public int criarEmpresa(String tipoEmpresa, int dono, String nome, String endereco, String tipoCozinha) {
        Validador.validarNomeEmpresa(nome);
        Validador.validarEnderecoEmpresa(endereco);
        Validador.validarTipoCozinha(tipoCozinha);

        // Verificar se já existe uma empresa com o mesmo nome e dono
        for (Empresa empresa : empresas.values()) {
            if (empresa.getNome().equals(nome) && empresa.getDono() == dono) {
                throw new RuntimeException("Empresa com esse nome já existe.");
            }
        }

        // Verificar se já existe uma empresa com o mesmo nome e endereço
        for (Empresa empresa : empresas.values()) {
            if (empresa.getNome().equals(nome) && empresa.getEndereco().equals(endereco)) {
                throw new RuntimeException("Proibido cadastrar duas empresas com o mesmo nome e local.");
            }
        }

        Empresa novaEmpresa = new Empresa(contadorId++, nome, endereco, dono);
        empresas.put(novaEmpresa.getId(), novaEmpresa);
        PersistenciaEmpresa.salvarEmpresas(empresas);
        return novaEmpresa.getId();
    }

    public String getEmpresasDoUsuario(int idDono) {
        StringBuilder sb = new StringBuilder();
        for (Empresa empresa : empresas.values()) {
            if (empresa.getDono() == idDono) {
                sb.append("[").append(empresa.getNome()).append(", ").append(empresa.getEndereco()).append("], ");
            }
        }
        return sb.length() > 0 ? sb.substring(0, sb.length() - 2) : "{}";
    }

    public int getIdEmpresa(int idDono, String nome, int indice) {
        Validador.validarNomeEmpresa(nome);

        int count = 0;
        for (Empresa empresa : empresas.values()) {
            if (empresa.getDono() == idDono && empresa.getNome().equals(nome)) {
                if (count == indice) {
                    return empresa.getId();
                }
                count++;
            }
        }
        throw new RuntimeException("Não existe empresa com esse nome.");
    }

    public String getAtributoEmpresa(int idEmpresa, String atributo) {
        Empresa empresa = empresas.get(idEmpresa);
        if (empresa == null) {
            throw new RuntimeException("Empresa não cadastrada.");
        }

        switch (atributo) {
            case "nome":
                return empresa.getNome();
            case "endereco":
                return empresa.getEndereco();
            case "tipoCozinha":
                return empresa.getTipoCozinha();
            case "dono":
                return String.valueOf(empresa.getDono());
            default:
                throw new RuntimeException("Atributo inválido.");
        }
    }
}
