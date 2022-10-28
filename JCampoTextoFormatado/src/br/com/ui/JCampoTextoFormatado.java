/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ui;

import java.awt.event.FocusEvent;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author dti
 */
public class JCampoTextoFormatado extends JFormattedTextField{

    public JCampoTextoFormatado(NumberFormatter df) {
        super(df);

    }
    public void initComponents(){

    }

    @Override
    protected void processFocusEvent(FocusEvent e) {
        super.processFocusEvent(e);
        if ( e.getID() == FocusEvent.FOCUS_GAINED )
        selectAll() ;
    }



}
