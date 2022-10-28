package bemajava;

import br.com.ecf.ECFSuportados;
import br.manipulararquivos.ManipulacaoArquivo;

public  class Bematech  {
        //public native static Bematech INSTANCE = (Bematech) CarregarDLL.Carregar("BemaFI32.dll", Bematech.class);
	public native static  int AlteraSimboloMoeda(String simboloMoeda);
	public native static  int AbreCupom(String CPF_CGC);
	public native static int AumentaDescricaoItem(String descricao);
	public native static int AbreComprovanteNaoFiscalVinculado(String formaPagamento,String valor,String numeroCupom);
	public native static int Autenticacao();
	public native static int AberturaDoDia(String valorAux,String formaPagamento);
	public native static int AbrePortaSerial();
	public native static int AbrePorta(int numero);
	public native static int AbreBilhetePassagem(String imprimeValorFim,String imprimeEnfatizado,String embarque,String destino,String linha,String prefixo,String agente,String agencia,String data,String hora,String poltrona,String plataforma);
	public native static int Acrescimos(BemaString valorAcrescimo);
	public native static int CancelaItemAnterior();
	public native static int CancelaItemGenerico(String numeroItem);
	public native static int CancelaCupom();
	public native static int EfetuaFormaPagamento(String formaPagamento, String valorFormaPagamento);
	public native static   int EfetuaFormaPagamentoDescricaoForma(String formaPagamento, String valorFormaPagamento, String descricaoForma);
	public native static   int EfetuaFormaPagamentoImpAntiga(String formaPagamento, String valorFormaPagamento);
	public native static  int EfetuaFormaPagamentoIndice(String indice,String valorFormaPagamento);
	public native static  int EspacoEntreLinhas(int dots);
	public native static  int EstornoFormasPagamento(String formaOrigem,String formaDestino,String valor);
	public native static  int FechaCupom(String formaPagamento,String acrescimoDesconto,String tipoAcrescimoDesconto,String valorAcrescimoDesconto,String valorPago,String mensagem);
	public native static  int FechaCupomResumido(String formaPagamento,String mensagem);
	public native static  int ForcaImpactoAgulhas(int iValorImpacto);
	public native static  int IniciaFechamentoCupom(String acrescimoDesconto,String tipoAcrescimoDesconto,String valorAcrescimoDesconto);
	public native static  int LinhasEntreCupons(int linhas);
	public native static  int NomeiaDepartamento(int indice, String departamento);
	public native static  int NomeiaTotalizadorNaoSujeitoIcms(int indice, String totalizador);
	public native static  int ProgramaAliquota(String aliquota, int vinculo);
	public native static  int ProgramaArredondamento();
	public native static  int ProgramaTruncamento();
	public native static  int ProgramaHorarioVerao();

	public native static  int VendeItem(String codigo, String descricao, String aliquota,String tipoQuantidade, String cQuantidade, int iCasasDecimais, String cUnitario, String tipoDesconto,String desconto);
	public native static  int VendeItemDepartamento(String codigo,String descricao,String aliquota,String valorUnitario,String cQuantidade,String valorAcrescimo,String valorDesconto,String indiceDepartamento,String cUnidadeMedida);


	public native static  int TerminaFechamentoCupom(String mensagem);
	public native static  int UsaUnidadeMedida(String cUnidadeMedida);
	public native static  int LeituraMemoriaFiscalData(String dataInicial, String dataFinal);
	public native static  int LeituraMemoriaFiscalReducao(String cReducaoInicial, String cReducaoFinal);
	public native static  int LeituraMemoriaFiscalSerialData(String dataInicial, String dataFinal);
	public native static  int LeituraMemoriaFiscalSerialReducao(String cReducaoInicial, String cReducaoFinal);
	public native static  int LeituraX();
	public native static  int LeituraXSerial();
	public native static  int ReducaoZ(String data, String cHora);

