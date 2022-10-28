/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdf;

import br.com.log.LogDinnamus;

/**
 *
 * @author DSWM
 */
public  class teste {
    public static void main(String[] args){
         LogDinnamus.Iniciar();
        System.out.println("Exibir");
        String arquivoPDF = "";
        PDFView instance = new PDFView();
        boolean expResult = false;
        PDFView pdfView = new PDFView();
        pdfView.Exibir("C:\\Users\\DSWM\\Downloads\\M_A_S_MITRE_10_09_2018_1740.pdf");
        //boolean result = instance.Exibir("C:\\Users\\DSWM\\Downloads\\M_A_S_MITRE_10_09_2018_1740.pdf");
        
    }
}
