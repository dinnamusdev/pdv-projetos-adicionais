
/*
 * frmPDV.java
 *
 * Created on 26/05/2010, 17:54:58
 */

package dinnamus.ui.InteracaoUsuario.Venda;


import br.com.ecf.ECFDinnamuS;
import br.com.info.Sistema;
import br.com.log.LogDinnamus;
import br.com.repositorio.DAO_RepositorioLocal;
import com.toedter.calendar.JDateChooser;
import dinnamus.entidades.cadastro.Dadosorc;
import dinnamus.entidades.cadastro.Itensorc;
import dinnamus.entidades.recursos.Recursos;
import br.diversos.ControlarDigitacao;
import br.data.DataHora;
import br.valor.formatar.FormatarNumero;
import br.String.ManipulacaoString;
import br.data.ManipularData;
import br.arredondar.NumeroArredondar;
import br.TratamentoNulo.TratamentoNulos;
import br.com.ui.ItemLista;
import br.ui.teclas.controleteclas;
import dinnamus.infraestrutura.sinc.SincronizarMovimento;
import dinnamus.infraestrutura.sinc.VerificarStatusServidor;

import dinnamus.metodosnegocio.entidadesbasicas.TiposdePagamentos;
import dinnamus.metodosnegocio.entidadesbasicas.cadproduto;
import dinnamus.metodosnegocio.entidadesbasicas.clientes;
import dinnamus.metodosnegocio.entidadesbasicas.tabeladepreco;
import dinnamus.metodosnegocio.entidadesbasicas.vendedor;
import dinnamus.metodosnegocio.seguranca.UsuarioAuditar;
import dinnamus.metodosnegocio.seguranca.UsuarioSistema;
import dinnamus.metodosnegocio.venda.caixa.ComprovanteNaoFiscal;
import dinnamus.metodosnegocio.venda.caixa.GerenciarCaixa;
import dinnamus.metodosnegocio.venda.caixa.PDVComprovante;
import dinnamus.metodosnegocio.venda.infraestrura.Crediario;
import dinnamus.metodosnegocio.venda.infraestrura.FormatadorDeTexto;
import dinnamus.metodosnegocio.venda.infraestrura.TratarCodigoBalanca;
import dinnamus.metodosnegocio.venda.infraestrura.Venda;
import dinnamus.metodosnegocio.venda.infraestrura.VendaEmEdicao;
import dinnamus.metodosnegocio.venda.infraestrura.pdvgerenciar;
import dinnamus.ui.InteracaoUsuario.Estoque.frmPesquisarProduto;
import dinnamus.ui.InteracaoUsuario.MetodosUI_Auxiliares;
import dinnamus.ui.InteracaoUsuario.Seguranca.ValidarAcessoAoProcesso;
//import br.com.ui.controleteclas;
import dinnamus.dao.repositorio_local.DAO_RepositorioLocal_Inicializar;
import dinnamus.entidades.cadastro.Usuario;
import dinnamus.metodosnegocio.venda.infraestrura.DadosorcRN;
import dinnamus.metodosnegocio.venda.infraestrura.ItensorcRN;
import dinnamus.metodosnegocio.venda.infraestrura.PagtoorcRN;
import dinnamus.metodosnegocio.venda.infraestrura.ParcorcRN;
import dinnamus.ui.metodosauxiliares.SwingUtilidade;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.PatternSyntaxException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.SimpleAttributeSet;

/**
 *
 * @author dti
 */

public class frmPDV extends javax.swing.JDialog {

    /** Creates new form frmPDV */
    private static String ControleCX="";
    private static Dadosorc dadosorc=null;
    private static Itensorc itensorc=null;
    private static ArrayList<Itensorc> arItensorc=new ArrayList<Itensorc>();
    private static Integer nCodigoFilial=0;
    private static Integer nCodigoObjetoCaixa=0;
    private static ECFDinnamuS EcfDinnmus = new ECFDinnamuS();
    private static String NomeImpressoraComprovante="";
    private static boolean bParaThread =false;
    private static boolean bAtualizacaoPedente =false;
    private static ResultSet rsDescontoAtacado = null;
          

    /**
     * @return the nDescontoLiberado
     */
    public static Float getnDescontoLiberado() {
        
        return nDescontoLiberado;

    }

    /**
     * @param anDescontoLiberado the nDescontoLiberado to set
     */
    public static void setnDescontoLiberado(Float anDescontoLiberado) {
        nDescontoLiberado = anDescontoLiberado;
    }

    /**
     * @return the ControleCX
     */
    public static String getControleCX() {
        return ControleCX;
    }

    /**
     * @param aControleCX the ControleCX to set
     */
    public static void setControleCX(String aControleCX) {
        ControleCX = aControleCX;
    }

    /**
     * @return the EcfDinnmus
     */
    public static ECFDinnamuS getEcfDinnmus() {
        return EcfDinnmus;
    }

    /**
     * @param aEcfDinnmus the EcfDinnmus to set
     */
    public static void setEcfDinnmus(ECFDinnamuS aEcfDinnmus) {
        EcfDinnmus = aEcfDinnmus;
    }

    /**
     * @return the NomeImpressoraComprovante
     */
    public static String getNomeImpressoraComprovante() {
        return NomeImpressoraComprovante;
    }

    /**
     * @param aNomeImpressoraComprovante the NomeImpressoraComprovante to set
     */
    public static void setNomeImpressoraComprovante(String aNomeImpressoraComprovante) {
        NomeImpressoraComprovante = aNomeImpressoraComprovante;
    }
    
    private frmPDVItensVendidos itensVendidos=null;
    
    private static Float nDescontoLiberado=0f;
    //private static JFrame framebasico=null;
    frmClienteListagem formTabela;

    public boolean InicializarForm()
    {
        VerificarStatusServidor.setVerificarServidor(true);
        if(!InicializarUI())
        {
           //MetodosUI_Auxiliares.MensagemAoUsuarioSimples((JFrame) parent, "Não foi possível inicializar a UI", "UI Não inicializada", "AVISO");
           this.dispose();
           //this.setVisible(false);
           return false;
        }
        else
        {
            formTabela = new frmClienteListagem(null, true,false);
            controleteclas.SetarTodosOsBotoes(this.getContentPane());
            //controleteclas.UsarTeclaNaTrocaDeCampos(PainelSubtotal5, KeyEvent.VK_ENTER);
            if(UsuarioSistema.getIdCaixaAtual()==null){
                MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O operador não possui um caixa aberto", "Pdv DinnamuS", "AVISO");
                this.dispose();
                return false;
            }
            else
            {
            ResultSet rs = GerenciarCaixa.ListarCaixas(Sistema.getLojaAtual(), 0, pdvgerenciar.CaixaVinculado());
            try {
                if (rs.next()) {
                        setControleCX(rs.getString("controlecx"));
                }
            } catch (SQLException ex) {
                LogDinnamus.Log(ex);
            }
            this.setLocationRelativeTo(null);
            
            this.setVisible(true);
            
            }
            return true;
        }
    }
    public frmPDV(java.awt.Frame parent, boolean modal) {
        //super(parent, modal);
        //this.parent=parent;
        this.setUndecorated(false);
        initComponents();
        //Toolkit tk = Toolkit.getDefaultToolkit();           
        // Dimension screenSize = tk.getScreenSize();  
        //this.setSize(screenSize.width, screenSize.height); 
        
         this.setLocationRelativeTo(null);          
         this.InicializarForm();
         //this.setVisible(true);
  
    }



    private void LimparCampoValoresProdutos() {
        txtSubTotal.setText("");
        txtQuantidade.setText("");
        txtPreco.setText("");
        txtNomeProduto.setText("");
    }

