package br.ufal.ic.p2.myfood.persistencia;

import br.ufal.ic.p2.myfood.modelo.Produto;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class PersistenciaProduto {
    private static final String CAMINHO_XML = "src/br/ufal/ic/p2/myfood/xml/produtos.xml";

    public static void salvarProdutos(Map<Integer, Produto> produtos) {
        try (XMLEncoder encoder = new XMLEncoder(Files.newOutputStream(Paths.get(CAMINHO_XML)))) {
            encoder.writeObject(produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<Integer, Produto> carregarProdutos() {
        Map<Integer, Produto> produtos = new HashMap<>();
        try (XMLDecoder decoder = new XMLDecoder(Files.newInputStream(Paths.get(CAMINHO_XML)))) {
            produtos = (Map<Integer, Produto>) decoder.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return produtos;
    }
}
