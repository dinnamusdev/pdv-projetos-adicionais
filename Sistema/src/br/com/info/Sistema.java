/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.info;


import br.com.ecf.ECFDinnamuS;
import br.com.generica.Dao_Generica;
import br.com.log.LogDinnamus;
import br.com.repositorio.DAO_RepositorioLocal;
import br.com.servidor.Dao_Jdbc_1;
import br.infraestrurura.inicializacao.VerificarArquivoCFG;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author dti
 */
public class Sistema {
    static private String dtPrimeiroSinc="01/01/2001 00:00:00";
    static private boolean bOnline=false;
    static private Timestamp dDataUltimoSinc=null;
    static private Integer nLojaAtual=0;
    static private boolean InicializacaoDoPdv;
    static private int Filial =0;
    static public ECFDinnamuS ecf=null;
    static private boolean ForcaOFFLine = false;
    static boolean arquivoCFGVerificado=false;
    
    public static String emitenteAvulsoCNPJ(){
        String ret=null;
        
        if(!arquivoCFGVerificado){
           arquivoCFGVerificado = VerificarArquivoCFG.Executar();
        }
        if(VerificarArquivoCFG.getHmServidores().get("Servidor0").containsKey("EmitenteAvulsoCNPJ")){
           ret =  VerificarArquivoCFG.getHmServidores().get("Servidor0").get("EmitenteAvulsoCNPJ");
        }
        
        return ret;
    }
    
    public static int emitenteavulso_fabricantes(Long CodigoVenda, String cnpjAvulso) throws SQLException{
        Integer ret=0;
        StringBuilder sql = new StringBuilder();
        
        sql
         .append("select  count(cp.fabricante) ")
	.append("from itensorc io, itensgradeproduto ig, cadproduto cp ")
	.append("where cp.chaveunica=ig.codigo and ig.chaveunica = io.codprod ")
		.append("and io.codigo=")
                .append(CodigoVenda.toString())
	.append(" and not cp.fabricante in (select fabricante from emitenteavulso_fabricantes where cnpjEmitenteavulso ='")
        .append(cnpjAvulso) 
        .append("')");
        
        ResultSet GerarResultSet = DAO_RepositorioLocal.GerarResultSet(sql.toString());
        if(GerarResultSet!=null && GerarResultSet.next()){
            ret = GerarResultSet.getInt(1);
        }        
        return ret;        
                
    }
    public static String emitenteavulso_stmapeamento(String st) throws SQLException{
        String ret=null;
        
        String sql ="select " +
                    (st.length()==2 ? "simples" : "normal") +
                    " from emitenteavulso_stmapeamento where " +
                    (st.length()==2 ? "normal" : "simples"  ) + "='" + st  + "'" ;
        
        ResultSet result=Dao_Generica.Pesquisar(sql,false);

        if(result!=null && result.next()){
            ret = result.getString(1);
        }
        
        return ret;
    }
    
    public static Integer emitenteavulso_regimetributario(String cnpj) throws SQLException{
        Integer ret=null;
        
        String sql ="select regimetributario from emitenteavulso where cnpj='"+ cnpj +"'" ;
                            
        ResultSet result=Dao_Generica.Pesquisar(sql,false);

        if(result!=null && result.next()){
            ret = result.getInt(1);
        }
        
        return ret;
    }
    
