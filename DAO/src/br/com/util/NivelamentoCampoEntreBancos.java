/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.util;


import br.com.log.LogDinnamus;
import br.com.servidor.Dao_Jdbc_1;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dti
 */
public class NivelamentoCampoEntreBancos {

        private static ResultSet rsNivelamento=null;

        public static ResultSet BuscarTipoCampoBancoOff(String cNomeCampoOn)
        {
            String cRet="";
            rsNivelamento= Dao_Jdbc_1.getConexao().GerarResultSet("select TipoCampoJavaDB,UsarTamanho from off_nivelamento where TipoCampoSQL like '"+  cNomeCampoOn  +"%'");
            try {
                if (!rsNivelamento.next()) {
                    rsNivelamento=null;
                }
            } catch (SQLException ex) {
                LogDinnamus.Log(ex);
            }
            return rsNivelamento;
        }
        public static String Listar_TipoCamposExcecao(String cNomeTabela, String cNomeCampo)
    {

        String cTipoCampoExcecao="";
        ResultSet rsCamposTabela=null;
        try {

            String cQuery="select TipoCampoOff from off_ExcessaoCampos where nometabela='"+ cNomeTabela +"' and nomecampo='"+ cNomeCampo +"'";

            rsCamposTabela=Dao_Jdbc_1.getConexao().GerarResultSet(cQuery,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            if(rsCamposTabela.next())
            {
                cTipoCampoExcecao=rsCamposTabela.getString("TipoCampoOff");
            }

        } catch (Exception exception) {

             LogDinnamus.Log(exception);

        }
        return cTipoCampoExcecao;

    }

    /**
     * @return the rsNivelamento
     */
    
        //ManipulacaoArquivo.LerArquivo(cNomeArquivoNivelamento, true)
}
