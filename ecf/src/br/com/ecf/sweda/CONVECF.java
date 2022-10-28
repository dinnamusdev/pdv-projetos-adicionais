/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ecf.sweda;

import br.com.ecf.CarregarDLL;
import br.com.ecf.ECFSuportados;
import com.sun.jna.win32.StdCallLibrary;

/**
 *
 * @author SWEDA
 */
public interface CONVECF extends StdCallLibrary {
       
       CONVECF INSTANCE = (CONVECF) CarregarDLL.Carregar(ECFSuportados.PastaDLL_ECF(ECFSuportados.getNomeECFSweda())+  "\\CONVECF", CONVECF.class);
// ------------------------------------------- INICIALIZACAO DA BIBLIOTECA
       public  int   ECF_AbrePortaSerial();
       public  int   ECF_FechaPortaSerial();
       public  int   ECF_RetornaPortaVelocidade(byte[] porta, byte[] veloc);
       public  int   ECF_LigaDesligaJanelas(String papel, String eventos);
       public  int   ECF_ZAUTO(String auto);
       public  int   ECF_CfgRedZAutomatico(String auto);
       public  int   ECF_Registry();
       public  int   ECF_Registry_AlteraRegistry(String chave, String valor);
       public  int   ECF_Registry_Path(String caminho);
       public  int   ECF_Registry_PathMFD(String caminho);
       public  int   ECF_Registry_Zautomatica(String auto);
       public  int   ECF_Registry_Verao(String verao);
       public  int   ECF_Registry_Log(String log);
       public  int   ECF_Registry_AplMensagem1(String mens);
       public  int   ECF_Registry_AplMensagem2(String mens);
       public  int   ECF_Registry_Default();
       public  int   ECF_Registry_RetornaValor(String ecf, String chave, byte [] valor);
       public  int   ECF_RetornaPath(byte [] caminho);
       public  int   ECF_RetornaPathMFD(byte [] caminho);


// --------------------------------------------------- PROGRAMACAO
       public  int   ECF_EnviarLogotipoCliche(String bitmap);
       public  int   ECF_GravarLogotipoCliche();
       public  int   ECF_ExcluirLogotipoCliche();
       public  int   ECF_ApagaTabelaAliquotas();
       public  int   ECF_ApagaTabelaNomesNaoFiscais();
       public  int   ECF_ApagaTabelaNomesRelatoriosGerenciais();
       public  int   ECF_ApagaTabelaNomesFormasdePagamento();
       public  int   ECF_ProgramarParametrosDiversos(String ecf, String loja, String extrap, String extraq, String iss);
       public  int   ECF_ProgramarCliche(String razao, String fantasia, String endereco);
       public  int   ECF_ProgramarTotalizadoresNaoTributados(String nf, String ni, String nn, String nfs, String nis, String nns);
       public  int   ECF_ProgramaAliquota(String aliquota, int tipo);
       public  int   ECF_ProgramarRelogio(String data, String hora, String verao);
       public  int   ECF_ProgramaHorarioVerao();
       public  int   ECF_NomeiaTotalizadorNaoSujeitoIcms(int indice, String cTot);
       public  int   ECF_ProgramaFormaPagamentoMFD(String cFormaPgto, String cTef);
       public  int   ECF_ProgramaFormasPagamento(String meios);
       public  int   ECF_NomeiaRelatorioGerencialMFD(String indice, String legenda);
       public  int   ECF_ProgramaOperador(String operador);
       public  int   ECF_CfgFechaAutomaticoCupom(String fecha);
       public  int   ECF_CfgHorarioVerao(String verao);
       public  int   ECF_CfgCupomAdicional(String adicional);
       public  int   ECF_ImprimeConfiguracoesImpressora();
       public  int   ECF_ConfigurarECF(String corte, String veloc, String modo, String beep);


// ------------------------------------------------ ABERTURA DO DIA
       public  int   ECF_AberturaDoDia(String valor, String meio);


// ------------------------------------------------- CUPOM FISCAL
       public  int   ECF_AbreCupom(String cpf);
       public  int   ECF_AbreCupomMFD(String cpf, String nome, String endereco);
       public  int   ECF_IdentificaConsumidor(String nome, String endereco, String cpf);
       public  int   ECF_AumentaDescricaoItem(String nome);
       public  int   ECF_UsaUnidadeMedida(String kilos);
       public  int   ECF_VendeItem(String cod, String nome, String aliq, String tipoqt, String quant, int casas, String unit, String tpdesc, String valdesc);
       public  int   ECF_VendeItemTresDecimais(String cod,String nome,String aliq,String quant,String unit,String tpdesc,String taxadesc);
       public  int   ECF_VendeItemDepartamento(String cod,String nome,String aliq,String unit,String quant,String acresc,String desc,String indice,String umedida);
       public  int   ECF_DescontoSobreItemVendido(String item, String tipo, String val);
       public  int   ECF_AcrescimoDescontoItemMFD(String item,String selecao, String tipo, String valor);
       public  int   ECF_CancelaItemAnterior();
       public  int   ECF_CancelaItemGenerico(String item);
       public  int   ECF_CancelaAcrescimoDescontoItemMFD(String tipo, String item);
       public  int   ECF_IniciaFechamentoCupom(String selecao,String tipo,String valor);
       public  int   ECF_IniciaFechamentoCupomMFD(String selecao,String tipo,String valAcres,String valDesc);
       public  int   ECF_SubTotalizaCupomMFD();
       public  int   ECF_AcrescimoDescontoSubtotalMFD(String flag, String tipo, String valor);
       public  int   ECF_CancelaAcrescimoDescontoSubtotalMFD(String tipo);
       public  int   ECF_TotalizaCupomMFD();
       public  int   ECF_EfetuaFormaPagamento(String meio, String valor);
       public  int   ECF_EfetuaFormaPagamentoDescricaoForma(String meio, String valor, String descricao);
       public  int   ECF_EfetuaFormaPagamentoMFD(String meio, String valor, String parcelas, String descricao);
       public  int   ECF_TerminaFechamentoCupom(String mensagem);
       public  int   ECF_FechaCupom(String meio,String selecao, String tipo, String val,String pago,String mensagem);
       public  int   ECF_FechaCupomResumido(String meio, String mensagem);
       public  int   ECF_AtivaDesativaCorteProximoMFD(int flag);
       public  int   ECF_CupomAdicionalMFD();
       public  int   ECF_ProgramaArredondamento();
       public  int   ECF_ProgramaTruncamento();


// ---------------------------------- CANCELAMENTO DE CUPOM E COMPROVANTE
       public  int   ECF_CancelaCupom();
       public  int   ECF_CancelaCupomMFD(String cpf, String nome, String endereco);
       public  int   ECF_CancelaRecebimentoNaoFiscalMFD(String cpf, String nome, String endereco);


// ------------------------------------------------- COMPROVANTE
       public  int   ECF_EstornoFormasPagamento(String origem, String destino, String valor);


// ---------------------------------------------- COMPROVANTE CDC - TEF
       public  int   ECF_IniciaModoTEF();
       public  int   ECF_AbreComprovanteNaoFiscalVinculado(String meio, String valor, String ignorado);
       public  int   ECF_AbreComprovanteNaoFiscalVinculadoMFD(String meio, String valor,String ignorado, String cpf, String nome, String endereco);
       public  int   ECF_UsaComprovanteNaoFiscalVinculado(String texto);
       public  int   ECF_UsaComprovanteNaoFiscalVinculadoTEF(String texto);
       public  int   ECF_FechaComprovanteNaoFiscalVinculado();
       public  int   ECF_FinalizaModoTEF();
       public  int   ECF_TEF_TravarTeclado();
       public  int   ECF_SegundaViaNaoFiscalVinculadoMFD();
       public  int   ECF_SegundaViaCNFV();
       public  int   ECF_ReimpressaoNaoFiscalVinculadoMFD();
       public  int   ECF_EstornoNaoFiscalVinculadoMFD(String cpf, String nome, String endereco);
       public  int   ECF_CancelamentoCNFV(String ignorado);
       public  int   ECF_TEF_ImprimirRespostaCartao(String arq, String meio, String travar, String valor);
       public  int   ECF_TEF_ImprimirResposta(String arq, String meio, String travar);
       public  int   ECF_TEF_FechaRelatorio();
       public  int   ECF_TEF_EsperarArquivo(String arq, String tempo, String travar);
       public  int   ECF_RelatorioGerencialTEF(String texto);
       public  int   ECF_UsaRelatorioGerencialMFDTEF(String texto);


// ------------------------------------------------- RECEBIMENTOS
       public  int   ECF_Suprimento(String valor, String meio);
       public  int   ECF_Sangria(String valor);
       public  int   ECF_FundoCaixa(String valor, String meio);
       public  int   ECF_AbreRecebimentoNaoFiscalMFD(String cpf, String nome, String endereco);
       public  int   ECF_AbreRecebimentoNaoFiscal(String indice, String selecao, String tipo, String val, String valreceb, String texto);
       public  int   ECF_RecebimentoNaoFiscal(String cIndiceTot,String cValor ,String cFormaPgto);
       public  int   ECF_EfetuaRecebimentoNaoFiscalMFD(String indice, String valor);
       public  int   ECF_CancelaItemNaoFiscalMFD(String item);
       public  int   ECF_AcrescimoItemNaoFiscalMFD(String item, String selecao, String tipo, String valor);
       public  int   ECF_AcrescimoDescontoSubtotalRecebimentoMFD(String flag, String tipo, String valor);
       public  int   ECF_TotalizaRecebimentoMFD();
       public  int   ECF_IniciaFechamentoRecebimentoNaoFiscalMFD(String selecao, String tipo, String valAcres, String valDesc);
       public  int   ECF_FechaRecebimentoNaoFiscalMFD(String mensagem);
       public  int   ECF_EfetuaFormaPagamentoNaoFiscal(String meio, String val,String nome);


// ------------------------------------------------- RELATORIO GERENCIAL
       public  int   ECF_AbreRelatorioGerencial();
       public  int   ECF_AbreRelatorioGerencialMFD(String texto);
       public  int   ECF_EnviarTextoCNF(String texto);
       public  int   ECF_RelatorioGerencial(String texto);
       public  int   ECF_UsaRelatorioGerencialMFD(String texto);
       public  int   ECF_FechaRelatorioGerencial();
       public  int   ECF_FechaRelatorioXouZ();


// ------------------------------------------------- LEITURAS FISCAIS
       public  int   ECF_LeituraX();
       public  int   ECF_LeituraMemoriaFiscalData(String datai,String dataf);
       public  int   ECF_LeituraMemoriaFiscalDataMFD(String datai, String dataf, String simplif);
       public  int   ECF_LeituraMemoriaFiscalReducao(String crzi,String crzf);
       public  int   ECF_LeituraMemoriaFiscalReducaoMFD(String crzi, String crzf, String simplif);




// ------------------------------------------------- ENCERRAMENTO DO DIA
       public  int   ECF_FechamentoDoDia();
       public  int   ECF_ReducaoZ(String cDatai, String cHorai);
       public  int   ECF_ReducaoZAjustaDataHora(String data, String hora);


// --------------------------------------------------- VALIDACAO
       public  int   ECF_Autenticacao();
       public  int   ECF_AutenticacaoMFD(String linhas, String texto);
       public  int   ECF_AutenticacaoStr(String texto);
       public  int   ECF_VerificaDocAutenticacao();
       public  int   ECF_ProgramaCaracterAutenticacao(String logo);


// --------------------------------------------------- GAVETA
       public  int   ECF_AcionaGaveta();
       public  int   ECF_VerificaEstadoGaveta(int [] status);
       public  int   ECF_VerificaEstadoGavetaStr(byte[] estado);


// --------------------------------------------------- CHEQUE
       public  int   ECF_ProgramaMoedaSingular(String moeda);
       public  int   ECF_ProgramaMoedaPlural(String moeda);
       public  int   ECF_ProgramarLeiauteCheque(String banco, String leiaute);
       public  int   ECF_IncluiCidadeFavorecido(String cidade, String favorecido);
       public  int   ECF_VerificaStatusCheque(int [] status);
       public  int   ECF_VerificaStatusChequeStr(byte[] status);
       public  int   ECF_ImprimeCheque(String banco,String valor,String nominal,String cidade ,String data,String mensagem);
       public  int   ECF_ImprimeChequeMFD(String banco,String valor,String nominal,String cidade,String data,String mensagem,String verso,String linhas);
       public  int   ECF_ImprimeCopiaCheque();
       public  int   ECF_CancelaImpressaoCheque();
       public  int   ECF_LeituraChequeMFD(byte[] sCMC7);
       public  int   ECF_ImprimirCheque(String banco,String cidade,String data,String nome,String valor,String pos);
       public  int   ECF_LiberarCheque();
       public  int   ECF_LeituraCodigoMICR(byte[] cmc7);
       public  int   ECF_CancelarCheque();
       public  int   ECF_LeituraLeiautesCheques();


// -------------------------------------------------- STATUS DE PROGRAMACAO
       public  int   ECF_RetornaTipoEcf(byte[] tipo);
       public  int   ECF_VersaoDll(byte[] versao);
       public  int   ECF_VersaoFirmware(byte[] versao);
       public  int   ECF_NumeroSerie(byte[] serie);
       public  int   ECF_NumeroSerieMFD(byte[] serie);
       public  int   ECF_NumeroSerieMemoriaMFD(byte[] serie);
       public  int   ECF_MarcaModeloTipoImpressoraMFD(byte[] marca, byte[] modelo, byte[] tipo);
       public  int   ECF_CGC_IE(byte[] cgc, byte[] ie);
       public  int   ECF_CNPJMFD(byte[] cnpj);
       public  int   ECF_InscricaoEstadualMFD(byte[] ie);
       public  int   ECF_InscricaoMunicipalMFD(byte[] im);
       public  int   ECF_NumeroSubstituicoesProprietario(byte[] subst);
       public  int   ECF_SimboloMoeda(byte[] moeda);
       public  int   ECF_ClicheProprietario(byte[] cliche);
       public  int   ECF_NumeroLoja(byte[] loja);
       public  int   ECF_NumeroCaixa(byte[] caixa);
       public  int   ECF_DataHoraImpressora(byte[] data, byte[] hora);
       public  int   ECF_RetornoAliquotas(byte[] aliq);
       public  int   ECF_VerificaAliquotasIss(byte[] aliq);
       public  int   ECF_VerificaIndiceAliquotasIss(byte[] indices);
       public  int   ECF_VerificaTotalizadoresNaoFiscais(byte[] totalnf);
       public  int   ECF_VerificaTotalizadoresNaoFiscaisMFD(byte[] totalnf);
       public  int   ECF_VerificaModoOperacao(byte[] modo);
       public  int   ECF_RetornaIndiceComprovanteNaoFiscal(String nome, byte[] RetIndice);
       public  int   ECF_LerAliquotasComIndice(byte[] aliq);
       public  int   ECF_ClicheProprietarioEx(byte[] cliche);
       public  int   ECF_VerificaDescricaoFormasPagamento(byte[] nomes);
       public  int   ECF_VerificaTotalizadoresNaoFiscaisEx(byte[] status);
       public  int   ECF_VerificaRelatorioGerencialProgMFD(byte[] status);
       public  int   ECF_CasasDecimaisProgramada(byte[] casas1, byte[] casas2);
       public  int   ECF_VerificaTruncamento(byte[] status);


// ------------------------ RETORNO DE VALORES DO ULTIMO CUPOM / CUPOM CORRENTE
       public  int   ECF_TipoUltimoDocumento(byte[] tipo);
       public  int   ECF_UltimoItemVendido(byte[] ultimo);
       public  int   ECF_SubTotal(byte[] valor);
       public  int   ECF_SubTotalComprovanteNaoFiscalMFD(byte[] valor);
       public  int   ECF_ValorPagoUltimoCupom(byte[] valor);
       public  int   ECF_NumeroCupom(byte[] cupom);
       public  int   ECF_VerificaFormasPagamento(byte[] meios);
       public  int   ECF_VerificaFormasPagamentoMFD(byte[] meios);
       public  int   ECF_UltimaFormaPagamento(byte[] meio, byte[] valor);
       public  int   ECF_VerificaFormasPagamentoEx(byte[] meios);
       public  int   ECF_TotaisVenda(byte[] valor);
       public  int   ECF_ValorAcrescimosUltimoCupom(byte[] val);
       public  int   ECF_ValorDescontosUltimoCupom(byte[] val);
       public  int   ECF_ValorCancelamentosUltimoCupom(byte[] val);
       public  int   ECF_ValorCancelamentosUltimoCupomISS(byte[] val);
       public  int   ECF_ValorAliquotaUltimoCupom(byte[] val);