    private void LimparCamposClientes() {
        
        txtNomeCliente.setText("");
    }
    public boolean AtualizarSubTotal()
    {
        boolean bRet=false;



        return bRet;
    }
    public Long RegistraItem_RegistrarEntidades_Dadosorc()
    {
        Long nRet=Long.valueOf(0);
        Date dDataAbertura;
        Integer nCodigoVendedor, nCodigoCliente,  nCodigoOperador;
        String cControleCX="";
        String cNomeVendedor,cNomeCliente,cNomeOperador;
        try {

            //DataHora.getStringToData(cControleCX)
            dDataAbertura=  DataHora.getStringToData(lblData.getText(),"dd/MM/yyyy");
            nCodigoVendedor= Integer.parseInt(((ItemLista) cbVendedor.getSelectedItem()).getIndice().toString());
            cNomeVendedor=((ItemLista) cbVendedor.getSelectedItem()).getDescricao();
            nCodigoOperador=UsuarioSistema.getIdUsuarioLogado();
            cNomeOperador=txtOperador.getText();
            nCodigoCliente= 0;//Integer.valueOf(getDadosorc().getCodcliente()) ;
            cNomeCliente="";
            
            nRet=RegistraItem_RegistrarEntidades_Dadosorc_Persistencia( dDataAbertura, nCodigoVendedor,cNomeVendedor, nCodigoCliente, cNomeCliente, nCodigoOperador, cNomeOperador,Sistema.getLojaAtual(), UsuarioSistema.getIdUsuarioLogadoCaixa(), UsuarioSistema.getIdCaixaAtual(), getControleCX(), pdvgerenciar.CodigoPDV());

        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return nRet;
    }
    public Long RegistraItem_RegistrarEntidades_Dadosorc_Persistencia(Date dDataAbertura,Integer nCodigoVendedor,String cNomeVendedor, Integer nCodigoCliente, String cNomeCliente, Integer nCodigoOperador, String cNomeOperador, Integer nLoja, Integer nCodigoOpCaixa, Integer nCodigoObjetoCaixa, String cControleCX, int nCodigoPDV )
    {
        Long nRetorno=Long.valueOf(0);
        try {

            getDadosorc().setCodigo(DAO_RepositorioLocal.NovoValorIdentidade("dadosorc", Sistema.getLojaAtual(),nCodigoPDV));
            getDadosorc().setData(dDataAbertura);            
            getDadosorc().setHora(Timestamp.valueOf(DataHora.getDataHoraAtual()));
            getDadosorc().setLoja(nLoja);
            getDadosorc().setFilial(nCodigoFilial);
            getDadosorc().setCodoperador(nCodigoOperador.toString());
            getDadosorc().setOperador(cNomeOperador);
            getDadosorc().setVendedor(nCodigoVendedor.toString());
            getDadosorc().setCodvendedor(nCodigoVendedor.toString());
            getDadosorc().setControleCx("");
            getDadosorc().setObjetoCaixa(0);
            getDadosorc().setCodcaixa(0);
            getDadosorc().setRecebido("N");
            getDadosorc().setFeito("S");
            getDadosorc().setPdv(nCodigoPDV);
            nRetorno= DadosorcRN.Dadosorc_Incluir(DAO_RepositorioLocal.getCnRepLocal(), dadosorc, 0,true,true);

        } catch (Exception e) {

            LogDinnamus.Log(e);
        }
        return nRetorno;
    }
   


    private SimpleAttributeSet FormatoDoCorpo()
    {
        SimpleAttributeSet atr;
        atr=FormatadorDeTexto.FonteNome("Courier New");
        atr.addAttributes(FormatadorDeTexto.FonteTamanho(10));
        return atr;
    }
    private SimpleAttributeSet FormatoDoCabacalho()
    {
        SimpleAttributeSet atr;
        atr=FormatadorDeTexto.FonteNome("Courier New");
        atr.addAttributes(FormatadorDeTexto.FonteTamanho(18));
        atr.addAttributes(FormatadorDeTexto.FonteNegrito());
        return atr;
    }
    public boolean  RegistrarItem_Tela_CabecaNota(Dadosorc d, ResultSet rsDadosEmpresa, String cTipoNota )
    {
        boolean bRet=false;
        String cDados="";
        try {
            //cDados= rsDadosEmpresa.getString("Nome");
            SimpleAttributeSet atr;

            cDados = ComprovanteNaoFiscal.CabecaNota_Conteudo( d,  rsDadosEmpresa,  cTipoNota,false,null );

            bRet=EscreverNaTela(cDados,FormatoDoCorpo());

        } catch (Exception e) {

            LogDinnamus.Log(e);
        }
        return bRet;
    }
    

    public boolean EscreverNaTela(String cTexto)
    {
        return EscreverNaTela( cTexto, null);
    }
    public boolean EscreverNaTela(String cTexto, SimpleAttributeSet attributeSet)
    {
        try {

            ///SimpleAttributeSet atrib =new SimpleAttributeSet();
            //atrib.addAttributes(atrib);
            txtItens.getStyledDocument().insertString(txtItens.getStyledDocument().getLength(), cTexto , attributeSet);

            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return false;
    
    }
    public boolean RegistrarITem_SomarTotalNotas()
    {
        boolean bRet=false;

        try {
            BigDecimal nValorTotal=new BigDecimal("0");
            String cTipoItem="";
            for (int i=0 ;i<=getArItensorc().size()-1;i++) {
                cTipoItem=TratamentoNulos.getTratarString().Tratar( getArItensorc().get(i).getCodmov(),"");
                if(cTipoItem.equalsIgnoreCase("")){
                   nValorTotal=nValorTotal.add(getArItensorc().get(i).getTotal());
                }
            }
            txtTotal.setValue(nValorTotal);
            bRet=true;
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return bRet;
    }
    public boolean RefazerNota()
    {
        boolean bRet=false;
        txtItens.setText("");
        if(ReEscreverItensNota())
            if(RegistrarITem_SomarTotalNotas())
                bRet=true;

        return bRet;
    }
    public static Itensorc SetarItensorc( Dadosorc dDadosorc,int nSeq , Long nItensorc_Idunico ,Long nIG_ChaveUnica, String cCodigoProduto,String cNomeProduto, Double nQuantidade, Double nPreco, Double nSubTotal, String cSituacaoTributaria, String cAliquota, Float nPercentualIcms, int nCodigoLoja, String cTabela, Long nCodigoReexibicao, int nCodigoPDV, boolean  bFracionado, String cCodMov, String Unidade, Float Custo, String NCM)
    {
        Itensorc i=null;
        try {
            i=new Itensorc();
            //i.setIdunico(DAO_RepositorioLocal.NovoValorIdentidade("itensorc", Sistema.getLojaAtual(),nCodigoPDV));
            i.setCodigo(dDadosorc);
            i.setCodprod(nIG_ChaveUnica);
            i.setRef(cCodigoProduto);
            i.setNomeImpresso(cNomeProduto);
            i.setDescricao(cNomeProduto);
            i.setCodaliquota(cAliquota);
            i.setIcms(nPercentualIcms.toString());
            i.setPreco(new BigDecimal(nPreco.toString()));
            i.setPrecooriginal(i.getPreco());
            i.setQuantidade(new BigDecimal(nQuantidade.toString()));
            i.setData(ManipularData.DataAtual());
            i.setTotal(new BigDecimal(nSubTotal.toString()));
            i.setLiquido(new BigDecimal(nSubTotal.toString()));
            i.setTabela(cTabela);
            i.setSt(cSituacaoTributaria);
            i.setLoja(nCodigoLoja);
            i.setFracionado(bFracionado);
            i.setCodmov(cCodMov);
            i.setUnidade(Unidade);
            i.setNcm(NCM);
            i.setCusto(new BigDecimal(Custo.toString()));
            if(nCodigoReexibicao==0){
               i=Venda.RegistraItem_RegistrarEntidades_Itensorc_Persistencia(i,nCodigoReexibicao);
            }else{
                i.setSeq(nSeq);
                i.setIdunico(nItensorc_Idunico);
            }

            if(i.getIdunico()==0){
               return null;
            }

        } catch (Exception e) {
            i=null;
            LogDinnamus.Log(e);
        }
        return i;
    }
    public boolean ReEscreverItensNota()
    {

        boolean bRet=false;
        try {

            Itensorc itensorc=null;
            bRet=RegistrarItem_Tela_CabecaNota (getDadosorc(),Sistema.getDadosLojaAtualSistema(),btTipoComprovante.getText());
            for (int i = 0; i < arItensorc.size(); i++) {
                itensorc = arItensorc.get(i);
                if(!EscreverNaTela(ComprovanteNaoFiscal.RegistrarItem_Tela_Itens(itensorc.getSeq().toString(),
                        itensorc.getDescricao(),
                        itensorc.getRef(),
                        itensorc.getPreco().doubleValue(),
                        itensorc.getQuantidade().doubleValue(),
                        (itensorc.getDescv()==null ? 0 : itensorc.getDescv().floatValue()), itensorc.getTotal().doubleValue()),FormatoDoCorpo()))
                        return false;
            }
            bRet=true;
        }
        catch (Exception e) {
            LogDinnamus.Log(e);
        }


        return bRet;
    }
    
    public boolean RegistraItem(Integer nSeq , Long nItensorc_Idunico,Long nIG_ChaveUnica, String cCodigoProduto,String cNomeProduto, Double nQuantidade, Double nPreco,Float nDesc ,Double nSubTotal, String cSituacaoTributaria, String cAliquota, Float nPercentualIcms, String cTipoComprovante, String cTabela, Long nCodigoVendaReexibir, String cTipoComprovanteReexibido, boolean bContinuarImpressao, int nCodigoPDV, boolean bFracionado, boolean bItemCancelado,String cCodMov,String Unidade, Float Custo, String NCM)
    {
        boolean bRet=false;
        String cDadosCabecaNota="";
                
        try {
            if(arItensorc.size()==0 )
            {
                txtItens.setText("");
                Long nCodigoVenda = Long.valueOf(0);
                if(nCodigoVendaReexibir!=0){
                    nCodigoVenda = nCodigoVendaReexibir;
                }else{
                    nCodigoVenda=RegistraItem_RegistrarEntidades_Dadosorc();
                }
                if(nCodigoVenda!=0){
                    bRet=true;
                    if(bRet){
                        getDadosorc().setCodigo(nCodigoVenda);
                        getDadosorc().setPdv(pdvgerenciar.CodigoPDV());
                        getDadosorc().setObjetoCaixa(nCodigoObjetoCaixa);
                        getDadosorc().setCodoperador(UsuarioSistema.getIdUsuarioLogado().toString());
                        getDadosorc().setOperador(UsuarioSistema.getNomeUsuario());
                        txtSequencia1.setText(nCodigoVenda.toString());
                        bRet=RegistrarItem_Tela_CabecaNota (getDadosorc(),Sistema.getDadosLojaAtualSistema(),cTipoComprovante);
                    }
                }else{
                    return false;
                }
            }
            else
            {
                bRet=true;
            }
            if(bRet){
                bRet=false;
                if(nPreco==0f){
                    MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O produto não pode ser registrado sem preco", "Produto sem Preço", "AVISO");
                    return false;
                }
                if(cCodigoProduto.trim()==""){
                    MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O produto não pode ser registrado sem um codigo valido", "Produto sem Código", "AVISO");
                    return false;
                }
                
                Itensorc i = SetarItensorc(getDadosorc(), nSeq,nItensorc_Idunico,nIG_ChaveUnica,  cCodigoProduto, cNomeProduto,  nQuantidade,  nPreco,  nSubTotal,  cSituacaoTributaria,  cAliquota, nPercentualIcms, Sistema.getLojaAtual() , cTabela, nCodigoVendaReexibir,nCodigoPDV,bFracionado, cCodMov,Unidade, Custo,NCM);
                if(i!=null){
                    if(i.getIdunico()!=0){
                    bRet=true;

                    if(getArItensorc().size()==0 ){
                        String cTipoComprovantePDV="";
                        if(cTipoComprovanteReexibido.equalsIgnoreCase("")){
                            if(cTipoComprovante.equalsIgnoreCase("CUPOM FISCAL.")){
                                cTipoComprovantePDV="fiscal";
                            }else if(!cTipoComprovante.equalsIgnoreCase("")){
                                cTipoComprovantePDV="nfiscal";
                            }else{
                                btImprimirComprovante.setSelected(false);
                            }
                        }else{
                            cTipoComprovantePDV = cTipoComprovanteReexibido;
                        }
                        if(btImprimirComprovante.isSelected()){
                          if(PDVComprovante.Iniciar(cTipoComprovantePDV,getEcfDinnmus(),getNomeImpressoraComprovante())){
                             if(!bContinuarImpressao){
                                if(!PDVComprovante.AbrirCupom( "",getDadosorc(),Sistema.getDadosLojaAtualSistema(),cTipoComprovante,false,null)){
                                   ApagarNota(true);
                                   return false;
                                }
                            }else{
                                PDVComprovante.setTipoComprovante(cTipoComprovante);
                                cTipoComprovantePDV=cTipoComprovante;
                            }
                          }else{
                               // Nao inicio o comprovante
                               ApagarNota(true);
                               return false;
                          }
                        }
                        if(nCodigoVendaReexibir==0){
                            VendaEmEdicao.RegistrarVendaEmEdicao(getDadosorc().getCodigo(),( btImprimirComprovante.getText().equalsIgnoreCase("Imprimir Comprovante - [ OFF ]") ? "" : cTipoComprovantePDV ),false);
                        }
                    }
                    if(btImprimirComprovante.isSelected()){
                        if(!bContinuarImpressao){
                            if(!bItemCancelado){
                                if(!PDVComprovante.ImprimirItem(i)){
                                   ItensorcRN.Itensorc_Excluir(i.getIdunico(), i.getCodigo().getCodigo());
                                   return false;
                                }
                            }
                        }
                    }
                    
                    if(!EscreverNaTela(ComprovanteNaoFiscal.RegistrarItem_Tela_Itens(i.getSeq().toString() ,cNomeProduto, cCodigoProduto, nPreco, nQuantidade, nDesc, nSubTotal,false,bFracionado),FormatoDoCorpo())){
                       return false;
                    }
                    if(bItemCancelado){
                        if(!EscreverNaTela(ComprovanteNaoFiscal.RegistrarItem_Tela_CancelarItens(i,i.getSeq(), false),FormatoDoCorpo()) ){
                            return false;
                        }
                    }
                    getArItensorc().add(i);

                    if(!RegistrarITem_SomarTotalNotas()){
                       getArItensorc().remove(arItensorc.size()-1);
                    }
             
                    if(nCodigoVendaReexibir==0){
                        VendaEmEdicao.RegistrarItemImpresso(getDadosorc().getCodigo(), i.getSeq());
                    }
                                

                        
                    }
                }
            }            
        } catch (Exception e) {

            LogDinnamus.Log(e);
        }


        return bRet;
    }
    private boolean RegistrarItensDaNota(ResultSet rs, Long nCodigoVendaReexibir, String cTipoComprovante, boolean bContinuarImpressao,int nCodigoPDV, boolean bItemCancelado){
        boolean bRet=false;
        try {
                //Itensorc i=new Itensorc();
                Long nIG_ChaveUnica;
                String cCodigoProduto,Unidade="";
                String cNomeProduto,NCM="";
                Double nQuantidade;
                Double nPreco;
                Float nDesc;
                Float Custo=0f;
                boolean bFracionado=false;
                Double nSubTotal;
                String cSituacaoTributaria;
                String cAliquota;
                Float nPercentualIcms;
                //String cTipoComprovante;
                String cTabela;
                nIG_ChaveUnica = rs.getLong("codprod");
                cCodigoProduto = TratamentoNulos.getTratarString().Tratar(rs.getString("ref"),"");
                cNomeProduto = TratamentoNulos.getTratarString().Tratar(rs.getString("descricao"),"");
                nQuantidade = rs.getDouble("quantidade");
                nPreco = rs.getDouble("preco");
                nDesc = rs.getFloat("descv");
                nSubTotal =  rs.getDouble("total");
                bFracionado=rs.getBoolean("fracionado");
                Custo =rs.getFloat("custo");
                NCM = rs.getString("ncm");
                Unidade=TratamentoNulos.getTratarString().Tratar(rs.getString("unidade"),"UN");
                String cCodMov= TratamentoNulos.getTratarString().Tratar( rs.getString("codmov"),"");
                cSituacaoTributaria = TratamentoNulos.getTratarString().Tratar(rs.getString("st"),"");
                cAliquota = TratamentoNulos.getTratarString().Tratar(rs.getString("codaliquota"),"");
                nPercentualIcms = Float.valueOf(rs.getString("icms"));
                cTabela=TratamentoNulos.getTratarString().Tratar(rs.getString("tabela"),"");
                bRet = RegistraItem(rs.getInt("seq"),rs.getLong("idunico"), nIG_ChaveUnica,  cCodigoProduto,cNomeProduto,  nQuantidade,  nPreco, nDesc , nSubTotal,  cSituacaoTributaria,  cAliquota,  nPercentualIcms,  cTipoComprovante,  cTabela, nCodigoVendaReexibir,cTipoComprovante,bContinuarImpressao,nCodigoPDV,bFracionado, bItemCancelado,cCodMov,Unidade,Custo,NCM);
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return bRet;
    }
    private boolean ReExibirNotaInterrompida(Long nCodigoVenda, String cTipoComprovante, boolean bContinuarImpressao, int nCodigoPDV){
        boolean bRet=false;
        try {
            ResultSet rsDadosorc = DadosorcRN.Dadosorc_Listar(nCodigoVenda);
            if(rsDadosorc.next()){
                Long nSeq = rsDadosorc.getLong("codigo");

                String cCodVendedor =  TratamentoNulos.getTratarString().Tratar(rsDadosorc.getString("codvendedor"),"");
                String cCodCliente =   TratamentoNulos.getTratarString().Tratar(rsDadosorc.getString("codcliente"),"");
                String cNomeCliente= TratamentoNulos.getTratarString().Tratar(rsDadosorc.getString("cliente"),"");
                MetodosUI_Auxiliares.SetarOpcaoCombo(cbVendedor,cCodVendedor);                
                txtNomeCliente.setText(cCodCliente  + " - " + cNomeCliente);
                ResultSet rsItens = ItensorcRN.Itensorc_Listar(nCodigoVenda);
                getDadosorc().setCodcliente(cCodCliente);
                getDadosorc().setCliente(cNomeCliente);
                getDadosorc().setData(rsDadosorc.getDate("data"));
                getDadosorc().setHora(rsDadosorc.getTimestamp("hora"));
                getDadosorc().setPdv(rsDadosorc.getInt("pdv"));
                getDadosorc().setOperador(rsDadosorc.getString("operador"));
                //DataHora.getStringToData(lblData.getText(),"dd/MM/yyyy");
                getDadosorc().setLoja(rsDadosorc.getInt("loja"));
                getDadosorc().setFilial(rsDadosorc.getInt("filial"));
                getDadosorc().setCodoperador( TratamentoNulos.getTratarString().Tratar( rsDadosorc.getString("codoperador"),""));
                getDadosorc().setOperador(TratamentoNulos.getTratarString().Tratar( rsDadosorc.getString("operador"),""));
                getDadosorc().setControleCx("");
                getDadosorc().setObjetoCaixa(0);
                getDadosorc().setCodcaixa(0);
                getDadosorc().setRecebido("N");
                getDadosorc().setFeito("S");
                boolean bItemCancelado=false;
                while(rsItens.next()){
                    bItemCancelado = TratamentoNulos.getTratarString().Tratar(rsItens.getString("codmov"),"").equalsIgnoreCase("apagar") ? true : false;
                    if(!RegistrarItensDaNota(rsItens, nSeq,cTipoComprovante,bContinuarImpressao,nCodigoPDV, bItemCancelado)){
                        return false;
                    }                    
                }
                
                if(cTipoComprovante.equalsIgnoreCase("nfiscal")){
                    MudarBotaoTipoComprovante();
                }else if(cTipoComprovante.equalsIgnoreCase("")){
                        AtivarDesativarImpressao();
                }
                bRet=true;

            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return bRet;
    }
    private boolean  ProcurarProduto() {
        ArrayList<String> arRetorno=null;
        boolean bRet=false;
        boolean bProdutoBalanca=false;
        try {

            int nTabelaPreco = Integer.parseInt(((ItemLista) cbTbPreco.getSelectedItem()).getIndice().toString());

            txtProcurar.setText(ManipulacaoString.LimparStringDeCaracteresInvalidos(txtProcurar.getText().toString().trim()));
            String cCodigo = txtProcurar.getText().substring(0,1);
            if(cCodigo.equals("2")){
                arRetorno = TratarCodigoBalanca.Tratar(txtProcurar.getText());
                if(arRetorno.size()>0){
                    
                    if(!arRetorno.get(0).equalsIgnoreCase("0")){
                        bProdutoBalanca=true;
                        txtProcurar.setText(arRetorno.get(0));
                        if(arRetorno.get(1).equals("Peso")){
                            txtQuantidade.setText(arRetorno.get(2));
                        }                    
                    }
                }
            }
            ResultSet rs = cadproduto.Pesquisar(txtProcurar.getText().toString().trim(), Sistema.getCodigoLojaMatriz(), true, nTabelaPreco, 0, 0, 0f, true, true, 1);
            if (rs != null) {
                if(rs.isFirst()){
                    try {
                        boolean bPrecoLivre = rs.getBoolean("precolivre");
                        Double nPrecoProduto=0d, nQuantidade = 0d, nSubTotal = 0d;
                        Float nPercentualDeIcms=0f, nCusto=0f;
                        if (bPrecoLivre) {
                            String cRetornoPrecoLivre= MetodosUI_Auxiliares.InputBox(null,"Digite o Valor" , "Produto com Preço Livre", "AVISO");
                            
                            try {
                                
                                cRetornoPrecoLivre= cRetornoPrecoLivre.replaceAll(",", ".");
                                
                                nPrecoProduto = Double.parseDouble(cRetornoPrecoLivre);
                                if(nPrecoProduto<=0 || nPrecoProduto >999){
                                   MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O valor informado está fora do limite aceitavel [0-999]", "Preço Livre Inválido", "AVISO"); 
                                   return false;
                                }  
                                        
                            } catch (Exception e) {
                                MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Digite um preço válido", "Preço Livre", "AVISO");
                                return false;
                            }
                            
                        }else{
                            nPrecoProduto = rs.getDouble("ITP_PrecoVenda");
                        }
                        
                        
                        
                         
                        boolean bFracionado =false;
                        String cTributacaoIcms="",cAliquota="", cTabela="",Unidade="",NCM="";
                        //rs.first();

                        txtNomeProduto.setText(rs.getString("CP_Nome"));
                        if (txtQuantidade.getText().trim().length() == 0) {
                            txtQuantidade.setText("1");
                        }
                        cTabela=cbTbPreco.getSelectedItem().toString();
                        nQuantidade = Double.parseDouble(txtQuantidade.getText());
                        nSubTotal = nQuantidade * nPrecoProduto;
                        bFracionado = rs.getBoolean("fracionado");
                        Unidade = TratamentoNulos.getTratarString().Tratar( rs.getString("unidade"),"UN");
                        nCusto = rs.getFloat("ultimopreco");
                        NCM=rs.getString("codigonbn");
                        
                        
                        if(bProdutoBalanca && !bFracionado){
                            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O produto não foi identificado como pesavel.", "Produto Pesável", "AVISO");
                            txtNomeProduto.setText("");
                            txtProcurar.setText("");
                            txtQuantidade.setText("");
                            LimparCampoValoresProdutos();
                            return false;
                        }
                        if(bProdutoBalanca){
                           if(arRetorno.get(1).equals("Valor")){
                              nQuantidade = NumeroArredondar.Arredondar_Double(Double.valueOf(arRetorno.get(2))/nPrecoProduto,3);
                              txtQuantidade.setText(nQuantidade.toString());
                              nSubTotal= (nQuantidade*nPrecoProduto);
                              nSubTotal =  NumeroArredondar.truncar(nSubTotal.toString(),2).doubleValue();  //  Float.valueOf(arRetorno.get(2));
                           }
                        }else{
                           
                        }       
                        cTributacaoIcms=(rs.getString("Tributaçãoicms")==null ? "1" : rs.getString("Tributaçãoicms")) ;
                        cAliquota=(  rs.getString("Codaliquota")==null ? "01"  : rs.getString("Codaliquota"));
                        nPercentualDeIcms= (rs.getFloat("Percentualdeicms")==0f ?  17f : rs.getFloat("Percentualdeicms"));

                        txtPreco.setValue(nPrecoProduto);
                        txtPreco.commitEdit();
                        txtSubTotal.setValue(nSubTotal);
                        txtSubTotal.commitEdit();
                        if(opModoVenda.isSelected()){
                            bRet= RegistraItem(0,Long.valueOf(0),rs.getLong("IG_Chaveunica"), txtProcurar.getText(), txtNomeProduto.getText(),  nQuantidade, nPrecoProduto, 0f ,nSubTotal,cTributacaoIcms,cAliquota,nPercentualDeIcms,(btTipoComprovante.getText()),cTabela,Long.valueOf(0),"",false,pdvgerenciar.CodigoPDV(),bFracionado,false,"",Unidade, nCusto,NCM);
                            if(!bRet)
                            {
                                MetodosUI_Auxiliares.MensagemAoUsuarioSimples( new JFrame(), "O item não pode ser registrado", "Registro de Item", "AVISO");
                                LimparCampoValoresProdutos();
                            }
                        }
                        //txtProcurar.setValue(null);
                        txtProcurar.setText("");
                        txtQuantidade.setText("");
                    } catch (SQLException ex) {
                        LogDinnamus.Log(ex);

                    }
                }
                else{
                    txtNomeProduto.setText("");
                    //txtProcurar.setText(null);
                    String cCodigoNaoLocalizado=txtProcurar.getText();
                    txtProcurar.setText("");
                    txtQuantidade.setText("");
                    LimparCampoValoresProdutos();
                    MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Produto não localizado [ " + cCodigoNaoLocalizado + " ]", "Pesquisar Produto", "AVISO");
                }

            } else {
                    ProcurarProduto();
            }            
        } catch (Exception e) {
                    LogDinnamus.Log(e);
        }

        return bRet;
    }
    private void LiberarObjetos()
    {
        try {
            setDadosorc(null);
            setItensorc(null);
            setArItensorc(null);
            //parent=null;

        } catch (Exception e) {
                LogDinnamus.Log(e);
        }
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        opGrupoOpcao = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        lblHora = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        Sequencia4 = new javax.swing.JLabel();
        txtCaixa = new javax.swing.JTextField();
        Sequencia2 = new javax.swing.JLabel();
        txtOperador = new javax.swing.JTextField();
        Sequencia5 = new javax.swing.JLabel();
        txtECF = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        opOffline = new javax.swing.JRadioButton();
        opOnline = new javax.swing.JRadioButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblNomeEmpresa1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblPDV = new javax.swing.JLabel();
        cbTbPreco = new javax.swing.JComboBox();
        txtNomeCliente = new javax.swing.JTextField();
        cbVendedor = new javax.swing.JComboBox();
        Sequencia3 = new javax.swing.JLabel();
        txtSequencia1 = new javax.swing.JTextField();
        Sequencia1 = new javax.swing.JLabel();
        txtPDV = new javax.swing.JTextField();
        btCliente = new javax.swing.JButton();
        btTabela = new javax.swing.JButton();
        tpPrincipal = new javax.swing.JTabbedPane();
        PainelProduto = new javax.swing.JPanel();
        PainelPasseProduto = new javax.swing.JPanel();
        txtProcurar = new javax.swing.JTextField();
        PainelTipoAtendimento = new javax.swing.JPanel();
        opModoVenda = new javax.swing.JRadioButton();
        opModoConsulta = new javax.swing.JRadioButton();
        PainelPasseProduto1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtNomeProduto = new javax.swing.JTextArea();
        PainelPreco = new javax.swing.JPanel();
        txtPreco = new javax.swing.JFormattedTextField();
        PainelQuantidade = new javax.swing.JPanel();
        txtQuantidade = new javax.swing.JTextField();
        PainelSubtotal = new javax.swing.JPanel();
        txtSubTotal = new javax.swing.JFormattedTextField();
        tpMenu = new javax.swing.JTabbedPane();
        jPanel12 = new javax.swing.JPanel();
        btApagar = new javax.swing.JButton();
        btSair = new javax.swing.JButton();
        btExcluirItem = new javax.swing.JButton();
        btExtornarVenda = new javax.swing.JButton();
        btPesquisarProduto = new javax.swing.JButton();
        btModotrabalho = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        btSincronizarCadastros = new javax.swing.JButton();
        btAbrirGaveta = new javax.swing.JButton();
        btGaveta = new javax.swing.JToggleButton();
        jPanel13 = new javax.swing.JPanel();
        btLeituraX = new javax.swing.JButton();
        btReducaoZ = new javax.swing.JButton();
        btResumo = new javax.swing.JButton();
        btCancelarCupom = new javax.swing.JButton();
        btStatusECF = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        PainelItensAtendimento = new javax.swing.JPanel();
        btTipoComprovante = new javax.swing.JButton();
        jscrol = new javax.swing.JScrollPane();
        txtItens = new javax.swing.JTextPane();
        jPanel11 = new javax.swing.JPanel();
        txtTotal = new javax.swing.JFormattedTextField();
        btImprimirComprovante = new javax.swing.JToggleButton();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        PainelSubtotal1 = new javax.swing.JPanel();
        txtValorVenda = new javax.swing.JFormattedTextField();
        PainelSubtotal2 = new javax.swing.JPanel();
        txtDescVal = new javax.swing.JFormattedTextField();
        txtDescPerc = new javax.swing.JFormattedTextField();
        txtAcrecVal = new javax.swing.JFormattedTextField();
        txtAcrescPerc = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        PainelSubtotal8 = new javax.swing.JPanel();
        txtDinheiro = new javax.swing.JFormattedTextField();
        PainelSubtotal10 = new javax.swing.JPanel();
        txtTroco = new javax.swing.JFormattedTextField();
        btEfetivar = new javax.swing.JButton();
        PainelSubtotal3 = new javax.swing.JPanel();
        txtValorFinal = new javax.swing.JFormattedTextField();
        jLabel12 = new javax.swing.JLabel();
        txtDescontoAtacado = new javax.swing.JFormattedTextField();
        jLabel13 = new javax.swing.JLabel();
        tbFormasPagto = new javax.swing.JTabbedPane();
        PainelSubtotal5 = new javax.swing.JPanel();
        dbgFormasPagto = new br.com.ui.JTableDinnamuS();
        btIncluirForma = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btRemoverForma = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtNumeroParcelas = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtNomeForma = new javax.swing.JTextField();
        txtCodForma = new javax.swing.JTextField();
        txtPrimeiroVencimento = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        txtValorForma = new javax.swing.JFormattedTextField();
        jPanel16 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        dbgParcelas = new br.com.ui.JTableDinnamuS();
        jPanel19 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtValorParcela = new javax.swing.JFormattedTextField();
        txtVencimentoParcela = new JDateChooser("dd/MM/yyyy", "##/##/####", '_');
        txtParcela = new javax.swing.JTextField();
        btGravarParcela = new javax.swing.JButton();
        PainelSubtotal11 = new javax.swing.JPanel();
        dbgOpcoesPagto = new br.com.ui.JTableDinnamuS();
        txtProcurarForma = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("DINNAMUS - CHECKOUT");
        setBackground(new java.awt.Color(153, 153, 153));
        setModal(true);
        setResizable(false);

        jPanel1.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanel17.setLayout(new java.awt.GridLayout(10, 2));

        jPanel4.setPreferredSize(new java.awt.Dimension(1000, 137));

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.setPreferredSize(new java.awt.Dimension(800, 42));

        lblHora.setBackground(new java.awt.Color(0, 0, 0));
        lblHora.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblHora.setText("99:99:99");

        lblData.setBackground(new java.awt.Color(0, 0, 0));
        lblData.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblData.setText("01/01/2000");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(lblData)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblHora)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblData, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        Sequencia4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Sequencia4.setText("Caixa:");

        txtCaixa.setEditable(false);

        Sequencia2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Sequencia2.setText("Op:");

        txtOperador.setEditable(false);

        Sequencia5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Sequencia5.setText("ECF");

        txtECF.setEditable(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(Sequencia4))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(Sequencia5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(txtCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sequencia2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                        .addComponent(txtOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtECF, javax.swing.GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sequencia2)
                    .addComponent(txtCaixa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sequencia4, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtECF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sequencia5, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Sequencia2, txtOperador});

        jPanel6Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Sequencia4, Sequencia5, txtECF});

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel7.setEnabled(false);
        jPanel7.setPreferredSize(new java.awt.Dimension(800, 35));

        buttonGroup1.add(opOffline);
        opOffline.setText("Off-line");
        opOffline.setEnabled(false);
        opOffline.setFocusable(false);

        buttonGroup1.add(opOnline);
        opOnline.setText("Online");
        opOnline.setEnabled(false);
        opOnline.setFocusable(false);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(opOnline)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(opOffline)
                .addGap(18, 18, 18))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opOffline)
                    .addComponent(opOnline))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinnamus/ui/InteracaoUsuario/DinnamuSBarra.jpg"))); // NOI18N

        lblNomeEmpresa1.setBackground(new java.awt.Color(0, 0, 0));
        lblNomeEmpresa1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblNomeEmpresa1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblNomeEmpresa1.setText("NomeEmpresa");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblNomeEmpresa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNomeEmpresa1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setPreferredSize(new java.awt.Dimension(800, 52));

        lblPDV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblPDV.setText("PDV");

        cbTbPreco.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbTbPreco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbTbPrecoKeyPressed(evt);
            }
        });

        txtNomeCliente.setEditable(false);
        txtNomeCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeClienteActionPerformed(evt);
            }
        });
        txtNomeCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeClienteKeyPressed(evt);
            }
        });

        cbVendedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbVendedorActionPerformed(evt);
            }
        });
        cbVendedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cbVendedorKeyPressed(evt);
            }
        });

        Sequencia3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Sequencia3.setText("Vendedor");

        txtSequencia1.setEditable(false);
        txtSequencia1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSequencia1ActionPerformed(evt);
            }
        });

        Sequencia1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        Sequencia1.setText("Sequencia");

        txtPDV.setEditable(false);
        txtPDV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPDVActionPerformed(evt);
            }
        });

        btCliente.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btCliente.setMnemonic('l');
        btCliente.setText("Cliente");
        btCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClienteActionPerformed(evt);
            }
        });

        btTabela.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btTabela.setMnemonic('T');
        btTabela.setText("Tabela");
        btTabela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTabelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblPDV, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Sequencia1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtPDV, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSequencia1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sequencia3, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btTabela)
                        .addGap(72, 72, 72)
                        .addComponent(btCliente))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cbTbPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(29, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblPDV)
                    .addComponent(Sequencia1, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(Sequencia3, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btCliente)
                        .addComponent(btTabela)))
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSequencia1)
                    .addComponent(cbTbPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(cbVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE)
                    .addComponent(txtPDV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNomeCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cbTbPreco, cbVendedor, txtNomeCliente, txtSequencia1});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {Sequencia1, Sequencia3, btCliente, lblPDV});

        tpPrincipal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        PainelProduto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PainelProduto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PainelProduto.setPreferredSize(new java.awt.Dimension(410, 433));

        PainelPasseProduto.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Pesquisar [Alt+S Nome]", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        PainelPasseProduto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtProcurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtProcurarActionPerformed(evt);
            }
        });
        txtProcurar.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                txtProcurarAncestorAdded(evt);
            }
        });
        txtProcurar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProcurarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcurarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtProcurarKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout PainelPasseProdutoLayout = new javax.swing.GroupLayout(PainelPasseProduto);
        PainelPasseProduto.setLayout(PainelPasseProdutoLayout);
        PainelPasseProdutoLayout.setHorizontalGroup(
            PainelPasseProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPasseProdutoLayout.createSequentialGroup()
                .addComponent(txtProcurar)
                .addContainerGap())
        );
        PainelPasseProdutoLayout.setVerticalGroup(
            PainelPasseProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtProcurar, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE)
        );

        PainelTipoAtendimento.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Modo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        PainelTipoAtendimento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        opGrupoOpcao.add(opModoVenda);
        opModoVenda.setMnemonic('v');
        opModoVenda.setSelected(true);
        opModoVenda.setText("Venda");
        opModoVenda.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                opModoVendaFocusGained(evt);
            }
        });

        opGrupoOpcao.add(opModoConsulta);
        opModoConsulta.setMnemonic('c');
        opModoConsulta.setText("Consulta");
        opModoConsulta.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                opModoConsultaFocusGained(evt);
            }
        });

        javax.swing.GroupLayout PainelTipoAtendimentoLayout = new javax.swing.GroupLayout(PainelTipoAtendimento);
        PainelTipoAtendimento.setLayout(PainelTipoAtendimentoLayout);
        PainelTipoAtendimentoLayout.setHorizontalGroup(
            PainelTipoAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTipoAtendimentoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(opModoVenda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(opModoConsulta)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        PainelTipoAtendimentoLayout.setVerticalGroup(
            PainelTipoAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelTipoAtendimentoLayout.createSequentialGroup()
                .addGroup(PainelTipoAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(opModoVenda)
                    .addComponent(opModoConsulta))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        PainelPasseProduto1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Produto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        PainelPasseProduto1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtNomeProduto.setColumns(20);
        txtNomeProduto.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        txtNomeProduto.setLineWrap(true);
        txtNomeProduto.setRows(3);
        txtNomeProduto.setWrapStyleWord(true);
        jScrollPane2.setViewportView(txtNomeProduto);

        javax.swing.GroupLayout PainelPasseProduto1Layout = new javax.swing.GroupLayout(PainelPasseProduto1);
        PainelPasseProduto1.setLayout(PainelPasseProduto1Layout);
        PainelPasseProduto1Layout.setHorizontalGroup(
            PainelPasseProduto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 387, Short.MAX_VALUE)
        );
        PainelPasseProduto1Layout.setVerticalGroup(
            PainelPasseProduto1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelPasseProduto1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PainelPreco.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Preço", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        PainelPreco.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtPreco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtPreco.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtPreco.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtPreco.setEnabled(false);
        txtPreco.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        javax.swing.GroupLayout PainelPrecoLayout = new javax.swing.GroupLayout(PainelPreco);
        PainelPreco.setLayout(PainelPrecoLayout);
        PainelPrecoLayout.setHorizontalGroup(
            PainelPrecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtPreco, javax.swing.GroupLayout.DEFAULT_SIZE, 126, Short.MAX_VALUE)
        );
        PainelPrecoLayout.setVerticalGroup(
            PainelPrecoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtPreco, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        PainelQuantidade.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Quant.", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        PainelQuantidade.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtQuantidade.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        txtQuantidade.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout PainelQuantidadeLayout = new javax.swing.GroupLayout(PainelQuantidade);
        PainelQuantidade.setLayout(PainelQuantidadeLayout);
        PainelQuantidadeLayout.setHorizontalGroup(
            PainelQuantidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtQuantidade, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
        );
        PainelQuantidadeLayout.setVerticalGroup(
            PainelQuantidadeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelQuantidadeLayout.createSequentialGroup()
                .addComponent(txtQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PainelSubtotal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sub-Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        PainelSubtotal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PainelSubtotal.setPreferredSize(new java.awt.Dimension(1000, 84));

        txtSubTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtSubTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtSubTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtSubTotal.setEnabled(false);
        txtSubTotal.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        javax.swing.GroupLayout PainelSubtotalLayout = new javax.swing.GroupLayout(PainelSubtotal);
        PainelSubtotal.setLayout(PainelSubtotalLayout);
        PainelSubtotalLayout.setHorizontalGroup(
            PainelSubtotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtSubTotal)
        );
        PainelSubtotalLayout.setVerticalGroup(
            PainelSubtotalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtSubTotal, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );

        btApagar.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btApagar.setText("Apagar - F2");
        btApagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btApagarActionPerformed(evt);
            }
        });

        btSair.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btSair.setMnemonic('R');
        btSair.setText("Sair - Esc");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        btExcluirItem.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btExcluirItem.setMnemonic('E');
        btExcluirItem.setText("Excl.Item - F4");
        btExcluirItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirItemActionPerformed(evt);
            }
        });

        btExtornarVenda.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btExtornarVenda.setText("Pesquisar - F3");
        btExtornarVenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExtornarVendaActionPerformed(evt);
            }
        });

        btPesquisarProduto.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btPesquisarProduto.setText("Pesq.Prod ");
        btPesquisarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPesquisarProdutoActionPerformed(evt);
            }
        });

        btModotrabalho.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btModotrabalho.setText("Modo Trab.");
        btModotrabalho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btModotrabalhoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btSair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btApagar, javax.swing.GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btPesquisarProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExtornarVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btModotrabalho, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btExcluirItem, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE))
                .addGap(28, 28, 28))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(btExcluirItem, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btModotrabalho))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btExtornarVenda)
                            .addComponent(btApagar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btSair)
                            .addComponent(btPesquisarProduto))))
                .addGap(23, 23, 23))
        );

        tpMenu.addTab("Vendas - F5", jPanel12);

        jButton14.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton14.setText("Trocar Op. - F2");
        jButton14.setEnabled(false);
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton15.setText("Reimprimir - F3");
        jButton15.setEnabled(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton16.setText("Adm. TEF - F1");
        jButton16.setEnabled(false);

        btSincronizarCadastros.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btSincronizarCadastros.setMnemonic('q');
        btSincronizarCadastros.setText("Sinc. - F4");
        btSincronizarCadastros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSincronizarCadastrosActionPerformed(evt);
            }
        });

        btAbrirGaveta.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btAbrirGaveta.setMnemonic('a');
        btAbrirGaveta.setText("Abrir Gaveta");
        btAbrirGaveta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAbrirGavetaActionPerformed(evt);
            }
        });

        btGaveta.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btGaveta.setMnemonic('g');
        btGaveta.setSelected(true);
        btGaveta.setText("Gaveta - On");
        btGaveta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGavetaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton14)
                    .addComponent(btSincronizarCadastros, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton16, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btAbrirGaveta, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton15)
                    .addComponent(btGaveta))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel14Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAbrirGaveta, btGaveta, btSincronizarCadastros, jButton14, jButton15, jButton16});

        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btGaveta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btSincronizarCadastros)
                        .addComponent(btAbrirGaveta)))
                .addGap(47, 47, 47))
        );

        jPanel14Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton14, jButton15, jButton16});

        tpMenu.addTab("Operações - F6", jPanel14);

        btLeituraX.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btLeituraX.setText("Leitura X - F2");
        btLeituraX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLeituraXActionPerformed(evt);
            }
        });

        btReducaoZ.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btReducaoZ.setText("Redução Z - F3");
        btReducaoZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btReducaoZActionPerformed(evt);
            }
        });

        btResumo.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btResumo.setText("Resumo - F4");
        btResumo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btResumoActionPerformed(evt);
            }
        });

        btCancelarCupom.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btCancelarCupom.setText("Canc.Cupom-F1");
        btCancelarCupom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarCupomActionPerformed(evt);
            }
        });

        btStatusECF.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        btStatusECF.setText("Status ECF");
        btStatusECF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btStatusECFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btResumo, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(btCancelarCupom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(9, 9, 9)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btStatusECF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btLeituraX, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btReducaoZ, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btLeituraX)
                    .addComponent(btCancelarCupom)
                    .addComponent(btReducaoZ))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btStatusECF)
                    .addComponent(btResumo))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        tpMenu.addTab("Caixa - F7", jPanel13);

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton13.setText("Ficha Cad. - F1");
        jButton13.setEnabled(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton17.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton17.setText("Crediário - F2");
        jButton17.setEnabled(false);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        jButton18.setText("Fidelidade - F3");
        jButton18.setEnabled(false);
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton18)
                .addContainerGap(49, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton13)
                    .addComponent(jButton17)
                    .addComponent(jButton18))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        tpMenu.addTab("Cliente - F8", jPanel15);

        PainelItensAtendimento.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PainelItensAtendimento.setPreferredSize(new java.awt.Dimension(400, 433));

        btTipoComprovante.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        btTipoComprovante.setMnemonic('.');
        btTipoComprovante.setText("CUPOM FISCAL.");
        btTipoComprovante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTipoComprovanteActionPerformed(evt);
            }
        });

        txtItens.setEditable(false);
        txtItens.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jscrol.setViewportView(txtItens);

        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TOTAL", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        txtTotal.setEditable(false);
        txtTotal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("###,###.00"))));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        txtTotal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 54, Short.MAX_VALUE)
                .addContainerGap())
        );

        btImprimirComprovante.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btImprimirComprovante.setMnemonic('P');
        btImprimirComprovante.setSelected(true);
        btImprimirComprovante.setText("Imprimir Comprovante - [ ON ]");
        btImprimirComprovante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btImprimirComprovanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout PainelItensAtendimentoLayout = new javax.swing.GroupLayout(PainelItensAtendimento);
        PainelItensAtendimento.setLayout(PainelItensAtendimentoLayout);
        PainelItensAtendimentoLayout.setHorizontalGroup(
            PainelItensAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btTipoComprovante, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jscrol)
            .addComponent(btImprimirComprovante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PainelItensAtendimentoLayout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        PainelItensAtendimentoLayout.setVerticalGroup(
            PainelItensAtendimentoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelItensAtendimentoLayout.createSequentialGroup()
                .addComponent(btTipoComprovante)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jscrol, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btImprimirComprovante)
                .addGap(211, 211, 211))
        );

        javax.swing.GroupLayout PainelProdutoLayout = new javax.swing.GroupLayout(PainelProduto);
        PainelProduto.setLayout(PainelProdutoLayout);
        PainelProdutoLayout.setHorizontalGroup(
            PainelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelProdutoLayout.createSequentialGroup()
                        .addComponent(PainelPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PainelQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PainelSubtotal, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                    .addGroup(PainelProdutoLayout.createSequentialGroup()
                        .addComponent(PainelTipoAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PainelPasseProduto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(PainelPasseProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tpMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PainelItensAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 349, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        PainelProdutoLayout.setVerticalGroup(
            PainelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelProdutoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PainelItensAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PainelProdutoLayout.createSequentialGroup()
                        .addGroup(PainelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PainelTipoAtendimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PainelPasseProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PainelPasseProduto1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelProdutoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PainelPreco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PainelQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(PainelSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tpMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        PainelProdutoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {PainelPreco, PainelQuantidade, PainelSubtotal});

        PainelProdutoLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {PainelPasseProduto, PainelTipoAtendimento});

        tpPrincipal.addTab("Venda", new javax.swing.ImageIcon(getClass().getResource("/dinnamus/ui/img/cart_put.png")), PainelProduto); // NOI18N

        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Forma de Pagamento", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N

        PainelSubtotal1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor Da Venda", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        PainelSubtotal1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PainelSubtotal1.setPreferredSize(new java.awt.Dimension(1000, 84));

        txtValorVenda.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtValorVenda.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorVenda.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValorVenda.setEnabled(false);
        txtValorVenda.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtValorVenda.setValue(new Float(0f));

        javax.swing.GroupLayout PainelSubtotal1Layout = new javax.swing.GroupLayout(PainelSubtotal1);
        PainelSubtotal1.setLayout(PainelSubtotal1Layout);
        PainelSubtotal1Layout.setHorizontalGroup(
            PainelSubtotal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtValorVenda, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
        );
        PainelSubtotal1Layout.setVerticalGroup(
            PainelSubtotal1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSubtotal1Layout.createSequentialGroup()
                .addComponent(txtValorVenda)
                .addContainerGap())
        );

        PainelSubtotal2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Desconto/Acréscimo( % / $ )", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        PainelSubtotal2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PainelSubtotal2.setPreferredSize(new java.awt.Dimension(1000, 84));

        txtDescVal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtDescVal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDescVal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDescVal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtDescVal.setNextFocusableComponent(txtProcurarForma);
        txtDescVal.setValue(new Float(0f));
        txtDescVal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescValActionPerformed(evt);
            }
        });
        txtDescVal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescValFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescValFocusLost(evt);
            }
        });
        txtDescVal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescValKeyPressed(evt);
            }
        });

        txtDescPerc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.00"))));
        txtDescPerc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDescPerc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDescPerc.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtDescPerc.setValue(new Float(0f));
        txtDescPerc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescPercActionPerformed(evt);
            }
        });
        txtDescPerc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDescPercFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDescPercFocusLost(evt);
            }
        });
        txtDescPerc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDescPercKeyPressed(evt);
            }
        });

        txtAcrecVal.setEditable(false);
        txtAcrecVal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,##0.00"))));
        txtAcrecVal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtAcrecVal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAcrecVal.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtAcrecVal.setNextFocusableComponent(txtProcurarForma);
        txtAcrecVal.setValue(new Float(0f));
        txtAcrecVal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAcrecValActionPerformed(evt);
            }
        });
        txtAcrecVal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAcrecValFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAcrecValFocusLost(evt);
            }
        });
        txtAcrecVal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAcrecValKeyPressed(evt);
            }
        });

        txtAcrescPerc.setEditable(false);
        txtAcrescPerc.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.00"))));
        txtAcrescPerc.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtAcrescPerc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtAcrescPerc.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        txtAcrescPerc.setValue(new Float(0f));
        txtAcrescPerc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAcrescPercActionPerformed(evt);
            }
        });
        txtAcrescPerc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtAcrescPercFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtAcrescPercFocusLost(evt);
            }
        });
        txtAcrescPerc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtAcrescPercKeyPressed(evt);
            }
        });

        jLabel10.setText("Desc.");

        jLabel11.setText("Acresc.");

        javax.swing.GroupLayout PainelSubtotal2Layout = new javax.swing.GroupLayout(PainelSubtotal2);
        PainelSubtotal2.setLayout(PainelSubtotal2Layout);
        PainelSubtotal2Layout.setHorizontalGroup(
            PainelSubtotal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSubtotal2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PainelSubtotal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelSubtotal2Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescPerc, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDescVal))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelSubtotal2Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAcrescPerc, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAcrecVal)))
                .addContainerGap())
        );
        PainelSubtotal2Layout.setVerticalGroup(
            PainelSubtotal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSubtotal2Layout.createSequentialGroup()
                .addGroup(PainelSubtotal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtDescVal, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                    .addComponent(txtDescPerc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addGroup(PainelSubtotal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelSubtotal2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtAcrecVal, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtAcrescPerc, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PainelSubtotal8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "DINHEIRO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        PainelSubtotal8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PainelSubtotal8.setPreferredSize(new java.awt.Dimension(1000, 84));

        txtDinheiro.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtDinheiro.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDinheiro.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDinheiro.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        txtDinheiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDinheiroActionPerformed(evt);
            }
        });
        txtDinheiro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtDinheiroFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtDinheiroFocusLost(evt);
            }
        });
        txtDinheiro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDinheiroKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout PainelSubtotal8Layout = new javax.swing.GroupLayout(PainelSubtotal8);
        PainelSubtotal8.setLayout(PainelSubtotal8Layout);
        PainelSubtotal8Layout.setHorizontalGroup(
            PainelSubtotal8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSubtotal8Layout.createSequentialGroup()
                .addComponent(txtDinheiro, javax.swing.GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE)
                .addContainerGap())
        );
        PainelSubtotal8Layout.setVerticalGroup(
            PainelSubtotal8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSubtotal8Layout.createSequentialGroup()
                .addComponent(txtDinheiro, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        PainelSubtotal10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "TROCO", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        PainelSubtotal10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        PainelSubtotal10.setPreferredSize(new java.awt.Dimension(1000, 84));

        txtTroco.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtTroco.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTroco.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTroco.setEnabled(false);
        txtTroco.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N

        javax.swing.GroupLayout PainelSubtotal10Layout = new javax.swing.GroupLayout(PainelSubtotal10);
        PainelSubtotal10.setLayout(PainelSubtotal10Layout);
        PainelSubtotal10Layout.setHorizontalGroup(
            PainelSubtotal10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txtTroco, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
        );
        PainelSubtotal10Layout.setVerticalGroup(
            PainelSubtotal10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSubtotal10Layout.createSequentialGroup()
                .addComponent(txtTroco, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        btEfetivar.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btEfetivar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinnamus/ui/img/money.png"))); // NOI18N
        btEfetivar.setText("Efetivar Venda");
        btEfetivar.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        btEfetivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEfetivarActionPerformed(evt);
            }
        });

        PainelSubtotal3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Valor Final", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        PainelSubtotal3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        PainelSubtotal3.setPreferredSize(new java.awt.Dimension(1000, 84));

        txtValorFinal.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtValorFinal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorFinal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValorFinal.setEnabled(false);
        txtValorFinal.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        txtValorFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorFinalActionPerformed(evt);
            }
        });

        jLabel12.setText("Desc.Atacado");

        txtDescontoAtacado.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtDescontoAtacado.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtDescontoAtacado.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtDescontoAtacado.setEnabled(false);
        txtDescontoAtacado.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        txtDescontoAtacado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescontoAtacadoActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("VALOR FINAL");

        javax.swing.GroupLayout PainelSubtotal3Layout = new javax.swing.GroupLayout(PainelSubtotal3);
        PainelSubtotal3.setLayout(PainelSubtotal3Layout);
        PainelSubtotal3Layout.setHorizontalGroup(
            PainelSubtotal3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelSubtotal3Layout.createSequentialGroup()
                .addGroup(PainelSubtotal3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(PainelSubtotal3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtValorFinal, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                    .addComponent(txtDescontoAtacado))
                .addGap(161, 161, 161))
        );
        PainelSubtotal3Layout.setVerticalGroup(
            PainelSubtotal3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSubtotal3Layout.createSequentialGroup()
                .addGroup(PainelSubtotal3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescontoAtacado, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PainelSubtotal3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        PainelSubtotal5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        PainelSubtotal5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PainelSubtotal5.setPreferredSize(new java.awt.Dimension(1000, 84));

        dbgFormasPagto.setExibirBarra(false);

        btIncluirForma.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btIncluirForma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinnamus/ui/img/money_add.png"))); // NOI18N
        btIncluirForma.setText("Incluir");
        btIncluirForma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btIncluirFormaActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Digite o Valor :");

        btRemoverForma.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btRemoverForma.setIcon(new javax.swing.ImageIcon(getClass().getResource("/dinnamus/ui/img/money_delete.png"))); // NOI18N
        btRemoverForma.setMnemonic('r');
        btRemoverForma.setText("Remover");
        btRemoverForma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverFormaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("No. Parc.");

        txtNumeroParcelas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtNumeroParcelasFocusGained(evt);
            }
        });
        txtNumeroParcelas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumeroParcelasKeyPressed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("1o Vencto.");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Forma");

        txtNomeForma.setEditable(false);
        txtNomeForma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeFormaKeyPressed(evt);
            }
        });

        txtCodForma.setEditable(false);
        txtCodForma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCodFormaKeyPressed(evt);
            }
        });

        txtPrimeiroVencimento.setNextFocusableComponent(btIncluirForma);
        txtPrimeiroVencimento.getDateEditor().getUiComponent().addKeyListener(

            new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode()==KeyEvent.VK_ENTER){
                        btIncluirForma.requestFocus();
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {}

            }
        );
        txtPrimeiroVencimento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPrimeiroVencimentoKeyPressed(evt);
            }
        });

        txtValorForma.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#,###.00"))));
        txtValorForma.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorForma.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValorForma.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtValorFormaFocusGained(evt);
            }
        });
        txtValorForma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorFormaKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout PainelSubtotal5Layout = new javax.swing.GroupLayout(PainelSubtotal5);
        PainelSubtotal5.setLayout(PainelSubtotal5Layout);
        PainelSubtotal5Layout.setHorizontalGroup(
            PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSubtotal5Layout.createSequentialGroup()
                .addGroup(PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelSubtotal5Layout.createSequentialGroup()
                        .addComponent(dbgFormasPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btRemoverForma, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                            .addComponent(btIncluirForma, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)))
                    .addGroup(PainelSubtotal5Layout.createSequentialGroup()
                        .addGroup(PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PainelSubtotal5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(txtCodForma, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNomeForma, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, PainelSubtotal5Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(105, 105, 105)))
                        .addGroup(PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(PainelSubtotal5Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel3))
                            .addComponent(txtValorForma))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(txtNumeroParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PainelSubtotal5Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                                .addGap(33, 33, 33))
                            .addComponent(txtPrimeiroVencimento, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))))
                .addContainerGap())
        );
        PainelSubtotal5Layout.setVerticalGroup(
            PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSubtotal5Layout.createSequentialGroup()
                .addGroup(PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNumeroParcelas, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(txtValorForma, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtNomeForma, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(txtCodForma, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))
                    .addComponent(txtPrimeiroVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PainelSubtotal5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelSubtotal5Layout.createSequentialGroup()
                        .addComponent(btIncluirForma, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addGap(17, 17, 17)
                        .addComponent(btRemoverForma))
                    .addComponent(dbgFormasPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        PainelSubtotal5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btIncluirForma, txtNumeroParcelas});

        tbFormasPagto.addTab("Forma de Pagamento - F2", PainelSubtotal5);

        dbgParcelas.setExibirBarra(false);
        dbgParcelas.getjTable().addKeyListener(

            new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {
                    if(e.getKeyCode()==KeyEvent.VK_ENTER){
                        e.consume();
                    }
                }

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode()==KeyEvent.VK_ENTER){
                        e.consume();
                        txtVencimentoParcela.getDateEditor().getUiComponent().requestFocus();
                    }else if(e.getKeyCode()==KeyEvent.VK_F2){
                        Evento_F2();
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {
                    if(e.getKeyCode()==KeyEvent.VK_ENTER){
                        e.consume();
                    }
                }

            }
        );

        jPanel19.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados da Parcela", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel7.setText("Parcela");

        jLabel8.setText("Data");

        jLabel9.setText("Valor");

        txtValorParcela.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("0.00"))));
        txtValorParcela.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtValorParcela.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtValorParcela.setValue(new Float(0f));
        txtValorParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorParcelaActionPerformed(evt);
            }
        });
        txtValorParcela.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtValorParcelaFocusGained(evt);
            }
        });
        txtValorParcela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtValorParcelaKeyPressed(evt);
            }
        });

        txtVencimentoParcela.setNextFocusableComponent(btIncluirForma);
        txtVencimentoParcela.getDateEditor().getUiComponent().addKeyListener(

            new KeyAdapter() {
                @Override
                public void keyTyped(KeyEvent e) {}

                @Override
                public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode()==KeyEvent.VK_ENTER){
                        SwingUtilidade.RequestFocus(txtValorParcela);
                    }
                }
                @Override
                public void keyReleased(KeyEvent e) {}

            }
        );
        txtVencimentoParcela.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtVencimentoParcelaFocusGained(evt);
            }
        });
        txtVencimentoParcela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtVencimentoParcelaKeyPressed(evt);
            }
        });

        txtParcela.setEditable(false);
        txtParcela.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtParcelaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtParcelaKeyReleased(evt);
            }
        });

        btGravarParcela.setText("OK");
        btGravarParcela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGravarParcelaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtVencimentoParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtValorParcela, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btGravarParcela)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addGap(1, 1, 1)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtVencimentoParcela, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtValorParcela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE)
                    .addComponent(btGravarParcela, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jPanel19Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btGravarParcela, txtValorParcela});

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addComponent(dbgParcelas, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dbgParcelas, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbFormasPagto.addTab("Parcelas - F3", jPanel16);

        PainelSubtotal11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Opções  Pagto", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        PainelSubtotal11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        PainelSubtotal11.setPreferredSize(new java.awt.Dimension(1000, 84));

        dbgOpcoesPagto.setExibirBarra(false);
        dbgOpcoesPagto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                dbgOpcoesPagtoKeyPressed(evt);
            }
        });

        txtProcurarForma.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtProcurarFormaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtProcurarFormaKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Pesquisar Cod.:");

        javax.swing.GroupLayout PainelSubtotal11Layout = new javax.swing.GroupLayout(PainelSubtotal11);
        PainelSubtotal11.setLayout(PainelSubtotal11Layout);
        PainelSubtotal11Layout.setHorizontalGroup(
            PainelSubtotal11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSubtotal11Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtProcurarForma, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(dbgOpcoesPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        PainelSubtotal11Layout.setVerticalGroup(
            PainelSubtotal11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PainelSubtotal11Layout.createSequentialGroup()
                .addGroup(PainelSubtotal11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtProcurarForma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbgOpcoesPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PainelSubtotal1, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(PainelSubtotal11, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(PainelSubtotal2, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(PainelSubtotal3, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(PainelSubtotal8, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(PainelSubtotal10, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2)
                                .addComponent(btEfetivar))
                            .addComponent(tbFormasPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 497, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PainelSubtotal1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(PainelSubtotal2, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(PainelSubtotal3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(tbFormasPagto, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btEfetivar, javax.swing.GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE)
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(PainelSubtotal10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(PainelSubtotal8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(PainelSubtotal11, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tpPrincipal.addTab("Fechamento", new javax.swing.ImageIcon(getClass().getResource("/dinnamus/ui/img/money.png")), jPanel9); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(tpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tpPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 783, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
        // TODO add your handling code here:
        try {
            
            if( arItensorc!=null ? (arItensorc.size()>0 ? true : false) : false )
            {
                MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Existe uma nota em edição. Operação de saida cancelada", "Fechamento do Checkout", "INFO");
            }
            else
                if(MetodosUI_Auxiliares.MensagemAoUsuarioOpcoes(null, "Confirma a saida do checkout?", "Saida do Checkout", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION)
                {
                    LiberarObjetos();
                    this.dispose();
                    return;
                }
            txtProcurar.requestFocus();
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }

        
 
}//GEN-LAST:event_btSairActionPerformed

    private void btTipoComprovanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTipoComprovanteActionPerformed
        // TODO add your handling code here:
        if(arItensorc.size()==0)
        {
            MudarBotaoTipoComprovante();
            
        }
        txtProcurar.requestFocus();
    }//GEN-LAST:event_btTipoComprovanteActionPerformed
 private void MudarBotaoTipoComprovante() {
    if(btTipoComprovante.getText().matches("CUPOM VENDA"))
       btTipoComprovante.setText("CUPOM FISCAL.");
    else
        btTipoComprovante.setText("CUPOM VENDA");
 }
    private void opModoConsultaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_opModoConsultaFocusGained
                // TODO add your handling code here:
        txtProcurar.requestFocus();
    }//GEN-LAST:event_opModoConsultaFocusGained
   
    private void btApagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btApagarActionPerformed
        // TODO add your handling code here:
        try {
            if(txtSequencia1.getText().length()>0){
                Integer nCodigoUsuario =ValidarAcessoAoProcesso.Verificar(null , UsuarioSistema.getIdUsuarioLogado(), "ExcluirCotacao", Sistema.getLojaAtual(), true, "Cancelamento de Nota");
                Long nCodigoSequencia = Long.valueOf( txtSequencia1.getText());
                if(nCodigoUsuario>0){
                    if(ApagarNota(true)){
                        VendaEmEdicao.FinalizarNota(nCodigoSequencia);
                        UsuarioAuditar.Auditar(nCodigoUsuario, "VENDAS","Excluiu a cotação");
                        if(btImprimirComprovante.isSelected()){
                            PDVComprovante.CancelarComprovante();
                        }
                    }
                }else{
                    MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Usuário não autorizado", "Apaga Nota", "AVISO");
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        

        //MetodosUI_Auxiliares.MensagemAoUsuarioSimples((JFrame)parent, nCodigoUsuario.toString(), "", "AVISO");
    }//GEN-LAST:event_btApagarActionPerformed

    private void txtPDVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPDVActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtPDVActionPerformed

    private void txtSequencia1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSequencia1ActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtSequencia1ActionPerformed

    private void txtNomeClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeClienteActionPerformed
        // TODO add your handling code here:
}//GEN-LAST:event_txtNomeClienteActionPerformed

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void btExcluirItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirItemActionPerformed
        // TODO add your handling code here:
        if(arItensorc.size()>0)
        {
          itensVendidos=new frmPDVItensVendidos(null, true);
          itensVendidos.Inicializar(getDadosorc().getCodigo(), arItensorc.size());
          itensVendidos.setVisible(true);
          Object[] objLinha = itensVendidos.getObjLinha();
          if(objLinha!=null)
          {
              if(objLinha.length>0) {
                Integer nCodigoUsuario =ValidarAcessoAoProcesso.Verificar( null , UsuarioSistema.getIdUsuarioLogado(), "CancelarItem", Sistema.getLojaAtual(), true,"Cancelamento de ITEM");
                if(nCodigoUsuario>0){
                    int nSeq=Integer.parseInt(objLinha[0].toString());
                    Long nIDUnico=Long.parseLong(objLinha[5].toString());
                    Itensorc item_escolhido=arItensorc.get(nSeq-1);
                    if(btImprimirComprovante.isSelected()){
                        item_escolhido.setSeq(nSeq);    
                        if(!PDVComprovante.CancelarItem(item_escolhido)){
                            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Não foi possível realizar o cancelamento do item", "Cancelar Item", "AVISO");
                            txtProcurar.requestFocus();
                            return;
                        }
                    }
                    if(ItensorcRN.Itensorc_Cancelar(item_escolhido)){                      
                      arItensorc.get(nSeq-1).setCodmov("Apagar");
                      UsuarioAuditar.Auditar(nCodigoUsuario, "VENDAS","Cancelamento do item no PDV" + txtPDV.getText());
                      RegistrarITem_SomarTotalNotas();
                      EscreverNaTela(ComprovanteNaoFiscal.RegistrarItem_Tela_CancelarItens(item_escolhido,nSeq, false),FormatoDoCorpo());                        
                    }
                }
                else
                {
                    MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Usuário não autorizado a cancelar item", "Acesso Negado", "AVISO");
                }
              }
          }
        }
        txtProcurar.requestFocus();
    }//GEN-LAST:event_btExcluirItemActionPerformed
private boolean GridOpcoesPagto_Atualizar(){
    try {
        //String sql="TIPOSDEPAGAMENTOS WHERE LOJA=" + Sistema.getCodigoLojaMatriz() + " AND (BLOQUEADO <> 1) OR (BLOQUEADO IS NULL) AND UPPER(DESTINO) IN ('CAIXA','CHEQUES RECEBIDOS','CARTAO DE CREDITO','A RECEBER & CREDIARIO')";

        dbgOpcoesPagto.setRsDados(TiposdePagamentos.ListarTudo(Sistema.getCodigoLojaMatriz(), !getDadosorc().getCodcliente().equalsIgnoreCase("0") ));
        dbgOpcoesPagto.update(dbgOpcoesPagto.getGraphics());
        return true;
    } catch (Exception e) {
        LogDinnamus.Log(e);
        return false;
    }
}
private String TipoComprovante(){
    String cTipoComprovantePDV="";
    try {
        if(btTipoComprovante.getText().equalsIgnoreCase("CUPOM FISCAL.")){
            cTipoComprovantePDV="fiscal";
        }else{
            cTipoComprovantePDV="nfiscal";
        }

    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
    return cTipoComprovantePDV;

}
private boolean IniciarUI_GridOpcoesPagto()
{
    boolean bRet=false;
    try {
        
        dbgOpcoesPagto.addClColunas("cod", "COD", 40);
        dbgOpcoesPagto.addClColunas("nome", "NOME", 40);
        dbgOpcoesPagto.addClColunas("chaveunica", "CHAVEUNICA", 40);
        
        dbgOpcoesPagto.getjTable().getTableHeader().setResizingAllowed(false);
        dbgOpcoesPagto.getjTable().getTableHeader().setReorderingAllowed(false);
        dbgOpcoesPagto.getjTable().addKeyListener(new KeyListener() {
        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode()==KeyEvent.VK_ENTER)
            {
                e.consume();
                Double nValorFinal =  FormatarNumero.FormatarNumero_Double(txtValorFinal.getText());
                Double nValorDistribuido=PagtoorcRN.Pagtorc_SomarValores(getDadosorc().getCodigo());
                if(nValorDistribuido==nValorFinal){
                    MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O valor total da nota já foi informado", "Formas de Pagto", "Aviso");
                }
                else{
                    Object[] obj = dbgOpcoesPagto.TratarLinhaSelecionada(dbgOpcoesPagto.getjTable());
                    if(obj!=null)
                    {
                        Integer nCodigoForma = Integer.valueOf(obj[0].toString());
                        ResultSet rs = TiposdePagamentos.Listar(nCodigoForma,Sistema.getCodigoLojaMatriz());
                        Integer nIntervalo=0;
                        try {
                            if(rs.next()){
                                
                             
                                
                                nIntervalo = rs.getInt("intervalo");
                                if(TratamentoNulos.getTratarString().Tratar(rs.getString("UtilizaCheque"),"N").equalsIgnoreCase("N")){
                                    txtNumeroParcelas.setEnabled(true);
                                    txtPrimeiroVencimento.setEnabled(true);
                                }else{

                                    txtNumeroParcelas.setEnabled(false);
                                    txtPrimeiroVencimento.setEnabled(false);
                                }
                            }
                        } catch (SQLException ex) {
                            LogDinnamus.Log(ex);
                        }
                        Double nValorForma = nValorFinal - nValorDistribuido;
                        
                        Double nValorAcrescimento=0d, nPercAcrescimento=0d;
                        try {
                            nPercAcrescimento = rs.getDouble("acrescimo");
                        } catch (SQLException ex) {
                             LogDinnamus.Log(ex);
                        }

                        try {                            
                            if(nPercAcrescimento>0f && nPercAcrescimento <=99f){
                                BigDecimal nValorDescontoNota = new BigDecimal( txtDescVal.getValue().toString());
                                nValorAcrescimento = NumeroArredondar.Arredondar_Double(nValorForma*nPercAcrescimento/100,2);
                                nValorForma = nValorForma + nValorAcrescimento ;
                                txtAcrescPerc.setValue(BigDecimal.valueOf(nPercAcrescimento));
                                txtAcrecVal.setValue(BigDecimal.valueOf(nValorAcrescimento));
                                BigDecimal nValorVenda = new BigDecimal( txtValorVenda.getValue().toString());
                                
                                nValorVenda = nValorVenda.add(BigDecimal.valueOf( nValorAcrescimento.doubleValue())).subtract(nValorDescontoNota); //nValorFinalVenda.add(BigDecimal.valueOf(nValorAcrescimento.longValue()));
                                txtValorFinal.setValue(nValorVenda);
                            }
                        } catch (Exception ex) {
                                 LogDinnamus.Log(ex);
                        }
   
                     
                        
                        Date dDataParcela=ManipularData.Adicionar_Subtrair_Data(ManipularData.DataAtual(), nIntervalo, Calendar.DATE);
                        txtCodForma.setText(obj[0].toString());
                        txtNomeForma.setText(obj[1].toString());
                        txtValorForma.setValue( BigDecimal.valueOf(nValorForma));
                        txtNumeroParcelas.setText("1");
                        //txtPrimeiroVencimento.setText( DataHora.getData(DataHora.FormatDataPadrao, dDataParcela) );
                        txtPrimeiroVencimento.setDate(dDataParcela);
                        txtValorForma.requestFocus();
                        tbFormasPagto.setSelectedIndex(0);
                    }
                }
            }else if(e.getKeyCode()==KeyEvent.VK_F2)
            {
                Evento_F2();
            }
        }
        });
        
        bRet=true;
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
    
    return bRet;
}
private boolean FormasDePagto_AtualizarGrid(Long nCodigo)
{
   
    try {
         dbgFormasPagto.IniciarTabela("SELECT CODFORMA AS COD,GRUPOFORMA AS NOME,VALOR,IDUNICO FROM PAGTOORC WHERE CODIGO="+ nCodigo  , 0,"IDUNICO" ,50, true );
         dbgFormasPagto.TamanhoColunas(new int[]{40,200,60,0});
         dbgFormasPagto.update(dbgFormasPagto.getGraphics());
         return true;
    } catch (Exception e) {
        LogDinnamus.Log(e);
        return false;
    }

}
private boolean IniciarUI_FormasDePagto()
{
    boolean bRet=false;
    try {
        
        FormasDePagto_AtualizarGrid((getDadosorc().getCodigo()==null ? 0 :getDadosorc().getCodigo()));        
        dbgFormasPagto.getjTable().getTableHeader().setResizingAllowed(false);
        dbgFormasPagto.getjTable().getTableHeader().setReorderingAllowed(false);
        dbgFormasPagto.getjTable().addKeyListener( new KeyAdapter() {
                @Override
            public void keyTyped(KeyEvent e) {}

                @Override
            public void keyPressed(KeyEvent e) {
                    if(e.getKeyCode()==KeyEvent.VK_ENTER){
                        if(ValorFaltaDistribuir()==0){
                            btEfetivar.requestFocus();
                        }else{
                            dbgOpcoesPagto.getjTable().requestFocus();
                        }
                    }
            }

                @Override
            public void keyReleased(KeyEvent e) {}
        }
        );
        bRet=true;
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }

    return bRet;
}
private boolean IniciarUI_FormasDePagto_Parcelas()
{
    boolean bRet=false;
    try {
        FormasDePagto_Parcelas_AtualizarGrid((getDadosorc().getCodigo()==null ?  0 : getDadosorc().getCodigo()));
        dbgParcelas.TamanhoColunas(new int[]{40,100,100,0});
        dbgParcelas.getjTable().getTableHeader().setResizingAllowed(false);
        dbgParcelas.getjTable().getTableHeader().setReorderingAllowed(false);
        HashMap<String, DateFormat> hashMapData = new HashMap<String, DateFormat>();
        hashMapData.put("DATA", (new SimpleDateFormat("dd/MM/yyyy")));
        dbgParcelas.setDateFormat(hashMapData);        
        bRet=true;
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }

    return bRet;
}
private boolean FormasDePagto_Parcelas_AtualizarGrid(Long nCodigo)
{
   
    try {
         dbgParcelas.IniciarTabela("SELECT parcela AS Prc,Data,VALOR,IDUNICO FROM PARCORC WHERE CODIGO="+ nCodigo  , 0,"IDUNICO" ,50,true );
         dbgParcelas.getjTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                            int[] nLinha = dbgParcelas.getjTable().getSelectedRows();
                            TableModel tm = dbgParcelas.getjTable().getModel();
                            if(nLinha.length>0)
                            {                               
                                txtParcela.setText(tm.getValueAt(nLinha[0], 0).toString());                                
                                txtVencimentoParcela.setDate(DataHora.getStringToData(tm.getValueAt(nLinha[0], 1).toString(),"dd/MM/yyyy"));
                                String cNumero=tm.getValueAt(nLinha[0], 2).toString().toUpperCase();
                                txtValorParcela.setValue(FormatarNumero.FormatarNumero(cNumero));
                                
                            }
                }
            });
         dbgParcelas.update(dbgParcelas.getGraphics());
         return true;
    } catch (Exception e) {
        LogDinnamus.Log(e);
        return false;
    }

}
protected boolean InserirFormaPagto(Integer nTipoForma, Integer nLoja)
{
    boolean bRet=false;

    try {

    

    } catch (Exception e) {
            LogDinnamus.Log(e);
    }

    return bRet;
}
private Float SomarDescontoAtacado(Long nCodigoVenda){
    Float  Desconto =0f;
    try {
        ResultSet rs = Venda.DescontoAtacado_ListarItem(nCodigoVenda);
        
          while(rs.next()){
                Float DescontoItem = rs.getFloat("desconto");
                if(DescontoItem>0){
                    Desconto+=DescontoItem;
                }
          }        
          
          rs.beforeFirst();
          
          rsDescontoAtacado = rs;
          
        return Desconto;
    } catch (Exception e) {
        LogDinnamus.Log(e, true);
        return Desconto;
    }

}
private boolean PrepararFechamento()
{
    boolean bRet=false;
    try {
        if(PagtoorcRN.PagtoOrc_Excluir(getDadosorc().getCodigo(),Long.valueOf(0),true)){
            if(GridOpcoesPagto_Atualizar()){
                if(FormasDePagto_AtualizarGrid(Long.valueOf(0))){
                    if(FormasDePagto_Parcelas_AtualizarGrid(Long.valueOf(0))){
                        Float DescontoAtacado =0f;
                        if(Venda.DescontoAtacado_Verificar(getDadosorc().getCodigo()).next()){      
                            DescontoAtacado =SomarDescontoAtacado(getDadosorc().getCodigo());
                            txtDescontoAtacado.setValue(DescontoAtacado);
                        }else{
                            txtDescontoAtacado.setValue(0f);
                            rsDescontoAtacado =null;
                        }
                        
                        tpPrincipal.setSelectedIndex(1);                        
                        txtPrimeiroVencimento.setDate(null);
                        txtValorForma.setValue(BigDecimal.ZERO);
                        txtValorVenda.setText(txtTotal.getText());
                        
                        Float ValorFinal =   Float.parseFloat(txtTotal.getValue().toString()) - DescontoAtacado;
                        
                        txtValorFinal.setValue(FormatarNumero.FormatarNumero(ValorFinal.toString()));
                        txtValorFinal.commitEdit();
                        AlterarDescontos(0f, 0f, FormatarNumero.FormatarNumero(txtTotal.getText()));
                      
                        txtDescPerc.requestFocus();
                        txtCodForma.setText("");
                        txtNumeroParcelas.setText("");
                        txtNomeForma.setText("");
                        txtProcurarForma.setText("");
                        txtDinheiro.setValue(0);
                        txtTroco.setValue(0);
                        txtAcrecVal.setValue(0);
                        txtAcrescPerc.setValue(0);
                        
                        txtParcela.setText("");
                        txtVencimentoParcela.setDate(null);
                        txtValorParcela.setText("0");
                        
                        
                    }
                }
            }
        }
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
    return bRet;
}
private void txtProcurarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcurarKeyPressed



        if(evt.getKeyChar()=='\n'){
            if(txtProcurar.getText().toString().trim().length()>0)
            {
               ProcurarProduto();
               
            }
            else
            {
                if(arItensorc.size()>0 || FormatarNumero.FormatarNumero(txtTotal.getText()) >0f)
                {
                    if(PrepararFechamento())
                    {
                        evt.consume();                        
                    }
                }
            }

        }
        else if(evt.getKeyChar()=='*')
        {
            evt.consume();
            if(txtProcurar.getText()!=null)
            {
                String cConteudoQt=txtProcurar.getText();
                //cConteudoQt=cConteudoQt.replaceAll("\\*", "");
                boolean bValidarFracionado=false;
                int nTamanhoMaximo =4;
                
                if(cConteudoQt.matches("^[0-9]*$") || cConteudoQt.contains(".") )
                {                   
                    
                    txtProcurar.setText("");
                    if(cConteudoQt.length()<=8)
                    {                        
                        try {
                            
                             Float nQtDigitada = Float.parseFloat((cConteudoQt));
                             int nPosPonto = cConteudoQt.indexOf(".");    
                             if (nPosPonto>=0) {
                                
                                String CasasDecimais = cConteudoQt.substring(nPosPonto+1) ;
                                 if (CasasDecimais.length()>3) {
                                     MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informação digitada Inválida(9999.999)", "Quantidade Livre", "AVISO");                                
                                     txtProcurar.requestFocus();
                                     return;                                                                      
                                 }                                        
                             }                                      
                             
                             String ParteInteira = "";
                             if(nPosPonto>0){                               
                               ParteInteira = cConteudoQt.substring(0,nPosPonto);
                             }else{
                                 ParteInteira=cConteudoQt;
                             }
                                     
                             if (ParteInteira.length()>4) {  
                                 MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informação digitada Inválida(1-9999)", "Quantidade Livre", "AVISO");

                             }else{
                                    txtQuantidade.setText(cConteudoQt);
                             }                           
                             
                             
                             
                        } catch (Exception e) {
                            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informação digitada Inválida", "Quantidade Livre", "AVISO");
                        }     
                       
                    }
                    else
                    {
                        LimparCampoValoresProdutos();
                        MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informação digitada acima do limite aceitavel", "Quantidade Livre", "AVISO");
                    }
                }
            }
        }
        txtProcurar.requestFocus();

}//GEN-LAST:event_txtProcurarKeyPressed

private void btExtornarVendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExtornarVendaActionPerformed
    // TODO add your handling code here:
    
    try {
        if(arItensorc.isEmpty())
        {/*
            frmExtornarVenda extornarVenda = new frmExtornarVenda(null, true);
            extornarVenda.nCodigoFilial = nCodigoFilial;
            extornarVenda.nCodigoLoja= Sistema.getLojaAtual();
            extornarVenda.nCodigoPDV = pdvgerenciar.CodigoPDV();
            extornarVenda.ControleCX=getControleCX();
            extornarVenda.setLocationRelativeTo(null);
            extornarVenda.setVisible(true);
           */ 
        }
        
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
            
}//GEN-LAST:event_btExtornarVendaActionPerformed

private void cbVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbVendedorActionPerformed
    // TODO add your handling code here:
    
}//GEN-LAST:event_cbVendedorActionPerformed

private void cbVendedorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbVendedorKeyPressed

    // TODO add your handling code here:
    if(evt.getKeyChar()=='\n')
    {
        txtProcurar.requestFocus();
    }
}//GEN-LAST:event_cbVendedorKeyPressed

private void cbTbPrecoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbTbPrecoKeyPressed
    // TODO add your handling code here:
   if(evt.getKeyChar()=='\n')
    {
        txtProcurar.requestFocus();
    }
}//GEN-LAST:event_cbTbPrecoKeyPressed

private void txtDescPercActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescPercActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_txtDescPercActionPerformed

private void txtValorFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorFinalActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_txtValorFinalActionPerformed

private void txtProcurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtProcurarActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_txtProcurarActionPerformed

private void txtDescPercFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescPercFocusGained

    // TODO add your handling code here:    
        SwingUtilidade.SelectAll(txtDescPerc);
}//GEN-LAST:event_txtDescPercFocusGained

private void txtDescValFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescValFocusGained
    // TODO add your handling code here:
    SwingUtilidade.SelectAll(txtDescVal);
}//GEN-LAST:event_txtDescValFocusGained

private void txtDescPercKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescPercKeyPressed
    // TODO add your handling code here:
    if(evt.getKeyChar()=='\n')
    {
        txtDescVal.requestFocus();
    }
}//GEN-LAST:event_txtDescPercKeyPressed

private void txtDescValKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescValKeyPressed
    // TODO add your handling code here:
    if(evt.getKeyChar()=='\n')
    {
        evt.consume();
        txtProcurarForma.requestFocus();
        
    }
}//GEN-LAST:event_txtDescValKeyPressed

private void txtDescValActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescValActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_txtDescValActionPerformed

private void txtProcurarFormaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcurarFormaKeyReleased
    // TODO add your handling code here:
    if(evt.getKeyCode()==KeyEvent.VK_DOWN)
    {        
        dbgOpcoesPagto.getjTable().requestFocus();
        dbgOpcoesPagto.getjTable().setRowSelectionInterval(0, 0);
    }
}//GEN-LAST:event_txtProcurarFormaKeyReleased

private void txtDinheiroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDinheiroKeyPressed
    // TODO add your handling code here:
    if(evt.getKeyCode()==KeyEvent.VK_ENTER)
    {
        btEfetivar.requestFocus();
    }

}//GEN-LAST:event_txtDinheiroKeyPressed
private Double ValorFaltaDistribuir()
{
    Double nValorRetorno=0d;
    Double nValorFinal =0d;
    Double nValalorDistribuido=0d;
    try {

        nValorFinal = FormatarNumero.FormatarNumero_Double(txtValorFinal.getText());
        nValalorDistribuido = PagtoorcRN.Pagtorc_SomarValores(getDadosorc().getCodigo());
        nValorRetorno = NumeroArredondar.Arredondar_Double(nValorFinal - nValalorDistribuido,2);

    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
    return nValorRetorno;
}
private boolean CadastroDeCheques(Dadosorc d, Float nValor){
    boolean bRet=false;
    String cContole="";
    try {
        ResultSet rsPagtoorc = PagtoorcRN.PagtoOrc_Listar(d.getCodigo());
        if(rsPagtoorc.last()){
           cContole = String.valueOf(rsPagtoorc.getInt("idunico"));
        }
        new frmCadCheques(null,true,d,cContole,pdvgerenciar.CodigoPDV(),nValor );
        
        bRet=true;
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
    return bRet;
}
private boolean IncluirFormaPagamento(Dadosorc d,Long nCodigoVenda, Integer nCodigoForma, int nLoja, Double nValorForma, int nQtParcelas, int nCodigoPDV){
    return IncluirFormaPagamento( d, nCodigoVenda,  nCodigoForma,  nLoja,  nValorForma,  nQtParcelas,  nCodigoPDV, 0d );

}
private boolean IncluirFormaPagamento(Dadosorc d,Long nCodigoVenda, Integer nCodigoForma, int nLoja, Double nValorForma, int nQtParcelas, int nCodigoPDV, Double nValorAcrescimento )
{
    boolean bRet=false;
    try {
       

        if(PagtoorcRN.Pagtoorc_GerarParcelasFormas(d,nCodigoVenda ,nCodigoForma, nLoja,nValorForma,nQtParcelas,nCodigoPDV,0d)) {
            ResultSet rs = TiposdePagamentos.Listar(nCodigoForma, nLoja);
            if(rs.next()){
                if(rs.getString("destino").equalsIgnoreCase("Cheques Recebidos")){
                  CadastroDeCheques(d,nValorForma);
                }
            }
            FormasDePagto_AtualizarGrid(nCodigoVenda);
            FormasDePagto_Parcelas_AtualizarGrid(nCodigoVenda);
            tbFormasPagto.update(tbFormasPagto.getGraphics());
            txtCodForma.setText("");
            txtNomeForma.setText("");
            txtNumeroParcelas.setText("");
            txtValorForma.setText("");
            if(ValorFaltaDistribuir()>0){
               dbgOpcoesPagto.getjTable().requestFocus();
            }else{
                Float nValorDinheiro = PagtoorcRN.Pagtorc_SomarValoresDinheiros(nCodigoVenda);
                if(nValorDinheiro>0){
                   SwingUtilidade.RequestFocus(txtDinheiro);
                   
                }else{
                    btEfetivar.requestFocus();
                }
            }
            bRet=true;
      }else{
         MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Não foi possível incluir a forma de pagamento", "Operação não realizada", "AVISO");
      }
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
    return bRet;
}
private void btIncluirFormaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btIncluirFormaActionPerformed
    // TODO add your handling code here:
    try {
          Double nValorForma = FormatarNumero.FormatarNumero_Double(txtValorForma.getText());
          Double nValorFaltaDistribuir = ValorFaltaDistribuir();
          Integer nQtParcelas = FormatarNumero.FormatarNumeroInteiro(txtNumeroParcelas.getText());
          if(ValidarFormaPagto(nValorForma, nValorFaltaDistribuir,nQtParcelas)){
               IncluirFormaPagamento(getDadosorc(), getDadosorc().getCodigo(),Integer.valueOf(txtCodForma.getText()), Sistema.getCodigoLojaMatriz(),nValorForma,nQtParcelas,pdvgerenciar.CodigoPDV() );
          }
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
}//GEN-LAST:event_btIncluirFormaActionPerformed

protected boolean ValidarFormaPagto(Double nValorForma,Double nValorFaltaDistribuir,Integer nQtParcelas )
{
    boolean bRet=false;
    try {
        if(txtCodForma.getText().length()>0){
            if(nQtParcelas!=FormatarNumero.INTEIRO_INVALIDO ){
                if(nQtParcelas<=0){
                   MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informe uma quantidade válida de Parcelas", "Operação não realizada", "AVISO");
                   return false;
                }
            }else{
                  MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informe o numero de parcelas", "Operação não realizada", "AVISO");
                  return false;
            }
            if(!nValorForma.isNaN()){
                if(nValorForma>0){
                    if(nValorFaltaDistribuir>0){
                        if(nValorForma>nValorFaltaDistribuir){
                            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O valor da forma de pagamento excede o valor restante", "Valor não permitido", "AVISO");
                            txtValorForma.setValue(BigDecimal.valueOf(nValorFaltaDistribuir));
                        }else{
                            bRet= true;
                        }
                    }else{
                        MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Todo o valor ja foi informado nas formas de pagto", "Valor já Informado", "AVISO");
                    }
                }else{
                        MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Valor informado inválido", "Incluir Forma Pagto", "AVISO");
                }
            }else{
                 MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informe um valor válido", "Valor da Forma de pagamento", "AVISO");
            }
        }else{
            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Não foi informado uma forma de pagto", "Inclusão de Forma Pagto", "AVISO");
        }


    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
    return bRet;
}
private void btRemoverFormaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverFormaActionPerformed
    // TODO add your handling code here:
    try {
        
        Object[] obj = dbgFormasPagto.TratarLinhaSelecionada(dbgFormasPagto.getjTable());
        if(obj!=null)
        {
            Long nIdUnico = Long.parseLong(obj[3].toString());
            if(PagtoorcRN.PagtoOrc_Excluir(getDadosorc().getCodigo(),nIdUnico,true))
            {
               FormasDePagto_AtualizarGrid(getDadosorc().getCodigo());
               FormasDePagto_Parcelas_AtualizarGrid(getDadosorc().getCodigo());
               tbFormasPagto.update(tbFormasPagto.getGraphics());
               dbgOpcoesPagto.getjTable().requestFocus();

            }
            else
            {
                MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Não foi possível excluir a forma de pagto", "Exclusão de Forma Pagto", "AVISO");
            }
        }        
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }

}//GEN-LAST:event_btRemoverFormaActionPerformed

private void txtDinheiroFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDinheiroFocusGained
    // TODO add your handling code here:

        try {
            Float nValorDinheiro = PagtoorcRN.Pagtorc_SomarValoresDinheiros(getDadosorc().getCodigo());
            txtDinheiro.setValue(nValorDinheiro);
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        SwingUtilidade.SelectAll(txtDinheiro);
}//GEN-LAST:event_txtDinheiroFocusGained

private void dbgOpcoesPagtoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dbgOpcoesPagtoKeyPressed

    // TODO add your handling code here:
    if(evt.getKeyCode() ==KeyEvent.VK_ENTER)
    {
        Float nValorVenda = Float.valueOf(txtValorFinal.getText());
        if(PagtoorcRN.Pagtorc_SomarValores(dadosorc.getCodigo()).floatValue()==nValorVenda.floatValue() )
        {
            txtDinheiro.setText(PagtoorcRN.Pagtorc_SomarValoresDinheiros(dadosorc.getCodigo()).toString());
            txtDinheiro.requestFocus();
        }
    }
}//GEN-LAST:event_dbgOpcoesPagtoKeyPressed

private void opModoVendaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_opModoVendaFocusGained
    // TODO add your handling code here:
    txtProcurar.requestFocus();
}//GEN-LAST:event_opModoVendaFocusGained

private void txtNomeFormaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeFormaKeyPressed
    // TODO add your handling code here:
}//GEN-LAST:event_txtNomeFormaKeyPressed

