/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ui;

import br.com.log.LogDinnamus;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author dti
 */


public class JTableDinnamuS extends javax.swing.JPanel {
    public int Alinhamento_Direita = SwingConstants.RIGHT;
    public int Alinhamento_Centro = SwingConstants.CENTER;
    public int Alinhamento_Esqueda = SwingConstants.LEFT;
    private  boolean AjustarColunas=false;
    private ArrayList<String> clColunasEditaveis=new ArrayList<String>();
    private JTable jTable=new JTable();
    private JScrollPane jScrollPane= new javax.swing.JScrollPane();
    private JToolBar jToolBar1 = new javax.swing.JToolBar();
    private JButton btPrimeiro = new javax.swing.JButton();
    private JButton btAnterior = new javax.swing.JButton();
    private JButton btProximo = new javax.swing.JButton();
    private JButton btUltimo = new javax.swing.JButton();
    private JLabel lblPagina = new javax.swing.JLabel();
    
    private Integer TotalPaginas=0;
    private Integer PaginaAtual=0;
    private String CampoChavePrimaria;
    
    private Integer nTamanhoPagina;
    private String StringConsulta;
    //private AbstractTableModel ModeltbDinnamuS=new TbDinnamuS();
    private TbDinnamuS tbDinnamuS= new TbDinnamuS();  
    private TbDinnamuSObject modelo;
    private ResultSet rsDados;
    private boolean ExibirBarra=false;
    private HashMap<String, NumberFormat> numberFormat=new HashMap<String, NumberFormat>();
    private HashMap<String, DateFormat> dateFormat=new HashMap<String, DateFormat>();
    private HashMap<String,Integer> alinhamentos = new HashMap<String, Integer>();
    private HashMap<Object,Integer> clColunas=new HashMap<Object, Integer>();    
    private HashMap<Object,String> clColunasExibir = new HashMap<Object, String>();
    private int[] TamColunas;
    private boolean bAjustouColunas=false;
    private String Edicao_ChavePrimaria ="";
    private String Edicao_ChaveEstrangeira ="";
    private String Edicao_Tabela="";
    private Connection Edicao_Cnx = null;
    private String Edicao_Query ="";
    private ArrayList<Object> Colunas = new ArrayList<Object>();
    private boolean ColunaComTamanhosEmPercentual = false;
    private TreeMap<String,String> ColunasNaoVinculadas = new TreeMap<String, String>();
    private TreeMap<String,Object> ColunasComEditoresEspeciais = new TreeMap<String, Object>();
    private ArrayList<String> ColunasInvisiveis = new ArrayList<String>();
    private ArrayList<String> ColunasBotoes = new ArrayList<String>();
    private TreeMap<String, JTableDinnamuS_SubTabela> SubTabelas = new TreeMap<String, JTableDinnamuS_SubTabela>();
    private TreeMap<String, String> SubTabelas_ChavePrimarias = new TreeMap<String, String>();
    private TreeMap<String, Integer> TipoJDBC = new TreeMap<String, Integer>();
    private TreeMap<String,Integer> CampoNumerico_CasaDecimais = new TreeMap<String, Integer>();
    //private TreeMap<String, String> SubTabelas_ChaveEstrageira = new TreeMap<String, String>();
    private boolean AjustaColunaAoPainel = false;
    //private TreeMap<String,String> ColunasNaoVinculadas = new TreeMap<String, String>();
    public JTableDinnamuS()
    {            
        initComponents();        
        DefinirTipoSelecao();
        tbDinnamuS =new TbDinnamuS();
        getjTable().getTableHeader().setResizingAllowed(false);
        getjTable().getTableHeader().setReorderingAllowed(false);
    }
    private NumberFormat NumberFormat_Padrao_Moeada = DecimalFormat.getCurrencyInstance();
    private NumberFormat NumberFormat_Padrao  =  new DecimalFormat("#,##0.00");
    private DateFormat DateFormat_Padrao = new SimpleDateFormat("dd/MM/yyyy");
    public DateFormat DateFormat_ddmmyy_hhmmss = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    public Object getValorCelula(String NomeCampo){
        return tbDinnamuS.getValorCelula(NomeCampo);
    }
    public int LinhaAtualModel(){
        
        return tbDinnamuS.getLinhaAtual();
        
    }
    public int ColunaAtualModel(){
        return tbDinnamuS.getColunaAtual();
    }
    public void initComponents()
    {        
       
        java.awt.GridBagConstraints gridBagConstraints = null;
        jToolBar1.setRollover(true);
        getjTable().addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent evt) {
               
                //AjustarColunas();
            }
        });
        //jScrollPane.add();
        jScrollPane.setViewportView(getjTable());
        btPrimeiro.setText("<< Primeiro");;
        btPrimeiro.setFocusable(false);
        btPrimeiro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btPrimeiro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPrimeiroActionPerformed(evt);
            }
        });
        jToolBar1.add(btPrimeiro);

        btAnterior.setText("< Anterior");
        btAnterior.setFocusable(false);
        btAnterior.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btAnterior.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnteriorActionPerformed(evt);
            }
        });
        jToolBar1.add(btAnterior);

        btProximo.setText("Próximo >");
        btProximo.setFocusable(false);
        btProximo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btProximo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProximoActionPerformed(evt);
            }
        });
        jToolBar1.add(btProximo);

        btUltimo.setText("Último >>");
        btUltimo.setFocusable(false);
        btUltimo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btUltimo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btUltimoActionPerformed(evt);
            }
        });
        
        jToolBar1.add(btUltimo);
        lblPagina.setText("Pagina:");
        jToolBar1.add(lblPagina); 
        
        java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
        this.setLayout(layout);
       
        getjTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //getjTable().setAutoscrolls(bAjustouColunas);
        
        
        jScrollPane.setBackground(Color.WHITE);
        jScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
         add(jScrollPane, gridBagConstraints);
         
        if(isExibirBarra()){
           jToolBar1.setVisible(true);           
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 1;
            gridBagConstraints.weightx = 0;
            gridBagConstraints.weighty = 0;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
             add(jToolBar1, gridBagConstraints);
        
        }
       
        
    }
    /*public boolean IniciarTabela(String StringSQL, Integer nTamanhoPagina, String cCampoChavePrimaria, Integer nTotalRegistros)
    {
        return IniciarTabela( StringSQL,  nTamanhoPagina, cCampoChavePrimaria, nTotalRegistros,false);
    }
    public boolean IniciarTabela(String StringSQL, Integer nTamanhoPagina, String cCampoChavePrimaria, Integer nTotalRegistros, boolean bForcaConsultaRepositorio)
    {
        boolean bRetorno=false;
        try {
            Float nTotalPaginaFlutuante=Float.valueOf(0);
            int nTotalPagina=0;
            setCampoChavePrimaria(cCampoChavePrimaria);
            setnTamanhoPagina(nTamanhoPagina);
            setStringConsulta(StringSQL);

            nTotalPaginaFlutuante= Float.parseFloat(nTotalRegistros.toString())/ Float.parseFloat(getnTamanhoPagina().toString()); //  Float.valueOf(StringSQL.valueOf(nTotalRegistros/getnTamanhoPagina()));
            //System.out.print(nTotalPaginaFlutuante);
            if (nTotalPaginaFlutuante.intValue()!= nTotalPaginaFlutuante)
                nTotalPagina = nTotalPaginaFlutuante.intValue()+1;

            setTotalPaginas(nTotalPagina<1 ? 1 : nTotalPagina);
            setPaginaAtual(1);

            setRsDados(Dao_Generica.Pesquisar(StringSQL, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY ,nTamanhoPagina,cCampoChavePrimaria,0,"avanço",1,( !bForcaConsultaRepositorio  ? Sistema.isOnline() : false ) ));

          
            bRetorno=true;

        } catch (Exception ex) {
                LogDinnamus.Log(ex);
        }
        return bRetorno;


    }*/
    private void DefinirTipoSelecao(){
        try {
           if(jTable.getSelectionModel()==null){
                jTable.setSelectionModel(
                            new DefaultListSelectionModel()
                            {
                                @Override
                                public void setSelectionMode(int selectionMode) {
                                    //super.setSelectionMode(selectionMode);
                                    this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                                }
                             }
                     );
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }

    @SuppressWarnings("static-access")
    private void btProximoActionPerformed(java.awt.event.ActionEvent evt) {

        if(getPaginaAtual()<getTotalPaginas())
        {
            try {
                // TODO add your handling code here:
                //JTableDinnamuS jt = new JTableDinnamuS(tbDinnamuS);
                int nValorChave=0;
                //setRsDados(Dao_Generica.Pesquisar(getStringConsulta(), ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY ,getnTamanhoPagina(), getCampoChavePrimaria(),nValorChave,"avanço", getPaginaAtual()+1,Sistema.isOnline()));
                AtualizarModelo(getRsDados());
                setPaginaAtual(getPaginaAtual()+1);

            } catch (Exception ex) {
                LogDinnamus.Log(ex);
            }
        }
    }

    @SuppressWarnings("static-access")
    private void btAnteriorActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(getPaginaAtual()>1)
        {
            try {
                // TODO add your handling code here:
                ResultSet rs=null;
                int nValorChave=0;
                //if(getRsDados().first())
                //{
                    nValorChave=0;//getRsDados().getInt(getCampoChavePrimaria());
                    //rs=Dao_Generica.Pesquisar(getStringConsulta(), ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY ,getnTamanhoPagina(), getCampoChavePrimaria(),nValorChave,"voltar",getPaginaAtual()-1,Sistema.isOnline());
                    //if(rs.next()){
                        setRsDados(rs);
                        AtualizarModelo(getRsDados());
                        setPaginaAtual(getPaginaAtual()-1);
                    //}
                ////}
            } catch (Exception ex) {
                LogDinnamus.Log(ex);
            }
        }
    }

    @SuppressWarnings("static-access")
    private void btUltimoActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(getTotalPaginas()>1)
        {
            try {
                // TODO add your handling code here:
                ResultSet rs=null;
               // rs=Dao_Generica.Pesquisar(getStringConsulta(), ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY ,getnTamanhoPagina(), getCampoChavePrimaria(),0,"",getTotalPaginas(),Sistema.isOnline());
                //if(rs.next()){
                setRsDados(rs);
                AtualizarModelo(getRsDados());
                setPaginaAtual(getTotalPaginas());
                // }
            } catch (Exception ex) {
                LogDinnamus.Log(ex);
            }
        } 
    } 
    public TbDinnamuSObject getModeloObjeto(){
        return this.modelo ;
    }
    public void setModeloObjeto(TbDinnamuSObject modelo){
          this.modelo = modelo;
          modelo.setColunas(Colunas);
          getjTable().setModel(modelo);
          AtualizarModeloObjetos();
          AjustarColunas();  
    }
    public boolean AtualizarModeloObjetos()
    {
        boolean bRet=false;
        this.modelo .setNumberFormat(getNumberFormat());
        this.modelo .setDateFormat(getDateFormat());
        try {
           
            if(getClColunas().size()>0){
                String cNomeColuna="";
                Integer nTamColuna=0; 
                for(int i=0;i<getjTable().getColumnModel().getColumnCount(); i++){
                    //TableColumn tableColumn = getjTable().getColumnModel().getColumn(i);
                    //cNomeColuna=getjTable().getColumnModel().getColumn(i).getHeaderValue().toString();
                    //getjTable().getColumnModel().getColumn(i).setPreferredWidth( nTamColuna  );  
                    cNomeColuna=modelo.getColumnName(i);
                    getjTable().getColumnModel().getColumn(i).setIdentifier(i);
                    if(!Colunas.contains(cNomeColuna.toLowerCase())){                      
                        getjTable().getColumnModel().getColumn(i).setMinWidth(0);
                        getjTable().getColumnModel().getColumn(i).setMaxWidth(0);
                    }else{                        
                            //cNomeColuna=getjTable().getColumnModel().getColumn(i).getHeaderValue().toString();
                            nTamColuna=clColunas.get(cNomeColuna.toLowerCase());
                            
                            if(clColunasExibir!=null){
                                if(clColunasExibir.size()>0){
                                    String ColunaExibir=clColunasExibir.get(cNomeColuna.toLowerCase());
                                    getjTable().getColumnModel().getColumn(i).setHeaderValue(ColunaExibir);                                    
                                    if(ColunasComEditoresEspeciais.containsKey(cNomeColuna)){                                        
                                        if(ColunasComEditoresEspeciais.get(cNomeColuna).getClass().getName()=="javax.swing.JCheckBox"){
                                           getjTable().getColumnModel().getColumn(i).setCellEditor( Render_E_Editor_Personalizado.EditorCheckBox());
                                           getjTable().getColumnModel().getColumn(i).setCellRenderer( Render_E_Editor_Personalizado.RenderCheckBox_2());                                            
                                           
                                        }else if(ColunasComEditoresEspeciais.get(cNomeColuna).getClass().getName()=="javax.swing.JButton"){
                                            //JComboBox<ItemLista> combo = (JComboBox<ItemLista>) ColunasComEditoresEspeciais.get(cNomeColuna);
                                            JButton b = (JButton)ColunasComEditoresEspeciais.get(cNomeColuna);
                                            getjTable().getColumnModel().getColumn(i).setCellEditor( Render_E_Editor_Personalizado.EditorBotao(b));//getjTable().getColumn(i).                                            
                                            getjTable().getColumnModel().getColumn(i).setCellRenderer( Render_E_Editor_Personalizado.RenderBotao(b) );//getjTable().getColumn(i).
                                        }else if(ColunasComEditoresEspeciais.get(cNomeColuna).getClass().getName()=="javax.swing.JComboBox"){
                                            
                                            JComboBox<ItemLista> combo = (JComboBox<ItemLista>) ColunasComEditoresEspeciais.get(cNomeColuna);
                                            getjTable().getColumnModel().getColumn(i).setCellEditor(Render_E_Editor_Personalizado.EditorComboBox(combo));                                            
                                        }else if(ColunasComEditoresEspeciais.get(cNomeColuna).getClass().getName()=="java.util.TreeMap"){                                            
                                            
                                            DefaultTableCellRender_Imagens cellRender_JTableDinnamus = new DefaultTableCellRender_Imagens((TreeMap<Object,ImageIcon>) ColunasComEditoresEspeciais.get(cNomeColuna));
                                            getjTable().getColumnModel().getColumn(i).setCellRenderer( cellRender_JTableDinnamus );//getjTable().getColumn(i).
                                            //getjTable().getColumnModel().getColumn(i).setCellRenderer( Render_E_Editor_Personalizado.RenderImagens_2((TreeMap<Object,ImageIcon>) ColunasComEditoresEspeciais.get(cNomeColuna)) );
                                            
                                        }
                                    }else{
                                        if(SubTabelas.containsKey(cNomeColuna)){
                                           getjTable().getColumnModel().getColumn(i).setCellRenderer( Render_E_Editor_Personalizado.RenderTabela( SubTabelas.get(cNomeColuna) ));
                                        }
                                   }
                                    
                                }
                            }
                    }
                }
                DefinirAlinhamentos();
                //getjTable().updateUI();
                //this.doLayout();
            }
        } catch (Exception ex) {
            LogDinnamus.Log(ex);
        }
        return bRet;
    }
     public boolean AtualizarModelo(ResultSet rs)
    {
        boolean bRet=false;

        try {
            //boolean bTemRegistros = this.rsDados.next();
            boolean ModeloUsandoColecao = getTbDinnamuS().isModeloUsandoColecao();
            setTbDinnamuS(new TbDinnamuS());
            getTbDinnamuS().setRs(rs);
            getTbDinnamuS().setModeloUsandoColecao(ModeloUsandoColecao);
            getTbDinnamuS().setNumberFormat(getNumberFormat());
            getTbDinnamuS().setDateFormat(getDateFormat());
            getTbDinnamuS().setColunasInvisiveis(getColunasInvisiveis());
            getTbDinnamuS().setColunas(Colunas);
            getTbDinnamuS().setClColunasEditaveis(clColunasEditaveis);
            getTbDinnamuS().setEdicao_Cnx(Edicao_Cnx);
            getTbDinnamuS().setEdicao_Query(Edicao_Query);
            getTbDinnamuS().setTabela_Edicao(Edicao_Tabela);
            getTbDinnamuS().setChavePrimaria(Edicao_ChavePrimaria);
            getTbDinnamuS().setChaveEstrangeira(Edicao_ChaveEstrangeira);
            getTbDinnamuS().setColunasNaoVinculadas(getColunasNaoVinculadas());
            getTbDinnamuS().setSubTabelas(SubTabelas);
            getTbDinnamuS().setColunasBotoes(ColunasBotoes);
            getTbDinnamuS().setTipoJDBC(TipoJDBC);
            getTbDinnamuS().setCampoNumerico_CasaDecimais(CampoNumerico_CasaDecimais);
            //getTbDinnamuS().setSubTabelas_ChaveEstrageira(SubTabelas_ChaveEstrageira);
            getTbDinnamuS().setSubTabelas_ChavePrimarias(SubTabelas_ChavePrimarias);
            getjTable().setModel(getTbDinnamuS());
            //getTbDinnamuS().fireTableStructureChanged();
            if(getClColunas().size()>0){
                String cNomeColuna="";
                Integer nTamColuna=0; 
                for(int i=0;i<getjTable().getColumnModel().getColumnCount(); i++){
                    //TableColumn tableColumn = getjTable().getColumnModel().getColumn(i);
                    //cNomeColuna=getjTable().getColumnModel().getColumn(i).getHeaderValue().toString();
                    //getjTable().getColumnModel().getColumn(i).setPreferredWidth( nTamColuna  );  
                    cNomeColuna=tbDinnamuS.getColumnName(i);
                    getjTable().getColumnModel().getColumn(i).setIdentifier(i);
                    if(!Colunas.contains(cNomeColuna.toLowerCase())){                      
                        getjTable().getColumnModel().getColumn(i).setMinWidth(0);
                        getjTable().getColumnModel().getColumn(i).setMaxWidth(0);
                    }else{                        
                            cNomeColuna=getjTable().getColumnModel().getColumn(i).getHeaderValue().toString();
                            nTamColuna=clColunas.get(cNomeColuna.toLowerCase());
                            
                            if(clColunasExibir!=null){
                                if(clColunasExibir.size()>0){
                                    String ColunaExibir=clColunasExibir.get(cNomeColuna.toLowerCase());
                                    getjTable().getColumnModel().getColumn(i).setHeaderValue(ColunaExibir);                                    
                                    if(ColunasComEditoresEspeciais.containsKey(cNomeColuna)){                                        
                                        if(ColunasComEditoresEspeciais.get(cNomeColuna).getClass().getName()=="javax.swing.JCheckBox"){
                                           getjTable().getColumnModel().getColumn(i).setCellEditor( Render_E_Editor_Personalizado.EditorCheckBox());
                                           getjTable().getColumnModel().getColumn(i).setCellRenderer( Render_E_Editor_Personalizado.RenderCheckBox_2());                                            
                                           
                                        }else if(ColunasComEditoresEspeciais.get(cNomeColuna).getClass().getName()=="javax.swing.JButton"){
                                            //JComboBox<ItemLista> combo = (JComboBox<ItemLista>) ColunasComEditoresEspeciais.get(cNomeColuna);
                                            JButton b = (JButton)ColunasComEditoresEspeciais.get(cNomeColuna);
                                            getjTable().getColumnModel().getColumn(i).setCellEditor( Render_E_Editor_Personalizado.EditorBotao(b));//getjTable().getColumn(i).                                            
                                            getjTable().getColumnModel().getColumn(i).setCellRenderer( Render_E_Editor_Personalizado.RenderBotao(b) );//getjTable().getColumn(i).
                                        }else if(ColunasComEditoresEspeciais.get(cNomeColuna).getClass().getName()=="javax.swing.JComboBox"){
                                            
                                            JComboBox<ItemLista> combo = (JComboBox<ItemLista>) ColunasComEditoresEspeciais.get(cNomeColuna);
                                            getjTable().getColumnModel().getColumn(i).setCellEditor(Render_E_Editor_Personalizado.EditorComboBox(combo));                                            
                                        }else if(ColunasComEditoresEspeciais.get(cNomeColuna).getClass().getName()=="java.util.TreeMap"){                                            
                                            
                                            DefaultTableCellRender_Imagens cellRender_JTableDinnamus = new DefaultTableCellRender_Imagens((TreeMap<Object,ImageIcon>) ColunasComEditoresEspeciais.get(cNomeColuna));
                                            getjTable().getColumnModel().getColumn(i).setCellRenderer( cellRender_JTableDinnamus );//getjTable().getColumn(i).
                                            //getjTable().getColumnModel().getColumn(i).setCellRenderer( Render_E_Editor_Personalizado.RenderImagens_2((TreeMap<Object,ImageIcon>) ColunasComEditoresEspeciais.get(cNomeColuna)) );
                                            
                                        }
                                    }else{
                                        if(SubTabelas.containsKey(cNomeColuna)){
                                           getjTable().getColumnModel().getColumn(i).setCellRenderer( Render_E_Editor_Personalizado.RenderTabela( SubTabelas.get(cNomeColuna) ));
                                        }
                                   }
                                    
                                }
                            }
                    }
                }
                DefinirAlinhamentos();
                //getjTable().updateUI();
                //this.doLayout();
            }
        } catch (Exception ex) {
            LogDinnamus.Log(ex);
        }
        return bRet;
    }
    public boolean AtualizarModelo(){
        return  AtualizarModelo(getRsDados());
    }
    
    @SuppressWarnings("static-access")
    private void btPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(getTotalPaginas()>1)
        {
            try {
                // TODO add your handling code here:
                ResultSet rs=null;
                //rs=Dao_Generica.Pesquisar(getStringConsulta(), ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY ,getnTamanhoPagina(), getCampoChavePrimaria(),0,"",1,Sistema.isOnline());
                //if(rs.next()){
                setRsDados(rs);
                AtualizarModelo(getRsDados());
                setPaginaAtual(1);
                // }
            } catch (Exception ex) {
                LogDinnamus.Log(ex);
            }
        }
    }

    /**
     * @return the TotalPaginas
     */
    public Integer getTotalPaginas() {
        return TotalPaginas;
    }

    /**
     * @param TotalPaginas the TotalPaginas to set
     */
    public void setTotalPaginas(Integer TotalPaginas) {
        this.TotalPaginas = TotalPaginas;
    }

    /**
     * @return the PaginaAtual
     */
    public Integer getPaginaAtual() {
        return PaginaAtual;
    }

    /**
     * @param PaginaAtual the PaginaAtual to set
     */
    public void setPaginaAtual(Integer PaginaAtual) {
        lblPagina.setText("Pagina :" + PaginaAtual + "/" + getTotalPaginas());
        this.PaginaAtual = PaginaAtual;
    }

    /**
     * @return the CampoChavePrimaria
     */
    public String getCampoChavePrimaria() {
        return CampoChavePrimaria;
    }

    /**
     * @param CampoChavePrimaria the CampoChavePrimaria to set
     */
    public void setCampoChavePrimaria(String CampoChavePrimaria) {

            this.CampoChavePrimaria = CampoChavePrimaria;
    }

    /**
     * @return the nTamanhoPagina
     */
    public Object[] TratarLinhaSelecionada(JTable jTable)
    {
        Object[] objLinhas=null;

        int nLinhas = jTable.getSelectedRow();

//        nLinhas = nLinhas==0 ? 1 : nLinhas;
        if(nLinhas>=0)
        {
            TableModel tm =jTable.getModel();
            objLinhas = new Object[tm.getColumnCount()];
            for(int i=0 ; i<tm.getColumnCount();i++)
            {
                objLinhas[i] = tm.getValueAt(nLinhas, i);
            }    
        }
        return objLinhas;
    }
    public Integer getnTamanhoPagina() {
        return nTamanhoPagina;
    }

    /**
     * @param nTamanhoPagina the nTamanhoPagina to set
     */
    public void setnTamanhoPagina(Integer nTamanhoPagina) {
        this.nTamanhoPagina = nTamanhoPagina;
    }

    /**
     * @return the StringConsulta
     */
    public String getStringConsulta() {
        return StringConsulta;
    }

    /**
     * @param StringConsulta the StringConsulta to set
     */
    public void setStringConsulta(String StringConsulta) {
        this.StringConsulta = StringConsulta;
    }

    /**
     * @return the rsDados
     */
    public ResultSet getRsDados() {
        return rsDados;
    }

    /**
     * @param rsDados te rsDados to set
     */
    public void setRsDados(ResultSet rsDados) {
            setRsDados(rsDados, true);
    }
    
    public void setRsDados(ResultSet rsDados, boolean bAjustarModelo) {
        this.rsDados = rsDados;        
        tbDinnamuS.setRs(rsDados);
        
        if(bAjustarModelo){
            AtualizarModelo(getRsDados());        
        }else{
            this.updateUI();
        }     
      
        AjustarColunas();  
        
        DefinirAlinhamentos();
        tbDinnamuS.setLinhaAtual(0);
        if(jTable.getRowCount()>0){
           jTable.setRowSelectionInterval(0, 0);
         }
        
        
    }
    
     public void setObjetosDados(TbDinnamuSObject modelo, boolean bAjustarModelo) {
            
        this.modelo = modelo;
        
        if(bAjustarModelo){
            AtualizarModelo(getRsDados());        
        }else{
            this.updateUI();
        }     
      
        AjustarColunas();  
        
        DefinirAlinhamentos();
        tbDinnamuS.setLinhaAtual(0);
        if(jTable.getRowCount()>0){
           jTable.setRowSelectionInterval(0, 0);
         }
        
        
    }
    
    private void DefinirAlinhamentos(){

        try {
//           cNomeColuna="";
            for(int i=1;i<getjTable().getColumnCount();i++){
                final String cNomeColuna=getjTable().getColumnName(i).toLowerCase();
                if(getAlinhamentos().containsKey(cNomeColuna)){                                       
                    DefaultTableCellRenderer d = new DefaultTableCellRenderer(){
                        @Override
                        public void setHorizontalAlignment(int alignment) {
                            super.setHorizontalAlignment(getAlinhamentos().get(cNomeColuna)); //To change body of generated methods, choose Tools | Templates.
                        }                            
                    };           
                    jTable.getColumnModel().getColumn(i).setCellRenderer(d);
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
    }
    public int getLinhaAtual(){
        int nLinha=0;
        try {
            
            
            nLinha = getjTable().getSelectedRow();
            
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        return nLinha;

    }
    public boolean Localizar(String cCampo, String cTextoBusca){
           boolean bRet=false;

           ResultSet rs = getRsDados();
            try {
                rs.beforeFirst();
                String cDados=null;
                while(rs.next()){
                    cDados=rs.getString(cCampo);
                    if(cDados!=null){
                        if(cDados.equals(cTextoBusca)){
                           bRet=true;
                           getjTable().setRowSelectionInterval(rs.getRow()-1, rs.getRow()-1);
                           break;
                        }
                    }
                }
            } catch (SQLException ex) {
                LogDinnamus.Log(ex);
            }
           return bRet;
    }
    private int AjustarTamanhoAoPainel(int[] TamColunas, int TamanhoColuna){
        int Ret = 0;
        try {
            
            int TamanhoTotalColunas = 0;
            Float Perc =0f;
            for (int i = 0; i < TamColunas.length; i++) {
                TamanhoTotalColunas+=TamColunas[i];
            }
            
            Perc =  new Float(TamanhoColuna)/TamanhoTotalColunas;
                    
            Dimension TamanhoPainel = jScrollPane.getSize();         
            //Dimension TamanhoTabela = jTable.getSize();  
            
            Ret =  new Float((TamanhoPainel.getWidth() * Perc)  ).intValue(); 
            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
        return Ret;
    }
     private int SomarTamanhoColunas(int[] TamColunas){
        int Total =0;
        try {
            
            for (int i = 0; i < TamColunas.length; i++) {
                Total+=TamColunas[i];
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Total;
    }
    private int SomarTamanhoColunas(HashMap<Object,Integer> TamColunas){
        int Total =0;
        try {
            Integer tamcol[] = clColunas.values().toArray(new Integer[clColunas.size()]);    
            for (int i = 0; i <  tamcol.length; i++) {
                Total+=tamcol[i];
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Total;
    }
    private int AjustarTamanhoAoPainel(Integer TamanhoTotalColunas, int TamanhoColuna){
        int Ret = 0;
        try {            
            Float Perc =0f;
            Perc =  new Float(TamanhoColuna)/TamanhoTotalColunas;            
            Dimension TamanhoPainel = jScrollPane.getParent().getSize();
            Ret =  new Float((TamanhoPainel.getWidth() * Perc)  ).intValue(); 
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
        return Ret;
    }
    public void AjustarColunas()
    {
        try {
            if(bAjustouColunas){
              // return; 
            }
            //jScrollPane.repaint();
            int LarguraTotalColunas= 0;
            //getjTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            int TamanhoColunas =0;
            if(clColunas.size()>0){
                 TamanhoColunas = SomarTamanhoColunas(clColunas);
            }else{
                if(TamColunas!=null){
                    TamanhoColunas = SomarTamanhoColunas(TamColunas);   
                }else{
                    return;
                }
            }
            for(int i=0; i< getjTable().getColumnModel().getColumnCount();i++){ 
                Integer Tamanho =0;                
                String NomeColuna = getjTable().getModel().getColumnName(i);      
                if(clColunas.size()>0){
                    if(clColunas.containsKey(NomeColuna.toLowerCase())){
                        Tamanho = clColunas.get(NomeColuna.toLowerCase());                           
                        if(isAjustaColunaAoPainel()){                            
                            Tamanho = AjustarTamanhoAoPainel(TamanhoColunas, Tamanho);    
                            LarguraTotalColunas+=Tamanho;
                        }
                    } 
                }else{
                    if(TamColunas!=null){
                        Tamanho = TamColunas[i];
                        if(isColunaComTamanhosEmPercentual()){
                           Tamanho = AjustarTamanhoAoPainel(TamanhoColunas, Tamanho);                     
                           LarguraTotalColunas+=Tamanho;
                        }
                    }else{
                        Tamanho=-1;
                    }
                }
                if(Tamanho>0){
                    getjTable().getColumnModel().getColumn(i).setPreferredWidth(Tamanho);
                    getjTable().getColumnModel().getColumn(i).setMinWidth(Tamanho);
                    getjTable().getColumnModel().getColumn(i).setMaxWidth(Tamanho);
                }
            }
            if(LarguraTotalColunas!=0){                
            //    this.setSize( LarguraTotalColunas, new Double(this.getSize().getHeight()).intValue());
            }
            //this.update(this.getGraphics());
            
            bAjustouColunas=true;
            //AjustarColunas=true;
            
            //this.doLayout();         
        } catch (Exception e) {
           LogDinnamus.Log(e);
        }
    }
    public void TamanhoColunas(int[] nTamColunas )
    {        
        setTamColunas(nTamColunas);
        AjustarColunas();
    }

    /**
     * @return the ExibirBarra
     */
    public boolean isExibirBarra() {
        return ExibirBarra;
    }

    /**
     * @param ExibirBarra the ExibirBarra to set
     */
    public void setExibirBarra(boolean ExibirBarra) {
        
        this.ExibirBarra = ExibirBarra;
        initComponents();
    }

    /**
     * @return the jTable
     */
    public JTable getjTable() {
        return jTable;
    }

    /**
     * @param jTable the jTable to set
     */
    public void setjTable(JTable jTable) {
        this.jTable = jTable;
    }

    /**
     * @return the numberFormat
     */
    public HashMap<String, NumberFormat> getNumberFormat() {

        return numberFormat;
    }

    /**
     * @param numberFormat the numberFormat to set
     */
    public void setNumberFormat(HashMap<String, NumberFormat> numberFormat) {
        this.numberFormat = numberFormat;
    }
    
    public void addNumberFormat(String Coluna) {
            this.numberFormat.put(Coluna, NumberFormat_Padrao);
    }
    public void addNumberFormatMoeda(String Coluna) {
            this.numberFormat.put(Coluna, NumberFormat_Padrao_Moeada);
    }

    /**
     * @return the dateFormat
     */
    public HashMap<String, DateFormat> getDateFormat() {
        return dateFormat;
    }

    /**
     * @param dateFormat the dateFormat to set
     */
    public void setDateFormat(HashMap<String, DateFormat> dateFormat) {
        this.dateFormat = dateFormat;
    }
   public void addDateFormat(String Coluna, DateFormat formato) { 
        this.dateFormat.put(Coluna, formato);
   }
   public void addDateFormat(String Coluna) {
        //for (int i = 0; i < Coluna.size(); i++) {
            this.dateFormat.put(Coluna, DateFormat_Padrao);
        //}
        
    }
    /**
     * @return the TamColunas
     */
    public int[] getTamColunas() {
        return TamColunas;
    }

    /**
     * @param TamColunas the TamColunas to set
     */
    public void setTamColunas(int[] TamColunas) {
        this.TamColunas = TamColunas;
    }

    /**
     * @return the alinhamentos
     */
    public HashMap<String, Integer> getAlinhamentos() {
        if(alinhamentos==null){
            //HashMap<String, DefaultTableCellRenderer> hmalinhamento = new HashMap<String, DefaultTableCellRenderer>();

        }
        return alinhamentos;
    }

    /**
     * @param alinhamentos the alinhamentos to set
     */
    
    public void addAlinhamentos(String Coluna, int alinhamento) {
        //DefaultTableCellRenderer alinhamentocelula = new DefaultTableCellRenderer();       
        //alinhamentocelula.setHorizontalAlignment(alinhamento);        
        alinhamentos.put(Coluna.toLowerCase(), alinhamento);              
    }
    
    public void setAlinhamentos(HashMap<String, Integer> alinhamentos) {
        this.alinhamentos = alinhamentos;
    }
    public void AlinhamentosAdicionar(String cNomecoluna, int Alinhamento){
            //DefaultTableCellRenderer alinhamento=new DefaultTableCellRenderer();
            
            getAlinhamentos().put(cNomecoluna.toLowerCase(), Alinhamento);
    }
 
    public void FormatoNumericoAdicionar(String cNomeColuna, NumberFormat format){
            getNumberFormat().put(cNomeColuna,format);
    }
    public void FormatoDataAdicionar(String cNomeColuna, DateFormat format){
            getDateFormat().put(cNomeColuna,format);
    }

    /**
     * @return the clColunas
     */
    public HashMap<Object, Integer> getClColunas() {
        return clColunas;
    }
    
    /**
     * @param clColunas the clColunas to set
     */
    public enum FormatoColuna{
        Data,Moeda,Numero
    }
    public void addClColunas(String Coluna,String DescricaoColuna, Integer Tamanho) {     
                addClColunas(Coluna, DescricaoColuna, Tamanho, true,false ,-1,null,"",null,null,null);
    }
    public void addClColunas(String Coluna,String DescricaoColuna, Integer Tamanho, Boolean Exibir) {     
                addClColunas(Coluna, DescricaoColuna, Tamanho, Exibir,false ,-1,null,"",null,null,null);
    }
    
    public void addClColunas(String Coluna,String DescricaoColuna, Integer Tamanho, Boolean Exibir,boolean Editavel ,int Alinhamento) {     
                addClColunas(Coluna, DescricaoColuna, Tamanho, Exibir,Editavel ,Alinhamento,null,"",null,null,null);
    }
    public void addClColunas(String Coluna,String DescricaoColuna, Integer Tamanho, Boolean Exibir,boolean Editavel ,int Alinhamento,Integer TipoSQL) {     
                addClColunas(Coluna, DescricaoColuna, Tamanho, Exibir,Editavel ,Alinhamento,null,"",null,TipoSQL,null);
    }

    public void addClColunas(String Coluna,String DescricaoColuna, Integer Tamanho, Boolean Exibir,boolean Editavel ,int Alinhamento,Integer TipoSQL,Integer CasaDecimais) {     
                addClColunas(Coluna, DescricaoColuna, Tamanho, Exibir,Editavel ,Alinhamento,null,"",null,TipoSQL,CasaDecimais);
    }
    public void addClColunas(String Coluna,String DescricaoColuna, Integer Tamanho, boolean Exibir,boolean Editavel, Object EditorCelulaEspecial, String TipoColunaNaoVinculada) {        
            addClColunas(Coluna, DescricaoColuna, Tamanho, Exibir, Editavel, -1,EditorCelulaEspecial, TipoColunaNaoVinculada,null,null,null);
        
    }
    public void addClColunas(String Coluna,String DescricaoColuna, Integer Tamanho, int Alinhamento , FormatoColuna f) {        
        addClColunas(Coluna, DescricaoColuna, Tamanho, true, false, Alinhamento,null, "",f,null,null);
    }
    public void addClColunas(String Coluna,String DescricaoColuna, Integer Tamanho, boolean Exibir,boolean Editavel, int Alinhamento,Object EditorCelulaEspecial, String TipoColunaNaoVinculada) {        
        addClColunas(Coluna, DescricaoColuna, Tamanho, Exibir, Editavel, Alinhamento, EditorCelulaEspecial, TipoColunaNaoVinculada, null,null,null);
    }
    public void addClColunas(String Coluna,String DescricaoColuna, Integer Tamanho, boolean Exibir,boolean Editavel, int Alinhamento,Object EditorCelulaEspecial, String TipoColunaNaoVinculada,FormatoColuna f, Integer TipoSQL ,Integer CasasDecimais) {        
        try {        
            
            getTbDinnamuS().setModeloUsandoColecao(true);
            this.Colunas.add(Coluna);

            this.clColunas.put( Coluna ,Tamanho);            

            if(Exibir){
               clColunasExibir.put(Coluna.toString().toLowerCase(), DescricaoColuna);
            }
            if(Alinhamento>=0){
               addAlinhamentos(Coluna.toString().toLowerCase(), Alinhamento);
            }      
            if(Editavel){
               AddColunaEditavel(Coluna.toString().toLowerCase()); 
            }
            if(EditorCelulaEspecial!=null){
                ColunasComEditoresEspeciais.put(Coluna,  EditorCelulaEspecial);
                
                if (EditorCelulaEspecial instanceof  JButton ){
                    ColunasBotoes.add(Coluna);
            
                }
            }
            if(TipoColunaNaoVinculada.length()>0){               
               getColunasNaoVinculadas().put(Coluna, TipoColunaNaoVinculada);
            }
            
            if(f== FormatoColuna.Data){
                 addDateFormat(Coluna);
            }
            else if(f == FormatoColuna.Moeda){     
                addNumberFormatMoeda(Coluna);
            }
            else if(f == FormatoColuna.Numero){                     
                addNumberFormat(Coluna);
            
            }
            if(TipoSQL!=null){
               TipoJDBC.put(Coluna, TipoSQL);
            }
            if(CasasDecimais!=null){
               CampoNumerico_CasaDecimais.put(Coluna, CasasDecimais);
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
    }
    public boolean addSubTabela(String Coluna,String DescricaoColuna, Integer Tamanho, JTableDinnamuS_SubTabela subtabela, String ChavePrimaria,String ChaveEstrangeira) {        
        try {
            if(!Colunas.contains(Coluna)){
                this.Colunas.add(Coluna);            
                this.clColunas.put((DescricaoColuna.equalsIgnoreCase("") ? Coluna : DescricaoColuna).toLowerCase(),Tamanho);            
                this.clColunasExibir.put(Coluna.toString().toLowerCase(), DescricaoColuna);
                this.SubTabelas.put(Coluna, subtabela);
                this.SubTabelas_ChavePrimarias.put(Coluna, ChavePrimaria);
                //this.SubTabelas_ChaveEstrageira.put(Coluna, ChaveEstrangeira);
            }
            
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }    
    }
    public void setClColunas(HashMap<Object, Integer> clColunas) {
        this.clColunas = clColunas;
    }

    /**
     * @return the tbDinnamuS
     */
    public TbDinnamuS getTbDinnamuS() {
        return tbDinnamuS;
    }

    /**
     * @param tbDinnamuS the tbDinnamuS to set
     */
    public void setTbDinnamuS(TbDinnamuS tbDinnamuS) {
        this.tbDinnamuS = tbDinnamuS;
    }

    /**
     * @return the clColunasExibir
     */
    public HashMap<Object,String> getClColunasExibir() {
        return clColunasExibir;
    }

    /**
     * @param clColunasExibir the clColunasExibir to set
     */
    public void setClColunasExibir(HashMap<Object,String> clColunasExibir) {
        this.clColunasExibir = clColunasExibir;
    }

    /**
     * @return the clColunasEditaveis
     */
    public boolean AddColunaEditavel(String Coluna){
        try {
            
            clColunasEditaveis.add(Coluna);
            
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
    public ArrayList<String> getClColunasEditaveis() {
        return clColunasEditaveis;
    }

    /**
     * @param clColunasEditaveis the clColunasEditaveis to set
     */
    public void setClColunasEditaveis(ArrayList<String> clColunasEditaveis) {
        this.clColunasEditaveis = clColunasEditaveis;
    }

    /**
     * @return the Edicao_ChavePrimaria
     */
    public String getEdicao_ChavePrimaria() {
        return Edicao_ChavePrimaria;
    }

    /**
     * @param Edicao_ChavePrimaria the Edicao_ChavePrimaria to set
     */
    public void setEdicao_ChavePrimaria(String Edicao_ChavePrimaria) {
        this.Edicao_ChavePrimaria = Edicao_ChavePrimaria;
    }

    /**
     * @return the Edicao_Tabela
     */
    public void setEdicao_ChaveEstrangeira(String campo){
        this.Edicao_ChaveEstrangeira = campo;
    }
    public String getEdicao_Tabela() {
        return Edicao_Tabela;
    }

    /**
     * @param Edicao_Tabela the Edicao_Tabela to set
     */
    public void setEdicao_Tabela(String Edicao_Tabela) {
        this.Edicao_Tabela = Edicao_Tabela;
    }

    /**
     * @return the Edicao_Cnx
     */
    public Connection getEdicao_Cnx() {
        return Edicao_Cnx;
    }

    /**
     * @param Edicao_Cnx the Edicao_Cnx to set
     */
    public void setEdicao_Cnx(Connection Edicao_Cnx) {
        this.Edicao_Cnx = Edicao_Cnx;
    }

    /**
     * @return the Edicao_Query
     */
    public String getEdicao_Query() {
        return Edicao_Query;
    }

    /**
     * @param Edicao_Query the Edicao_Query to set
     */
    public void setEdicao_Query(String Edicao_Query) {
        this.Edicao_Query = Edicao_Query;
        tbDinnamuS.setEdicao_Query(Edicao_Query);
    }

    /**
     * @return the ColunaComTamanhosEmPercentual
     */
    public boolean isColunaComTamanhosEmPercentual() {
        return ColunaComTamanhosEmPercentual;
    }

    /**
     * @param ColunaComTamanhosEmPercentual the ColunaComTamanhosEmPercentual to set
     */
    public void setColunaComTamanhosEmPercentual(boolean ColunaComTamanhosEmPercentual) {
        this.ColunaComTamanhosEmPercentual = ColunaComTamanhosEmPercentual;
        setAjustaColunaAoPainel(ColunaComTamanhosEmPercentual);
    }

    /**
     * @return the ColunasNaoVinculadas
     */
    public TreeMap<String,String> getColunasNaoVinculadas() {
        return ColunasNaoVinculadas;
    }

    /**
     * @param ColunasNaoVinculadas the ColunasNaoVinculadas to set
     */
    public void setColunasNaoVinculadas(TreeMap<String,String> ColunasNaoVinculadas) {
        this.ColunasNaoVinculadas = ColunasNaoVinculadas;
    }
    public Long getLong(String NomeCampo){
        Long Ret =0l;
        try {
            int linha = jTable.getSelectedRow();
            if(linha >=0){
               Ret = getTbDinnamuS().getValorCelulaLong(NomeCampo,linha);
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
     public Float getFloat(String NomeCampo){
        Float Ret =0f;
        try {
            int linha = jTable.getSelectedRow();
            if(linha >=0){
               Ret = getTbDinnamuS().getValorCelulaFloat(NomeCampo,linha);
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
   public String getString(String NomeCampo){
        String Ret ="";
        try {
            int linha = jTable.getSelectedRow();
            if(linha >=0){
               Ret = getTbDinnamuS().getValorCelulaString(NomeCampo,linha);
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
    public void OcultarColuna(String idColuna){
        try {
            
            if(!getColunasInvisiveis().contains(idColuna)){
                
                getColunasInvisiveis().add(idColuna);
                setRsDados(rsDados);
                
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }
    public void ExibirColuna(String idColuna){
        try {
            
             if(getColunasInvisiveis().contains(idColuna)){
                getColunasInvisiveis().remove(idColuna);                 
                setRsDados(rsDados);
             }
            
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }

    /**
     * @return the ColunasInvisiveis
     */
    public ArrayList<String> getColunasInvisiveis() {
        return ColunasInvisiveis;
    }

    /**
     * @param ColunasInvisiveis the ColunasInvisiveis to set
     */
    public void setColunasInvisiveis(ArrayList<String> ColunasInvisiveis) {
        this.ColunasInvisiveis = ColunasInvisiveis;
    }

    /**
     * @return the AjustaColunaAoPainel
     */
    public boolean isAjustaColunaAoPainel() {
        return AjustaColunaAoPainel;
    }

    /**
     * @param AjustaColunaAoPainel the AjustaColunaAoPainel to set
     */
    public void setAjustaColunaAoPainel(boolean AjustaColunaAoPainel) {
        this.AjustaColunaAoPainel = AjustaColunaAoPainel;
    }

    public void AumentaAlturaLinhas(Float Perc){
        try {
            
            Float Altura = new Float(getjTable().getRowHeight());
            Altura *= Perc;
            getjTable().setRowHeight(Altura.intValue());
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
    }

    
    public void DefinirFonte(String Fonte, int Estilo, int Tamanho ){
        try {
            
            getjTable().setFont(new Font(Fonte ,Estilo, Tamanho));
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }
    
    
   
}

