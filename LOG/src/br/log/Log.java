package br.log;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




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
public abstract class Log {
        private  Logger logger;
        private String nomeLog ;
        public  Logger getLogger(){
            return logger;
        }
        public  boolean Iniciar(String nomeLog)
        {
            boolean bRetorno=true;
            try {
                //BasicConfigurator.configure();
                this.nomeLog =nomeLog;
                logger = Logger.getLogger(Log.class);
                logger.addAppender(getFileAppend());
                logger.info("Iniciando o sistema de LogDinnamus - " + getCalendarInstance().getTime().toString());
                logger.addAppender(getConsoleAppend());

            } catch (Exception exception) {
                    exception.printStackTrace();
                    bRetorno=false;
            }
            return bRetorno;
        }
        
        public  void Log(Throwable thr) {
             Log(thr, false);
        }
        
        public  void Log(Throwable thr, boolean  bExibirMSG) {
            
                logger.error("Registro de Exceção detectada : " + getCalendarInstance().getTime().toString()  , thr);
                
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
                    
                    JOptionPane.showMessageDialog(null,scroll, "DinnamuS 2.0 - REGISTRO DE EXCEÇÃO",JOptionPane.ERROR_MESSAGE);
                    
                }
                
                //return logger;
        }
        
        public  void Informacao(String cMsg) {
                logger.info(cMsg + " - "+getCalendarInstance().getTime().toString());
                //return logger;
        }
        

        private  Calendar getCalendarInstance()
        {
            return GregorianCalendar.getInstance();
        }
        private  Appender getFileAppend()
        {
            Appender fileAppender=null;
            try {
                Calendar cl=getCalendarInstance();

                String cNomeArquivo =  "log" + File.separator + nomeLog +  String.valueOf(cl.get(Calendar.DAY_OF_MONTH)) + String.valueOf(cl.get(Calendar.MONTH)) + String.valueOf(cl.get(Calendar.YEAR)) +  ".log";
                fileAppender = new FileAppender(getLayOut(PatternLayout.TTCC_CONVERSION_PATTERN), cNomeArquivo);

            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
            }
            return fileAppender;

        }
        private  Layout getLayOut(String pl)
        {
            return new PatternLayout(pl);
        }
        private  ConsoleAppender getConsoleAppend()
        {
            ConsoleAppender ca=null;

            try {
                ca=new ConsoleAppender(getLayOut(PatternLayout.TTCC_CONVERSION_PATTERN));

            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return ca;
        }



}
