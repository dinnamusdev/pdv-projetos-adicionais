package testeecf;


import com.sun.jna.win32.StdCallLibrary;

public interface Bematech  extends StdCallLibrary {
        public Bematech INSTANCE = (Bematech) CarregarDLL.Carregar("C:\\DTI\\DinnamuS_2\\app\\DinnamuS\\BemaFI32.dll", Bematech.class);
	public  int AlteraSimboloMoeda(String simboloMoeda);
	public  int AbreCupom(String CPF_CGC);
	public  int AumentaDescricaoItem(String descricao);
	public  int AbreComprovanteNaoFiscalVinculado(String formaPagamento,String valor,String numeroCupom);
	public  int Autenticacao();
	public  int AberturaDoDia(String valorAux,String formaPagamento);
	public  int AbrePortaSerial();
	public  int AbrePorta(int numero);
	public  int AbreBilhetePassagem(String imprimeValorFim,String imprimeEnfatizado,String embarque,String destino,String linha,String prefixo,String agente,String agencia,String data,String hora,String poltrona,String plataforma);
	public  int Acrescimos(BemaString valorAcrescimo);
	public  int CancelaItemAnterior();
	public  int CancelaItemGenerico(String numeroItem);
	public  int CancelaCupom();
	public  int EfetuaFormaPagamento(String formaPagamento, String valorFormaPagamento);
	public  int EfetuaFormaPagamentoDescricaoForma(String formaPagamento, String valorFormaPagamento, String descricaoForma);
	public  int EfetuaFormaPagamentoImpAntiga(String formaPagamento, String valorFormaPagamento);
	public  int EfetuaFormaPagamentoIndice(String indice,String valorFormaPagamento);
	public  int EspacoEntreLinhas(int dots);
	public  int EstornoFormasPagamento(String formaOrigem,String formaDestino,String valor);
	public  int FechaCupom(String formaPagamento,String acrescimoDesconto,String tipoAcrescimoDesconto,String valorAcrescimoDesconto,String valorPago,String mensagem);
	public  int FechaCupomResumido(String formaPagamento,String mensagem);
	public  int ForcaImpactoAgulhas(int iValorImpacto);
	public  int IniciaFechamentoCupom(String acrescimoDesconto,String tipoAcrescimoDesconto,String valorAcrescimoDesconto);
	public  int LinhasEntreCupons(int linhas);
	public  int NomeiaDepartamento(int indice, String departamento);
	public  int NomeiaTotalizadorNaoSujeitoIcms(int indice, String totalizador);
	public  int ProgramaAliquota(String aliquota, int vinculo);
	public  int ProgramaArredondamento();
	public  int ProgramaTruncamento();
	public  int ProgramaHorarioVerao();

	public  int VendeItem(String codigo, String descricao, String aliquota,String tipoQuantidade, String cQuantidade, int iCasasDecimais, String cUnitario, String tipoDesconto,String desconto);
	public  int VendeItemDepartamento(String codigo,String descricao,String aliquota,String valorUnitario,String cQuantidade,String valorAcrescimo,String valorDesconto,String indiceDepartamento,String cUnidadeMedida);


	public  int TerminaFechamentoCupom(String mensagem);
	public  int UsaUnidadeMedida(String cUnidadeMedida);
	public  int LeituraMemoriaFiscalData(String dataInicial, String dataFinal);
	public  int LeituraMemoriaFiscalReducao(String cReducaoInicial, String cReducaoFinal);
	public  int LeituraMemoriaFiscalSerialData(String dataInicial, String dataFinal);
	public  int LeituraMemoriaFiscalSerialReducao(String cReducaoInicial, String cReducaoFinal);
	public  int LeituraX();
	public  int LeituraXSerial();
	public  int ReducaoZ(String data, String cHora);

	public  int UsaComprovanteNaoFiscalVinculado(String texto);
	public  int FechaComprovanteNaoFiscalVinculado();
	public  int FechaRelatorioGerencial();
	public  int RelatorioGerencial(String texto);
	public  int RecebimentoNaoFiscal(String indiceTotalizador, String valorRecebimento, String formaPagamento);
	public  int Sangria(String valor);
	public  int Suprimento(String valor, String formaPagamento);

