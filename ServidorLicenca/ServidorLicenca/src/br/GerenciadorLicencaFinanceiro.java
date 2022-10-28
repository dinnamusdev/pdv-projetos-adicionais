/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br;


import br.cep.CepUtil;
import br.cep.Logradouro;
import br.com.log.LogDinnamus;
import br.com.nordestefomento.jrimum.bopepo.Boleto;
import br.com.nordestefomento.jrimum.bopepo.campolivre.CampoLivre;
import br.com.nordestefomento.jrimum.bopepo.exemplo.CampoLivreSicredi;
import br.com.nordestefomento.jrimum.bopepo.view.BoletoViewer;
import br.com.nordestefomento.jrimum.domkee.comum.pessoa.endereco.CEP;
import br.com.nordestefomento.jrimum.domkee.comum.pessoa.endereco.Endereco;
import br.com.nordestefomento.jrimum.domkee.comum.pessoa.endereco.UnidadeFederativa;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Agencia;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Banco;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Carteira;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Cedente;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.CodigoDeCompensacaoBACEN;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.ContaBancaria;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.NumeroDaConta;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Sacado;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.SacadorAvalista;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.TipoDeTitulo;
import br.com.nordestefomento.jrimum.domkee.financeiro.banco.febraban.Titulo;
import br.com.servidor.Dao_Jdbc;
import br.com.util.NamedParameter;
import br.data.DataOperacoes;
import br.entidades.LicencaDadosContratante;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Fernando
 */
public class GerenciadorLicencaFinanceiro {
    public static ResultSet ListarParcelas(int IDContrato){
        return ListarParcelas(IDContrato, false);
    }
    