	public native static  int UsaComprovanteNaoFiscalVinculado(String texto);
	public native static  int FechaComprovanteNaoFiscalVinculado();
	public native static  int FechaRelatorioGerencial();
	public native static  int RelatorioGerencial(String texto);
	public native static  int RecebimentoNaoFiscal(String indiceTotalizador, String valorRecebimento, String formaPagamento);
	public native static  int Sangria(String valor);
	public native static  int Suprimento(String valor, String formaPagamento);

	public native static  int ProgramaCaracterAutenticacao(String caracter);
	public native static  int VerificaEstadoGaveta(BemaInteger estado);
	public native static  int CancelaImpressaCheque();
	public native static  int ImprimeCheque(String numeroBanco,String valor,String favorecido,String cidade,String data,String mensagem);
	public native static  int ImprimeCopiaCheque();
	public native static  int IncluiCidadeFavorecido(String cidade,String favorecido);
	public native static  int ProgramaMoedaPlural(String moedaPlural);
	public native static  int ProgramaMoedaSingular(String moedaSingular);
	public native static  int VerificaStatusCheque(BemaInteger status);

	public native static  int FechamentoDoDia();
	public native static  int FechaPortaSerial();
	public native static  int ImprimeConfiguracoesImpressora();
	public native static  int ImprimeDepartamentos();
	public native static  int MapaResumo();
	public native static  int RelatorioTipo60Analitico();
	public native static  int RelatorioTipo60Mestre();
	public native static  int ResetaImpressora();
	public native static  int RetornoImpressora(BemaInteger ACK, BemaInteger ST1, BemaInteger ST2);
	public native static  int VerificaImpressoraLigada();


	public native static  int ContadorBilhetePassagem(BemaString contador);
	public native static  int ImpressaoCarne(String titulo, String cParcela, String datas,int iQuantidade, String texto,	 String cliente,String cRGCPF,String cupom,int iVias,int iAssina);

	public native static  int Cancelamentos(BemaString cancelamentos);
	public native static  int CGCIE(BemaString CGC, BemaString IE);
	public native static  int ClicheProprietario(BemaString clicheProprietario);
	public native static  int ContadoresTotalizadoresNaoFiscais(BemaString Contadores);
	public native static  int DadosUltimaReducao(BemaString dadosReducao);
	public native static  int DataHoraImpressora(BemaString data, BemaString hora);
	public native static  int DataHoraReducao(BemaString data, BemaString hora);
	public native static  int DataMovimento(BemaString dataMovimento); 
	public native static  int Descontos(BemaString descontos);
	public native static  int FlagsFiscais(BemaInteger flagFiscal);
	public native static  int FlagsVinculacaoIss(BemaInteger flag1, BemaInteger flag2);
	public native static  int GrandeTotal(BemaString grandeTotal);
	public native static  int MinutosImprimindo(BemaString minutosImprimindo);
	public native static  int MinutosLigada(BemaString minutosLigada);
	public native static  int ModeloImpressora(BemaString modeloImpressora);
	public native static  int MonitoramentoPapel(BemaInteger linhasImpressas);
	public native static  int NumeroCaixa(BemaString numeroCaixa);
	public native static  int NumeroCupom(BemaString numeroCupom); 
	public native static  int NumeroCuponsCancelados(BemaString numeroCuponsCancelados);
	public native static  int NumeroIntervencoes(BemaString numeroIntervencoes);
	public native static  int NumeroLoja(BemaString numeroLoja);
	public native static  int NumeroOperacoesNaoFiscais(BemaString operacoes);
	public native static  int NumeroReducoes(BemaString numeroReducoes); 
	public native static  int NumeroSerie(BemaString numeroSerie);
	public native static  int NumeroSubstituicoesProprietario(BemaString substituicoes);
	public native static  int RetornoAliquotas(BemaString aliquotas);
	public native static  int SimboloMoeda(BemaString simboloMoeda);
	public native static  int SubTotal(BemaString subTotal);
	public native static  int UltimoItemVendido(BemaString ultimoItemVendido);
	public native static  int VendaBruta(BemaString valor);
	public native static  int ValorFormaPagamento(String forma, BemaString valorForma);
	public native static  int ValorPagoUltimoCupom(BemaString valorUltimoCupom);
	public native static  int ValorTotalizadorNaoFiscal(String totalizador, BemaString valor);
	public native static  int VerificaAliquotasISS(BemaString aliquotasISS);
	public native static  int VerificaDepartamentos(BemaString departamentos);
	public native static  int VerificaEpromConectada(BemaString flagEprom);
	public native static  int VerificaEstadoImpressora(BemaInteger ACK,BemaInteger ST1,BemaInteger ST2); 
	public native static  int VerificaFormasPagamento(BemaString formasPagamento);
	public native static  int VerificaIndiceAliquotasIss(BemaString indiceAliquotas);
	public native static  int VerificaMemoriaLivre(BemaString bytesLivres);
	public native static  int VerificaModoOperacao(BemaString modoOperacao);
	public native static  int VerificaRecebimentoNaoFiscal(BemaString recebimentos);
	public native static  int VerificaReducaoZAutomatica(BemaInteger flag);
	public native static  int VerificaTipoImpressora(BemaInteger tipo);
	public native static  int VerificaTotalizadoresNaoFiscais(BemaString totalizadores);
	public native static  int VerificaTotalizadoresParciais(BemaString totalizadores);
	public native static  int VerificaTruncamento(BemaString flagTruncamento);
	public native static  int VersaoFirmware(BemaString versaoFirmware);
	public native static  int VerificaZPendente(BemaString status);
	
