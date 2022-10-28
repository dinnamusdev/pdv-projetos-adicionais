/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ecf;

//import br.com.ecf.sweda.CONVECF;



import bemajava.BemaString;
import bemajava.Bematech;
import br.com.FormatarNumeros;
import br.com.daruma.DarumaECF;
import br.com.daruma.UtilitarioDaruma;
import br.com.daruma.jna.ECF;



import br.com.ecf.bematech.BematechMetodosECFComplementares;
import br.com.ecf.sweda.CONVECF;
import br.com.ecf.sweda.Sweda_MetodosAuxiliares;
import br.com.epson.InterfaceEpson_2;
import br.com.epson.epsondll;
import br.com.epson.epsonmetodosadicionais;
import br.com.log.LogDinnamus;
import br.com.repositorio.DAO_RepositorioLocal;
import br.com.ui.MetodosUI_Auxiliares_1;
import br.data.DataHora;
import br.ui.frmCadastrarAliquota;
import br.ui.frmCadastrarPagto;
import br.valor.formatar.FormatarNumero;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import javax.swing.JOptionPane;

/**
 *
 * @author dti
 */
public class ECFDinnamuS {

    //Venda

    private String NomeECFSweda = "SWEDA MFD STxxxx";
    private String NomeECFBematech = "Bematech MP20 FII";
    private String NomeECFEpson = "EPSON TM-T81 FBIII";
    private String NomeECFDaruma32 = "Daruma Framework";
    private boolean bVerificacaoInicial=false;
    private String TipoECF;
    private String Error="";
    public String Porta ="";
    /**
     * @return the TipoECF
     */
     // Razao Social , cnpj, marca, modelo , tipo, n.serie mfd
     
     public HashMap<String,String> DadosDoECF(){
         HashMap<String,String>  ret = new HashMap<String, String>();         
         try {
              if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                  ret = Sweda_MetodosAuxiliares.DadosDoECF();
              }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                  ret = BematechMetodosECFComplementares.DadosDoECF();
              }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                  ret = epsonmetodosadicionais.DadosDoECF();
              }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                  ret = DarumaECF.DadosDoECF();
              }
             
             
         } catch (Exception e) {
             LogDinnamus.Log(e, true);
         }
         return ret;
     }
    public boolean ValidarMFD(Integer CodigoPDV){
        boolean Ret = false;
        try {
            ResultSet rs = DAO_RepositorioLocal.GerarResultSet("select ecf_serie_mfd from caixas where pdvoff=" + CodigoPDV );
            if(rs.next()){
               String MFDConfig = rs.getString("ecf_serie_mfd");                 
               String MFDEcf = "";
               if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                   MFDEcf= Sweda_MetodosAuxiliares.DadosDoECF_NumSerieMFD();
               }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                   MFDEcf = BematechMetodosECFComplementares.DadosDoECF_NumSerieMFD();
               }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                   MFDEcf = epsonmetodosadicionais.DadosDoECF_NumSerieMFD();    
               }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                    MFDEcf = DarumaECF.DadosDoECF_NumSerieMFD();    
                }
               if(MFDConfig.trim().equalsIgnoreCase(MFDEcf.trim())){
                   Ret=true;                   
               }
            }            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
    public boolean AtualizarDiaAberto(String DataMov){
        try {
              //System.out.println("update off_configuracaoestacao set diaaberto=" + (DataMov==null ? "null"  : "'"+ DataMov +"'" ));
              if(DAO_RepositorioLocal.ExecutarComandoSimples("update off_configuracaoestacao set diaaberto=" + (DataMov==null ? "null"  : "'"+ DataMov +"'" ), true)){
                return true;
              }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);            
        }
        return false;
    }
    public boolean VerificacaoInicial(){
        return VerificacaoInicial(0,false,false,false);
    }
    public boolean VerificacaoInicial(Integer CodigoPDV, boolean  IgnorarZPendente, boolean IgnorarZJaEmitida, boolean IgnorarDiaFechado){
            
            boolean bRet=true;      
            System.out.println("ECF : " + TipoECF);
            System.out.println("VERIFICACAO INCIAL : 1 ");
            
            if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
               if(!Sweda_MetodosAuxiliares.isPortaOk()){
                  Sweda_MetodosAuxiliares.IniciarECF();
               }
            }
            if(ExibirMsgImpressoras(true, true,false,CodigoPDV,IgnorarZPendente,IgnorarZJaEmitida,IgnorarDiaFechado)){
                return false;
            }   
            
            if(!ValidarMFD(CodigoPDV)){
                MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesAVISO("MFD DO ECF É DIFERENTE DA CONFIGURAÇÃO DESTE PDV\n\nVERIFIQUE AS CONFIGURAÇÕES FISCAIS DO PDV", "MEMÓRIA FITA DETALHE(MFD) NÃO IDENTIFICADA");
                return false;
            }
            
            if(!isbVerificacaoInicial()){
                try {
                    System.out.println("VERIFICACAO INICIAL : 2");
                    if(ZPendente()){
                        String cDataMovimento=DataMovimento();
                        int nRet =MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes(null, "Existem uma redução Z Pendente do dia: [ " + cDataMovimento + " ].\n\n Deseja encerrar o dia", "Z Pendente",JOptionPane.YES_NO_OPTION ,JOptionPane.QUESTION_MESSAGE);
                        if(nRet==JOptionPane.YES_OPTION){
                           CancelarQualquerComprovanteAberto();
                           if(FechamentoDoDia()==1){
                             int nRetDiaFiscal = MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes_Sim_e_Nao(null, "O dia fiscal ainda não foi aberto. \nDESEJA INICIAR O DIA FISCAL AGORA ?", "Dia Fiscal Fechado");
                             if(nRetDiaFiscal==MetodosUI_Auxiliares_1.Sim()){
                                if(AberturaDoDia("", "" )==1){
                                   bRet=true;
                                 }
                              }
                           }
                        }else{
                            return false;
                        }
                    }
                    System.out.println("VERIFICACAO INCIAL : 3");
                    String VerificarZ = VerificaZJaEmitida();
                    if(!VerificarZ.equalsIgnoreCase("")){
                       MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesAVISO(null, VerificarZ + "\n\nMODO FISCAL BLOQUEADO", "REDUÇÃO Z JÁ EMITIDA");
                       return false;
                    }
                    System.out.println("VERIFICACAO INCIAL : 4");
                    int nRet =VerificaDiaAberto(CodigoPDV);
                    if(nRet==0){
                       int nRetDiaFiscal = MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes_Sim_e_Nao(null, "O dia fiscal ainda não foi aberto. \nDESEJA INICIAR O DIA FISCAL AGORA ?", "Dia Fiscal Fechado");
                       if(nRetDiaFiscal==MetodosUI_Auxiliares_1.Sim()){
                          CancelarQualquerComprovanteAberto();
                          if(AberturaDoDia("", "" )==1){
                             bRet=true;
                          }
                       }else{
                          return false;
                       }
                    }/*else if(nRet==1){
                        if(!ExibirMsgImpressoras(true,false,false,CodigoPDV)){
                           setbVerificacaoInicial(true);
                           bRet=true;
                        }
                    }*/
                            
                     bRet=true;
                     
                } catch (Exception e) {
                     LogDinnamus.Log(e);
                }
            }else{
                if(!ExibirMsgImpressoras(true,false,true,CodigoPDV,IgnorarZPendente, IgnorarZJaEmitida, IgnorarDiaFechado)){
                    bRet=true;
                }                
            }
            return bRet;
    }
    public boolean ExibirMsgImpressoras(boolean bExibirSomenteErros,  boolean bIgnorarErrosOperacionais, boolean VerificacaoInicial, int CodigoPDV,boolean IgnorarZPendente, boolean IgnorarZJaEmitida, boolean IgnorarDiaFechado){
        boolean bRet=false;
        String cMsgExibir="";
        try {
          
            boolean Ignorar = false;
                    
            ArrayList<String> alMsg =  PalavraStatus(VerificacaoInicial);
            if(alMsg.size()>0){
                for (int i = 0; i < alMsg.size(); i++) {                
                     Ignorar=false;
                    if(bExibirSomenteErros){
                      
                        if(alMsg.get(i).indexOf("#")>=0){
                            if(IgnorarZPendente && alMsg.get(i).contains("REDUCAO Z PENDENTE")){
                               Ignorar=true;
                            }else if(IgnorarDiaFechado && alMsg.get(i).contains("DIA FISCAL FECHADO")){
                               Ignorar=true;
                             }else if(IgnorarZJaEmitida && alMsg.get(i).contains("REDUCAO Z EMITIDA")){
                               Ignorar=true;  
                            } 
                            //
                            if(bIgnorarErrosOperacionais){
                                if(!alMsg.get(i).contains("REDUCAO Z EMITIDA") || !alMsg.get(i).contains("REDUCAO Z PENDENTE") || !alMsg.get(i).contains("DIA FISCAL FECHADO")){                                                                   
                                     if(!Ignorar){
                                       cMsgExibir += alMsg.get(i).replace("#", "").toUpperCase() +"\n";
                                     }
                                }      
                            }else{                                
                                if(!Ignorar){
                                    cMsgExibir += alMsg.get(i).replace("#", "").toUpperCase() +"\n";
                                }
                            }
                        }
                    }else
                    {
                         cMsgExibir += alMsg.get(i).toUpperCase() +"\n";
                    }

                    //cMsgExibir += (bExibirSomenteErros ?  (alMsg.get(i).indexOf("#")>=0 ?  alMsg.get(i) : ""  )  : alMsg.get(i)).replaceAll("#", "");
                }               
            }else{
                cMsgExibir="ECF NÃO RESPONDE";
            }

        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        if(cMsgExibir.length()>0){
           MetodosUI_Auxiliares_1.MensagemAoUsuarioSimples(null, cMsgExibir, "Mensagens do ECF", "AVISO");
           bRet=true;
        }
        return bRet;
    }
    public String getTipoECF() {
        
        return this.TipoECF;
    }
    
    /**
     * @param TipoECF the TipoECF to set
     */
    public void setTipoECF(String TipoECF, String Porta) {
        
        this.TipoECF = TipoECF;        
        if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
            epsondll.NomePorta = Porta;
        }
    }
    public int FecharPorta(){
           int nRet=0;
           
                   
                   // getNomeECFBematech()
           if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                 CONVECF.INSTANCE.ECF_FechaPortaSerial();
           }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                bemajava.Bematech.FechaPortaSerial();
           }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
               nRet = epsondll.getInstance().EPSON_Serial_Fechar_Porta();
               epsondll.PortaStatus=false;
               if(nRet==0){
                   nRet =1;
                   
               }else{
                   nRet =0;
               }
           }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
               //DarumaECF
               //nRet = 
