/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br;


import br.com.log.LogDinnamus;
import br.com.servidor.Dao_Jdbc;
import br.com.ui.MetodosUI_Auxiliares;
import br.com.util.NamedParameter;
import br.entidades.LicencaDadosContratante;
import br.entidades.LicencaDadosContrato;
import br.entidades.LicencaRepresentante;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import ui.frmGerenciador;

/**
 *
 * @author Fernando
 */
public class GerenciadorLicenca {
    private int nLoja=0,  nCodigoPDV =0;
    private static String Msg_Licenca_Expirada ="Licença expirada em : [$data]";
    public static boolean  Licenca_Validar(){
        return true;
    }
    public static ResultSet  Licenca_Dados(){
        return null;
    }
    public static boolean  Licenca_Expirar(){
        return true;
    }
    
    public static boolean  Licenca_Renovar(String DadosLicenca){
        return true;
    }
    
    
    public static boolean  CarregarInterfaceUsuario(int nLoja, int nCodigoPDV ){
        try {
          
                if(Licenca_Validar()){                                
                    (new frmGerenciador()).setVisible(true);
                }else{
                    MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, Msg_Licenca_Expirada, "Gerenciador de Licença", "AVISO");
                }
          
            return true;
        } catch (Exception e) {
              LogDinnamus.Log(e, true);
              return false;
        }
    }
        
    public static boolean Contratante_Localizar(String CNPJ_ou_CPF){
        try {
                
            ResultSet rs= Dao_Jdbc.getConexao().GerarResultSet("select * from LICENCA_DADOS_CONTRATANTE where CNPJ='"+  CNPJ_ou_CPF + "'", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            return rs.next();
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
    public static LicencaDadosContratante Contratante_Pesquisar(int nIdContratante){
        LicencaDadosContratante c = new LicencaDadosContratante();
        try {           
             ResultSet rs= Dao_Jdbc.getConexao().GerarResultSet("select * from LICENCA_DADOS_CONTRATANTE where IDCONTRATANTE=" +  nIdContratante, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             if(rs.next()){            
                c.setCnpj(rs.getString("CNPJ"));            
                c.setRazao(rs.getString("RAZAO"));
                c.setEndereco(rs.getString("ENDERECO"));
                c.setNumero(rs.getString("NUMERO"));
                c.setBairro(rs.getString("BAIRRO"));            
                c.setCodmunicipio(rs.getString("CODMUNICIPIO"));
                c.setCep(rs.getString("CEP"));
                c.setData(rs.getDate("DATA"));
                c.setStatus(rs.getString("STATUS"));
                c.setUf(rs.getString("UF"));
                c.setNomefantasia(rs.getString("NOMEFANTASIA"));
                c.setIdcontratante(nIdContratante);
             }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return c;
    }
    
    
    public static ResultSet Contratantes_Listar(){
        return Contratantes_Listar(0);
    }
    
    public static ResultSet Contratantes_Listar(int IDContratante){
         return Contratantes_Listar(IDContratante, 0);
    }
    public static ResultSet Contratantes_Listar(int IDContratante, int IDRepresentante){
        ResultSet rs = null;
        try { 
            
            String cSQL ="select * from LICENCA_DADOS_CONTRATANTE where IDCONTRATANTE>0 "+ (IDContratante!=0 ? " and  IDCONTRATANTE="+IDContratante : "" )+ (IDRepresentante!=0 ? " and  IDCONTRATANTE  in (select distinct idcontratante from licenca_dados_contrato where idrepresentante="+ IDRepresentante +" and status='ATIVO')" : "" ) +" order by IDCONTRATANTE";
            
            rs= Dao_Jdbc.getConexao().GerarResultSet(cSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return rs;
      
        
    }
    
    public static boolean Contratante_Excluir(int IdContratante){
        try {
             Dao_Jdbc.getConexao().setPontoDeSalvamento("contratante_excluir");
             if(Contratante_Excluir_Acao(IdContratante)){
                Dao_Jdbc.getConexao().Commitar_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
                return true;
            }else{
                Dao_Jdbc.getConexao().RollBack_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
                return false;
            }
           
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
        
    }
    private static boolean Contratante_Excluir_Acao(int IdContratante){
        try {
            
             String cSQL = "delete LICENCA_DADOS_CONTRATANTE where  IDCONTRATANTE=:IDCONTRATANTE" ;
             NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), cSQL);
             np.setInt("IDCONTRATANTE", IdContratante);
             np.execute();
             
             return true;
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
        }
        
    
    }
    public static boolean Contratante_Editar(LicencaDadosContratante contratante){
        try {
             
             Dao_Jdbc.getConexao().setPontoDeSalvamento("editar_contratante");
             
             if(Contratante_Editar_Acao(contratante)){
                Dao_Jdbc.getConexao().Commitar_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
                return true;
             }else{
                Dao_Jdbc.getConexao().RollBack_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
                return false;
             }
        } catch (Exception ex) {
            LogDinnamus.Log(ex, true);
             return false;
        }
        
    }
        private static boolean Contratante_Editar_Acao(LicencaDadosContratante contratante){
        try {
            
            
            String cSQL = "update LICENCA_DADOS_CONTRATANTE set "  +
                      "IDCONTRATANTE=:IDCONTRATANTE, CNPJ=:CNPJ, RAZAO=:RAZAO,NOMEFANTASIA=:NOMEFANTASIA, ENDERECO=:ENDERECO, NUMERO=:NUMERO, BAIRRO=:BAIRRO, CODMUNICIPIO=:CODMUNICIPIO, CEP=:CEP, DATA=:DATA, STATUS=:STATUS, UF=:UF"+
                    " where  IDCONTRATANTE=:IDCONTRATANTE";
            
            NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), cSQL);
            np.setInt("IDCONTRATANTE", contratante.getIdcontratante());
            np.setString("CNPJ", contratante.getCnpj());
            np.setString("RAZAO", contratante.getRazao());
            np.setString("NOMEFANTASIA", contratante.getNomefantasia());
            np.setString("ENDERECO", contratante.getEndereco());
            np.setString("NUMERO", contratante.getNumero());
            np.setString("BAIRRO", contratante.getBairro());            
            np.setString("CODMUNICIPIO", contratante.getCodmunicipio());
            np.setString("CEP", contratante.getCep());
            np.setDate("DATA", new Date(contratante.getData().getTime()));
            np.setString("STATUS", contratante.getStatus());
            np.setString("UF", contratante.getUf());
            
            
            np.execute();
            
            
            
            return true;
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
        }
    }
    public static boolean Contratante_Incluir(LicencaDadosContratante contratante, int nLoja, int nCodigoPDV){
        try {
             
             Dao_Jdbc.getConexao().setPontoDeSalvamento("contratante");
             if(Contratante_Incluir_Acao(contratante, nLoja, nCodigoPDV)){
                Dao_Jdbc.getConexao().Commitar_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
                return true;
            }else{
                Dao_Jdbc.getConexao().RollBack_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
                return false;
            }
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
        }
       
    }
    private static boolean Contratante_Incluir_Acao(LicencaDadosContratante contratante, int nLoja, int nCodigoPDV){
        try {
            
            
            String cSQL = "INSERT INTO LICENCA_DADOS_CONTRATANTE" +
                      "(IDCONTRATANTE, CNPJ, RAZAO,NOMEFANTASIA, ENDERECO, NUMERO, BAIRRO, CODMUNICIPIO, CEP, DATA, STATUS, UF)"+
                    "VALUES (:IDCONTRATANTE, :CNPJ, :RAZAO,:NOMEFANTASIA, :ENDERECO, :NUMERO, :BAIRRO, :CODMUNICIPIO, :CEP, :DATA, :STATUS, :UF)";
            
            NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), cSQL);
            np.setLong("IDCONTRATANTE", Dao_Jdbc.getConexao().NovoValorIdentidade("LICENCA_DADOS_CONTRATANTE",nLoja, nCodigoPDV,false));
            np.setString("CNPJ", contratante.getCnpj());
            np.setString("RAZAO", contratante.getRazao());
            np.setString("NOMEFANTASIA", contratante.getNomefantasia());
            np.setString("ENDERECO", contratante.getEndereco());
            np.setString("NUMERO", contratante.getNumero());
            np.setString("BAIRRO", contratante.getBairro());            
            np.setString("CODMUNICIPIO", contratante.getCodmunicipio());
            np.setString("CEP", contratante.getCep());
            np.setDate("DATA", new Date(contratante.getData().getTime()));
            np.setString("STATUS", contratante.getStatus());
            np.setString("UF", contratante.getUf());
             
            np.execute();
          
            return true;
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
        }
    
    }
    
    
    public static boolean Contrato_Persistir(LicencaDadosContrato contrato, int nLoja, int nCodigoPDV){
       try {
             
            boolean bRet = false;
            
            Dao_Jdbc.getConexao().setPontoDeSalvamento("contrato");
            
            if(contrato.getIdcontrato()==null){
                bRet=Contrato_Incluir_Acao(contrato, nLoja, nCodigoPDV);
            }else{
                bRet=Contrato_Atualizar_Acao(contrato, nLoja, nCodigoPDV);            
            }
            if(bRet){
                Dao_Jdbc.getConexao().Commitar_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento(),false);
                return true;
            }else{
                Dao_Jdbc.getConexao().RollBack_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
                return false;
            }
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
        }
    
    }
     public static int Contrato_Contar(int IDContratante ){
           String cSQL = "select count(*) CONTAR from LICENCA_DADOS_CONTRATO" +
                " where IDCONTRATANTE="+IDContratante;
         ResultSet rs =null;
         int nConta=0;
        try {
            
             rs = Dao_Jdbc.getConexao().GerarResultSet(cSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
             if(rs.next()){
                 nConta = rs.getInt("contar");
             }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return nConta;  
    }
     public static boolean Contrato_AtualizarAjustesData(int IDContrato){
         try {             
            if(Dao_Jdbc.getConexao().ExecutarSQL("update LICENCA_DADOS_CONTRATO set ajuste_data=isnull(ajuste_data,0) +1 where idcontrato="+ IDContrato)){
                return Dao_Jdbc.getConexao().Commitar_Statment();
             }else{
                Dao_Jdbc.getConexao().RollBack_Statment();
                return false;
            }
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
         }
     
     }
     
     public static boolean Contrato_AtualizarStatus(int IDContrato){
         try {             
            if(Dao_Jdbc.getConexao().ExecutarSQL("update LICENCA_DADOS_CONTRATO set ATIVADO=1 where idcontrato="+ IDContrato)){
                return Dao_Jdbc.getConexao().Commitar_Statment();
             }else{
                Dao_Jdbc.getConexao().RollBack_Statment();
                return false;
            }
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
         }
     
     }
     public static ResultSet Contrato_Listar(int IDContratante){
         return Contrato_Listar(IDContratante, "");
     }
    public static ResultSet Contrato_Listar_Por_ID(int IDContrato ){
        String cSQL = "select * from LICENCA_DADOS_CONTRATO" +
                " where idcontrato="+IDContrato ;
        ResultSet rs =null;
        try {
            
             rs = Dao_Jdbc.getConexao().GerarResultSet(cSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return rs;  
    }
    public static ResultSet Contrato_Listar(int IDContratante, String status ){
           String cSQL = "select * from LICENCA_DADOS_CONTRATO" +
                " where IDCONTRATANTE="+IDContratante + (status != "" ? " and status='"+ status  +"'" : "") + " order by idcontrato";
         ResultSet rs =null;
        try {
            
             rs = Dao_Jdbc.getConexao().GerarResultSet(cSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return rs;  
    }
     public static LicencaDadosContrato Contrato_Pesquisar(int IDContratante){
         return Contrato_Pesquisar(IDContratante,0);
    }
    public static LicencaDadosContrato Contrato_Pesquisar(int IDContratante, int IDContrato ){
        LicencaDadosContrato contrato = new LicencaDadosContrato();
        try {
            
            String cSQL = "select * from LICENCA_DADOS_CONTRATO" +
                " where IDCONTRATANTE="+IDContratante +  (IDContrato >0 ? " and idcontrato=" + IDContrato : "");
               
            ResultSet rs = Dao_Jdbc.getConexao().GerarResultSet(cSQL);
            contrato.setIdcontratante(IDContratante);
            if(rs.next()){                
                contrato.setIdcontrato(rs.getInt("IDCONTRATO"));
                contrato.setLicret(rs.getInt("LICRET"));
                contrato.setLicpdv(rs.getInt("LICPDV"));
                contrato.setLicpre(rs.getInt("LICPRE"));
                contrato.setIniciocontrato(rs.getDate("INICIOCONTRATO"));
                contrato.setTermimnocontrato(rs.getDate("TERMIMNOCONTRATO"));
                contrato.setPeriodicidadelib(rs.getInt("PERIODICIDADELIB"));
                contrato.setPrazoexpirarlic(rs.getInt("PRAZOEXPIRARLIC"));
                contrato.setDataemissao(rs.getDate("DATAEMISSAO"));
                contrato.setIdrepresentante(rs.getInt("idrepresentante"));
                contrato.setValorcontrato(rs.getBigDecimal("valorcontrato"));
                contrato.setStatus(rs.getString("status"));
                contrato.setVencimento(rs.getInt("VENCIMENTO"));
                contrato.setNumeroparcelas(rs.getInt("NUMEROPARCELAS"));
                contrato.setPrimeirovencimento(rs.getDate("PRIMEIROVENCIMENTO"));
                
                
            }
          
            return contrato;
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return contrato;
        }
    
    }
    
    public static boolean Contrato_Renovar(LicencaDadosContrato contrato, int nLoja, int nCodigoPDV, int nIdContratoAnterior){
       boolean bRet = false;
       try {
            
            Dao_Jdbc.getConexao().setPontoDeSalvamento("renovar");
            contrato.setStatus("ATIVO");
            bRet=Contrato_Incluir_Acao(contrato, nLoja, nCodigoPDV);
            
            if(bRet){
               bRet=Dao_Jdbc.getConexao().ExecutarSQL("update LICENCA_DADOS_CONTRATO set status ='EXPIRADO' WHERE IDCONTRATO=" + nIdContratoAnterior);                       
            }
            
            if(bRet){
                Dao_Jdbc.getConexao().Commitar_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
                return true;
            }else{
                Dao_Jdbc.getConexao().RollBack_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
                return false;
            }
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
        }
    }
    
    public static LicencaDadosContrato Gerar_Contrato_Renovado(LicencaDadosContrato contrato, int nLoja, int nCodigoPDV, int nAnos){
        try {
            Calendar c = new GregorianCalendar();
            c.setTime(contrato.getIniciocontrato());
            c.add(Calendar.YEAR, nAnos);
            contrato.setIniciocontrato(c.getTime());
            
            c.setTime(contrato.getTermimnocontrato());
            c.add(Calendar.YEAR, nAnos);
            contrato.setTermimnocontrato(c.getTime());
            
            contrato.setDataemissao(new java.util.Date());
            contrato.setIdcontrato(0);
                /*        
            if( Contrato_Incluir_Acao(contrato, nLoja, nCodigoPDV)){
                return Dao_Jdbc.getConexao().ExecutarSQL("update LICENCA_DADOS_CONTRATO set status ='EXPIRADO' WHERE IDCONTRATO="+  contrato.getIdcontrato());
            }           */ 
           return contrato;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return null;
        }
    }
    private static boolean Contrato_Incluir_Acao(LicencaDadosContrato contrato, int nLoja, int nCodigoPDV){
        try {
            
            
            String cSQL = "INSERT INTO LICENCA_DADOS_CONTRATO" +
                "(IDCONTRATO, IDCONTRATANTE, LICRET, LICPDV, LICPRE, INICIOCONTRATO, TERMIMNOCONTRATO, PERIODICIDADELIB, PRAZOEXPIRARLIC,DATAEMISSAO,IDREPRESENTANTE,VALORCONTRATO,STATUS,VENCIMENTO,PRIMEIROVENCIMENTO,NUMEROPARCELAS)"+
                  "values (:IDCONTRATO, :IDCONTRATANTE, :LICRET, :LICPDV, :LICPRE, :INICIOCONTRATO, :TERMIMNOCONTRATO, :PERIODICIDADELIB, :PRAZOEXPIRARLIC,:DATAEMISSAO,:IDREPRESENTANTE,:VALORCONTRATO,:STATUS,:VENCIMENTO,:PRIMEIROVENCIMENTO,:NUMEROPARCELAS)";
            
            
            int nPK = Dao_Jdbc.getConexao().NovoValorIdentidade("LICENCA_DADOS_CONTRATO",nLoja, nCodigoPDV,false).intValue();
            if(nPK==0){
                return false;
            }
            NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), cSQL);
            np.setLong("IDCONTRATO",nPK);
            np.setLong("IDCONTRATANTE", contrato.getIdcontratante());
            np.setInt("LICRET", contrato.getLicret());
            np.setInt("LICPDV", contrato.getLicpdv());
            np.setInt("LICPRE", contrato.getLicpre());
            np.setDate("INICIOCONTRATO", new Date(contrato.getIniciocontrato().getTime()));
            np.setDate("TERMIMNOCONTRATO", new Date(contrato.getTermimnocontrato().getTime()));
            np.setInt("PERIODICIDADELIB", contrato.getPeriodicidadelib());
            np.setInt("PRAZOEXPIRARLIC", contrato.getPrazoexpirarlic());
            np.setDate("DATAEMISSAO",  new Date(contrato.getDataemissao().getTime()));
            np.setInt("IDREPRESENTANTE", contrato.getIdrepresentante());
            np.setBigDecimal("VALORCONTRATO", contrato.getValorcontrato());
            np.setString("STATUS", contrato.getStatus());
            np.setInt("VENCIMENTO", contrato.getVencimento());
            np.setDate("PRIMEIROVENCIMENTO", new Date(contrato.getPrimeirovencimento().getTime()));
            np.setInt("NUMEROPARCELAS", contrato.getNumeroparcelas());
            np.execute();
            
            if(!GerenciadorLicencaFinanceiro.ListarParcelas(nPK).next()){
                if (!GerenciadorLicencaFinanceiro.GerarParcelas_Acao(nPK, contrato.getValorcontrato().floatValue(), contrato.getNumeroparcelas(), contrato.getDataemissao(), contrato.getPrimeirovencimento(), nLoja, nCodigoPDV)){
                    return false;
                }
            }   
            if(GerenciadorLicencaLiberacao.Liberacoes_Gerar(nPK,contrato.getPrimeirovencimento(), contrato.getIniciocontrato(),contrato.getTermimnocontrato(),contrato.getPeriodicidadelib(),contrato.getPrazoexpirarlic())){
                return true;
            }
            
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
            
        }
         return false;
    
    }
    private static boolean Contrato_Atualizar_Acao(LicencaDadosContrato contrato, int nLoja, int nCodigoPDV){
        try {
            
            
            String cSQL = "update LICENCA_DADOS_CONTRATO set " +
                      "  LICRET=:LICRET, LICPDV=:LICPDV, LICPRE=:LICPRE, INICIOCONTRATO=:INICIOCONTRATO," +
                    " TERMIMNOCONTRATO=:TERMIMNOCONTRATO, PERIODICIDADELIB=:PERIODICIDADELIB, PRAZOEXPIRARLIC=:PRAZOEXPIRARLIC,"+
                    "DATAEMISSAO=:DATAEMISSAO,IDREPRESENTANTE=:IDREPRESENTANTE,VALORCONTRATO=:VALORCONTRATO,STATUS=:STATUS,VENCIMENTO=:VENCIMENTO,PRIMEIROVENCIMENTO=:PRIMEIROVENCIMENTO,NUMEROPARCELAS=:NUMEROPARCELAS"+
                  " where IDCONTRATO=:IDCONTRATO";
            NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), cSQL);           
            np.setInt("IDCONTRATO", contrato.getIdcontrato());
            np.setInt("LICRET", contrato.getLicret());
            np.setInt("LICPDV", contrato.getLicpdv());
            np.setInt("LICPRE", contrato.getLicpre());
            np.setDate("INICIOCONTRATO", new Date(contrato.getIniciocontrato().getTime()));
            np.setDate("TERMIMNOCONTRATO", new Date(contrato.getTermimnocontrato().getTime()));
            np.setInt("PERIODICIDADELIB", contrato.getPeriodicidadelib());
            np.setInt("PRAZOEXPIRARLIC", contrato.getPrazoexpirarlic());
            np.setDate("DATAEMISSAO",  new Date(contrato.getDataemissao().getTime()));
            np.setInt("IDREPRESENTANTE", contrato.getIdrepresentante());
            np.setBigDecimal("VALORCONTRATO", contrato.getValorcontrato()); 
            np.setString("STATUS", contrato.getStatus());
            np.setInt("VENCIMENTO", contrato.getVencimento());
            np.setInt("NUMEROPARCELAS", contrato.getNumeroparcelas());
            np.setDate("PRIMEIROVENCIMENTO",  new Date(contrato.getPrimeirovencimento().getTime()));
            np.execute();         
            
            int nPK = contrato.getIdcontrato();
            
            if(GerenciadorLicencaFinanceiro.ListarParcelas(nPK).next()){
                if(!GerenciadorLicencaFinanceiro.ParcelasExcluir(nPK)){
                    return false;
                }
            }    
            if (!GerenciadorLicencaFinanceiro.GerarParcelas_Acao(nPK, contrato.getValorcontrato().floatValue(), contrato.getNumeroparcelas(), contrato.getDataemissao(), contrato.getPrimeirovencimento(), nLoja, nCodigoPDV)){
                return false;
            }
            
            if(GerenciadorLicencaLiberacao.Liberacoes_Deletar(contrato.getIdcontrato())){
                if(GerenciadorLicencaLiberacao.Liberacoes_Gerar(nPK,contrato.getPrimeirovencimento(), contrato.getIniciocontrato(),contrato.getTermimnocontrato(),contrato.getPeriodicidadelib(),contrato.getPrazoexpirarlic())){
                    return true;
                }
            }
                  
            
            
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
            
        }
         return false;
    
    }
    
    public static ResultSet UF_Listar(){
        ResultSet rs = null;
        try {
            rs= Dao_Jdbc.getConexao().GerarResultSet("SELECT codigo,nome FROM CEP_UF order by nome", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return rs;
      
        
    }
    public static ResultSet UF_MunicipioListar(String cUF){
        return UF_MunicipioListar(cUF,"");
    }
    public static ResultSet UF_MunicipioListar(String cUF, String CodMunicipio){
        ResultSet rs = null;
        try {
            String cSQL = "select distinct cm.codigo,ltrim(rtrim( cm.municipio)) municipio " +
                            "from cep c " +
                                "inner join cep_municipio cm " +
                                    "on c.codmunicipio=cm.codigo " +
                            "where c.uf='" + cUF +"' " +
                            ("".equals(CodMunicipio) ? "" : " and cm.codigo=" + CodMunicipio + " " ) +
                            "order by ltrim(rtrim( cm.municipio))";
            
            rs= Dao_Jdbc.getConexao().GerarResultSet(cSQL, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return rs;
      
        
    }
    public static boolean Representante_Localizar(String CNPJ_ou_CPF){
        try {
                
            ResultSet rs= Dao_Jdbc.getConexao().GerarResultSet("select * from LICENCA_REPRESENTANTE where CNPJ='"+  CNPJ_ou_CPF + "'", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            return rs.next();
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
private static boolean Representante_Incluir_Acao(LicencaRepresentante representante, int nLoja, int nCodigoPDV){
        try {
            
            
           String cSQL = "INSERT INTO LICENCA_REPRESENTANTE" +
                      "(IDREPRESENTANTE, CNPJ, RAZAO, ENDERECO, NUMERO, BAIRRO, CODMUNICIPIO, CEP, UF,DTCADASTRO,COMISSAO,OBS,EMAIL,STATUS)"+
                    "VALUES (:IDREPRESENTANTE, :CNPJ, :RAZAO, :ENDERECO, :NUMERO, :BAIRRO, :CODMUNICIPIO, :CEP, :UF,:DTCADASTRO,:COMISSAO,:OBS,:EMAIL,:STATUS)";
             
            int nPK = Dao_Jdbc.getConexao().NovoValorIdentidade("LICENCA_REPRESENTANTE",nLoja, nCodigoPDV).intValue();
            if(nPK==0){
                return false;
            }
            NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), cSQL);
            np.setLong("IDREPRESENTANTE", nPK);         
            np.setString("CNPJ", representante.getCnpj());
            np.setString("RAZAO", representante.getRazao());
            np.setString("ENDERECO", representante.getEndereco());
            np.setString("NUMERO", representante.getNumero());
            np.setString("BAIRRO", representante.getBairro());            
            np.setString("CODMUNICIPIO", representante.getCodmunicipio());
            np.setString("CEP", representante.getCep());
            np.setDate("DTCADASTRO", new Date(representante.getDtcadastro().getTime()));
            np.setString("UF", representante.getUf());
            np.setFloat("COMISSAO", representante.getComissao().floatValue());
            np.setString("OBS", representante.getObs());
            np.setString("EMAIL", representante.getEmail());
            np.setString("STATUS", representante.getStatus());            
            
            np.execute();
        
            return true;
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
        }
    
    }
public static boolean Representante_Excluir(int idrepresentante){
    try {
          String cSQL = "DELETE LICENCA_REPRESENTANTE " + 
                  "WHERE IDREPRESENTANTE=:IDREPRESENTANTE";
          NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), cSQL);           
          
          np.setLong("IDREPRESENTANTE",idrepresentante);
          
          np.execute();
          
        return true;
    } catch (Exception e) {
        LogDinnamus.Log(e, true);
        return false;
    }

}
    private static boolean Representante_Atualizar_Acao(LicencaRepresentante representante, int nLoja, int nCodigoPDV){
        try {
            
            
                 String cSQL = "UPDATE LICENCA_REPRESENTANTE SET " +
                      "CNPJ=:CNPJ, RAZAO=:RAZAO, ENDERECO=:ENDERECO, NUMERO=:NUMERO, BAIRRO=:BAIRRO, CODMUNICIPIO=:CODMUNICIPIO, CEP=:CEP, " + 
                         "UF=:UF,DTCADASTRO=:DTCADASTRO,COMISSAO=:COMISSAO,OBS=:OBS,EMAIL=:EMAIL,STATUS=:STATUS "+ 
                         "WHERE IDREPRESENTANTE=:IDREPRESENTANTE";
            NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), cSQL);           
            np.setLong("IDREPRESENTANTE",representante.getIdrepresentante());
            np.setString("CNPJ", representante.getCnpj());
            np.setString("RAZAO", representante.getRazao());
            np.setString("ENDERECO", representante.getEndereco());
            np.setString("NUMERO", representante.getNumero());
            np.setString("BAIRRO", representante.getBairro());            
            np.setString("CODMUNICIPIO", representante.getCodmunicipio());
            np.setString("CEP", representante.getCep());
            np.setDate("DTCADASTRO", new Date(representante.getDtcadastro().getTime()));
            np.setString("UF", representante.getUf());
            np.setFloat("COMISSAO", representante.getComissao().floatValue());
            np.setString("OBS", representante.getObs());
            np.setString("EMAIL", representante.getEmail());
            np.setString("STATUS", representante.getStatus());
               
            np.execute();
      
            
            return true;
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
        }
    
    }    
    public static LicencaRepresentante Representante_Pesquisar(int nIDRepresentante){
        LicencaRepresentante rep = new LicencaRepresentante();
        try {
            ResultSet rs =  Representante_Listar(nIDRepresentante);
            if(rs.next()){
                rep.setIdrepresentante(nIDRepresentante);
                rep.setCnpj( rs.getString("CNPJ"));            
                rep.setRazao(rs.getString("RAZAO"));
                rep.setEndereco(rs.getString("ENDERECO"));
                rep.setNumero( rs.getString("NUMERO"));
                rep.setBairro(rs.getString("BAIRRO"));
                rep.setCodmunicipio(rs.getString("CODMUNICIPIO"));
                rep.setCep(rs.getString("CEP"));
                rep.setDtcadastro(rs.getDate("DTCADASTRO"));
                rep.setUf( rs.getString("UF"));
                rep.setComissao( rs.getBigDecimal("COMISSAO"));
                rep.setObs( rs.getString("OBS"));
                rep.setEmail(rs.getString("EMAIL"));               
                rep.setStatus(rs.getString("STATUS"));      
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);           
        }
        return rep;
    }
    public static ResultSet Representante_Listar(){
        return Representante_Listar(0);
    }
    public static ResultSet Representante_Listar(int nIDRepresentante){
        ResultSet rs =null;
        try {
            
            rs = Dao_Jdbc.getConexao().GerarResultSet("SELECT  * FROM LICENCA_REPRESENTANTE" + (nIDRepresentante>0 ? " where idrepresentante=" + nIDRepresentante : ""),ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
             
        }
        return rs;
    }
    public static boolean Representante_Persistir(LicencaRepresentante rep, int nLoja, int nCodigoPDV){
       try {
             
            boolean bRet = false;
            
            Dao_Jdbc.getConexao().setPontoDeSalvamento("representante");
            if(rep.getIdrepresentante()==0){
                bRet=Representante_Incluir_Acao(rep, nLoja, nCodigoPDV);
            }else{
                bRet=Representante_Atualizar_Acao(rep, nLoja, nCodigoPDV);            
            }
            if(bRet){
                Dao_Jdbc.getConexao().Commitar_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
                return true;
            }else{
                Dao_Jdbc.getConexao().RollBack_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
                return false;
            }
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
        }
    
    }
}