	//FUNCOES RESTAURANTE
	public native static  int AbreConferenciaMesa(String mesa);
	public native static  int AbreCupomRestaurante(String mesa,String cPF);
	public native static  int CancelaVenda(String mesa,String codigo,String descricao,String aliqTributaria,String cQtde,String valor,String flagAcrescimoDesconto,String valorAcreDesc);
	public native static  int ConferenciaMesa(String mesa,String flagAcrescimoDesconto,String tipoAcrescimoDesconto,String valorAcrescimoDesconto);
	public native static  int ContaDividida(String numeroCupons,String valorPago,String cPF);
	public native static  int CardapioPelaSerial();
	public native static  int FechaConferenciaMesa(String flagAcrescimoDesconto,String tipoAcrescimoDesconto, String valorAcreDesc);
	public native static  int FechaCupomContaDividida(String numeroCupons,String acrescimoDesconto,String tipoAcrescimoDesconto,String valorAcrescimoDesconto,String formaPagamento,String valorFormaPagamento,String valorPagoConta,String cPF);
	public native static  int FechaCupomRestaurante(String formaPagamento,String acrescimoDesconto,String tipoAcrescimoDesconto,String valorAcrescimoDesconto,String valorPago,String mensagem);
	public native static  int FechaCupomResumidoRestaurante(String formaPagamento,String mensagem);
	public native static  int ImprimeCardapio();
	public native static  int RegistraVenda(String mesa,String codigo, String descricao,String aliquota, String cQuantidade,String valorUnitario, String FlagAcrescimoDesconto,String ValorAcrescimoDesconto);
	public native static  int RegistroVendaSerial(String mesa);
	public native static  int RelatorioMesasAbertas(int iTipoRelatorio);
	public native static  int RelatorioMesasAbertasSerial();
	public native static  int TransferenciaItem(String mesaOrigem,String codigo,String descricao,String aliquota,String cQuantidade,String valorUnitario,String flagAcrescimoDesconto,String valorAcrescimoDesconto,String mesaDestino);
	public native static  int TransferenciaMesa(String mesaOrigem, String mesaDestino);