       // -------------------------------------------- RETORNO DE VALORES DO DIA
       public  int   ECF_Acrescimos(byte[] valor);
       public  int   ECF_Descontos(byte[] valor);
       public  int   ECF_ValorFormaPagamento(String meio, byte[] valor);
       public  int   ECF_ValorFormaPagamentoMFD(String meio, byte[] valor);
       public  int   ECF_ValorTotalizadorNaoFiscal(String nome, byte[] valor);
       public  int   ECF_ValorTotalizadorNaoFiscalMFD(String nome, byte[] valor);
       public  int   ECF_VerificaRecebimentoNaoFiscal(byte[] receb);
       public  int   ECF_VerificaRecebimentoNaoFiscalMFD(byte[] receb);
       public  int   ECF_Cancelamentos(byte[] valor);
       public  int   ECF_GrandeTotal(byte[] valor);
       public  int   ECF_VerificaTotalizadoresParciais(byte[] valores);
       public  int   ECF_VerificaTotalizadoresParciaisMFD(byte[] valores);
       public  int   ECF_VendaBruta(byte[] valor);
       public  int   ECF_RetornaAcrescimoNF(byte[] valor);
       public  int   ECF_RetornaDescontoNF(byte[] valor);
       public  int   ECF_RetornaCancelamentoNF(byte[] valor);
       public  int   ECF_RetornaTotalPagamentos(byte[] valor);
       public  int   ECF_RetornaCNFNV(byte[] valor);
       public  int   ECF_RetornaTroco(byte[] valor);
       public  int   ECF_RetornaRegistradoresNaoFiscais(byte[] buf);
       public  int   ECF_RetornaRegistradoresFiscais(byte[] buf);
       public  int   ECF_RetornaValorComprovanteNaoFiscal(byte[] indice, byte[] valor);
       public  int   ECF_AcrescimosICMSISS(byte[] valorICMS, byte[] valorISS);
       public  int   ECF_CancelamentosICMSISS(byte[] valorICMS, byte[] valorISS);
       public  int   ECF_DescontosICMSISS(byte[] valorICMS, byte[] valorISS);


// ---------------------------------------- RETORNO DOS CONTADORES DIARIOS


