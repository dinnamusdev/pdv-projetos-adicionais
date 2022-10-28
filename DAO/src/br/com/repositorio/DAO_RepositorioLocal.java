/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.repositorio;


import br.com.log.LogDinnamus;
import br.com.util.DAO_Parametro_Generico;
import br.com.util.NamedParameter;
import br.ui.frmProgresso;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dti
 */
public class DAO_RepositorioLocal {
    public static String getMensagemErro() {
        return cMensagem;
    }
    //private frmMonitorProgresso progresso= new frmMonitorProgresso();
    private static int contador=0;
    public static frmProgresso progresso=null;
    private static Connection cnRepLocal;
    private static Statement st;
    private static Savepoint svpt1;
    private static String cMensagem="";
    public static String cNomeSchema="DINNAMUS";
    public static boolean Iniciar()
    {
        boolean bRetorno=true;

        try {
            try {
                Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(DAO_RepositorioLocal.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(DAO_RepositorioLocal.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            bRetorno=false;

            LogDinnamus.Log(ex);


        }
        
        return bRetorno;


    }


    public static Savepoint getPontoDeSalvamento() {
        return svpt1;
    }
    
     public static Savepoint CriarPontoDeSalvamento(String cNomePonto)
    {
        Savepoint svpt=null;
     
        try {

            svpt = getCnRepLocal().setSavepoint(cNomePonto);


        } catch (SQLException ex) {
          
            LogDinnamus.Log(ex);
        }
        return svpt;
    }

    public static boolean setPontoDeSalvamento(String cNomePonto)
    {
        boolean bRetorno = true;
        try {

            System.out.println("ABRINDO TRX DERBY : " + cNomePonto);
            svpt1 = getCnRepLocal().setSavepoint(cNomePonto);


        } catch (SQLException ex) {
            bRetorno=false;
            LogDinnamus.Log(ex);
        }
        return bRetorno;
    }


    public static boolean AbrirBanco(Map<String,String> hmRepositorioLocal, boolean bBancoNovo)
    {
        boolean bRetorno=true;
        try {
             if(getCnRepLocal()==null)
             {  
                String CNX = "jdbc:derby:" + hmRepositorioLocal.get("Banco") + ";user=" + hmRepositorioLocal.get("Usuario") + ";password=" + hmRepositorioLocal.get("Senha") + (bBancoNovo ? ";create=true;territory=pt_BR;collation=TERRITORY_BASED:PRIMARY" : "");                
                 
                 setCnRepLocal(DriverManager.getConnection(CNX)) ;
                 
                 getCnRepLocal().commit();
                 getCnRepLocal().setAutoCommit(false);
                 getCnRepLocal().setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
             }
        } catch (SQLException exception) {
            //if(exception.)
            SQLException next = exception.getNextException();
              // exception.
            
            if(next.getSQLState().equalsIgnoreCase("XSDB6")){
                JOptionPane.showMessageDialog(null, "OUTRA INSTANCIA DO APLICATIVO AINDA ESTA EM EXECUÇÃO\n\nFINALIZE ANTES DE ABRIR O SISTEMA", "PDV DINNAMUS", JOptionPane.WARNING_MESSAGE);
            }
            LogDinnamus.Log(exception);
            bRetorno=false;
                
        }
        return bRetorno;

    }

    
    public static Long NovoValorIdentidade(String cNomeTabela, Integer nCodigoLoja, Integer nCodigoPDV )
    {
        String sql="";

        long nNovoValor=0;
        ResultSet rs=null;
        
        rs=DAO_RepositorioLocal.GerarResultSet("select valor from SequencialTabelas where nometabela='"+ cNomeTabela.toUpperCase() +"'");
        try {
            if (rs.next()) {
                nNovoValor=rs.getLong("Valor")+1;
            }
            else
            {
                nNovoValor=1;
            }
            sql=(nNovoValor==1 ?
                                "insert into SequencialTabelas(nometabela,valor) values ('"+ cNomeTabela.toUpperCase() + "',"+ nNovoValor  +")" :
                                "update SequencialTabelas set valor=" + nNovoValor + " where nometabela='"+ cNomeTabela.toUpperCase()   +"'"
                                );
            //DAO_RepositorioLocal.getCnRepLocal().setAutoCommit(true);
            if(!DAO_RepositorioLocal.ExecutarComandoSimples(sql))
            {
                nNovoValor=-1;
            }
            //DAO_RepositorioLocal.getCnRepLocal().setAutoCommit(false);
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);
        }

        //CallableStatement cs;
        nNovoValor=(nCodigoLoja > 0 ? Long.parseLong(nCodigoLoja.toString() + "0" + nCodigoPDV  +"0" + nNovoValor) : nNovoValor);
        
        return nNovoValor;
    }
    /*
    private static boolean Inicializar_BancoDerby(Map<String,String> hmRepositorioLocal)
    {
        boolean bRetorno=true;
        boolean bBancoNovo=false;
        try {

            bBancoNovo=ManipulacaoArquivo.DiretorioExiste(hmRepositorioLocal.get("Banco"));
            if(!AbrirBanco(hmRepositorioLocal, !bBancoNovo))
            {
                bRetorno=false;
            }

        } catch (Exception exception) {
            LogDinnamus.Log(exception);
            bRetorno=false;
        }

        return bRetorno;
    }

    public static boolean VerificarRepositorioLocal(Map<String,String> hmRepositorioLocal)
    {
        boolean bRetorno=false;

        if(Iniciar())
        {
            if(Inicializar_BancoDerby(hmRepositorioLocal))
            {
                bRetorno=VerificarEstruturaBanco(cNomeSchema);
                if(!bRetorno)
                {
                    if(InicializarEstrutura())
                       bRetorno=true;

                }
               if(bRetorno)
               {
                   if(Sistema.isOnline()){
                       if(bRetorno)
                            bRetorno=(AlterarEstrutura(VerificarScriptsComplementares()));

                        if(bRetorno)
                            bRetorno=DAO_RepositorioLocal_Inicializar.SincronizarTabela_Entre_BancosON_e_BancoOFF();
                   }
               }


            }
        }
        return bRetorno;
    }
     *
     */
    /*
    public static Timestamp getUltimoSinc()
    {
        Timestamp tsUltimoSinc=null;

        try {

            ResultSet rs=GerarResultSet("select max(Data) DataUltimoSinc from dinnamus.HistoricoSincronismo");
            if(rs!=null)
            {
                if(rs.next())
                  tsUltimoSinc= rs.getTimestamp("DataUltimoSinc");

            }

            if(tsUltimoSinc==null)
                tsUltimoSinc = PrepararValorDeTipo.getTimestampInstance();

        } catch (Exception exception) {
                LogDinnamus.Log(exception);
        }

        return tsUltimoSinc;
    }
    */
    public static ResultSet GerarResultSet(String cString,DAO_Parametro_Generico<?>[] dpgParametros )
    {
        ResultSet rs = null;
        try {

            PreparedStatement pstmt = getCnRepLocal().prepareStatement(cString,ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

            for (int i = 0; i < dpgParametros.length; i++) {
                 pstmt.setString(i+1, dpgParametros[i].getGValorParamentro().toString().toUpperCase());
            }

            rs=pstmt.executeQuery();

        } catch (SQLException ex) {

            LogDinnamus.Log(ex);
        }

        return rs;

    }
    public static ResultSet GerarResultSet(String cStringSQL)
    {
        return GerarResultSet(cStringSQL, ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_READ_ONLY,ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }
    public static ResultSet GerarResultSet(String cString, int resultSetType,int resultSetConcurrency){
            return GerarResultSet(cString,resultSetType, resultSetConcurrency,ResultSet.HOLD_CURSORS_OVER_COMMIT);
    }
    public static ResultSet GerarResultSet(String cString, int resultSetType,int resultSetConcurrency,int resultSetHoldability)
    {
        return GerarResultSet(cString,resultSetType, resultSetConcurrency,0,0,resultSetHoldability);
    }
    public synchronized static ResultSet GerarResultSet(String cStringSQL, int resultSetType,int resultSetConcurrency, int nTamanhoPagina, String cCampoChavePrimaria, int nValorUltimaChavePrimaria, String cTipoNavegacao, int nPaginaAtual , int resultSetHoldability)
    {
        ResultSet rs=null;
        //int nValorLimite=0;
        //String cStringSQLOriginal=cStringSQL.toUpperCase();
        
        //PreparedStatement pm = new 
        
        cStringSQL=cStringSQL.toUpperCase();
        
        try {
            
            st = getCnRepLocal().createStatement(resultSetType,resultSetConcurrency,resultSetHoldability);
           
            if(nTamanhoPagina>0)
            {
                st.setFetchSize(nTamanhoPagina);
                 st.setMaxRows(nTamanhoPagina);
                st.setFetchDirection(ResultSet.FETCH_FORWARD);
                /*
                st.setMaxRows(nTamanhoPagina);
                st.setFetchSize(nTamanhoPagina);  

                    if(cStringSQL.toLowerCase().indexOf("order by")<0)
                       cStringSQL=cStringSQL + " ORDER BY " + cCampoChavePrimaria;
                    else
                    {
                        if(cStringSQL.toLowerCase().indexOf("order by " + cCampoChavePrimaria.toLowerCase())<0)
                           cStringSQL=cStringSQL.toLowerCase().replaceAll("order by", "ORDER BY " + cCampoChavePrimaria +"," );
                    }
                    cStringSQL= cStringSQL + " OFFSET " +  ((nTamanhoPagina*nPaginaAtual)-nTamanhoPagina) + " ROWS FETCH NEXT "+ (nTamanhoPagina*nPaginaAtual)  + " ROWS ONLY ";
                    */
            }

            cStringSQL= cStringSQL
                .replace("<from>", "from")
                .replace("<FROM>","FROM")
                .replace("<From>", "From");  
                    
            //System.out.print(cStringSQL.toUpperCase() +"\n");
            rs = st.executeQuery(cStringSQL.toUpperCase() );
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);
        }
        return rs;

    }
    
    
    
    public static ResultSet GerarResultSet(String cString, int resultSetType,int resultSetConcurrency, int nTamanhoPagina, int nPaginaAtual,int resultSetHoldability)
    {
        return GerarResultSet(cString, resultSetType, resultSetConcurrency, nTamanhoPagina, "", 0, "",nPaginaAtual,resultSetHoldability);
    }


    /*public static List ResultSet_Para_List( ResultSet results, Object c )
    {
            ConverterResultSetEmObjeto<Lojas> l = new ConverterResultSetEmObjeto<Lojas>();

            return l.Converter(results,new Lojas());


    }*/
    public static boolean ExecutarComandoSimples(String cComandoSQL)
    {
        return ExecutarComandoSimples(cComandoSQL,false);
    }

    public static boolean ExecutarComandoSimples(String cComandoSQL, boolean bComittarAuto)
    {
        return ExecutarComandoSimples( cComandoSQL,  bComittarAuto, true);
    }
    public static boolean ExecutarComandoSimples(String cComandoSQL, boolean bComittarAuto,boolean bLowerCase)
    {
        boolean bRetorno = true;
        try {

            //(cComandoSQL);
            st = getCnRepLocal().createStatement();
            System.out.println(cComandoSQL);
            st.execute((bLowerCase ?  cComandoSQL : cComandoSQL.toUpperCase()));
            if(bComittarAuto)
               cnRepLocal.commit();
            
            
        } catch (SQLException ex) {

                bRetorno = false;
            
                LogDinnamus.Log(ex);

        }
        return bRetorno;
    }

    private static String ConverterUTF_to_String(String cString)
    {
        String cStringRetorno =null;
        try {


            cStringRetorno = new String(cString.getBytes("pt_BR.UTF-8")).replace("?", "");


        } catch (UnsupportedEncodingException ex) {
            LogDinnamus.Log(ex);
        }
        return cStringRetorno;

    }

    public static boolean ExecutarComandoLote(String[] cComandoSQL ){
        return ExecutarComandoLote(cComandoSQL,true,false);
    }
    public static boolean ExecutarComandoLote(String[] cComandoSQL, boolean sqlminusculo){
        return ExecutarComandoLote(cComandoSQL,true,sqlminusculo);
    }
    public static boolean ExecutarComandoLote(String[] cComandoSQL, boolean silencioso, boolean sqlminusculo){
        try {
            
            
             boolean bRet = ExecutarComandoLote_Acao(cComandoSQL,silencioso, sqlminusculo);
         
             if(!silencioso){
                 //progresso.dispose();
             }
             
             return bRet;
          
        } catch (Exception e) {
             LogDinnamus.Log(e);
             return false;
        }
        
    }
        public static boolean ExecutarComandoLote_Acao(final String[] cComandoSQL, final boolean silencioso, final boolean sqlminusculo)
    {
        boolean bRetorno = true;
        String cQuery="";

        try {

            if(!silencioso){
                
            }
            
            for (int i = 0; i < cComandoSQL.length; i++) {

                cQuery= cComandoSQL[i];
                System.out.print("Query : "  + cQuery + "\n");
                contador=i;
            
                if(cQuery.length()>10)
                {                   
                   st = getCnRepLocal().createStatement(); 
                   
                   st.execute(sqlminusculo ? cQuery : cQuery.toUpperCase()  );
                }
            }
            getCnRepLocal().commit();

        } catch (SQLException ex) {
            try {
                bRetorno = false;
                getCnRepLocal().rollback();

                LogDinnamus.Log(ex);

                //ex.printStackTrace();
            } catch (SQLException ex1) {
                LogDinnamus.Log(ex1);
            }
        }
         return bRetorno;
    }
    /*
    private static boolean InicializarEstrutura()
    {
        boolean bRetorno=true;


        String cConteudoArquivo=ManipulacaoArquivo.LerArquivo(cNomeArquivoScriptRepLocal,true);

        String[] cQuery= cConteudoArquivo.split(";");

        if(!ExecutarComandoLote(cQuery))
           bRetorno=false;

        return bRetorno;
    }
    private static boolean AlterarEstrutura(File[] flArquivosScripts)
    {
        boolean bRetorno=true;
        String cNomeArquivoRenomeado="";
        for (int i = 0; i < flArquivosScripts.length; i++) {
            File file = flArquivosScripts[i];
            String cConteudoArquivo="";
            cConteudoArquivo = ManipulacaoArquivo.LerArquivo(file.getPath(),false);
            String[] cQuery= cConteudoArquivo.split(";");
            if(!ExecutarComandoLote(cQuery))
            {
               bRetorno=false;
               break;
            }
            else
            {
                cNomeArquivoRenomeado= ManipulacaoArquivo.DiretorioDeTrabalho() +File.separator + "scripts" + File.separator + "exec" + File.separator + file.getName();
                file.renameTo(new File(cNomeArquivoRenomeado));
                file.delete();
                // Move arquivo para a pasta de scripts exec
                //ManipulacaoArquivo.Arquivo_Copiar(file.getPath(),file.getPath().
            }
        }
        return bRetorno;
    }
    public static int LimparRegistrosExcluidos(Timestamp tsUltimoSinc, ArrayList arTabelas, frmMonitorProgresso monitor)
    {

        int nRetorno=0;    // Nao atualizou
        boolean bRetornoAcao=false;
        String cQueryExclusao="";
        RegistrosExcluidos rgExcluido;
        List lsRegistroExcluidos=null;

        try {
            lsRegistroExcluidos=ListaRegistrosExcluidos(tsUltimoSinc, arTabelas);
            if(lsRegistroExcluidos!=null)
            {
                if(lsRegistroExcluidos.size()>0)
                {
                    monitor.InicializarBarraProgresso(1,lsRegistroExcluidos.size());
                    for (int i = 0; i < lsRegistroExcluidos.size(); i++) {

                        rgExcluido = (RegistrosExcluidos) lsRegistroExcluidos.get(i);

                        monitor.Atualizar(i+1, "Limpando - PK: " + rgExcluido.getId() + " - Tabela : " +  rgExcluido.getTabela());

                        cQueryExclusao = "delete from dinnamus." + rgExcluido.getTabela() + " where " + rgExcluido.getChavePrimaria() + "="  + rgExcluido.getValor() ;
                        bRetornoAcao = DAO_RepositorioLocal.ExecutarComandoSimples(cQueryExclusao);
                        if(!bRetornoAcao)
                        {
                           throw new Exception("Erro na execução do comando " + cQueryExclusao);
                        }
                    }
                    nRetorno=1; //Atualizou
                }
            }
        } catch (Exception exception) {
            LogDinnamus.Log(exception);
            nRetorno=-1; // Erro
        }

        return nRetorno;
    }
    private static  List ListaRegistrosExcluidos(Timestamp tsUltimoSinc, ArrayList arTabelas)
    {

        List lsRegistroExcluidos=null;
        String cNomeTabela="";
        try {

            for (int i = 0; i < arTabelas.size(); i++) {
                 cNomeTabela= cNomeTabela +  ( i >0 && i< arTabelas.size() ? "," : "")+ "\'" + arTabelas.get(i) + "\'" ;
            }
            lsRegistroExcluidos=DAO_Servidor.PesquisarSQLNativo("SELECT * FROM RegistrosExcluidos r WHERE r.dataExclusao > '" + tsUltimoSinc + "' and r.tabela in (" + cNomeTabela + ") order by r.id",RegistrosExcluidos.class);

        } catch (Exception exception) {
            LogDinnamus.Log(exception);
        }
        return lsRegistroExcluidos;
    }
    private static File[] VerificarScriptsComplementares()
    {

        File[] f = ManipulacaoArquivo.ListarArquivos(".alt",  File.separator +  "scripts" + File.separator );

        return f;

    }
    private static boolean VerificarEstruturaBanco(String cNomeSchema)
    {
        boolean bRetorno = true;
        try {

            ResultSet rs;
            String cComandoVerificacao = "select COUNT(*) TotalTabelas	" + " from sys.systables st " + " inner join sys.sysschemas sc " + " on sc.schemaid=st.schemaid " + "where sc.schemaname='" + cNomeSchema + "'";

            rs = GerarResultSet(cComandoVerificacao);
            if(rs!=null){
              rs.next();
              if (rs.getInt("TotalTabelas") == 0) {
                    bRetorno=false;
              }
            }

        } catch (SQLException ex) {
           // Logger.getLogger(DAO_RepositorioLocal.class.getName()).log(Level.SEVERE, null, ex);
             LogDinnamus.Log(ex);
            bRetorno=false;
        }
        return bRetorno;

    }
     * *
     */
    public static boolean Commitar_Statment()
    {
        return Commitar_Statment(null);
    }
    public static boolean Commitar_Statment(Savepoint spPontoSalvamento)
    {
        boolean bRetorno = true;
        try {

            getCnRepLocal().commit();
            if (svpt1!=null){
                System.out.println("GRAVANDO TRX DERBY " +svpt1.getSavepointName() );
            }

        } catch (SQLException ex) {
            LogDinnamus.Log(ex);

            RollBack_Statment(spPontoSalvamento);

            bRetorno=false;

        }
        return bRetorno;
    }
    public static boolean LiberarSavePoint(Savepoint sv)
    {
        boolean bRet=true;
        try {
            getCnRepLocal().releaseSavepoint(sv);
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);
            bRet=false;
        }
        return bRet;
    }
    public static boolean RollBack_Statment()
    {
        return RollBack_Statment(null);
    }

    public static boolean RollBack_Statment(Savepoint sp)
    {
        boolean bRetorno = true;
        try {

            if(sp==null)
               getCnRepLocal().rollback();
            else
            {
                getCnRepLocal().rollback(sp);
                getCnRepLocal().releaseSavepoint(sp);
            }
            
            if (svpt1!=null){
                System.out.println("DESFAZENDO TRX DERBY " +svpt1.getSavepointName() );
            }


        } catch (SQLException ex) {

                LogDinnamus.Log(ex);
                bRetorno=false;

        }
        return bRetorno;
    }

    public static NamedParameter CriarNamedStatment(String cQuery)
    {
        try {
            return new NamedParameter(getCnRepLocal(),cQuery);
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);
            return null;
        }
    }
    
    
    
    public static ResultSet ListaCamposTabela(String cNomeTabela)
    {
        return ListaCamposTabela(cNomeTabela,"");
    }
    public static ResultSet ListaCamposTabela(String cNomeTabela,String cNomeCampo)
    {

        DAO_Parametro_Generico<?>[] dpgParametros=null;
        ResultSet rsCamposTabela=null;
        try {

            String cQuery="select st.TABLENAME, sc.COLUMNNAME " +
                            "from sys.SYSTABLES st " +
                                "inner join sys.SYSCOLUMNS sc " +
                                    "on sc.REFERENCEID=st.TABLEID "+
                        "where st.tablename=? " + ( cNomeCampo.length()>0 ? " sc.columnname='" +  cNomeCampo  + "'" : "" ) ;

            dpgParametros=new DAO_Parametro_Generico<?>[1];

            dpgParametros[0]=new DAO_Parametro_Generico<Object>("NomeTabela", cNomeTabela);

            rsCamposTabela=DAO_RepositorioLocal.GerarResultSet(cQuery,dpgParametros);

        } catch (Exception exception) {

             LogDinnamus.Log(exception);

        }
        return rsCamposTabela;

    }
      public static boolean verificarTabela(String cNomeTabela )
    {

        DAO_Parametro_Generico<?>[] dpgParametros=null;
        ResultSet rsCamposTabela=null;
        try {

            String cQuery="select st.TABLENAME  " +
                            "from sys.SYSTABLES st " +
                        "where st.tablename=? "  ;

            dpgParametros=new DAO_Parametro_Generico<?>[1];

            dpgParametros[0]=new DAO_Parametro_Generico<Object>("NomeTabela", cNomeTabela);

            rsCamposTabela=DAO_RepositorioLocal.GerarResultSet(cQuery,dpgParametros);
            if(rsCamposTabela!=null && rsCamposTabela.next()){
                return true;
            }
               

        } catch (Exception exception) {

             LogDinnamus.Log(exception);

        }
        return false;

    }
    public static int ContarRegistros(String cQuery)
    {
        int nTotalRegistros=0;

        ResultSet rs = GerarResultSet("select count(*) TotalRegistro from "  + cQuery);
        try {
            if (rs.next()) {
                nTotalRegistros = rs.getInt("TotalRegistro");
            }
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);
        }
        return nTotalRegistros;
    }