	public native static  int AbreBilhetePassagemMFD(String embarque,String destino,String cLinha,String agencia,String data,String cHora,String cPoltrona,String cPlataforma,String tipoPassagem,String cRG,String cNome,String endereco,String cUF);
	public native static  int AbreComprovanteNaoFiscalVinculadoMFD(String formaPagamento,String valor,
																  String numeroCupom,
																  String cPF,
																  String cNome,
																  String endereco);
	public native static  int AbreCupomMFD(String cPF,String cNome,String endereco);
	public native static  int AbreRecebimentoNaoFiscalMFD(String cPF,String cNome,String endereco);
	public native static  int AbreRelatorioGerencialMFD(String totalizador);
	public native static  int AcionaGuilhotinaMFD(int modo);
	public native static  int AcrescimoDescontoItemMFD(String item,
													  String acrescimoDesconto,
													  String tipoAcrescimoDesconto,
													  String valorAcrescimoDesconto);
	public native static  int AcrescimoDescontoSubtotalRecebimentoMFD(String flag,
																	 String tipo,
																	 String valor);
	public native static  int AcrescimoDescontoSubtotalMFD(String flag,
														  String tipo,
														  String valor);
	public native static  int AutenticacaoMFD(String cLinhas, String texto);
	public native static  int CancelaAcrescimoDescontoItemMFD(String flag, String item);
	public native static  int CancelaAcrescimoDescontoSubtotalMFD(String flag);
	public native static  int CancelaAcrescimoDescontoSubtotalRecebimentoMFD(String flag);
	public native static  int CancelaCupomMFD(String cPF, String cNome, String endereco);
	public native static  int CancelaRecebimentoNaoFiscalMFD(String cPF, String cNome, String endereco);
	public native static  int ComprovantesNaoFiscaisNaoEmitidosMFD(BemaString comprovantes);
	public native static  int CNPJMFD(BemaString cNPJ);
	public native static  int CodigoBarrasCODABARMFD(String codigo);
	public native static  int CodigoBarrasCODE39MFD(String codigo);
	public native static  int CodigoBarrasCODE93MFD(String barcode);
	public native static  int CodigoBarrasCODE128MFD(String barcode);
	public native static  int CodigoBarrasEAN13MFD(String barcode);
	public native static  int CodigoBarrasEAN8MFD(String barcode);
	public native static  int CodigoBarrasISBNMFD(String barcode);
	public native static  int CodigoBarrasITFMFD(String barcode);
	public native static  int CodigoBarrasMSIMFD(String barcode);
	public native static  int CodigoBarrasPDF417MFD(int NCE, int altura,int largura,int numColunas,String barcode);
	public native static  int CodigoBarrasPLESSEYMFD(String barcode);
	public native static  int CodigoBarrasUPCAMFD(String barcode);
	public native static  int CodigoBarrasUPCEMFD(String barcode);
	public native static  int ConfiguraCodigoBarrasMFD(int altura, int largura,int posicaoCaracteres,int fonte,int margem);

	public native static  int ContadorComprovantesCreditoMFD(BemaString contador);
	public native static  int ContadorCupomFiscalMFD(BemaString contador);
	public native static  int ContadorFitaDetalheMFD(BemaString contador);
	public native static  int ContadorOperacoesNaoFiscaisCanceladasMFD(BemaString contador);
	public native static  int ContadorRelatoriosGerenciaisMFD(BemaString contador);
	public native static  int ContadoresTotalizadoresNaoFiscaisMFD(BemaString contador);
	public native static  int CupomAdicionalMFD();
	public native static  int DadosSintegra(String dataInicial ,String dataFinal);

	public native static  int DadosUltimaReducaoMFD(BemaString dadosUltReducao);
	public native static  int DataHoraUltimoDocumentoMFD(BemaString dataHora);
	public native static  int DownloadMF(String nomeArquivo);
	public native static  int DownloadMFD(String nomeArquivo,String tipoDownload,String dadoInicial,String dadoFinal,String usuario);
	public native static  int DownloadSB(String nomeArquivo);
	public native static  int EfetuaFormaPagamentoMFD(String formaPagamento, 
													 String valorFormaPagamento, 
													 String cParcelas, 
													 String descricaoFormaPagto);
	
