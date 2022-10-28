/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import br.com.generica.Dao_Generica;
import br.com.info.Sistema;
import br.com.log.LogDinnamus;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.DefaultListSelectionModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author dti
 */


public class JTableDinnamuSCached extends javax.swing.JPanel {
    public int Alinhamento_Direita = SwingConstants.RIGHT;
    public int Alinhamento_Centro = SwingConstants.CENTER;
    public int Alinhamento_Esqueda = SwingConstants.LEFT;
    
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
    private TbDinnamuSCached tbDinnamuS= new TbDinnamuSCached();
    private ResultSet rsDados;
    private boolean ExibirBarra=true;
    private HashMap<String, NumberFormat> numberFormat=new HashMap<String, NumberFormat>();
    private HashMap<String, DateFormat> dateFormat=new HashMap<String, DateFormat>();
    private HashMap<String,DefaultTableCellRenderer> alinhamentos = new HashMap<String, DefaultTableCellRenderer>();
    private HashMap<String,Integer> clColunas=new HashMap<String, Integer>();
    private HashMap<String,String> clColunasExibir = new HashMap<String, String>();
    private int[] TamColunas;
    private boolean bAjustouColunas=false;
    private String Edicao_ChavePrimaria ="";
    private String Edicao_ChaveEstrangeira ="";
    private String Edicao_Tabela="";
    private Connection Edicao_Cnx = null;
    private String Edicao_Query ="";
    
    public JTableDinnamuSCached()
    {            
        initComponents();
        DefinirTipoSelecao();
        tbDinnamuS =new TbDinnamuSCached();
        getjTable().getTableHeader().setResizingAllowed(false);
        getjTable().getTableHeader().setReorderingAllowed(false);
        

    }
    private NumberFormat NumberFormat_Padrao_Moeada = DecimalFormat.getCurrencyInstance();
    private NumberFormat NumberFormat_Padrao  = new DecimalFormat("#,##0.00");
    private DateFormat DateFormat_Padrao = new SimpleDateFormat("dd/MM/yyyy");
    
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
        
        /*
         getjTable().setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Titulo 1", "Titulo 2", "Titulo 3", "Titulo 4"
            }
        ));*/
        //setDao_Generica(dg);

        //jScrollPane1.setViewportView(this);