    public static ResultSet Caixa(){
        ResultSet rs = null;
        try {
            rs = DAO_RepositorioLocal.GerarResultSet("select cx.* from off_configuracaoestacao cfg inner join caixas cx on cfg.caixavinculadoaopdv=cx.codigo");
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return rs;
    }
    
     public static void main(String[] args){
         //DataServidorSQL();
    }
 
 public static Date DataServidorSQL(){
         
        Date d = null;
        try {
            if(Sistema.isOnline()){
                //ResultSet rsInfoServidor=  Dao_Generica.Pesquisar("select getdate() Data", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, true);
                ResultSet rsInfoServidor = Dao_Jdbc_1.getConexao().GerarResultSet("select getdate() Data", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                if(rsInfoServidor.next()){
                   d = new Date(rsInfoServidor.getDate("Data").getTime());
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return d;
    
    }    
    public static int ClientePadrao(){
        int nCodigo=0;
        try {

            ResultSet rsDadosLojaAtual   = Sistema.getDadosLoja(Sistema.getLojaAtual(),true);
            if(rsDadosLojaAtual!=null){
                int nCodigoClientePadrao =0;
                nCodigoClientePadrao =rsDadosLojaAtual.getInt("ClientePadrao");
            }

        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return nCodigo;
    }
    public static int getCodigoLojaMatriz(){
        return getCodigoLojaMatriz(0);
    }
    public static int getCodigoLojaMatriz(int nCodigoLoja )
    {
        int nCodigoLojaMatriz=0;
        ResultSet rs =getDadosLoja((nCodigoLoja==0 ? nLojaAtual : nCodigoLoja),true);
        try {
            if(rs!=null)
            {
                if (rs.getInt("CodigoLojaMatriz") > 0) {
                    nCodigoLojaMatriz = rs.getInt("CodigoLojaMatriz");
                } else {
                    nCodigoLojaMatriz = (nLojaAtual==0 ? nCodigoLoja : nLojaAtual);
                }
            }
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);
        }

        return nCodigoLojaMatriz;
    }
    public static String getDtPrimeiroSinc() {
        return dtPrimeiroSinc;
    }
    public static Integer getLojaAtual() {
        return nLojaAtual;
    }
    
    public static void setNLojaAtual(Integer aNLojaAtual) {
        nLojaAtual = aNLojaAtual;
    }

    public static Timestamp getDataUltimoSinc() {
        return dDataUltimoSinc;
    }

    public static void setDataUltimoSinc(Timestamp aDDataUltimoSinc) {
        dDataUltimoSinc = aDDataUltimoSinc;
    }

    public static boolean isOnline() {
        return bOnline;
    }


   
    public static void setOnline(boolean aBOnline) {
        bOnline = aBOnline;
        
    }
    
     public static boolean isForcaOFFLine() {
        return ForcaOFFLine;
    }


    public static void setForcaOFFLine(boolean aForcaOFFLine) {
        ForcaOFFLine = aForcaOFFLine;
        
    }


    /**
     * @return the cNomeLoja
     */
    public static ResultSet getClientePadrao(int nCodigoLoja, int nCodigoCliente )
    {
        ResultSet rs = Dao_Generica.Pesquisar("select codigo,nome from clientes where loja in (" + getLojasDaRede(nCodigoLoja) + ")  and codigo="  + nCodigoCliente,Sistema.isOnline());
        try {
            if (!rs.next()) {
                rs = null;
            }
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);
        }

        return rs;
    }
    public static ResultSet getRsLojasDaRede(int nCodigoLoja){
        ResultSet rs=null;
        try {
            String sql="select codigo,nome " +
            "from lojas " +
            "where codigolojamatriz in ( select l1.codigolojamatriz from lojas l1 where l1.codigo="+ nCodigoLoja + ") or " +
            "      codigo in" +
            "        ( " +
            "         select l1.codigolojamatriz from lojas l1 where l1.codigo="+ nCodigoLoja +
            "        ) "+
            "union " +
            "select l3.codigo, l3.nome from lojas l3 where l3.codigo="+ nCodigoLoja + " or l3.codigolojamatriz="+ nCodigoLoja ;
            rs = Dao_Generica.Pesquisar(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,Sistema.isOnline());
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return rs;
    }
    public static String getLojasDaRede(int nCodigoLoja)
    {
        ResultSet rs=null;

        String cRetorno="";
        String sql="select CODIGO " +
        "from lojas " +
        "where codigolojamatriz in ( select l1.codigolojamatriz from lojas l1 where l1.codigo="+ nCodigoLoja + ") or " +
        "      codigo in" +
        "        ( " +
        "         select l1.codigolojamatriz from lojas l1 where l1.codigo="+ nCodigoLoja +
        "        ) "+
        "union " +
        "select l3.codigo from lojas l3 where l3.codigo="+ nCodigoLoja + " or l3.codigolojamatriz="+ nCodigoLoja ;


        rs = Dao_Generica.Pesquisar(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,Sistema.isOnline());
        try {
            while (rs.next()) {
                cRetorno = cRetorno +  rs.getInt("Codigo") + (!rs.isLast() ? "," : "");
            }
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);
        }

        return cRetorno;

    }
    public static ResultSet getDadosLojaAtualSistema() {
        return getDadosLojaAtualSistema(false);
    }
    public static ResultSet getDadosLojaAtualSistema(boolean online) {
        ResultSet result=null;
        if(nLojaAtual>0)
        {
            String sql="select * from lojas where codigo=" + nLojaAtual;
            result=Dao_Generica.Pesquisar(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY, online);
            try {
                if(!result.next()){
                    result=null;
                }

            } catch (SQLException ex) {
                 result=null;
                 LogDinnamus.Log(ex);
            }
        }
        return result;
    }
    public static ResultSet getDadosFilialLoja(int nCodigoLoja)
    {
        ResultSet rs=null;
        try {
            String sql="select * from filial " + (nCodigoLoja >0 ? " where codigoloja=" + nCodigoLoja : "") ;
            rs = Dao_Generica.Pesquisar(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY,Sistema.isOnline());
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return rs;

    }
    public static ResultSet getDadosLoja(int nCodigo)
    {
        
         return getDadosLoja (nCodigo,false);
    }
    public static ResultSet getDadosLoja(int nCodigo, boolean bPosiciona) {
        ResultSet result=null;
        String emitenteAvulsoCNPJ = emitenteAvulsoCNPJ();
        String sql="";
        if(emitenteAvulsoCNPJ==null){
            sql="select * from lojas" + (nCodigo>0 ? " where codigo=" + nCodigo : "");
        }else{
            sql="select * from emitenteavulso" + (nCodigo>0 ? " where codigo=" + nCodigo : "");
        }

        result=Dao_Generica.Pesquisar(sql,false);

        try {
            if(result!=null)
            {
                if(bPosiciona)
                    if(!result.next())
                        result=null;
            }
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);
        }

        return result;
    }

    /**
     * @return the InicializacaoDoPdv
     */
    public static boolean isInicializacaoDoPdv() {
        return InicializacaoDoPdv;
    }

    /**
     * @param aInicializacaoDoPdv the InicializacaoDoPdv to set
     */
    public static void setInicializacaoDoPdv(boolean aInicializacaoDoPdv) {
        InicializacaoDoPdv = aInicializacaoDoPdv;
    }

    /**
     * @return the Filial
     */
    public static int CodigoDaFilial_LojaAtual(){
            ResultSet rsDadosFilial =  Sistema.getDadosFilialLoja(Sistema.getLojaAtual());
        try {
            if(rsDadosFilial.next()){
                return rsDadosFilial.getByte("codigofilial");
            }else{
                return 0;
            }
        } catch (SQLException ex) {
            LogDinnamus.Log(ex, true);
            return 0;
        }
            
            
    }
    public static int getFilial() {

        return Filial;
    }

    /**
     * @param aFilial the Filial to set
     */
    public static void setFilial(int aFilial) {
        Filial = aFilial;
    }

    /**
     * @return the ecf
     */
    public static ECFDinnamuS getEcf() {
        return ecf;
    }

    /**
     * @param aEcf the ecf to set
     */
    public static void setEcf(ECFDinnamuS aEcf) {
        ecf = aEcf;
    }
    

    /**
     * @return the Mensagens
     */



}