	public native static  int EfetuaFormaPagamentoIndiceMFD(String indice,String valorFormaPagamento,String cParcelas, String descricaoForma);
	public native static  int EfetuaRecebimentoNaoFiscalMFD(String indiceTotalizador,
														   String valorRecebimento);
	public native static  int EstornoNaoFiscalVinculadoMFD(String cPF, String cNome, String endereco);
	public native static  int FechaRecebimentoNaoFiscalMFD(String mensagem);
	public native static  int HabilitaDesabilitaRetornoEstendidoMFD(String flagRetorno);
	public native static  int ImprimeChequeMFD(String numeroBanco, String valor,
											  String favorecido, String cidade,
											  String data,String msg,String impressaoVerso,
							  				  String cLinhas);
	public native static  int IniciaFechamentoCupomMFD(String acrescimoDesconto,
													  String tipoAcrescimoDesconto,
													  String valorAcrescimo,
													  String valorDesconto);
	public native static  int IniciaFechamentoRecebimentoNaoFiscalMFD(String acrescimoDesconto,
																	 String tipoAcrescimoDesconto,
			   														 String valorAcrescimo,
			   														 String valorDesconto);
	public native static  int InscricaoEstadualMFD(BemaString IE);
	public native static  int InscricaoMunicipalMFD(BemaString IM);
	public native static  int LeituraChequeMFD(BemaString cMC7);
	public native static  int LeituraMemoriaFiscalDataMFD(String dataInicial,String dataFinal,String flagLeitura);
	public native static  int LeituraMemoriaFiscalReducaoMFD(String cReducaoInicial,String cReducaoFinal,String flagLeitura);
	public native static  int LeituraMemoriaFiscalSerialDataMFD(String dataInicial,String dataFinal,String flagLeitura);
	public native static  int LeituraMemoriaFiscalSerialReducaoMFD(String cReducaoInicial,String cReducaoFinal,String flagLeitura);
	public native static  int MapaResumoMFD();
	public native static  int MarcaModeloTipoImpressoraMFD(BemaString marca, BemaString modelo, BemaString tipo);
	public native static  int MinutosEmitindoDocumentosFiscaisMFD(BemaString minutos);
	public native static  int NomeiaRelatorioGerencialMFD(String indice, String descricao);
	public native static  int NumeroSerieMFD(BemaString numeroSerie);
	public native static  int NumeroSerieMemoriaMFD(BemaString numeroSerie);
	public native static  int PercentualLivreMFD(BemaString valor);
	public native static  int ProgramaFormaPagamentoMFD(String formaPagamento, String cOperacaoTef);
	public native static  int ReducoesRestantesMFD(BemaString valor);
	public native static  int ReimpressaoNaoFiscalVinculadoMFD();
	public native static  int RetornoImpressoraMFD(BemaInteger ACK,BemaInteger ST1,BemaInteger ST2,BemaInteger ST3);
	public native static  int SegundaViaNaoFiscalVinculadoMFD();
	public native static  int SubTotalizaCupomMFD();
	public native static  int SubTotalizaRecebimentoMFD();
	public native static  int TotalLivreMFD(BemaString tamanho);
	public native static  int TamanhoTotalMFD(BemaString tamanho);
	public native static  int TempoOperacionalMFD(BemaString tempo);
	public native static  int TotalizaCupomMFD();
	public native static  int TotalizaRecebimentoMFD();
	public native static  int UsaRelatorioGerencialMFD(String texto);
	public native static  int ValorFormaPagamentoMFD(String formaPagamento, BemaString valorForma);
	public native static  int ValorTotalizadorNaoFiscalMFD(String totalizador, BemaString valor);
	public native static  int VerificaFormasPagamentoMFD(BemaString formasPagamento);
	public native static  int VerificaRecebimentoNaoFiscalMFD(BemaString recebimentoNaoFiscal);
	public native static  int VerificaRelatorioGerencialMFD(BemaString relatorio);
	public native static  int VerificaTotalizadoresNaoFiscaisMFD(BemaString totalizadores);
	public native static  int VerificaTotalizadoresParciaisMFD(BemaString totalizadores);
	public native static  int VersaoFirmwareMFD(BemaString versao);

