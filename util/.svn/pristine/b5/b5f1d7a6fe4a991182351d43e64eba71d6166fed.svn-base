/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com;

import br.com.log.LogDinnamus;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author Administrador
 */
public class FormatarNumeros {

    private static NumberFormat numberFormat=new DecimalFormat();

    public static String FormatarParaMoeda(Float nNumero){
        String cRetorno="";
        try {
            numberFormat.setMaximumFractionDigits(2);
            numberFormat.setMinimumFractionDigits(2);
            cRetorno= numberFormat.format(nNumero);
        } catch (Exception e) {
            LogDinnamus.Log(e);

        }
        return cRetorno;

    }
     public static String FormatarParaMoeda(Double nNumero){
        String cRetorno="";
        try {
            numberFormat.setMaximumFractionDigits(2);
            numberFormat.setMinimumFractionDigits(2);
            cRetorno= numberFormat.format(nNumero);
        } catch (Exception e) {
            LogDinnamus.Log(e);

        }
        return cRetorno;

    }
    
    public static String FormatarParaMoeda_3Casas(Float nNumero){
        String cRetorno="";
        try {
            numberFormat.setMaximumFractionDigits(3);
            numberFormat.setMinimumFractionDigits(3);
            cRetorno= numberFormat.format(nNumero);
        } catch (Exception e) {
            LogDinnamus.Log(e);

        }
        return cRetorno;

    }
    public static String FormatarParaMoeda_3Casas(Double nNumero){
        String cRetorno="";
        try {
            numberFormat.setMaximumFractionDigits(3);
            numberFormat.setMinimumFractionDigits(3);
            cRetorno= numberFormat.format(nNumero);
        } catch (Exception e) {
            LogDinnamus.Log(e);

        }
        return cRetorno;

    }

}
