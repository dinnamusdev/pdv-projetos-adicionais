/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.config;

/**
 *
 * @author DSWM
 */
public class frmCarregandoChat extends javax.swing.JDialog {

    /**
     * Creates new form frmCarregandoChat
     */
    public frmCarregandoChat(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
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
        lblLogo = new javax.swing.JLabel();
        lblLogoProcessando = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblMensagem2 = new javax.swing.JLabel();
        lblMensagem1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(306, 168));
        setUndecorated(true);
        setSize(new java.awt.Dimension(306, 168));
        getContentPane().setLayout(new java.awt.GridBagLayout());

        PainelPrincipal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PainelPrincipal.setLayout(new java.awt.GridBagLayout());

        lblLogo.setBackground(new java.awt.Color(0, 0, 0));
        lblLogo.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/logodinnamus.JPG"))); // NOI18N
        lblLogo.setOpaque(true);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.1;
        PainelPrincipal.add(lblLogo, gridBagConstraints);

        lblLogoProcessando.setBackground(new java.awt.Color(255, 255, 204));
        lblLogoProcessando.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblLogoProcessando.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smack/ui/loading.gif"))); // NOI18N
        lblLogoProcessando.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        PainelPrincipal.add(lblLogoProcessando, gridBagConstraints);

        jPanel1.setLayout(new java.awt.GridBagLayout());

        lblMensagem2.setBackground(new java.awt.Color(255, 255, 204));
        lblMensagem2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lblMensagem2.setText("Conectando servidor....");
        lblMensagem2.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        jPanel1.add(lblMensagem2, gridBagConstraints);

        lblMensagem1.setBackground(new java.awt.Color(255, 255, 204));
        lblMensagem1.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        lblMensagem1.setText("Conectando servidor....");
        lblMensagem1.setToolTipText("");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        jPanel1.add(lblMensagem1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        PainelPrincipal.add(jPanel1, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
        gridBagConstraints.weightx = 0.3;
        gridBagConstraints.weighty = 0.1;
        getContentPane().add(PainelPrincipal, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void fechar(){
        this.dispose();
    }
    /**
     * @param args the command line arguments
     */
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelPrincipal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JLabel lblLogoProcessando;
    public javax.swing.JLabel lblMensagem1;
    public javax.swing.JLabel lblMensagem2;
    // End of variables declaration//GEN-END:variables
}