	public native static  int FechaRelatorioXouZ();
	public native static  int FormatoDadosMFD(String arquivoMFD,String destino,String formato,String tipoDownload,String dadoInicial,String dadoFinal,String cUsuario);
	public native static  int GeraRelatorioSintegraMFD(int iRelatorios,String cOrigem,String destino, String mes,String ano,String cRazaoSocial,String endereco,String numero,String complemento,String cBairro,String cidade,String CEP,String telefone,String fax,String contato);
	public native static  int ImpressaoFitaDetalhe( String tipo, String dadoInicial, String dadoFinal, String usuario );
	public native static  int LeArquivoRetorno(BemaString retorno);
	public native static  int ProgramaIdAplicativoMFD(String idAplicativo);
	public native static  int ReducaoZImpAntiga();
	public native static  int RegistrosTipo60();
	public native static  int RelatorioGerencialImpAntiga( String texto );
	public native static  int RelatorioSintegraMFD(int relatorios, String arquivo,String mes,String ano,String razaoSocial,String endereco,String numero,String complemento,String bairro,String cidade,String CEP,String telefone,String fax,String contato);
	public native static  int TerminaFechamentoCupomCodigoBarrasMFD(String mensagem,String tipoCodigo,String codigo,int iAltura,int iLargura,int iPosicaoCaracteres,int iFonte,int iMargem,int iCorrecaoErros,int iColunas);
	public native static  int VersaoDll(BemaString versao);
	public native static  int SetaMFD(int flag);
	public native static  int AtivaDesativaVendaUmaLinhaMFD(int flag);
	public native static  int AtivaDesativaSensorPoucoPapelMFD(int flag);
	public native static  int AtivaDesativaAlinhamentoEsquerdaMFD(int flag);
	public native static  int AtivaDesativaTratamentoONOFFLineMFD(int flag);
	public native static  int StatusEstendidoMFD(BemaInteger status);
	public native static  int TempoRestanteComprovanteMFD(BemaString tempo);
	public native static  int UFProprietarioMFD(BemaString UF);
	public native static  int GrandeTotalUltimaReducaoMFD(BemaString grandeTotal);
	public native static  int DataMovimentoUltimaReducaoMFD(BemaString dataMovimento);
	public native static  int SubTotalComprovanteNaoFiscalMFD(BemaString subTotal);
	public native static  int InicioFimCOOsMFD(BemaString COOInicial, BemaString COOFinal);
	public native static  int InicioFimGTsMFD(BemaString GTInicial, BemaString GTFinal);
	public native static  int VerificaFlagCorteMFD(BemaInteger flag);
	public native static  int SelecionaIniLocal();
	public native static  int CancelaItemNaoFiscalMFD(String numeroItem);
	public native static  int AcrescimoItemNaoFiscalMFD(String numeroItem , String acrDesc , String tipoAcrDesc , String valorAcrDesc );
	public native static  int CancelaAcrescimoNaoFiscalMFD(String numeroItem,String acrDesc);
	public native static  int ImprimeClicheMFD();
	public native static  int ImprimeInformacaoChequeMFD(int posicao, int linhas, String mensagem);
	public native static  int VerificaAliquotasIssImpAntiga(BemaString aliquotasIss);
	public native static  int VerificaIndiceAliquotasIssImpAntiga(BemaString indices);
	public native static  int InfoBalanca(BemaString porta, BemaInteger modelo, BemaString peso,BemaString precoKg, BemaString total);
	public native static  int IniciaModoTEF();
	public native static  int FinalizaModoTEF();
	public native static  int UsaRelatorioGerencialMFDTEF(String texto);
	public native static  int RelatorioGerencialTEF( String texto );
	public native static  int UsaComprovanteNaoFiscalVinculadoTEF( String texto );
	public native static  int VerificaEstadoImpressoraMFD(BemaInteger ACK,BemaInteger ST1,BemaInteger ST2,BemaInteger ST3);
	public native static  int RelatorioTipo60AnaliticoMFD();

	//FUNCOES NOVAS
	public native static  int AtivaDesativaCancelamentoCupom2HorasMFD(int flag);
	public native static  int VerificaSensorPoucoPapelMFD(BemaString flag);
	public native static  int VerificaCancelamentoCupom2HorasMFD(BemaString flag);
	public native static  int LeituraFitaDetalheMFD(String tipo,String dadoInicial,String dadoFinal, String usuario);