private void txtCodFormaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodFormaKeyPressed
    // TODO add your handling code here:
}//GEN-LAST:event_txtCodFormaKeyPressed

private void txtNumeroParcelasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtNumeroParcelasFocusGained
    // TODO add your handling code here:
    ((JTextField)evt.getSource()).selectAll();
}//GEN-LAST:event_txtNumeroParcelasFocusGained
/*

 *
 *
 */
private void txtNumeroParcelasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumeroParcelasKeyPressed
    // TODO add your handling code here:
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            txtPrimeiroVencimento.getDateEditor().getUiComponent().requestFocus();

        }
}//GEN-LAST:event_txtNumeroParcelasKeyPressed

private void txtPrimeiroVencimentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrimeiroVencimentoKeyPressed
    // TODO add your handling code here:
    if(evt.getKeyCode()==KeyEvent.VK_ENTER)
    {
        btIncluirForma.requestFocus();

    }
}//GEN-LAST:event_txtPrimeiroVencimentoKeyPressed

private void txtValorFormaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorFormaKeyPressed
    // TODO add your handling code here:
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
    txtNumeroParcelas.requestFocus();
     try {
            Float nValorFaltaDistribuir = ValorFaltaDistribuir();
            if(nValorFaltaDistribuir>0){
                Float nValorForma = FormatarNumero.FormatarNumero(txtValorForma.getText());
                if(ValidarFormaPagto( nValorForma, nValorFaltaDistribuir, 1)){
                   ResultSet rs = TiposdePagamentos.Listar((Integer.parseInt(txtCodForma.getText())), Sistema.getCodigoLojaMatriz());
                   if(rs.next()){                     
                        if(TratamentoNulos.getTratarString().Tratar(rs.getString("utilizacheque"), "S").equalsIgnoreCase("S")){
                            IncluirFormaPagamento(getDadosorc(), getDadosorc().getCodigo(),Integer.parseInt(txtCodForma.getText()),Sistema.getCodigoLojaMatriz(),nValorForma,1,pdvgerenciar.CodigoPDV());
                        }                     
                   }
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }

}//GEN-LAST:event_txtValorFormaKeyPressed

private void txtValorFormaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorFormaFocusGained
    // TODO add your handling code here:
    SwingUtilidade.SelectAll(txtValorForma);

}//GEN-LAST:event_txtValorFormaFocusGained
private void AlterarDescontos(Float nPercDesconto, Float nValorDesconto, Float nValorFinal)
{
    try {
        txtDescPerc.setValue(nPercDesconto);
        txtDescPerc.commitEdit();
        txtDescVal.setValue(nValorDesconto);
        txtDescVal.commitEdit();
        if(nValorFinal>0){
            txtValorFinal.setValue(nValorFinal - Float.valueOf(txtDescontoAtacado.getValue().toString()));
            txtValorFinal.commitEdit();
        }
        getDadosorc().setDesconto(nValorDesconto);
        getDadosorc().setPercdesc(BigDecimal.valueOf(nPercDesconto));
        
        //txtDescVal.commitEdit();
        //txtDescPerc.commitEdit();
        //txtValorFinal.commitEdit();
    } catch (Exception e) {
        LogDinnamus.Log(e);
        txtDescPerc.setText("0");
        txtDescVal.setText("0");
    }


}
private void txtDescPercFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescPercFocusLost
    // TODO add your handling code here:
    Float nValorVenda=0f;

    try {        
        txtValorVenda.commitEdit();
        txtDescPerc.commitEdit();
        //Float  nValorDescontoAtual= FormatarNumero.FormatarNumero(txtDescVal.getValue().toString());
        nValorVenda = FormatarNumero.FormatarNumero(txtValorVenda.getValue().toString());
        Float nPercDesc =   Float.valueOf(txtDescPerc.getValue().toString());
        Float nValorFinal =0f, nValorDesconto=0f;

        if(nValorFinal.isNaN() || nPercDesc.isNaN()){
            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informe um percentual válido[1-99%]", "%Desconto", "AVISO");
            AlterarDescontos(0f,0f,nValorVenda);
            txtDescPerc.requestFocus();
        }else{        
            if(nPercDesc>0 && nPercDesc<100){

                nValorDesconto = nValorVenda * (nPercDesc/100);
                nValorFinal=  NumeroArredondar.Arredondar2(nValorVenda - nValorDesconto,2);
                if(getDadosorc().getDesconto().floatValue()!=nValorDesconto.floatValue()){
                    AlterarDescontos(nPercDesc,nValorDesconto,nValorFinal);
                }
            }else{
                AlterarDescontos(0f,0f,nValorVenda);
                if(nPercDesc!=0){
                    MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informe um percentual válido [1-99%] ", "%Desconto", "AVISO");
                    txtDescPerc.requestFocus();
                }
            }
        }
    } catch (Exception e) {
        MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informe um percentual válido [1-99%] ", "%Desconto", "AVISO");
        txtDescPerc.requestFocus();
        AlterarDescontos(0f,0f,nValorVenda);
        LogDinnamus.Log(e);
    }

}//GEN-LAST:event_txtDescPercFocusLost