	public  int ProgramaCaracterAutenticacao(String caracter);
	public  int VerificaEstadoGaveta(BemaInteger estado);
	public  int CancelaImpressaCheque();
	public  int ImprimeCheque(String numeroBanco,String valor,String favorecido,String cidade,String data,String mensagem);
	public  int ImprimeCopiaCheque();
	public  int IncluiCidadeFavorecido(String cidade,String favorecido);
	public  int ProgramaMoedaPlural(String moedaPlural);
	public  int ProgramaMoedaSingular(String moedaSingular);
	public  int VerificaStatusCheque(BemaInteger status);

	public  int FechamentoDoDia();
	public  int FechaPortaSerial();
	public  int ImprimeConfiguracoesImpressora();
	public  int ImprimeDepartamentos();
	public  int MapaResumo();
	public  int RelatorioTipo60Analitico();
	public  int RelatorioTipo60Mestre();
	public  int ResetaImpressora();
	public  int RetornoImpressora(BemaInteger ACK, BemaInteger ST1, BemaInteger ST2);
	public  int VerificaImpressoraLigada();


	public  int ContadorBilhetePassagem(BemaString contador);
	public  int ImpressaoCarne(String titulo, String cParcela, String datas,int iQuantidade, String texto,	 String cliente,String cRGCPF,String cupom,int iVias,int iAssina);

	public  int Cancelamentos(BemaString cancelamentos);
	public  int CGCIE(BemaString CGC, BemaString IE);
	public  int ClicheProprietario(BemaString clicheProprietario);
	public  int ContadoresTotalizadoresNaoFiscais(BemaString Contadores);
	public  int DadosUltimaReducao(BemaString dadosReducao);
	public  int DataHoraImpressora(BemaString data, BemaString hora);
	public  int DataHoraReducao(BemaString data, BemaString hora);
	public  int DataMovimento(BemaString dataMovimento); 
	public  int Descontos(BemaString descontos);
	public  int FlagsFiscais(BemaInteger flagFiscal);
	public  int FlagsVinculacaoIss(BemaInteger flag1, BemaInteger flag2);
	public  int GrandeTotal(BemaString grandeTotal);
	public  int MinutosImprimindo(BemaString minutosImprimindo);
	public  int MinutosLigada(BemaString minutosLigada);
	public  int ModeloImpressora(BemaString modeloImpressora);
	public  int MonitoramentoPapel(BemaInteger linhasImpressas);
	public  int NumeroCaixa(BemaString numeroCaixa);
	public  int NumeroCupom(BemaString numeroCupom); 
	public  int NumeroCuponsCancelados(BemaString numeroCuponsCancelados);
	public  int NumeroIntervencoes(BemaString numeroIntervencoes);
	public  int NumeroLoja(BemaString numeroLoja);
	public  int NumeroOperacoesNaoFiscais(BemaString operacoes);
	public  int NumeroReducoes(BemaString numeroReducoes); 
	public  int NumeroSerie(BemaString numeroSerie);
	public  int NumeroSubstituicoesProprietario(BemaString substituicoes);
	public  int RetornoAliquotas(BemaString aliquotas);
	public  int SimboloMoeda(BemaString simboloMoeda);
	public  int SubTotal(BemaString subTotal);
	public  int UltimoItemVendido(BemaString ultimoItemVendido);
	public  int VendaBruta(BemaString valor);
	public  int ValorFormaPagamento(String forma, BemaString valorForma);
	public  int ValorPagoUltimoCupom(BemaString valorUltimoCupom);
	public  int ValorTotalizadorNaoFiscal(String totalizador, BemaString valor);
	public  int VerificaAliquotasISS(BemaString aliquotasISS);
	public  int VerificaDepartamentos(BemaString departamentos);
	public  int VerificaEpromConectada(BemaString flagEprom);
	public  int VerificaEstadoImpressora(BemaInteger ACK,BemaInteger ST1,BemaInteger ST2); 
	public  int VerificaFormasPagamento(BemaString formasPagamento);
	public  int VerificaIndiceAliquotasIss(BemaString indiceAliquotas);
	public  int VerificaMemoriaLivre(BemaString bytesLivres);
	public  int VerificaModoOperacao(BemaString modoOperacao);
	public  int VerificaRecebimentoNaoFiscal(BemaString recebimentos);
	public  int VerificaReducaoZAutomatica(BemaInteger flag);
	public  int VerificaTipoImpressora(BemaInteger tipo);
	public  int VerificaTotalizadoresNaoFiscais(BemaString totalizadores);
	public  int VerificaTotalizadoresParciais(BemaString totalizadores);
	public  int VerificaTruncamento(BemaString flagTruncamento);
	public  int VersaoFirmware(BemaString versaoFirmware);
	public  int VerificaZPendente(BemaString status);
	
