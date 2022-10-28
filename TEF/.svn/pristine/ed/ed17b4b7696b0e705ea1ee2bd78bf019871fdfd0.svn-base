/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tef.Padrao;

import br.com.log.LogDinnamus;
import br.comum.MetodosComuns;
import br.manipulararquivos.ManipulacaoArquivo;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class TEFValidarRetorno {
    
    public static String VerificarArquivo_ATIVO_001(){
        String Retorno ="";
        try {
            String NomeArquivoAtivo001 = TefPadrao.getPastaPadrao_Resp() + "ativo.001";
             
            String cConteudoArquivo = ManipulacaoArquivo.LerArquivo(NomeArquivoAtivo001, false);
             
            ArrayList<String> Linhas = MetodosComuns.ExtrairDados("016",cConteudoArquivo);
            if(Linhas.size()>0){
               Retorno = Linhas.get(0);//.substring(Linhas.get(0).indexOf("=")+1).trim();
               
               Retorno = Retorno.substring(2,4) + "/" +  Retorno.substring(0,2) +" " + 
                       Retorno.substring(4).substring(0,2)+":"+
                       Retorno.substring(4).substring(2,4) + ":" +
                       Retorno.substring(4).substring(4,6) 
                       ;
            }else{
                Retorno="ARQUIVO ATIVO.001 NÃO DISPONÍVEL PARA ESTE TEF";
            }           
             
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return Retorno;
    }
    public static boolean VerificarIdentificacaoDoArquivo(String NomeArquivo, String Identificacao){
        boolean bRetorno =false;
        try {
            
            //String NomeArquivoCompleto =  NomeArquivo;
            
            String cConteudoArquivo = ManipulacaoArquivo.LerArquivo(NomeArquivo, false);
            
            if(cConteudoArquivo.length()>0){
                if(cConteudoArquivo.indexOf(Identificacao)>=0){
                    bRetorno=true;
                }
            }            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return bRetorno;
               
    }
    
}
