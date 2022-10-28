/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.epson;

import br.String.ManipulacaoString;
import br.com.log.LogDinnamus;

/**
 *
 * @author Fernando
 */
public class epsondll {
   public static boolean PortaStatus =false;
   public static int Porta =1;
   public static String NomePorta ="";
   public static void Terminar(){
        InterfaceEpson_2.INSTANCE.EPSON_Serial_Fechar_Porta();
   }
   private static int getPorta(){
        int Retorno=0;
        try {
            if(!NomePorta.equalsIgnoreCase("")){
                if(NomePorta.equalsIgnoreCase("usb")){
                    Retorno=0;
                }else{
                    Retorno = Integer.valueOf(ManipulacaoString.DeixarSomenteNumeros(NomePorta));
                }
            }else{
               Retorno=1;            
            }            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }

    public static InterfaceEpson_2 getInstance(){
        try {
            if(!PortaStatus){                
                int nRetorno = InterfaceEpson_2.INSTANCE.EPSON_Serial_Abrir_Porta(38400, getPorta());
                if(nRetorno==0){
                    PortaStatus=true;
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
        return InterfaceEpson_2.INSTANCE;
    }
    
}