	//FUNCOES RESTAURANTE
	public  int AbreConferenciaMesa(String mesa);
	public  int AbreCupomRestaurante(String mesa,String cPF);
	public  int CancelaVenda(String mesa,String codigo,String descricao,String aliqTributaria,String cQtde,String valor,String flagAcrescimoDesconto,String valorAcreDesc);
	public  int ConferenciaMesa(String mesa,String flagAcrescimoDesconto,String tipoAcrescimoDesconto,String valorAcrescimoDesconto);
	public  int ContaDividida(String numeroCupons,String valorPago,String cPF);
	public  int CardapioPelaSerial();
	public  int FechaConferenciaMesa(String flagAcrescimoDesconto,String tipoAcrescimoDesconto, String valorAcreDesc);
	public  int FechaCupomContaDividida(String numeroCupons,String acrescimoDesconto,String tipoAcrescimoDesconto,String valorAcrescimoDesconto,String formaPagamento,String valorFormaPagamento,String valorPagoConta,String cPF);
	public  int FechaCupomRestaurante(String formaPagamento,String acrescimoDesconto,String tipoAcrescimoDesconto,String valorAcrescimoDesconto,String valorPago,String mensagem);
	public  int FechaCupomResumidoRestaurante(String formaPagamento,String mensagem);
	public  int ImprimeCardapio();
	public  int RegistraVenda(String mesa,String codigo, String descricao,String aliquota, String cQuantidade,String valorUnitario, String FlagAcrescimoDesconto,String ValorAcrescimoDesconto);
	public  int RegistroVendaSerial(String mesa);
	public  int RelatorioMesasAbertas(int iTipoRelatorio);
	public  int RelatorioMesasAbertasSerial();
	public  int TransferenciaItem(String mesaOrigem,String codigo,String descricao,String aliquota,String cQuantidade,String valorUnitario,String flagAcrescimoDesconto,String valorAcrescimoDesconto,String mesaDestino);
	public  int TransferenciaMesa(String mesaOrigem, String mesaDestino);


	public  int AbreBilhetePassagemMFD(String embarque,String destino,String cLinha,String agencia,String data,String cHora,String cPoltrona,String cPlataforma,String tipoPassagem,String cRG,String cNome,String endereco,String cUF);
	public  int AbreComprovanteNaoFiscalVinculadoMFD(String formaPagamento,String valor,
																  String numeroCupom,
																  String cPF,
																  String cNome,
																  String endereco);
	public  int AbreCupomMFD(String cPF,String cNome,String endereco);
	public  int AbreRecebimentoNaoFiscalMFD(String cPF,String cNome,String endereco);
	public  int AbreRelatorioGerencialMFD(String totalizador);
	public  int AcionaGuilhotinaMFD(int modo);
	public  int AcrescimoDescontoItemMFD(String item,
													  String acrescimoDesconto,
													  String tipoAcrescimoDesconto,
													  String valorAcrescimoDesconto);
	public  int AcrescimoDescontoSubtotalRecebimentoMFD(String flag,
																	 String tipo,
																	 String valor);
	public  int AcrescimoDescontoSubtotalMFD(String flag,
														  String tipo,
														  String valor);
	public  int AutenticacaoMFD(String cLinhas, String texto);
	public  int CancelaAcrescimoDescontoItemMFD(String flag, String item);
	public  int CancelaAcrescimoDescontoSubtotalMFD(String flag);
	public  int CancelaAcrescimoDescontoSubtotalRecebimentoMFD(String flag);
	public  int CancelaCupomMFD(String cPF, String cNome, String endereco);
	public  int CancelaRecebimentoNaoFiscalMFD(String cPF, String cNome, String endereco);
	public  int ComprovantesNaoFiscaisNaoEmitidosMFD(BemaString comprovantes);
	public  int CNPJMFD(BemaString cNPJ);
	public  int CodigoBarrasCODABARMFD(String codigo);
	public  int CodigoBarrasCODE39MFD(String codigo);
	public  int CodigoBarrasCODE93MFD(String barcode);
	public  int CodigoBarrasCODE128MFD(String barcode);
	public  int CodigoBarrasEAN13MFD(String barcode);
	public  int CodigoBarrasEAN8MFD(String barcode);
	public  int CodigoBarrasISBNMFD(String barcode);
	public  int CodigoBarrasITFMFD(String barcode);
	public  int CodigoBarrasMSIMFD(String barcode);
	public  int CodigoBarrasPDF417MFD(int NCE, int altura,int largura,int numColunas,String barcode);
	public  int CodigoBarrasPLESSEYMFD(String barcode);
	public  int CodigoBarrasUPCAMFD(String barcode);
	public  int CodigoBarrasUPCEMFD(String barcode);
	public  int ConfiguraCodigoBarrasMFD(int altura, int largura,int posicaoCaracteres,int fonte,int margem);

