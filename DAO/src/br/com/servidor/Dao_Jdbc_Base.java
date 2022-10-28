/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.servidor;


import br.com.util.DAO_Parametro_Generico;
import br.com.log.LogDinnamus;
import br.com.util.NamedParameter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dti
 */
public class Dao_Jdbc_Base {
    private  Savepoint svpt1=null;
    private  Connection cnx;
    private  Statement stm ;
    private  String Erro ="";
    private Map<String,String> hmServidor;
    public String getErro(){
        return Erro;
    }
    public  Connection getCNX()
    {
        //if(cnx==null)
        //   AbrirCnx();
        return cnx;
    }
    public  boolean TestarConexao()
    {
        try {
            if(cnx!=null){
                getCNX().prepareStatement("select 1").execute();
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            return false;
        }
    }
    public  ResultSet GerarResultSet(String cString,DAO_Parametro_Generico<?>[] dpgParametros )
    {

        ResultSet rs = null;
        try {

            PreparedStatement pstmt =getCNX().prepareStatement(cString,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            for (int i = 0; i < dpgParametros.length; i++) {
                 pstmt.setString(i+1, dpgParametros[i].getGValorParamentro().toString().toUpperCase());
            }
            rs=pstmt.executeQuery();

        } catch (SQLException ex) {

            LogDinnamus.Log(ex);
        }

        return rs;

    }    
    public  ResultSet ListaCamposTabela(String cNomeTabela){
        return ListaCamposTabela(cNomeTabela,"");
    }
    
    
    public  boolean TabelaExiste(String cNomeTabela)
    {

        DAO_Parametro_Generico<?>[] dpgParametros=null;
        ResultSet rsCamposTabela=null;
        try {

            String cQuery="select * " +
                         "from sysobjects so " +
                         "where so.name='" + cNomeTabela + "'";
                     

            rsCamposTabela=GerarResultSet(cQuery);

            if(rsCamposTabela.next()){
                return true;
            }
            
        } catch (Exception exception) {

             LogDinnamus.Log(exception);

        }
        return false;
    }
    public  ResultSet ListaCamposTabela(String cNomeTabela, String Campo)
    {

        DAO_Parametro_Generico<?>[] dpgParametros=null;
        ResultSet rsCamposTabela=null;
        try {

            String cQuery="select sc.name Columnname, sc.xtype ,st.name columntype, sc.length columnlength  " +
                         "from syscolumns sc " +
                                "inner join sysobjects so " +
                                        "on so.id=sc.id  " +
                                "inner join systypes st " +
                                        "on st.xtype=sc.xtype and st.name<>'sysname' " +
                         "where so.name=?  "+ 
                        (!Campo.equalsIgnoreCase("") ? " and sc.name='"+ Campo +"' " :"") +
                      "order by sc.colorder ";

            dpgParametros=new DAO_Parametro_Generico<?>[1];

            dpgParametros[0]=new DAO_Parametro_Generico<Object>("NomeTabela", cNomeTabela);

            rsCamposTabela=GerarResultSet(cQuery,dpgParametros);

        } catch (Exception exception) {

             LogDinnamus.Log(exception);

        }
        return rsCamposTabela;

    }
    public  ResultSet GerarResultSet(String cStringSQL)
    {
        return GerarResultSet(cStringSQL, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY);

    }
    public  ResultSet GerarResultSet(String cStringSQL, int resultSetType,int resultSetConcurrency )
    {
        return GerarResultSet(cStringSQL,resultSetType,resultSetConcurrency,0);
    }
    public  ResultSet GerarResultSet(String cStringSQL, int resultSetType,int resultSetConcurrency, int nTamanhoPagina )
    {
        return  GerarResultSet(cStringSQL,  resultSetType,resultSetConcurrency, nTamanhoPagina, "",0,"avanço" ,0);
    }
    public  ResultSet GerarResultSet(String cStringSQL, int resultSetType,int resultSetConcurrency, int nTamanhoPagina, String cCampoChavePrimaria, int nValorUltimaChavePrimaria, String cTipoNavegacao, int nPaginaAtual )
    {
        return GerarResultSet( cStringSQL, resultSetType,resultSetConcurrency, nTamanhoPagina,  cCampoChavePrimaria,  nValorUltimaChavePrimaria,  cTipoNavegacao, nPaginaAtual, false );
    }
    
    
     public synchronized  ResultSet GerarResultSetPaginado(String cStringSQL, int pagina )
    {
        ResultSet rs=null;
        try { 
            if(cnx.getAutoCommit()){
                cnx.setAutoCommit(false);
            }
            stm.setFetchSize(pagina);
            
            stm = cnx.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY,ResultSet.HOLD_CURSORS_OVER_COMMIT);
            
           
            //stm.setMaxFieldSize(pagina);
            
            rs = stm.executeQuery(cStringSQL );
        }
        catch (SQLException ex) {
            LogDinnamus.Log(ex);  
        }

        return rs;

    }
    public synchronized  ResultSet GerarResultSet(String cStringSQL, int resultSetType,int resultSetConcurrency, int nTamanhoPagina, String cCampoChavePrimaria, int nValorUltimaChavePrimaria, String cTipoNavegacao, int nPaginaAtual, boolean  bExibirMsgErrorConexao )
    {
        //System.out.print("PASSEI");
        ResultSet rs=null;
        String cStringSQLComplementar=cStringSQL;
        try { 
            
            stm = cnx.createStatement(resultSetType,resultSetConcurrency,ResultSet.HOLD_CURSORS_OVER_COMMIT);
            
             cStringSQL= cStringSQL
                .replace("<from>", "from")
                .replace("<FROM>","FROM")
                .replace("<From>", "From");
            
            //stm.getConnection().
            
            if(nTamanhoPagina>0)
            {
                stm.setMaxRows(nTamanhoPagina);
                if(cStringSQL.toLowerCase().indexOf("order by")<0)
                   cStringSQL=cStringSQL + " ORDER BY " + cCampoChavePrimaria;
                else
                {
                    if(cStringSQL.toLowerCase().indexOf("order by " + cCampoChavePrimaria.toLowerCase())<0)
                       cStringSQL=cStringSQL.toLowerCase().replaceAll("order by", "ORDER BY " + cCampoChavePrimaria +"," );
                }
                cStringSQLComplementar="where " + cCampoChavePrimaria + " not in (select top  " +  ((nPaginaAtual-1)*nTamanhoPagina) +" " + cCampoChavePrimaria +"  from " + cStringSQL.substring(cStringSQL.toLowerCase().indexOf("from")+5, cStringSQL.length()) + ")";
                cStringSQL=cStringSQL.toLowerCase().replaceAll("where", cStringSQLComplementar + " and ");
                cStringSQL=cStringSQL.toLowerCase().replaceFirst("select", "select top "+ nTamanhoPagina  +" ");
            }
            rs = stm.executeQuery(cStringSQL );
             
            
        }
        catch (SQLException ex) {
            
            LogDinnamus.Log(ex);  
            
        }

        return rs;

    }
    public  boolean ExecutarSQL(String cStringSQL)
    {
        return ExecutarSQL(cStringSQL,false);
    }
    public  boolean ExecutarSQL(String cStringSQL, boolean bCommitarAuto)
    {
        boolean bRet=true;
        try {
            //cStringSQL=TratarComandoSQL(cStringSQL);
            stm = cnx.createStatement();

            stm.execute(cStringSQL);
            if(bCommitarAuto)
              cnx.commit();

            stm.close();
        } catch (SQLException ex) {
            
            LogDinnamus.Log(ex, true);
            
            bRet=false;
        }

        return bRet;    
    }
    public  void FecharCNX()
    {
        try {
            cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(Dao_Jdbc_Base.class.getName()).log(Level.SEVERE, null, ex);
        }
        cnx=null;

    }
    public  boolean  AbrirCnx(Map<String,String> hmServidor){
        return AbrirCnx(hmServidor, false);
    }

    public  boolean  AbrirCnx(Map<String,String> hmServidor, boolean bExibirMsg)
    {
        return AbrirCnx(hmServidor, bExibirMsg,false);
    }
    public  boolean  AbrirCnx(Map<String,String> hmServidor, boolean bExibirMsg, boolean bComitarAuto)
    {
        boolean bRet =false;
        try
        {
            
            //Map<String,String> hmServidor=VerificarArquivoCFG.getHmServidores().get("Servidor0");
            LogDinnamus.Informacao("Abrindo conexao com o servidor");
            String Url = "jdbc:jtds:sqlserver://"+ hmServidor.get("Host")  + "/" + hmServidor.get("Banco")+ ";useCursors=true";
            String User=hmServidor.get("Usuario");
            String Pass=hmServidor.get("Senha");

            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            try {

                cnx = DriverManager.getConnection(Url, User, Pass );
                cnx.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
                cnx.setAutoCommit(false);
                this.hmServidor = hmServidor;
                //cnx.s
                bRet=true;
            } catch (SQLException ex) {
                     Erro = ex.getMessage();
                    LogDinnamus.Informacao("Falha na comunicacao com o servidor : " + ex.getMessage()  );

            }
            LogDinnamus.Informacao("Abrindo conexao com o servidor : "+ Url);
        } catch (ClassNotFoundException ex) {
            LogDinnamus.Log(ex, false);
            Erro = ex.getMessage();
        }

        return bRet;
    }
    public  boolean Commitar_Statment()
    {
        return Commitar_Statment(null);
    }
    
    public  boolean Commitar_Statment(Savepoint spPontoSalvamento){
        return Commitar_Statment(spPontoSalvamento, true);
    }
    public  boolean Commitar_Statment(Savepoint spPontoSalvamento, boolean autocommit)
    {
        boolean bRetorno = true;
        try {
             
            if (svpt1!=null){
                System.out.println("GRAVANDO TRX JDBC " +svpt1.getSavepointName() );
            }
            
            cnx.commit();
            svpt1=null;
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);

            RollBack_Statment(spPontoSalvamento);

            bRetorno=false;

        }
        return bRetorno;
    }
    public  boolean RollBack_Statment()
    {
        return RollBack_Statment(null);
    }