	public native static  int NumeroSerieCriptografado(BemaString NumSerie);
	public native static  int NumeroSerieDescriptografado(String NumSerieCriptografado, BemaString NumSerieDescriptografado);
	public native static  int EfetuaFormaPagamentoIndiceDescricaoForma(String IndiceFormaPagamento, String ValorFormaPagamento, String DescricaoForma);
	public native static  int AcionaGaveta();
	public native static  int DadosSintegraMFD(String DataInicial, String DataFinal);
	public native static  int ConfiguraCorteGuilhotinaMFD(int tempo);
	public native static  int AtivaDesativaCorteTotalMFD(int flag);
	public native static  int AtivaDesativaCorteProximoMFD();
	//public native static  int AtivaDesativaSensorPoucoPapelMFD(int flag);
	public native static  int GeraRegistrosCAT52MFD(String Origem, String data);
	public native static  int GeraRegistrosCAT52MFDEx(String Origem, String data, BemaString Destino);
	public native static  int TotalIcmsCupom(BemaString cpf);
	public native static  int ViraChequeMFD();
	public native static  int AvancaPapelAcionaGuilhotinaMFD(int linhas, int modo);
	public native static  int ImprimeChequeMFDEx(String NumeroBanco, String Valor, String Favorecido, String Cidade, String Data, String Mensagem, String Fonte);
	public native static  int EstornoNaoFiscalVinculadoPosteriorMFD(String FromaPagamento, String Valor, String COOCupom, String COOCDC, String CPF, String Nome, String Endereco);
	public native static  int AtivaDesativaGuilhotinaMFD(short flag);
	public native static  int AbreSegundaViaNaoFiscalVinculadoMFD();
	public native static  int TotalIssCupomMFD(BemaString ISS);
	public native static  int VendeItemArredondamentoMFD(String Codigo, String Descricao, String Aliquota, String UnidadeMedida, String QtdFracionaria, String Unitario, String Acrescimo, String Desconto, boolean Arredonda);
	public native static  int FlagsFiscais3MFD(BemaInteger flag);
	public native static  int BaudRateBalanca(short a);
	public native static  int DataHoraGravacaoUsuarioSWBasicoMFAdicional(BemaString DataUsuario, BemaString DataSoftwareBasico, BemaString letraAdicional);
	public native static  int GeraRegistrosSpedMFD(  String ArquivoOrigem,
																String ArquivoDestino,
																String DataInicial,
																String DataFinal,
																String Perfil,
																String CFOP,
																String COBS,
																String Pis,
																String Cofins);
	public native static  int GeraRegistrosSpedCompleto( String ArquivoOrigem,
																	String ArquivoDestino,
																	String DataInicial,
																	String DataFinal,
																	String Perfil,
																	String CFOP,
																	String COBS,
																	String Pis,
																	String Cofins,
																	String Empresa,
																	String CodIBGE);
																	