private void txtDescValFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDescValFocusLost
    // TODO add your handling code here:
    Float nValorVenda=0f;
    try {
        Float nPercDescAtual = FormatarNumero.FormatarNumero( txtDescPerc.getValue().toString());
        Float nPercDesconto = 0f;
        txtDescVal.commitEdit();
        Float nValorFinal = 0f;
        Float nValorDesconto= Float.valueOf(txtDescVal.getValue().toString());
        nValorVenda = FormatarNumero.FormatarNumero(txtValorVenda.getText());        

        if(!nValorDesconto.isNaN() && !nValorVenda.isNaN()){
            if(nValorDesconto<0 || nValorDesconto >=nValorVenda){
                MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O Valor do desconto não pode ser menor que 0(zero) ou maior que o valor da venda", "$Desconto", "AVISO");
                AlterarDescontos(0f, 0f, nValorVenda);
                txtDescVal.requestFocus();
            }else{
                nValorVenda= NumeroArredondar.Arredondar(nValorVenda, 2);
                if(nValorDesconto.floatValue()>0f){
                    nValorFinal = NumeroArredondar.Arredondar2(nValorVenda-nValorDesconto,2);
                    //Float nValorDescontoGravado= NumeroArredondar.Arredondar(getDadosorc().getDesconto().floatValue(),2);
                    nPercDesconto = (nValorDesconto/nValorVenda)*100;
                    boolean bDescontoLiberado=true;
                    if(nPercDesconto.floatValue()!=getnDescontoLiberado().floatValue()){
                        bDescontoLiberado=Venda.VerificarPermissaoLiberarDesconto(getDadosorc().getCodigo(), nPercDesconto, UsuarioSistema.getIdUsuarioLogado().longValue());
                    }
                    if(!bDescontoLiberado){
                       MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Usuário não autorizado a liberar esse desconto", "Solicitação negada", "AVISO");            //}
                       AlterarDescontos(0f, 0f, nValorVenda);
                       setnDescontoLiberado(0F);
                    }else{
                         Float nValorDescontoConcedido = NumeroArredondar.Arredondar2(nValorVenda * (nPercDescAtual/100),2);
                         if(nValorDescontoConcedido!=nValorDesconto){
                               AlterarDescontos(NumeroArredondar.Arredondar2(nPercDesconto,2), nValorDesconto, nValorFinal);
                               setnDescontoLiberado(nPercDesconto);
                         }
                         else{
                            setnDescontoLiberado(nPercDescAtual);
                         }
                    }
                }else{
                    setnDescontoLiberado(0f);
                    AlterarDescontos(0f, 0f, nValorVenda);
                }
            }
        }else{
            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informe um valor válido", "$Desconto", "AVISO");            //}
            AlterarDescontos(0f,0f,nValorVenda);
            txtDescVal.requestFocus();
        }

    } catch (Exception e) {
        MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informe um valor válido", "$Desconto", "AVISO");
        txtDescVal.requestFocus();
        AlterarDescontos(0f,0f,nValorVenda);
        LogDinnamus.Log(e);
    }
}//GEN-LAST:event_txtDescValFocusLost
private boolean AtualizaValorFinalVenda(){
    try {
        
        
        BigDecimal nValorDesconto = new BigDecimal(txtDescVal.getValue().toString());
        BigDecimal nValorAcrescimo = new BigDecimal(txtAcrecVal.getValue().toString());
        BigDecimal nValorVenda  = new BigDecimal(txtValorVenda.getValue().toString());
        BigDecimal nValorFinal = nValorVenda.add(nValorAcrescimo).subtract( nValorDesconto);
        txtValorFinal.setValue(nValorFinal);
        
        
        return true;
    } catch (Exception e) {
        
        LogDinnamus.Log(e);
        return false;
    }
     
}
private void txtProcurarFormaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcurarFormaKeyPressed
    // TODO add your handling code here:
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        try {

            if(txtProcurarForma.getText().length()>0){
               if(!dbgOpcoesPagto.Localizar("cod", txtProcurarForma.getText())){
                   MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Informação não localizada", "Procurar Forma", "AVISO");
                   txtProcurarForma.setText("");
               }else{
                   dbgOpcoesPagto.getjTable().requestFocus();
               }
            }else{
                dbgOpcoesPagto.getjTable().requestFocus();
                dbgOpcoesPagto.getjTable().setRowSelectionInterval(0, 0);

            }

            //filtrarNomeNaTabela(txtProcurarForma.getText());
            //ResultSet rs = dbgOpcoesPagto.getRsDados();

        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }
}//GEN-LAST:event_txtProcurarFormaKeyPressed

