package br.ufal.ic.p2.myfood.utils;

import java.io.File;
import java.io.IOException;
import java.beans.XMLEncoder;
import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class TratarXML {

    public void AdicionarSerieXML(Object objeto, String file){
        try(FileOutputStream fos = new FileOutputStream(file);
            XMLEncoder encoder = new XMLEncoder(fos)){
            encoder.writeObject(objeto);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T TirarSerieXML(T objeto, String file){
        File arquivo = new File(file);

        if(!arquivo.exists()) {
            return objeto;
        }
        else if(arquivo.length() == 0){
            return objeto;
        }

        try (FileInputStream fis = new FileInputStream(file);
             XMLDecoder decoder = new XMLDecoder(fis);) {
            objeto = (T) decoder.readObject();
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }

        return objeto;
    }

    public void DeletarDadosNoXML(String file){
        try(FileOutputStream fos = new FileOutputStream(file);){
            fos.write(new byte[0]);
        }
        catch (IOException e){
            throw new RuntimeException(e);
        }
    }

}