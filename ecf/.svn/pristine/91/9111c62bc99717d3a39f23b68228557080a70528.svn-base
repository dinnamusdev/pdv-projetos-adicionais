/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecf;

import br.String.ManipulacaoString;
import br.com.log.LogDinnamus;
import br.manipulararquivos.ManipulacaoArquivo;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class ECFSuportados {
    private static String EncondingPadrao= "ISO-8859-1";
    private static String NomeECFSweda = "SWEDA MFD STxxxx";
    private static String NomeECFBematech = "Bematech MP20 FII";
    private static String NomeECFEpson = "EPSON TM-T81 FBIII";
    private static String NomeECFDaruma32 = "Daruma Framework";

    /**
     * @return the NomeECFSweda
     */
    public static String getNomeECFSweda() {
        return NomeECFSweda;
    }

    /**
     * @return the NomeECFBematech
     */
    public static String getNomeECFBematech() {
        return NomeECFBematech;
    }

    /**
     * @return the NomeECFEpson
     */
    public static String getNomeECFEpson() {
        return NomeECFEpson;
    }

    /**
     * @return the NomeECFDaruma32
     */
    public static String getNomeECFDaruma32() {
        return NomeECFDaruma32;
    }
    
    /**
     * @return the DLL_Arquivos
     */
    public static String getDLLs_ECF_ArquivoCFG(String ModeloECF) {
        String ret = new String();
        try {             
             if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFBematech())){              
                    ret ="BemaFI32.ini";                           
              }else if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFDaruma32())){               
                    ret = "DarumaFrameWork.xml";                
              }else if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFEpson())){                          
              }else if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFSweda())){ 
                    ret ="SWC.INI";                
              }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }        
        return ret;        
    }
    public static ArrayList<String> getDLLs_ECF(String ModeloECF) {
        ArrayList<String> DLL_Arquivos = new ArrayList<String>();
        try {             
             if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFBematech())){
                DLL_Arquivos.add("BemaFI32.dll");
                DLL_Arquivos.add("BemaFI32.ini");
                DLL_Arquivos.add("BemaMFD.dll");
                DLL_Arquivos.add("BemaMFD2.dll");             
              }else if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFDaruma32())){
                DLL_Arquivos.add("DarumaFrameWork.dll");
                DLL_Arquivos.add("DarumaFrameWork.xml");                
              }else if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFEpson())){
                DLL_Arquivos.add("InterfaceEpson.dll");                
              }else if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFSweda())){
                DLL_Arquivos.add("CONVECF.dll");
                DLL_Arquivos.add("CONVECF95.dll");
                DLL_Arquivos.add("CONVECF98.dll");
                DLL_Arquivos.add("Swmfd.dll");
                DLL_Arquivos.add("SWC.INI");
                DLL_Arquivos.add("SWMFD.INI");
              }

            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
        return DLL_Arquivos;
    } 
    public static boolean EditarCFG(String ModeloECF,String Porta){
        boolean Ret = false;
        try {
            if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFBematech())){
                    Ret = EditarCFG_BEMATECH(ModeloECF, Porta);
              }else if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFDaruma32())){                          
                   Ret = EditarCFG_DARUMA(ModeloECF, Porta);
              }else if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFSweda())){
                  Ret = EditarCFG_SWEDA_SWC(ModeloECF, Porta);
                  if(Ret){
                      Ret =EditarCFG_SWEDA_SWMFD(ModeloECF, Porta);
                  }
              }
            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
    public static boolean EditarCFG_DARUMA(String ModeloECF, String Porta){
      boolean Ret = false;
        try {
            String Arquivo = PastaDLL_ECF(ModeloECF) + ManipulacaoArquivo.getSeparadorDiretorio() + getDLLs_ECF_ArquivoCFG(ModeloECF);
            boolean Atualizou=false;
            if(Arquivo.length()>0){
                String Conteudo = ManipulacaoArquivo.LerArquivo_Enconding(Arquivo,false,EncondingPadrao);
                if(Conteudo.length()>0){
                    String[] arConteudo = Conteudo.split("\n");
                    boolean TagComunicacao = false;
                    for (int i = 0; i < arConteudo.length; i++) {
                        if(arConteudo[i].trim().toLowerCase().equalsIgnoreCase("<ecf>".toLowerCase())){
                           TagComunicacao =true; 
                        }
                        if(TagComunicacao && arConteudo[i].trim().toLowerCase().contains("<portaserial>")){
                           arConteudo[i]="    <PortaSerial>COM"+Porta.substring(3).trim() + "</PortaSerial>";
                           Atualizou=true;
                           break;
                        }
                    }
                    if(Atualizou){
                       String NovoArquivo = ManipulacaoString.TransformarArrayEmStringComTerminador(arConteudo, ManipulacaoArquivo.newline);
                       if(NovoArquivo.length()>0){
                           if(ManipulacaoArquivo.CriarArquivo_Enconding(Arquivo, NovoArquivo, EncondingPadrao)){
                               Ret=true;
                           }
                       }
                    }
                }
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
    
    public static boolean EditarCFG_BEMATECH(String ModeloECF, String Porta){
      boolean Ret = false;
        try {
            String Arquivo = PastaDLL_ECF(ModeloECF) + ManipulacaoArquivo.getSeparadorDiretorio() + getDLLs_ECF_ArquivoCFG(ModeloECF);
            boolean Atualizou=false;
            if(Arquivo.length()>0){
                String Conteudo = ManipulacaoArquivo.LerArquivo_Enconding(Arquivo,false,EncondingPadrao);
                if(Conteudo.length()>0){
                    String[] arConteudo = Conteudo.split("\n");
                    boolean TagComunicacao = false;
                    for (int i = 0; i < arConteudo.length; i++) {
                        if(arConteudo[i].trim().toLowerCase().equalsIgnoreCase("[Sistema]".toLowerCase())){
                           TagComunicacao =true; 
                        }
                        if(TagComunicacao && arConteudo[i].trim().toLowerCase().contains("porta")){
                           arConteudo[i]="Porta=COM"+Porta.substring(3).trim();
                           Atualizou=true;
                           break;
                        }
                    }
                    if(Atualizou){
                       String NovoArquivo = ManipulacaoString.TransformarArrayEmStringComTerminador(arConteudo, ManipulacaoArquivo.newline);
                       if(NovoArquivo.length()>0){
                           if(ManipulacaoArquivo.CriarArquivo_Enconding(Arquivo, NovoArquivo, EncondingPadrao)){
                               Ret=true;
                           }
                       }
                    }
                }
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
    
    public static boolean EditarCFG_SWEDA_SWC(String ModeloECF, String Porta){
        boolean Ret = false;
        try {
            String Arquivo = PastaDLL_ECF(ModeloECF) + ManipulacaoArquivo.getSeparadorDiretorio() + getDLLs_ECF_ArquivoCFG(ModeloECF);
            boolean Atualizou=false;
            if(Arquivo.length()>0){
                String Conteudo = ManipulacaoArquivo.LerArquivo_Enconding(Arquivo,false,EncondingPadrao);
                if(Conteudo.length()>0){
                    String[] arConteudo = Conteudo.split("\n");
                    boolean TagComunicacao = false;
                    for (int i = 0; i < arConteudo.length; i++) {
                        if(arConteudo[i].trim().toLowerCase().equalsIgnoreCase("[COMUNICAÇÃO]".toLowerCase())){
                           TagComunicacao =true; 
                        }
                        if(TagComunicacao && arConteudo[i].trim().toLowerCase().contains("porta")){
                           arConteudo[i]="   PORTA="+Porta.substring(3).trim();
                           Atualizou=true;
                           break;
                        }
                    }
                    if(Atualizou){
                       String NovoArquivo = ManipulacaoString.TransformarArrayEmStringComTerminador(arConteudo, ManipulacaoArquivo.newline);
                       if(NovoArquivo.length()>0){
                           if(ManipulacaoArquivo.CriarArquivo_Enconding(Arquivo, NovoArquivo, EncondingPadrao)){
                               Ret=true;
                           }
                       }
                    }
                }
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
    public static boolean EditarCFG_SWEDA_SWMFD(String ModeloECF, String Porta){
        boolean Ret = false;
        try {
            String Arquivo = PastaDLL_ECF(ModeloECF) + ManipulacaoArquivo.getSeparadorDiretorio() + "SWMFD.INI";
            boolean Atualizou=false;
            if(Arquivo.length()>0){
                String Conteudo = ManipulacaoArquivo.LerArquivo_Enconding(Arquivo,false,EncondingPadrao);
                if(Conteudo.length()>0){
                    String[] arConteudo = Conteudo.split("\n");
                    boolean TagComunicacao = false;
                    for (int i = 0; i < arConteudo.length; i++) {
                        if(arConteudo[i].trim().toLowerCase().equalsIgnoreCase("[COMUNICAÇÃO]".toLowerCase())){
                           TagComunicacao =true; 
                        }
                        if(TagComunicacao && arConteudo[i].trim().toLowerCase().contains("porta")){
                           arConteudo[i]="   PORTA="+Porta.substring(3).trim();
                           Atualizou=true;
                           break;
                        }
                    }
                    if(Atualizou){
                       String NovoArquivo = ManipulacaoString.TransformarArrayEmStringComTerminador(arConteudo, ManipulacaoArquivo.newline);
                       if(NovoArquivo.length()>0){
                           if(ManipulacaoArquivo.CriarArquivo_Enconding(Arquivo, NovoArquivo, EncondingPadrao)){
                               Ret=true;
                           }
                       }
                    }
                }
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }

    public static String PastaDLL_ECF(String ModeloECF){
         String ret ="";
         try {             
             if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFBematech())){
                  ret = "bematech";
              }else if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFDaruma32())){
                  ret = "daruma";
              }else if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFEpson())){
                  ret ="epson";
              }else if(ModeloECF.equalsIgnoreCase(ECFSuportados.getNomeECFSweda())){
                  ret = "sweda";
              }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return ManipulacaoArquivo.DiretorioDeTrabalho(); //+ "\\ecf\\"+ ret;
    }
}