private void txtDinheiroFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtDinheiroFocusLost
    // TODO add your handling code here:
    try {
        Float nValorDinheiroVenda = PagtoorcRN.Pagtorc_SomarValoresDinheiros(getDadosorc().getCodigo());
        Float nValorDinheiroInformado = NumeroArredondar.Arredondar2(Float.parseFloat(txtDinheiro.getValue().toString()),2);
        Float nTroco =0f;
        if(nValorDinheiroInformado < nValorDinheiroVenda){
            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O dinheiro informado é inferior ao valor  solicitado na venda", "Valor Insuficiente", "AVISO");
            //txtDinheiro.setValue(0);
            SwingUtilidade.RequestFocus(txtDinheiro);
            //(new Throwable("Valor Invalido")).
        }else{
            nTroco = nValorDinheiroInformado - nValorDinheiroVenda;
            txtTroco.setValue(nTroco);
            btEfetivar.requestFocus();            
            if(btGaveta.isSelected() && btImprimirComprovante.isSelected()){
                PDVComprovante.AbrirGaveta(TipoComprovante(),NomeImpressoraComprovante);
            }
        }

    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
}//GEN-LAST:event_txtDinheiroFocusLost

private void txtValorParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorParcelaActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_txtValorParcelaActionPerformed