	public  int ContadorComprovantesCreditoMFD(BemaString contador);
	public  int ContadorCupomFiscalMFD(BemaString contador);
	public  int ContadorFitaDetalheMFD(BemaString contador);
	public  int ContadorOperacoesNaoFiscaisCanceladasMFD(BemaString contador);
	public  int ContadorRelatoriosGerenciaisMFD(BemaString contador);
	public  int ContadoresTotalizadoresNaoFiscaisMFD(BemaString contador);
	public  int CupomAdicionalMFD();
	public  int DadosSintegra(String dataInicial ,String dataFinal);

	public  int DadosUltimaReducaoMFD(BemaString dadosUltReducao);
	public  int DataHoraUltimoDocumentoMFD(BemaString dataHora);
	public  int DownloadMF(String nomeArquivo);
	public  int DownloadMFD(String nomeArquivo,String tipoDownload,String dadoInicial,String dadoFinal,String usuario);
	public  int DownloadSB(String nomeArquivo);
	public  int EfetuaFormaPagamentoMFD(String formaPagamento, 
													 String valorFormaPagamento, 
													 String cParcelas, 
													 String descricaoFormaPagto);
	
	public  int EfetuaFormaPagamentoIndiceMFD(String indice,String valorFormaPagamento,String cParcelas, String descricaoForma);
	public  int EfetuaRecebimentoNaoFiscalMFD(String indiceTotalizador,
														   String valorRecebimento);
	public  int EstornoNaoFiscalVinculadoMFD(String cPF, String cNome, String endereco);
	public  int FechaRecebimentoNaoFiscalMFD(String mensagem);
	public  int HabilitaDesabilitaRetornoEstendidoMFD(String flagRetorno);
	public  int ImprimeChequeMFD(String numeroBanco, String valor,
											  String favorecido, String cidade,
											  String data,String msg,String impressaoVerso,
							  				  String cLinhas);
	public  int IniciaFechamentoCupomMFD(String acrescimoDesconto,
													  String tipoAcrescimoDesconto,
													  String valorAcrescimo,
													  String valorDesconto);
	public  int IniciaFechamentoRecebimentoNaoFiscalMFD(String acrescimoDesconto,
																	 String tipoAcrescimoDesconto,
			   														 String valorAcrescimo,
			   														 String valorDesconto);
	public  int InscricaoEstadualMFD(BemaString IE);
	public  int InscricaoMunicipalMFD(BemaString IM);
	public  int LeituraChequeMFD(BemaString cMC7);
	public  int LeituraMemoriaFiscalDataMFD(String dataInicial,String dataFinal,String flagLeitura);
	public  int LeituraMemoriaFiscalReducaoMFD(String cReducaoInicial,String cReducaoFinal,String flagLeitura);
	public  int LeituraMemoriaFiscalSerialDataMFD(String dataInicial,String dataFinal,String flagLeitura);
	public  int LeituraMemoriaFiscalSerialReducaoMFD(String cReducaoInicial,String cReducaoFinal,String flagLeitura);
	public  int MapaResumoMFD();
	public  int MarcaModeloTipoImpressoraMFD(BemaString marca, BemaString modelo, BemaString tipo);
	public  int MinutosEmitindoDocumentosFiscaisMFD(BemaString minutos);
	public  int NomeiaRelatorioGerencialMFD(String indice, String descricao);
	public  int NumeroSerieMFD(BemaString numeroSerie);
	public  int NumeroSerieMemoriaMFD(BemaString numeroSerie);
	public  int PercentualLivreMFD(BemaString valor);
	public  int ProgramaFormaPagamentoMFD(String formaPagamento, String cOperacaoTef);
	public  int ReducoesRestantesMFD(BemaString valor);
	public  int ReimpressaoNaoFiscalVinculadoMFD();
	public  int RetornoImpressoraMFD(BemaInteger ACK,BemaInteger ST1,BemaInteger ST2,BemaInteger ST3);
	public  int SegundaViaNaoFiscalVinculadoMFD();
	public  int SubTotalizaCupomMFD();
	public  int SubTotalizaRecebimentoMFD();
	public  int TotalLivreMFD(BemaString tamanho);
	public  int TamanhoTotalMFD(BemaString tamanho);
	public  int TempoOperacionalMFD(BemaString tempo);
	public  int TotalizaCupomMFD();
	public  int TotalizaRecebimentoMFD();
	public  int UsaRelatorioGerencialMFD(String texto);
	public  int ValorFormaPagamentoMFD(String formaPagamento, BemaString valorForma);
	public  int ValorTotalizadorNaoFiscalMFD(String totalizador, BemaString valor);
	public  int VerificaFormasPagamentoMFD(BemaString formasPagamento);
	public  int VerificaRecebimentoNaoFiscalMFD(BemaString recebimentoNaoFiscal);
	public  int VerificaRelatorioGerencialMFD(BemaString relatorio);
	public  int VerificaTotalizadoresNaoFiscaisMFD(BemaString totalizadores);
	public  int VerificaTotalizadoresParciaisMFD(BemaString totalizadores);
	public  int VersaoFirmwareMFD(BemaString versao);

