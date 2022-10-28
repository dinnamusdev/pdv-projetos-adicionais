/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br;

import br.com.log.LogDinnamus;
import br.manipulararquivos.GerarArquivoDados;
import br.manipulararquivos.ManipulacaoArquivo;
import br.manipulararquivos.ManipulacaoArquivoZIPUNZIP;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Fernando
 */
public class GerenciadorLicencaArquivo {
    private static String Diretorio =  ManipulacaoArquivo.DiretorioDeTrabalho() + ManipulacaoArquivo.getSeparadorDiretorio() + "Temp";
    private static String NomeArquivoLicenca ="contrato_#contrato#_";
    private static String NomeArquivoLiberacao ="contrato_#contrato#_liberacao_#liberecao#_";
    public static String GerarArquivoControle(Integer IDContratante, Integer IDContrato){
        String cRetorno="";
        try {
         
            ArrayList<String> arLista = new ArrayList<String>();
                        
            if(!ManipulacaoArquivo.DiretorioExiste(Diretorio)){
                ManipulacaoArquivo.CriarDiretorio(Diretorio);
            }
            
            ArrayList<String> arListaLicenca = GerarArquivoLicenca(IDContratante, IDContrato);
           
            
            String cNomeArquivoZIP=Diretorio +  ManipulacaoArquivo.getSeparadorDiretorio() +"contrato_#contrato#.zip".replace("#contrato#", IDContrato.toString());
                    
            ManipulacaoArquivoZIPUNZIP zIP=new ManipulacaoArquivoZIPUNZIP();
            if(ManipulacaoArquivo.ArquivoExiste(cNomeArquivoZIP, false)){
                ManipulacaoArquivo.ExcluirArquivo(cNomeArquivoZIP);
            }
            if(zIP.CompactarArquivosDinnamuS( cNomeArquivoZIP, arListaLicenca))
            {                   
                if(ManipulacaoArquivo.ExcluirArquivo(arListaLicenca))
                {
                    cRetorno=cNomeArquivoZIP;
                }
            }
            
            zIP.fecharZip();
            ManipulacaoArquivo.ExcluirArquivo(arListaLicenca);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return cRetorno;
    }
    public static ArrayList<String> GerarArquivoLicenca(Integer IDContratante, Integer IDContrato){
        try {
                        
            GerarArquivoDados.Iniciar(Diretorio, NomeArquivoLicenca.replace("#contrato#", IDContrato.toString()));
            
            GerarArquivoDados.ProcessarTabela("LICENCA_DADOS_CONTRATANTE", GerenciadorLicenca.Contratantes_Listar(IDContratante),true);
            
            GerarArquivoDados.ProcessarTabelaEstrutura("LICENCA_DADOS_CONTRATANTE", GerenciadorLicenca.Contratantes_Listar(IDContratante).getMetaData(),true);
            
            GerarArquivoDados.ProcessarTabela("LICENCA_DADOS_CONTRATO", GerenciadorLicenca.Contrato_Listar_Por_ID(IDContrato),true);
            
            GerarArquivoDados.ProcessarTabelaEstrutura("LICENCA_DADOS_CONTRATO", GerenciadorLicenca.Contrato_Listar_Por_ID(IDContrato).getMetaData(),true);
            
            GerarArquivoDados.ProcessarTabela("LICENCA_LIBERACAO", GerenciadorLicencaLiberacao.Liberacoes_Listar(IDContrato),true);
            
            GerarArquivoDados.ProcessarTabelaEstrutura("LICENCA_LIBERACAO", GerenciadorLicencaLiberacao.Liberacoes_Listar(IDContrato).getMetaData(),true);
            
            
            return GerarArquivoDados.getArquivosCarga();
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return null;
        }
    }
}