private void txtValorParcelaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtValorParcelaKeyPressed
    // TODO add your handling code here:
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        btGravarParcela.requestFocus();
    }
}//GEN-LAST:event_txtValorParcelaKeyPressed

private void txtVencimentoParcelaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtVencimentoParcelaKeyPressed
    // TODO add your handling code here:
    if(evt.getKeyCode()==KeyEvent.VK_ENTER){
        SwingUtilidade.RequestFocus(txtValorParcela);
    }
}//GEN-LAST:event_txtVencimentoParcelaKeyPressed

private void txtParcelaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParcelaKeyPressed
    // TODO add your handling code here:
}//GEN-LAST:event_txtParcelaKeyPressed

private void txtParcelaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtParcelaKeyReleased
    // TODO add your handling code here:
}//GEN-LAST:event_txtParcelaKeyReleased

private void btGravarParcelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGravarParcelaActionPerformed
    // TODO add your handling code here:
    try {
        Integer nParc =0;
        Date dDataVencimento =null;
        Float nValor =0f;

        if(txtParcela.getText().length()>0 && txtValorParcela.getText().length()>0){
            if(FormatarNumero.FormatarNumero(txtValorParcela.getText())!=Float.NaN){
                if(DataHora.IsDateValid("dd/MM/yyyy", txtVencimentoParcela.getDate())){
                    nParc = Integer.valueOf(txtParcela.getText());
                    dDataVencimento = txtVencimentoParcela.getDate();
                    nValor = FormatarNumero.FormatarNumero(txtValorParcela.getText());
                    if(!ParcorcRN.Parcorc_AlterarParcelas(getDadosorc().getCodigo(), nParc, dDataVencimento, nValor)){
                        MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Não foi possível alterar a parcela", "Alteração da Parcela", "AVISO");
                    }else{
                        FormasDePagto_Parcelas_AtualizarGrid((getDadosorc().getCodigo()==null ?  0 : getDadosorc().getCodigo()));
                        Integer nTotalParcelaFormas = ParcorcRN.Parcorc_Contar(getDadosorc().getCodigo());
                        dbgParcelas.getjTable().requestFocus();
                        if(nParc< nTotalParcelaFormas)
                          dbgParcelas.getjTable().setRowSelectionInterval(nParc,nParc);
                        else{
                           if(PagtoorcRN.Pagtorc_SomarValoresDinheiros(getDadosorc().getCodigo())==0f){
                                btEfetivar.requestFocus();
                           }else{
                               SwingUtilidade.RequestFocus(txtDinheiro);
                           }
                        }
                    }
                }else{
                    MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Data inválida", "Alterar Parcela", "AVISO");
                }
            }else{
                 MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Valor inválido", "Alterar Parcela", "AVISO");
            }
        }
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
    
}//GEN-LAST:event_btGravarParcelaActionPerformed

private void txtValorParcelaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtValorParcelaFocusGained
    // TODO add your handling code here:
    SwingUtilidade.SelectAll(txtValorParcela);
}//GEN-LAST:event_txtValorParcelaFocusGained

private void txtVencimentoParcelaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtVencimentoParcelaFocusGained
    // TODO add your handling code here:
    
}//GEN-LAST:event_txtVencimentoParcelaFocusGained
private boolean SetarValoresDadosOrc(){
   
    boolean bRet=false;
    try {
        ResultSet rs = GerenciarCaixa.ListarCaixas(Sistema.getLojaAtual(), UsuarioSistema.getIdUsuarioLogadoCaixa(), 0);
        if(rs.next()){
            ItemLista i ;
            getDadosorc().setCodigocotacao(0l);
            getDadosorc().setCodigoorcamento(0l);
            getDadosorc().setControleCx(getControleCX());
            getDadosorc().setCodcaixa(rs.getInt("OperadorCx"));
            getDadosorc().setObjetoCaixa(rs.getInt("codigo"));
            getDadosorc().setValor(BigDecimal.valueOf( Float.valueOf(txtValorFinal.getValue().toString())));
            //getDadosorc().setCodcliente(txtCodigoCliente.getText());
            //getDadosorc().setCliente(txtNomeCliente.getText());
            getDadosorc().setDesconto(Float.valueOf(txtDescVal.getValue().toString()));
            getDadosorc().setPercdesc(BigDecimal.valueOf(Float.valueOf(txtDescPerc.getValue().toString())));
            getDadosorc().setAcrescimopercentual(Float.valueOf(txtAcrescPerc.getValue().toString()));
            getDadosorc().setAcrescimo(Float.valueOf(txtAcrecVal.getValue().toString()));
            if(cbVendedor.getSelectedIndex()>=0){
                i=(ItemLista)cbVendedor.getSelectedItem();
                if (i.getIndice()==null) {
                   MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "NAO EXISTE UM VENDEDOR VINCULADO AO CAIXA", "PDV", "AVISO");
                   return false;
                }
                
                if (i.getDescricao()==null) {
                   MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "NAO EXISTE UM VENDEDOR VINCULADO AO CAIXA", "PDV", "AVISO");
                   return false;
                }
                
                getDadosorc().setCodvendedor(i.getIndice().toString());
                    
                getDadosorc().setVendedor(i.getDescricao().toString());                    
                
            }
            getDadosorc().setLoja(Sistema.getLojaAtual());
            BigDecimal nTotalBruto = new BigDecimal( FormatarNumero.FormatarNumero(txtValorVenda.getValue().toString()) );
            nTotalBruto.setScale(2, RoundingMode.HALF_EVEN );
            getDadosorc().setTotalbruto(nTotalBruto);
            getDadosorc().setFilial(rs.getInt("filial"));
            getDadosorc().setVendaCondicional(false);
            getDadosorc().setCoo("");
            getDadosorc().setFracao(BigDecimal.ZERO);
            getDadosorc().setDinheiro(BigDecimal.valueOf(Float.parseFloat(txtDinheiro.getValue().toString())));
            getDadosorc().setTroco(BigDecimal.valueOf(Float.parseFloat(txtTroco.getValue().toString())));
            getDadosorc().setPontuacaoatual(BigDecimal.ZERO);
            getDadosorc().setPontuacaoresgate(BigDecimal.ZERO);
            getDadosorc().setPontuacaovenda(BigDecimal.ZERO);
            getDadosorc().setPdv(pdvgerenciar.CodigoPDV());
        }
        bRet=true;

    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
    return bRet;
}
private boolean EfetivarVenda() {
    boolean bRet=false;

    try {
        if (SetarValoresDadosOrc()) {
            
            if(Venda.VerificaSeECrediario(getDadosorc().getCodigo())){
                Long nCodigoCliente=0l;
                try {
                    nCodigoCliente = Long.parseLong(getDadosorc().getCodcliente());
                } catch (Exception e) {
                }
                if(nCodigoCliente>0){
                   if(!Crediario.VerificarLimite(Sistema.getLojaAtual(),  nCodigoCliente, getDadosorc().getCodigo())){
                      return false; 
                   }                        
                }else{
                    MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "NÃO FOI IDENTIFICADO O CLIENTE", "VENDA CREDIARIO", "AVISO");
                    return true;
                }       
            }
            
            
            boolean bAlterarVerificacao =false;
            if(VerificarStatusServidor.isVerificarServidor()){
                VerificarStatusServidor.setVerificarServidor(false);
                bAlterarVerificacao=true;
            }
            boolean bImprimiuOK=true;
            

            
            if(btImprimirComprovante.isSelected()){
                bRet=PDVComprovante.FecharCupomVenda(getDadosorc(),rsDescontoAtacado,1,TipoComprovante(),getEcfDinnmus(),true,0l,false,null);
                if(!bRet){
                    MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Não foi possível finalizar o comprovante ", "Venda Efetivada sem Impressão", "Aviso");                    
                    bImprimiuOK=false;
                }                
            }
            if(bImprimiuOK){
                Long CodigoVenda = getDadosorc().getCodigo();
                if (Venda.Venda_Efetivar(getDadosorc(), getArItensorc(), Venda.CodigoTipoMovEstoqueSaidaPorVenda, 0l,pdvgerenciar.CodigoPDV(),false,null)) {                
                    bRet=true;
                    if(VendaEmEdicao.FinalizarNota(getDadosorc().getCodigo(),true ,false)){
                        bRet= true;
                    }                    
                }
                if(bRet){
                    if(bAlterarVerificacao && Sistema.isOnline()){
                       SincronizarMovimento.Iniciar(false,  true,true,CodigoVenda);
                    }
                }
            }else{
                bRet=false;
            }
            if(bAlterarVerificacao)
            {
                VerificarStatusServidor.setVerificarServidor(true);
            }
        }
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
    return bRet;
}