       public  int   ECF_NumeroIntervencoes(byte[] contador);
       public  int   ECF_RetornaCOO(byte[] contador);
       public  int   ECF_ContadorCupomFiscalMFD(byte[] contador);
       public  int   ECF_NumeroCuponsCancelados(byte[] contador);
       public  int   ECF_NumeroOperacoesNaoFiscais(byte[] contador);
       public  int   ECF_ContadorComprovantesCreditoMFD(byte[] contador);
       public  int   ECF_ComprovantesNaoFiscaisNaoEmitidosMFD(byte[] contador);
       public  int   ECF_ContadorOperacoesNaoFiscaisCanceladasMFD(byte[] contador);
       public  int   ECF_RetornaCNFCancelados(byte[] contador);
       public  int   ECF_ContadorFitaDetalheMFD(byte[] contador);
       public  int   ECF_ContadorRelatoriosGerenciaisMFD(byte[] contador);
       public  int   ECF_NumeroReducoes(byte[] contador);
       public  int   ECF_RetornaCRZ(byte[] contador);
       public  int   ECF_COO(byte[] inicio, byte[] fim);
       public  int   ECF_ContadoresTotalizadoresNaoFiscais(byte[] contador);
       public  int   ECF_ContadoresTotalizadoresNaoFiscaisMFD(byte[] contador);
       public  int   ECF_VerificaRelatorioGerencialMFD(byte[] contador);

// -------------------------------------------- STATUS
       public  int   ECF_VerificaImpressoraLigada();
       public  int   ECF_FlagsFiscais(int [] status);
       public  int   ECF_FlagsFiscaisStr(byte[] status);
       public  int   ECF_VerificaEstadoImpressora(int [] ack,int [] st1, int [] st2);
       public  int   ECF_VerificaEstadoImpressoraMFD(int [] ack, int [] st1, int [] st2, int [] st3);


