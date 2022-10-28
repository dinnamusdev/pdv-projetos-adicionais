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
public class ItemContatoUI extends JPanel{
     public javax.swing.JLabel LblStatus;
    public javax.swing.JLabel lblQtMensagemNaoLidas;
    public javax.swing.JLabel lblUltimaMensagem;
    public javax.swing.JLabel lblUsuario;

    public ItemContatoUI() {
   
        java.awt.GridBagConstraints gridBagConstraints;

        lblUsuario = new javax.swing.JLabel();
        LblStatus = new javax.swing.JLabel();
        lblUltimaMensagem = new javax.swing.JLabel();
        lblQtMensagemNaoLidas = new javax.swing.JLabel();

        //setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        this.setLayout(new java.awt.GridBagLayout());

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/icons8-usuário-30.png"))); // NOI18N
        lblUsuario.setText("usuario");
       
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.5;
         gridBagConstraints.insets = new java.awt.Insets(35, 5, 5, 5);
        this.add(lblUsuario, gridBagConstraints);

        LblStatus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/online.png"))); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
         gridBagConstraints.insets = new java.awt.Insets(35, 5, 5, 5);
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        this.add(LblStatus, gridBagConstraints);

        lblUltimaMensagem.setText("ultimamensagem");
        lblUltimaMensagem.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.5;
         gridBagConstraints.insets = new java.awt.Insets(5, 5, 25, 5);
        this.add(lblUltimaMensagem, gridBagConstraints);

        lblQtMensagemNaoLidas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lblQtMensagemNaoLidas.setForeground(new java.awt.Color(255, 255, 255));
        lblQtMensagemNaoLidas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblQtMensagemNaoLidas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/icons8-envelope-32.png"))); // NOI18N
        lblQtMensagemNaoLidas.setText("9");
        lblQtMensagemNaoLidas.setToolTipText("");
        lblQtMensagemNaoLidas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_END;
         gridBagConstraints.insets = new java.awt.Insets(5, 5,25, 5);
        this.add(lblQtMensagemNaoLidas, gridBagConstraints);
        setOpaque(true);
        lblUsuario.setOpaque(true);
    }
}
