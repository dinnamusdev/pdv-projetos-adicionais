/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.br.ui;

import java.awt.Color;
import java.awt.Component;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;

/**
 *
 * @author DSWM
 */
public class SmackListRender extends ItemContatoUI implements ListCellRenderer<SmackListItemContato>  {
    
private Border border=BorderFactory.createLineBorder(Color.RED, 1);    

    public SmackListRender() {
           
    }

    @Override
    public Component getListCellRendererComponent(JList<? extends SmackListItemContato> list, SmackListItemContato value, int index, boolean isSelected, boolean cellHasFocus) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(value!=null){
            
            lblUsuario.setText(value.getNome());
            lblUsuario.setIcon(value.getImageIcon());
            LblStatus.setIcon(value.getImageIconStatus());
            if(value.getQtMensagemNaoLidas()==null){
               lblQtMensagemNaoLidas.setVisible(false);
            }else{
                lblQtMensagemNaoLidas.setVisible(true);
                lblQtMensagemNaoLidas.setText(value.getQtMensagemNaoLidas().toString());
            }
            
            if(value.getUltimaMensagemNaoLida()==null){
              lblUltimaMensagem.setVisible(false);
            }else{
                 lblUltimaMensagem.setVisible(true);
                  lblUltimaMensagem.setText(value.getUltimaMensagemNaoLida().getBody());
            }
            if (isSelected) {
                setBorder(BorderFactory.createLineBorder(Color.BLACK));
            } else {
                 setBorder(null);
            }
            setOpaque(true);
        }
       return this;
    }
    
    
    

}
