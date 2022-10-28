/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.tef.Padrao;

import br.TratamentoNulo.TratamentoNulos;
import br.com.ecf.ECFDinnamuS;
import br.com.info.Sistema;
import br.com.log.LogDinnamus;
import br.com.ui.InteracaoDuranteProcessamento;
import br.com.ui.MetodosUI_Auxiliares_1;
import br.comum.MetodosComuns;
import br.comum.TEFImpressao;
import br.impressao.ImpressoraCompravante;
import br.impressao.Perifericos;
import br.manipulararquivos.ManipulacaoArquivo;
import br.valor.formatar.FormatarNumero;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author Fernando
 */
public class TefPadrao {
    public static String TEF_GP_Ativo="";    
    
    //public static String PastaPadrao ="c:\\tef_dial\\";
    private static String PastaPadrao_Req = getPastaPadrao_Req();//"c:\\tef_dial\\req\\";
    private static String PastaPadrao_Resp =  getPastaPadrao_Resp();//"c:\\tef_dial\\resp\\";
    private static String NomeArquivo001 = "";//PastaReq()+"intpos.001";
    private static String NomeArquivoTmp ="";//PastaReq()+"intpos.tmp";
    private static String NomeArquivoSTS = "";// PastaResp() + "intpos.sts";
    private static String NomeArquivo001_Resp ="";//PastaResp()+"intpos.001";
    private static HashMap<String,String> RetornoVenda = null;
    private static HashMap<String,String> Retorno =new HashMap<>();
    private static int CodigoPDV =0;
    private static String NomeImpressoraComprovante ="";
    private static ImpressoraCompravante _impressora_comprovante=null;
    public static int ViasComprovanteTEF(){
        int Ret = 1;
        try {
            
            ResultSet rsCaixa= Sistema.Caixa();
            if(rsCaixa!=null){
                if(rsCaixa.next()){
                  Ret = rsCaixa.getInt("viascomptef");                    
                }
            
            } 
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
     public static boolean Sitef(){
        boolean Ret = false;
        try {
            
            ResultSet rsCaixa= Sistema.Caixa();
            if(rsCaixa!=null){
                if(rsCaixa.next()){
                  Ret = rsCaixa.getBoolean("sitef");                    
                }            
            }            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
    public static String PastaReq(){
        String Retorno ="";
        try {
            
            ResultSet rsCaixa= Sistema.Caixa();
            if(rsCaixa!=null){
                if(rsCaixa.next()){
                    Retorno= TratamentoNulos.getTratarString().Tratar(rsCaixa.getString("pasta_tef_req"),"");
                    if("".equalsIgnoreCase(Retorno)){
                       Retorno ="c:\\tef_dial\\req\\";
                    }else{
                        Retorno+="\\";
                    }                 
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public static String PastaResp(){
        String Retorno ="";
        try {            
            ResultSet rsCaixa= Sistema.Caixa();
            if(rsCaixa!=null){
                if(rsCaixa.next()){
                    Retorno= TratamentoNulos.getTratarString().Tratar(rsCaixa.getString("pasta_tef_resp"),"");
                    if("".equalsIgnoreCase(Retorno)){
                       Retorno ="c:\\tef_dial\\resp\\";
                    }else{
                        Retorno+="\\";
                    }
                }
            }     
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public static HashMap<String,HashMap<String,String>> IniciarVendaTEF_MultiplosCartoes( String cDocumento, ArrayList<Double> ValoresCartao, ECFDinnamuS ECF_TEF,String CNFV ){
        //int Retorno =0;
        HashMap<String,HashMap<String,String>> Retorno = new HashMap<String,HashMap<String,String>>();
        
        
        try {
            Double cValor=0d;
            if(!ValidarAmbienteTEF()){
                 Retorno = null;
            }else{
                for (int i = 0; i <ValoresCartao.size(); i++) {
                    cValor=ValoresCartao.get(i);
                    if(cValor<0.01f){
                        InteracaoDuranteProcessamento.Mensagem("TEF DinnamuS", "NÃO É POSSÍVEL REALIZAR A TRANSAÇÃO COM VALOR ZERADO", 5000);
                        return null;
                    }else{                    
                        if(VerificarGP()){
                            InteracaoDuranteProcessamento.Mensagem_Iniciar("TEF DinnamuS", "REALIZANDO VENDA "+ (i+1) +"ª - VALOR ["+ FormatarNumero.FormatarNumero(cValor,"##0.00") +"]");
                            //TEFInteracao.MensagemTEF_Terminar();
                            HashMap<String,String> RetornoVenda = TEF_Venda(cDocumento, cValor);
                            String  MensagemAoOperador  = RetornoVenda.get("msg");
                            // EXIBIR MENSAGEM AO OPERADOR
                            InteracaoDuranteProcessamento.Mensagem_Terminar();
                            if(MensagemAoOperador.length()>0){                              
                              InteracaoDuranteProcessamento.Mensagem("TEF DinnamuS", MensagemAoOperador, 5000);
                            }
                            if ("aprovada".equals(RetornoVenda.get("situacaovenda"))){                           
                                // CONFIRMANDO VENDA , CASO IMPRESSAO OK
                                String DadosImprimir = RetornoVenda.get(2);
                                TEFImpressao.setEcfTEF(ECF_TEF);
                                TEFImpressao.ComprovanteVinculado(CNFV, DadosImprimir);
                                String NumeroCupom =cDocumento,Rede =RetornoVenda.get(3),NSU=RetornoVenda.get(4),FINALIZACAO=RetornoVenda.get(5);                                                 
                            }
                            Retorno.put(String.valueOf((i+1)), RetornoVenda);
                        }else{
                            InteracaoDuranteProcessamento.Mensagem("TEF DinnamuS", "O TEF NÃO ESTA ATIVADO", 5000);
                        }
                    } 
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public static HashMap<String,String> IniciarVendaTEF(
                final String cDocumento,
                final Double cValor, Long nSeqVenda){
          return IniciarVendaTEF(cDocumento, cValor, nSeqVenda, true,"");
    }
    public static HashMap<String,String> IniciarVendaTEF(
                final String cDocumento,
                final Double cValor, Long nSeqVenda, boolean ExibirMSG, String IdentificacaoVenda){
        
        try {
          
            if(!ValidarAmbienteTEF()){
                 Retorno = null;
            }else{
                if(cValor<0.01f){                    
                    InteracaoDuranteProcessamento.Mensagem("TEF DinnamuS", "NÃO É POSSÍVEL REALIZAR A TRANSAÇÃO COM VALOR ZERADO", 5000);
                    Retorno = null;
                }else{
                   // if(VerificarGP()){
                    InteracaoDuranteProcessamento.Mensagem_Iniciar("TEF DinnamuS", "REALIZANDO VENDA "+ (nSeqVenda) +"ª - \nVALOR ["+ FormatarNumero.FormatarNumero(cValor,"##0.00") +"]");
                    RetornoVenda = TEF_Venda(cDocumento, cValor,IdentificacaoVenda);
                     String  MensagemAoOperador  = RetornoVenda.get("msg");
                    // EXIBIR MENSAGEM AO OPERADOR
                    InteracaoDuranteProcessamento.Mensagem_Terminar();
                    if(ExibirMSG){                        
                        if(MensagemAoOperador.length()>0){                              
                             InteracaoDuranteProcessamento.Mensagem("TEF DinnamuS", MensagemAoOperador, 5000);
                        }
                    }
                    Retorno =RetornoVenda;                                    
                        
                   
                } 
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);            
        }
        return Retorno;
    }
     public static HashMap<String,String> TEF_CANCELAR_VENDA(String ValorOP, String DataOP, String HoraOP,String NumeroCupom,String Rede, String NSU, String Finalizacao){
         try {
             
             String ConteudoArquivoCNC = TEFConteudoArquivo.TEF_ConteudoArquivo_CNC(NumeroCupom, Rede, NSU, Finalizacao,ValorOP,DataOP,HoraOP);
             String IDArquivoCNC = TEFConteudoArquivo.Identificacao;
             System.out.print("INICIANDO (CNC)\n");
             String cRetornoCriarArquivo =  MetodosComuns.CriarArquivo(getNomeArquivo001(),ConteudoArquivoCNC);
             if(cRetornoCriarArquivo.length()>0){
                System.out.print("VERIFICANDO INTPOS.001 (CNC)\n");
                Thread.sleep(2000);
                int nRetSTS = TEFAguardarArquivo(getNomeArquivo001_Resp(),IDArquivoCNC,0);
                //ManipulacaoArquivo.ExcluirArquivo(NomeArquivo001_Resp);
                Thread.sleep(2000); 
                if(nRetSTS==1){
                   String DadosRetornoCNC = ManipulacaoArquivo.LerArquivo(getNomeArquivo001_Resp(),false);                     
                   ManipulacaoArquivo.ExcluirArquivo(getNomeArquivo001_Resp());                   
                   if(MetodosComuns.ExtrairDados("028", DadosRetornoCNC).size()>0){
                      ArrayList<String> MsgAoOperador = MetodosComuns.ExtrairDados("030", DadosRetornoCNC); 
                      ArrayList<String> DadosParaImprimir = MetodosComuns.ExtrairDados("029", DadosRetornoCNC);
                      ArrayList<String> SituacaoVenda = MetodosComuns.ExtrairDados("009", DadosRetornoCNC);
                      ArrayList<String> REDE_CNC =MetodosComuns.ExtrairDados("010", DadosRetornoCNC);
                      ArrayList<String> NSU_CNC =MetodosComuns.ExtrairDados("012", DadosRetornoCNC);
                      ArrayList<String> FINALIZACAO_CNC =MetodosComuns.ExtrairDados("027", DadosRetornoCNC);
                      ArrayList<String> DATAOP_CNC =MetodosComuns.ExtrairDados("022", DadosRetornoCNC);
                      ArrayList<String> HORAOP_CNC =MetodosComuns.ExtrairDados("023", DadosRetornoCNC);
                      ArrayList<String> VALOROP_CNC =MetodosComuns.ExtrairDados("003", DadosRetornoCNC);
                      HashMap<String,String> retornoCNC = new HashMap<>();
                      retornoCNC.put("situacao", MetodosComuns.ConverterArrayListEmString(SituacaoVenda));
                      retornoCNC.put("msg", MetodosComuns.ConverterArrayListEmString(MsgAoOperador));
                      retornoCNC.put("imprimir", MetodosComuns.ConverterArrayListEmString(DadosParaImprimir));
                      retornoCNC.put("rede", MetodosComuns.ConverterArrayListEmString(REDE_CNC));
                      retornoCNC.put("nsu", MetodosComuns.ConverterArrayListEmString(NSU_CNC));
                      retornoCNC.put("finalizacao", MetodosComuns.ConverterArrayListEmString(FINALIZACAO_CNC));
                      System.out.print("OPERACAO (CNC) REALIZADA COM SUCESSO(\n");
                      return retornoCNC;
                   }                   
                   return null;     
                }
             }               
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             
         }
         return null;
     }
     public static boolean TEF_NAO_CONFIRMAR_VENDA(String NumeroCupom,String Rede, String NSU, String Finalizacao){
         try {
             
             String ConteudoArquivoCNF = TEFConteudoArquivo.TEF_ConteudoArquivo_NCN(NumeroCupom, Rede, NSU, Finalizacao);
             String IDArquivoCNF = TEFConteudoArquivo.Identificacao;
             System.out.print("INICIANDO NAO CONFIRMACAO DA OPERACAO(NCN)\n");
             String cRetornoCriarArquivo =  MetodosComuns.CriarArquivo(getNomeArquivo001(),ConteudoArquivoCNF);
             if(cRetornoCriarArquivo.length()>0){
                System.out.print("VERIFICANDO STS DA NAO CONFIRMACAO(NCN)\n");
                Thread.sleep(2000);
                int nRetSTS = TEFAguardarArquivo(getNomeArquivoSTS(),IDArquivoCNF,0);
                ManipulacaoArquivo.ExcluirArquivo(getNomeArquivoSTS());
                Thread.sleep(2000); 
                if(nRetSTS==1){
                   System.out.print("OPERACAO DE NAO CONFIRMACAO COM SUCESSO(NCN)\n");
                   ExcluirArquivoRetornoSalvo();
                   return true;     
                }
             }               
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             
         }
         return false;
     }
     public static boolean TEF_CONFIRMAR_VENDA(String NumeroCupom,String Rede, String NSU, String Finalizacao){
         InteracaoDuranteProcessamento.Mensagem_Iniciar("TEF DINNAMUS", "CONFIRMANDO TRANSAÇÃO ...AGUARDE");
         boolean ret = TEF_CONFIRMAR_VENDA_ACAO(NumeroCupom, Rede, NSU, Finalizacao);
         InteracaoDuranteProcessamento.Mensagem_Terminar();
         return ret;
         
     }
     public static boolean TEF_CONFIRMAR_VENDA_ACAO(String NumeroCupom,String Rede, String NSU, String Finalizacao){
         
         try {
             
             String ConteudoArquivoCNF = TEFConteudoArquivo.TEF_ConteudoArquivo_CNF(NumeroCupom, Rede, NSU, Finalizacao);
             String IDArquivoCNF = TEFConteudoArquivo.Identificacao;
             System.out.print("INICIANDO CONFIRMACAO DA OPERACAO\n");
             String cRetornoCriarArquivo =  MetodosComuns.CriarArquivo(getNomeArquivo001(),ConteudoArquivoCNF);
             if(cRetornoCriarArquivo.length()>0){
                System.out.print("VERIFICANDO STS DA CONFIRMACAO\n");
                Thread.sleep(2000);
                int nRetSTS = TEFAguardarArquivo(getNomeArquivoSTS(),IDArquivoCNF,0);
                ManipulacaoArquivo.ExcluirArquivo(getNomeArquivoSTS());
                Thread.sleep(2000); 
                if(nRetSTS==1){
                   System.out.print("OPERACAO CONFIRMADA COM SUCESSO\n");
                   ExcluirArquivoRetornoSalvo();
                   return true;     
                }
             }               
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             
         }
         return false;
     }
     public static  HashMap<String,String> TEF_Venda(String cDocumento,Double cValor){
         return TEF_Venda(cDocumento, cValor, "");
     }
     public static  HashMap<String,String> TEF_Venda(String cDocumento,Double cValor, String IdentificacaoVenda){
         //ArrayList<String> Retorno = new ArrayList<>();
         HashMap<String,String> Retorno = new HashMap<>();
         //boolean Retorno = false;
         try {             
                String ConteudoArquivoTransacao = TEFConteudoArquivo.TEF_Conteudo_Arquivo_Transacao(cDocumento,cValor,IdentificacaoVenda);
                String IDArquivoTRX = TEFConteudoArquivo.Identificacao;
                String cRetornoCriarArquivo =  MetodosComuns.CriarArquivo(getNomeArquivo001(),ConteudoArquivoTransacao);
                if(cRetornoCriarArquivo.length()>0){
                   // Criou arquivo transacao com sucesso. Iniciando o aguardo do retorno
                    InteracaoDuranteProcessamento.Mensagem_Terminar();
                    System.out.print("VERIFICANDO STS DA VENDA\n");
                    Thread.sleep(2000);
                    int nRetSTS = TEFAguardarArquivo(getNomeArquivoSTS(),IDArquivoTRX,0);
                    ManipulacaoArquivo.ExcluirArquivo(getNomeArquivoSTS());
                    Thread.sleep(2000);
                    
                    if(nRetSTS==1){
                       //ACHOU O STS CORRETO. AGUARDA O ARQUIVO INTPOS.001
                        System.out.print("VERIFICANDO 001 DA VENDA\n");
                        int nRet001 = TEFAguardarArquivo(getNomeArquivo001_Resp(),IDArquivoTRX,0);                        
                        if(nRet001==1){
                            
                           //ARQUIVO DE RESPOSTA INTPOS.001 DISPONIVEL
                           String MensagemAoOperador ="";
                           String cDadosIntpos001 = ManipulacaoArquivo.LerArquivo(getNomeArquivo001_Resp(),false);                            
                           ArrayList<String> NSU =MetodosComuns.ExtrairDados("012", cDadosIntpos001);
                           ArrayList<String> SituacaoVenda = MetodosComuns.ExtrairDados("009", cDadosIntpos001);
                           if("0".equals(SituacaoVenda.get(0).trim())){
                               //if(NSU.size())
                                SalvarArquivoRetorno(getNomeArquivo001_Resp());
                            }
                           ManipulacaoArquivo.ExcluirArquivo(getNomeArquivo001_Resp());
                           ArrayList<String> MsgAoOperador = MetodosComuns.ExtrairDados("030", cDadosIntpos001);
                           ArrayList<String> MsgAoOperadorEspecial = MetodosComuns.ExtrairDados("031", cDadosIntpos001);
                           ArrayList<String> DadosParaImprimir = MetodosComuns.ExtrairDados("029", cDadosIntpos001);
                           
                           ArrayList<String> REDE =MetodosComuns.ExtrairDados("010", cDadosIntpos001);
                           
                           ArrayList<String> FINALIZACAO =MetodosComuns.ExtrairDados("027", cDadosIntpos001);
                           ArrayList<String> DATAOP =MetodosComuns.ExtrairDados("022", cDadosIntpos001);
                           ArrayList<String> HORAOP =MetodosComuns.ExtrairDados("023", cDadosIntpos001);
                           ArrayList<String> VALOROP =MetodosComuns.ExtrairDados("003", cDadosIntpos001);
                           ArrayList<String> IDENTIFICACAO =MetodosComuns.ExtrairDados("001", cDadosIntpos001);
                           ArrayList<String> QTPARCELAS =MetodosComuns.ExtrairDados("018", cDadosIntpos001);
                           
                           if(MsgAoOperador.size()>0){
                             MensagemAoOperador=MetodosComuns.ConverterArrayListEmString(MsgAoOperador);
                           }
                           if(MsgAoOperadorEspecial.size()>0){
                              MensagemAoOperador=MensagemAoOperador + "\n"+MetodosComuns.ConverterArrayListEmString(MsgAoOperadorEspecial);
                           }             
                           if("0".equals(SituacaoVenda.get(0).trim())){
                               Retorno.put("situacaovenda","aprovada");
                               Retorno.put("msg",MensagemAoOperador);
                               Retorno.put("imprimir", MetodosComuns.ConverterArrayListEmString(DadosParaImprimir));  
                               Retorno.put("rede",REDE.get(0));
                               Retorno.put("nsu",NSU.get(0));
                               Retorno.put("finalizacao",FINALIZACAO.get(0));     
                               Retorno.put("dataop",DATAOP.get(0));
                               Retorno.put("horaop",HORAOP.get(0));   
                               Retorno.put("valorop",VALOROP.get(0));   
                               Retorno.put("identificacao",IDENTIFICACAO.get(0));   
                               if(QTPARCELAS.size()>0){
                                 Retorno.put("parcelas",QTPARCELAS.get(0));   
                               }else{   
                                  Retorno.put("parcelas","1");   
                               }
                               
                           }else{
                               Retorno.put("situacaovenda","negada");
                               Retorno.put("msg",MensagemAoOperador);
                           }
                        }                       
                    }
                }else{
                    InteracaoDuranteProcessamento.Mensagem("TEF DinnamuS", "NÃO É POSSÍVEL CRIAR O ARQUIVO INTPOS.001 DE TRANSACAO ", 5000);
                }
            
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             
         }
         return Retorno;
     }
     public static String TEF_CriarArquivo(String cNomeArquivo, String cDadosArquivo){
         String cRetorno ="";
         try {
             
             
             String cNomeArquivoReq = cNomeArquivo; 
             String cNomeArquivoReq_Tmp = cNomeArquivo.replaceAll(".001", ".tmp"); 
              
             cNomeArquivo=MetodosComuns.CriarArquivo(cNomeArquivoReq_Tmp, cDadosArquivo);
             
             if(cNomeArquivo.length()>0){                           
                if(ManipulacaoArquivo.ArquivoExiste(cNomeArquivoReq, false)){
                   ManipulacaoArquivo.ExcluirArquivo(cNomeArquivoReq);
                }
                if(ManipulacaoArquivo.Arquivo_Copiar(cNomeArquivoReq_Tmp, cNomeArquivoReq)){
                   ManipulacaoArquivo.ExcluirArquivo(cNomeArquivoReq_Tmp);
                   cRetorno = cNomeArquivo;     
                 }else{
                     InteracaoDuranteProcessamento.Mensagem("TEF DinnamuS", "NÃO É POSSÍVEL CRIAR O ARQUIVO INTPOS.001", 5000);
                 }     
             }
         } catch (Exception e) {
             LogDinnamus.Log(e, true);          
         }
         return cRetorno;
     }
     public static void main(String[] args) {
        
       // LogDinnamus.Iniciar();
        
        try {
            ArrayList<HashMap<String,Double>> Valores = new ArrayList<>();
         
            ECFDinnamuS ecf = new ECFDinnamuS();
            //ecf.setTipoECF(ecf.getNomeECFBematech());
            
            /*if(!ecf.ECF_OK()){
                System.out.print(MetodosComuns.ConverterArrayListEmString(ecf.StatusECF()));
                System.exit(0); 
            }
            */
            int nRet = ecf.VerificarStatusCupomAberto();
            Thread.sleep(1000);
            if(nRet==1){
                ecf.CancelarUltimoCupom();
            }
            ArrayList<String> StatusEstentido = new ArrayList<>();
            
            StatusEstentido= ecf.StatusECF_Estentido();
            for (int i = 0; i < StatusEstentido.size(); i++) {
                if(StatusEstentido.get(i).equalsIgnoreCase("CNFV ABERTO")){
                    ecf.CNFV_Fechar();                
                }
            }
            Thread.sleep(1000);
            
            int ret = ecf.AbreCupom("");
            if(ret!=1){ 
                System.out.print("falha na abertura do cupom\n");
                System.exit(0); 
            }
            Thread.sleep(1000);
            String COO = ecf.UltimoCupom();
            
            //ret = ecf.VendeItem("01", "PRODUTO TEF DINNAMUS","II", "I", 1f,2, 100f, "$", "0", "un");
            if(ret!=1){
                System.out.print("falha na imp do item\n");
                System.exit(0); 
            }
         
            ret= ecf.IniciaFechamentoCupom("D", "$", 0d);
            
            if(ret!=1){
                System.out.print("falha no inicio do fechamento\n");
                System.exit(0); 
            }
            //ecf.EfetuarFormaPagto("CARTAO", 100f);  
            Hashtable<String,Float> tabela = new Hashtable<>();
            
            
            //ArrayList<HashMap<String,String>> teste = new ArrayList<>();
            
            
            HashMap<String,Float> Valor = new HashMap<>();
            
            Valor.put("VISA", 100f);
            //Valores.add(0, Valor);            
           /* 
            Valor = new HashMap<>();
            Valor.put("AMEX", 200f);
            Valores.add(1,Valor);
            /*
            Valor = new HashMap<>();
            Valor.put("REDECARD", 300f);
            Valores.add(2,Valor);
            */
             
            
            HashMap<String,String>RetornoVenda = new HashMap<>();
            HashMap<String,HashMap<String,String>> RetornoVendas = new HashMap<String,HashMap<String,String>>();
            
            boolean Negada =false;
            boolean MultiplosCartao = (Valores.size()>1 ? true : false);
            Long SeqVenda =0l;
            for (int i = 0; i < Valores.size(); i++) {
               // Valor   = Valores.get(i);
                
                Double ValorTEF = (Double) Valor.values().toArray()[0];
                //SeqVenda = (long) i+1;
                RetornoVenda = IniciarVendaTEF(COO,ValorTEF,  (long)i+1);                
                
                RetornoVendas.put(String.valueOf(i), RetornoVenda);
                
                if("aprovada".equals(RetornoVenda.get("situacaovenda"))){
                   if(i<Valores.size()-1  && Valores.size()>1) {
                     // É UMA VENDA COM MULTIPLOS CARTOES. 
                     //CONFIRMAR TODAS AS VENDAS MENOS AO ULTIMA, A QUAL VAI FICAR AGUARDANDO O RETORNO DA IMPRESSAO DOS COMPROVANTES
                     boolean retcnf = TEF_CONFIRMAR_VENDA(COO,RetornoVenda.get("rede"),RetornoVenda.get("nsu"),RetornoVenda.get("finalizacao"));
                     if(retcnf==false){
                        break;
                     }
                   }
                }else{
                    //Negada=true;
                    break;
                }
            }          
            //VERIFICAR SE TODAS AS OPERACOES FORAM APROVADAS            
            for (Map.Entry<String, HashMap<String, String>> entry : RetornoVendas.entrySet()) {
               
               // EXISTE ALGUMA TRANSACAO NEGADA ?
                if(entry.getValue().get("situacaovenda")=="negada"){
                    Negada=true;
                }                
            }
            
            int ImprimiuOK = 0;
            if(!Negada){
               // TUDO APROVADO OK
               // FECHA O CUPOM FISCAL PARA INICIAR OS COMPROVANTE
               ImprimiuOK = TerminarCupomFiscal(Valores, ecf);                
               // INICIAR A IMPRESSAO DOS COMPROVANTES
               if(ImprimiuOK==1){
                    //ImprimiuOK = ImprimirComprovanteTEF(Valores, RetornoVendas, ecf, COO);
                    if(ImprimiuOK==1){
                         //IMPRIMIU TUDO OK  
                         Integer nVendaConfirma =0;
                         if(MultiplosCartao){
                            nVendaConfirma = RetornoVendas.size()-1;
                         }else{
                            nVendaConfirma=0;
                         }
                         String NSU = RetornoVendas.get(nVendaConfirma.toString()).get("nsu");
                         String FINALIZACAO = RetornoVendas.get(nVendaConfirma.toString()).get("finalizacao");
                         String REDE = RetornoVendas.get(nVendaConfirma.toString()).get("rede");
                         boolean bRetConfirmacao = TEF_CONFIRMAR_VENDA(COO, REDE, NSU, FINALIZACAO);   
                    }
               }
                
            }
            System.exit(0);
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
    }
     
     public static int TerminarCupomFiscal( ArrayList<HashMap<String,Double>> Valores, ECFDinnamuS ecf){
         int Retorno=0;
         int ImprimiuOK=0;
         try {
                HashMap<String,Double> Valor = new HashMap<>();
                String NomeFormapagto ="";
                Double ValorFormaPagto=0d;
                for (int i = 0; i < Valores.size(); i++) {                    
                    Valor   = Valores.get(i);                    
                    NomeFormapagto = Valor.keySet().toArray()[0].toString();                    
                    ValorFormaPagto= Double.valueOf( Valor.values().toArray()[0].toString());                    
                    Boolean bECF_OK =false;
                    while(!bECF_OK){         
                         
                         ImprimiuOK=ecf.EfetuarFormaPagto(NomeFormapagto, ValorFormaPagto); 
                         
                         Thread.sleep(1000);
                         
                         //bECF_OK = ecf.ECF_OK();
                         ////
                         //MetodosUI_Auxiliares.MensagemAoUsuarioSimplesAVISO(null, bECF_OK.toString(), "");
                         
                         if(ImprimiuOK!=1){
                             int nRetResp= MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes_Sim_e_Nao(null, "A IMPRESSORA NÃO RESPONDE"+  (ecf.getError()!="" ? "(" + ecf.getError() + ")" : "") +". DESEJA TENTAR NOVAMENTE? ", "IMPRESSÃO COMPROVANTE(FECHAR F.PAGTO)");
                             if(nRetResp!=MetodosUI_Auxiliares_1.Sim()){                                 
                                 break;
                             }
                         }else{
                             break;
                         }
                    }                    
                }
                ImprimiuOK=0;
                while(ImprimiuOK!=1){                                                 
                    ImprimiuOK= ecf.TerminaFechamento(0f, "CUPOM TESTE DO TEF V2");
                    if(ImprimiuOK!=1){
                        int nRetResp= MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes_Sim_e_Nao(null, "A IMPRESSORA NÃO RESPONDE. DESEJA TENTAR NOVAMENTE? ", "IMPRESSÃO COMPROVANTE(FECHAR F.PAGTO)");
                        if(nRetResp!=MetodosUI_Auxiliares_1.Sim()){                                 
                            break;
                        }
                    }else{
                        Retorno=1;
                        break;
                    }
                }
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return   Retorno;
         
     }
     public static int ImprimirComprovanteTEF( String Titulo,String DadosImprimir,String NomeFormapagto,String COO,Double ValorFormapagto, ECFDinnamuS ecf, String TipoComprovanteImpresso){
         int Retorno=0;
         int ImprimiuOK=0;
         try {
            //FileOutputStream porta=null;
            //TEFInteracao.MensagemTEF_Iniciar("TEF DINNAMUS - COMPROVANTE [ "+ Titulo +" ]" , "ABRINDO COMPROVANTE....AGUARDE");                        
            if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){
                ImprimiuOK=  ecf.CNFV_Abrir(NomeFormapagto,COO,ValorFormapagto);
                if(ImprimiuOK!=1){
                    int nRetResp= MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes_Sim_e_Nao(null, "A IMPRESSORA NÃO RESPONDE. DESEJA TENTAR NOVAMENTE? ", "IMPRESSÃO COMPROVANTE(ABRIR CNFV)");
                    if(nRetResp!=MetodosUI_Auxiliares_1.Sim()){
                        //INTERROMPE A IMPRESSÃO
                        return 0;
                    }else{
                        return 2; //recomeçar
                    }
                }
            }
                                                      
            ImprimiuOK = 0;
            int nVia =1;
            boolean SITEF =  Sitef();
            int nTotVias = ViasComprovanteTEF();
            InteracaoDuranteProcessamento.Mensagem_Iniciar("TEF DINNAMUS - COMPROVANTE ["+ Titulo +"]", "IMPRIMINDO....AGUARDE");                        
            while(ImprimiuOK!=1 && nVia<=nTotVias){                
                 if(SITEF){                              
                    //String[] ComprovanteSITEF = DadosImprimir.split("(SiTef)");
                    int nPosSitef =  DadosImprimir.indexOf("(SiTef)");  
                    String ParteCompSITEF_ViasCliente = DadosImprimir.substring(0,nPosSitef+7);
                    String ParteCompSITEF_ViasEstabelecimento = DadosImprimir.substring(nPosSitef+7);
                    ImprimiuOK = ecf.CNFV_Usar(ParteCompSITEF_ViasCliente);
                    if(ImprimiuOK==1){
                        for (int i = 0; i < 3; i++) {
                            if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){
                                ImprimiuOK =  ecf.CNFV_Usar("     " + System.getProperty("line.separator"));
                            }else{                             
                                try {
                                     String Imp ="     " + System.getProperty("line.separator");
                                     getImpressora_comprovante().Imprimir_Texto(Imp.getBytes());
                                     ImprimiuOK=1;
                                } catch (Exception e) {
                                    LogDinnamus.Log(e, true);
                                    ImprimiuOK=0;
                                }
                               
                            }
                            if (ImprimiuOK != 1) {
                                break;
                            }
                        }
                       if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){
                            ecf.AcionarGuilhotina();
                            ImprimiuOK = ecf.CNFV_Usar(ParteCompSITEF_ViasEstabelecimento);
                       }else{
                           try {
                               ImprimiuOK =AcionarGulhotinaImpComprovante();
                               getImpressora_comprovante().Imprimir_Texto(ParteCompSITEF_ViasEstabelecimento.getBytes());   
                               ImprimiuOK=1;
                           } catch (Exception e) {
                               LogDinnamus.Log(e,true);
                               ImprimiuOK=0;
                           }
                           
                       }
                    }                    
                 }else{
                    if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){
                        ImprimiuOK = ecf.CNFV_Usar(DadosImprimir);       
                    }else{
                         try {
                               getImpressora_comprovante().Imprimir_Texto(DadosImprimir.getBytes());                            
                               ImprimiuOK=1;
                           } catch (Exception e) {
                               LogDinnamus.Log(e,true);
                               ImprimiuOK=0;
                           }
                    }
                 }
                 if(ImprimiuOK!=1){
                     InteracaoDuranteProcessamento.Mensagem_Terminar(); 
                     int nRetResp= MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes_Sim_e_Nao(null, "A IMPRESSORA NÃO RESPONDE. DESEJA TENTAR NOVAMENTE? ", "IMPRESSÃO COMPROVANTE(IMPRIMIR CNFV)");
                     if(nRetResp!=MetodosUI_Auxiliares_1.Sim()){
                         //INTERROMPE A IMPRESSÃO
                         return 0;
                     }else{
                         return 2; //recomeçar
                     }
                 }else{   
                     if(nVia==1 && nVia<nTotVias){
                         
                         for (int i = 0; i < 6; i++) {
                            ImprimiuOK =  ecf.CNFV_Usar("     " + System.getProperty("line.separator"));
                            if (ImprimiuOK != 1) {
                                break;
                            }
                        }
                        if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){
                         ecf.AcionarGuilhotina();
                        }else{
                               ImprimiuOK =AcionarGulhotinaImpComprovante();
                        }
                         //TEFInteracao.MensagemTEF("TEF DINNAMUS - COMPROVANTE["+  Titulo +"]", "DESTAQUE A 1ª VIA DO COMPROVANTE\n\nVOCÊ TEM 5 (CINCO) SEGUNDOS PARA A IMPRESSÃO DA SEGUNDA VIA", 5000);
                         ImprimiuOK=0;
                     }
                     nVia++;
                 }
            }                       
           InteracaoDuranteProcessamento.Mensagem_Terminar(); 
           if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){
             ecf.CNFV_Fechar();
           }
            
           
            if(TipoComprovanteImpresso.equalsIgnoreCase("nfce")){
              ImprimiuOK =AcionarGulhotinaImpComprovante();
              //porta.close();
            }
           
            if(ImprimiuOK!=1){
                return 0; // interrompe
            }else{
                return 1;
            }                    
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             Retorno=-1; 
         }
         return Retorno;
     
     }
/*     public static int ImprimirComprovanteTEF( ArrayList<HashMap<String,Double>> Valores, ArrayList<HashMap<String,String>> RetornoVendas  , ECFDinnamuS ecf, String COO){
         return ImprimirComprovanteTEF(Valores, RetornoVendas, ecf, COO, 0,"fiscal");
     }*/
     public static int ImprimirComprovanteTEF( ArrayList<HashMap<String,Double>> Valores, ArrayList<HashMap<String,String>> RetornoVendas  , ECFDinnamuS ecf, String COO, int TipoComprovante, String TipoComprovanteImpresso){
         int Retorno=0;
         int ImprimiuOK=0;
         HashMap<String,Double> Valor = new HashMap<>();
         HashMap<String,String>RetornoVenda = new HashMap<>();
         
         
         try {
             
            for (int i = 0; i < RetornoVendas.size(); i++) {
                    Valor   = Valores.get(i);
                    
                    String NomeFormapagto =(String) Valor.keySet().toArray()[0];
                    
                    Double ValorFormapagto =(Double) Valor.values().toArray()[0];
                    
                    RetornoVenda  =  RetornoVendas.get(i); //.get(String.valueOf(i));
                    String DadosImprimir =RetornoVenda.get("imprimir");
                    
                    if(TipoComprovante==0){ // comprovante vinculado
                        ImprimiuOK=ImprimirComprovanteTEF(RetornoVenda.get("rede"), DadosImprimir, NomeFormapagto, COO, ValorFormapagto, ecf, TipoComprovanteImpresso);
                    }else{
                        ImprimiuOK=ImprimirComprovanteTEF_Adm(DadosImprimir, ecf, RetornoVenda.get("rede"),true,TipoComprovanteImpresso);
                    }
                    
                    if(ImprimiuOK==2){// recomeçar com relatorio gerencial
                        return ImprimirComprovanteTEF(Valores, RetornoVendas, ecf, COO, 1,TipoComprovanteImpresso);                        
                    }else if(ImprimiuOK==0 || ImprimiuOK<=-1){ // interrompe impressao
                        return ImprimiuOK;
                    }else{
                        Retorno=1;
                    }
               }
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         } 
         return Retorno;
     }
     public static int ImprimirComprovanteTEF_Adm_Acao( String DadosImprimir  , ECFDinnamuS ecf, String Titulo,String TipoComprovanteImpresso){
         int  Retorno=0;
         try {
         
             Retorno =ImprimirComprovanteTEF_Adm(DadosImprimir, ecf, Titulo, true, TipoComprovanteImpresso);
             
             if(Retorno==2){
                return ImprimirComprovanteTEF_Adm_Acao(DadosImprimir, ecf, Titulo, TipoComprovanteImpresso);
             }             
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             Retorno = -1;         
         }
         return Retorno;         
     }
     public static int ImprimirComprovanteTEF_Adm( String DadosImprimir  , ECFDinnamuS ecf, String Titulo){
            return ImprimirComprovanteTEF_Adm(DadosImprimir, ecf, Titulo, false,"");
     }
     public  static int AcionarGulhotinaImpComprovante(){
         int ImprimiuOK  =0;
         try {
             byte[] Codigo_guilhotina = Perifericos.BuscarCodigoGuilhotina();
             if (Codigo_guilhotina != null) {
                 getImpressora_comprovante().Imprimir_Texto(Codigo_guilhotina);
             }
             ImprimiuOK = 1;
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             ImprimiuOK = 0;
         }
         return ImprimiuOK;
     }
     public static int ImprimirComprovanteTEF_Adm( String DadosImprimir  , ECFDinnamuS ecf, String Titulo, boolean ImpressaoDireta,String TipoComprovanteImpresso){
         int Retorno=0;//
         int ImprimiuOK=0;
         //HashMap<String,Float> Valor = new HashMap<>();
         //HashMap<String,String>RetornoVenda = new HashMap<>();
         //FileOutputStream porta=null;
         try {               
            
            
             
            ImprimiuOK = 0;
            int nVia =1;
            
            if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){
                ecf.CancelarQualquerComprovanteAberto();
            }
            
            InteracaoDuranteProcessamento.Mensagem_Terminar();
            //System.out.println("iniciando comprovante adm");
            if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){
                ecf.ComprovanteNaoFiscal_Abrir();
            }
            int nViasComp = ViasComprovanteTEF();
            boolean Sitef = Sitef();
            while(ImprimiuOK!=1 && nVia<=nViasComp){
                 if(!ImpressaoDireta){
                    InteracaoDuranteProcessamento.Mensagem_Iniciar("TEF DINNAMUS [" + Titulo +"]", "IMPRIMINDO " +  nVia +"ª VIA DO COMPROVANTE....AGUARDE");                        
                 }
                 System.out.println("ImprimirComprovanteTEF_Adm");
                 System.out.println(DadosImprimir);
                 if(Sitef){
                    int nPosSitef =  DadosImprimir.indexOf("(SiTef)");  
                    String ParteCompSITEF_ViasCliente = DadosImprimir.substring(0,nPosSitef+7);
                    String ParteCompSITEF_ViasEstabelecimento = DadosImprimir.substring(nPosSitef+7);
                    if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){
                        ImprimiuOK = ecf.ComprovanteNaoFiscal_Usar(ParteCompSITEF_ViasCliente);
                    }else{                        
                        try {
                            getImpressora_comprovante().Imprimir_Texto(ParteCompSITEF_ViasCliente.getBytes());                            
                            ImprimiuOK=1;
                        } catch (Exception e) {
                            LogDinnamus.Log(e, true);
                            ImprimiuOK=0;
                        }
                        
                    }
                    if(ImprimiuOK==1){
                        for (int i = 0; i < 3; i++) {
                            if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){
                                ImprimiuOK = ecf.ComprovanteNaoFiscal_Usar(System.getProperty("line.separator"));
                            }else{
                                getImpressora_comprovante().Imprimir_Texto(System.getProperty("line.separator").getBytes());
                            }
                            if (ImprimiuOK != 1) {
                                break;
                            }
                        }
                      if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){
                         ecf.AcionarGuilhotina();
                         ImprimiuOK = ecf.ComprovanteNaoFiscal_Usar(ParteCompSITEF_ViasEstabelecimento);
                      }else{
                          try {
                              ImprimiuOK =AcionarGulhotinaImpComprovante();
                              
                              getImpressora_comprovante().Imprimir_Texto(ParteCompSITEF_ViasEstabelecimento.getBytes());
                              
                              ImprimiuOK=1;
                          } catch (Exception e) {
                              LogDinnamus.Log(e, true);
                              ImprimiuOK=0;
                          }                          
                      }
                    }         
                 }else{
                     if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){
                        ImprimiuOK = ecf.ComprovanteNaoFiscal_Usar(DadosImprimir);
                     }else{
                           try {                              
                              getImpressora_comprovante().Imprimir_Texto(DadosImprimir.getBytes());
                              ImprimiuOK=1;
                          } catch (Exception e) {
                              LogDinnamus.Log(e, true);
                              ImprimiuOK=0;
                          }
                     }
                 }
                 
                 //System.out.println("comprovante adm reg : " + ImprimiuOK);
                 if(!ImpressaoDireta){
                    InteracaoDuranteProcessamento.Mensagem_Terminar(); 
                 }
                 if(ImprimiuOK!=1){
                     int nRetResp= MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes_Sim_e_Nao(null, "A IMPRESSORA NÃO RESPONDE. DESEJA TENTAR NOVAMENTE? ", "IMPRESSÃO COMPROVANTE(IMP REL GERENCIAL)");
                     if(nRetResp!=MetodosUI_Auxiliares_1.Sim()){
                         //INTERROMPE A IMPRESSÃO
                         return 0;
                     }else{
                         return 2;
                     }
                 }else{   
                     if(/*nVia==1 &&*/ nVia <= nViasComp){
                           ImprimiuOK = Espacamento_Apos_Impressao(TipoComprovanteImpresso,  ecf);
                            if(ImprimiuOK!=1){
                                return 0;
                            }                             
                            if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){               
                                ecf.AcionarGuilhotina();
                            }else{
                                try {   
                                    
                                    ImprimiuOK =AcionarGulhotinaImpComprovante();
                                } catch (Exception e) {
                                    LogDinnamus.Log(e, true);
                                    ImprimiuOK = 0;
                                }
                            }
                            //TEFInteracao.MensagemTEF("TEF DINNAMUS ["+  Titulo +"]", "DESTAQUE A 1ª VIA DO COMPROVANTE\n\nVOCÊ TEM 5 (CINCO) SEGUNDOS PARA A IMPRESSÃO DA SEGUNDA VIA", 5000);
                         //}
                         ImprimiuOK=0;
                     }
                     nVia++;
                 }
            }                        
            //System.out.println("fechando comprovante adm ");/
              if(TipoComprovanteImpresso.equalsIgnoreCase("fiscal")){     
                ecf.ComprovanteNaoFiscal_Fechar();
              
              }
            
           // if(ImprimiuOK!=1){            
           //      return 0;
           // }else{
                return 1;
            //}

            //return ImprimiuOK; 
            
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return -1;
         } 
         
     }
     private static int Espacamento_Apos_Impressao(String TipoComprovanteImpresso, ECFDinnamuS ecf){
         int ImprimiuOK = 0;
         try {
             
             for (int i = 0; i < 6; i++) {
                 if (TipoComprovanteImpresso.equalsIgnoreCase("fiscal")) {
                     ImprimiuOK = ecf.ComprovanteNaoFiscal_Usar(" " + System.getProperty("line.separator"));
                 } else {
                     try {
                         String imp = " " + System.getProperty("line.separator");
                         getImpressora_comprovante().Imprimir_Texto(imp.getBytes());
                         ImprimiuOK = 1;
                     } catch (Exception e) {
                         LogDinnamus.Log(e, true);
                         ImprimiuOK = 0;
                     }
                 }
                 if (ImprimiuOK != 1) {
                     break;
                 }
             }

         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ImprimiuOK;
     }
     public static boolean ValidarAmbienteTEF(){
         try {
             
             if(!ManipulacaoArquivo.DiretorioExiste(PastaPadrao_Req)){
                 InteracaoDuranteProcessamento.Mensagem("TEF - Ambiente Inválido","Não foi localizado a pasta padrão de requisição ["+ getPastaPadrao_Req() +"]", 5000);
                 return false;
             }
             
              if(!ManipulacaoArquivo.DiretorioExiste(PastaPadrao_Resp)){
                 InteracaoDuranteProcessamento.Mensagem("TEF - Ambiente Inválido","Não foi localizado a pasta padrão de respostas ["+ getPastaPadrao_Resp() +"]", 5000);
                 return false;
             }
             if(ManipulacaoArquivo.ArquivoExiste(getNomeArquivoSTS(), false)){
                 ManipulacaoArquivo.ExcluirArquivo(getNomeArquivoSTS());
             }
             if(ManipulacaoArquivo.ArquivoExiste(getNomeArquivo001(), false)){
                 ManipulacaoArquivo.ExcluirArquivo(getNomeArquivo001());
             }
             if(ManipulacaoArquivo.ArquivoExiste(getNomeArquivo001_Resp(), false)){
                 // ARQUIVO INTPOS.001 NA PASTA. DESENVOLVER ROTINA DE CANCELAMENTO
                 ManipulacaoArquivo.ExcluirArquivo(getNomeArquivo001_Resp());
             }
             
             if(ManipulacaoArquivo.ArquivoExiste(getNomeArquivoTmp(), false)){
             
                 ManipulacaoArquivo.ExcluirArquivo(getNomeArquivoTmp());
             }
             
             
             return true;
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
         }
     }
     public static boolean VerificarGP(){
         boolean Retorno =false;
         try {
             
             InteracaoDuranteProcessamento.Mensagem_Iniciar("TEF DINNAMUS", "INICIANDO TEF....VERIFICANDO GP");
             //TEFInteracao.MensagemTEF_Iniciar("", "");
             
             Retorno = VerificarGP_Acao();             
             
             InteracaoDuranteProcessamento.Mensagem_Terminar();
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             
         }
         return Retorno;
     }
     
     public static boolean VerificarGP_Acao(){
         
         boolean Retorno =false;
         
         try {
                //String cNomeArquivoReq = "intpos.001"; 
                
                //LIMPAR STS ANTIGO
                if(!ManipulacaoArquivo.ArquivoExiste(NomeArquivoSTS, false)){
                    ManipulacaoArquivo.ExcluirArquivo(getNomeArquivoSTS());
                }
                
               // VERIFICANDO O GERENCIADOR PADRÃO
                
                String ConteudoArquivoVerificarGP = TEFConteudoArquivo.TEF_Conteudo_Arquivo_VerificarGP();
                String IDArquivoGP =TEFConteudoArquivo.Identificacao;
                if(ConteudoArquivoVerificarGP.length()>0){
                    boolean bIdentificouSTS =false;
                    boolean bTermina=false;
                    while(!bIdentificouSTS && !bTermina){
                            String cRetornoCriarArquivoGP =  TEF_CriarArquivo(getNomeArquivo001(),ConteudoArquivoVerificarGP);
                            Thread.sleep(1000);
                            System.out.print("CRIOU ARQUIVO REQ\n");
                            if(cRetornoCriarArquivoGP.length()>0){
                                // VERIFICAR RETORNO DA VERIFICACAO DO GP                                  
                                int nRetSTS = TEFAguardarArquivo(getNomeArquivoSTS(),IDArquivoGP,5000);
                                if(nRetSTS==1){
                                   //ACHOU O STS CORRETO
                                   bIdentificouSTS=true; 
                                }else if(nRetSTS==0){
                                    //INTERROMPE A LEITURA DO STS
                                    bTermina=true;
                                }                               
                            }else{                                
                                 InteracaoDuranteProcessamento.Mensagem("TEF DinnamuS", "NÃO É POSSÍVEL CRIAR O ARQUIVO INTPOS.001 DE VERIF. DO GP", 5000);
                                 return false;   
                            }
                     }
                      if(bIdentificouSTS){
                        //IDENTIFICOU O STS COM SUCESSO LENDO O ARQUIVO ATIVO.001
                        String RetornoAtivo001 =TEFValidarRetorno.VerificarArquivo_ATIVO_001();
                        if(RetornoAtivo001.length()>0){
                           TEF_GP_Ativo=RetornoAtivo001;
                           //System.out.print(RetornoAtivo001 + "\n");
                           Retorno= true;
                        }                             
                      }                    
                }else{
                     InteracaoDuranteProcessamento.Mensagem("TEF DinnamuS", "NÃO É POSSÍVEL INICIAR O ARQUIVO INTPOS.001 DE VERIF. DO GP", 5000);
                }    
             
          
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
          
         }
         return Retorno;
     }
     public static int TEFAguardarArquivo(String cNomeArquivoSTS,String IDArquivoGP, int Timeout){
         try {
            
               boolean bRetornoRetorno = TEFAguardaRetorno.VerificarArquivo_Iniciar(cNomeArquivoSTS,Timeout);
               
               System.out.print("INICIANDO THREAD DE VERIFICACAO DE RETORNO\n");
        
               while(TEFAguardaRetorno.Verificando){
                  // VERIFICAR O CONTEUDO DO ARQUIVO STS
                   System.out.print("AGUARDANDO RETORNO ["+ cNomeArquivoSTS +"] \n");
                   if(TEFAguardaRetorno.TimeoutExcedido){                      
                      if( MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes_Sim_e_Nao(null, "FOI EXECEDIDO O TEMPO DE ESPERA DO DINNAMUS.\n\nDESEJA CONTINUAR AGUARDANDO ?","TEF - Tempo Excedido")== MetodosUI_Auxiliares_1.Sim()){
                         TEFAguardaRetorno.VerificarArquivo_Interromper(); 
                      }else{
                          return 0;
                      }
                   }else{                                       
                        if(TEFAguardaRetorno.AchouArquivo){
                           System.out.print("ARQUIVO ["+ cNomeArquivoSTS +"] ENCONTRADO\n");
                           if(TEFValidarRetorno.VerificarIdentificacaoDoArquivo(cNomeArquivoSTS, IDArquivoGP)){
                              System.out.print("ARQUIVO [" +  cNomeArquivoSTS + "] OK\n");
                              //bIdentificouSTS=true;
                              TEFAguardaRetorno.VerificarArquivo_Interromper();
                              return 1;
                           }else{
                              //NÃO ACHOU O STS CERTO E CONTINUA A VERIFICACAO                                             
                              TEFAguardaRetorno.Verificando=true;
                           }
                        }
                   }
                  //Thread.sleep(1000);
                }
         } catch (Exception e) {
             LogDinnamus.Log(e, true);             
         }
         return -1;
     
     }
     public static boolean IniciarAdmTEF(ECFDinnamuS ecf,String TipoComprovanteImpresso){
        boolean Ret = false;
        
         try {
             //MetodosUI_Auxiliares.Tela_Bloquear(null, Float.NaN);
             Ret=IniciarAdmTEF_Acao(ecf, TipoComprovanteImpresso);
          
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
          
         }
        return Ret;
     }
     public static boolean IniciarAdmTEF_Acao(ECFDinnamuS ecf,String TipoComprovanteImpresso){
         try {
             HashMap<String, String> Retorno_Adm = IniciarAdmTEF_Acao();                          
             if(Retorno_Adm.size()>0){             
                String SituacaoVenda = Retorno_Adm.get("situacaovenda");
                if(SituacaoVenda.equalsIgnoreCase("aprovada")){
                    String MsgTEF = Retorno_Adm.get("msg");
                    String Imprimir = Retorno_Adm.get("imprimir");
                    String Rede = Retorno_Adm.get("rede");
                    String NSU = "";
                    if(Retorno_Adm.containsKey("nsu")){
                       NSU = Retorno_Adm.get("nsu");
                    }                    
                    String Finalizacao = "";
                    if(Retorno_Adm.containsKey("finalizacao")){
                       Finalizacao=Retorno_Adm.get("finalizacao");
                    }
                    int Retorno=0;
                    if(Imprimir.length()>0){
                        Retorno = ImprimirComprovanteTEF_Adm_Acao(Imprimir, ecf,MsgTEF, TipoComprovanteImpresso);
                    }else
                    {
                       Retorno=1;
                    }
                    if(NSU.length()>0 && Finalizacao.length()>0){
                        if(Retorno==1){
                           TEF_CONFIRMAR_VENDA("", Rede, NSU, Finalizacao);
                        }else{
                           boolean bRetorno_NCN = TEF_NAO_CONFIRMAR_VENDA("", Rede, NSU, Finalizacao);
                           if(bRetorno_NCN){
                               String MSG_NCN = "REDE        : " + Rede + "\n" +
                                                "NSU         : " + NSU + "\n"+
                                                "FINALIZAÇÃO : "+ Finalizacao ;
                               MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesINFO(null, MSG_NCN, "ÚLTIMA TRANSAÇÃO TEF CANCELADA(NCN)");
                           }
                        }
                    }
                }
             }             
             return true;
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
         }
     }        
     public static HashMap<String,String> IniciarAdmTEF_Acao(){        
        try {          
            if(!ValidarAmbienteTEF()){
                 Retorno = null;
            }else{                
                if(VerificarGP()){
                    InteracaoDuranteProcessamento.Mensagem_Iniciar("TEF DinnamuS", "INICIANDO ADMINISTRAÇÃO TEF");
                    RetornoVenda = TEF_AcionarGP();
                    String  MensagemAoOperador  = RetornoVenda.get("msg");
                    // EXIBIR MENSAGEM AO OPERADOR
                    InteracaoDuranteProcessamento.Mensagem_Terminar();
                    if(MensagemAoOperador.length()>0){                              
                       InteracaoDuranteProcessamento.Mensagem("TEF DinnamuS", MensagemAoOperador, 5000);
                    }
                    Retorno =RetornoVenda;                                    
                }else{
                    InteracaoDuranteProcessamento.Mensagem("TEF DinnamuS", "O TEF NÃO ESTA ATIVADO", 5000);
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);            
        }
        return Retorno;
    }
    public static HashMap<String,String> Transformar_DadosArquivoResp_Em_Hashmap(String cDadosIntpos001){
        HashMap<String,String> Retorno = new HashMap<>();
        try {
            String MensagemAoOperador="";
            //System.out.println(cDadosIntpos001);
            ArrayList<String> OperacaoTEF = MetodosComuns.ExtrairDados("000", cDadosIntpos001);
            ArrayList<String> CupomECf = MetodosComuns.ExtrairDados("002", cDadosIntpos001);
            ArrayList<String> IdentificacaoVenda = MetodosComuns.ExtrairDados("001", cDadosIntpos001);
            ArrayList<String> MsgAoOperador = MetodosComuns.ExtrairDados("030", cDadosIntpos001);
            //ArrayList<String> MsgAoOperadorEspecial = MetodosComuns.ExtrairDados("031", cDadosIntpos001);
            ArrayList<String> DadosParaImprimir = MetodosComuns.ExtrairDados("029", cDadosIntpos001);
            ArrayList<String> SituacaoVenda = MetodosComuns.ExtrairDados("009", cDadosIntpos001);
            ArrayList<String> REDE =MetodosComuns.ExtrairDados("010", cDadosIntpos001);
            ArrayList<String> NSU =MetodosComuns.ExtrairDados("012", cDadosIntpos001);
            ArrayList<String> FINALIZACAO =MetodosComuns.ExtrairDados("027", cDadosIntpos001);
            ArrayList<String> DATAOP =MetodosComuns.ExtrairDados("022", cDadosIntpos001);
            ArrayList<String> HORAOP =MetodosComuns.ExtrairDados("023", cDadosIntpos001);
            ArrayList<String> VALOROP =MetodosComuns.ExtrairDados("003", cDadosIntpos001);
            if(MsgAoOperador.size()>0){
                MensagemAoOperador=MetodosComuns.ConverterArrayListEmString(MsgAoOperador);
            }
            Retorno.put("operacao",MetodosComuns.ConverterArrayListEmString(OperacaoTEF));
            Retorno.put("identificacao",IdentificacaoVenda.get(0));
            Retorno.put("situacaovenda","aprovada");
            Retorno.put("msg",MensagemAoOperador);
            Retorno.put("imprimir", MetodosComuns.ConverterArrayListEmString(DadosParaImprimir));  
            Retorno.put("rede",REDE.get(0));
            if(NSU.size()>0){
                Retorno.put("nsu",NSU.get(0));
            }
            if(FINALIZACAO.size()>0){
               Retorno.put("finalizacao",FINALIZACAO.get(0));     
            }
            Retorno.put("dataop",DATAOP.get(0));
            Retorno.put("horaop",HORAOP.get(0));   
            if(VALOROP.size()>0){
                Retorno.put("valorop",VALOROP.get(0).replace(",", "").replace(".", ""));   
            }
            if(CupomECf.size()>0){
                Retorno.put("cupom",CupomECf.get(0));   
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    private static  HashMap<String,String> TEF_AcionarGP(){
         //ArrayList<String> Retorno = new ArrayList<>();
         HashMap<String,String> Retorno = new HashMap<>();
         //boolean Retorno = false;
         try {             
                String ConteudoArquivoTransacao = TEFConteudoArquivo.TEF_Conteudo_Arquivo_AcionarGP();
                String IDArquivoTRX = TEFConteudoArquivo.Identificacao;
                String cRetornoCriarArquivo =  MetodosComuns.CriarArquivo(getNomeArquivo001(),ConteudoArquivoTransacao);
                if(cRetornoCriarArquivo.length()>0){
                   // Criou arquivo transacao com sucesso. Iniciando o aguardo do retorno
                    InteracaoDuranteProcessamento.Mensagem_Terminar();
                    System.out.print("VERIFICANDO STS DA ADM\n");
                    Thread.sleep(2000);
                    int nRetSTS = TEFAguardarArquivo(getNomeArquivoSTS(),IDArquivoTRX,0);
                    ManipulacaoArquivo.ExcluirArquivo(getNomeArquivoSTS());
                    Thread.sleep(2000);
                    
                    if(nRetSTS==1){
                       //ACHOU O STS CORRETO. AGUARDA O ARQUIVO INTPOS.001
                        System.out.print("VERIFICANDO 001 DA ADM\n");
                        int nRet001 = TEFAguardarArquivo(getNomeArquivo001_Resp(),IDArquivoTRX,0);                        
                        if(nRet001==1){
                            
                           //ARQUIVO DE RESPOSTA INTPOS.001 DISPONIVEL
                           String MensagemAoOperador ="";
                           String cDadosIntpos001 = ManipulacaoArquivo.LerArquivo(getNomeArquivo001_Resp(),false);                            
                           System.out.println("Dados do arquivo de retorno");
                           System.out.println(cDadosIntpos001);
                           
                           ArrayList<String> NSU =MetodosComuns.ExtrairDados("012", cDadosIntpos001);
                           ArrayList<String> SituacaoVenda = MetodosComuns.ExtrairDados("009", cDadosIntpos001);
                           ArrayList<String> FINALIZACAO =MetodosComuns.ExtrairDados("027", cDadosIntpos001);
                           if("0".equals(SituacaoVenda.get(0).trim())){
                               if(NSU.size()>0 && FINALIZACAO.size()>0){
                                  SalvarArquivoRetorno(getNomeArquivo001_Resp());
                               }
                           }
                           
                           ManipulacaoArquivo.ExcluirArquivo(getNomeArquivo001_Resp());
                           //Gerar uma copia do arquivo de resposta
                           
                           ArrayList<String> MsgAoOperador = MetodosComuns.ExtrairDados("030", cDadosIntpos001);
                           ArrayList<String> MsgAoOperadorEspecial = MetodosComuns.ExtrairDados("031", cDadosIntpos001);
                           ArrayList<String> DadosParaImprimir = MetodosComuns.ExtrairDados("029", cDadosIntpos001);
                           
                           ArrayList<String> REDE =MetodosComuns.ExtrairDados("010", cDadosIntpos001);
                           
                           
                           ArrayList<String> DATAOP =MetodosComuns.ExtrairDados("022", cDadosIntpos001);
                           ArrayList<String> HORAOP =MetodosComuns.ExtrairDados("023", cDadosIntpos001);
                           ArrayList<String> VALOROP =MetodosComuns.ExtrairDados("003", cDadosIntpos001);
                           
                           if(MsgAoOperador.size()>0){
                             MensagemAoOperador=MetodosComuns.ConverterArrayListEmString(MsgAoOperador);
                           }
                           System.out.println(cDadosIntpos001);
                           if(MsgAoOperadorEspecial.size()>0){
                              MensagemAoOperador=MensagemAoOperador + "\n"+MetodosComuns.ConverterArrayListEmString(MsgAoOperadorEspecial);
                           }             
                           if("0".equals(SituacaoVenda.get(0).trim())){
                               Retorno.put("situacaovenda","aprovada");
                               Retorno.put("msg",MensagemAoOperador);
                               Retorno.put("imprimir", MetodosComuns.ConverterArrayListEmString(DadosParaImprimir));  
                               Retorno.put("rede",REDE.get(0));
                               if(NSU.size()>0){
                                  Retorno.put("nsu",NSU.get(0));
                               }
                               if(FINALIZACAO.size()>0){
                                  Retorno.put("finalizacao",FINALIZACAO.get(0));     
                               }
                               Retorno.put("dataop",DATAOP.get(0));
                               Retorno.put("horaop",HORAOP.get(0));  
                               if(VALOROP.size()>0){
                                   Retorno.put("valorop",VALOROP.get(0));   
                               }
                               
                           }else{
                               Retorno.put("situacaovenda","negada");
                               Retorno.put("msg",MensagemAoOperador);
                           }
                        }                       
                    }
                }else{
                    InteracaoDuranteProcessamento.Mensagem("TEF DinnamuS", "NÃO É POSSÍVEL CRIAR O ARQUIVO INTPOS.001 DE TRANSACAO ", 5000);
                }
            
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
             
         }
         return Retorno;
     }
    public static boolean ExcluirArquivoRetornoSalvo(){
        try {
             String Arquivo =DiretorioBackup() + "intpos.001";
             
             return ManipulacaoArquivo.ExcluirArquivo(Arquivo);            

        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
    public static String DiretorioBackup(){
        String Diretorio ="";
        try {
             String DiretorioBackup = getPastaPadrao_Resp() + "\\backup\\";
             boolean Ret = ManipulacaoArquivo.DiretorioExiste(DiretorioBackup);
             if(!Ret){
                 Ret =ManipulacaoArquivo.CriarDiretorio(DiretorioBackup);
             }else{
                Ret=true; 
             }
             if(Ret){
                 Diretorio = DiretorioBackup;
             }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Diretorio;
    }
    public static boolean SalvarArquivoRetorno(String Arquivo){
        
         try {
             String DiretorioBackup = DiretorioBackup();
             
             String ArquivoCopia =  DiretorioBackup +"intpos.001";
           
             ManipulacaoArquivo.Arquivo_Copiar(Arquivo, ArquivoCopia);
             
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
    public static String DadosArquivoResposta(){
        String DadosArquivoResposta="";
        try {
            DadosArquivoResposta = ManipulacaoArquivo.LerArquivo(DiretorioBackup() + "intpos.001" , false);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return DadosArquivoResposta;
    }
    public static boolean ExcluirArquivoTransacaoInterrompidaa(){
        try {
           
            return  ManipulacaoArquivo.ExcluirArquivo(getNomeArquivo001_Resp());
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
    public static boolean VerificarTransacaoInterrompida(){
        boolean bAchaou=false;
        try {
            bAchaou =TEFAguardaRetorno.VerificarArquivo(DiretorioBackup() + "intpos.001" );
                      
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return bAchaou;
    }

    /**
     * @return the CodigoPDV
     */
    public static int getCodigoPDV() {
        return CodigoPDV;
    }

    /**
     * @param aCodigoPDV the CodigoPDV to set
     */
    public static void setCodigoPDV(int aCodigoPDV) {
        CodigoPDV = aCodigoPDV;
    }

    /**
     * @return the PastaPadrao_Req
     */
    public static String getPastaPadrao_Req() {
        return PastaReq();
    }

    /**
     * @param aPastaPadrao_Req the PastaPadrao_Req to set
     */
    public static void setPastaPadrao_Req(String aPastaPadrao_Req) {
        PastaPadrao_Req = aPastaPadrao_Req;
    }

    /**
     * @return the PastaPadrao_Resp
     */
    public static String getPastaPadrao_Resp() {
        return PastaResp();
    }

    /**
     * @param aPastaPadrao_Resp the PastaPadrao_Resp to set
     */
    public static void setPastaPadrao_Resp(String aPastaPadrao_Resp) {
        PastaPadrao_Resp = aPastaPadrao_Resp;
    }

    /**
     * @return the NomeArquivo001
     */
    public static String getNomeArquivo001() {
        return PastaReq()+"intpos.001";
    }

    /**
     * @param aNomeArquivo001 the NomeArquivo001 to set
     */
    public static void setNomeArquivo001(String aNomeArquivo001) {
        NomeArquivo001 = aNomeArquivo001;
    }

    /**
     * @return the NomeArquivoTmp
     */
    public static String getNomeArquivoTmp() {
        return PastaReq()+"intpos.tmp";
    }

    /**
     * @param aNomeArquivoTmp the NomeArquivoTmp to set
     */
    public static void setNomeArquivoTmp(String aNomeArquivoTmp) {
        NomeArquivoTmp = aNomeArquivoTmp;
    }

    /**
     * @return the NomeArquivoSTS
     */
    public static String getNomeArquivoSTS() {
        return  PastaResp() + "intpos.sts";
    }

    /**
     * @param aNomeArquivoSTS the NomeArquivoSTS to set
     */
    public static void setNomeArquivoSTS(String aNomeArquivoSTS) {
        NomeArquivoSTS = aNomeArquivoSTS;
    }

    /**
     * @return the NomeArquivo001_Resp
     */
    public static String getNomeArquivo001_Resp() {
        return PastaResp()+"intpos.001";
    }

    /**
     * @param aNomeArquivo001_Resp the NomeArquivo001_Resp to set
     */
    public static void setNomeArquivo001_Resp(String aNomeArquivo001_Resp) {
        NomeArquivo001_Resp = aNomeArquivo001_Resp;
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

    /**
     * @return the _impressora_comprovante
     */
    public static ImpressoraCompravante getImpressora_comprovante() {
        return _impressora_comprovante;
    }

    /**
     * @param aImpressora_comprovante the _impressora_comprovante to set
     */
    public static void setImpressora_comprovante(ImpressoraCompravante aImpressora_comprovante) {
        _impressora_comprovante = aImpressora_comprovante;
    }
  

                
}
