/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tef.Padrao;

import br.com.log.LogDinnamus;
import br.data.DataHora;
import br.manipulararquivos.ManipulacaoArquivo;
import br.valor.formatar.FormatarNumero;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Fernando
 */
public class TEFConteudoArquivo {
       public static String Identificacao = "";
       public static String TEF_Conteudo_Arquivo_Transacao(String Documento, Double Valor){
           return TEF_Conteudo_Arquivo_Transacao(Documento, Valor,"");
       }
       public static String TEF_Conteudo_Arquivo_Transacao(String Documento, Double Valor, String cIdentificacao){
        String cRetorno="";
        try {
             //String cIdentificacao="";
             Calendar c = new GregorianCalendar();
             c.setTime(new Date());
             if(cIdentificacao.equalsIgnoreCase("")){
                cIdentificacao = String.valueOf( c.get(Calendar.SECOND));            
             }
             cRetorno =
            "000-000 = CRT"  + ManipulacaoArquivo.newline +
            "001-000 = " + cIdentificacao +  ManipulacaoArquivo.newline +
            "002-000 = " + Documento +  ManipulacaoArquivo.newline +
            "003-000 = " +  FormatarNumero.FormatarNumero(Valor ,"####.00").replaceAll(",", "") +  ManipulacaoArquivo.newline +
            "999-999 = 0" + ManipulacaoArquivo.newline ;
             Identificacao = cIdentificacao;
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);            
        }
        return cRetorno;
    }
        public static String TEF_Conteudo_Arquivo_VerificarGP(){
        String cRetorno="";
        try {
              Calendar c = new GregorianCalendar();
             c.setTime(new Date());
             String cIdentificacao = String.valueOf( c.get(Calendar.SECOND));
             cRetorno =
            "000-000 = ATV"  + ManipulacaoArquivo.newline +
            "001-000 = " + cIdentificacao +  ManipulacaoArquivo.newline +            
            "999-999 = 0" + ManipulacaoArquivo.newline ;
             
             Identificacao = cIdentificacao;
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);            
        }
        return cRetorno;
        
    }
        public static String TEF_Conteudo_Arquivo_AcionarGP(){
        String cRetorno="";
        try {
              Calendar c = new GregorianCalendar();
             c.setTime(new Date());
             String cIdentificacao = String.valueOf( c.get(Calendar.SECOND));
             cRetorno =
            "000-000 = ADM"  + ManipulacaoArquivo.newline +
            "001-000 = " + cIdentificacao +  ManipulacaoArquivo.newline +            
            "999-999 = 0" + ManipulacaoArquivo.newline ;
             
             Identificacao = cIdentificacao;
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);            
        }
        return cRetorno;
        
    }    
    public static String TEF_ConteudoArquivo_CNF(String NumeroCupom,String Rede, String NSU, String Finalizacao){
        String Retorno ="";
        try {
                Identificacao = String.valueOf(DataHora.Calendario().get(Calendar.SECOND));
                Retorno =
                "000-000 = CNF" + ManipulacaoArquivo.newline+ 
                "001-000 = " + Identificacao + ManipulacaoArquivo.newline+
                (NumeroCupom.length()>0 ? "002-000 = " + NumeroCupom  + ManipulacaoArquivo.newline : ""   )+
                (Rede.length()>0 ? "010-000 = " + Rede + ManipulacaoArquivo.newline : "")+
                (NSU.length()>0 ? "012-000 = " + NSU + ManipulacaoArquivo.newline : "")+
                (Finalizacao.length()>0 ? "027-000 = " + Finalizacao + ManipulacaoArquivo.newline : "") +
                "999-999 = 0" + ManipulacaoArquivo.newline;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
        
        return Retorno;
    }
    public static String TEF_ConteudoArquivo_CNC(String NumeroCupom,String Rede, String NSU, String Finalizacao,String ValorCancelamento,String DataComprovante,String HoraComprovante){
        String Retorno ="";
        try {
                Identificacao = String.valueOf(DataHora.Calendario().get(Calendar.SECOND));
                Retorno =
                "000-000 = CNC" + ManipulacaoArquivo.newline+ 
                "001-000 = " + Identificacao + ManipulacaoArquivo.newline+                
                "003-000 = " + ValorCancelamento + ManipulacaoArquivo.newline+      
                "010-000 = " + Rede + ManipulacaoArquivo.newline +
                "012-000 = " + NSU + ManipulacaoArquivo.newline +
                "022-000 = " + DataComprovante + ManipulacaoArquivo.newline +
                "023-000 = " + HoraComprovante + ManipulacaoArquivo.newline +
                "999-999 = 0" + ManipulacaoArquivo.newline;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
        
        return Retorno;
    }    
    public static String TEF_ConteudoArquivo_NCN(String NumeroCupom,String Rede, String NSU, String Finalizacao){
        String Retorno ="";
        try {
                Identificacao = String.valueOf(DataHora.Calendario().get(Calendar.SECOND));
                Retorno =
                "000-000 = NCN" + ManipulacaoArquivo.newline+ 
                "001-000 = " + Identificacao + ManipulacaoArquivo.newline+
                (NumeroCupom.length()>0 ? "002-000 = " + NumeroCupom  + ManipulacaoArquivo.newline : "" )+
                (Rede.length()>0 ? "010-000 = " + Rede + ManipulacaoArquivo.newline : "")+
                (NSU.length()>0 ? "012-000 = " + NSU + ManipulacaoArquivo.newline : "")+
                (Finalizacao.length()>0 ? "027-000 = " + Finalizacao + ManipulacaoArquivo.newline : "") +
                "999-999 = 0" + ManipulacaoArquivo.newline;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
        
        return Retorno;
    }
    
}