    public  boolean RollBack_Statment(Savepoint sp)
    {
        boolean bRetorno = true;
        try {

            if(sp==null)
               cnx.rollback();
            else
            {
               cnx.rollback(sp);
               //cnx.releaseSavepoint(sp);
            }
            
            cnx.setAutoCommit(false);
            svpt1=null;
            
            System.out.println("DESFAZENDO TRX JDBC " + (sp!=null ? sp.getSavepointName() : "")  );

        } catch (SQLException ex) {

                LogDinnamus.Log(ex);
                bRetorno=false;

        }
        return bRetorno;
    }
    
    public  Savepoint getPontoDeSalvamento() {
        return svpt1;
    }

    public  boolean setPontoDeSalvamento(String cNomePonto)
    {
        boolean bRetorno = true;
        try {

            //cnx.setAutoCommit(false);
            
            svpt1 = cnx.setSavepoint(cNomePonto);
            
            System.out.println("ABRINDO TRX JDBC : " + cNomePonto);

        } catch (SQLException ex) {
            bRetorno=false;
            LogDinnamus.Log(ex);
        }
        return bRetorno;
    }
 public  int ContarRegistros(String cQuery)
    {
        int nTotalRegistros=0;

        ResultSet rs = GerarResultSet("select count(*) TotalRegistro from "  + cQuery);
        try {
            if (rs.next()) {
                nTotalRegistros = rs.getInt("TotalRegistro");
            }
            
            rs.getStatement().close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Dao_Jdbc_Base.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        return nTotalRegistros;

    }
    public  int ContarRegistros2(String cQuery)
    {
        int nTotalRegistros=0;

        ResultSet rs = GerarResultSet("SELECT " +
"       IDX.Rows AS TotalRegistro " +
"FROM " +
"        sysobjects AS OBJ " +
"INNER JOIN " +
"        sysindexes AS IDX " +
"ON " +
"       OBJ.id = IDX.id " +
"WHERE  OBJ.NAME ='"+ cQuery +"' and " +
"       type = 'U' " +
"AND " +
"       IDX.IndId < 2"   );
        try {
            if (rs.next()) {
                nTotalRegistros = rs.getInt("TotalRegistro");
            }
            
            rs.getStatement().close();
            rs.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(Dao_Jdbc_Base.class.getName()).log(Level.SEVERE, null, ex);
        }

        

        return nTotalRegistros;

    }
    public  Long NovoValorIdentidade(String cNomeTabela, Integer nCodigoLoja, Integer nCodigoPDV){
        return NovoValorIdentidade(cNomeTabela, nCodigoLoja, nCodigoPDV, true);
    }
    public  Long NovoValorIdentidade(String cNomeTabela, Integer nCodigoLoja, Integer nCodigoPDV, boolean comitt)
    {
        Long nNovoValor=0l;
        CallableStatement cs;
        try {
            
            cs = cnx.prepareCall("{call NovoCodigoTabela(?,?)}");

            cs.setString(1, cNomeTabela);
            cs.registerOutParameter(2, Types.INTEGER);
            cs.execute();
            nNovoValor=(nCodigoLoja>0 ? Long.parseLong(nCodigoLoja.toString()+ "0" + nCodigoPDV + "0" + cs.getInt(2)) : cs.getInt(2));
            if(comitt){
                cnx.commit();
            }

        } catch (SQLException ex) {
            try {
                if(comitt){
                   cnx.rollback();
                }
            } catch (SQLException ex1) {
                LogDinnamus.Log(ex1);
            }
            
            LogDinnamus.Log(ex);
            
            
        }


        return nNovoValor;
    }
    public ArrayList<Long> NovoSequencialCotacao(Integer Loja,Date Data){
            ArrayList<Long> ret = new ArrayList<Long>();
            CallableStatement cs;
            try {
                
                cs = cnx.prepareCall("{call GerarNovoSequencialTabelaPDV(?,?,?,?)}");

                cs.setString(1, "dadosorc-lj:" + Loja.toString());
                cs.setDate(2, Data);
                cs.registerOutParameter(3, Types.INTEGER);
                cs.registerOutParameter(4, Types.INTEGER);
                cs.execute();
                
                ret.add(cs.getLong(3));
                ret.add(cs.getLong(4));
                //nNovoValor=(nCodigoLoja>0 ? Long.parseLong(nCodigoLoja.toString()+ "0" + nCodigoPDV + "0" + cs.getInt(2)) : cs.getInt(2));
                
             
            } catch (Exception e) {
                LogDinnamus.Log(e, true);
            }
            return ret;
     }
    
     public  ResultSet ListaIndicesTabela(String cNomeTabela)
    {

        ResultSet rsCamposTabela=null;
        try {

            String cQuery="select NomeIndice,ISNULL(Unico,0) UNICO from off_tabelasincronismo_indice where nometabela='"+ cNomeTabela +"'";

            rsCamposTabela=GerarResultSet(cQuery,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

        } catch (Exception exception) {

             LogDinnamus.Log(exception);

        }
        return rsCamposTabela;

    }    
    public  ResultSet ListaIndicesTabela_Campos(String cNomeIndice)
    {

        ResultSet rsCamposTabela=null;
        try {

            String cQuery="select NomeCampo from off_tabelasincronismo_indice_campos where NomeIndice='"+ cNomeIndice +"'";

            rsCamposTabela=GerarResultSet(cQuery,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        } catch (Exception exception) {

             LogDinnamus.Log(exception);

        }
        return rsCamposTabela;

    }
    public  NamedParameter CriarNamedStatment(String cQuery)
    {
        try {
            return new NamedParameter(getCNX(),cQuery);
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);
            return null;
        }
    }
    public  NamedParameter SetarParametros(NamedParameter npstmt,String cNomeParametro, Object objValorParametro, Type tp)
    {
        String cTipoCampo =tp.toString().replace("class", "").replace("CLASS", "").replace("Class","").trim();
        Integer nValorInt=0;
        Object obj=new Object();
        try {
            if (cTipoCampo.equalsIgnoreCase("JAVA.LANG.INTEGER"))
            {
                if(objValorParametro==null)
                    objValorParametro=new Integer(0);

                nValorInt=Integer.parseInt(objValorParametro.toString());
                npstmt.setInt(cNomeParametro, nValorInt);
            }
            else if (cTipoCampo.equalsIgnoreCase("JAVA.MATH.BIGDECIMAL"))
            {
                if(objValorParametro==null)
                    objValorParametro=new BigDecimal(0);

                npstmt.setBigDecimal(cNomeParametro, new BigDecimal(objValorParametro.toString()));
            }
            else if (cTipoCampo.equalsIgnoreCase("JAVA.LANG.STRING"))
            {
                if(objValorParametro==null)
                    objValorParametro=new String("");

                npstmt.setString(cNomeParametro, objValorParametro.toString());
            }
            else if (cTipoCampo.equalsIgnoreCase("JAVA.LANG.BOOLEAN"))
            {
                if(objValorParametro==null)
                    objValorParametro=new Boolean(false);

                npstmt.setInt(cNomeParametro, (objValorParametro.toString().equals("true") ? 1 : 0) );
            }
            else if (cTipoCampo.equalsIgnoreCase("JAVA.LANG.FLOAT"))
            {
                if(objValorParametro==null)
                    objValorParametro=new Float(0);

                npstmt.setFloat(cNomeParametro, Float.parseFloat(objValorParametro.toString()) );
            }
            else if (cTipoCampo.equalsIgnoreCase("JAVA.LANG.SHORT"))
            {
                if(objValorParametro==null)
                   objValorParametro =  Short.parseShort("0");

                npstmt.setShort(cNomeParametro, Short.parseShort(objValorParametro.toString()));
            }
            else if (cTipoCampo.equalsIgnoreCase("JAVA.LANG.DOUBLE"))
            {
                if(objValorParametro==null)
                   objValorParametro = new Double(0);

                npstmt.setDouble(cNomeParametro, Double.parseDouble(objValorParametro.toString()));
            }
            else if (cTipoCampo.equalsIgnoreCase("java.sql.Timestamp"))
            {
                if(objValorParametro!=null){
                   Timestamp time = (Timestamp)objValorParametro;
                   //npstmt.setDate(cNomeParametro,  new Date(Time.getTime()_);
                   npstmt.setTimestamp(cNomeParametro, time);
                }
                else
                  npstmt.setDate(cNomeParametro, new Date(0));
            }
            else if (cTipoCampo.equalsIgnoreCase("JAVA.UTIL.DATE") || cTipoCampo.equalsIgnoreCase("JAVA.SQL.DATE"))
            {
                if(objValorParametro!=null)
                {
                  if(objValorParametro.getClass().getName().equalsIgnoreCase("java.sql.Timestamp"))
                    npstmt.setTimestamp(cNomeParametro, (Timestamp)objValorParametro);
                  else
                     npstmt.setDate(cNomeParametro, (Date)objValorParametro);
                }
                else
                     npstmt.setDate(cNomeParametro, new Date(0));

            }
            else if (cTipoCampo.equalsIgnoreCase("JAVA.LANG.CHARACTER"))
            {
                if(objValorParametro==null)
                    objValorParametro=new String();
                npstmt.setString(cNomeParametro, objValorParametro.toString());

            }
            else if (cTipoCampo.equalsIgnoreCase("java.sql.Blob") || cTipoCampo.equalsIgnoreCase("image") || cTipoCampo.equalsIgnoreCase("interface java.sql.Blob") )
            {
                if(objValorParametro==null)
                   npstmt.setNull(cNomeParametro, java.sql.Types.BLOB);
                else
                    npstmt.setBlob(cNomeParametro, (Blob) objValorParametro);
            }
            else
                npstmt.setObject(cNomeParametro, objValorParametro);


        }
        catch (SQLException ex) {
             LogDinnamus.Log(ex);
             npstmt=null;
        }
        catch (Exception ex)
        {
                LogDinnamus.Log(ex);
                npstmt= null;
        }
        return npstmt;
    }
     public Timestamp DataHoraServidor(){
        Timestamp Ret = null;
        try {
            ResultSet rs = GerarResultSet("select getdate() datahora");
            if(rs.next()){
                Ret = rs.getTimestamp("datahora");
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }

    /**
     * @return the hmServidor
     */
    public Map<String,String> getHmServidor() {
        return hmServidor;
    }

    /**
     * @param hmServidor the hmServidor to set
     */
    public void setHmServidor(Map<String,String> hmServidor) {
        this.hmServidor = hmServidor;
    }

}
