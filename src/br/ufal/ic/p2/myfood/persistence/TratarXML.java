package br.ufal.ic.p2.myfood.persistence;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TratarXML {

    public TratarXML() {

    }

    /**
     * Serializa as informa��es do objeto no arquivo informado
     * @param objeto O objeto que deseja serializar
     * @param nome_arquivo O caminho onde os dados ser�o guardados
     */
    public void SerializarXML(Object objeto, String nome_arquivo) {
        try (FileOutputStream fos = new FileOutputStream(nome_arquivo);
             XMLEncoder encoder = new XMLEncoder(fos)) {
            encoder.writeObject(objeto);
//            System.out.println("Objetos de " + nome_arquivo + " foram serializados");
        } catch (IOException e) {
            System.out.println("Deu ruim!!");
        }

    }

    /**
     * Desserializa as informa��es do arquivo informado no objeto
     * @param objeto Objeto onde ser�o guardados as informa��es
     * @param nome_arquivo O caminho onde os dados est�o guardados
     * @return Retorna o objeto com as informa��es desserializadas
     */
    public <T> T DesserializarXML(T objeto, String nome_arquivo) {
        File file = new File(nome_arquivo);

        // Verificar se o arquivo existe e se est� vazio
        if (!file.exists()) {
//            System.out.println(nome_arquivo + " n�o existe.");
            return objeto;
        } else if (file.length() == 0) {
//            System.out.println(nome_arquivo + " est� vazio.");
            return objeto;
        }

        try (FileInputStream fis = new FileInputStream(nome_arquivo);
             XMLDecoder decoder = new XMLDecoder(fis)) {
            objeto = (T) decoder.readObject();
//            System.out.println("Objetos de " + nome_arquivo + " foram desserializados");
        } catch (IOException e) {
            System.out.println("Deu ruim!!");
        }
        return objeto;
    }

    /**
     * Limpa o arquivo informado
     * @param nome_arquivo Caminho com o arquivo a ser limpado
     */
    public void ApagarDadosXML(String nome_arquivo) {
        try (FileOutputStream fos = new FileOutputStream(nome_arquivo)) {
            // Escreve um conte�do vazio no arquivo, efetivamente apagando todos os dados
            fos.write(new byte[0]);
//            System.out.println("Todos os dados foram apagados do arquivo " + nome_arquivo);
        } catch (IOException e) {
            System.out.println("Deu ruim!!");
        }
    }
}