       public  int   ECF_VerificaEstadoImpressoraStr(byte[] ack, byte[] st1, byte[] st2);
       public  int   ECF_RetornoImpressora(int [] ack, int [] st1, int [] st2);
       public  int   ECF_RetornoImpressoraMFD(int [] ack, int [] st1, int [] st2, int [] st3);
       public  int   ECF_RetornoImpressoraStr(byte[] ack, byte[] st1, byte[] st2);
       public  int   ECF_VerificaTipoImpressora(int [] tipo);
       public  int   ECF_VerificaTipoImpressoraStr(byte[] tipo);
       public  int   ECF_StatusEstendidoMFD(int [] status);
       public  int   ECF_StatusCupomFiscal(byte[] status);
       public  int   ECF_StatusRelatorioGerencial(byte[] status);
       public  int   ECF_StatusComprovanteNaoFiscalVinculado(byte[] status);
       public  int   ECF_StatusComprovanteNaoFiscalNaoVinculado(byte[] status);
       public  int   ECF_VerificaModeloEcf();
       public  int   ECF_VerificaHorarioVerao(byte[] status);
       public  int   ECF_VerificaZPendente(byte[] status);
       public  int   ECF_VerificaDiaAberto(byte[] status);
       public  int   ECF_PalavraStatus(byte[] status);
       public  int   ECF_PalavraStatusBinario(byte[] status);
       public  int   ECF_VerificaFlagCorteMFD(int [] status);
       public  int   ECF_GeraRFD(byte[] status);
       public  int   ECF_MsgLog(byte[] area);


