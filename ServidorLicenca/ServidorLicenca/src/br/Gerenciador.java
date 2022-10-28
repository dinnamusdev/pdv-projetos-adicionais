/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br;

import br.com.log.LogDinnamus;
import br.com.servidor.Dao_Jdbc;
import br.com.ui.MetodosUI_Auxiliares;
import br.infraestrurura.inicializacao.CriarArquivoCFG;
import br.infraestrurura.inicializacao.VerificarArquivoCFG;
import br.manipulararquivos.ManipulacaoArquivo;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import ui.frmGerenciador;

/**
 *
 * @author Fernando
 */
public class Gerenciador {
    private static Map<String,Map<String,String>> hmServidores=new HashMap<String, Map<String, String>>();
    
    public static void main(String[] args) {
        
        LogDinnamus.Iniciar();
        
        try {
            if(!Iniciar()){
                System.exit(0);
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
    }
    
    public static boolean Iniciar(){
        try {
            
            String cPastaCFG = ManipulacaoArquivo.DiretorioDeTrabalho() + ManipulacaoArquivo.getSeparadorDiretorio() + "cfg_gerenciador";
            String cNomeArquivoCFG = cPastaCFG  + ManipulacaoArquivo.getSeparadorDiretorio() +"dinnamus.cfg.xml";
            if(!ManipulacaoArquivo.DiretorioExiste(cPastaCFG)){
                if(!ManipulacaoArquivo.CriarDiretorio(cPastaCFG)){
                    return false;
                }
            }
            if(!ManipulacaoArquivo.ArquivoExiste(cNomeArquivoCFG, false)){
               hmServidores = CriarArquivoCFG.AbrirInterfaceComUsuario();
               if(hmServidores==null){
                   return false;
               }else{
                   if(!CriarArquivoCFG.Criar(null,hmServidores, "cfg_gerenciador",  "dinnamus.cfg.xml")){
                       return false;
                   }
               }
            }
            
            if(VerificarArquivoCFG.VerificarXMLCFG(cNomeArquivoCFG)){
                hmServidores=VerificarArquivoCFG.getHmServidores();
            }else{
                return false;
            }
         
            if (Dao_Jdbc.getConexao().AbrirCnx(hmServidores.get("Servidor0"),true)){
                new frmGerenciador().setVisible(true);
            }else{
                MetodosUI_Auxiliares.MensagemAoUsuarioSimplesAVISO(null, "NÃO FOI POSSÍVEL CONECTAR O HOST " + hmServidores.get("Servidor0").get("Host") , "GERENCIADOR FINANCEIRO DINNAMUS");
            }
            
            return true;
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return false;
    }
}