	public  int FechaRelatorioXouZ();
	public  int FormatoDadosMFD(String arquivoMFD,String destino,String formato,String tipoDownload,String dadoInicial,String dadoFinal,String cUsuario);
	public  int GeraRelatorioSintegraMFD(int iRelatorios,String cOrigem,String destino, String mes,String ano,String cRazaoSocial,String endereco,String numero,String complemento,String cBairro,String cidade,String CEP,String telefone,String fax,String contato);
	public  int ImpressaoFitaDetalhe( String tipo, String dadoInicial, String dadoFinal, String usuario );
	public  int LeArquivoRetorno(BemaString retorno);
	public  int ProgramaIdAplicativoMFD(String idAplicativo);
	public  int ReducaoZImpAntiga();
	public  int RegistrosTipo60();
	public  int RelatorioGerencialImpAntiga( String texto );
	public  int RelatorioSintegraMFD(int relatorios, String arquivo,String mes,String ano,String razaoSocial,String endereco,String numero,String complemento,String bairro,String cidade,String CEP,String telefone,String fax,String contato);
	public  int TerminaFechamentoCupomCodigoBarrasMFD(String mensagem,String tipoCodigo,String codigo,int iAltura,int iLargura,int iPosicaoCaracteres,int iFonte,int iMargem,int iCorrecaoErros,int iColunas);
	public  int VersaoDll(BemaString versao);
	public  int SetaMFD(int flag);
	public  int AtivaDesativaVendaUmaLinhaMFD(int flag);
	public  int AtivaDesativaSensorPoucoPapelMFD(int flag);
	public  int AtivaDesativaAlinhamentoEsquerdaMFD(int flag);
	public  int AtivaDesativaTratamentoONOFFLineMFD(int flag);
	public  int StatusEstendidoMFD(BemaInteger status);
	public  int TempoRestanteComprovanteMFD(BemaString tempo);
	public  int UFProprietarioMFD(BemaString UF);
	public  int GrandeTotalUltimaReducaoMFD(BemaString grandeTotal);
	public  int DataMovimentoUltimaReducaoMFD(BemaString dataMovimento);
	public  int SubTotalComprovanteNaoFiscalMFD(BemaString subTotal);
	public  int InicioFimCOOsMFD(BemaString COOInicial, BemaString COOFinal);
	public  int InicioFimGTsMFD(BemaString GTInicial, BemaString GTFinal);
	public  int VerificaFlagCorteMFD(BemaInteger flag);
	public  int SelecionaIniLocal();
	public  int CancelaItemNaoFiscalMFD(String numeroItem);
	public  int AcrescimoItemNaoFiscalMFD(String numeroItem , String acrDesc , String tipoAcrDesc , String valorAcrDesc );
	public  int CancelaAcrescimoNaoFiscalMFD(String numeroItem,String acrDesc);
	public  int ImprimeClicheMFD();
	public  int ImprimeInformacaoChequeMFD(int posicao, int linhas, String mensagem);
	public  int VerificaAliquotasIssImpAntiga(BemaString aliquotasIss);
	public  int VerificaIndiceAliquotasIssImpAntiga(BemaString indices);
	public  int InfoBalanca(BemaString porta, BemaInteger modelo, BemaString peso,BemaString precoKg, BemaString total);
	public  int IniciaModoTEF();
	public  int FinalizaModoTEF();
	public  int UsaRelatorioGerencialMFDTEF(String texto);
	public  int RelatorioGerencialTEF( String texto );
	public  int UsaComprovanteNaoFiscalVinculadoTEF( String texto );
	public  int VerificaEstadoImpressoraMFD(BemaInteger ACK,BemaInteger ST1,BemaInteger ST2,BemaInteger ST3);
	public  int RelatorioTipo60AnaliticoMFD();

