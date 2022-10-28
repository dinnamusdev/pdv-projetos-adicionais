/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tef.Padrao;

import br.com.log.LogDinnamus;
import br.data.DataHora;
import br.manipulararquivos.ManipulacaoArquivo;
import static java.lang.Thread.sleep;
import java.util.Date;

/**
 *
 * @author Fernando
 */
public class TEFAguardaRetorno {
    
    //public static boolean bInterrompe=false;
    public static boolean Verificando=false;
    public static String NomeArquivo = "";
    public static boolean AchouArquivo=false;
    public static int Timeout =0;
    public static boolean TimeoutExcedido=false;
    public static boolean Executar=false;
    
    public static boolean VerificarArquivo_Interromper(){
            try {
            
            Verificando=false;
            Executar=false;
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
    public static boolean VerificarArquivo_Iniciar(String cNomeArquivo, final int Timeout){
        try {
                Verificando=true;
                AchouArquivo=false;
                NomeArquivo=cNomeArquivo;
                TimeoutExcedido=false;
                
                new Thread(){

                    @Override
                    public void run() {
                        Executar=true;
                        long Agora = DataHora.Calendario().getTimeInMillis();
                        while(Executar){ 
                             System.out.print("THREAD VERIFICAR ARQUIVO "  +new Date() + "\n");
                            if(Timeout>0){
                               long diferenca = System.currentTimeMillis() - Agora; 
                               if(diferenca>Timeout){
                                   Executar=false;
                                   TimeoutExcedido=true;                                   
                               }
                            }                            
                            if(Verificando){
                                System.out.print("....VERIFICANDO ARQUIVO " + NomeArquivo + " - " + new Date() + "\n");
                                if(VerificarArquivo(NomeArquivo)){                                    
                                  Verificando=false;
                                }
                            }                            
                            try {
                                sleep(1000);
                            } catch (InterruptedException ex) {
                                LogDinnamus.Log(ex, true);
                            }
                        }
                    }
                }.start();
                return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
            
    }
    public static boolean VerificarArquivo(String cNomeArquivo){
        int nRetorno = 0;
        try {     
           
            //String NomeArquivoVerificar = cNomeArquivo;
          
            if(ManipulacaoArquivo.ArquivoExiste(cNomeArquivo, false)){                     
                AchouArquivo=true;
                return true;
            }          
          
        } catch (Exception e) {
            LogDinnamus.Log(e, true);            
        }
        //InteracaoTEF.MensagemTEF("TEF DinnamuS", "Foram Feitas 10(dez) tentativas para abrir o arquivo de RETORNO, porêm este, pode estar em Uso pelo Gerenciador Padrão. Aguarde Alguns Instantes e tente Novamente.CASO O PROBLEMA PERSISTA , ENTRE EM CONTATO COM O SUPORTE DTI-INFORMÁTICA", 5000);                        
        return false;
        
    }
}
