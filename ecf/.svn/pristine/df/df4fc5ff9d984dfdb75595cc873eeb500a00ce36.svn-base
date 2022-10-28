/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.epson;

import br.com.ecf.CarregarDLL;
import com.sun.jna.win32.StdCallLibrary;



/**
 *
 * @author Fernando
 */
public interface InterfaceEpson_2   extends StdCallLibrary {
    //public static native int AlteraSimboloMoeda(String simboloMoeda);
    
    
    public InterfaceEpson_2 INSTANCE = (InterfaceEpson_2) CarregarDLL.Carregar("InterfaceEpson.dll", InterfaceEpson_2.class);
    
    
    public int  EPSON_Obter_Dados_Ultima_RZ(byte[] pszUltimaRZ);
    public int  EPSON_Obter_Hora_Relogio(byte[] pszDataHoraRelogio);
    public int  EPSON_Obter_Tabela_Pagamentos(byte[] pszTabelaPagamentos);
    public int  EPSON_RelatorioFiscal_Abrir_Jornada();
    public int  EPSON_Serial_Fechar_Porta();
    public int  EPSON_Serial_Abrir_Porta(Integer dwVelocidade, Integer wPorta);
    
    public  int EPSON_Fiscal_Abrir_Cupom( String pszCPFCNPJ, String pszRazaoSocialNomeDoCliente ,
                                                        String pszEnderecoLinha1 , String pszEnderecoLinha2 ,Integer dwPosicaoDadosImpressão);
    
    public  int EPSON_Fiscal_Vender_Item( String pszCodigo, String pszDescricao , String pszQuantidade,
int dwCasasDecimaisQuantidade, String pszUnidadeDeMedida, String pszPrecoUnidade, int dwCasasDecimaisPreco,String pszAliquotas, int dwLinhasImpressao);
    
    public  int EPSON_RelatorioFiscal_LeituraX();
   
    public  int EPSON_RelatorioFiscal_RZ( String pszData, String pszHora, Integer dwHorarioVerao, String pszCRZ );
    
    public  int EPSON_Fiscal_Cancelar_Item(String pszNumeroItem);
    //public  int EPSON_Fiscal_Cancelar_Item(byte[] pszNumeroItem);
    
    //public  int EPSON_Obter_Estado_Cupom(String pszEstadoCupom);
    
    public  int EPSON_Fiscal_Pagamento(String pszNumeroDescricaoPagamento, String pszValorPagamento,
Integer dwCasasDecimaisPagamento, String pszLinhaDescricao1, String pszLinhaDescricao2);
    
    public  int  EPSON_Fiscal_Desconto_Acrescimo_Subtotal( String pszValorDescontoAcrescimo,
Integer dwNumeroCasasDecimais, Boolean bTipoDescontoAcrescimo, Boolean bTipoPercentagemMoeda);
    
    public  int  EPSON_Fiscal_Fechar_Cupom(Boolean bCortarPapel, Boolean bImprimirCupomAdicional);
    
    public  int   EPSON_Fiscal_Imprimir_MensagemEX(String pszLinhasTexto);
    
    public  int   EPSON_Fiscal_Imprimir_Mensagem( String pszLinhaTexto1, String pszLinhaTexto2,
String pszLinhaTexto3, String pszLinhaTexto4,String pszLinhaTexto5,String pszLinhaTexto6,
String pszLinhaTexto7, String pszLinhaTexto8);
    
    public int EPSON_Fiscal_Desconto_Acrescimo_Item( String pszValorDescontoAcrescimo, Integer dwNumeroCasasDecimais,
                Boolean bTipoDescontoAcrescimo,Boolean bTipoPercentagemMoeda);
    
    public int EPSON_Fiscal_Desconto_Acrescimo_ItemEX(String pszNumeroItemCupom, String pszValorDescontoAcrescimo, Integer dwNumeroCasasDecimais,
                Boolean bTipoDescontoAcrescimo,Boolean bTipoPercentagemMoeda);

    public  int EPSON_Fiscal_Cancelar_Cupom();
    
    public  int EPSON_Impressora_Abrir_Gaveta();
     
     public  int EPSON_Obter_Estado_ImpressoraEX( byte[] szEstadoImpressora, byte[] szEstadoFiscal,
byte[] szRetornoComando,  byte[] szMsgErro);
    
     public int EPSON_Obter_Dados_Jornada(byte[] pszDadosJornada);
     
     public  int  EPSON_Obter_Estado_Impressora(byte[] pszEstadoImpressora);
     
    public int EPSON_Obter_Estado_Cupom(byte[] pszEstadoCupom) ;
    //relatorio gerencial
    public int EPSON_NaoFiscal_Abrir_Relatorio_Gerencial(String pszNumeroRelatorio);
    public int EPSON_NaoFiscal_Imprimir_LinhaEX(String pszLinhaTexto  );
    public int EPSON_NaoFiscal_Fechar_Relatorio_Gerencial(boolean bStatusCortarPapel);
    // comprovante nao fiscal vinculado
    public int EPSON_NaoFiscal_Abrir_CCD(String pszNumeroDescricaoPagamento ,String pszValorPagamento,
    int dwCasasDecimaisPagamento , String pszParcelas);
    public int EPSON_NaoFiscal_Fechar_CCD(boolean bStatusCortarPapel);
    public int EPSON_Impressora_Cortar_Papel();
    public int EPSON_Config_Forma_Pagamento(boolean bTipoVinculado, String szNumeroMeio ,String pszDescricao);
    public int EPSON_Config_Aliquota(String pszValorAliquota, boolean bTipoAliquota);
    public int EPSON_Obter_Tabela_Aliquotas( byte[] pszTabelaAliquotas);
    public int EPSON_Obter_Dados_Impressora(byte[] pszDadosImpressora);
    public int EPSON_Obter_Dados_Usuario(byte[] pszDadosUsuario);
}
