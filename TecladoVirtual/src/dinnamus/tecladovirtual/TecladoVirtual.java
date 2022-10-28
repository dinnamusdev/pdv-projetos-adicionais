/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dinnamus.tecladovirtual;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 *
 * @author Fernando
 */
public class TecladoVirtual extends JPanel{
    public static int NUMERICO = 0;
    public static int ALFANUMERICO = 1;
    
    private JTextField txt;
    private String[] teclasNumericas = {"7", "8", "9", "4", "5", "6", "1", "2", "3", "0", ",", "←"};
    private String[] teclasAlfaNumericas = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "(", ")", "←",
                                            "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "Ç", "~", "`",
                                            "A", "S", "D", "F", "G", "H", "J", "K", "L", "'", "^", ":", ";",
                                            "Z", "X", "C", "V", "B", "N", "M", ",", ".", "/", "@", "_", "-"};
    
    private ArrayList<JButton> botoes = new ArrayList<JButton>();
    private JPanel pNumeros, pCaracteres, pEspaco, pEnter;
    private JPopupMenu pop;
    
    private ImageIcon icone;
    private JButton botao;
    
    private Acao acao = new Acao();
    private AcaoBotao acaoBotao = new AcaoBotao();
    
    private String acento = "";
    
    public TecladoVirtual() {}
    
    public JButton getBotaoTecladoVirtual(JButton btn, JTextField t, int tipoTeclado) {
    
        txt = t;
        botao = btn;
        configuraCampo(tipoTeclado);
        configuraBotao(tipoTeclado);
        
        return botao;
        
    }
    
    private void configuraCampo(int tipoTeclado) {
        
        if (tipoTeclado == NUMERICO) {
            
            pNumeros = new JPanel();
            setLayout(new BorderLayout());
            pNumeros.setLayout(new GridLayout(4, 3, 0, 0));

            for(int i = 0; i < teclasNumericas.length; i++) {
                JButton b = new JButton(teclasNumericas[i]);
                b.addActionListener(acao);
                pNumeros.add(b);
                botoes.add(b);
            }
            
        } else {
            
            pCaracteres = new JPanel();
            setLayout(new BorderLayout());
            pCaracteres.setLayout(new GridLayout(4, 13, 0, 0));

            for(int i = 0; i < teclasAlfaNumericas.length; i++) {
                JButton b = new JButton(teclasAlfaNumericas[i]);
                b.addActionListener(acao);
                pCaracteres.add(b);
                botoes.add(b);
            }

            pEspaco = new JPanel(new GridLayout(1, 1, 0, 0));
            JButton bEspaco = new JButton("Espaço");
            bEspaco.addActionListener(acao);
            pEspaco.add(bEspaco);
            
        }
        
        pEnter = new JPanel(new GridLayout(1, 1, 0, 0));
        JButton bEnter = new JButton("Enter");
        bEnter.addActionListener(acao);
        pEnter.add(bEnter);
        
    }
    
    private JButton configuraBotao(int tipoTeclado) {
        
        icone = new ImageIcon(getClass().getResource("/cpadivisual/imagem/teclado.png"));
        botao.setIcon(icone);
        botao.setText("");

        
        acaoBotao.setTipoTeclado(tipoTeclado);
        botao.addActionListener(acaoBotao);
        
        return botao;
        
    }
    
    private class Acao implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            JButton b = (JButton)e.getSource();
            
            if (b.getText().equalsIgnoreCase("Enter")) {
                txt.requestFocus();
                pop.setVisible(false);
                
            } else if (b.getText().equalsIgnoreCase("Espaço")) {
                txt.setText(txt.getText()+" ");
                
            } else if (b.getText().equalsIgnoreCase("←")) {
                txt.setText(txt.getText().length() > 0 ? ""+txt.getText().substring(0, txt.getText().length() - 1) : "");
            
            } else if (b.getText().equalsIgnoreCase("`")) {
                acento = "\u0300";
                
            } else if (b.getText().equalsIgnoreCase("'")) {
                acento = "\u0301";
            
            } else if (b.getText().equalsIgnoreCase("^")) {
                acento = "\u0302";
                
            } else if (b.getText().equalsIgnoreCase("~")) {
                acento = "\u0303";
                
            } else {
                if (!"".equalsIgnoreCase(acento) && b.getText().matches("[AEIOU]+")) {
                    String l = Normalizer.normalize(b.getText() + acento, Normalizer.Form.NFC);
                    txt.setText("" + txt.getText() + l);
                } else {
                    txt.setText(""+txt.getText()+b.getText());
                }
                acento = "";
            }
            
        }
    }
    
    private class AcaoBotao implements ActionListener {
        
        private int tipoTeclado;
        
        public void actionPerformed(ActionEvent e) {
            txt.requestFocus();

            pop = new JPopupMenu();
            
            if (tipoTeclado == NUMERICO) {
                pop.add(pNumeros);
            } else {
                pop.add(pCaracteres);
                pop.add(pEspaco, BorderLayout.SOUTH);
            }
            
            System.out.println("chegou na acao do botao!");
            
            pop.add(pEnter, BorderLayout.SOUTH);
            pop.setVisible(true);
            pop.setLocation(txt.getLocationOnScreen().x+112, txt.getLocationOnScreen().y-1);
        }
        
        public void setTipoTeclado(int tipoTeclado) {
            this.tipoTeclado = tipoTeclado;
        }
        
    }
    
      public static void main(String[] args) {
          TecladoVirtual teclado = new TecladoVirtual();  
          
      }

}