private void btEfetivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEfetivarActionPerformed
    // TODO add your handling code here:
    btEfetivar.setEnabled(false);
    try {

        if(!EfetivarVenda()){
           MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Não foi possível efetivar a venda.", "Problema na efetivação da Venda", "Aviso"); 
           btEfetivar.requestFocus();
        }else{

            ApagarNota(false);
            rsDescontoAtacado = null;
        }
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
    btEfetivar.setEnabled(true);

}//GEN-LAST:event_btEfetivarActionPerformed

private void btClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClienteActionPerformed
    // TODO add your handling code here:
    try {
       // formTabela.setObjLinha(null);
            formTabela.setVisible(true);
            Object[] objLinha=new Object[2];
            Long CodigoCliente =formTabela.ClienteSelecionado;
            String NomeCliente=formTabela.ClienteSelecionadoNome;
            if(CodigoCliente>0l) {
               getDadosorc().setCodcliente(CodigoCliente.toString());
               getDadosorc().setCliente(NomeCliente);
                txtNomeCliente.setText(objLinha[0].toString() + " - " + objLinha[1].toString() );                
            }else{
                getDadosorc().setCodcliente("0");
                getDadosorc().setCliente("** Consumidor **");
            }            
        //}
        txtProcurar.requestFocus();
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }

}//GEN-LAST:event_btClienteActionPerformed

private void btResumoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btResumoActionPerformed
    // TODO add your handling code here:
   new frmResumoCaixa(null, true,Sistema.getLojaAtual(),UsuarioSistema.getIdCaixaAtual(), getControleCX(), UsuarioSistema.getIdUsuarioLogadoCaixa(),false);

}//GEN-LAST:event_btResumoActionPerformed

private void btLeituraXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLeituraXActionPerformed
    // TODO add your handling code here:
    int nRet = MetodosUI_Auxiliares.MensagemAoUsuarioOpcoes(null, "Essa é a 1ª Abertura do Dia ?", "Leitura X", JOptionPane.YES_NO_CANCEL_OPTION ,JOptionPane.QUESTION_MESSAGE);
    if(nRet==JOptionPane.YES_OPTION){
       getEcfDinnmus().AberturaDoDia("0", "");
    }else if(nRet==JOptionPane.NO_OPTION){
          getEcfDinnmus().LeituraX();
    }

}//GEN-LAST:event_btLeituraXActionPerformed

private void btReducaoZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btReducaoZActionPerformed
    // TODO add your handling code here:
    try {
        if(ValidarAcessoAoProcesso.Verificar(null , UsuarioSistema.getIdUsuarioLogado(), "ReduçãoZ", Sistema.getLojaAtual(), true, "Encerramento do Movimento Fiscal - Redução Z")>0){
            if(MetodosUI_Auxiliares.MensagemAoUsuarioOpcoes(null, "Confirma o encerramento do Dia?", "Redução Z", JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
                getEcfDinnmus().FechamentoDoDia();
            }
        }
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
}//GEN-LAST:event_btReducaoZActionPerformed

private void btStatusECFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btStatusECFActionPerformed
    // TODO add your handling code here:
    try {
        ArrayList<String> alMsg = EcfDinnmus.PalavraStatus(true);
        String cMsg = "";
        for(int i=0;i<alMsg.size();i++){
           cMsg += alMsg.get(i) + "\n";
        }
        if(cMsg.length()>0){
            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, cMsg, "Status da Impressora", "AVISO");
        }
        txtProcurar.requestFocus();

    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
}//GEN-LAST:event_btStatusECFActionPerformed

private void btCancelarCupomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarCupomActionPerformed
    // TODO add your handling code here:
    try {
        if(ValidarAcessoAoProcesso.Verificar(null , UsuarioSistema.getIdUsuarioLogado(), "CancelarCupom", Sistema.getLojaAtual(), true, "Cancelar Cupom Fiscal")>0){
            if(MetodosUI_Auxiliares.MensagemAoUsuarioOpcoes(null, "Confirma o cancelamento do cupom fiscal","Cancelar Cupom Fiscal", JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
                getEcfDinnmus().CancelarUltimoCupom();
            }
        }
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
}//GEN-LAST:event_btCancelarCupomActionPerformed
private void AtivarDesativarImpressao(){
      try {

            if(btImprimirComprovante.getText().equalsIgnoreCase("Imprimir Comprovante - [ ON ]")){
                btImprimirComprovante.setText("Imprimir Comprovante - [ OFF ]");
                btImprimirComprovante.setSelected(false);
            }else{
                btImprimirComprovante.setText("Imprimir Comprovante - [ ON ]");
                btImprimirComprovante.setSelected(true);
            }

    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
}
private void btImprimirComprovanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btImprimirComprovanteActionPerformed
    // TODO add your handling code here:

      try {
        if (arItensorc.size()==0) {
            AtivarDesativarImpressao();
        }
        else{
            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Existe uma nota aberta. Operação não realizada","Desativar Impressão", "AVISO");
        }
        txtProcurar.requestFocus();
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
}//GEN-LAST:event_btImprimirComprovanteActionPerformed

private void txtDinheiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDinheiroActionPerformed
    // TODO add your handling code here:
}//GEN-LAST:event_txtDinheiroActionPerformed

private void txtNomeClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeClienteKeyPressed
    // TODO add your handling code here:
    try {
        if(evt.getKeyCode() == KeyEvent.VK_DELETE || evt.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            InicializarCampoClientePadrao();
            txtProcurar.requestFocus();
        }

    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
}//GEN-LAST:event_txtNomeClienteKeyPressed

private void btGavetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGavetaActionPerformed
    // TODO add your handling code here:
    try {
        if(btGaveta.isSelected()){
            btGaveta.setText("Gaveta - On");
        }else{
            btGaveta.setText("Gaveta - Off");
        }
        txtProcurar.requestFocus();
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
}//GEN-LAST:event_btGavetaActionPerformed

private void btAbrirGavetaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAbrirGavetaActionPerformed

    try {
        Integer nCodigoUsuario =ValidarAcessoAoProcesso.Verificar(null , UsuarioSistema.getIdUsuarioLogado(), "AbrirGaveta", Sistema.getLojaAtual(), true, "Abertura da Gaveta");    // TODO add your handling code here:
        if(nCodigoUsuario>0){             
             
             if(PDVComprovante.AbrirGaveta(TipoComprovante(),getNomeImpressoraComprovante())){
                 UsuarioAuditar.Auditar(nCodigoUsuario, "VENDAS","Abriu Gaveta");
             }
        }else{
            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Usuário não autorizado", "Abertura de Gaveta", "AVISO");
        }
        txtProcurar.requestFocus();
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }


}//GEN-LAST:event_btAbrirGavetaActionPerformed
private String PesquisarProduto(){
    String Retorno = "";
    try {
        frmPesquisarProduto frm= new frmPesquisarProduto( null,true,nCodigoFilial,"",true);
        frm.setVisible(true);
        Retorno = frm.getCodigoProduto();
        
    } catch (Exception e) {
        LogDinnamus.Log(e, true);
    }
    return Retorno;
}
private void btPesquisarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPesquisarProdutoActionPerformed
    // TODO add your handling code here:
    PesquisarProduto();
    txtProcurar.requestFocus();
}//GEN-LAST:event_btPesquisarProdutoActionPerformed

private void txtProcurarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcurarKeyTyped
    // TODO add your handling code here:
    try {
        String cLetra = String.valueOf(evt.getKeyChar()).toLowerCase();
        if(cLetra.equals("s") && evt.isAltDown()){
           String cRetorno = PesquisarProduto();
           if(cRetorno.length()>0){
               txtProcurar.setText(cRetorno);
               ProcurarProduto();
           }
        }
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
    
}//GEN-LAST:event_txtProcurarKeyTyped

private void txtProcurarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtProcurarKeyReleased

    // TODO add your handling code here:
}//GEN-LAST:event_txtProcurarKeyReleased

private void btModotrabalhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btModotrabalhoActionPerformed
    // TODO add your handling code here:
    try {
        Object[] Opcoes =new Object[] {(opOnline.isSelected() ? "OFF-Line" : "On-Line"),"Automático"};
        String cRet= MetodosUI_Auxiliares.InputBox(null, "Alteração do modo de trabalho \n\nEscolha uma opção Abaixo:\n", "Modo de Trabalho", "AVISO",Opcoes,"");
        if(cRet.equalsIgnoreCase("OFF-Line")){
           VerificarStatusServidor.setVerificarServidor(false);
           Sistema.setOnline(false);
        }else if(cRet.equalsIgnoreCase("On-Line")){
            VerificarStatusServidor.setVerificarServidor(true);
            Sistema.setOnline(true);
        }else{
            VerificarStatusServidor.setVerificarServidor(true);
        }
        txtProcurar.requestFocus();

    } catch (Exception e) {
        LogDinnamus.Log(e);
    }
}//GEN-LAST:event_btModotrabalhoActionPerformed

private void btSincronizarCadastrosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSincronizarCadastrosActionPerformed
// TODO add your handling code here:
    try {
        if (arItensorc.size()==0) {
            if(Sistema.isOnline()){             
                boolean bExecSincronismo = DAO_RepositorioLocal_Inicializar.bExecutandoSincronismo;

                //MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null,String.valueOf(bExecSincronismo),"TEste","Aviso");

                 if(DAO_RepositorioLocal_Inicializar.bExecutandoSincronismo)
                 {
                     MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O sistema esta realizando o sincronismo automatico. \n\nAguarde alguns instante e tente novamente", "Sincronismo Cadastro", "INFO");
                     return;             
                 }       
                 if(MetodosUI_Auxiliares.MensagemAoUsuarioOpcoes(null,"Confirma o sicronismo dos cadastros ?","SINCRONIZAR CADASTROS" , JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){        
                     String cRet="";
                     boolean bSincTotal =false;
                     Object[] Opcoes=new Object[] {"Ultimas Modificações","Sincronismo Completo"};
                     cRet= MetodosUI_Auxiliares.InputBox(null, "Informe o tipo de Atualização", "Tipo de Sincronismo", "AVISO",Opcoes,"Ultimas Modificações");

                     if(cRet!=null){
                         if (cRet.equalsIgnoreCase("Sincronismo Completo")) {
                             bSincTotal=true;
                         }
                     }else{
                         return;
                     }    

                     if(DAO_RepositorioLocal_Inicializar.Executar(Sistema.getDataUltimoSinc(), "",false,bSincTotal))
                     {
                         MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Sincronismo Realizado com sucesso", "Sincronismo Cadastro", "INFO");
                     }
                     else
                     {
                         MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Não foi possível realizar o sincronismo", "Sincronismo Cadastro", "AVISO");
                     }            


                 }
            }else{
                MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Não é possível realizar o sincronismo com o servidor\\nSistema em modo OFF-Line.", "Sincronismo Cadastro", "AVISO");
            }
        }
        else{
            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Existe uma nota aberta. Operação não realizada","SINCRONISMO", "AVISO");
        }
        txtProcurar.requestFocus();
    } catch (Exception e) {
        LogDinnamus.Log(e, true);
    }
}//GEN-LAST:event_btSincronizarCadastrosActionPerformed

private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_jButton15ActionPerformed

private void txtProcurarAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_txtProcurarAncestorAdded
// TODO add your handling code here:
}//GEN-LAST:event_txtProcurarAncestorAdded

private void txtAcrecValActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAcrecValActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtAcrecValActionPerformed

private void txtAcrecValFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAcrecValFocusGained
// TODO add your handling code here:
}//GEN-LAST:event_txtAcrecValFocusGained

private void txtAcrecValFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAcrecValFocusLost
// TODO add your handling code here:
}//GEN-LAST:event_txtAcrecValFocusLost

private void txtAcrecValKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAcrecValKeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_txtAcrecValKeyPressed

private void txtAcrescPercActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAcrescPercActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_txtAcrescPercActionPerformed

private void txtAcrescPercFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAcrescPercFocusGained
// TODO add your handling code here:
}//GEN-LAST:event_txtAcrescPercFocusGained

private void txtAcrescPercFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtAcrescPercFocusLost
// TODO add your handling code here:
}//GEN-LAST:event_txtAcrescPercFocusLost

private void txtAcrescPercKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAcrescPercKeyPressed
// TODO add your handling code here:
}//GEN-LAST:event_txtAcrescPercKeyPressed

    private void txtDescontoAtacadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescontoAtacadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescontoAtacadoActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton18ActionPerformed

    private void btTabelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTabelaActionPerformed
        // TODO add your handling code here:
        cbTbPreco.requestFocus();
    }//GEN-LAST:event_btTabelaActionPerformed
