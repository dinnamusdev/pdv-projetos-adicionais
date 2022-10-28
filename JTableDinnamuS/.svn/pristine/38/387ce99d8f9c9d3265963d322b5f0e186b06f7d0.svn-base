/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ui;

import br.com.log.LogDinnamus;
import java.util.TreeMap;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Fernando
 */
public class DefaultTableCellRender_Imagens extends DefaultTableCellRenderer {
 
   private TreeMap<Object,ImageIcon> Imagens = null;
   public DefaultTableCellRender_Imagens(TreeMap<Object,ImageIcon> Imagens){
       this.Imagens=Imagens;
   }
    protected void setValue(Object value){  
        try {
            if (value != null){  
                if(Imagens.containsKey(value.toString())){
                    ImageIcon d = (ImageIcon) Imagens.get(value.toString());  
                    setHorizontalAlignment(SwingConstants.CENTER);
                    setIcon(d); 
                    setText("");
                    
                }else{
                  super.setValue(value);  
                }
            }else{
                super.setValue(value); 
                setIcon(null); 
            }            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }
    
}
