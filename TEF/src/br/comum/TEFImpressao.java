/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.comum;

import br.com.ecf.ECFDinnamuS;
import br.com.log.LogDinnamus;

/**
 *
 * @author Fernando
 */
public class TEFImpressao {
    private static ECFDinnamuS EcfTEF = null;

    /**
     * @return the EcfTEF
     */
    
    public static boolean ComprovanteVinculado(String CNFV, String DadosImprimir){
        boolean Retorno = false;
        try {
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    
    }
    public static ECFDinnamuS getEcfTEF() {
        
        return EcfTEF;
    }

    /**
     * @param aEcfTEF the EcfTEF to set
     */
    public static void setEcfTEF(ECFDinnamuS aEcfTEF) {
        EcfTEF = aEcfTEF;
    }
    
}
