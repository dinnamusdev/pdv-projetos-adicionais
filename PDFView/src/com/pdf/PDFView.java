package com.pdf;

import javax.swing.JDialog;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author DSWM
 */
public class PDFView {

    public void Exibir(String arquivoPDF) {
       CustomPDFReader customPDFReader = new CustomPDFReader(arquivoPDF);
       
      customPDFReader.ExibirUI();
    }

}
