package ui.com.jcheckboxlist;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Fernando
 */
public class JCheckBoxList  extends javax.swing.JPanel {
//    private JList lista = new JList();
    private JCheckBox[] ChkLista =null;     
    protected static Border noFocusBorder = new EmptyBorder(1, 1, 1, 1);
    private String opcoes = "Item 1,Item 2,Item 3,Item 4,Item 5";
    private HashMap<String,Boolean> estadoCheckBox = new HashMap<>();        
    public JCheckBoxList()
    {
        //super();  
        UI();
        initComponents();
        jList1.requestFocus();
        
        
    }
    
    private void UI(){
        getLista().setModel(new DefaultListModel());
        getLista().setCellRenderer(new CheckBoxCellRenderer());
 
        getLista().addMouseListener(new MouseAdapter()
        {
            public void mousePressed(MouseEvent e)
            {
                int index = getLista().locationToIndex(e.getPoint());
                if (index != -1)
                {
                    JCheckBox checkbox = (JCheckBox) getLista().getModel().getElementAt(index);
                    checkbox.setSelected(!checkbox.isSelected());
                    estadoCheckBox.put(checkbox.getText(),checkbox.isSelected());                                                
                    repaint();
                }
            }
        });
 
        getLista().addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_SPACE)
                {
                    int index = getLista().getSelectedIndex();
                    if (index != -1)
                    {
                        JCheckBox checkbox = (JCheckBox) getLista().getModel().getElementAt(index);                        
                        checkbox.setSelected(!checkbox.isSelected());
                        estadoCheckBox.put(checkbox.getText(),checkbox.isSelected());                                                
                        repaint();
                    }
                }
            }
        });
 
        getLista().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    /**
     * @return the lista
     */
    
    private JList getLista() {
        return jList1;
    }

    /**
     * @return the opcoes
     */
    public String getOpcoes() {
        return opcoes;
    }
    

    /**
     * @param opcoes Variavel que recebe as opcoes do CheckBoxList separado por Virgula Ex: setOpoes("Opcao 1,Opcao 2,Opcoes 3")
     */
    public void setOpcoes(String opcoes) {
        this.opcoes = opcoes;
        jList1.setModel(getModel());
    }
    /**
     * Retorna o estado de cada checkbox apartir do indice desejado
     * 
     */
    public boolean getEstadoCheckBox(int Index){  
        boolean Ret = false;
        String Key = estadoCheckBox.keySet().toArray()[Index].toString();
        Ret = estadoCheckBox.get(Key);
        return Ret;
    }
    /**
     * Retorna o estado de cada checkbox apartir do nome do checkbox desejado
     * 
     */
    public boolean getEstadoCheckBox(String Key){  
        boolean Ret = false;
        //String Key = estadoCheckBox.keySet().toArray()[Index].toString();
        Ret = estadoCheckBox.get(Key);
        return Ret;
    }
    
    /**
     * Retorna a quantidade de Checkbox do List
     * 
     */
    public int getTotalCheckBox(){  
        
        return estadoCheckBox.size();
    }
    /**
     * Retorna  o nome de cada Checkbox do List apartir do index
     * 
     */
    public String getNomeCheckBox(int Index){  
        String Ret = "";
        Ret = estadoCheckBox.keySet().toArray()[Index].toString();        
        return Ret;
    }

    /**
     * @param lista the lista to set
     */
    private AbstractListModel getModel(){
        AbstractListModel model = null;
        try {
            final String[] arOpcoes = getOpcoes().split(",");
                model = new javax.swing.AbstractListModel() {                    
                JCheckBox[] strings =null;                
                public JCheckBox[] getString(){
                    if(strings==null){
                        strings = new JCheckBox[arOpcoes.length];
                        if(arOpcoes.length>0){                        
                            for(int i=0 ; i < arOpcoes.length ; i++){
                                strings[i] =   new JCheckBox(arOpcoes[i]);
                                estadoCheckBox.put(arOpcoes[i], Boolean.FALSE);
                            }
                        }
                        ChkLista = strings;
                    }
                    return strings;                    
                }                
                public int getSize() { 
                    return getString().length; 
                }
                public Object getElementAt(int i) {
                    return getString()[i]; 
                }};
            
        } catch (Exception e) {
            throw e;
        }
        return model;
    } 
    protected class CheckBoxCellRenderer implements ListCellRenderer
    {
        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus)
        {                
            //String cTipoCampo=value.getClass(). 
            JCheckBox checkbox = (JCheckBox) value;
            checkbox.setBackground(isSelected ? getLista().getSelectionBackground() : getBackground());
            checkbox.setForeground(isSelected ? getLista().getSelectionForeground() : getForeground());
 
            checkbox.setEnabled(isEnabled());
	    checkbox.setFont(getFont());
            checkbox.setForeground(getForeground());
            checkbox.setFocusPainted(false);
 
            checkbox.setBorderPainted(true);
            checkbox.setBorder(isSelected ? UIManager.getBorder("List.focusCellHighlightBorder") : noFocusBorder);

            return checkbox;
        }
    }
   private javax.swing.JList jList1 = new JList();
   private javax.swing.JScrollPane jScrollPane1;
   private void initComponents() {
        //java.awt.GridBagConstraints gridBagConstraints;
        jScrollPane1 = new javax.swing.JScrollPane();
        //jList1 = new javax.swing.JList();

        //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

       jList1.setModel(getModel());
        jScrollPane1.setViewportView(jList1);
        
        //jList1.setBackground(Color.red);
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
        );
        getLista().setBackground(getBackground());
        //Dimension d = (new JCheckBox("Item 1")).getSize();
        //setSize(100, 100);
        //this.pack();
    }// 
 
 /*
    public static void main(String args[])
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
 
        JCheckBoxList cbList = new JCheckBoxList();
 
		JCheckBox check = new JCheckBox("one");
		Object[] cbArray = new Object[3];
        cbArray[0] = check;
        cbArray[1] = new JCheckBox("two");
        cbArray[2] = new JCheckBox("three");
		
        cbList.setListData(cbArray);
 
        frame.getContentPane().add(cbList);
        frame.pack();
        frame.setVisible(true);
    }*/
}
