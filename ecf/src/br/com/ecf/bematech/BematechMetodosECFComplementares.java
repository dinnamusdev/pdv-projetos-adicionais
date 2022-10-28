/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ecf.bematech;

import bemajava.Bematech;
import br.String.ManipulacaoString;
import br.com.ecf.CarregarDLL;
import br.com.log.LogDinnamus;
import br.com.repositorio.DAO_RepositorioLocal;
import br.data.formatar.DataFormatar;
import br.valor.formatar.FormatarNumero;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

/**
 *
 * @author Fernando
 */
public class BematechMetodosECFComplementares  {
    
    
    
     
     
     
     public static TreeMap<String,Float> TratarStringAliquotas(String cAliq){
          TreeMap<String,Float> aliq = new TreeMap<String, Float>();
          try 
          {
                String[] PercAliq = cAliq.split(",");
                
                for (Integer i = 1; i <= PercAliq.length; i++) {
                    String Aliquota = FormatarNumero.FormatarStringDeMoeda_EmMoeda(PercAliq[i-1]);
                    if(!Aliquota.equalsIgnoreCase("0.00")){
                        aliq.put(i.toString(), new Float(Aliquota));
                    }
                }
                
          } catch (Exception e) {
              LogDinnamus.Log(e, true);
          }
          return aliq;
      }
      public static boolean ConsultarDiaAberto(){
        try {
            ResultSet rsDadosEstacao = DAO_RepositorioLocal.GerarResultSet("select diaaberto from off_configuracaoestacao where diaaberto='"+ DataECF() +"'");
            if(rsDadosEstacao.next()){
               return true;                    
            } 
        } catch (Exception e) {
            LogDinnamus.Log(e, true);           
        }
        return false;
    }
    public static String CarregarMeiosPagamento(){
        String Retorno ="";
         try {
             bemajava.BemaString bs = new bemajava.BemaString();
             if(Bematech.VerificaFormasPagamentoMFD(bs)==1){
                 String MeiosPagto = bs.getBuffer();
                 if(MeiosPagto.length()>0){
                     String[] Meios = MeiosPagto.split(",");
                     String Forma ="";
                     for (int i = 0; i < Meios.length; i++) {
                         Forma = Meios[i].substring(0, 15).trim();
                         if(Forma.length()>0){
                            Retorno+= (Retorno.length()>0 ? "," : "") + Forma;
                         }
                     }
                 }
             }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public static int AbrirMovimento(){
         int nRet =0;
        try {
            nRet = bemajava.Bematech.AberturaDoDia("0,00", "DINHEIRO");                           
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return nRet;
    }
    public static String DataECF(){
        String cData="";
        try {            
            bemajava.BemaString Data = new bemajava.BemaString();
            bemajava.BemaString Hora = new bemajava.BemaString();            
            Bematech.DataHoraImpressora(Data, Hora);
            cData = Data.getBuffer();
                      
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return cData;
    }
    public static String MensagemStatusECF(){
        String Retorno="";
        try {
            
            
            
        } catch (Exception e) { 
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public static String ZJaEmitida(){
        String cRetorno ="";
        try {
            bemajava.BemaString DadosUltimaReducao = new bemajava.BemaString();
            if(Bematech.DadosUltimaReducao(DadosUltimaReducao)==1){               
               String DataMovimentoVinculadoAZ =   DadosUltimaReducao.getBuffer().split(",")[13];
               String DataECF = DataECF();
               if( DataMovimentoVinculadoAZ.equalsIgnoreCase(DataECF) ){
                  bemajava.BemaString DataUltimaZ  = new bemajava.BemaString();
                  bemajava.BemaString HoraUltimaZ  = new bemajava.BemaString();
                  if(bemajava.Bematech.DataHoraReducao(DataUltimaZ, HoraUltimaZ)==1){
                     String DataFormatada =  DataFormatar.Formatar_String_DDMMYY_Em_String_DD_MM_YY(DataUltimaZ.getBuffer());
                     String HoraFormatada =  DataFormatar.Formatar_String_HHMMSS_Em_String_HH_MM_SS(HoraUltimaZ.getBuffer());
                     cRetorno="ATENÇÃO --> REDUCAO Z EMITIDA EM [ "+  DataFormatada + " AS " + HoraFormatada  +" ] #"; 
                  }
               }
            }            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return cRetorno;
    }
    public static ArrayList<String> VerificaEstadoECF(boolean VerificacaoInicial){
        ArrayList<String> Retorno = new ArrayList<String>();
        try {
             //Retorno = StatusECF()
                bemajava.BemaInteger ack = new bemajava.BemaInteger();
                bemajava.BemaInteger st1 = new bemajava.BemaInteger();
                bemajava.BemaInteger st2 = new bemajava.BemaInteger();
                bemajava.BemaInteger st3 = new bemajava.BemaInteger();
                System.out.println("VerificaEstadoECF 1");
                bemajava.Bematech.HabilitaDesabilitaRetornoEstendidoMFD("1");
                bemajava.Bematech.VerificaEstadoImpressoraMFD(ack, st1,st2,st3);
                int nRet = st1.getNumber();
                System.out.println("VerificaEstadoECF 2");
                if(nRet>=128){
                   Retorno.add("ATENÇAO --> IMPRESSORA SEM PAPEL#");
                   nRet=nRet-128;
                }
                if(nRet>=64){
                   Retorno.add("ATENÇAO --> IMPRESSORA COM POUCO PAPEL#");
                   nRet=nRet-64;
                }
                if(nRet>=32){
                   Retorno.add("ATENÇAO --> ERRO NO RELOGIO#");
                   nRet=nRet-32;
                }
                if(nRet>=16){
                   Retorno.add("ATENÇAO --> IMPRESSORA EM ERRO#");
                   nRet=nRet-16;
                }
                if(nRet>=8){
                   Retorno.add("PRIMEIRO DADO DE CMD NA FOI ESC");
                   nRet=nRet-8;
                }
                if(nRet>=4){
                   Retorno.add("COMANDO INEXISTENTE");
                   nRet=nRet-4;
                }
                if(nRet>=2){
                   Retorno.add("CUPOM FISCAL ABERTO" /*+ ( VerificacaoInicial  ? "#" : "")*/);
                   nRet=nRet-2;
                }
                if(nRet>=1){
                   Retorno.add("NUMERO DE PARAMETRO DE CMD INVÁLIDO");
                   nRet=nRet-1;
                }
                boolean bDiaAberto =true;
                        
                if(ZPendente()){
                   String cDataMovimento = DataMovimento();
                   Retorno.add("ATENÇAO --> REDUCAO Z PENDENTE DO DIA [ "+ cDataMovimento +" ]#");
                   bDiaAberto =false;
                }
                String ZJaEmitida = ZJaEmitida();
                
                if(ZJaEmitida.trim().length()>0){
                    Retorno.add(ZJaEmitida);
                    bDiaAberto =false;
                }
                
                if(ConsultarDiaAberto()){
                    Retorno.add("DIA FISCAL ABERTO");
                }else{
                    Retorno.add("DIA FISCAL FECHADO#"); 
                }
                System.out.println("VerificaEstadoECF 3");
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public static ArrayList<String> StatusECF_2(){
        ArrayList<String> arRet = new ArrayList<String>();
        try {
            ArrayList<bemajava.BemaInteger> ret_ultimocomando = RetornoImpressoraUltimoComando();
            if(ret_ultimocomando.size()>0){
                //st1
                int nRet =  ret_ultimocomando.get(0).number;
                
                if (nRet >=128){   
                   arRet.add("MEMORIA FISCAL SEM ESPACAO"); 
                  nRet = nRet -128;
                }            
                if (nRet >=64){
                   arRet.add("NAO UTILIZADO");  
                   nRet = nRet -64;
                }
                if (nRet >=32){
                   arRet.add("PERMITE CANCELAR CUPOM FISCAL");  
                   nRet = nRet -32;
                }
                if (nRet >=16){
                    arRet.add("NAO UTILIZADO");
                   nRet = nRet -16;
                }
                if (nRet >=8){
                   arRet.add("JA HOUVE REDUCAO Z NO DIA");  
                   nRet = nRet -8;
                }

                if (nRet >=4){
                   arRet.add("HORARIO DE VERAO SELECIONADO");  
                   nRet = nRet -4;
                }

                if (nRet >=2){
                   arRet.add("FECHAMENTO DE FORMA DE PAGTO INICIADO");  
                   nRet = nRet -2;
                }
                if (nRet >=1){
                   arRet.add("CUPOM FISCAL ABERTO");  
                   nRet = nRet -1;
                }
                
            }            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
           
        }
        return arRet;
    }
    public static boolean ZPendente(){
        try {
            
            bemajava.BemaString bs = new bemajava.BemaString();
             int nret = Bematech.VerificaZPendente(bs);
             if(nret==1){
                 if(bs.getBuffer().trim().equalsIgnoreCase("1")){
                     return true;
                 }
             }            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);            
        }
        return false;
    }
    public static String DataMovimento(){
        String DataHora ="";
        try {
            
            bemajava.BemaString bsDataMovimento = new bemajava.BemaString();
            
            if(Bematech.DataMovimento(bsDataMovimento)==1){
                DataHora = DataFormatar.Formatar_Data_Em_String_DDMMYY(DataFormatar.Formatar_String_DDMMYY_Em_Data_2(bsDataMovimento.getBuffer()));
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return DataHora;
    }
    public static ArrayList<bemajava.BemaInteger> RetornoImpressoraUltimoComando(){
        //String Retorno = "";
        ArrayList<bemajava.BemaInteger> Retorno = new ArrayList<bemajava.BemaInteger>();
        try {                
                bemajava.BemaInteger bi= new bemajava.BemaInteger();
                bemajava.BemaInteger bi1=  new bemajava.BemaInteger();
                bemajava.BemaInteger bi2=  new bemajava.BemaInteger();
                //bemajava.BemaInteger bi3= new bemajava.BemaInteger();
                bemajava.BemaInteger fLAG_fISCAL= new bemajava.BemaInteger();
                bemajava.Bematech.FlagsFiscais(fLAG_fISCAL);
                //int nRetorno = bemajava.Bematech.VerificaEstadoImpressora(bi, bi1, bi2);
                //if(nRetorno==1){
                    Retorno.add(fLAG_fISCAL);
                    //Retorno.add(bi1);
                  //  Retorno.add(bi2);
                //}                
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public static int CupomFiscalAberto()
    {
        int nRet=0;
        try {
             ArrayList<String> arRet= StatusECF_2();
             if(arRet.contains("CUPOM FISCAL ABERTO")){
                nRet=1;
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return nRet;
    }
    
    public static ArrayList<String>  StatusECF_Estendido(){
       ArrayList<String> arRet= new ArrayList<String>();
               
        try {
            bemajava.BemaInteger bemaInteger = new bemajava.BemaInteger();            
            int FlagsFiscais = bemajava.Bematech.StatusEstendidoMFD(bemaInteger);
            int nRet = bemaInteger.number;
            if (nRet >=128){                
               nRet = nRet -128;
            }
            
            if (nRet >=64){
               arRet.add("Estorno de Comprovante de Débito ou Crédito permitido");  
               nRet = nRet -64;
            }
            if (nRet >=32){
               arRet.add("Permite cancelamento do CNF");  
               nRet = nRet -32;
            }
            if (nRet >=16){
                
               nRet = nRet -16;
            }
            if (nRet >=8){
               arRet.add("Totalizando Cupom");  
               nRet = nRet -8;
            }
            
            if (nRet >=4){
               arRet.add("Relatório Gerencial Aberto(RG)");  
               nRet = nRet -4;
            }
            
            if (nRet >=2){
               arRet.add("Comprovante de Débito ou Crédito aberto(CDC)");  
               nRet = nRet -2;
            }
            if (nRet >=1){
               arRet.add("Comprovante Não-Fiscal Aberto(CNFV)");  
               nRet = nRet -1;
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
               
        return arRet;
    
    }
        
    public static ArrayList<String>  StatusECF()
    {
    
        ArrayList<String> arRet= new ArrayList<String>();
        
        try {
            //int bemaInteger = 0;
            bemajava.BemaInteger bemaInteger = new bemajava.BemaInteger();
            int FlagsFiscais = bemajava.Bematech.FlagsFiscais(bemaInteger);
            int nRet = bemaInteger.getNumber();
            if (nRet >=128){
               arRet.add("Memória fiscal sem espaço");  
               nRet = nRet -128;
            }
            
            if (nRet >=64){
               arRet.add("NAO UTILIZADO");  
               nRet = nRet -64;
            }
            if (nRet >=32){
               arRet.add("Permite cancelar cupom fiscal");  
               nRet = nRet -32;
            }
            if (nRet >=16){
               arRet.add("NAO UTILIZADO");  
               nRet = nRet -16;
            }
            if (nRet >=8){
               arRet.add("Já houve redução Z no dia");  
               nRet = nRet -8;
            }
            
            if (nRet >=4){
               arRet.add("Horário de verão selecionado");  
               nRet = nRet -4;
            }
            
            if (nRet >=2){
               arRet.add("Fechamento de formas de pagamento iniciado");  
               nRet = nRet -2;
            }
            if (nRet >=1){
               arRet.add("Cupom fiscal aberto");  
               nRet = nRet -1;
            }
            
            
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return arRet;
 
        
       
        
    }
    
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
             
             
             bemajava.BemaString mfd = new bemajava.BemaString();
             if(bemajava.Bematech.NumeroSerieMFD(mfd)==1){
                 ret = mfd.getBuffer();
             }
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
     }
    
    public static String DadosDoECF_CNPJ(){
         String  ret = new String();         
         try {
                          
             bemajava.BemaString  cnpj =new  bemajava.BemaString();
             if(Bematech.CNPJMFD(cnpj)==1){
                ret =ManipulacaoString.DeixarSomenteNumeros(cnpj.getBuffer());
             }
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
     }
    public static HashMap<String,String> DadosDoECF_MarcaModeloTipo(){
         HashMap<String,String>  ret = new HashMap<String, String>();         
         try {
             
             bemajava.BemaString marca = new bemajava.BemaString();                     
             bemajava.BemaString modelo = new bemajava.BemaString();
             bemajava.BemaString tipo= new bemajava.BemaString();
             if(Bematech.MarcaModeloTipoImpressoraMFD(marca, modelo, tipo)==1){
                 ret.put("marca",marca.getBuffer());
                 ret.put("modelo", modelo.getBuffer());
                 ret.put("tipo", tipo.getBuffer());                 
             }             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
     }
    
    
}
