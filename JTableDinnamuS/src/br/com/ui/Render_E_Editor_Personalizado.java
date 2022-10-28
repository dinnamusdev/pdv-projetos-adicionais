/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ui;

import br.com.log.LogDinnamus;
import java.awt.Color;
import java.awt.Component;
import java.net.URL;
import java.util.EventObject;
import java.util.TreeMap;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;



/**
 *
 * @author Fernando
 */
public class Render_E_Editor_Personalizado {
    private static JCheckBox check =  new JCheckBox();  
    private static ImageIcon imgCheck=null ;

    public  static ImageIcon  getImageCheck(){
        try {
            if(imgCheck==null){          
                //Object
               
                URL  url=  Class.forName("java.lang.Object").getResource("/dinnamus/ui/img/Yes.png");
                imgCheck = new ImageIcon(url);
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return imgCheck;
    }
    public static DefaultTableCellRenderer RenderCheckBox_2(){
        DefaultTableCellRenderer render = null;
        
        try {           
            render = new DefaultTableCellRenderer(){
                 protected void setValue(Object value){    
                    setVisible(false);
                    if (value instanceof Object){  
                        if (value != null){    
                            Boolean bValue = (Boolean) value;
                            if(bValue){                                                                                                                    
                                setHorizontalAlignment(SwingConstants.CENTER);
                                setIcon(getImageCheck());                                                                                               
                            }else{                                                                 
                              setIcon(null);  
                            }
                            setText("");
                            
                        } else{  
                            setText("");  
                            setIcon(null);  
                        }  
                    } else {  
                        super.setValue(value);  
                    }     
                 }
                
            };            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return render;
    }
    public static TableCellRenderer RenderCheckBox(){
        
        TableCellRenderer render = null;
        try {
              render = new TableCellRenderer() {
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        Boolean Valor  = false;
                        try {
                            if(value !=null){
                                Valor = (Boolean)value;
                            }                                                      
                        } catch (Exception e) {
                            LogDinnamus.Log(e, true);
                        }    
                        check.setHorizontalAlignment(SwingConstants.CENTER);
                        check.setSelected(Valor);
                        
                        if(isSelected){
                           check.setBorder(table.getBorder());
                        }
                        
                        return check; 
                    }
                };
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return render;
    }
     public static DefaultCellEditor EditorCheckBox(){
        DefaultCellEditor editor = null;
        try {
            JCheckBox checkEditar =new JCheckBox("", false);
            checkEditar.setHorizontalAlignment(SwingConstants.CENTER);
            checkEditar.setVisible(false);
            editor =  new DefaultCellEditor(checkEditar);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return editor;
    }
    public static DefaultCellEditor EditorComboBox(JComboBox<ItemLista> c){
        DefaultCellEditor editor = null;
        try {
            //JComboBox<ItemLista> c = new JComboBox<ItemLista>(model);
            editor =  new DefaultCellEditor(c);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return editor;
    } 
    public static TableCellRenderer RenderTabela(final JTableDinnamuS_SubTabela subtabela){
        TableCellRenderer render = null;
        try {            
            render = new TableCellRenderer() {              
              public Component getTableCellRendererComponent(JTable table,
                      Object value, boolean isSelected, boolean hasFocus,int row, int column) {                    
                      
                       System.out.println("Tabela Principal : Linha : "  + row + " Coluna : " + column) ;
                       JTable tb = new JTable(subtabela.getUI().getModel());
                       
                        if(isSelected){
                            tb.setBackground(table.getSelectionBackground());
                            tb.setForeground(table.getSelectionForeground());
                        }
                       
                       if(row>=0){
                          int Altura = subtabela.getUI().getPreferredSize().height;
                          if(Altura>0){    
                            int Linhatb =table.getRowHeight(row);
                            if(Linhatb<Altura){
                              table.setRowHeight(row, Altura);  
                            }
                          }
                        }   
                        return tb;
                    }
          };
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return render;
    }   
    public static TableCellEditor EditorBotao(final JButton bt){
        TableCellEditor editor = null;
        try {
            //JComboBox<ItemLista> c = new JComboBox<ItemLista>(model);
            editor = new EditorJButton(bt);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return editor;
    } 
    public static TableCellRenderer RenderBotao(final JButton bt){
        //botao
        TableCellRenderer render = null;
        try {            
            render = new TableCellRenderer() {              
              public Component getTableCellRendererComponent(JTable table,
                      Object value, boolean isSelected, boolean hasFocus,int row, int column) {                    
                        //JButton botao =bt;
                       if(value!=null){
                           bt.setText(value.toString());
                           
                       } 
                       Color back = bt.getBackground();
                       Color Fore = bt.getForeground();
                       if(isSelected){                           
                           bt.setBackground(Color.WHITE);
                           bt.setForeground(Color.BLACK );                       
                       }else{
                           bt.setBackground(Color.BLACK);
                           bt.setForeground(Color.WHITE );                       
                       }
                       return bt;
                    }
          };
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return render;
    }   
    
    public static TableCellRenderer RenderImagens(final TreeMap<Object,ImageIcon> Imagens){
        TableCellRenderer render = null;
        
        try {
              render = new TableCellRenderer() {
                    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                        JLabel l = new JLabel();                        
                       
                        //Component c =  table.getCellRenderer(row, column).getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                        
                        try {                            
                                
                            if(Imagens.containsKey(value.toString())){                        
                                
                               l = new JLabel(Imagens.get(value.toString()));
                               
                                
                            }
                            
                        } catch (Exception e) {
                            LogDinnamus.Log(e, true);
                        }                            
                        return l; 
                    }
                };
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return render;
    }
    public static DefaultTableCellRenderer RenderImagens_2(final TreeMap<Object,ImageIcon> Imagens){
        DefaultTableCellRenderer render = null;
        
        try {           
            render = new DefaultTableCellRenderer(){
                 protected void setValue(Object value){    
                    if (value instanceof ImageIcon){  
                        if (value != null){                              
                            if(Imagens.containsKey(value.toString())){                                                                                      
                              ImageIcon d = (ImageIcon) Imagens.get(value);  
                              setHorizontalAlignment(SwingConstants.CENTER);
                              setIcon(d);      
                            }else{                                
                                 setText("");  
                                 setIcon(null);                                  
                            }                            
                        } else{  
                            setText("");  
                            setIcon(null);  
                        }  
                    } else {  
                        super.setValue(value);  
                    }     
                 }
                
            };            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return render;
    }
    
     
}
