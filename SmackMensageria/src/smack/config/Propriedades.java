/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.config;

import br.com.log.LogDinnamus;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
 
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 *
 * @author DSWM
 */
public class Propriedades {
      public Properties getPropriedades(String filename) {
        try {
            Properties prop = new Properties();
            InputStream input = null;

            //String filename = "parametrosBasicos.properties";
            input = Propriedades.class.getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                System.out.println("O arquivo [ " + filename + " ] nao foi localizado");
                return null;
            }

            prop.load(input);

            return prop;

        } catch (Exception e) {
            LogDinnamus.Log(e, false);
            return null;
        }

    }
      
     public Properties setPropriedades(String filename, String propriedade, String value) {
        try {
            Properties prop =  getPropriedadesPorArquivo(filename);
            
            prop.setProperty(propriedade, value);
            
            File f = new File(filename);
            
            OutputStream input =  new FileOutputStream( f ) ;
            
            prop.store(input, null);
            
            return prop;

        } catch (Exception e) {
            LogDinnamus.Log(e, false);
            return null;
        }

    }  
      
      
      
     public Properties getPropriedadesPorArquivo(String filename) {
        try {
            Properties prop = new Properties();
            InputStream input = null;

            //String filename = "parametrosBasicos.properties";
            input = new FileInputStream(new File(filename));
            if (input == null) {
                System.out.println("O arquivo [ " + filename + " ] nao foi localizado");
                return null;
            }

            prop.load(input);

            return prop;

        } catch (Exception e) {
            LogDinnamus.Log(e, false);
            return null;
        }

    }  
      
}