    public static boolean BaixaParcela(int IDFinanceiro,Float ValorPago, Date DataPagto,String cMeio){
        try {
            Dao_Jdbc.getConexao().getCNX().setAutoCommit(false);
            Dao_Jdbc.getConexao().setPontoDeSalvamento("baixarparcela");
            if(BaixaParcela_Acao(IDFinanceiro, ValorPago, DataPagto,cMeio)){
                return Dao_Jdbc.getConexao().Commitar_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
            }else{
                Dao_Jdbc.getConexao().RollBack_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return false;
    }   
    private static boolean BaixaParcela_Acao(int IDFinanceiro,Float ValorPago, Date DataPagto,String cMeio){
        try {
            
            String cSQL = "update LICENCA_DADOS_CONTRATO_FINANCEIRO set meiocobranca=:meiocobranca, pagto=:pagto,valorpago=:valorpago where idfinanceiro=:idfinanceiro";
            
            NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), cSQL);
            np.setDate("pagto", new java.sql.Date(DataPagto.getTime()));
            np.setFloat("valorpago", ValorPago);
            np.setInt("idfinanceiro", IDFinanceiro);
            np.setString("meiocobranca", cMeio);
            np.execute();
            
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
        
    }
    public static boolean DesfazerBaixaParcela(int IDFinanceiro ){
        try {
            Dao_Jdbc.getConexao().getCNX().setAutoCommit(false);
            if(DesfazerBaixaParcela_Acao(IDFinanceiro)){
                return Dao_Jdbc.getConexao().Commitar_Statment();
            }else{
                Dao_Jdbc.getConexao().RollBack_Statment();
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return false;
    }   
    private static boolean DesfazerBaixaParcela_Acao(int IDFinanceiro){
        try {

            String cSQL = "update LICENCA_DADOS_CONTRATO_FINANCEIRO set meiocobranca=null, pagto=null,valorpago=null where idfinanceiro=:idfinanceiro";

            NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), cSQL);

            np.setInt("idfinanceiro", IDFinanceiro);

            np.execute();

            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
    public static boolean ParcelaEditar(int IDfinanceiro, Float nValor , Date Emissao, Date Vencimento, String Status){
        try {
            Dao_Jdbc.getConexao().getCNX().setAutoCommit(false);
            Dao_Jdbc.getConexao().setPontoDeSalvamento("parcelaeditar");
            if(ParcelaEditar_Acao(IDfinanceiro, nValor, Emissao, Vencimento,Status)){
                return Dao_Jdbc.getConexao().Commitar_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
            }else{
                Dao_Jdbc.getConexao().RollBack_Statment(Dao_Jdbc.getConexao().getPontoDeSalvamento());
            }            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return false;
    
    }
    private static boolean ParcelaEditar_Acao(int IDfinanceiro, Float nValor , Date Emissao, Date Vencimento, String Status){
        try {
             
            String cSQL = "update LICENCA_DADOS_CONTRATO_FINANCEIRO set status =:status , emissao=:emissao,vencimento=:vencimento,valor=:valor where idfinanceiro=:idfinanceiro";

            NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), cSQL);

            np.setInt("idfinanceiro", IDfinanceiro);
            np.setFloat("valor", nValor);
            np.setDate("emissao", new java.sql.Date(Emissao.getTime()));
            np.setDate("vencimento", new java.sql.Date(Vencimento.getTime()));
            np.setString("status", Status);
            
            np.execute();
            
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
          
        }
        return false;
    }
     public static ResultSet ListarParcelas(int IDContrato, boolean Pagos){
         ResultSet rs = null;
         try {
             rs = Dao_Jdbc.getConexao().GerarResultSet("select * from licenca_dados_contrato_financeiro where idcontrato=" +  IDContrato + (Pagos ? " and not valorpago is null " : ""),  ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
         } catch (Exception e) {
              LogDinnamus.Log(e, true);
         }
         return rs;
     }
     
     public static boolean ParcelasExcluir(int IDContrato){
         
         try {
             return  Dao_Jdbc.getConexao().ExecutarSQL("delete from licenca_dados_contrato_financeiro where idcontrato=" +  IDContrato );
         } catch (Exception e) {
              LogDinnamus.Log(e, true);
         }
         return false;
     }
     
     public static String ParcelaStatus(int IDFinanceiro){
         String cRetorno="";
         try {
             ResultSet rs = Parcela(IDFinanceiro);
             if(rs.next()){
                 cRetorno=rs.getString("status");
                 
                 cRetorno=(cRetorno==null ? "ATIVO" : cRetorno);
             }
             
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return cRetorno;
     
     }
     public static boolean ParcelaPaga(int IDFinanceiro){
         try {
             ResultSet rs = Parcela(IDFinanceiro);
             if(rs.next()){
                Float nValorParcela = rs.getFloat("valor");
                Float nValorPago = rs.getFloat("valorpago");
                if(nValorPago>=nValorParcela){
                    return true;
                }                
             }             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);             
         }
         return false;
     }
     public static ResultSet Parcela(int IDFinanceiro){
         ResultSet rs = null;
         try {
             rs = Dao_Jdbc.getConexao().GerarResultSet("select * from licenca_dados_contrato_financeiro where IDFinanceiro=" +  IDFinanceiro ,  ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
         } catch (Exception e) {
              LogDinnamus.Log(e, true);
         }
         return rs;
     }
     public static boolean GerarParcelas(int IDContrato, Float nValorMensal, int NumeroParcelas, Date Emissao,Date PrimeiroVencimento, int nLoja, int nCodigoPDV){
       try {
             //Dao_Jdbc.getConexao().getCNX().setAutoCommit(false);
            boolean bRet = false;
            
            bRet=GerarParcelas_Acao(IDContrato, nValorMensal, NumeroParcelas, Emissao, PrimeiroVencimento, nLoja, nCodigoPDV);
           
            if(bRet){
                Dao_Jdbc.getConexao().Commitar_Statment();
                return true;
            }else{
                Dao_Jdbc.getConexao().RollBack_Statment();
                return false;
            }
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
        }
     }
    
    public static boolean Gerar_Boleto(Integer IDFinanceiro ){
        try {
            ResultSet rsParcela = Parcela(IDFinanceiro);
            if(rsParcela.next()){
                int IDContrato = rsParcela.getInt("idcontrato");
                ResultSet rsContrato = GerenciadorLicenca.Contrato_Listar_Por_ID(IDContrato);
                if (rsContrato.next()) {
                    int IDContratante = rsContrato.getInt("idcontratante");
                    LicencaDadosContratante contratante = GerenciadorLicenca.Contratante_Pesquisar(IDContratante);
                    
                    Cedente c = new Cedente("FZR BRITO","01.003.592/0001-68");
                    
                    Sacado s = new Sacado(contratante.getRazao());
                    Endereco e = new Endereco();                    
                    e.setUF(UnidadeFederativa.PA);                                        
                    Logradouro l= CepUtil.Pesquisar(contratante.getCep(), true);                    
                    e.setLocalidade(l.getMunicipio());
                    e.setCep(new CEP(contratante.getCep()));
                    e.setBairro(contratante.getBairro());
                    e.setLogradouro(contratante.getEndereco());
                    e.setNumero(contratante.getNumero());
                    s.addEndereco(e);
                    
                    ContaBancaria contaBancaria = new ContaBancaria(new Banco(new CodigoDeCompensacaoBACEN(415),"CEF"));
                    contaBancaria.setNumeroDaConta(new NumeroDaConta(415, "1"));
                    contaBancaria.setCarteira(new Carteira(2));
                    contaBancaria.setAgencia(new Agencia(885, "0"));
                    
                    Titulo titulo = new Titulo(contaBancaria, s, c);
                    titulo.setNumeroDoDocumento(IDFinanceiro.toString());
                    titulo.setNossoNumero("241234567890123450");
                    titulo.setDigitoDoNossoNumero("5");
                    titulo.setValor(rsParcela.getBigDecimal("valor"));
                    titulo.setDataDoDocumento(new Date());
                    titulo.setDataDoVencimento(new Date());
                    titulo.setTipoDeDocumento(TipoDeTitulo.DM_DUPLICATA_MERCANTIL);
                    titulo.setAceite(Titulo.EnumAceite.A);
                    titulo.setDesconto(BigDecimal.ZERO);
                    
                    CampoLivre campo = new CampoLivre() {
                        @Override
                        public String write() {
                            return "1234567890123456789012345";//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void read(String g) {
                            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }
                    };
                    
                    Boleto boleto = new Boleto(titulo,campo);
                    //boleto.addImagensExtras(null, null);
                    boleto.setLocalPagamento("Pagável preferencialmente nas Loterias ou em " +
                                "qualquer Banco até o Vencimento.");
                    //boleto.setCampoLivre(new CampoLivreSicredi(titulo));
                    boleto.setInstrucaoAoSacado("Senhor sacado, sabemos sim que o valor " +
                "cobrado não é o esperado, aproveite o DESCONTÃO!");
                    BoletoViewer boletoViewer = new BoletoViewer(boleto);
                    
                    File arquivoPdf = boletoViewer.getPdfAsFile("boleto_"+ IDFinanceiro.toString()  +".pdf");                            
                    
                    java.awt.Desktop desktop = java.awt.Desktop.getDesktop();
                     
                    desktop.open(arquivoPdf);                   
                }                
            }
            
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    
    } 
     
    public static boolean GerarParcelas_Acao(int IDContrato, Float nValorMensal, int NumeroParcelas, Date Emissao,Date PrimeiroVencimento, int nLoja, int nCodigoPDV){
        try {
            
            String SQL ="insert into licenca_dados_contrato_financeiro(idfinanceiro,idcontrato,valor,emissao,vencimento,status)"+
                    " values (:idfinanceiro,:idcontrato,:valor,:emissao,:vencimento,:status)";
            NamedParameter np = new NamedParameter(Dao_Jdbc.getConexao().getCNX(), SQL);
            Date Vencimento = new Date();
            for (int i = 1; i <= NumeroParcelas; i++) {
                int nPK = Dao_Jdbc.getConexao().NovoValorIdentidade("licenca_dados_contrato_financeiro",nLoja, nCodigoPDV,false).intValue();
                if(nPK==0){
                    return false;
                }
                np.setInt("idfinanceiro", nPK);
                np.setInt("idcontrato", IDContrato);
                np.setFloat("valor", nValorMensal);
                np.setDate("emissao", new java.sql.Date(Emissao.getTime()));
                np.setDate("vencimento", new java.sql.Date(PrimeiroVencimento.getTime()));
                np.setString("status", "ATIVO");
                PrimeiroVencimento = DataOperacoes.SomarMes(PrimeiroVencimento, 1);
                np.execute();
            }
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
}