public void filtrarNomeNaTabela(String text) {
    AbstractTableModel tabela_clientes = (AbstractTableModel) dbgOpcoesPagto.getjTable().getModel();
    final TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(dbgOpcoesPagto.getjTable().getModel());
    dbgOpcoesPagto.getjTable().setRowSorter(sorter);
    //String text = jText_Procura.getText().toUpperCase();

    if (text.length() == 0)
    {
         sorter.setRowFilter(null);
    }
    else
    {
         try
         {
             RowFilter<TableModel, Object> rf = null;
              try {
                     rf = RowFilter.regexFilter(text, 1);
              } catch (java.util.regex.PatternSyntaxException e) {
                  return;
              }

              sorter.setRowFilter(rf);
              
         }
         catch (PatternSyntaxException pse)  {
               System.err.println("Erro");
         }
    }


}
protected boolean VerificarCaixaAberto()
{
    boolean bRet=false;
    ResultSet rs=null;
    try {

        rs = GerenciarCaixa.ListarCaixas(Sistema.getLojaAtual(), UsuarioSistema.getIdUsuarioLogadoCaixa(),0,pdvgerenciar.CodigoPDV(),false);
        if(rs.next())
        {
          if(rs.getString("status").equalsIgnoreCase("F")){
              MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Caixa atual esta FECHADO", "CAIXA FECHADO", "AVISO");
          }else{
              txtCaixa.setText(rs.getString("nome"));
              nCodigoFilial = rs.getInt("filial");
              nCodigoObjetoCaixa = rs.getInt("Codigo");
              if(!GerenciarCaixa.VerificarSeCaixaOFF(Sistema.getLojaAtual(), nCodigoObjetoCaixa)){
                   MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O caixa selecionado não é um caixa off line", "Abertura de Caixa", "AVISO");
                   return false;
               }
               int nCodigoPDV_VinculadoCaixa =GerenciarCaixa.VerificarPDV_VinculadoAoCaixa(Sistema.getLojaAtual(), nCodigoObjetoCaixa);

               if(nCodigoPDV_VinculadoCaixa!=0 && nCodigoPDV_VinculadoCaixa!=pdvgerenciar.CodigoPDV()){
                   MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O caixa ja foi aberto em outro pdv", "Abertura de Caixa", "AVISO");
                   return false;
               } 
             
              setNomeImpressoraComprovante(TratamentoNulos.getTratarString().Tratar( rs.getString("impressoranaofiscal"),""));
              String cECF = TratamentoNulos.getTratarString().Tratar(rs.getString("IMPRESSORAFISCAL"),"NAO CONFIGURADO");
              String cPorta = TratamentoNulos.getTratarString().Tratar(rs.getString("porta"),"COM1");
              if(!cECF.equalsIgnoreCase("NAO CONFIGURADO")){
                EcfDinnmus.setTipoECF(cECF,cPorta);
                PDVComprovante.setEcfatual(EcfDinnmus);
              }
              txtECF.setText(cECF);
              if(nCodigoFilial>0){
                bRet=true;
              }else{
                  MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Nenhum local de estoque foi vinculado ao caixa atual", "Caixa não vinculado", "AVISO");
              }
          }
        }
        else
        {
            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "O operador não possui um caixa aberto", "Pdv DinnamuS", "AVISO");
        }                        
    } catch (Exception e) {
        LogDinnamus.Log(e);
    }

    return bRet;
}

    /**
    * @param args the command line arguments
    */
    public boolean ApagarNotaRegistros(Long nCodigoVenda, boolean bRemoverRegistroNotaEdicao){
        boolean bRet=false;
        try {
            if(nCodigoVenda!=0){
               bRet= DadosorcRN.Dadosorc_Excluir(nCodigoVenda,bRemoverRegistroNotaEdicao);
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return bRet;

    }
    public boolean ApagarNota(boolean bApagarRegistros)
    {
        boolean bRet=false;
        try {
            if(bApagarRegistros){
                Long nCodigoVenda=Long.valueOf(0);
                if(txtSequencia1.getText().length()>0){
                    nCodigoVenda= Long.valueOf(txtSequencia1.getText());
                    if(!ApagarNotaRegistros(nCodigoVenda,true)){
                        return false;
                    }
                }
            }
            txtItens.setText("");
            txtSequencia1.setText("");
            LimparCampoValoresProdutos();
            txtTotal.setValue(null);
            getArItensorc().clear();
            setnDescontoLiberado((Float) 0f);
            setDadosorc(new Dadosorc());
            txtProcurar.requestFocus();
            tpPrincipal.setSelectedIndex(0);
            txtCodForma.setText("");
            txtNumeroParcelas.setText("");
            txtNomeForma.setText("");
            txtProcurarForma.setText("");
            txtDinheiro.setText("");
            txtTroco.setText("");
            txtParcela.setText("");
            txtVencimentoParcela.setDate(null);
            txtValorParcela.setText("0");
            PDVComprovante.setIniciou(false);
            if(InicializarCampoClientePadrao()){
                bRet=true;
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return bRet;
    }
    public boolean IniciarTarefaSincronismo(){
        boolean bRet=false;
        try {
            
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return bRet;
    }
    public boolean InicializarUI()
    {
         boolean bRet=false;
         dadosorc=new Dadosorc();
         itensorc=new Itensorc();
         arItensorc=new ArrayList<Itensorc>();
        if(ValidarAcessoAoProcesso.Verificar(null , UsuarioSistema.getIdUsuarioLogado(), "Check-Out", Sistema.getLojaAtual(), true, "Check-Out")>0){
         if(VerificarCaixaAberto())
            if(DefinirControleTeclasFuncoesCheckout())
             if(InicializarCamposComControleDeDigitacao())
                if(InicializarUI_Combos())
                    if(InicializarUI_Grid())
                        if(InicializarCamposBases())
                            if(InicializarRelogio()){
                               if(VerificarNotaInterrompida()){
                                  bRet=true;
                                  txtProcurar.requestFocus();
                               }
                               
                            }
        }else{
            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, Recursos.getMensagens().getString("acesso.negado"), "Acesso Negado", "AVISO");
        }
        return bRet;

    }
    public boolean InicializarECF(){
        boolean bRet=false;
        try {



        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return bRet;
    }
    public boolean  InicializarCamposComControleDeDigitacao()
    {
        boolean bRet=false;

        try {

            txtProcurar.setDocument(new ControlarDigitacao("ALFANUMERICO",true ));
            bRet=true;
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }

        return bRet;
    }
    public boolean InicializarCampoClientePadrao(){
        boolean bRet=false;
        try {
            Integer nCodigoClientePadrao = TratamentoNulos.getTratarInt().Tratar(Sistema.getDadosLojaAtualSistema().getInt("clientepadrao"),0);
            if(nCodigoClientePadrao>0){
                ResultSet rs = clientes.Listar(Sistema.getLojaAtual(),nCodigoClientePadrao.toString());
                if(rs.next()){
                   String cNomeCliente =TratamentoNulos.getTratarString().Tratar(rs.getString("nome"),"");
                   getDadosorc().setCodcliente(nCodigoClientePadrao.toString());
                   getDadosorc().setCliente(cNomeCliente);
                   txtNomeCliente.setText(nCodigoClientePadrao.toString() + " - " + cNomeCliente);
                }else{
                    getDadosorc().setCodcliente("0");
                    getDadosorc().setCliente("** Consumidor **");
                    txtNomeCliente.setText("** Consumidor **");
                }
            }else{
                getDadosorc().setCodcliente("0");
                getDadosorc().setCliente("** Consumidor **");
                txtNomeCliente.setText("** Consumidor **");
            }
            bRet=true;
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return bRet;
    }
    public boolean  InicializarCamposBases()
    {
        
        try {
            lblNomeEmpresa1.setText(Sistema.getDadosLojaAtualSistema().getString("nomefantasia"));
            txtPDV.setText("PDV : " + String.valueOf(pdvgerenciar.DadosPdv().getInt("CodigoPDV")));
            if(InicializarCampoClientePadrao()){
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(frmPDV.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public boolean  InicializarRelogio()
    {
        try {
            Thread clockThread = new Thread(new ClockRunnable(), "Clock thread");
            clockThread.setDaemon(true);
            clockThread.start();
            return true;
        } catch (Exception e) {
                LogDinnamus.Log(e);
        }
        return false;

    }
    public boolean InicializarUI_Grid(){
        boolean bRet=false;
        if(IniciarUI_FormasDePagto())
            if(IniciarUI_FormasDePagto_Parcelas())
                if(IniciarUI_GridOpcoesPagto())
                    bRet= true;

        return bRet;
    }
    public boolean InicializarUI_Combos()
    {
        boolean bRet=false;
        bRet= MetodosUI_Auxiliares.PreencherCombo(cbTbPreco, tabeladepreco.Listar(Sistema.getCodigoLojaMatriz()), "Descricao", "Codigo", true);
        if(bRet)
        {          
            
            
            bRet= MetodosUI_Auxiliares.PreencherCombo(cbVendedor,vendedor.Listar(Sistema.getLojaAtual()),"Nome","Codigo",true);
            MetodosUI_Auxiliares.SetarOpcaoCombo(cbVendedor, UsuarioSistema.getCodVendedorVinculadoAoCaixa());
            if(bRet)
                bRet=IniciarCamposTexto();
        }

        return bRet;

    }
    public boolean VerificarNotaInterrompida(){
        String cRet="0";
         try {
            ResultSet rs = VendaEmEdicao.VerificarVendaInterrompida();
            if(rs.next()){
                
                String cTipoComprovante=rs.getString("TipoComprovante");
                Object[] Opcoes;
                if(!cTipoComprovante.equalsIgnoreCase("")){
                    Opcoes=new Object[] {"Continuar nota","Reiniciar do 1º Item","Ignorar Nota"};
                }
                else{
                    Opcoes=new Object[] {"Continuar Nota","Ignorar Nota"};
                }
                
                cRet= MetodosUI_Auxiliares.InputBox(null, "Foi identificado um nota interrompida\n\nEscolha uma opção Abaixo:\n", "Nota interrompida", "AVISO",Opcoes,"Continuar Nota");
                cRet= TratamentoNulos.getTratarString().Tratar(cRet, "").trim();
                
                if(cRet.equalsIgnoreCase("Continuar Nota") || cRet.equalsIgnoreCase("Reiniciar do 1º Item") || cRet.equalsIgnoreCase("Ignorar Nota") ){
                    Long nCodigoVenda = rs.getLong("CodigoUltimaVenda");                    
                    if( cRet.equalsIgnoreCase("Continuar Nota")){
                        return ReExibirNotaInterrompida(nCodigoVenda,cTipoComprovante,(cRet.equalsIgnoreCase("Continuar Nota") ?  true : false ),pdvgerenciar.CodigoPDV());
                    }else if(cRet.equalsIgnoreCase("Reiniciar do 1º Item")){
                          if(!cTipoComprovante.equalsIgnoreCase("")){
                              int nRet= MetodosUI_Auxiliares.MensagemAoUsuarioOpcoes(null, "Deseja utilizar o mesmo tipo de compravante ?","Tipo de Comprovante",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
                              if(nRet==JOptionPane.NO_OPTION){
                                if(cTipoComprovante.equalsIgnoreCase("nfiscal")){
                                    cTipoComprovante="fiscal";
                                }else{
                                    cTipoComprovante="CUPOM VENDA";
                                }
                              }else if(nRet==JOptionPane.CANCEL_OPTION){
                                    this.dispose();
                                    return false;
                              }
                          }
                          return ReExibirNotaInterrompida(nCodigoVenda,cTipoComprovante,(cRet.equalsIgnoreCase("Continuar Nota") ?  true : false ),pdvgerenciar.CodigoPDV());
                    }
                    else if(cRet.equalsIgnoreCase("Ignorar Nota")){
                           VendaEmEdicao.FinalizarNota(nCodigoVenda,true,true); 
                    }
                    return true;
                }else{
                    MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Opção inválida", "Nota interrompida", "AVISO");
                    return false;
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }

        return true;
    }
    public boolean IniciarCamposTexto()
    {
        boolean bRet=false;
        try {
            try {
                    ResultSet rs = Sistema.getClientePadrao(Sistema.getLojaAtual(), Sistema.getDadosLoja(Sistema.getLojaAtual(),true).getInt("ClientePadrao"));
                    if(rs!=null){
                        txtNomeCliente.setText(String.valueOf(rs.getInt("Codigo")) + " - " + rs.getString("nome"));
                        getDadosorc().setCodcliente(String.valueOf(rs.getInt("Codigo")));
                        getDadosorc().setCliente(rs.getString("nome"));
                    }else{
                        txtNomeCliente.setText("0 - ** Consumidor **");
                        getDadosorc().setCodcliente("0");
                        getDadosorc().setCliente("");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(frmPDV.class.getName()).log(Level.SEVERE, null, ex);
                }
                txtOperador.setText(UsuarioSistema.getNomeUsuario());
                opModoVenda.setSelected(true);
                bRet=true;
        } catch (Exception e) {
           LogDinnamus.Log(e);
        }
        return bRet;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelItensAtendimento;
    private javax.swing.JPanel PainelPasseProduto;
    private javax.swing.JPanel PainelPasseProduto1;
    private javax.swing.JPanel PainelPreco;
    private javax.swing.JPanel PainelProduto;
    private javax.swing.JPanel PainelQuantidade;
    private javax.swing.JPanel PainelSubtotal;
    private javax.swing.JPanel PainelSubtotal1;
    private javax.swing.JPanel PainelSubtotal10;
    private javax.swing.JPanel PainelSubtotal11;
    private javax.swing.JPanel PainelSubtotal2;
    private javax.swing.JPanel PainelSubtotal3;
    private javax.swing.JPanel PainelSubtotal5;
    private javax.swing.JPanel PainelSubtotal8;
    private javax.swing.JPanel PainelTipoAtendimento;
    private javax.swing.JLabel Sequencia1;
    private javax.swing.JLabel Sequencia2;
    private javax.swing.JLabel Sequencia3;
    private javax.swing.JLabel Sequencia4;
    private javax.swing.JLabel Sequencia5;
    private javax.swing.JButton btAbrirGaveta;
    private javax.swing.JButton btApagar;
    private javax.swing.JButton btCancelarCupom;
    private javax.swing.JButton btCliente;
    private javax.swing.JButton btEfetivar;
    private javax.swing.JButton btExcluirItem;
    private javax.swing.JButton btExtornarVenda;
    private javax.swing.JToggleButton btGaveta;
    private javax.swing.JButton btGravarParcela;
    private javax.swing.JToggleButton btImprimirComprovante;
    private javax.swing.JButton btIncluirForma;
    private javax.swing.JButton btLeituraX;
    private javax.swing.JButton btModotrabalho;
    private javax.swing.JButton btPesquisarProduto;
    private javax.swing.JButton btReducaoZ;
    private javax.swing.JButton btRemoverForma;
    private javax.swing.JButton btResumo;
    private javax.swing.JButton btSair;
    private javax.swing.JButton btSincronizarCadastros;
    private javax.swing.JButton btStatusECF;
    private javax.swing.JButton btTabela;
    private javax.swing.JButton btTipoComprovante;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cbTbPreco;
    private javax.swing.JComboBox cbVendedor;
    private br.com.ui.JTableDinnamuS dbgFormasPagto;
    private br.com.ui.JTableDinnamuS dbgOpcoesPagto;
    private br.com.ui.JTableDinnamuS dbgParcelas;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jscrol;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblNomeEmpresa1;
    private javax.swing.JLabel lblPDV;
    private javax.swing.ButtonGroup opGrupoOpcao;
    private javax.swing.JRadioButton opModoConsulta;
    private javax.swing.JRadioButton opModoVenda;
    private javax.swing.JRadioButton opOffline;
    private javax.swing.JRadioButton opOnline;
    private javax.swing.JTabbedPane tbFormasPagto;
    private javax.swing.JTabbedPane tpMenu;
    private javax.swing.JTabbedPane tpPrincipal;
    private javax.swing.JFormattedTextField txtAcrecVal;
    private javax.swing.JFormattedTextField txtAcrescPerc;
    private javax.swing.JTextField txtCaixa;
    private javax.swing.JTextField txtCodForma;
    private javax.swing.JFormattedTextField txtDescPerc;
    private javax.swing.JFormattedTextField txtDescVal;
    private javax.swing.JFormattedTextField txtDescontoAtacado;
    private javax.swing.JFormattedTextField txtDinheiro;
    private javax.swing.JTextField txtECF;
    private javax.swing.JTextPane txtItens;
    private javax.swing.JTextField txtNomeCliente;
    private javax.swing.JTextField txtNomeForma;
    private javax.swing.JTextArea txtNomeProduto;
    private javax.swing.JTextField txtNumeroParcelas;
    private javax.swing.JTextField txtOperador;
    private javax.swing.JTextField txtPDV;
    private javax.swing.JTextField txtParcela;
    private javax.swing.JFormattedTextField txtPreco;
    private com.toedter.calendar.JDateChooser txtPrimeiroVencimento;
    private javax.swing.JTextField txtProcurar;
    private javax.swing.JTextField txtProcurarForma;
    private javax.swing.JTextField txtQuantidade;
    private javax.swing.JTextField txtSequencia1;
    private javax.swing.JFormattedTextField txtSubTotal;
    private javax.swing.JFormattedTextField txtTotal;
    private javax.swing.JFormattedTextField txtTroco;
    private javax.swing.JFormattedTextField txtValorFinal;
    private javax.swing.JFormattedTextField txtValorForma;
    private javax.swing.JFormattedTextField txtValorParcela;
    private javax.swing.JFormattedTextField txtValorVenda;
    private com.toedter.calendar.JDateChooser txtVencimentoParcela;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the dadosorc
     */
    public Dadosorc getDadosorc() {
        if(dadosorc==null)
            dadosorc=new Dadosorc();

        return dadosorc;
    }

    /**
     * @param dadosorc the dadosorc to set
     */
    public void setDadosorc(Dadosorc dadosorc) {

        this.dadosorc = dadosorc;
    }

    /**
     * @return the itensorc
     */
    public Itensorc getItensorc() {
        if(itensorc==null)
            itensorc=new Itensorc();
        return itensorc;
    }

    /**
     * @param itensorc the itensorc to set
     */
    public void setItensorc(Itensorc itensorc) {
        this.itensorc = itensorc;
    }

    /**
     * @return the arItensorc
     */
    public ArrayList<Itensorc> getArItensorc() {
        if(arItensorc==null)
            arItensorc=new ArrayList<Itensorc>();
        return arItensorc;
    }

    /**
     * @param arItensorc the arItensorc to set
     */
    public void setArItensorc(ArrayList<Itensorc> arItensorc) {
        this.arItensorc = arItensorc;
    }
    
    private class ClockRunnable implements Runnable {
        public void run() {
            try {
                while (true) {
                    lblData.setText((new SimpleDateFormat("dd/MM/yyyy")).format(new Date()));
                    lblHora.setText(( new SimpleDateFormat("HH:mm:ss")).format(new Date()));
                    if(Sistema.isOnline()){
                      if(opOffline.isSelected()){
                          bParaThread=true;
                          opOffline.setSelected(false);
                          bAtualizacaoPedente=true;
                          bParaThread=false;
                      }
                      opOnline.setSelected(true);
                      if(bAtualizacaoPedente && (arItensorc==null ? 0 :arItensorc.size())==0){
                         //if(MetodosUI_Auxiliares.MensagemAoUsuarioOpcoes(null,"O Sistema esta retornando ao modo ONLINE. \n\nGostaria de realizar o sincronismo ?","Sincronizar Movimento" , JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==JOptionPane.YES_OPTION){
                             //SincronizarMovimento.Iniciar(false, false);
                         //}
                         bAtualizacaoPedente=false;
                      }
                    }else{
                      opOffline.setSelected(true);
                      opOnline.setSelected(false);
                    }

                    Thread.sleep(1000);
                }
            }
            catch (InterruptedException e) {
                LogDinnamus.Log(e);
            }
        }
    }

    private void Evento_F2(){
        try {
            if(tpPrincipal.getSelectedIndex()==0 && tpMenu.getSelectedIndex()==0) {
                if(tpMenu.getSelectedIndex()==0) // VENDAS.APAGAR NOTA
                {
                   btApagarActionPerformed(null);
                   tpPrincipal.setSelectedIndex(0);
                }
            }else if(tpPrincipal.getSelectedIndex()==1){
                    tbFormasPagto.setSelectedIndex(0);
                    dbgFormasPagto.getjTable().requestFocus();
                    //dbgFormasPagto.getjTable().setRowSelectionInterval(0, 0);
            }else if(tpPrincipal.getSelectedIndex()==0 && tpMenu.getSelectedIndex()==2){
                    btLeituraXActionPerformed(null);

            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }
    private void Evento_F3(){
        try {
            if(tpPrincipal.getSelectedIndex()==1) {
                tbFormasPagto.setSelectedIndex(1);
                dbgParcelas.getjTable().requestFocus();
            }else if(tpPrincipal.getSelectedIndex()==0 && tpMenu.getSelectedIndex()==2){
                btReducaoZActionPerformed(null);
            }else if(tpPrincipal.getSelectedIndex()==0 && tpMenu.getSelectedIndex()==0){    
                btExtornarVendaActionPerformed(null);
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }
    
    private void Evento_F1(){
        try {
            if(tpPrincipal.getSelectedIndex()==0 && tpMenu.getSelectedIndex()==2) {
                btCancelarCupomActionPerformed(null);            
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }
    private void Evento_F4(){
        try {
            if(tpMenu.getSelectedIndex()==0 && tpPrincipal.getSelectedIndex()==0) // VENDAS.EXCLUIRITEM
            {
                btExcluirItemActionPerformed(null);
           }else if(tpMenu.getSelectedIndex()==1 && tpPrincipal.getSelectedIndex()==0) {
                btSincronizarCadastrosActionPerformed(null);
            }else if(tpMenu.getSelectedIndex()==2 && tpPrincipal.getSelectedIndex()==0) {
                    btResumoActionPerformed(null);
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }
    private void Evento_F5(){
        try {
           if(tpPrincipal.getSelectedIndex()==0){
              tpMenu.setSelectedIndex(0);
           }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }
    private void Evento_F6(){
        try {
            if(tpPrincipal.getSelectedIndex()==0){
             tpMenu.setSelectedIndex(1);
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }
    private void Evento_F7(){
        try {
              if(tpPrincipal.getSelectedIndex()==0){
                     tpMenu.setSelectedIndex(2);
                }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }
    private void Evento_F8(){
        try {
            if(tpPrincipal.getSelectedIndex()==0){
             tpMenu.setSelectedIndex(3);
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }
    private void Evento_ESC(){
        try {
             if(tpPrincipal.getSelectedIndex()==0)
               btSairActionPerformed(null);
            else
            {
                tpPrincipal.setSelectedIndex(0);
                txtProcurar.requestFocus();
                txtProcurar.requestFocus();
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }

    private boolean DefinirControleTeclasFuncoesCheckout()
    {
        boolean bret=false;
        try {

            int[] Teclas = new int[]{KeyEvent.VK_F1,KeyEvent.VK_F2,KeyEvent.VK_F3,KeyEvent.VK_F4,KeyEvent.VK_F5,
                                       KeyEvent.VK_F6,KeyEvent.VK_F7,KeyEvent.VK_F8,KeyEvent.VK_F9,KeyEvent.VK_F10,
                                       KeyEvent.VK_F11,KeyEvent.VK_F12,KeyEvent.VK_ESCAPE };


            setJMenuBar(controleteclas.ControleDeTeclasDeFuncao(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        
                            if(e.getActionCommand().toString().equalsIgnoreCase(KeyEvent.getKeyText(KeyEvent.VK_F2))){
                                Evento_F2();
                            }
                            else if(e.getActionCommand().toString().equalsIgnoreCase(KeyEvent.getKeyText(KeyEvent.VK_F1))){
                                  Evento_F1();
                            }
                            else if(e.getActionCommand().toString().equalsIgnoreCase(KeyEvent.getKeyText(KeyEvent.VK_F3))){
                                  Evento_F3();
                            }
                            else if(e.getActionCommand().toString().equalsIgnoreCase(KeyEvent.getKeyText(KeyEvent.VK_F4))){
                                  Evento_F4();
                            }
                            else if(e.getActionCommand().toString().equalsIgnoreCase(KeyEvent.getKeyText(KeyEvent.VK_F5))){
                                  Evento_F5();
                            }
                            else if(e.getActionCommand().toString().equalsIgnoreCase(KeyEvent.getKeyText(KeyEvent.VK_F6))){
                                  Evento_F6();
                            }
                            else if(e.getActionCommand().toString().equalsIgnoreCase(KeyEvent.getKeyText(KeyEvent.VK_F7))){
                                  Evento_F7();
                            }
                            else if(e.getActionCommand().toString().equalsIgnoreCase(KeyEvent.getKeyText(KeyEvent.VK_F8))){
                                  Evento_F8();
                            }
                            else if(e.getActionCommand().toString().equalsIgnoreCase(KeyEvent.getKeyText(KeyEvent.VK_ESCAPE))){                                
                                  Evento_ESC();
                            }
                    }
                }
            ,Teclas));
           bret=true;
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return bret;
    }
    

}
