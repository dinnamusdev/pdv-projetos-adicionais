/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * principal.java
 *
 * Created on 19/07/2010, 00:22:34
 */

package br.com.retaguarda.ui;

/**
 *
 * @author dti
 */
public class principal extends javax.swing.JFrame {

    /** Creates new form principal */
    public principal() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miGerarCarga = new javax.swing.JMenuItem();
        miLerCargaPdv = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Retaguarda");

        miGerarCarga.setText("Gerar Carga Inicial PDV");
        miGerarCarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miGerarCargaActionPerformed(evt);
            }
        });
        jMenu1.add(miGerarCarga);

        miLerCargaPdv.setText("Ler Carga PDV");
        miLerCargaPdv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLerCargaPdvActionPerformed(evt);
            }
        });
        jMenu1.add(miLerCargaPdv);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sair");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void miGerarCargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miGerarCargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miGerarCargaActionPerformed

    private void miLerCargaPdvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLerCargaPdvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_miLerCargaPdvActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem miGerarCarga;
    private javax.swing.JMenuItem miLerCargaPdv;
    // End of variables declaration//GEN-END:variables

}
