/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.epson;

import br.String.ManipulacaoString;
import br.com.log.LogDinnamus;
import br.valor.formatar.FormatarNumero;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author Fernando
 */
public class epsonmetodosadicionais {
 
    public static String CondicaoCadPagto ="Os documentos devem estar fechados e o ECF com papel.\nAtenção: formas de pagamento somente podem ser removidas em intervenção técnica";
    public static String CondicaoCadAliquota ="Os documentos devem estar fechados e o ECF com papel. \nCaso o ECF não possua inscrição municipal cadastrada, \nnão será possível incluir alíquotas ISS.";
    public static boolean ECFLigado(){
        boolean Ret = false;
        try {
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
    public static boolean ProgramarPagto(boolean Vinculado, String Forma, String Cod){
        try {
            int Retorno  = epsondll.getInstance().EPSON_Config_Forma_Pagamento(Vinculado, Cod, Forma);
            if(Retorno==0){
                return true;
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);           
        }
         return false;
    }
    public static TreeMap<String,Float> TratarStringAliquotas(String Aliquotas){
        //TreeMap<String,Float> lista = new TreeMap<String, Float>();
        
        TreeMap<String,Float> aliqList = new TreeMap<String, Float>();
        String Ret ="";
        ArrayList<String> aliq  =new ArrayList<String>();
        try {
            String Aliquota ="";
            String PercAliq = "";
            String CodAliquota = "";
            for (int i = 0; i < 23; i++) {
                Aliquota = Aliquotas.substring(i*23, (i*23)+23);
                PercAliq = Aliquota.substring(2,6);                
                if(PercAliq.trim().length()>0){
                   CodAliquota =Aliquota.substring(0, 2);
                   PercAliq = FormatarNumero.FormatarStringDeMoeda_EmMoeda(PercAliq);
                   aliqList.put(CodAliquota, new Float(PercAliq));
                }                
            }                
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return aliqList;
    }
    public static String RetornarFormasDePagto(){
        String cRetorno ="";
        try {
            byte[] pszTabelaPagamentos = new byte[881];
            if(epsondll.getInstance().EPSON_Obter_Tabela_Pagamentos(pszTabelaPagamentos)==0){
                String cDados = new String(pszTabelaPagamentos);
                int nPos =2;
                for (int i = 1; i < 20; i++) {
                    String Forma = cDados.substring(nPos, nPos+14).trim();
                    if(Forma.trim().length()>0){
                        cRetorno+= (cRetorno.length()>0 ? "," : "") + Forma;
                       
                    }
                    nPos += 44;
                }
            }
            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return cRetorno;
    }
    public static int CupomAberto(){
        try {
            String cRetorno= TratarRetornoDocumentoAberto(EstadoECF("EstadoFiscal").toCharArray(),false);
            if("ATENÇÃO --> Cupom Fiscal aberto".equalsIgnoreCase(cRetorno)){
                return 1;
            }            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
          
        }
          return 0;
        
    }
    public static int EstadoECF_Gaveta(){
        try {
            String EstadoImpressora =  EstadoECF("EstadoImpressora");
            if(EstadoImpressora.trim().length()>0){
                if(EstadoImpressora.substring(2, 3)=="0"){
                    return 0;
                }else{
                    return 1;
                }
            }else{
               return 0;
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return -1;
        }
    }
    public static String DataECF(){
        String Retorno="";
        try {
            byte[] DadosJornada = new byte[15];            
            String DataECF ="";
            if(epsondll.getInstance().EPSON_Obter_Hora_Relogio(DadosJornada)==0){
               Retorno = String.valueOf((char)DadosJornada[0]) + String.valueOf((char)DadosJornada[1]) + "/" +
                                String.valueOf((char)DadosJornada[2]) +String.valueOf((char)DadosJornada[3]) + "/" +
                                      String.valueOf((char)DadosJornada[4]) + String.valueOf((char)DadosJornada[5]) +  String.valueOf((char)DadosJornada[6]) + String.valueOf((char)DadosJornada[7]) ;
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public static boolean DiaAberto(){
        try {            
           String DataECF = DataECF();
           String DataMovimento =DataMovimento();
           if(DataECF.equalsIgnoreCase(DataMovimento)){
               return true;
           }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);           
        }
        return false;
    }
    
    public static String UltimoCupom(){
        String Retorno ="";
        try {
            
            Retorno = Obter_Estado_Cupom();
            if(Retorno.length()>0){
                Retorno = Retorno.substring(2, 8);
            }
                    
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    
    }
    public static String Obter_Estado_Cupom(){
        String Retorno ="";
        try {
            
            byte[] RetornoCupom = new byte[57];
            epsondll.getInstance().EPSON_Obter_Estado_Cupom(RetornoCupom);
            Retorno = new String(RetornoCupom);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public static String MensagemErro(boolean VerificacaoInicial){
        String cRetorno ="";
        try {
            
            cRetorno =  EstadoECF("MsgErro");
            String cEstadoECFAvancado ="";
            ArrayList<String>  EstadoECFAvancado = MensagemEstadoECF(VerificacaoInicial);
            for (int i = 0; i < EstadoECFAvancado.size(); i++) {
                if(EstadoECFAvancado.get(i).contains("#")){
                   cEstadoECFAvancado += " " + EstadoECFAvancado.get(i)+ "\n";
                }
            }
            if(cEstadoECFAvancado.length()>0){
                cEstadoECFAvancado = "\nSTATUS ECF ATUAL\n" + cEstadoECFAvancado;
                cRetorno += cEstadoECFAvancado;
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return cRetorno;
    }
    
    public static boolean ZPendente(){
        try {
            
               byte[] DadosJornada = new byte[68];
               
               int nRetorno =epsondll.getInstance().EPSON_Obter_Dados_Jornada(DadosJornada);
               if(nRetorno==0){
                   if(DadosJornada[65]=='1'){
                       return true;
                   }
               }
                   
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return false;
    }
    public static String DataMovimento(){
        String Retorno ="";
        try {            
            byte[] DadosJornada = new byte[68];
            int nRetorno =epsondll.getInstance().EPSON_Obter_Dados_Jornada(DadosJornada);
            if(nRetorno==0){            
               String DataInicioJornada = String.valueOf((char)DadosJornada[0]) + String.valueOf((char)DadosJornada[1]) + "/" +
                                String.valueOf((char)DadosJornada[2]) +String.valueOf((char)DadosJornada[3]) + "/" +
                                String.valueOf((char)DadosJornada[4]) + String.valueOf((char)DadosJornada[5]) +  String.valueOf((char)DadosJornada[6]) + String.valueOf((char)DadosJornada[7]) ;
               return DataInicioJornada;
            }    
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public static String ZJaEmitida(){
        String Retorno="";
        try {            
               byte[] DadosJornada = new byte[68];
               byte[] DataHoraRelogio = new byte[15];
               int nRetorno =epsondll.getInstance().EPSON_Obter_Dados_Jornada(DadosJornada);
               if(nRetorno==0){
            
                   String DataInicioJornada ="", DataReducao = "" ,HoraReducao="", DataMovimento="";
                   
                   byte[] pszUltimaRZ = new byte[1168];
                   
                   nRetorno = epsondll.getInstance().EPSON_Obter_Dados_Ultima_RZ(pszUltimaRZ);
                   
                   
                   DataReducao = String.valueOf((char)pszUltimaRZ[0]) + String.valueOf((char)pszUltimaRZ[1]) + "/" +
                                String.valueOf((char)pszUltimaRZ[2]) +String.valueOf((char)pszUltimaRZ[3]) + "/" +
                                String.valueOf((char)pszUltimaRZ[4]) + String.valueOf((char)pszUltimaRZ[5]) +  
                               String.valueOf((char)pszUltimaRZ[6]) + String.valueOf((char)pszUltimaRZ[7]) ;

                   HoraReducao = String.valueOf((char)pszUltimaRZ[8]) + String.valueOf((char)pszUltimaRZ[9]) + ":" +
                                 String.valueOf((char)pszUltimaRZ[10]) + String.valueOf((char)pszUltimaRZ[11]) + ":" +
                                 String.valueOf((char)pszUltimaRZ[12]) + String.valueOf((char)pszUltimaRZ[13]);
                   
                   DataMovimento = String.valueOf((char)pszUltimaRZ[1159]) + String.valueOf((char)pszUltimaRZ[1160]) + "/" +
                                String.valueOf((char)pszUltimaRZ[1161]) +String.valueOf((char)pszUltimaRZ[1162]) + "/" +
                                String.valueOf((char)pszUltimaRZ[1163]) + String.valueOf((char)pszUltimaRZ[1164]) +  
                               String.valueOf((char)pszUltimaRZ[1165]) + String.valueOf((char)pszUltimaRZ[1166]) ;

                  
                  DataInicioJornada = String.valueOf((char)DadosJornada[0]) + String.valueOf((char)DadosJornada[1]) + "/" +
                                String.valueOf((char)DadosJornada[2]) +String.valueOf((char)DadosJornada[3]) + "/" +
                                String.valueOf((char)DadosJornada[4]) + String.valueOf((char)DadosJornada[5]) +  String.valueOf((char)DadosJornada[6]) + String.valueOf((char)DadosJornada[7]) ;
                  
                   if(DataMovimento.equalsIgnoreCase(DataInicioJornada) && DataInicioJornada.equalsIgnoreCase(DataReducao)){
                       Retorno= "ATENÇÃO --> ** REDUCAO Z EMITIDA EM ["+ DataReducao + " AS " +  HoraReducao +"]**#".toUpperCase();
                   }
               }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return Retorno;
    }
    public static ArrayList<String> MensagemEstadoECF(boolean VerificacaoInicial){
        ArrayList<String> alStatus = new ArrayList<String>();
        try {
            char[] cRetornoECf = EstadoECF("EstadoImpressora").toCharArray();
            if(cRetornoECf.length==0){
                return alStatus;                
            }
            if(cRetornoECf[0]=='0'){
                alStatus.add("IMPRESSORA ONLINE");
            }else{
                alStatus.add("ATENÇÃO -- > IMPRESSORA OFFLINE#");
            }
            
            if(cRetornoECf[1]=='1'){
                alStatus.add("ATENÇÃO -- >Erro de impressão#".toUpperCase());             
            }
            
            if(cRetornoECf[2]=='1'){
                alStatus.add("ATENÇÃO -- > TAMPA SUPERIOR ABERTA#".toUpperCase());             
            }
            
            if(cRetornoECf[3]=='0'){
                alStatus.add("GAVETA FECHADA".toUpperCase());             
            }else{
                alStatus.add("ATENÇÃO -- > GAVETA ABERTA#".toUpperCase());             
            }
            
            if(cRetornoECf[7]=='1'){
                alStatus.add("Aguardando retirada do papel".toUpperCase());             
            
            }
            if(cRetornoECf[14]=='1'){
                alStatus.add("ATENÇÃO -- > IMPRESSORA SEM PAPEL#".toUpperCase());             
            
            }
            if(cRetornoECf[15]=='1'){
                alStatus.add("ATENÇÃO --> IMPRESSORA COM POUCO PAPEL#".toUpperCase());             
            
            }
            
            String cDadosFiscal = EstadoECF("EstadoFiscal");
            char[] cEstadoFiscal = cDadosFiscal.toCharArray();
            
              if(cEstadoFiscal[3]=='0'){
                  alStatus.add("Modo de operação normal".toUpperCase());                           
              }else{    
                  alStatus.add("ATENÇÃO --> Modo de Intervenção Técnica#".toUpperCase());                           
               }
              
               if(cEstadoFiscal[8]=='1'){
                  alStatus.add("Período de vendas aberto".toUpperCase());                           
              }else{    
                  alStatus.add("ATENÇÃO --> DIA FISCAL FECHADO#".toUpperCase());                           
               }
               
               String cRetornoDocAberto = TratarRetornoDocumentoAberto(cEstadoFiscal,VerificacaoInicial);
               if(cRetornoDocAberto.length()>0){
                      alStatus.add(cRetornoDocAberto);   
               }
               
               if(ZPendente()){
                 alStatus.add("ATENÇÃO --> REDUCAO Z PENDENTE#");
               }
               String ZJaEmitida = ZJaEmitida();
               
               if(!ZJaEmitida.equalsIgnoreCase("")){
                   alStatus.add(ZJaEmitida);
               }
    
              
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        } 
        return alStatus;
    }
    public static ArrayList<String> StatusECF_Estendido(){
        ArrayList<String> retorno = new ArrayList<String>();
        try {
            
            byte[] estadoecf = new byte[21];
            
            int nRetorno =epsondll.getInstance().EPSON_Obter_Estado_Impressora(estadoecf);
            byte[] EstadoFiscal = EstadoECF("EstadoFiscal").getBytes();
            if(EstadoFiscal.length>0){
                char Byte13 = (char)(EstadoFiscal[12]);
                char Byte14 = (char)(EstadoFiscal[13]);
                char Byte15 = (char)(EstadoFiscal[14]);
                char Byte16 = (char)(EstadoFiscal[15]);

                if( Byte13=='0' && Byte14=='0' && Byte15=='1' && Byte16=='0'){
                   // COMPROVANTE DE DEBITO CREDITO 
                    retorno.add("CDC");
                }if(Byte13=='0' && Byte14=='1' && Byte15=='0' && Byte16=='0'){
                  //RELATORIO GERENCIAL
                    retorno.add("RG");
                }if(Byte13=='1' && Byte14=='0' && Byte15=='0' && Byte16=='0'){
                  //COMPROVANTE NAO FISCAL  
                    retorno.add("CNFV");
                }
            }
            
                    
            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return retorno;
    }
    public static String TratarRetornoDocumentoAberto(char[] cEstadoFiscal, boolean VerificacaoInicial){
        
        String cRetorno ="";
         try {
              // char[] cEstadoFiscal = Dados.toCharArray();    
             if(cEstadoFiscal.length>0){
                char[] DocumentoAberto =new char[4];
                DocumentoAberto[0]=cEstadoFiscal[12];
                DocumentoAberto[1]=cEstadoFiscal[13];
                DocumentoAberto[2]=cEstadoFiscal[14];
                DocumentoAberto[3]=cEstadoFiscal[15];
                String cDocumentoAberto = new String(DocumentoAberto);
                if("0001".equalsIgnoreCase(cDocumentoAberto)){
                   cRetorno  ="ATENÇÃO --> Cupom Fiscal aberto".toUpperCase() + (VerificacaoInicial ? "#" : "");                         
                }else if(!"0000".equalsIgnoreCase(cDocumentoAberto)){
                   cRetorno= "ATENÇÃO --> documento aberto".toUpperCase()  ;                        
                }    
             }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        
        }
         return cRetorno;
    }
    public static String EstadoECF(String cTipoDados ){
        String cRet ="";
         try {
             
            
            byte[]  szEstadoImpressora =new byte[17];
           // Arrays.fill(szEstadoImpressora, ' ');
            
            byte[] szEstadoFiscal=new byte[17];
            //Arrays.fill(szEstadoFiscal, ' ');
            
            byte[] szRetornoComando= new byte[17];
            //Arrays.fill(szRetornoComando, ' ');
            
            byte[] szMsgErro= new byte[101];
            //Arrays.fill(szMsgErro, ' ');
            
            int nRetorno =epsondll.getInstance().EPSON_Obter_Estado_ImpressoraEX(szEstadoImpressora, szEstadoFiscal, szRetornoComando, szMsgErro);
            
            //int nRetorno =epsondll.getInstance().EPSON_Obter_Estado_Impressora(szEstadoImpressora);//, szEstadoFiscal, szRetornoComando, szMsgErro);
            
            if(nRetorno==0){
                if(cTipoDados.equalsIgnoreCase("EstadoImpressora")){
                    cRet= new String(szEstadoImpressora);
                }else if(cTipoDados.equalsIgnoreCase("EstadoFiscal")){
                    cRet= new String(szEstadoFiscal);
                }else if(cTipoDados.equalsIgnoreCase("RetornoComando")){
                    cRet= new String(szRetornoComando);
                }else if(cTipoDados.equalsIgnoreCase("MsgErro")){
                    cRet= new String(szMsgErro);
                }
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
         return cRet;
    }
    
    // cnpj, marca, modelo , tipo, numeroserie
    public static HashMap<String,String> DadosDoECF(){
         HashMap<String,String>  ret = new HashMap<String, String>();         
         try {
             ret =  DadosDoECF_MarcaModeloTipoMFD();
             if(ret.size()>0){ 
                ret.put("cnpj", DadosDoECF_CNPJ());
             }
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
    }
    public static String DadosDoECF_NumSerieMFD(){
         String  ret = new String();         
         try {
             
             
            HashMap<String,String> retfunc =  DadosDoECF_MarcaModeloTipoMFD();
            if(retfunc.size()>0){
                ret = retfunc.get("mfd");
            }
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
     }
    
    public static String DadosDoECF_CNPJ(){
         String  ret = new String();         
         try {
                          
             byte[] cnpj = new byte[49];
             
             if(epsondll.getInstance().EPSON_Obter_Dados_Usuario(cnpj)==0){
                String cnpjecf = new String(cnpj).substring(0, 18);                
                ret =ManipulacaoString.DeixarSomenteNumeros(cnpjecf);
             }
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
     }
    public static HashMap<String,String> DadosDoECF_MarcaModeloTipoMFD(){
         HashMap<String,String>  ret = new HashMap<String, String>();         
         try {
            
             byte[] dadosecf= new byte[109];
             if(epsondll.getInstance().EPSON_Obter_Dados_Impressora(dadosecf)==0){
                 String Marca="",Modelo="",Tipo="",MFD ="";
                 String DadosECF = new String( dadosecf);
                 MFD= DadosECF.substring(20,40).trim();
                 Marca = DadosECF.substring(40,60).trim();
                 Modelo = DadosECF.substring(60,80).trim();
                 Tipo = DadosECF.substring(80,100).trim();
                 ret.put("marca", Marca);
                 ret.put("modelo", Modelo);
                 ret.put("tipo", Tipo);
                 ret.put("mfd", MFD);
             }             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
     }
}
