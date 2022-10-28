/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pdf;

import br.com.log.LogDinnamus;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import org.apache.pdfbox.PDFReader;
import org.apache.pdfbox.pdfviewer.PageWrapper;
import org.apache.pdfbox.pdmodel.PDPage;

/**
 *
 * @author DSWM
 */
public class CustomPDFReader extends PDFReader {

    private static final long serialVersionUID = 678451510308887925L;
    private String arquivoPDF = null;

    public CustomPDFReader() {
        super();

    }

    public CustomPDFReader(String arquivoPDF) {
        super();
        this.arquivoPDF = arquivoPDF;

    }

    public void setCurrentFile(String file) {
        try {
            Method m = getClass().getSuperclass().getDeclaredMethod("openPDFFile",
                    new Class<?>[]{String.class, String.class});
            m.setAccessible(true);
            m.invoke(this, file, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void imprimir() throws IOException, PrinterException {

        PDFPrinting pDFPrinting = new PDFPrinting();
        pDFPrinting.imprimirPDF(arquivoPDF);

    }

    public void ExibirUI() {

        try {

            CustomPDFReader reader = new CustomPDFReader();
            // remove menubar
            JMenuBar menu = reader.getJMenuBar();
            menu.setVisible(false);

            JPanel header = new JPanel(new BorderLayout());

            JPanel firstLine = new JPanel();

            JPanel secondLine = new JPanel();

            JButton button = new JButton("Imprimir");
            button.setPreferredSize(new Dimension(100, 30));
            secondLine.add(button);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    try {
                        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        imprimir();
                    } catch (IOException ex) {
                        Logger.getLogger(CustomPDFReader.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (PrinterException ex) {
                        Logger.getLogger(CustomPDFReader.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

            header.add(firstLine, java.awt.BorderLayout.NORTH);
            header.add(secondLine, java.awt.BorderLayout.SOUTH);

            reader.getContentPane().add(header, java.awt.BorderLayout.NORTH);

            // set default opened file
            reader.setCurrentFile(this.arquivoPDF);
            reader.showAllPages();
            reader.setLocationRelativeTo(null);
            reader.setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);

            reader.setVisible(true);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }

    }

    private void showAllPages() {
        try {
            Field pages = getClass().getSuperclass().getDeclaredField("pages");
            pages.setAccessible(true);
            List<PDPage> pagesList = (List<PDPage>) pages.get(this);

            Field documentPanel = getClass().getSuperclass().getDeclaredField("documentPanel");
            documentPanel.setAccessible(true);
            JPanel panel = (JPanel) documentPanel.get(this);
            panel.remove(0);
            GridLayout layout = new GridLayout(0, 1);
            panel.setLayout(layout);
            for (PDPage page : pagesList) {
                PageWrapper wrapper = new PageWrapper(this);
                wrapper.displayPage(page);
                panel.add(wrapper.getPanel());
            }
            pack();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
