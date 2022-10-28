/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * frmGerenciador.java
 *
 * Created on 20/08/2013, 11:06:18
 */
package ui;

import br.GerenciadorLicenca;
import br.com.log.LogDinnamus;
import br.com.ui.ItemLista;
import br.com.ui.MetodosUI_Auxiliares;
import br.entidades.LicencaDadosContratante;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.swing.JOptionPane;
import ui.rel.frmListagemLiberacoes;

/**
 *
 * @author Fernando
 */
public class frmGerenciador extends javax.swing.JFrame {

    /** Creates new form frmGerenciador */
    public frmGerenciador() {
        initComponents();
        setLocationRelativeTo(null);
      
    }
    public void  IniciarUI_dbgContratantes_Atualizar(){
        
        cbRepresentanteItemStateChanged(null);
        //dbgContratantes.setRsDados(GerenciadorLicenca.Contratantes_Listar());
    
    }
    public boolean IniciarCBRepresentante(){
        try {
            MetodosUI_Auxiliares.PreencherCombo(cbRepresentante, GerenciadorLicenca.Representante_Listar(), "razao", "idrepresentante",false, "string","ESCOLHA UM ITEM DA LISTA");
           
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    
    }
    public boolean IniciarUI(){
    
        try {
            HashMap <Object, Integer> colunas = new HashMap<Object, Integer>();

            colunas.put("nomefantasia", 100);
            colunas.put("cnpj", 100);
            colunas.put("razao", 300);                    
            dbgContratantes.setClColunas(colunas);            
            IniciarCBRepresentante();
            IniciarUI_dbgContratantes_Atualizar();
            
            
            
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        dbgContratantes = new br.com.ui.JTableDinnamuS();
        jPanel3 = new javax.swing.JPanel();
        cbRepresentante = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btEditarCancelar = new javax.swing.JButton();
        btEditarContratante = new javax.swing.JButton();
        btNovoContratante = new javax.swing.JButton();
        btLicenca = new javax.swing.JButton();
        btListagemLiberacao = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        dbgRepresentantes = new br.com.ui.JTableDinnamuS();
        jPanel6 = new javax.swing.JPanel();
        btExcluirRep = new javax.swing.JButton();
        btEditarRep = new javax.swing.JButton();
        btNovoRep = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GERENCIADOR DE LICENÇAS");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        dbgContratantes.setExibirBarra(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        cbRepresentante.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbRepresentanteItemStateChanged(evt);
            }
        });

        jLabel1.setText("Filtrar Por Representante");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cbRepresentante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbRepresentante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btEditarCancelar.setText("Excluir");
        btEditarCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarCancelarActionPerformed(evt);
            }
        });

        btEditarContratante.setText("Editar");
        btEditarContratante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarContratanteActionPerformed(evt);
            }
        });

        btNovoContratante.setText("Novo");
        btNovoContratante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoContratanteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btNovoContratante, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addGap(50, 50, 50)
                .addComponent(btEditarContratante, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addComponent(btEditarCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btNovoContratante)
                    .addComponent(btEditarContratante)
                    .addComponent(btEditarCancelar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btLicenca.setText("Contrato de Licença");
        btLicenca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLicencaActionPerformed(evt);
            }
        });

        btListagemLiberacao.setText("Listagem Liberação");
        btListagemLiberacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btListagemLiberacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btLicenca)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btListagemLiberacao))
                    .addComponent(dbgContratantes, javax.swing.GroupLayout.PREFERRED_SIZE, 744, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dbgContratantes, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btLicenca)
                    .addComponent(btListagemLiberacao))
                .addGap(104, 104, 104))
        );

        jTabbedPane1.addTab("CONTRATANTES", jPanel2);

        dbgRepresentantes.setExibirBarra(false);

        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btExcluirRep.setText("Excluir");
        btExcluirRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExcluirRepActionPerformed(evt);
            }
        });

        btEditarRep.setText("Editar");
        btEditarRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditarRepActionPerformed(evt);
            }
        });

        btNovoRep.setText("Novo");
        btNovoRep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNovoRepActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btNovoRep)
                .addGap(59, 59, 59)
                .addComponent(btEditarRep)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                .addComponent(btExcluirRep)
                .addGap(31, 31, 31))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btExcluirRep)
                    .addComponent(btEditarRep)
                    .addComponent(btNovoRep))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(dbgRepresentantes, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dbgRepresentantes, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
                .addGap(13, 13, 13))
        );

        jTabbedPane1.addTab("REPRESENTANTE", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 785, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
// TODO add your handling code here:
      if(!IniciarUI()){
            MetodosUI_Auxiliares.MensagemAoUsuarioSimples(null, "Não foi possível iniciar a interface com usuário", this.getTitle(), "AVISO");
            dispose();
     }
}//GEN-LAST:event_formWindowOpened

