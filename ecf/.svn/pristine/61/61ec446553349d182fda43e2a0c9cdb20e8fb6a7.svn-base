/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.daruma;

import br.com.daruma.jna.ECF;
import br.com.log.LogDinnamus;
import br.com.repositorio.DAO_RepositorioLocal;
import br.conversao.ConversaoNumerica;
import br.data.formatar.DataFormatar;
import br.manipulararquivos.ManipulacaoArquivo;
import br.valor.formatar.FormatarNumero;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

 

/**
 *
 * @author Fernando
 */
public class DarumaECF {
    
   // private static ECF DLLDaruma = new ECF();

    /**
     * @return the DLLDaruma
     */
     private static boolean OK=false;
     static{        
        try{            
            //MetodosUI_Auxiliares.MensagemAoUsuarioSimplesAVISO(null, "teste", "teste");
            LogDinnamus.Informacao("Carregando DLL [DarumaFramework] ....");
            
            String PathDLL = ManipulacaoArquivo.DiretorioDeTrabalho();
                  
            String PathAtual =  System.getProperty("java.library.path");
            if(!PathAtual.contains(PathDLL)){
                System.setProperty("java.library.path",  PathDLL +";"+ PathAtual );
            }
            System.loadLibrary("DarumaFramework");
            OK=true;
            LogDinnamus.Informacao("DLL [DarumaFramework] carregada com sucesso");
            
        } catch(Exception e) {
           LogDinnamus.Log(null, true);
        }
    }
     
