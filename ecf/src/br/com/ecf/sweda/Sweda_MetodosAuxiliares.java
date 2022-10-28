/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ecf.sweda;

import br.String.ManipulacaoString;
import br.com.ManipularString;
import br.com.log.LogDinnamus;
import br.data.formatar.DataFormatar;
import br.valor.formatar.FormatarNumero;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
 

/**
 *
 * @author dti
 */

public class Sweda_MetodosAuxiliares {
    private static boolean PortaOk = false;
    public static String CondicaoCadPagto = "Só será possível programar o pagamento no ECF\nimediatamente após a REDUÇÃO Z e antes do inicio do dia (LEITURA X)";
    
    public static boolean EcfLigado(){
        boolean Ret = false;
        try {
            
            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
    public static boolean IniciarECF(){
        try {
            
            
             if(!Sweda_MetodosAuxiliares.isPortaOk()){
                    //int ret  = CONVECF.INSTANCE.ECF_AbrePortaSerial();                    
                    //if(ret ==1){
                      setPortaOk(true);
                    //}                  
                    if(isPortaOk()){
                        CONVECF.INSTANCE.ECF_LigaDesligaJanelas("0", "0");
                    }
                }
            
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
    public static  TreeMap<String,Float> TratarStringAliquotas(String StrAliquotas){
        TreeMap<String,Float> aliq = new TreeMap<String, Float>();
        try {
            String[] Aliquotas = StrAliquotas.split(",");
            for (Integer i = 1; i <= Aliquotas.length; i++) {
                if(Aliquotas[i-1].trim().indexOf("T")>=0){
                    String CodAliquota = Aliquotas[i-1].trim().substring(0,2);
                    String AliquotaFormatada = FormatarNumero.FormatarStringDeMoeda_EmMoeda( Aliquotas[i-1].trim().substring(2));
                    aliq.put(CodAliquota, new Float(AliquotaFormatada));
                }
                
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return aliq;
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
             
             
             byte[] mfd = new byte[20];
             if(CONVECF.INSTANCE.ECF_NumeroSerieMFD(mfd)==1){
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
                          
             byte[] cnpj = new byte[20];
             if(CONVECF.INSTANCE.ECF_CNPJMFD(cnpj)==1){
                ret =ManipulacaoString.DeixarSomenteNumeros(new String(cnpj));
             }
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
     }
    public static HashMap<String,String> DadosDoECF_MarcaModeloTipo(){
         HashMap<String,String>  ret = new HashMap<String, String>();         
         try {
             
             byte[] marca = new byte[15];
             byte[] modelo = new byte[20];
             byte[] tipo= new byte[7];
             if(CONVECF.INSTANCE.ECF_MarcaModeloTipoImpressoraMFD(marca, modelo, tipo)==1){
                 ret.put("marca", new String(marca));
                 ret.put("modelo", new String(modelo));
                 ret.put("tipo", new String(tipo));                 
             }             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
     }
    public static boolean DiaAberto(){
        
        boolean retorno = false;
        try {   
                int nRetorno =0;
                byte[] Status={0,0};
                if(CONVECF.INSTANCE.ECF_VerificaDiaAberto(Status)==1){
                   String cRetornoFuncao = (new String(Status)).trim();
                   nRetorno = Integer.parseInt(cRetornoFuncao);
                   if(nRetorno==1){
                       retorno=true;
                   }
                }else{
                    nRetorno=-1;
                }   
            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return retorno;
    }
    public static int StatusCupomFiscal(){
        
        int nRet=0;
        try {
               
               byte[] Status={0,0};

               nRet= CONVECF.INSTANCE.ECF_StatusCupomFiscal(Status);
               if(nRet==1){
                   if(Status[0]==Byte.valueOf("49")){
                       nRet=1;
                   }else{
                       nRet=0;
                   }
               }    
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
       return nRet;
    }

    public static String DataMovimento(){
        String cRetorno="";
        try {
            byte[] DataMovim={0,0,0,0,0,0};
            CONVECF.INSTANCE.ECF_DataMovimento( DataMovim);
            cRetorno = new String(DataMovim);
            cRetorno = cRetorno.substring(0, 2) + "/" + cRetorno.substring(2, 4)  + "/20" + cRetorno.substring(4, 6);
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return cRetorno;
    }
    public static String ZJaEmitida(){
        String Retorno="";
        try {
            byte[] DataMovimentoUltimaZ = new byte[6];
            byte[] DataECF = new byte[6];
            byte[] HoraECF = new byte[6];
            if(CONVECF.INSTANCE.ECF_DataMovimentoUltimaReducaoMFD(DataMovimentoUltimaZ)==1){
                if(CONVECF.INSTANCE.ECF_DataHoraImpressora(DataECF, HoraECF)==1){
                    String cDataMovimentoUltimaZ = new String(DataMovimentoUltimaZ);
                    String cDataECF = new String(DataECF);
                    String cHoraECF = new String(HoraECF);
                    
                    if(cDataMovimentoUltimaZ.equalsIgnoreCase(cDataECF)){
                         Retorno = "ATENÇÃO --> REDUCAO Z EMITIDA EM [ "+  
                                    DataFormatar.Formatar_String_DDMMYY_Em_String_DD_MM_YY(cDataECF) +" ] AS [ " + 
                                    DataFormatar.Formatar_String_HHMMSS_Em_String_HH_MM_SS(cHoraECF) + " ]";
                    }
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public static ArrayList<String> PalavraStatus(boolean VerificacaoInicial){
        ArrayList<String> alPalavras = new ArrayList<String>();
        try {
                byte[] Status = {0,0,0,0,0,0,0,0,0,0,0,0};
                String cPalavra="";
                String cLetra ="";
                String ByteLido="";
                String cMensagem="";

                if(CONVECF.INSTANCE.ECF_PalavraStatus(Status)==1){
                    cPalavra  = new String(Status);
                    for (int i = 0; i < cPalavra.length()-1; i++) {
                        cLetra = cPalavra.substring(i, i+1);
                        ByteLido = Integer.toBinaryString(Integer.parseInt(cLetra,16));                        
                        if(ByteLido.length()<4){
                           ByteLido =  ManipulacaoString.Replicate("0", 4 - ByteLido.length()) + ByteLido;
                        }
                        for(int j=0;j<ByteLido.length();j++){
                            cMensagem = TratarMensagem(ByteLido.charAt(j), j,i+1,VerificacaoInicial);
                            if(cMensagem.length()>0){
                                alPalavras.add(cMensagem);
                            }
                        }
                    }
                }
                if(DiaAberto()){
                   alPalavras.add("DIA FISCAL ABERTO");
                }else{
                   alPalavras.add("ATENÇÃO --> DIA FISCAL FECHADO#");
                }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return alPalavras;
    }

    public static ArrayList<String> RetornoAposComando(){
           ArrayList<String> arMsg = new ArrayList<String>();
           int ack[]={0,0,0,0}, st1[]={0,0,0,0}, st2[]={0,0,0,0};
           try {               
               CONVECF.INSTANCE.ECF_RetornoImpressora(ack, st1, st2);
           } catch (Exception e) {
               LogDinnamus.Log(e);
           }
           return arMsg;
    }
    private static String TratarMensagem(char cLetra, int nBit ,int nByte, boolean VerificacaoInicial){
            String cRetorno="";
            
            try {
                if(nByte==1 ){
                   if(cLetra=='1' && nBit==0 ) {
                      cRetorno +="Gaveta aberta";
                   }else if(cLetra=='1' && nBit==1) {
                       cRetorno +="ECF com erro#";
                   }else if(cLetra=='1' && nBit==2){
                       cRetorno +="Sem documento para autenticar";
                   }else if(cLetra=='1' && nBit==3){
                       cRetorno +="Fim de papel";
                   }
                }else if(nByte==2){
                    if(cLetra=='1' && nBit==0) {
                      cRetorno +="ECF ligado";
                   }else if(cLetra=='1' && nBit==1) {
                       cRetorno +="Sempre Zero";
                   }else if(cLetra=='1' && nBit==2){
                       cRetorno +="REDUCAO Z PENDENTE#";
                   }else if(cLetra=='1' && nBit==3){
                       cRetorno +="Papel acabando";
                   }
                }else if(nByte==3){
                    if(cLetra=='1' && nBit==0) {
                      cRetorno +="ECF em operação";
                   }else if(cLetra=='1' && nBit==1) {
                       cRetorno +="Sensor de autenticação habilitado";
                   }else if(cLetra=='1' && nBit==2){
                       cRetorno +="Guilhotina habilitada";
                   }else if(cLetra=='1' && nBit==3){
                       cRetorno +="Fechamento automático de cupom habilitado";
                   }
                }else if(nByte==4){
                    if(cLetra=='1' && nBit==0) {
                      cRetorno +="Relatório Gerencial aberto";
                   }else if(cLetra=='1' && nBit==1) {
                       cRetorno +="Cupom Fiscal Aberto"  ;//+ (VerificacaoInicial ? "#" : "");
                   }
                }else if(nByte==5){
                    if(cLetra=='1' && nBit==0) {
                      cRetorno +="Intervenção Técnica – MIT.#";
                   }else if(cLetra=='1' && nBit==1) {
                       cRetorno +="ECF com defeito#";
                   }
                }else if(nByte==6){
                    if(cLetra=='1' && nBit==0) {
                      cRetorno +="Programação na próxima REDUÇÃO Z";
                   }else if(cLetra=='1' && nBit==1) {
                       cRetorno +="LEITURA X do início do dia já emitida";
                   }else if(cLetra=='1' && nBit==2){
                       cRetorno +=ZJaEmitida();//"REDUÇÃO Z  emitida – Dia encerrado#";
                   }else if(cLetra=='1' && nBit==3){
                       cRetorno +="Fim de papel#";
                   }
                }else if(nByte==10){
                    if(cLetra=='1' && nBit==0) {
                      cRetorno +="erro de MF#";
                   }else if(cLetra=='1' && nBit==1) {
                       cRetorno +="erro de MF#";
                   }else if(cLetra=='1' && nBit==2){
                       cRetorno +="erro de relógio#";
                   }              
                }                    
            } catch (Exception e) {
                LogDinnamus.Log(e);
            }            
            
            return cRetorno;
    }

    /**
     * @return the PortaOk
     */
    public static boolean isPortaOk() {
        return PortaOk;
    }

    /**
     * @param PortaOk the PortaOk to set
     */
    public static void setPortaOk(boolean Porta) {
        PortaOk = Porta;
    }

    /**
     * @return the jnaConvECF
     */
    
    
}