private void btNovoContratanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoContratanteActionPerformed
// TODO add your handling code here:
    (new frmNovoContratante(this, true,null)).setVisible(true);
    IniciarUI_dbgContratantes_Atualizar();
}//GEN-LAST:event_btNovoContratanteActionPerformed
private int RetornoContratanteAtual(){

    int nRet=0;
    try {        
        if(dbgContratantes.getTbDinnamuS().getLinhaAtual()>=0){
           nRet = Integer.valueOf( dbgContratantes.getTbDinnamuS().getValorCelula("idcontratante").toString());                
        }             
    } catch (Exception e) {
        LogDinnamus.Log(e, true);
  
    }
    return nRet;

}
    private void btEditarContratanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarContratanteActionPerformed
        // TODO add your handling code here:
        
        try {
            
            if(dbgContratantes.getTbDinnamuS().getLinhaAtual()>=0){
                int nIdContratante = Integer.valueOf( dbgContratantes.getTbDinnamuS().getValorCelula("idcontratante").toString());
                if(nIdContratante>0){
                    LicencaDadosContratante contatante = GerenciadorLicenca.Contratante_Pesquisar(nIdContratante);
                    frmNovoContratante c = new frmNovoContratante(this, true,contatante);                  
                    c.setVisible(true);
                    IniciarUI_dbgContratantes_Atualizar();
                }                
            }            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }//GEN-LAST:event_btEditarContratanteActionPerformed

    private void btEditarCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarCancelarActionPerformed
        // TODO add your handling code here:
         try {
            
            if(dbgContratantes.getTbDinnamuS().getLinhaAtual()>=0){
                int nIdContratante = Integer.valueOf( dbgContratantes.getTbDinnamuS().getValorCelula("idcontratante").toString());
                if(nIdContratante>0){
                     if(MetodosUI_Auxiliares.MensagemAoUsuarioOpcoes_Sim_e_Nao(this, "Confirma a exclusão do contratante ?", "Contratante")== MetodosUI_Auxiliares.Sim()){
                         ResultSet rsContrato = GerenciadorLicenca.Contrato_Listar(nIdContratante);
                         if(rsContrato.next())   {
                            if(rsContrato.getString("STATUS").equalsIgnoreCase("ATIVO")){ 
                               MetodosUI_Auxiliares.MensagemAoUsuarioSimplesAVISO(this, "Contratante selecionado possui um contrato de licenciamento ATIVO", getTitle());
                               return;
                            }
                         }
                         if(GerenciadorLicenca.Contratante_Excluir(nIdContratante)){
                             IniciarUI_dbgContratantes_Atualizar();
                             MetodosUI_Auxiliares.MensagemAoUsuarioSimplesINFO(this, "Contratante excluido com sucesso", getTitle());
                         }else{
                             MetodosUI_Auxiliares.MensagemAoUsuarioSimplesAVISO(this, "Não foi possível excluir contratante", getTitle());
                         }
                     }
                }                
            }            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }//GEN-LAST:event_btEditarCancelarActionPerformed

    private void btLicencaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLicencaActionPerformed
        // TODO add your handling code here:
        
        int nContratante = RetornoContratanteAtual();
        if(nContratante>0){
            String Contratante =  dbgContratantes.getTbDinnamuS().getValorCelula("razao").toString();
            (new frmLicencas(this, true,nContratante,Contratante)).setVisible(true);
        }
    }//GEN-LAST:event_btLicencaActionPerformed

    private void btNovoRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNovoRepActionPerformed
        // TODO add your handling code here:
         (new frmRepresentantes(this, true,0)).setVisible(true);
         IniciarUI_DbgRep_Atualizar();
         
    }//GEN-LAST:event_btNovoRepActionPerformed
    private int dbgrep_selecionar_atual(){
        int nRep =0;
        try {
            if(dbgRepresentantes.getTbDinnamuS().getLinhaAtual()>=0){
               nRep =Integer.parseInt(dbgRepresentantes.getTbDinnamuS().getValorCelula("idrepresentante").toString());
               
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return nRep;
    }
    private boolean IniciarUI_DbgRep(){
       try {
           
            HashMap <Object, Integer> colunas = new HashMap<Object, Integer>();
            
            colunas.put("cnpj", 100);
            colunas.put("razao", 300);                    
            dbgRepresentantes.setClColunas(colunas);            
            IniciarUI_DbgRep_Atualizar();
           return true;
       } catch (Exception e) {
           LogDinnamus.Log(e, true);
           return false;
       }
   }
    private boolean IniciarUI_DbgRep_Atualizar(){
       try {
           
            dbgRepresentantes.setRsDados(GerenciadorLicenca.Representante_Listar());
           return true;
       } catch (Exception e) {
           LogDinnamus.Log(e, true);
           return false;
       }
   }
    private void btEditarRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditarRepActionPerformed
        // TODO add your handling code here:
         try {
            
            int nRep = dbgrep_selecionar_atual();
            if(nRep>0){
              (new frmRepresentantes(this, true,nRep)).setVisible(true);
              IniciarUI_DbgRep_Atualizar();
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }//GEN-LAST:event_btEditarRepActionPerformed

    private void btExcluirRepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExcluirRepActionPerformed
        // TODO add your handling code here:
           try {
            
            int nRep = dbgrep_selecionar_atual();
            if(nRep>0){
              if(MetodosUI_Auxiliares.MensagemAoUsuarioOpcoes_Sim_e_Nao(this, "CONFIRMA A EXCLUSÃO DO REPRESENTANTE ?", getTitle()) ==MetodosUI_Auxiliares.Sim() ){
                 if(GerenciadorLicenca.Representante_Excluir(nRep)){
                     IniciarUI_DbgRep_Atualizar();
                     MetodosUI_Auxiliares.MensagemAoUsuarioSimplesINFO(this, "REPRESENTANTE EXCLUÍDO COM SUCESSO", getTitle());
                 }else{
                     MetodosUI_Auxiliares.MensagemAoUsuarioSimplesAVISO(this, "NÃO FOI POSSÍVEL EXCLUIR O REPRESENTANTE", getTitle());
                 }
              }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }//GEN-LAST:event_btExcluirRepActionPerformed
    private void IniciarDBG_Financeiro(int nContrato){
    
    }
    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        // TODO add your handling code here:
        if(jTabbedPane1.getSelectedIndex()==1){
            IniciarUI_DbgRep();
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void btListagemLiberacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListagemLiberacaoActionPerformed
        // TODO add your handling code here:
        new frmListagemLiberacoes(this, true).setVisible(true);
    }//GEN-LAST:event_btListagemLiberacaoActionPerformed

    private void cbRepresentanteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbRepresentanteItemStateChanged
        // TODO add your handling code here:
        try {
            
            ItemLista i =   (ItemLista) cbRepresentante.getSelectedItem();
            if(i!=null){
                Integer nCodigoRep = new Integer(  i.getIndice().toString());
                if(nCodigoRep>0){
                   dbgContratantes.setRsDados(GerenciadorLicenca.Contratantes_Listar(0,nCodigoRep));
                }else{
                   dbgContratantes.setRsDados(GerenciadorLicenca.Contratantes_Listar());
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }//GEN-LAST:event_cbRepresentanteItemStateChanged

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEditarCancelar;
    private javax.swing.JButton btEditarContratante;
    private javax.swing.JButton btEditarRep;
    private javax.swing.JButton btExcluirRep;
    private javax.swing.JButton btLicenca;
    private javax.swing.JButton btListagemLiberacao;
    private javax.swing.JButton btNovoContratante;
    private javax.swing.JButton btNovoRep;
    private javax.swing.JComboBox cbRepresentante;
    private br.com.ui.JTableDinnamuS dbgContratantes;
    private br.com.ui.JTableDinnamuS dbgRepresentantes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JTabbedPane jTabbedPane1;
    // End of variables declaration//GEN-END:variables
}
