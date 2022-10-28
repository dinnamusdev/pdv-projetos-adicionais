/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.daruma;

import br.com.daruma.jna.ECF;
import br.com.daruma.jna.UTIL;
import javax.swing.JOptionPane;

/**
 *
 * @author Daruma
 */
public class UtilitarioDaruma {

    public static final int PRODUTO_FISCAL   = 10;
    public static final int PRODUTO_DUAL    = 20;
    public static final int PRODUTO_MODEM  = 30;
    public static char[] cIORetorno = new char[300];
    public static char[] cIOErro = new char[300];
    public static char[] cIOAviso = new char[300];

    /**
     * @param iRetorno
     * @param TP_PRODUTO
     */
    public static void validaRetornoDaruma(int iRetorno, int TP_PRODUTO){
        switch(TP_PRODUTO){
            case PRODUTO_FISCAL:
                validaFiscal(iRetorno,false,false);
                break;
                
            default:
                JOptionPane.showMessageDialog(null, "Produto Não Cadastrado!!",
                        "Daruma_Framework_Java",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }

    /**
     * @param iRetorno
     * @param strSucess
     * @param strError
     */
    public static void validaFiscal(int iRetorno, boolean SaidaConsole , boolean SomenteErros) {
        int[] iErro  = new int[1];
        int[] iAviso = new int[1];
        
        
        
        ECF.rStatusUltimoCmdInt(iErro, iAviso);

        String strRetono = "Retorno do Método = " + getRetornoDarumaFiscal(iRetorno) + "\n" +
                           "Número Erro = " + getNumErroDarumaFiscal(iErro[0])       + "\n" +
                           "Número Aviso = " + getNumAvisoDarumaFiscal(iAviso[0]);
        if(iRetorno == 1 && !SomenteErros ){
            if(SaidaConsole){
                System.out.println(strRetono);
            }else{
                JOptionPane.showMessageDialog(null, strRetono,
                        "DarumaFramework Retorno do Método",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            if(SaidaConsole){
                System.out.println(strRetono);
            }else{
                JOptionPane.showMessageDialog(null, strRetono,
                        "DarumaFramework Retorno do Método",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * @param iRetorno
     * @return
     */
      private static void limparBuffer(char[] cIORetorno) {
        for(int i = 0; i < cIORetorno.length; i++){
            cIORetorno[i] = ' ';
        }
    }
    private static String getRetornoDarumaFiscal(int iRetorno) {
        limparBuffer(cIORetorno);
        int iRet = ECF.eInterpretarRetorno(iRetorno,cIORetorno);
        String strRetorno = new String(cIORetorno).trim();
        return strRetorno;
        
    }

    /**
     * @param iNumErro
     * @return
     */
    private static String getNumErroDarumaFiscal(int iNumErro) {        
       limparBuffer(cIOErro);
        int iRet = ECF.eInterpretarErro(iNumErro,cIOErro);
        String strErro = new String(cIOErro).trim();
        return strErro;
    }

    /**
     * @param iAviso
     * @return
     */
    private static String getNumAvisoDarumaFiscal(int iAviso) {
        limparBuffer(cIOAviso);
        int iRet = ECF.eInterpretarAviso(iAviso,cIOAviso);
        String strAviso = new String(cIOAviso).trim();
        return strAviso;
    }
}