        jToolBar1.setRollover(true);
        jScrollPane.setViewportView(getjTable());
        btPrimeiro.setText("<< Primeiro");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);

       layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
            .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE))
        );
        jToolBar1.setVisible(isExibirBarra());

    }
    
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
                    rs=Dao_Generica.Pesquisar(getStringConsulta(), ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY ,getnTamanhoPagina(), getCampoChavePrimaria(),nValorChave,"voltar",getPaginaAtual()-1,Sistema.isOnline());
                    //if(rs.next()){
                        //setRsDados(rs);
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
                rs=Dao_Generica.Pesquisar(getStringConsulta(), ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY ,getnTamanhoPagina(), getCampoChavePrimaria(),0,"",getTotalPaginas(),Sistema.isOnline());
                //if(rs.next()){
                //setRsDados(rs);
                AtualizarModelo(getRsDados());
                setPaginaAtual(getTotalPaginas());
                // }
            } catch (Exception ex) {
                LogDinnamus.Log(ex);
            }
        }
    }
    public boolean AtualizarModelo(){
        return  AtualizarModelo(getRsDados());
    }
    public boolean AtualizarModelo(ResultSet rs)
    {
        boolean bRet=false;

        try {
            boolean bTemRegistros = this.rsDados.next();
            setTbDinnamuS(new TbDinnamuSCached());
            getTbDinnamuS().setRs(this.rsDados);
            getTbDinnamuS().setNumberFormat(getNumberFormat());
            getTbDinnamuS().setDateFormat(getDateFormat());
            getTbDinnamuS().setClColunas(getClColunas());
            getTbDinnamuS().setClColunasEditaveis(clColunasEditaveis);
            getTbDinnamuS().setEdicao_Cnx(Edicao_Cnx);
            getTbDinnamuS().setEdicao_Query(Edicao_Query);
            getTbDinnamuS().setTabela_Edicao(Edicao_Tabela);
            getTbDinnamuS().setChavePrimaria(Edicao_ChavePrimaria);
            getTbDinnamuS().setChaveEstrangeira(Edicao_ChaveEstrangeira);
            
            getjTable().setModel(getTbDinnamuS());
            //getTbDinnamuS().fireTableStructureChanged();
            
            if(getClColunas().size()>0){
                
                String cNomeColuna="";
                Integer nTamColuna=0;
                for(int i=0;i<getjTable().getColumnModel().getColumnCount(); i++){
                    //TableColumn tableColumn = getjTable().getColumnModel().getColumn(i);
                    //cNomeColuna=getjTable().getColumnModel().getColumn(i).getHeaderValue().toString();
                    cNomeColuna=tbDinnamuS.getColumnName(i);
                    getjTable().getColumnModel().getColumn(i).setIdentifier(i);
                    if(!getClColunas().containsKey(cNomeColuna.toLowerCase())){                      
                        getjTable().getColumnModel().getColumn(i).setMinWidth(0);
                        getjTable().getColumnModel().getColumn(i).setMaxWidth(0);
                    }else{                        
                            cNomeColuna=getjTable().getColumnModel().getColumn(i).getHeaderValue().toString();
                            nTamColuna=clColunas.get(cNomeColuna.toLowerCase());
                            
                            getjTable().getColumnModel().getColumn(i).setPreferredWidth( nTamColuna  );  
                            if(clColunasExibir!=null){
                                if(clColunasExibir.size()>0){
                                    String ColunaExibir=clColunasExibir.get(cNomeColuna.toLowerCase());
                                    getjTable().getColumnModel().getColumn(i).setHeaderValue(ColunaExibir);
                                }
                            }
                    }
                }
                DefinirAlinhamentos();
                getjTable().updateUI();
                this.doLayout();
            }
        } catch (Exception ex) {
            LogDinnamus.Log(ex);
        }
        return bRet;
    }
    @SuppressWarnings("static-access")
    private void btPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if(getTotalPaginas()>1)
        {
            try {
                // TODO add your handling code here:
                ResultSet rs=null;
                rs=Dao_Generica.Pesquisar(getStringConsulta(), ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY ,getnTamanhoPagina(), getCampoChavePrimaria(),0,"",1,Sistema.isOnline());
                //if(rs.next()){
                //setRsDados(rs);
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
        
        
    }
    private void DefinirAlinhamentos(){
        String cNomeColuna="";
        try {
            
            for(int i=1;i<getjTable().getColumnCount();i++){
                cNomeColuna=getjTable().getColumnName(i);
                if(getAlinhamentos().containsKey(cNomeColuna.toLowerCase())){
                    //if(getAlinhamentos().containsKey(cNomeColuna.toLowerCase())){
                       getjTable().getColumn(i).setCellRenderer(getAlinhamentos().get(cNomeColuna.toLowerCase()));
                    //}
                }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e);
        }
        System.out.print(cNomeColuna);
    }
    public int getLinhaAtual(){
        int nLinha=0;
        try {
            
            
            int[] nLinhas = getjTable().getSelectedRows();
            if(nLinhas.length>0){
                nLinha=nLinhas[0];
            }
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
    private void AjustarColunas()
    {
        try {
            //if(!bAjustouColunas){
                
                if(TamColunas !=null){
                    getjTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                    for(int i=0; i<getTamColunas().length;i++){
                        if(getjTable().getColumnModel().getColumnCount()>0){
                            getjTable().getColumnModel().getColumn(i).setPreferredWidth(getTamColunas()[i]);
                        }
                    }
                    bAjustouColunas=true;
                    this.doLayout();
                }
                
                
            //}
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
    public HashMap<String, DefaultTableCellRenderer> getAlinhamentos() {
        if(alinhamentos==null){
            //HashMap<String, DefaultTableCellRenderer> hmalinhamento = new HashMap<String, DefaultTableCellRenderer>();

        }
        return alinhamentos;
    }

    /**
     * @param alinhamentos the alinhamentos to set
     */
    
   
    public void addAlinhamentos(String Coluna, int alinhamento) {
        DefaultTableCellRenderer alinhamentocelula = new DefaultTableCellRenderer();       
        alinhamentocelula.setHorizontalAlignment(alinhamento);        
        alinhamentos.put(Coluna, alinhamentocelula);              
    }
    
    public void setAlinhamentos(HashMap<String, DefaultTableCellRenderer> alinhamentos) {
        this.alinhamentos = alinhamentos;
    }
    public void AlinhamentosAdicionar(String cNomecoluna, int Alinhamento){
            DefaultTableCellRenderer alinhamento=new DefaultTableCellRenderer();
            getAlinhamentos().put(cNomecoluna, alinhamento);
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
    public HashMap<String, Integer> getClColunas() {
        return clColunas;
    }

    /**
     * @param clColunas the clColunas to set
     */
    public void addClColunas(String Coluna,String DescricaoColuna, Integer Tamanho, boolean Exibir) {     
                addClColunas(Coluna, DescricaoColuna, Tamanho, Exibir,false ,0);
    }
    public void addClColunas(String Coluna,String DescricaoColuna, Integer Tamanho, boolean Exibir,boolean Editavel, int Alinhamento) {        
        try {
        
            this.clColunas.put(Coluna,Tamanho);
            if(Exibir){
               clColunasExibir.put(Coluna, DescricaoColuna);
            }
            if(Alinhamento>0){
               addAlinhamentos(Coluna, Alinhamento);
            }      
            if(Editavel){
               AddColunaEditavel(Coluna); 
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
    }
    public void setClColunas(HashMap<String, Integer> clColunas) {
        this.clColunas = clColunas;
    }

    /**
     * @return the tbDinnamuS
     */
    public TbDinnamuSCached getTbDinnamuS() {
        return tbDinnamuS;
    }

    /**
     * @param tbDinnamuS the tbDinnamuS to set
     */
    public void setTbDinnamuS(TbDinnamuSCached tbDinnamuS) {
        this.tbDinnamuS = tbDinnamuS;
    }

    /**
     * @return the clColunasExibir
     */
    public HashMap<String,String> getClColunasExibir() {
        return clColunasExibir;
    }

    /**
     * @param clColunasExibir the clColunasExibir to set
     */
    public void setClColunasExibir(HashMap<String,String> clColunasExibir) {
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


    
    

    
   
}
