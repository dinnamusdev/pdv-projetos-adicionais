/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tef.Padrao.ui;

import br.com.log.LogDinnamus;
import br.data.DataHora;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author Fernando
 */
public class TEFInteracao {
        private static frmMensagemTEF mensagemTEF =null;
        private static frmMensagemTEFAguardar mensagemTEFAguardar =null;
        public static void MensagemTEF(String Titulo, String Mensagem , int Tempo){
            MensagemTEF(Titulo, Mensagem, Tempo, false);
        }
        public static void MensagemTEF(String Titulo, String Mensagem , int Tempo, boolean Maximizar){
            new frmMensagemTEF(null,true,Tempo,Mensagem,Titulo,false).setVisible(true);
        }
        
        public static void MensagemTEF_Iniciar(final String Titulo, final String Mensagem  ){
            MensagemTEF_Iniciar(Titulo, Mensagem, false);
        }
        public static void MensagemTEF_Iniciar(final String Titulo, final String Mensagem , final boolean Maximizar){
               mensagemTEFAguardar =null;
               
               new Thread(new Runnable() {  
                    @Override
                    public void run() {                                        
                        mensagemTEFAguardar =new  frmMensagemTEFAguardar(null,true,0,Mensagem,Titulo,Maximizar);                        
                        mensagemTEFAguardar.setVisible(true);                      
                  }                   
               }).start();
               while(mensagemTEFAguardar==null){
                   System.out.println("Loop :"+DataHora.getHora());
                   if(mensagemTEFAguardar!=null){
                       if(mensagemTEFAguardar.isVisible()){
                           mensagemTEFAguardar.update(mensagemTEFAguardar.getGraphics());
                          break; 
                       }
                   }
               }
               
        }
        public static void MensagemTEF_Terminar()  {
                if(mensagemTEFAguardar!=null){
                
                    mensagemTEFAguardar.bTerminarMensagemfixa=true;

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        LogDinnamus.Log(ex, true);
                    }
                    mensagemTEFAguardar.dispose();
                }
                
        }
          
}