//               DarumaECF.getECF().
           
           }
               
           
           return nRet;
    }
    public int AbreCupom(String str){
           int nRet=0;
           
                   
                   // getNomeECFBematech()
           System.out.println("Abrir Cupom  : " + TipoECF);
           if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
               nRet= CONVECF.INSTANCE.ECF_AbreCupom(str);
           }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
               nRet = bemajava.Bematech.AbreCupom(str);
           }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
               nRet = epsondll.getInstance().EPSON_Fiscal_Abrir_Cupom( str, "", "", "", 1);
               if(nRet==0){
                   nRet =1;
               }else{
                   nRet =0;
               }
           }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
               if(DarumaECF.OK()){
                   nRet=  ECF.iCFAbrir(str, "", "");                   
                   nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;                    
               }
           }
               
            System.out.println("Ret Abrir Cupom  : " + nRet);
           return nRet;
    }
    public int LeituraX(){
            int nRet=0;
            
           //MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, getNomeECFBematech() + " " + TipoECF, "Teste", "AVISO");
            
           if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
               nRet= CONVECF.INSTANCE.ECF_LeituraX();
           }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
            int LeituraX = bemajava.Bematech.LeituraX();
           }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
            int LeituraX = epsondll.getInstance().EPSON_RelatorioFiscal_LeituraX();
             if(nRet==0){
                   nRet =1;
               }else{
                   nRet =0;
               }
          }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                   if(DarumaECF.OK()){
                       nRet = ECF.iLeituraX();
                       nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                   }
           }
           return nRet;
    }
    public int ReducaoZ(String cDataInicial , String cDataFinal){
            int nRet=0;
            int ReducaoZ=0;
           if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
               nRet= CONVECF.INSTANCE.ECF_ReducaoZ(cDataInicial, cDataFinal);
           }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
               ReducaoZ = bemajava.Bematech.ReducaoZ("", "");
           }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
               ReducaoZ = epsondll.getInstance().EPSON_RelatorioFiscal_RZ("", "",2,"");
           }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                  if(DarumaECF.OK()){
                      nRet = ECF.iReducaoZ("", "");
                      nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;                      
                  }
            }
           return nRet;
    }
    public int FechamentoDoDia(){
            int nRet=0;
             int ReducaoZ =0;
           CancelarQualquerComprovanteAberto();
           
           if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
               nRet= CONVECF.INSTANCE.ECF_FechamentoDoDia();
           }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                ReducaoZ = bemajava.Bematech.ReducaoZ("", "");
               if(ReducaoZ==1){
                  AtualizarDiaAberto(null);
               }
          }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
               ReducaoZ = epsondll.getInstance().EPSON_RelatorioFiscal_RZ("", "",2,"");
               if(ReducaoZ==0){
                   ReducaoZ =1;
               }else{
                   ReducaoZ =0;
               }
          }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                if(DarumaECF.OK()){
                   ReducaoZ= ECF.iReducaoZ("", "");
                   ReducaoZ = DarumaECF.TratarRetornoErroDaruma(ReducaoZ) ? 1 : 0 ;
                   if(ReducaoZ==1){
                      AtualizarDiaAberto(null);
                   }
                }
           }
           return nRet;
    }
    public boolean DadosDaAbertura(String Operador, String Caixa, String PDV){
        try {
            String DadosAbertura ="===================================\n" + 
                                  "ABERTURA DO CAIXA\n" +
                                  "===================================\n" +
                                  "OPERADOR  : "+  Operador  +"\n" +
                                  "CAIXA     : " +  Caixa +"\n" +
                                  "PDV       : "+  PDV +"\n" +
                                  "DATA/HORA : "+  DataHora.getData(DataHora.FormatDataPadrao) + " AS "  + DataHora.getHora(DataHora.FormatHoraPadrao) + "\n" +
                                  "===================================\n"  ;
            
            
            ComprovanteNaoFiscal_Usar(DadosAbertura);
            ComprovanteNaoFiscal_Fechar();
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }    
    }
    
    public int AberturaDoDia(String cValor, String cFinalizadar){
           int nRet=0;
           if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
               nRet= CONVECF.INSTANCE.ECF_AberturaDoDia(cValor, cFinalizadar);
           }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                nRet = BematechMetodosECFComplementares.AbrirMovimento();  
                if(nRet==1){
                    String DataMov = BematechMetodosECFComplementares.DataECF(); 
                    if(!AtualizarDiaAberto(DataMov)){
                       nRet=0;  
                    }
                }
           }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
               nRet = epsondll.getInstance().EPSON_RelatorioFiscal_Abrir_Jornada();
               if(nRet==0){
                   nRet =1;
               }else{
                   nRet =0;
               }
           }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
               if(DarumaECF.OK()){
                   nRet = DarumaECF.AbrirMovimento();                  
                   
                   nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;

                   String DataMov = DarumaECF.DataAtualECF(false);
                   if(!AtualizarDiaAberto(DataMov)){
                       nRet=0;  
                   }else{
                       nRet=1;
                   }                  
                    
                  
               }
           }
           return nRet;
    }
    /*
    public int ComprovanteNaoFiscal_Abrir(String Forma , Float Valor){
        int nRet =0;
        try {
            
            if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                String cValor = FormatarNumeros.FormatarParaMoeda(Valor);
                nRet= CONVECF.INSTANCE.ECF_AbreComprovanteNaoFiscalVinculado( Forma, cValor, "");
           }            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return nRet;    
    }*/
    public static void main(String[] args) {
       LogDinnamus.Iniciar();
       bemajava.Bematech.LeituraX();
        //br.com.ecf.bematech.Bematech.iniciar();
        //br.com.ecf.bematech.Bematech.LeituraX();
        /*LogDinnamus.Iniciar();
        ECFDinnamuS ecfds = new ECFDinnamuS();
        ecfds.setTipoECF(ecfds.getNomeECFSweda(), "COM1");
        //CONVECF.INSTANCE.ECF_AbrePortaSerial();
        //for (int i = 0; i < 3; i++) {
           BematechMetodosECFComplementares 
        
        ecfds.AbreCupom("TESTE");
        ecfds.VendeItem("01", "PRODUTO DE TESTE", "1700", "I", 10d, 2, 1d, "$", "0", "UN");
        ecfds.VendeItem("01", "PRODUTO DE TESTE", "1700", "I", 10d, 2, 1d, "$", "0", "UN");
        ecfds.IniciaFechamentoCupom("D", "$", 20f);
        ecfds.EfetuarFormaPagto("CHEQUE", 20f);
        ecfds.TerminaFechamento(0f, "");
        ecfds.CNFV_Abrir("CHEQUE", "", 20f);
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 1");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 2");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 3");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 1");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 2");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 3");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 1");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 2");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 3");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 1");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 2");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 3");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 1");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 2");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 3");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 1");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 2");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 3");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 1");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 2");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 3");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 1");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 2");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 3");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 1");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 2");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 3");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 1");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 2");
        ecfds.CNFV_Usar("TESTE DE COMPROVANTE 3");        
        ecfds.CNFV_Fechar();
        
        //}
        ecfds.FecharPorta();*/
        
    }
    public ArrayList<String> QuebrarTextoEmPedacos(String cTexto, int nTamanho){
        ArrayList<String> arPartes  = new ArrayList<String>();
        try {
            if(cTexto.length()>600){
                String[] arTexto =  cTexto.split("\n");
                String cTexto600="";
                for (int i = 0; i < arTexto.length; i++) {
                    if(cTexto600.length() + arTexto[i].length()>nTamanho){
                       //if(cTexto600.length()>0){                      
                          //cTexto600 +=  System.getProperty("line.separator") ;                   
                          arPartes.add(cTexto600);
                          cTexto600= arTexto[i] + System.getProperty("line.separator");
                       //}
                    }else{
                       cTexto600 =cTexto600+ arTexto[i] + System.getProperty("line.separator");                   
                    }
                }
                if(cTexto600.length()>0){
                    arPartes.add(cTexto600);
                }
            }else{
                arPartes.add(cTexto);
            }
             
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return arPartes;
    }
    public String UltimoCupom(){
         String Retorno="";
         try {
             
            if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){              
                bemajava.BemaString NumeroCupom= new bemajava.BemaString() ;
                NumeroCupom.buffer= "       ";
                bemajava.Bematech.NumeroCupom(NumeroCupom);
                Retorno = NumeroCupom.getBuffer(); 
            }else  if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){             
                byte[] cupom = {0,0,0,0,0,0};
                CONVECF.INSTANCE.ECF_NumeroCupom(cupom);
                Retorno  =  new String(cupom);
            }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                   // Retorno =DarumaECF.getDLLDaruma().
                Retorno = epsonmetodosadicionais.UltimoCupom();
            }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                if(DarumaECF.OK()){
                    Retorno =DarumaECF.UltimoCOO();
                }
            }
            
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    
    
   public boolean ECF_OK(){
        try {
           ArrayList<String> retorno = StatusECF();
           if(retorno.size()>0){
               if (retorno.size()==1 && retorno.contains("POUCO PAPEL")) {
                   return true;
               }else{               
                   return false;
               }
           }else{
               return true;
           }           
           
       } catch (Exception e) {
           LogDinnamus.Log(e, true);
           return false;
       }
   } 
    
    public ArrayList<String> StatusECF(){
          ArrayList<String> Retorno = new ArrayList<String>();
         try {
             
            if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){              
               
                 Retorno = BematechMetodosECFComplementares.StatusECF_2();
            }
            
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public int CNFV_Abrir(String FormaPagto,String COO, Double Valor){
        int nRet=0;
        try {
            
            if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                  String cValor = FormatarNumeros.FormatarParaMoeda(Valor);
                  nRet = CONVECF.INSTANCE.ECF_AbreComprovanteNaoFiscalVinculadoMFD(FormaPagto,cValor , "","","","");

             }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                 String cValor = FormatarNumeros.FormatarParaMoeda(Valor);
                 nRet = bemajava.Bematech.AbreComprovanteNaoFiscalVinculado(FormaPagto, cValor,COO);
            }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                String cValor = FormatarNumeros.FormatarParaMoeda(Valor).replace(",","").replace(".","");
                 int nRetepsonAbrir = epsondll.getInstance().EPSON_NaoFiscal_Abrir_CCD(FormaPagto, cValor,2,"1");
                 if(nRetepsonAbrir==0){
                     nRet=1;
                 }else{
                    nRet=0; 
                 }
            }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                if(DarumaECF.OK()){
                   String cValor = FormatarNumeros.FormatarParaMoeda(Valor).replace(",","").replace(".","");
                   nRet= ECF.iCCDAbrir(FormaPagto, "1", COO, cValor, "", "", "");
                   nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                }
            }
            
        } catch (Exception e) {
                LogDinnamus.Log(e, true);
        }
        return nRet;
    
    }
    public int CNFV_Usar(String cTexto){
        int nRet=0;
        try {
            
             ArrayList<String> TextoEmPartes = QuebrarTextoEmPedacos( cTexto, 600);
             
             for (int i = 0; i < TextoEmPartes.size(); i++) {
                  if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                        nRet = CONVECF.INSTANCE.ECF_UsaComprovanteNaoFiscalVinculadoTEF(TextoEmPartes.get(i));
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                       nRet = bemajava.Bematech.UsaComprovanteNaoFiscalVinculado(TextoEmPartes.get(i));                           
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                         
                        String Linhas= TextoEmPartes.get(i);
                        int nRetepson = epsondll.getInstance().EPSON_NaoFiscal_Imprimir_LinhaEX(Linhas);
                        if(nRetepson==0){
                            nRet=1;
                        }else{
                            nRet=0;
                            break;
                        }
                        
                     }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                         if(DarumaECF.OK()){
                            nRet= ECF.iCCDImprimirTexto(TextoEmPartes.get(i));
                            nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                            if(nRet!=1){
                                break;
                            } 
                         }
                     }
             }            
        } catch (Exception e) {
                LogDinnamus.Log(e, true);
        }
        return nRet;    
    }
     public int CNFV_Fechar(){
        int nRet=0;
        try {           
            
             if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                    nRet = CONVECF.INSTANCE.ECF_FechaComprovanteNaoFiscalVinculado();
             }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                   nRet = bemajava.Bematech.FechaComprovanteNaoFiscalVinculado();
             }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){             
                   nRet = epsondll.getInstance().EPSON_NaoFiscal_Fechar_CCD(true);
                    if(nRet==0){
                      nRet =1;
                    }else{
                        nRet =0;
                    }
              }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                  if(DarumaECF.OK()){
                    nRet = ECF.iCCDFechar();
                    nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                  }
              }
            
            
        } catch (Exception e) {
                LogDinnamus.Log(e, true);
        }
        return nRet;
    
    }
    public int AcionarGuilhotina(){
        int nRet=0;
        try {
               if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){               
                    nRet = epsondll.getInstance().EPSON_Impressora_Cortar_Papel();
                   if(nRet==0){
                        nRet =1;
                    }else{
                        nRet =0;
                    }
               }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){

                   nRet= bemajava.Bematech.AcionaGuilhotinaMFD(0);

               }else if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                   nRet= CONVECF.INSTANCE.ECF_AtivaDesativaCorteProximoMFD(1);

                }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                    if(DarumaECF.OK()){
                        nRet = ECF.eAcionarGuilhotina("1");
                        nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ; 
                    }
                }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return nRet;
    }
     public int ComprovanteNaoFiscal_Abrir(){
         int nRet=0; 
        try {
            ArrayList<String> StatusEstendidoECF = StatusECF_Estentido();
            if(!StatusEstendidoECF.contains("RG ABERTO") || StatusEstendidoECF.size()==0){
                if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){               
                    nRet = epsondll.getInstance().EPSON_NaoFiscal_Abrir_Relatorio_Gerencial("1");
                    if(nRet==0){
                        nRet =1;
                    }else{
                        nRet =0;
                    }
               }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){

                     nRet = bemajava.Bematech.AbreRelatorioGerencialMFD("01");

               }else if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                    nRet =  CONVECF.INSTANCE.ECF_AbreRelatorioGerencial();

                }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                    if(DarumaECF.OK()){
                        nRet = ECF.iRGAbrirPadrao();
                        nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                    }
                }
           }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return nRet;
    }
    public boolean ComprovanteNaoFiscal_Usar_2(String cTexto){ 
        boolean Ret = false;
        try {
            int nRet =ComprovanteNaoFiscal_Abrir();
            if(nRet==1){
               nRet=ComprovanteNaoFiscal_Usar(cTexto);               
               nRet = ComprovanteNaoFiscal_Fechar();
               if(nRet ==1){
                   Ret = true;
               }
            }            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
    public int ComprovanteNaoFiscal_Usar(String cTexto){
        int nRet=0;
        try {
            
            //ArrayList<String> arPartes  = new ArrayList<String>();
            //String[] arTexto =  cTexto.split(System.getProperty("line.separator"));
            //String cTexto600="";
            ArrayList<String> arPartes = QuebrarTextoEmPedacos(cTexto, 600);
            
           
            for (int i = 0; i < arPartes.size(); i++) {
 
                    if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                       nRet= CONVECF.INSTANCE.ECF_RelatorioGerencial(arPartes.get(i));
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                       nRet = bemajava.Bematech.UsaRelatorioGerencialMFD(arPartes.get(i));
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                         String[] Linhas= arPartes.get(i).split(System.getProperty("line.separator"));
                         for (int j = 0; j < Linhas.length; j++) {
                            String Linha = Linhas[j] + System.getProperty("line.separator");
                            int nRetepson = epsondll.getInstance().EPSON_NaoFiscal_Imprimir_LinhaEX(Linha);
                            if(nRetepson==0){
                                nRet= 1;
                            }else{
                                nRet=0;
                                break;
                            }
                        }                       
                     }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                         if(DarumaECF.OK()){
                            LogDinnamus.Informacao("IMPRIMINDO RG : \n" + arPartes.get(i));
                            nRet =  ECF.iRGImprimirTexto(arPartes.get(i));
                            nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                            LogDinnamus.Informacao("Retorno : " + nRet);
                            if(nRet!=1){
                                //if(nRet!=0){
                                    LogDinnamus.Informacao("Testando o retorno");
                                    DarumaECF.TratarRetornoErroDaruma(nRet);
                                    //LogDinnamus.Info("retorno");
                                //}
                                break;
                            }
                         }
                     }
            
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return nRet;
    }
    
    public int ComprovanteNaoFiscal_Fechar(){
        int nRet=0;
        try {
            
            if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
               nRet= CONVECF.INSTANCE.ECF_FechaRelatorioGerencial();
           }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
               nRet = bemajava.Bematech.FechaRelatorioGerencial();               
           }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
               nRet = epsondll.getInstance().EPSON_NaoFiscal_Fechar_Relatorio_Gerencial(true);
               if(nRet==0){
                   nRet =1;
               }else{
                   nRet =0;
               }
           }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
               if(DarumaECF.OK()){
                  nRet = ECF.iRGFechar();
                  nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                  //System.out.println("fechando rg : " + nRet);
               }
           }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return nRet;
    }
    
    public int VendeItem(String cod, String nome, String aliq, String tipoqt, Double quant, int casas, Double unit, String tpdesc, Double valdesc, String unidade){
            int nRet=0;
            try {
                    System.out.println("ECF:" +TipoECF);
                   if(!TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                        if (tipoqt.trim().equalsIgnoreCase("F") ){
                            quant = quant * 10;
                        }
                   }
 
                   String cQuant = "";
                   if (tipoqt.trim().equalsIgnoreCase("F") ){
                       cQuant = FormatarNumeros.FormatarParaMoeda(quant);
                   }else{
                       cQuant = (new DecimalFormat((tipoqt.equals("F") ? "##.###" : "#" ) )).format(quant.floatValue()).toString();
                   }
                   String cPreco =   FormatarNumeros.FormatarParaMoeda(unit);
                   String cDesconto =   FormatarNumeros.FormatarParaMoeda(valdesc);

                   //LogDinnamus.Info("Dados do produto :" +  cod + " nome :" + nome + " aliq :" + aliq + " tipoqt : " + tipoqt + " quant : " + cQuant + " unit : " + cPreco);
                   
                   if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){

                       if(nome.length()>26){
                           CONVECF.INSTANCE.ECF_AumentaDescricaoItem(nome);
                           nome=nome.substring(0, 26);
                       }
                       if (aliq.equalsIgnoreCase("0")) {
                           aliq="1700";
                       }
                       nRet= CONVECF.INSTANCE.ECF_VendeItem(cod,  nome, aliq,  tipoqt, cQuant,  casas,  cPreco,  tpdesc,  cDesconto);
                       setError("");
                       if(nRet!=1) {
                           if(nRet==0){
                               setError("Erro de comunicação");
                           }else if(nRet==-2){
                               setError("Parâmetro inválido na função");
                           }else if(nRet==-3){
                               setError("Alíquota não programada");
                           }else{
                               setError("Status do ECF diferente de 6,0,0,0 (ACK,ST1,ST2 e ST3");
                           }
                       }

                   }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                       if(nome.length()>26){
                           bemajava.Bematech.AumentaDescricaoItem(nome);
                           nome=nome.substring(0, 26);
                       }
                 
                       if (tipoqt.trim().equalsIgnoreCase("F") ){
                          cQuant = FormatarNumeros.FormatarParaMoeda_3Casas(quant/10d);
                          //cPreco=FormatarNumeros.FormatarParaMoeda(unit*100);
                          //unit=unit*10f;
                          cPreco=FormatarNumeros.FormatarParaMoeda(unit*10);
                       }else{
                          cQuant = FormatarNumeros.FormatarParaMoeda(quant*10);
                          cPreco=FormatarNumeros.FormatarParaMoeda(unit*10);
                       }
                       cDesconto=FormatarNumeros.FormatarParaMoeda(valdesc*10);
                       //cDesconto=FormatarNumeros.FormatarParaMoeda(unit*10);
                       
                       nRet=bemajava.Bematech.VendeItemDepartamento(cod, nome, aliq, cPreco, cQuant, "0", cDesconto, "01", unidade);
                       //nRet=Bematech_2.INSTANCE.VendeItem(cod,  nome, aliq,  tipoqt, cQuant,  casas,  cPreco,  tpdesc,  valdesc); 
                       setError("");
                       if(nRet!=1) {
                           if(nRet==0){
                               setError("Erro de comunicação");
                           }else if(nRet==-2){
                               setError("Parâmetro inválido na função");
                           }else if(nRet==-3){
                               setError("Alíquota não programada");
                           }else{
                               setError("Status do ECF diferente de 6,0,0,0 (ACK,ST1,ST2 e ST3");
                           }
                       }
                      }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                          if (tipoqt.trim().equalsIgnoreCase("F") ){
                                cQuant = FormatarNumeros.FormatarParaMoeda_3Casas(quant/10d);                              
                                cPreco=FormatarNumeros.FormatarParaMoeda(unit);
                            }else{
                               cQuant = FormatarNumeros.FormatarParaMoeda(quant);
                               cPreco=FormatarNumeros.FormatarParaMoeda(unit);
                            }
                          cDesconto=FormatarNumeros.FormatarParaMoeda(valdesc);
                          cDesconto = cDesconto.replace(",", "").replace(".", "");
                          cPreco = cPreco.replace(",", "").replace(".", "");
                          cQuant = cQuant.replace(",", "").replace(".", "");
                          aliq = aliq.replace(",", "").replace(".", "");
                          
                         nRet = epsondll.getInstance().EPSON_Fiscal_Vender_Item(cod, nome, cQuant, (tipoqt.trim().equalsIgnoreCase("F") ? 3 : 2 ),
                                    unidade,cPreco , 2, aliq, 1);
                       
                         if(nRet !=0){                          
                             setError(epsonmetodosadicionais.MensagemErro(false));
                             nRet=0;
                         }else if(nRet==0){
                             if(valdesc>0d){
                                 nRet = epsondll.getInstance().EPSON_Fiscal_Desconto_Acrescimo_Item(cDesconto, 2,true, false);
                                 if (nRet != 0) {
                                     setError(epsonmetodosadicionais.MensagemErro(false));
                                     nRet = 0;
                                 }else{
                                     nRet =1;
                                 }
                                 
                             }
                             nRet=1;
                         }
                      }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                          if(DarumaECF.OK()){
                              
                            if (tipoqt.trim().equalsIgnoreCase("F") ){
                                cQuant = FormatarNumeros.FormatarParaMoeda_3Casas(quant/10d);                              
                                cPreco=FormatarNumeros.FormatarParaMoeda(unit);
                            }else{
                               cQuant = FormatarNumeros.FormatarParaMoeda(quant);
                               cPreco=FormatarNumeros.FormatarParaMoeda(unit);
                            }
                             //valdesc=FormatarNumeros.FormatarParaMoeda(valdesc);
                              cDesconto=FormatarNumeros.FormatarParaMoeda(valdesc);
                              cPreco = cPreco.replace(".", "");
                              cQuant = cQuant.replace(".", "");
                              cDesconto = cDesconto.replace(".", "");
                              
                              if(!aliq.equalsIgnoreCase("II") && !aliq.equalsIgnoreCase("FF") && !aliq.equalsIgnoreCase("NN")){
                                 aliq = "T"  + aliq.replace(".", ",");  
                              }
                              if(unidade.trim().length()==0){
                                  unidade="UN";
                              }
                              
                              nRet =  ECF.iCFVender(aliq, cQuant, cPreco, "D$", cDesconto, cod, unidade, nome);
                              nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                              //UtilitarioDaruma.validaFiscal(nRet, true, true);
                              //System.out.println(nRet);
                          }
                      }
            } catch (Exception e) {
                    LogDinnamus.Log(e);
            }

            return nRet;
    }
    public int ConcederDescontoItem(Integer Seq, Float nValorDesconto){
        int nRet=0;
        try {
              
               if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                  String cDesconto=FormatarNumeros.FormatarParaMoeda(nValorDesconto); 
                  nRet= CONVECF.INSTANCE.ECF_DescontoSobreItemVendido(Seq.toString(), "$", cDesconto);
               }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                   String cPreco=FormatarNumeros.FormatarParaMoeda(nValorDesconto);
                   nRet=bemajava.Bematech.AcrescimoDescontoItemMFD(Seq.toString(), "D", "$", cPreco);
               }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                   String cDesconto=FormatarNumeros.FormatarParaMoeda(nValorDesconto).replace(",", "").replace(".", ""); 
                   nRet = epsondll.getInstance().EPSON_Fiscal_Desconto_Acrescimo_ItemEX(Seq.toString(), cDesconto, 2, true, false);
                    if(nRet==0){
                        nRet =1;
                    }else{
                        nRet =0;
                    }
               }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                   if(DarumaECF.OK()){
                       String cDesconto = nValorDesconto.toString().replace(".", ",");
                       nRet = ECF.iCFLancarDescontoItem(Seq.toString(), "D$", cDesconto);
                       nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                   }
                   
               }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return nRet;
    
    }
    public int CancelarItem(Integer nSeq){
            int nRet=0;
            try {
                   if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                       nRet= CONVECF.INSTANCE.ECF_CancelaItemGenerico(nSeq.toString());
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                       nRet = bemajava.Bematech.CancelaItemGenerico(nSeq.toString()) ;
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                       String  bytes = nSeq.toString();
                       nRet = epsondll.getInstance().EPSON_Fiscal_Cancelar_Item(bytes) ;
                     if(nRet==0){
                        nRet =1;
                    }else{
                        nRet =0;
                    }
                     }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                         if(DarumaECF.OK()){
                             nRet = ECF.iCFCancelarItem(nSeq.toString());
                             nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                         }
                         
                     }
            
            } catch (Exception e) {
                    LogDinnamus.Log(e);
            }

            return nRet;
    }
    public boolean ZPendente(){
        boolean bRet=false;

        try {
               if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                   byte[] Status={0,0};
                   //final CONVECF dll = CONVECF.INSTANCE;
                   //CONVECF.INSTANCE.ECF_VerificaZPendente(Status);
                   CONVECF.INSTANCE.ECF_VerificaZPendente(Status);
                   if(Status[0]==49){                       
                       return true;
                   }               
               }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                   
                   bRet = epsonmetodosadicionais.ZPendente();
                   
               }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                   
                   bRet = BematechMetodosECFComplementares.ZPendente();
               
              }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                  if(DarumaECF.OK()){
                     bRet = DarumaECF.ZPendente();
                     //nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                  }
              }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return bRet;
    }
    
    public boolean ProgramarAliquota(){
        try {
            String Forma="";
            boolean Tipo=false;
            frmCadastrarAliquota frmAliq = new frmCadastrarAliquota(null, true);
            frmAliq.setVisible(true);
            if(frmAliq.OK){
                String Condicao ="";
                if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                     Condicao = epsonmetodosadicionais.CondicaoCadAliquota;
                }else   if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                    //Condicao = Sweda_MetodosAuxiliares.CondicaoCadPagto;
                }else   if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                    
                }
                
                if(MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes_Sim_e_Nao(null, (Condicao.length()>0 ? "PARA REALIZAR ESSE PROCEDIMENTO NO ECF A CONDIÇÃO ABAIXO PRECISA SER ATENDIDA\n\n"+ Condicao  + "\n\n" : "") + "CONFIRMA A GRAVAÇÃO DA ALIQUOTA?", "PROGRAMAR ALIQUOTA")==MetodosUI_Auxiliares_1.Sim()){
                  if( ProgramarAliquota_Acao(new Float(frmAliq.txtAliquota.getText().replace(",", ".")), frmAliq.opICMS.isSelected() ? true : false)){
                      MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesINFO(null,"ALIQUOTA PROGRAMADA COM SUCESSO", "PROGRAMAR ALIQUOTA");
                      return true;
                  }else{
                     MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesAVISO(null, "NÃO FOI POSSÍVEL CADASTRAR A ALIQUOTA", "PROGRAMAR ALIQUOTA");
                  }
                }
            }            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);            
        }
        return false;
    }
    private boolean ProgramarAliquota_Acao(Float Aliquota, boolean TipoAliquota){
        boolean Ret = false;
        try {
            String AliquotasProgramadas = "";            
            Integer CodigoForma =0;            
            TreeMap<String,Float> Lista =  AliquotasProgramadas();            
            boolean Programada = false;
            
            Programada = Lista.containsValue(Aliquota.floatValue());
            if(!Programada){
                if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                     String AliqString = FormatarNumeros.FormatarParaMoeda(Aliquota).replace(",", "").replace(".", "");
                     if(AliqString.trim().length()<=3){
                         AliqString="0" + AliqString;
                     }
                     int RetCmd= epsondll.getInstance().EPSON_Config_Aliquota(AliqString, !TipoAliquota);
                     if(RetCmd==0){
                         Ret=true;
                     }
                }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                    String AliqString = FormatarNumeros.FormatarParaMoeda(Aliquota).replace(",", "").replace(".", "");
                     if(AliqString.trim().length()<=3){
                         AliqString="0" + AliqString;
                     }
                     if(bemajava.Bematech.ProgramaAliquota(AliqString,  (TipoAliquota ? 0 : 1) )==1){
                       Ret=true;  
                     }
                     
                }else if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                    String AliqString = FormatarNumeros.FormatarParaMoeda(Aliquota).replace(",", "").replace(".", "");
                     if(AliqString.trim().length()<=3){
                         AliqString="0" + AliqString;
                     }                    
                     if(CONVECF.INSTANCE.ECF_ProgramaAliquota(AliqString, (TipoAliquota ? 0 : 1))==1){
                        Ret=true;    
                     }
                 }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                     
                     String AliqString = FormatarNumeros.FormatarParaMoeda(Aliquota).replace(",", "").replace(".", "");
                     if(AliqString.trim().length()<=3){
                         AliqString="0" + AliqString;
                     } 
                     
                     int nRet = ECF.confCadastrarPadrao("ALIQUOTA", AliqString);
                     nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                     if(nRet==1){
                        Ret=true;    
                     }
                 }
            }else{
                MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesAVISO(null, "ALIQUOTA JÁ ESTA PROGRAMADA NO ECF", "PROGRAMAR ALIQUOTA");
            } 
        } catch (Exception e) {
            LogDinnamus.Log(e, true);            
        }
        return Ret;
    }     
    public boolean ProgramarFormaPagto(){
        try {
            String Forma="";
            boolean Tipo=false;
            frmCadastrarPagto pagto = new frmCadastrarPagto(null, true);
            pagto.setVisible(true);
            if(pagto.OK){
                String Condicao ="";
                if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                     Condicao = epsonmetodosadicionais.CondicaoCadPagto;
                }else   if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                    Condicao = Sweda_MetodosAuxiliares.CondicaoCadPagto;
                }
                
                if(MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes_Sim_e_Nao(null, (Condicao.length()>0 ? "PARA REALIZAR ESSE PROCEDIMENTO NO ECF A CONDIÇÃO ABAIXO PRECISA SER ATENDIDA\n\n"+ Condicao  + "\n\n" : "") + "CONFIRMA A GRAVAÇÃO DO MEIO DE PAGTO ?", "PROGRAMAR PAGTO")==MetodosUI_Auxiliares_1.Sim()){
                    if( ProgramarFormaPagto_Acao(pagto.txtDescricao.getText().trim(), pagto.opVinculado.isSelected() ? true : false)){
                       return true;
                    }else{
                        MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesAVISO(null, "NÃO FOI POSSÍVEL CADASTRAR A FORMA DE PAGTO", "PROGRAMAR PAGTO");
                    }
                }
            }            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);            
        }
        return false;
    }
    private boolean ProgramarFormaPagto_Acao(String Forma, boolean Tipo){
        boolean Ret = false;
        try {
            String FormaPagtoCadastradas = "";
            String[] Lista=null;
            Integer CodigoForma =0;            
            FormaPagtoCadastradas = VerificarFormasPagamento();
            if(FormaPagtoCadastradas.trim().length()>0){
               Lista =FormaPagtoCadastradas.split(",");
               CodigoForma = Lista.length+1;
            }else{
                CodigoForma=1;
            }
            if(!FormaPagtoCadastradas.trim().toLowerCase().contains(Forma.trim().toLowerCase())){
                if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                    Ret = epsonmetodosadicionais.ProgramarPagto(Tipo, Forma, CodigoForma.toString());                    
                }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                    if(bemajava.Bematech.ProgramaFormaPagamentoMFD(Forma, Tipo ? "1" : "0")==1){
                       Ret = true; 
                    }   
                }else if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                     if(CONVECF.INSTANCE.ECF_ProgramaFormaPagamentoMFD(Forma, Tipo ? "1" : "0")==1){
                        Ret = true; 
                     }
                 }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                     if(DarumaECF.OK()){
                        Ret  = DarumaECF.CadastrarPagto(Forma);
                     }
                 }
            }else{
                MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesAVISO(null, "FORMA DE PAGAMENTO JÁ ESTA PROGRAMADA NO ECF", "PROGRAMAR PAGTO");
            } 
        } catch (Exception e) {
            LogDinnamus.Log(e, true);            
        }
        return Ret;
    }
    public String VerificarFormasPagamento(){
        String cString="";

        try {
               if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                   byte[] nomes=new byte[275];
                   CONVECF.INSTANCE.ECF_VerificaDescricaoFormasPagamento(nomes);
                   cString = new String(nomes);
                   String[] Pagamento = cString.split(",");
                   cString="";
                   for (int i = 0; i < Pagamento.length; i++) {
                       if(Pagamento[i].trim().length()>0){
                         cString+=(cString.length()>0 ? "," : "") + Pagamento[i].trim();
                       }
                   }
               }else  if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                        cString = epsonmetodosadicionais.RetornarFormasDePagto();
               }else  if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                   if(DarumaECF.OK()){
                        cString = DarumaECF.ListarFormasDePagamento();
                   }
               }else  if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                      cString =BematechMetodosECFComplementares.CarregarMeiosPagamento();
               }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return cString;
    }
    public TreeMap<String,Float> AliquotasProgramadas(){
        TreeMap<String,Float> aliq = new TreeMap<String, Float>();
        
        String cString="";

        try {
               if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                   byte[] nomes=new byte[300];

                   if(CONVECF.INSTANCE.ECF_LerAliquotasComIndice(nomes)==1){                   
                      aliq =Sweda_MetodosAuxiliares.TratarStringAliquotas(new String(nomes));
                   }

               }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                    byte[] Aliquotas=new byte[533];
                    if(epsondll.getInstance().EPSON_Obter_Tabela_Aliquotas(Aliquotas)==0){
                        aliq =epsonmetodosadicionais.TratarStringAliquotas(new String(Aliquotas));
                    }                 
               }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                   bemajava.BemaString aliquotas = new BemaString();                   
                   if(bemajava.Bematech.RetornoAliquotas(aliquotas)==1){
                       aliq = BematechMetodosECFComplementares.TratarStringAliquotas(aliquotas.buffer);
                   }
                }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                     char[] Aliquotas=new char[150];
                     if(DarumaECF.OK()){
                        int Ret = ECF.rLerAliquotas(Aliquotas);
                        Ret = DarumaECF.TratarRetornoErroDaruma(Ret) ? 1 : 0 ;
                        if(Ret==1){
                            aliq = DarumaECF.TratarStringAliquotas(new String(Aliquotas));    
                        }
                     }
                    
                }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return aliq;
    }
    public int VerificaDiaAberto(){
        return VerificaDiaAberto(0);
    }
    public int VerificaDiaAberto(int CodigoPDV){
            int nRetorno=0;

            try {
                  if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                      byte[] Status={0,0};
                      if(CONVECF.INSTANCE.ECF_VerificaDiaAberto(Status)==1){
                         String cRetornoFuncao = (new String(Status)).trim();
                         nRetorno = Integer.parseInt(cRetornoFuncao);
                      }else{
                          nRetorno=-1;
                      }
                  }else  if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){

                       
                        if(epsonmetodosadicionais.DiaAberto()){
                            nRetorno=1;
                        }
                        /*
                        for (int i = 0; i < MensagensECF.size(); i++) {
                            if(MensagensECF.get(i).toLowerCase().contains("período de vendas aberto")){
                               nRetorno=1;
                               break;
                            }
                         }*/
                  
                  }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                        //String DataAtualECF =BematechMetodosECFComplementares.DataECF();
                        if(BematechMetodosECFComplementares.ConsultarDiaAberto()){
                           nRetorno=1; 
                        }
                 }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                     //String DataAtualECF =DarumaECF.DataMovimento(bVerificacaoInicial)DataECF();
                     if(DarumaECF.OK()){
                         if(DarumaECF.ConsultarDiaAberto()){
                             nRetorno=1; 
                         }
                     }
                 }
            } catch (Exception e) {
                    LogDinnamus.Log(e);
                    nRetorno=-1;
            }
            return nRetorno;
    }
    public String VerificaZJaEmitida(){
            String Retorno="";

            try {
                  if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                      
                      Retorno = Sweda_MetodosAuxiliares.ZJaEmitida();
                      
                  }else  if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                      
                        ArrayList<String> MensagensECF = epsonmetodosadicionais.MensagemEstadoECF(false);
                        
                        for (int i = 0; i < MensagensECF.size(); i++) {
                            if(MensagensECF.get(i).toLowerCase().contains("reducao z emitida")){
                               Retorno= MensagensECF.get(i);
                               break;
                            }
                         }
                  }else  if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                        String cRetornoZJAEmitida =BematechMetodosECFComplementares.ZJaEmitida();
                        if(cRetornoZJAEmitida.toLowerCase().contains("reducao z emitida")){
                           Retorno = cRetornoZJAEmitida ;
                        }
                  
                   }else  if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                       if(DarumaECF.OK()){
                          Retorno = DarumaECF.ZJaEmitida();
                       }
                   }
            } catch (Exception e) {
                    LogDinnamus.Log(e);                    
            }
            return Retorno;
    }

    public String DataMovimento(){
            String cRetorno="";

            try {
                    if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                        cRetorno=Sweda_MetodosAuxiliares.DataMovimento();
                    }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                        cRetorno = BematechMetodosECFComplementares.DataMovimento();
                        
                    }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                        cRetorno = epsonmetodosadicionais.DataMovimento();
                    }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                       if(DarumaECF.OK()){
                           cRetorno = DarumaECF.DataMovimento(true);
                       }
                    }
                    
            } catch (Exception e) {
                LogDinnamus.Log(e);
            }
            return cRetorno;
    }
    
    public int VerificarStatusCupomAberto(){
            int nRet=0;
            try {
                   if(TipoECF==null){
                       nRet=-1;
                   }else{
                       if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                           nRet=Sweda_MetodosAuxiliares.StatusCupomFiscal();
                       }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                           nRet = BematechMetodosECFComplementares.CupomFiscalAberto();
                       }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                           nRet = epsonmetodosadicionais.CupomAberto();
                       }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                           if(DarumaECF.OK()){
                               if(DarumaECF.CupomAberto()){
                                  nRet=1; 
                               }
                           }
                       }
                   }
            } catch (Exception e) {
                    LogDinnamus.Log(e);
                    nRet=-1;
            }
            return nRet;
    }
    /*
    public ArrayList<String> SituacaoECF(){
        ArrayList<String> Situacao = new ArrayList<String>();
        try {            
              if(TipoECF.equalsIgnoreCase(getNomeECFSweda())  ){
                    
              }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                    
              }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                      Situacao= epsonmetodosadicionais.MensagemEstadoECF();
              }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return  Situacao;
    }*/
    public int IniciaFechamentoCupom(String cAcredDesc, String cTipoAcredDesc, Double nValAcredDesc ){
            int nRet=0;
            try {
                   if(TipoECF.equalsIgnoreCase(getNomeECFSweda())  ){
                       if(nValAcredDesc>0f){
                          nValAcredDesc *=10;
                          nRet= CONVECF.INSTANCE.ECF_IniciaFechamentoCupom( cAcredDesc, cTipoAcredDesc,  nValAcredDesc.toString() );
                       }else{
                           nRet=1;
                       }
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                         //if(nValAcredDesc>0f){ 
                            //METO nValAcredDesc *=10;
                            String Valor = FormatarNumeros.FormatarParaMoeda(nValAcredDesc).replace(".", "").replace(",", "");
                            nRet = bemajava.Bematech.IniciaFechamentoCupom( cAcredDesc, cTipoAcredDesc,  Valor);
                         //}else{
                           //nRet=1;
                         ///}
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                         //if(nValAcredDesc>0f){ 
                            //nValAcredDesc *=10;
                            if(nValAcredDesc>0f){
                                String  cValAcredDesc=FormatarNumeros.FormatarParaMoeda( nValAcredDesc);
                                cValAcredDesc=cValAcredDesc.replace(",", "").replace(".", "");
                                nRet = epsondll.getInstance().EPSON_Fiscal_Desconto_Acrescimo_Subtotal(cValAcredDesc, 2, (cAcredDesc=="A" ? false : true), false);

                               if(nRet==0){
                                   nRet =1;
                               }else{
                                   setError(epsonmetodosadicionais.MensagemErro(false));
                                   nRet=0;                           
                               }
                            }else{
                                 nRet =1;
                            }
                           
                         //}else{
                           //nRet=1;
                     }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                         if(DarumaECF.OK()){
                             String Valor = nValAcredDesc.toString().replace(".", ".");
                             nRet = ECF.iCFTotalizarCupom("D" + cTipoAcredDesc, Valor);
                             nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                             
                         }
                     }
            } catch (Exception e) {
                    LogDinnamus.Log(e);
            }
            return nRet;
    }
    public int EfetuarFormaPagto(String FormaPag, Double ValorFormaPag){
            int nRet=0;
            try {
                    setError("");
                    
                   if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                       ValorFormaPag*=100;
                       String cValor = (new DecimalFormat("#.##")).format(ValorFormaPag);
                       
                       nRet= CONVECF.INSTANCE.ECF_EfetuaFormaPagamento( FormaPag,cValor );
                       if(nRet==-24){
                          setError("Forma de pagamento não programada.");
                       }else if(nRet==-2){
                           setError("Parâmetro inválido na função.");
                       }
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                       //ValorFormaPag*=100;
                       //String cValor = (new DecimalFormat("#.##")).format(ValorFormaPag);
                       String cValor = FormatarNumeros.FormatarParaMoeda(ValorFormaPag);
                       
                       cValor = cValor.replaceAll(",", "").replaceAll("\\.", "");
                              
                       nRet = bemajava.Bematech.EfetuaFormaPagamento(FormaPag,cValor );
                       if(nRet==-24){
                          setError("Forma de pagamento não programada.");
                       }else if(nRet==-2){
                           setError("Parâmetro inválido na função.");
                       }
                       
                  }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                       String cValor = FormatarNumeros.FormatarParaMoeda(ValorFormaPag);                       
                       
                       cValor = cValor.replaceAll(",", "").replaceAll("\\.", "");
                       
                       nRet = epsondll.getInstance().EPSON_Fiscal_Pagamento(FormaPag,cValor,2,"","" );
                       if(nRet!=0){
                          setError(epsonmetodosadicionais.EstadoECF("MsgErro"));
                          nRet =0;
                       }else{
                         nRet =1;
                       }
                       
                     }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                         if(DarumaECF.OK()){
                            String cValor = ValorFormaPag.toString().replace(".", ",");
                            nRet = ECF.iCFEfetuarPagamento(FormaPag, cValor, "");
                            nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                         }                         
                     }
            } catch (Exception e) {
                    LogDinnamus.Log(e);
            }
            return nRet;
    }
    public int TerminaFechamento(Float nDinheiro, String cMsg){
            int nRet=0;
            try {
                   if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){                       
                        nRet=CONVECF.INSTANCE.ECF_TerminaFechamentoCupom(cMsg);
                       
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){                       
                       nRet =  bemajava.Bematech.TerminaFechamentoCupom(cMsg);
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){                     
                
                       nRet= epsondll.getInstance().EPSON_Fiscal_Imprimir_MensagemEX(cMsg);
                       if(nRet!=0){
                          setError( epsonmetodosadicionais.MensagemErro(false));
                          return 0;                            
                       }        
                       nRet =  epsondll.getInstance().EPSON_Fiscal_Fechar_Cupom(true,false);
                        if(nRet!=0){
                          setError( epsonmetodosadicionais.MensagemErro(false));
                          return 0;                            
                       }else{     
                            if(nRet==0){nRet =1;}                         
                        }
                     }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){  
                         if(DarumaECF.OK()){
                            nRet = ECF.iCFEncerrarConfigMsg(cMsg);
                            nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                         }
                     }
            } catch (Exception e) {
                    LogDinnamus.Log(e);
            }
            return nRet;
    }
    public int CancelarUltimoCupom(){
            int nRet=0;
            try {
                   if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                       nRet= CONVECF.INSTANCE.ECF_CancelaCupom();
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                       nRet = bemajava.Bematech.CancelaCupom();
                       
                   }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                       nRet = epsondll.getInstance().EPSON_Fiscal_Cancelar_Cupom();
                        if(nRet==0){
                            nRet =1;
                        }else{
                            nRet =0;
                        }
                       
                     }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                         if(DarumaECF.OK()){
                             nRet = ECF.iCFCancelar();
                             nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;
                         }
                     }
            } catch (Exception e) {
                    LogDinnamus.Log(e);
            }
            return nRet;
    }


    /**
     * @return the NomeECFSweda
     */
    public String getNomeECFSweda() {
        return NomeECFSweda;
    }
    public boolean AbrirGaveta(){
        boolean bRet=false;
        try {
           if(GavetaFechada()){
               if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                   bRet=  (CONVECF.INSTANCE.ECF_AcionaGaveta()==1 ? true : false);
               }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                   bRet=  (bemajava.Bematech.AcionaGaveta() ==1 ? true : false);
               }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                   bRet=  (InterfaceEpson_2.INSTANCE.EPSON_Impressora_Abrir_Gaveta() ==1 ? true : false);
               }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                   if(DarumaECF.OK()){
                      bRet = ( ECF.eAbrirGaveta()==1 ? true : false );
                   }
               }
               
           }

        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return bRet;
    }
    public boolean GavetaFechada(){
        boolean bRet=false;
        try {
            int[] Status={0,0};
           if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){               
               if(CONVECF.INSTANCE.ECF_VerificaEstadoGaveta(Status)==1){
                   if(Status[0]==0){
                       bRet=true;
                   }
               }
           }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
               bemajava.BemaInteger i = new bemajava.BemaInteger();
               if(bemajava.Bematech.VerificaEstadoGaveta(i)==1){
                   if(i.getNumber()==1){
                       bRet=true;
                   }
               }
           }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
               
               if(epsonmetodosadicionais.EstadoECF_Gaveta()==1){               
                   bRet=true;
               }
            }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                if(DarumaECF.OK()){
                   int[] Status_Gaveta =new int[1];
                   int nRet = ECF.rStatusGaveta(Status_Gaveta);
                   nRet = DarumaECF.TratarRetornoErroDaruma(nRet) ? 1 : 0 ;        
                   if(nRet==1){
                       if(Status_Gaveta[0]==0){
                            bRet=true;
                       }
                   }
                }
            }

        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return bRet;
    }

    /**
     * @param NomeECFSweda the NomeECFSweda to set
     */
    public void setNomeECFSweda(String NomeECFSweda) {
        this.NomeECFSweda = NomeECFSweda;
    }
    public ArrayList<String> PalavraStatus(boolean VerificacaoInicial){
        ArrayList<String> alStatus = new ArrayList<String>();
        System.out.println("PalavraStatus : 1");
        try {
            if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){
                  alStatus = Sweda_MetodosAuxiliares.PalavraStatus(VerificacaoInicial);
            }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                      alStatus = epsonmetodosadicionais.MensagemEstadoECF(VerificacaoInicial);            
            }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                   alStatus = BematechMetodosECFComplementares.VerificaEstadoECF(VerificacaoInicial);
            }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                    System.out.println("PalavraStatus : 2");
                    if(DarumaECF.OK()){
                       alStatus = DarumaECF.VerificaEstadoECF(VerificacaoInicial);
                    }
                    System.out.println("PalavraStatus : 3");
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return alStatus;
    }
    public  boolean CancelarQualquerComprovanteAberto(){
                try {
                
                int nRet =VerificarStatusCupomAberto();
                if(nRet==1){
                    CancelarUltimoCupom();
                }else{
                    ArrayList<String> Retorno = StatusECF_Estentido();
                    if(Retorno.size()>0){
                        if(Retorno.contains("CNFV ABERTO")){
                          CNFV_Fechar();                           
                        }
                        else if(Retorno.contains("RG ABERTO")){
                           ComprovanteNaoFiscal_Fechar(); 
                        }
                    }
                }
                
                return true;
            } catch (Exception e) {
                LogDinnamus.Log(e, true);
                return false;
            }
     }
     public ArrayList<String> StatusECF_Estentido(){
        ArrayList<String> alStatus = new ArrayList<String>();
        ArrayList<String> alStatusEstendido = new ArrayList<String>();
        try {
            System.out.println("Tipo ECF " + TipoECF);
            if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                alStatusEstendido = BematechMetodosECFComplementares.StatusECF_Estendido();
                
            }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
                    alStatusEstendido = epsonmetodosadicionais.StatusECF_Estendido();    
                 
           }else if(TipoECF.equalsIgnoreCase(getNomeECFDaruma32())){
                 if(DarumaECF.OK()){
                    String RetornoDocAberto = DarumaECF.DocumentoAbertoECF();
                    if(!RetornoDocAberto.equalsIgnoreCase("")){
                        alStatusEstendido.add(RetornoDocAberto);
                    }
                 }
           }
            for (int i = 0; i < alStatusEstendido.size(); i++) {
                    if(alStatusEstendido.get(i).indexOf("CNFV")>=0){
                        alStatus.add("CNFV ABERTO");
                    }else if(alStatusEstendido.get(i).indexOf("RG")>=0){
                        alStatus.add("RG ABERTO");                    
                    }else if(alStatusEstendido.get(i).indexOf("CDC")>=0){
                        alStatus.add("CNFV ABERTO");                    
                    }
           }
            
            
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return alStatus;
    }


    /**
     * @return the Error
     */
    public String getError() {
        return Error;
    }

    /**
     * @param Error the Error to set
     */
    public void setError(String Error) {
        this.Error = Error;
    }

    /**
     * @return the NomeECFBematech
     */
    public String getNomeECFBematech() {
        return NomeECFBematech;
    }

    /**
     * @param NomeECFBematech the NomeECFBematech to set
     */
    public void setNomeECFBematech(String NomeECFBematech) {
        this.NomeECFBematech = NomeECFBematech;
    }

    /**
     * @return the NomeECFEpson
     */
    public String getNomeECFEpson() {
        return NomeECFEpson;
    }

    /**
     * @param NomeECFEpson the NomeECFEpson to set
     */
    public void setNomeECFEpson(String NomeECFEpson) {
        this.NomeECFEpson = NomeECFEpson;
    }
    
    public String[] RetonorEstendido(){
        
        String[] Retorno = null;
        try {
            
            if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                if (bemajava.Bematech.HabilitaDesabilitaRetornoEstendidoMFD("1")==1){
                    
                }
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Retorno;
    }
    public boolean VerificarECFLigado(){
        boolean Retorno = false;
        try {
           if(TipoECF.equalsIgnoreCase(getNomeECFSweda())){               
                
           }else if(TipoECF.equalsIgnoreCase(getNomeECFBematech())){
                
               boolean Exec=true;
               while(Exec){
                    int i=bemajava.Bematech.VerificaImpressoraLigada();
                    if(i==-6){
                        if(MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes_Sim_e_Nao(null, "IMPRESSORA DESLIGADA OU CABO DESCONECTADO. DESEJA TENTAR NOVAMENTE", "IMPRESSORA NÃO RESPONDE")!=MetodosUI_Auxiliares_1.Sim()){
                           return false; 
                        }     
                    }else{
                        return true;
                    }
               }
           }else if(TipoECF.equalsIgnoreCase(getNomeECFEpson())){
               
               
           }
            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            
        }
        return Retorno ;
    }

    /**
     * @return the NomeECFDaruma32
     */
    public String getNomeECFDaruma32() {
        return NomeECFDaruma32;
    }

    /**
     * @param NomeECFDaruma32 the NomeECFDaruma32 to set
     */
    public void setNomeECFDaruma32(String NomeECFDaruma32) {
        this.NomeECFDaruma32 = NomeECFDaruma32;
    }

    /**
     * @return the bVerificacaoInicial
     */
    public boolean isbVerificacaoInicial() {
        return bVerificacaoInicial;
    }

    /**
     * @param bVerificacaoInicial the bVerificacaoInicial to set
     */
    public void setbVerificacaoInicial(boolean bVerificacaoInicial) {
        this.bVerificacaoInicial = bVerificacaoInicial;
    }




}
