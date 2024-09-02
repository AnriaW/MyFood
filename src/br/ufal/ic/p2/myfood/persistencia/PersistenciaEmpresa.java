package br.ufal.ic.p2.myfood.persistencia;

import br.ufal.ic.p2.myfood.modelo.Empresa;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PersistenciaEmpresa {

    private static final String CAMINHO_XML = "src/br/ufal/ic/p2/myfood/xml/empresas.xml";
    private static Map<Integer, Empresa> empresas = new HashMap<>();


    @SuppressWarnings("unchecked")
    public static Map<Integer, Empresa> carregarEmpresas() {
        try (XMLDecoder decoder = new XMLDecoder(new FileInputStream(CAMINHO_XML))) {
            empresas = (Map<Integer, Empresa>) decoder.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ta dando errado... N sei pq");
            empresas = new HashMap<>();
        }
        return empresas;
    }

    public static void salvarEmpresas(Map<Integer, Empresa> empresas) {
        try (XMLEncoder encoder = new XMLEncoder(new FileOutputStream(CAMINHO_XML))) {
            encoder.writeObject(empresas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void adicionarEmpresa(Empresa empresa) {
        empresas.put(empresa.getId(), empresa);
        salvarEmpresas(empresas);
    }

    public static Empresa obterEmpresa(int id) {
        return empresas.get(id);
    }

    public static void removerEmpresa(int id) {
        empresas.remove(id);
        salvarEmpresas(empresas);
    }

    public static Map<Integer, Empresa> getEmpresas() {
        return empresas;
    }
}