    /**
     * @return the cnRepLocal
     */
    public static Connection getCnRepLocal() {
        return cnRepLocal;
    }

    /**
     * @param aCnRepLocal the cnRepLocal to set
     */
    private static void setCnRepLocal(Connection aCnRepLocal) {
        cnRepLocal = aCnRepLocal;
    }
    
    public static ResultSet ListaTabela_Repositorio(String cNomeTabela)
    {

        ResultSet rsCamposTabela=null;
        try {

            String cQuery="select SYS.SYSTABLES.* from SYS.SYSTABLES INNER JOIN SYS.SYSSCHEMAS " +
            "ON SYS.SYSSCHEMAS.SCHEMAID=SYS.SYSTABLES.SCHEMAID " +
            "WHERE cast(SYS.SYSSCHEMAS.SCHEMANAME as varchar(200))='"  + cNomeSchema + "'" + (cNomeTabela.length()>0 ? " and cast(SYS.SYSTABLES.TABLENAME as varchar(200))='" +  cNomeTabela + "'": "" );

            rsCamposTabela=DAO_RepositorioLocal.GerarResultSet(cQuery);

        } catch (Exception exception) {

             LogDinnamus.Log(exception);

        }
        return rsCamposTabela;

    }
    public static boolean  IndiceExiste(String cNomeTabela, String cNomeIndice)
    {
        boolean bRetorno=false;
        try {

            String cQuery="SELECT SYS.SYSCONGLOMERATES.CONGLOMERATENAME AS NOMEINDICE FROM SYS.SYSCONGLOMERATES " +
                              "INNER JOIN SYS.SYSTABLES " +
                                "ON SYS.SYSCONGLOMERATES.TABLEID=SYS.SYSTABLES.TABLEID " +
                        "WHERE cast(SYS.SYSTABLES.TABLENAME as varchar(200))='" + cNomeTabela.toUpperCase() + "' AND SYS.SYSCONGLOMERATES.ISINDEX=true AND cast(SYS.SYSCONGLOMERATES.CONGLOMERATENAME as varchar(100))='" + cNomeIndice.toUpperCase() + "'";


            bRetorno= DAO_RepositorioLocal.GerarResultSet(cQuery,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).next();

        } catch (Exception exception) {

             LogDinnamus.Log(exception);

        }
        return bRetorno;

    }
}
