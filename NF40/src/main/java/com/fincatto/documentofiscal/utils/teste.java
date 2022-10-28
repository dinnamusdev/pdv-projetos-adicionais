/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.fincatto.documentofiscal.utils;

import com.fincatto.documentofiscal.DFAmbiente;

/**
 *
 * @author DSWM
 */
public class teste {
    public static void main(String[] args){
         final byte[] cadeia = GeraCadeiaCertificados.geraCadeiaCertificados(DFAmbiente.PRODUCAO, "senha");
    }
}
