/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdf;

import java.awt.print.PrinterException;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;

/**
 *
 * @author DSWM
 */
public class PDFPrinting {
     public PDFPrinting()
    {
    }   

    /**
     * Entry point.
     */
    public void imprimirPDF(String nomepdf) throws IOException, PrinterException {

        PDDocument pdf = PDDocument.load(nomepdf);
        pdf.print();
    }

   
}