	//FUNCOES NOVAS
	public  int AtivaDesativaCancelamentoCupom2HorasMFD(int flag);
	public  int VerificaSensorPoucoPapelMFD(BemaString flag);
	public  int VerificaCancelamentoCupom2HorasMFD(BemaString flag);
	public  int LeituraFitaDetalheMFD(String tipo,String dadoInicial,String dadoFinal, String usuario);

	public  int NumeroSerieCriptografado(BemaString NumSerie);
	public  int NumeroSerieDescriptografado(String NumSerieCriptografado, BemaString NumSerieDescriptografado);
	public  int EfetuaFormaPagamentoIndiceDescricaoForma(String IndiceFormaPagamento, String ValorFormaPagamento, String DescricaoForma);
	public  int AcionaGaveta();
	public  int DadosSintegraMFD(String DataInicial, String DataFinal);
	public  int ConfiguraCorteGuilhotinaMFD(int tempo);
	public  int AtivaDesativaCorteTotalMFD(int flag);
	public  int AtivaDesativaCorteProximoMFD();
	//public  int AtivaDesativaSensorPoucoPapelMFD(int flag);
	public  int GeraRegistrosCAT52MFD(String Origem, String data);
	public  int GeraRegistrosCAT52MFDEx(String Origem, String data, BemaString Destino);
	public  int TotalIcmsCupom(BemaString cpf);
	public  int ViraChequeMFD();
	public  int AvancaPapelAcionaGuilhotinaMFD(int linhas, int modo);
	public  int ImprimeChequeMFDEx(String NumeroBanco, String Valor, String Favorecido, String Cidade, String Data, String Mensagem, String Fonte);
	public  int EstornoNaoFiscalVinculadoPosteriorMFD(String FromaPagamento, String Valor, String COOCupom, String COOCDC, String CPF, String Nome, String Endereco);
	public  int AtivaDesativaGuilhotinaMFD(short flag);
	public  int AbreSegundaViaNaoFiscalVinculadoMFD();
	public  int TotalIssCupomMFD(BemaString ISS);
	public  int VendeItemArredondamentoMFD(String Codigo, String Descricao, String Aliquota, String UnidadeMedida, String QtdFracionaria, String Unitario, String Acrescimo, String Desconto, boolean Arredonda);
	public  int FlagsFiscais3MFD(BemaInteger flag);
	public  int BaudRateBalanca(short a);
	public  int DataHoraGravacaoUsuarioSWBasicoMFAdicional(BemaString DataUsuario, BemaString DataSoftwareBasico, BemaString letraAdicional);
	public  int GeraRegistrosSpedMFD(  String ArquivoOrigem,
																String ArquivoDestino,
																String DataInicial,
																String DataFinal,
																String Perfil,
																String CFOP,
																String COBS,
																String Pis,
																String Cofins);
	public  int GeraRegistrosSpedCompleto( String ArquivoOrigem,
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
																	
	public  int FormatoDadosMF(String ArquivoMFD, String ArquivoDestino, String Formato, String TipoLeitura, String TipoParametro, String DadoInicial, String DadoFinal);
	//FUNCOES PAF-ECF
	public  int NomeiaRelatorioMeiosDePagamento();
	public  int NomeiaRelatorioDocumentoAuxiliarDeVenda();
	public  int NomeiaRelatorioDAVEmitidos();
	public  int NomeiaRelatorioIdentificacaoPAFECF();
	public  int NomeiaRelatoriosPAFECF();
	public  int AbreDocumentoAuxiliarVenda(String cIndice, String cTitulo, String cNumeroDAV, String cNomeEmitente, String cCNPJ_CPFEmitente, String cNomeDestinatario, String CNPJ_CPFDestinatario);
	public  int UsaDocumentoAuxiliarVenda(String cMercadoria, String cValorUnitario, String cValorTotal);
	public  int FechaDocumentoAuxiliarVenda(String cTotal);
	public  int TerminaFechamentoCupomPreVenda(String cMD5, String cNumeroPreVenda, String cMensagemPromocional);
	public  int DAVEmitidosRelatorioGerencial(String cIndice, String cDataInicial, String cDataFinal);
	public  int DAVEmitidosArquivo(String cNomeArquivo, String cDataInicial, String cDataFinal, String cChavePublica, String cChavePrivada);
	public  int LeituraMemoriaFiscalSerialDataPAFECF(String cDataInicial, String cDataFinal, String cFlagLeitura, String cChavePublica, String cChavePrivada);
	public  int LeituraMemoriaFiscalSerialReducaoPAFECF(String cCRZInicial, String cCRZFinal, String cFlagLeitura, String cChavePublica, String cChavePrivada);
	public  int EspelhoMFD(String cNomeArquivoDestino, String cDadoInicial, String cDadoFinal, String cTipoDownload, String cUsuario, String cChavePublica, String cChavePrivada);
	public  int ArquivoMFD(String cNomeArquivoOrigem, String cDadoInicial, String cDadoFinal, String cTipoDownload, String cUsuario, int cParametrizacao, String cChavePublica, String cChavePrivada, int iArquivoUnico);
	public  int ArquivoMFDPath(String cNomeArquivoOrigem, String cNomeArquivoDestino, String cDadoInicial, String cDadoFinal, String cTipoDownload, String cUsuario, int iParametrizacao, String cChavePublica, String cChavePrivada, int iArquivoUnico);
	public  int IdentificacaoPAFECF(String cIndice, String cNumeroLaudo, String cCNPJDesenvolvedor, String cRazaoSocial, String cEndereco, String cTelefone, String cContato, String cNomeComercial, String cVersao, String cPrincipalExecutavel, String cMD5PrincipalExecutavel, String cDemaisArquivos, String cMD5DemaisArquivos, String cNumerosFabricacao);
	public  int GrandeTotalCriptografado(BemaString cGTCriptografado);
	public  int GrandeTotalDescriptografado(String cGTCriptografado, BemaString cGTDescriptografado);
	public  int AbreRelatorioMeiosPagamento(String cIndice);
	public  int UsaRelatorioMeiosPagamento(String cIdentificacao, String cTipoDocumento, String cValorAcumulado, String cData);
	public  int FechaRelatorioMeiosPagamento();

	//FUNCOES SIGN_BEMA
	public  int setLibType(int type);
	public  int genkkey(BemaString pub, BemaString priv);
	public  int md5FromFile(String filename, BemaString md5out);
	public  int generateEAD(String filename, String pub, String priv, BemaString ead, int sign);
	public  int validateFile(String filename, String pub, String priv);

	public  int RetornaFatMFD();
        
	/*static{
	   System.loadLibrary ("BemaFI32");
		//System.load("C:/windows/system32SourceSafe/Impressora Fiscal/BemaFi32.dll/Fontes/Binario_USB/BemaFI32.dll");
		//System.load("C:\\Windows\\System32\\BemaFI32.dll");
	}*/
        /*
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
                    String DLL = ECFSuportados.PastaDLL_ECF(ECFSuportados.getNomeECFBematech())+ ManipulacaoArquivo.getSeparadorDiretorio() + "BemaFI32.dll";
                    CarregarDLL.Carregar(DLL,Bematech.class);
                    //String Diretorio = ManipulacaoArquivo.DiretorioDeTrabalho();
                    //Map<String, String> env = System.getenv();
                    System.loadLibrary("BemaFI32");
                    //System.load(DLL);
                    //Runtime.getRuntime().load(DLL);
		}
	}*/

}