     public static boolean OK(){
         return OK;
     }
      public static int AbrirMovimento(){
        int nRet =0;
        try {
             nRet = ECF.iLeituraX(); 
             nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;        
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return nRet;
    }
     public static TreeMap<String,Float>TratarStringAliquotas(String Aliquotas){
         TreeMap<String,Float> aliq = new TreeMap<String, Float>();
         try {
            String[] arAliquotas = Aliquotas.split(";");
            for (Integer i = 1; i <= arAliquotas.length; i++) {
                if(arAliquotas[i-1].trim().indexOf("T")>=0 && arAliquotas[i-1].trim().length()==5){                    
                    String AliquotaFormatada = FormatarNumero.FormatarStringDeMoeda_EmMoeda( arAliquotas[i-1].trim().substring(1));
                    if(!AliquotaFormatada.trim().equalsIgnoreCase("0.00")){
                      aliq.put(i.toString(), new Float(AliquotaFormatada));
                    }
                }
                
            }
         } catch (Exception e) {
             LogDinnamus.Log(e, OK);
         }
         return aliq;
     }
     public static String ListarFormasDePagamento(){
         String Retotno = "";
         try {
             char[] pszRelatorios = new char[330];
             int ret  = ECF.rLerMeiosPagto(pszRelatorios);
             ret = DarumaECF.TratarRetornoErroDaruma(ret) ? 1 : 0 ;        
             if(ret==1){
                Retotno  =  new String(pszRelatorios).replace(";", ",").trim();
             }
             System.out.println(Retotno);
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return Retotno;
     }
    
     public static String DocumentoAbertoECF(){
         String retorno = new String();
         try {
             char[] TipoDocumentoAberto = new char[1];
             int Ret = ECF.rRetornarInformacao("56",TipoDocumentoAberto);
             Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;        
             if(Ret==1){
                 if(TipoDocumentoAberto[0]=='1'){ // cupom fiscal
                    retorno ="CF"; 
                 }else if(TipoDocumentoAberto[0]=='2'){ // COMPROVANTE VINCULADO
                    retorno ="CNVF"; 
                 }else if(TipoDocumentoAberto[0]=='3'){ // COMPROVANTE CCD
                    retorno ="CDC";  
                 }else if(TipoDocumentoAberto[0]=='4'){ // COMPROVANTE RG
                     retorno ="RG";  
                 }
             
             }
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return retorno;
                 
     }
     public static String UltimoCOO(){
         String Retorno ="";
         try {
             char[] pszRetornar = new char[6];
             int Ret = ECF.rRetornarInformacao("26", pszRetornar);
             TratarRetornoErroDaruma(Ret);        
             if(Ret==1){
                 Retorno = new String(pszRetornar);
             }
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return Retorno;
     }
     public static boolean CupomAberto(){
         try {
             if(DocumentoAbertoECF().equalsIgnoreCase("CF")){
                 return true;
             }
         } catch (Exception e) {
             LogDinnamus.Log(e, true);             
         }
         return false;
     }

     public static boolean DiaAberto_ECF(){
         try {
           int[] retorno =new int[1];
           int Ret = ECF.rConsultaStatusImpressoraInt(4, retorno);
           Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;              
           if(Ret==1){
              if(retorno[0]==1){ // dia aberto
                 return true; 
              } 
           }           
         } catch (Exception e) {
             LogDinnamus.Log(e, true);           
         }
         return false;
     }
     
     public static String DataAtualECF(boolean DataFormatada){
         String Retorno = "";
         try {
             char[] DataMovimento = new char[8];
             int Ret = ECF.rRetornarInformacao("66",DataMovimento);
             Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;        
             if(Ret==1){
                 Retorno = new String(DataMovimento);
                 String DataMovECF = (new String(Retorno)).substring(0,8);
                 System.out.println("Data Mov Daruma : " + DataMovECF);
                 if(DataFormatada){                                          
                     Retorno =DataFormatar.Formatar_String_DDMMYY_Em_String_DD_MM_YYYY(DataMovECF);
                 }
                 System.out.println("Data Mov Daruma Formatada : " + Retorno);
             }
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return Retorno;
     }  
     public static boolean CadastrarPagto(String Descricao){
         try {
             int Ret =ECF.confCadastrarPadrao("FPGTO",Descricao);
             Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;        
             if(Ret==1){
                 return true;
             }
         } catch (Exception e) {
             LogDinnamus.Log(e, true);             
         }
         return false;
     }
     public static String DataMovimento(boolean DataFormatada){
         String Retorno = "";
         try {
             char[] DataMovimento = new char[8];
             int Ret =ECF.rRetornarInformacao("70",DataMovimento);
             Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;        
             if(Ret==1){
                 Retorno = new String(DataMovimento);
                 if(DataFormatada){
                     Retorno =DataFormatar.Formatar_String_DDMMYY_Em_String_DD_MM_YYYY(Retorno);
                 }
             }
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return Retorno;
     }  
     public static String ZJaEmitida(){
        String Retorno="";
        try {
            String DataMovimentoUltimaZ ="";
           
            char[] pszDadosRZ = new char[1200];
            int Ret = ECF.rRetornarDadosReducaoZ(pszDadosRZ);
            Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;        
            if(Ret==1){
               DataMovimentoUltimaZ =(new String(pszDadosRZ)).substring(0,8);
               String DataMovECF ="";
               String HoraMovECF ="";               
               char[] DataHoraECF = new char[14];   
               Ret = ECF.rRetornarInformacao("66",DataHoraECF);
               Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;         
               if(Ret==1){
                   DataMovECF = (new String(DataHoraECF)).substring(0,8);
                   HoraMovECF = (new String(DataHoraECF)).substring(8,14);                  
                  if(DataMovimentoUltimaZ.equalsIgnoreCase(DataMovECF)){
                         Retorno = "ATENÇÃO --> REDUCAO Z EMITIDA EM [ "+  
                                    DataFormatar.Formatar_String_DDMMYY_Em_String_DD_MM_YYYY(DataMovECF) + " ]#";
                 }                  
               }               
            }            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
     public static boolean ZPendente(){
         try {
             char[] zPendente = new char[1];
             int Ret =ECF.rVerificarReducaoZ(zPendente);
             Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;        
             if(Ret==1){
                if(new String(zPendente).equalsIgnoreCase("1")) {
                   return true;
                }
             }
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             
         }
         return false;
     }
     public static boolean ConsultarDiaAberto(){
        try {
            ResultSet rsDadosEstacao = DAO_RepositorioLocal.GerarResultSet("select diaaberto from off_configuracaoestacao where diaaberto='"+ DataAtualECF(false) +"'");
            if(rsDadosEstacao.next()){
               return true;                    
            } 
        } catch (Exception e) {
            LogDinnamus.Log(e, true);           
        }
        return false;
    }
     public static ArrayList<String> VerificaEstadoECF(boolean VerificacaoInicial){
         ArrayList<String> retorno = new ArrayList<String>();
         char[] pszStatus = new char[14];
         try {
            System.out.println("VerificaEstadoECF : 1");
            int Ret =ECF.rStatusImpressora(pszStatus);
            Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;         
            if(Ret==1){      
                System.out.println("VerificaEstadoECF : 2");
                String StatusLido = new String(pszStatus);
                ArrayList<String> StatusBinario = ConversaoNumerica.Hex_Para_Binario(StatusLido);
               
                if(StatusBinario.size()>0){
                   //byte 1
                   String Byte1 = StatusBinario.get(0);
                   if(Byte1.substring(0, 1).equalsIgnoreCase("0")){
                       retorno.add("MODO FISCAL");
                   }else{
                       retorno.add("EM MODO DE INTERVENÇÃO - MIT#"); 
                   }
                 /*  //BYTE 3
                   String Byte3 = StatusBinario.get(2);
                  if(Byte3.substring(3, 4).equalsIgnoreCase("0")){
                      retorno.add("DIA FISCAL FECHADO#");
                  }else{
                      retorno.add("EM JORNADA FISCAL - MOVIMENTO DO DIA ABERTO"); 
                  }*/
                   if(ConsultarDiaAberto()){
                       retorno.add("EM JORNADA FISCAL - MOVIMENTO DO DIA [ " +  DataMovimento(true) + " ] ABERTO");                   
                   }else{
                      retorno.add("ATENÇÃO - > DIA FISCAL FECHADO#");                    
                   }
                   
                   //BYTE 4
                   String Byte4 = StatusBinario.get(3);
                   if(Byte4.substring(0, 1).equalsIgnoreCase("1")){                       
                       retorno.add("Já emitiu Redução Z HOJE#");
                   }
                   //Z ja emitida
                   String TextoZJaEmitida = ZJaEmitida();
                   if(!TextoZJaEmitida.equalsIgnoreCase("")){
                      retorno.add(TextoZJaEmitida);
                   }
                   
                   if(Byte4.substring(1, 2).equalsIgnoreCase("0")){
                       retorno.add("Não há Redução Z pendente");                  
                   }
                   boolean ZPendente=false;
                   // Z PENDENTE    
                   if(ZPendente()){
                      retorno.add("ATENÇÃO --> REDUCAO Z PENDENTE DO DIA ["+  DataMovimento(true) +"]#");
                      ZPendente=true;
                   }
                   
                  
                   
                   if(Byte4.substring(3, 4).equalsIgnoreCase("0")){
                       retorno.add("Bobina de papel presente");
                   }else{
                       retorno.add("Bobina de papel ausente#");
                   }
                   //BYTE 5
                   String Byte5 = StatusBinario.get(4);
                   if(Byte5.substring(0, 1).equalsIgnoreCase("0")){
                       retorno.add("Gaveta fechada");
                   }else{
                       retorno.add("Gaveta aberta");
                   }
                   
                   //BYTE 8
                   String Byte8 = StatusBinario.get(7);
                   if(Byte8.substring(0, 1).equalsIgnoreCase("0")){
                       retorno.add("Papel carregado (bobina)");
                   }else{
                       retorno.add("Aguardando papel#");
                   }
                   //BYTE 9
                   String Byte9 = StatusBinario.get(8);
                   if(Byte9.substring(0, 1).equalsIgnoreCase("0")){
                       retorno.add("ECF operacional");
                   }else{
                       retorno.add("ECF bloqueado#");
                   }
                   if(Byte9.substring(3, 4).equalsIgnoreCase("0")){
                       retorno.add("Tampa fechada");
                   }else{
                       retorno.add("Tampa aberta#");
                   }
                   
                   
                }                
            }
            
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         System.out.println("VerificaEstadoECF : 3");
         return retorno;
                
         
     }
     // cnpj, marca, modelo , tipo, numeroserie
    public static HashMap<String,String> DadosDoECF(){
         HashMap<String,String>  ret = new HashMap<String, String>();         
         try {
             ret =  DadosDoECF_MarcaModeloTipo();
             if(ret.size()>0){ 
                ret.put("cnpj", DadosDoECF_CNPJ());
                ret.put("mfd", DadosDoECF_NumSerieMFD());
                 
             }
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
    }
    public static String DadosDoECF_NumSerieMFD(){
         String  ret = new String();         
         try {
                         
             char[] mfd = new char[30];             
             int Ret =ECF.rRetornarInformacao("77",mfd);
             Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;         
             if(Ret==1){
                 ret = new String(mfd);                  
             }             
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
     }
    
    public static String DadosDoECF_CNPJ(){
         String  ret = new String();         
         try {                         
                         
             char[] CNPJ = new char[30];             
             int Ret =ECF.rRetornarInformacao("90",CNPJ);
             Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;         
             if(Ret==1){
                 ret = new String(CNPJ);                  
             }         
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
     }
    public static HashMap<String,String> DadosDoECF_MarcaModeloTipo(){
         HashMap<String,String>  ret = new HashMap<String, String>();         
         try {
             
             char[] marca = new char[20];
             char[] modelo = new char[20];
             char[] tipo= new char[7];                        
             int Ret = ECF.rRetornarInformacao("80",marca);
             Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;            
             if(Ret==1){                                                
                 ret.put("marca", new String(marca));
             }  
             Ret =  ECF.rRetornarInformacao("81",modelo);
             Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;        
             if(Ret==1){
                 ret.put("modelo", new String(modelo));
             }
             Ret =ECF.rRetornarInformacao("79",tipo);
             Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;        
             if(Ret==1){
                 ret.put("tipo", new String(tipo));                 
             }             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
     }
     
    public static boolean TratarRetornoErroDaruma(int nRet){
        boolean Ret = false;
        try {           
            if(nRet==1 || nRet==0){
                Ret = true;
            }else{            
                char[] pszRetorno = new char[200];
                int[] piErro= new int[3];
                int[] piAviso= new int[3];
                ECF.rStatusUltimoCmdInt(piErro, piAviso);

                if(piErro!=null && piAviso!=null ){
                    LogDinnamus.Informacao("Retorno Erro ECF Daruma Framework : Erro: " + piErro[0] + " - Aviso : "+ piAviso[0] );
                }
                if(piErro[0]==0  && piAviso[0]==1){
                   Ret  = true; // Aviso de pouco papel
                }else{
                   Ret  = false; 
                }
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
}
