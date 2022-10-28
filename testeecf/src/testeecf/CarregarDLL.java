package testeecf;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.log.LogDinnamus;
import com.sun.jna.Native;

/**
 *
 * @author Fernando
 */
public class CarregarDLL {
    public static Object Carregar(String string, Class type){
            
        Object Ret = null;
        try {            
                       
           LogDinnamus.Informacao("Carregando Dll ECF [ " + string + "]");
          
           Ret=  Native.loadLibrary(string, type);
         
           LogDinnamus.Informacao("Dll ECF[ " + string + " ] carregada com sucesso"); 
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
        return Ret;    
    }
}
