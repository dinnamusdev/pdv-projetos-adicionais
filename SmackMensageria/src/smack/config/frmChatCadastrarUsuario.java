/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.config;

import br.com.log.LogDinnamus;
import br.com.ui.MetodosUI_Auxiliares_1;
import br.manipulararquivos.ManipulacaoArquivo;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author DSWM
 */
public class frmChatCadastrarUsuario extends javax.swing.JDialog {

    /**
     * Creates new form frmChat
     */
   
  
    private String usuario, senha, email;
    public boolean ok;
    public String nomeArquivoAvatar;
    public frmChatCadastrarUsuario(java.awt.Frame parent, boolean modal,
            String usuario, String nome, String senha) {
        super(parent, modal);
        initComponents();
        if(usuario!=null){
            this.txtUsuario1.setText(usuario);
            this.txtUsuario1.setEditable(false);
            this.txtUsuarioNome.setText(nome);
            this.txtSenha.setText(senha);
            this.txtSenha1.setText(senha);
        }
        setLocationRelativeTo(null);
        txtUsuario1.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        PainelPrincipal = new javax.swing.JPanel();
        PainelTitulo = new javax.swing.JPanel();
        lblTitulo = new javax.swing.JLabel();
        btFechar1 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        PainelCorpo = new javax.swing.JPanel();
        PainelUsuario = new javax.swing.JPanel();
        btOk = new javax.swing.JButton();
        PainelAvatar = new javax.swing.JPanel();
        btCarregarFoto = new javax.swing.JButton();
        lblUsuarioFoto = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtUsuarioNome = new javax.swing.JTextField();
        txtUsuario1 = new javax.swing.JTextField();
        lblSenha = new javax.swing.JLabel();
        txtSenha = new javax.swing.JPasswordField();
        lblUsuario = new javax.swing.JLabel();
        lblSenha1 = new javax.swing.JLabel();
        lblUsuario2 = new javax.swing.JLabel();
        txtSenha1 = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(429, 263));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(429, 263));
        setSize(new java.awt.Dimension(429, 263));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        PainelPrincipal.setLayout(new java.awt.GridBagLayout());

        PainelTitulo.setBackground(new java.awt.Color(255, 255, 204));
        PainelTitulo.setBorder(javax.swing.BorderFactory.createEtchedBorder(new java.awt.Color(204, 204, 204), null));
        PainelTitulo.setLayout(new java.awt.GridBagLayout());

        lblTitulo.setBackground(new java.awt.Color(255, 255, 204));
        lblTitulo.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTitulo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/icons8-colaboração-48.png"))); // NOI18N
        lblTitulo.setText("Usuário");
        lblTitulo.setToolTipText("");
        lblTitulo.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.7;
        gridBagConstraints.weighty = 0.1;
        PainelTitulo.add(lblTitulo, gridBagConstraints);

        btFechar1.setBackground(new java.awt.Color(255, 255, 204));
        btFechar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btFechar1.setForeground(new java.awt.Color(255, 255, 255));
        btFechar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/Shut down_16x16.png"))); // NOI18N
        btFechar1.setMnemonic('F');
        btFechar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFechar1ActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        PainelTitulo.add(btFechar1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weightx = 0.1;
        PainelTitulo.add(jLabel5, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        PainelPrincipal.add(PainelTitulo, gridBagConstraints);

        PainelCorpo.setBackground(new java.awt.Color(255, 255, 255));
        PainelCorpo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PainelCorpo.setLayout(new java.awt.GridBagLayout());

        PainelUsuario.setLayout(new java.awt.GridBagLayout());

        btOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/Yes.png"))); // NOI18N
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        PainelUsuario.add(btOk, gridBagConstraints);

        PainelAvatar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PainelAvatar.setLayout(new java.awt.GridBagLayout());

        btCarregarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/icons8-gestão-de-cliente-32.png"))); // NOI18N
        btCarregarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCarregarFotoActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.VERTICAL;
        PainelAvatar.add(btCarregarFoto, gridBagConstraints);

        lblUsuarioFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        PainelAvatar.add(lblUsuarioFoto, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 10;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 0.2;
        PainelUsuario.add(PainelAvatar, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(txtUsuarioNome, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(txtUsuario1, gridBagConstraints);

        lblSenha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/icons8-chave-32.png"))); // NOI18N
        lblSenha.setText("Senha");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblSenha, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(txtSenha, gridBagConstraints);

        lblUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/icons8-usuário-30.png"))); // NOI18N
        lblUsuario.setText("Usuario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblUsuario, gridBagConstraints);

        lblSenha1.setText("Redigite");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblSenha1, gridBagConstraints);

        lblUsuario2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/icons8-autógrafo-32.png"))); // NOI18N
        lblUsuario2.setText("Nome");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        jPanel1.add(lblUsuario2, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 5);
        jPanel1.add(txtSenha1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.2;
        PainelUsuario.add(jPanel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 11;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        PainelUsuario.add(jSeparator2, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        PainelCorpo.add(PainelUsuario, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 1.2;
        PainelPrincipal.add(PainelCorpo, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(PainelPrincipal, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btFechar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFechar1ActionPerformed

        this.dispose();
        

    }//GEN-LAST:event_btFechar1ActionPerformed
    private boolean conectado = false;
    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        // TODO add your handling code here:if
        try {
            if(MetodosUI_Auxiliares_1.MensagemAoUsuarioOpcoes_Sim_e_Nao("Confirma a gravação do usuário", "Usuário")==MetodosUI_Auxiliares_1.Sim()){
                if(txtUsuario1.getText().contains( " ")){
                   MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesAVISO("o usuário não pode ter espaço", "Usuário inválido");
                   return;
                }
                
                if(txtUsuario1.getText().trim().length()==0){
                   MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesAVISO("o usuário não foi informado", "Usuário inválido");
                   return;
                }
                
                if(txtUsuarioNome.getText().trim().length()==0){
                   MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesAVISO("o nome não foi informado", "Nome inválido");
                   return;
                }
                
                String senha = new String(txtSenha.getPassword());
                if(senha.length()<3){
                   MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesAVISO("A senha precisa ter pelo menos 6 caracteres", "Senha inválida");
                   return;
                }
                
                String senha2 = new String(txtSenha1.getPassword());
                if(senha == null ? senha2 != null : !senha.equals(senha2) ){
                   MetodosUI_Auxiliares_1.MensagemAoUsuarioSimplesAVISO("As senhas digitadas não conferem", "Senha inválida");
                   return;
                }
               
                ok=true;
                this.dispose();
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
     
    }//GEN-LAST:event_btOkActionPerformed
    public void atualizarFoto(ImageIcon img){
     try {
              //ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(93,107, Image.SCALE_DEFAULT));
              lblUsuarioFoto.setIcon(img);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }
    public void atualizarFoto(byte[] img){
   
        try {
              ImageIcon imageIcon = new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(93,107, Image.SCALE_DEFAULT));
              lblUsuarioFoto.setIcon(imageIcon);
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }
    private void btCarregarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCarregarFotoActionPerformed
        // TODO add your handling code here:
        try {
            String avatar = MetodosUI_Auxiliares_1.ListarArquivosPorExtensao(null, ManipulacaoArquivo.DiretorioDeTrabalho(), "*.jpg;*.gif", "Informe o arquivo");
            if (avatar.trim().length() > 0) {
                nomeArquivoAvatar = avatar;
                
                ImageIcon imageIcon = new ImageIcon(new ImageIcon(avatar).getImage().getScaledInstance(93,107, Image.SCALE_DEFAULT));
                lblUsuarioFoto.setIcon(imageIcon);
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
     
    }//GEN-LAST:event_btCarregarFotoActionPerformed
   

     
    
   

     

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JPanel PainelAvatar;
    private javax.swing.JPanel PainelCorpo;
    private javax.swing.JPanel PainelPrincipal;
    private javax.swing.JPanel PainelTitulo;
    private javax.swing.JPanel PainelUsuario;
    private javax.swing.JButton btCarregarFoto;
    private javax.swing.JButton btFechar1;
    private javax.swing.JButton btOk;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lblSenha;
    private javax.swing.JLabel lblSenha1;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel lblUsuario2;
    public javax.swing.JLabel lblUsuarioFoto;
    public javax.swing.JPasswordField txtSenha;
    public javax.swing.JPasswordField txtSenha1;
    public javax.swing.JTextField txtUsuario1;
    public javax.swing.JTextField txtUsuarioNome;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the smackListItem
     */
    
}