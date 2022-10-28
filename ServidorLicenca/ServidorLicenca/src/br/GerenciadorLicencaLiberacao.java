/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br;


import br.com.log.LogDinnamus;
import br.com.servidor.Dao_Jdbc;
import br.com.util.NamedParameter;
import br.data.DataHora;
import br.data.DataOperacoes;
import br.seguranca.criptografia;
import java.sql.ResultSet;
import java.util.Date;

/**
 *
 * @author Fernando
 */
public class GerenciadorLicencaLiberacao {
    public static ResultSet Liberacoes_Listar(int IDContrato){
        return Liberacoes_Listar(IDContrato,0);
    }
    
    public static ResultSet Liberacoes_RetornarLiberacaoAtual(int IDContrato){
        ResultSet rs =null;
        try {
            rs = Dao_Jdbc.getConexao().GerarResultSet("select top 1 * from LICENCA_LIBERACAO where enviado = 0 and idcontrato="+ IDContrato + " order by idliberacao", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return rs;
    
    }
    public static ResultSet Liberacoes_Listar(int IDContrato, int IDLiberacao){
         ResultSet rs =null;
         try {
             rs = Dao_Jdbc.getConexao().GerarResultSet("select * from LICENCA_LIBERACAO where idcontrato="+ IDContrato + ( IDLiberacao>0 ? " and idliberacao="+ IDLiberacao : "" ), ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
           
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
           
         }
         return rs;
     
     }
        public static boolean Liberacoes_Deletar(int IDContrato){
            try {
                
                String cSQL = "delete LICENCA_LIBERACAO where idcontrato="+ IDContrato ;
                
                if(Dao_Jdbc.getConexao().ExecutarSQL(cSQL)){
                    return true;
                }
                
            } catch (Exception e) {
                LogDinnamus.Log(e, true);                
            }
            return false;
        }
    public static boolean Liberacoes_Gerar(int IDContrato, Date PrimeiroVencimento, Date InicioContrato ,  Date TerminoContrato, int PeriodicidadeLiberacao, int nPrazoExpirar){
        try {
            Date DataExpirar = new Date();
            String cSQL = "INSERT INTO LICENCA_LIBERACAO" +
                      "(IDLIBERACAO, IDCONTRATO, DATA_EXPIRAR, LIBERACAO, ENVIADO)" +
                    " values (:IDLIBERACAO, :IDCONTRATO, :DATA_EXPIRAR, :LIBERACAO, :ENVIADO)";
            
            NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), cSQL);
            
            int MesesEntreData = DataOperacoes.MesesEntreData(InicioContrato, TerminoContrato);
            String cLiberacao = "";
            
            PrimeiroVencimento = DataOperacoes.SomarDia(PrimeiroVencimento, nPrazoExpirar);
            
            for (int i = PeriodicidadeLiberacao; i < MesesEntreData; i=i+PeriodicidadeLiberacao) {

                DataExpirar = DataOperacoes.SomarMes(PrimeiroVencimento, i);
                Integer nPK = Dao_Jdbc.getConexao().NovoValorIdentidade("LICENCA_LIBERACAO", 0, 0, false).intValue();
                np.setInt("IDLIBERACAO",nPK);
                np.setInt("IDCONTRATO", IDContrato);                
                //Date DataExpirar = new java.sql.Date(DataExpirar.getTime());
                String key =  DataHora.getHora("ddMMyy", DataExpirar);
                np.setDate("DATA_EXPIRAR", new java.sql.Date(DataExpirar.getTime()));
                cLiberacao = criptografia.EncriptRC4( nPK.toString() + key, String.valueOf(nPK));
                np.setString("LIBERACAO",cLiberacao);
                np.setInt("ENVIADO",0);
                np.execute();
                //DataExpirar = DataOperacoes.SomarMes(DataExpirar, i);
            }
            
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
}
