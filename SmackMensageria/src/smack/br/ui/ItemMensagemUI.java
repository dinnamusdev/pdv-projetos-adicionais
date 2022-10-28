/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.br.ui;

import javax.swing.JPanel;

/**
 *
 * @author DSWM
 */
public class ItemMensagemUI extends JPanel{

    public javax.swing.JTextArea Mensagem;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JLabel lblDireita;
    public javax.swing.JLabel lblEsquerda;
      public javax.swing.JLabel lblEsquerda2;
       public javax.swing.JLabel lblCentro;

    public ItemMensagemUI() {
        java.awt.GridBagConstraints gridBagConstraints;

        lblDireita = new javax.swing.JLabel();
        lblEsquerda = new javax.swing.JLabel();
          lblCentro = new javax.swing.JLabel();
      //  lblEsquerda2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Mensagem = new javax.swing.JTextArea();

        //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new java.awt.GridBagLayout());

        lblDireita.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblDireita.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/icons8-usuário-30.png"))); // NOI18N
        lblDireita.setToolTipText("");
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        this.add(lblDireita, gridBagConstraints);

        lblEsquerda.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblEsquerda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblEsquerda.setText("99:99");
        lblEsquerda.setToolTipText("");
        lblEsquerda.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        this.add(lblEsquerda, gridBagConstraints);
       
        lblCentro.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblCentro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblCentro.setText("11:99");
        lblCentro.setToolTipText("");
        lblCentro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx =2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        this.add(lblCentro, gridBagConstraints);
        

        Mensagem.setColumns(20);
        Mensagem.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        Mensagem.setLineWrap(true);
        Mensagem.setRows(3);
        //Mensagem.setText("MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO     MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO     MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO    MENSAGEM DE FERNANDO");
        Mensagem.setWrapStyleWord(true);
        Mensagem.setBorder(new org.jdesktop.swingx.border.DropShadowBorder());
        Mensagem.setOpaque(false);
        jScrollPane1.setViewportView(Mensagem);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(3, 3, 3, 3);
        this.add(jScrollPane1, gridBagConstraints);
        setOpaque(false);
         
    }
}
