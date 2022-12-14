package br.com.generica;


import br.com.log.LogDinnamus;
import br.com.repositorio.DAO_RepositorioLocal;
import br.com.servidor.Dao_Jdbc_1;
import br.com.util.NamedParameter;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dti
 */
public class Dao_Generica {

    public static final String IdentificadorDeCampoAutoIncrementavel="<[a-z]+>";

    public static Long NovoValorPK(String Tabela,Integer Loja,Integer PDV, boolean online,boolean commit){
        Long Retorno = 0l;
        try {
            
            if(online){
                
                Retorno= Dao_Jdbc_1.getConexao().NovoValorIdentidade(Tabela,Loja,PDV,commit);
                
            }else{
            
                Retorno= DAO_RepositorioLocal.NovoValorIdentidade(Tabela,Loja,PDV);
                
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return Retorno;
    }
    public static ResultSet Pesquisar(String cStringSQL, boolean  online)
    {
        return Pesquisar(cStringSQL, false,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY,online);
    }
    public static ResultSet Pesquisar(String cStringSQL, boolean  bPosiciona, boolean  online)
    {
        return Pesquisar(cStringSQL, bPosiciona,ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY,online);
    }
    public static ResultSet Pesquisar(String cStringSQL, boolean  bPosiciona,int resultSetType,int resultSetConcurrency, boolean  online )
    {
        ResultSet l=Pesquisar(cStringSQL,resultSetType,resultSetConcurrency,online);
        if(bPosiciona)
           try {
            l.next();
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);

        }

        return l;
    }
    public static ResultSet Pesquisar(String cStringSQL,int resultSetType,int resultSetConcurrency, boolean  online )
    {
        return Pesquisar(cStringSQL, resultSetType, resultSetConcurrency, 0,online);
    }
    public static ResultSet Pesquisar(String cStringSQL,int resultSetType,int resultSetConcurrency, int nTamanhoPagina, boolean  online )
    {
        return Pesquisar(cStringSQL,resultSetType,resultSetConcurrency, nTamanhoPagina, online,  false );
    }
    public static ResultSet Pesquisar(String cStringSQL,int resultSetType,int resultSetConcurrency, int nTamanhoPagina, boolean  online, boolean bExibirMsgError )
    {
        ResultSet l=null;

        if(online)
        {
           l = Pesquisar_Servidor(cStringSQL.toUpperCase(),resultSetType,resultSetConcurrency,bExibirMsgError) ;
           if(l==null)
           {
              l=Pesquisar(cStringSQL.toUpperCase(),resultSetType,resultSetConcurrency,nTamanhoPagina,false,bExibirMsgError);
           }
        }
        else
           l = Pesquisar_RepositorioLocal(cStringSQL.toUpperCase(),resultSetType,resultSetConcurrency,nTamanhoPagina, ResultSet.HOLD_CURSORS_OVER_COMMIT);

        return l;
    }
    
    public static ResultSet Pesquisar(String cStringSQL,int resultSetType,int resultSetConcurrency, int nTamanhoPagina, String cCampoChavePrimaria, int nValorUltimaChavePrimaria, String cTipoNavegacao, int nPaginaAtual , boolean  online){
        return Pesquisar(cStringSQL, resultSetType, resultSetConcurrency, nTamanhoPagina, cCampoChavePrimaria, nValorUltimaChavePrimaria, cTipoNavegacao, nPaginaAtual, online, ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }
    public static ResultSet Pesquisar(String cStringSQL,int resultSetType,int resultSetConcurrency, int nTamanhoPagina, String cCampoChavePrimaria, int nValorUltimaChavePrimaria, String cTipoNavegacao, int nPaginaAtual , boolean  online, int ResultSetHoldility)
    {
        ResultSet l=null;

        cStringSQL= cStringSQL
                .replace("<from>", "from")
                .replace("<FROM>","FROM")
                .replace("<From>", "From");
        
        if(online)
           l = Pesquisar_Servidor(cStringSQL,resultSetType,resultSetConcurrency,nTamanhoPagina, cCampoChavePrimaria, nValorUltimaChavePrimaria, cTipoNavegacao,nPaginaAtual) ;
        else
           l = Pesquisar_RepositorioLocal(cStringSQL,resultSetType,resultSetConcurrency,nTamanhoPagina,cCampoChavePrimaria, nValorUltimaChavePrimaria, cTipoNavegacao,nPaginaAtual,ResultSetHoldility);

        return l;
    }
    
    
    public static int ContarRegistros(String cStringSQL, boolean  online)
    {
        int i=0;

        
        i = cStringSQL.toLowerCase().indexOf("<from>");
        
        if(i>-1){
            StringBuilder queryCount = new StringBuilder("select count(*) from ");
            queryCount.append(cStringSQL.substring(i+7));
            i=ContarRegistros(queryCount.toString(),online);
        }else{
            i=ContarRegistros2(cStringSQL, online);
        }
        
        return i;
    }
    
    private static int ContarRegistros2(String cStringSQL, boolean  online)
    {
        int i=0;

        if(online)
           i = Dao_Jdbc_1.getConexao().ContarRegistros(cStringSQL);
        else
           i = DAO_RepositorioLocal.ContarRegistros(cStringSQL);

        return i;
    }
    public static ResultSet Pesquisar_Servidor(String cStringSQL)
    {
        return Pesquisar_Servidor(cStringSQL,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
    }
    public static ResultSet Pesquisar_Servidor(String cStringSQL , int resultSetType , int resultSetConcurrency)
    {
        return Pesquisar_Servidor( cStringSQL , resultSetType , resultSetConcurrency, false);
    }
    public static ResultSet Pesquisar_Servidor(String cStringSQL , int resultSetType , int resultSetConcurrency, boolean bExibirMsgError)
    {
        return Pesquisar_Servidor(cStringSQL, resultSetType, resultSetConcurrency,0,"",0,"",0,bExibirMsgError);
    }
    public static ResultSet Pesquisar_Servidor(String cStringSQL,int resultSetType,int resultSetConcurrency,int nTamanhoPagina,String cCampoChavePrimaria, int nValorUltimaChavePrimaria, String cTipoNavegacao, int nPaginaAtual)
    {
        return Pesquisar_Servidor( cStringSQL,resultSetType,resultSetConcurrency, nTamanhoPagina, cCampoChavePrimaria, nValorUltimaChavePrimaria,  cTipoNavegacao, nPaginaAtual,false);
    }
    public static ResultSet Pesquisar_Servidor(String cStringSQL,int resultSetType,int resultSetConcurrency,int nTamanhoPagina,String cCampoChavePrimaria, int nValorUltimaChavePrimaria, String cTipoNavegacao, int nPaginaAtual, boolean bExibirMsgError)
    {
        ResultSet l=null;
        l = Dao_Jdbc_1.getConexao().GerarResultSet(cStringSQL,resultSetType,resultSetConcurrency,nTamanhoPagina,cCampoChavePrimaria, nValorUltimaChavePrimaria, cTipoNavegacao,nPaginaAtual,bExibirMsgError);
        return l;
    }
    public static ResultSet Pesquisar_RepositorioLocal(String cStringSQL)
    {
        return Pesquisar_RepositorioLocal(cStringSQL,ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY,ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }
    public static ResultSet Pesquisar_RepositorioLocal(String cStringSQL,int resultSetType,int resultSetConcurrency,int resultsetHoldability)
    {
           return Pesquisar_RepositorioLocal(cStringSQL, resultSetType, resultSetConcurrency, 0,resultsetHoldability);
    }
    public static ResultSet Pesquisar_RepositorioLocal(String cStringSQL,int resultSetType,int resultSetConcurrency, int nTamanhoPagina,int resultsetHoldability)
    {
            return Pesquisar_RepositorioLocal( cStringSQL, resultSetType, resultSetConcurrency,  nTamanhoPagina, "", 0, "avan??o",0,resultsetHoldability);
    }
    public static ResultSet Pesquisar_RepositorioLocal(String cStringSQL,int resultSetType,int resultSetConcurrency, int nTamanhoPagina,String cCampoChavePrimaria, int nValorUltimaChavePrimaria, String cTipoNavegacao, int nPaginaAtual, int resultsetHoldability)
    {
        ResultSet l=null;

        l = DAO_RepositorioLocal.GerarResultSet(cStringSQL,resultSetType,resultSetConcurrency,nTamanhoPagina,cCampoChavePrimaria, nValorUltimaChavePrimaria, cTipoNavegacao,nPaginaAtual,resultsetHoldability);

        return l;
    }
    public static boolean  GravarTrx(boolean  bServidor)
    {
        boolean bRet=true;

        bRet = DAO_RepositorioLocal.Commitar_Statment(DAO_RepositorioLocal.getPontoDeSalvamento());
        if(bRet)
        {
            if(bServidor)
            {
                bRet=Dao_Jdbc_1.getConexao().Commitar_Statment(Dao_Jdbc_1.getConexao().getPontoDeSalvamento());

            }
        }

        return bRet;
    }
    public static boolean  DesfazerTrx(boolean  bServidor)
    {
        boolean bRet=true;

        bRet = DAO_RepositorioLocal.RollBack_Statment(DAO_RepositorioLocal.getPontoDeSalvamento());
        if(bRet)
        {
            if(bServidor)
            {
                bRet=Dao_Jdbc_1.getConexao().RollBack_Statment(Dao_Jdbc_1.getConexao().getPontoDeSalvamento());

            }
        }

        return bRet;
    }
    public static boolean IniciarTrx(boolean  bUsarServidor, boolean  online)
    {
        boolean bRet=true;

        bRet = DAO_RepositorioLocal.setPontoDeSalvamento("trx");

        if(bRet)
        {
            if(bUsarServidor)
            {
                if(online)
                   bRet=Dao_Jdbc_1.getConexao().setPontoDeSalvamento("trx");
            }
        }
        return bRet;
    }

    public static boolean ExecutarSQL(String cStringSQL, boolean  bGravarServidor, boolean  online)
    {
        return  ExecutarSQL( cStringSQL, bGravarServidor,   online, false);
    }
    public static boolean ExecutarSQL(String cStringSQL, boolean  bGravarServidor, boolean  online, boolean  bComittarAuto)
    {
        return ExecutarSQL(cStringSQL, bGravarServidor, online,   bComittarAuto,0);
    }
    public static boolean ExecutarSQL(String cStringSQL, boolean  bGravarServidor, boolean  online, boolean  bComittarAuto, Integer nCodigoLoja)
    {
        boolean bRet=true;

        bRet=ExecutarSQL_Repositorio(cStringSQL,bComittarAuto);

        if (bGravarServidor)
            if(online)
               bRet= ExecutarSQL_Servidor(cStringSQL,bComittarAuto);

        return bRet;
    }
    
    public static boolean ExecutarSQL_Repositorio(String cStringSQL)
    {
        return ExecutarSQL_Repositorio(cStringSQL,false);
    }
    public static boolean ExecutarSQL_Repositorio(String cStringSQL, boolean  bComittatAuto)
    {
        boolean bRet=true;

        bRet=DAO_RepositorioLocal.ExecutarComandoSimples(cStringSQL, bComittatAuto);

        return bRet;
    }
    public static boolean ExecutarSQL_Servidor(String cStringSQL)
    {
        return ExecutarSQL_Servidor( cStringSQL, false);
    }

    public static boolean ExecutarSQL_Servidor(String cStringSQL, boolean  bComittarAuto)
    {
        boolean bRet=true;

        bRet= Dao_Jdbc_1.getConexao().ExecutarSQL( cStringSQL,bComittarAuto);

        return bRet;
    }
    public static HashMap<String, Map<String, Integer>> TratarComandoSQL(String cSql, boolean bServidor, int nCodigoLoja)
    {
        HashMap<String,Integer> hmDadosString=new HashMap<String, Integer>();
        Integer nValorChave=0;
        String cRetorno="";
        String cNomeTabela="";
        Pattern p = Pattern.compile("<[a-z]+>");
        Matcher matcher=p.matcher( cSql.subSequence(0, cSql.length()));
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            cNomeTabela=matcher.group().toString().replaceAll("<", "").replaceAll(">", "");

            //nValorChave=(bServidor ? Dao_Jdbc.getConexao().NovoValorIdentidade(cNomeTabela,nCodigoLoja) :   DAO_RepositorioLocal.NovoValorIdentidade(cNomeTabela,nCodigoLoja));

            matcher.appendReplacement(sb, nValorChave.toString());
            hmDadosString.put(cNomeTabela, nValorChave);
        }
        matcher.appendTail(sb);
        cRetorno =sb.toString();
        HashMap<String, Map<String, Integer>> hmRetorno = new HashMap<String, Map<String, Integer>>();
        hmRetorno.put(cRetorno, hmDadosString);
        return hmRetorno;
    }
    public static NamedParameter SetarParametros(NamedParameter npstmt,String cNomeParametro, Object objValorParametro, Type tp)
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
            else if (cTipoCampo.equalsIgnoreCase("JAVA.LANG.LONG"))
            {
                if(objValorParametro==null)
                   objValorParametro = new Long(0);

                npstmt.setLong(cNomeParametro, Long.parseLong(objValorParametro.toString()));
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
                   //Timestamp time = (Timestamp)objValorParametro;
                   //npstmt.setDate(cNomeParametro,  new Date(Time.getTime()_);
                   npstmt.setTimestamp(cNomeParametro, (Timestamp)objValorParametro);
                }
                else
                  npstmt.setTimestamp(cNomeParametro, null);
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
            else if (cTipoCampo.equalsIgnoreCase("java.sql.Blob") || cTipoCampo.equalsIgnoreCase("interface java.sql.Blob") )
            {
                if(objValorParametro==null)
                   npstmt.setNull(cNomeParametro, java.sql.Types.BLOB);
                else
                    npstmt.setBlob(cNomeParametro, (Blob) objValorParametro);
            }

            else if (cTipoCampo.equalsIgnoreCase("image") || cTipoCampo.equalsIgnoreCase("interface java.sql.Blob") )
            {
                if(objValorParametro==null)
                   npstmt.setNull(cNomeParametro, java.sql.Types.BLOB);
                else
                    npstmt.setObject(cNomeParametro,  objValorParametro);
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
        }
        return npstmt;
    }

public static String PrepararStringUpdate(String cNomeTabela, ResultSet rsCamposTabela, String cChavePrimaria){
    return PrepararStringUpdate(cNomeTabela,rsCamposTabela,cChavePrimaria,new ArrayList<String>());
}
public static String PrepararStringUpdate(String cNomeTabela, ResultSet rsCamposTabela, String cChavePrimaria, ArrayList<String> clListaCamposIgnorar)
    {

        String cQuery = "update <Tabela> set ,<x>=:<x> where <pk>=:<pk>",cNomeCampo="";

        boolean bPrimeiroCampo=true;

        try {

            while (rsCamposTabela.next()) {
                cNomeCampo=rsCamposTabela.getString("ColumnName");
                if(!clListaCamposIgnorar.contains(cNomeCampo.toLowerCase())){
                    if(!cNomeCampo.equalsIgnoreCase(cChavePrimaria)){
                        cQuery=cQuery.replace(",<x>=:<x>", (!bPrimeiroCampo  ? "," : "" ) + cNomeCampo +  "=:" + cNomeCampo + ",<x>=:<x>"  );
                        bPrimeiroCampo=false;
                    }
                }
            }
            cQuery=cQuery.replace(",<x>=:<x>", "").replace("<Tabela>", cNomeTabela);
        } catch (SQLException ex) {
             LogDinnamus.Log(ex);
        }
        return cQuery;

    }
    public static String PrepararStringInsert(String cNomeTabela, ResultSet rsCamposTabela){

            return PrepararStringInsert( cNomeTabela,  rsCamposTabela, new ArrayList<String>());
    }
    public static String PrepararStringInsert(String cNomeTabela, ResultSet rsCamposTabela, ArrayList<String> clListaCamposIgnorar)
    {

        String cQuery = "Insert into <Tabela>(<x>) values (<y>)",cNomeCampo="";

        boolean bPrimeiroCampo=true;

        try {

            while (rsCamposTabela.next()) {
                cNomeCampo=rsCamposTabela.getString("ColumnName");
                if(!clListaCamposIgnorar.contains(cNomeCampo.toLowerCase())){
                    cQuery=cQuery.replace("<x>", ( !bPrimeiroCampo  ? "," : "" ) + cNomeCampo +  "<x>"  );
                    cQuery=cQuery.replace("<y>", ( !bPrimeiroCampo  ? "," : "" ) + ":" + cNomeCampo +  "<y>"  );
                    bPrimeiroCampo=false;
                }
                

            }
            cQuery=cQuery.replace("<x>", "").replace("<y>", "").replace("<Tabela>", cNomeTabela);


        } catch (SQLException ex) {
             LogDinnamus.Log(ex);
        }
        return cQuery;

    }
    public static NamedParameter SetarParametros(NamedParameter npstmt, ResultSet rsOrigem, ArrayList<String> CamposExcluidos){
        String cNomeParametro="";
        boolean bPulaCampo=false;
        Object objValorParametro=null;
        Class cClass;
        
        try {
                for (int j = 1; j <= rsOrigem.getMetaData().getColumnCount(); j++) {

                bPulaCampo = false;
                cNomeParametro = rsOrigem.getMetaData().getColumnName(j).toUpperCase();
                if(CamposExcluidos!=null){
                   if(CamposExcluidos.contains(cNomeParametro)) {
                      bPulaCampo = true;
                   }                
                }
                if (!bPulaCampo) {
                    objValorParametro = rsOrigem.getObject(cNomeParametro);
                    cClass = Class.forName(rsOrigem.getMetaData().getColumnClassName(j));

                    if (objValorParametro == null) {
                        objValorParametro = null;
                    }

                    npstmt = Dao_Generica.SetarParametros(npstmt, cNomeParametro, objValorParametro, Class.forName(rsOrigem.getMetaData().getColumnClassName(j)));
                   
                    if(npstmt==null){return null;}
                }

            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            npstmt=null;
        }
        return npstmt;
    }
}
