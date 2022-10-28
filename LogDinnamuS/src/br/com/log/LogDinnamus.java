/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.log;


 
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Layout;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 *
 * @author dti
 */
public class LogDinnamus {
        private static Logger logger;
        public static Logger getLogger(){
            if(logger==null){
                Iniciar();
            }
            return logger;
        }
        public static boolean Iniciar()
        {
            boolean bRetorno=true;
            try {
                //BasicConfigurator.configure();
                logger = Logger.getLogger(LogDinnamus.class);
                logger.addAppender(getFileAppend());
                logger.info("Iniciando o sistema de LogDinnamus - " + getCalendarInstance().getTime().toString());
                logger.addAppender(getConsoleAppend());

            } catch (Exception exception) {
                    exception.printStackTrace();
                    bRetorno=false;
            }
            return bRetorno;
        }
        
        public static void Log(Throwable thr) {
             Log(thr, false,0);
        }
        
        private static frmExibirLog jframe;
        private static void exibirJFrame(String msg , int tempovisivel){
            try {
                jframe= new frmExibirLog(null,true,tempovisivel);
                 
               
               jframe.jTextArea1.setText(msg);
                
                jframe.setVisible(true);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
        }
         public static void Log(Throwable thr, boolean  bExibirMSG) {
             Log(thr, bExibirMSG, 0);
         }
        
        public static void Log(Throwable thr, boolean  bExibirMSG, int tempovisivel) {
            
                getLogger().error("Registro de Exceção detectada : " + getCalendarInstance().getTime().toString()  , thr);
              // OffIntegracaoLogFacade offIntegracaoLogFacade = new OffIntegracaoLogFacade();
              // if(offIntegracaoLogFacade!=null){
              //     offIntegracaoLogFacade.gravarLog(new Exception(thr));
              // }
                
                if(bExibirMSG){
                   //PrintStream p = new PrintStream();*/
                    String cStackTrace ="";
                    for (int i = 0; i < thr.getStackTrace().length; i++) {
                        cStackTrace = cStackTrace  + thr.getStackTrace()[i].toString() + "\n";
                        
                    }
                    
                    JTextArea xMsg= new JTextArea();
                    xMsg.setText( thr.toString()+ "\n" +cStackTrace);
                    xMsg.setEditable(false);
                    xMsg.setSize(100, 100);
                    //xMsg.setLineWrap(true);
                    xMsg.setSelectionStart(0);
                    xMsg.setSelectionEnd(0);
                    
                  
                    Double y  =JOptionPane.getRootFrame().getToolkit().getScreenSize().getHeight()/7;
                    Double x = JOptionPane.getRootFrame().getToolkit().getScreenSize().getWidth()/4;
                    
                    JScrollPane scroll =new JScrollPane(xMsg);
                    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
                    scroll.setPreferredSize(new Dimension(x.intValue(), y.intValue()));
                    
                    exibirJFrame(cStackTrace,tempovisivel);
                    
                    //JOptionPane.showMessageDialog(null,scroll, "DinnamuS 2.0 - EXCEÇÃO DETECTADA",JOptionPane.ERROR_MESSAGE);
                    
                   
                }
                
                //return logger;
        }
        
        public static void Informacao(String cMsg) {
                getLogger().info(cMsg + " - "+getCalendarInstance().getTime().toString());
                //return logger;
        }
        

        private static Calendar getCalendarInstance()
        {
            return GregorianCalendar.getInstance();
        }
        private static Appender getFileAppend()
        {
            Appender fileAppender=null;
            try {
                Calendar cl=getCalendarInstance();

                String cNomeArquivo =  "log" + File.separator + "LogDinnamuS" +  String.valueOf(cl.get(Calendar.DAY_OF_MONTH)) + String.valueOf(cl.get(Calendar.MONTH)) + String.valueOf(cl.get(Calendar.YEAR)) +  ".log";
                fileAppender = new FileAppender(getLayOut(PatternLayout.TTCC_CONVERSION_PATTERN), cNomeArquivo);

            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(LogDinnamus.class.getName()).log(Level.SEVERE, null, ex);
            }
            return fileAppender;

        }
        private static Layout getLayOut(String pl)
        {
            return new PatternLayout(pl);
        }
        private static ConsoleAppender getConsoleAppend()
        {
            ConsoleAppender ca=null;

            try {
                ca=new ConsoleAppender(getLayOut(PatternLayout.TTCC_CONVERSION_PATTERN));

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return ca;
        }
        
        public static void main(String arg[])    {
            LogDinnamus.Iniciar();
            
            LogDinnamus.Log(new Exception("Testando log"),true,10);
            
        }



}