	public native static  int FormatoDadosMF(String ArquivoMFD, String ArquivoDestino, String Formato, String TipoLeitura, String TipoParametro, String DadoInicial, String DadoFinal);
	//FUNCOES PAF-ECF
	public native static  int NomeiaRelatorioMeiosDePagamento();
	public native static  int NomeiaRelatorioDocumentoAuxiliarDeVenda();
	public native static  int NomeiaRelatorioDAVEmitidos();
	public native static  int NomeiaRelatorioIdentificacaoPAFECF();
	public native static  int NomeiaRelatoriosPAFECF();
	public native static  int AbreDocumentoAuxiliarVenda(String cIndice, String cTitulo, String cNumeroDAV, String cNomeEmitente, String cCNPJ_CPFEmitente, String cNomeDestinatario, String CNPJ_CPFDestinatario);
	public native static  int UsaDocumentoAuxiliarVenda(String cMercadoria, String cValorUnitario, String cValorTotal);
	public native static  int FechaDocumentoAuxiliarVenda(String cTotal);
	public native static  int TerminaFechamentoCupomPreVenda(String cMD5, String cNumeroPreVenda, String cMensagemPromocional);
	public native static  int DAVEmitidosRelatorioGerencial(String cIndice, String cDataInicial, String cDataFinal);
	public native static  int DAVEmitidosArquivo(String cNomeArquivo, String cDataInicial, String cDataFinal, String cChavePublica, String cChavePrivada);
	public native static  int LeituraMemoriaFiscalSerialDataPAFECF(String cDataInicial, String cDataFinal, String cFlagLeitura, String cChavePublica, String cChavePrivada);
	public native static  int LeituraMemoriaFiscalSerialReducaoPAFECF(String cCRZInicial, String cCRZFinal, String cFlagLeitura, String cChavePublica, String cChavePrivada);
	public native static  int EspelhoMFD(String cNomeArquivoDestino, String cDadoInicial, String cDadoFinal, String cTipoDownload, String cUsuario, String cChavePublica, String cChavePrivada);
	public native static  int ArquivoMFD(String cNomeArquivoOrigem, String cDadoInicial, String cDadoFinal, String cTipoDownload, String cUsuario, int cParametrizacao, String cChavePublica, String cChavePrivada, int iArquivoUnico);
	public native static  int ArquivoMFDPath(String cNomeArquivoOrigem, String cNomeArquivoDestino, String cDadoInicial, String cDadoFinal, String cTipoDownload, String cUsuario, int iParametrizacao, String cChavePublica, String cChavePrivada, int iArquivoUnico);
	public native static  int IdentificacaoPAFECF(String cIndice, String cNumeroLaudo, String cCNPJDesenvolvedor, String cRazaoSocial, String cEndereco, String cTelefone, String cContato, String cNomeComercial, String cVersao, String cPrincipalExecutavel, String cMD5PrincipalExecutavel, String cDemaisArquivos, String cMD5DemaisArquivos, String cNumerosFabricacao);
	public native static  int GrandeTotalCriptografado(BemaString cGTCriptografado);
	public native static  int GrandeTotalDescriptografado(String cGTCriptografado, BemaString cGTDescriptografado);
	public native static  int AbreRelatorioMeiosPagamento(String cIndice);
	public native static  int UsaRelatorioMeiosPagamento(String cIdentificacao, String cTipoDocumento, String cValorAcumulado, String cData);
	public native static  int FechaRelatorioMeiosPagamento();

	//FUNCOES SIGN_BEMA
	public native static  int setLibType(int type);
	public native static  int genkkey(BemaString pub, BemaString priv);
	public native static  int md5FromFile(String filename, BemaString md5out);
	public native static  int generateEAD(String filename, String pub, String priv, BemaString ead, int sign);
	public native static  int validateFile(String filename, String pub, String priv);

	public native static  int RetornaFatMFD();
        
	/*static{
	   System.loadLibrary ("BemaFI32");
		//System.load("C:/windows/system32SourceSafe/Impressora Fiscal/BemaFi32.dll/Fontes/Binario_USB/BemaFI32.dll");
		//System.load("C:\\Windows\\System32\\BemaFI32.dll");
	}*/
        
	static
	{
		String OSName = System.getProperty("os.name");
		OSName = OSName.toLowerCase();

		if (OSName.contains("linux"))
		{
			System.loadLibrary("bemafiscal");
		}
		else
		{
                    String PathDLL = ManipulacaoArquivo.DiretorioDeTrabalho();
                    //CarregarDLL.Carregar(DLL,Bematech.class);
                    //String Diretorio = ManipulacaoArquivo.DiretorioDeTrabalho();
                    //Map<String, String> env = System.getenv();
                   String PathAtual =  System.getProperty("java.library.path");
                   if(!PathAtual.contains(PathDLL)){
                       System.setProperty("java.library.path",  PathDLL +";"+ PathAtual );
                   }                   
                    PathAtual =  System.getProperty("java.library.path");
                   System.loadLibrary("BemaFI32");
                    //System.load(DLL);
                    //Runtime.getRuntime().load(DLL);
		}
	}

}