       // ---------------------------------------------------- DIVERSOS
       public  int   ECF_PercentualLivreMFD(byte[] livre);
       public  int   ECF_TotalLivreMFD(byte[] livre);
       public  int   ECF_ReducoesRestantesMFD(byte[] resto);
       public  int   ECF_RetornaCRZRestante(byte[] resto);
       public  int   ECF_TamanhoTotalMFD(byte[] total);
       public  int   ECF_TempoRestanteComprovanteMFD(byte[] resto);
       public  int   ECF_DataMovimento(byte[] data);


// --------------------------------------- RETORNO DE VALORES APOS A REDUCAO Z
       public  int   ECF_DataHoraUltimoDocumentoMFD(byte[] datahora);
       public  int   ECF_DataHoraReducao(byte[] data, byte[] hora);
       public  int   ECF_DataMovimentoUltimaReducaoMFD(byte[] datahora);
       public  int   ECF_InicioFimCOOsMFD(byte[] cooi,byte[] coof);
       public  int   ECF_InicioFimGTsMFD(byte[] inicio,byte[] fim);
       public  int   ECF_GrandeTotalUltimaReducaoMFD(byte[] gt);
       public  int   ECF_DadosUltimaReducao(byte[] zz);
       public  int   ECF_DadosUltimaReducaoMFD(byte[] zz);
       public  int   ECF_MapaResumo();
       public  int   ECF_MapaResumoMFD();


// --------------------------------------------------------- SINTEGRA
       public  int   ECF_RegistrosTipo60();
       public  int   ECF_RelatorioTipo60Mestre();
       public  int   ECF_RelatorioTipo60Analitico();
       public  int   ECF_RelatorioTipo60AnaliticoMFD();
       public  int   ECF_RelatorioSintegraMFD(int tipo, String arq,String mesi,String anoi,String razao,String endereco, String numero,String compl,String bairro,String cidade,String cep, String telef,String fax, String contato);
       public  int   ECF_ArquivoSintegra2004MFD(int tipo, String arq,String mesi,String anoi,String mesf,String anof, String razao,String endereco,String numero,String compl,String bairro, String cidade, String uf,String cep, String telef, String fax, String contato);


// ----------------------------------------------------------- CAPTURA
       public   int   ECF_LeituraXSerial();
       public   int   ECF_LeituraMemoriaFiscalSerialData(String datai, String dataf);
       public   int   ECF_LeituraMemoriaFiscalSerialDataMFD(String datai, String dataf, String simplif);
       public   int   ECF_LeituraMemoriaFiscalSerialReducao(String crzi, String crzf);
       public   int   ECF_LeituraMemoriaFiscalSerialReducaoMFD(String crzi, String crzf, String simplif);
       public   int   ECF_DownloadSB(String arq);
       public   int   ECF_DownloadMF(String arq);
       public   int   ECF_DownloadMFD(String arq,String tipo,String inicio,String fim,String usuario);
       public   int   ECF_FormatoDadosMFD(String arq,String destino,String formato,String tipo ,String inicio,String fim,String usuario);
       public   int   ECF_CapturaDocumentos(String tipo, String inicio,String fim, String destino, String exibe);


// ------------------------------------------------------- COTEPE
       public   int   ECF_TabelaMercadoriasServicos3404(String arq,String inicio,String fim);
       public   int   ECF_ReproduzirMemoriaFiscalMFD(String tipo,String inicio,String fim,String arqtxt,String arqbin2);
       public   int   ECF_GeraRegistrosCAT52MFD(String arq, String data);


// ------------------------------------------------------- COdigo DE BARRAS
       public  int   ECF_ConfiguraCodigoBarrasMFD(int altura, int largura, int posicao, int fonte, int margem);
       public  int   ECF_CodigoBarrasCODABARMFD(String codigo);
       public  int   ECF_CodigoBarrasPDF417MFD(int nivel, int altura, int largura, int coluna, String codigo);
       public  int   ECF_CodigoBarrasCODE39MFD(String codigo);
       public  int   ECF_CodigoBarrasEAN13MFD(String codigo);
       public  int   ECF_CodigoBarrasEAN8MFD(String codigo);
       public  int   ECF_CodigoBarrasITFMFD(String codigo);
       public  int   ECF_CodigoBarrasUPCAMFD(String codigo);
       public  int   ECF_CodigoBarrasUPCEMFD(String codigo);


       /* *
        * Para adicionar um metodo da dll é só
        * colocar a assinatura do metodo aqui e
        * chamar o metodo na classe connectC
        * */


}
