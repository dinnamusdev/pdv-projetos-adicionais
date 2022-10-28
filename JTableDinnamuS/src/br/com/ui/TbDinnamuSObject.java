/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ui;



import br.com.log.LogDinnamus;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Types;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author desenvolvedor
 */
public class TbDinnamuSObject<T> extends AbstractTableModel implements TableModelListener{
    private boolean Editavel = true;
    //private ResultSet rs;
    private List<T> rs;
    //private ResultSetMetaData rsmd;
    private HashMap<String, NumberFormat> numberFormat=new HashMap<String, NumberFormat>();
    private HashMap<String, DateFormat> dateFormat=new HashMap<String, DateFormat>();    
    private ArrayList<String> clColunasEditaveis=new ArrayList<String>();    
    private String Edicao_ChavePrimaria ="";
    private String Edicao_ChaveEstrangeira ="";
    private String Edicao_Tabela="";
    private Connection Edicao_Cnx = null;
    private String Edicao_Query ="";
    private int LinhaAtual=-1;
    private int ColunaAtual=-1;
    private ArrayList<Integer> TiposNumericos = new ArrayList<Integer>();
    private ArrayList<Object> Colunas = new ArrayList<Object>();
    private boolean ModeloUsandoColecao =false;
    private TreeMap<String,TreeMap<Integer,Object>> ColunasNaoVinculadas_Valores = new TreeMap<String, TreeMap<Integer, Object>>();    
    private TreeMap<String,String> ColunasNaoVinculadas = new TreeMap<String, String>();
    private ArrayList<String> ColunasInvisiveis = new ArrayList<String>();
    private TreeMap<String, JTableDinnamuS_SubTabela> SubTabelas = new TreeMap<String, JTableDinnamuS_SubTabela>();
    private TreeMap<String, String> SubTabelas_ChavePrimarias = new TreeMap<String, String>();
    private ArrayList<String> ColunasBotoes = new ArrayList<String>();
    //private TreeMap<String, String> SubTabelas_ChaveEstrageira = new TreeMap<String, String>();
    private TreeMap<String,Integer> TipoJDBC = new TreeMap<String, Integer>();
    private TreeMap<String,Integer> CampoNumerico_CasaDecimais = new TreeMap<String, Integer>();
    private boolean IgnorarProcedimento = false;
            
    public TbDinnamuSObject() {
        
            //this.addTableModelListener(this);
            this.rs=null;
           // this.rsmd=null;            
                   
    }
    public  TreeMap<String, Integer> getTipoJDBC(){
            return TipoJDBC;
    } 
    public void setTipoJDBC( TreeMap<String, Integer> _tipo){
            TipoJDBC = _tipo;
        
    }
    public TreeMap<String,Integer> getCampoNumerico_CasaDecimais(){
        return CampoNumerico_CasaDecimais;
    }
    public void setCampoNumerico_CasaDecimais(TreeMap<String,Integer> _CampoNumerico_CasaDecimais ){
        CampoNumerico_CasaDecimais = _CampoNumerico_CasaDecimais;
    }
    
    /*@Override
    public Class<?> getColumnClass(int columnIndex) {
        Class<?> cl =null;
        try {
            String Classe ="";
            if(isModeloUsandoColecao()){
                String cNomeCampo=Colunas.get(columnIndex).toString();
                columnIndex = getIndiceColuna(cNomeCampo);            
            }
            Classe = rs.getMetaData().getColumnClassName(columnIndex+1);
            
            Class  t  = Class.forName(Classe);
            
            cl = t;
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
        return cl;
    }*/
    private ArrayList<Integer> getTiposNumericos(){
        try {
            if(TiposNumericos.size()==0){
               TiposNumericos.add(Types.BIGINT);
               TiposNumericos.add(Types.DECIMAL);
               TiposNumericos.add(Types.DOUBLE);
               TiposNumericos.add(Types.FLOAT);
               TiposNumericos.add(Types.INTEGER);
               TiposNumericos.add(Types.NUMERIC);
               TiposNumericos.add(Types.REAL);
               TiposNumericos.add(Types.SMALLINT);
               TiposNumericos.add(Types.TINYINT);
            }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return this.TiposNumericos;
    }
    
    @Override
    public boolean isCellEditable(int linha,int coluna){
        
        
        if(getClColunasEditaveis().isEmpty()){
            return false;
        }else{
            
            String cColunaAtual = getColumnName(coluna).toLowerCase();
            
            if(getClColunasEditaveis().contains(cColunaAtual)){
                if(isEditavel()){
                   return true;
                }else{
                    return false;
                }
            }else
            {
                return false;
            }
        }
    } 
    
    public TbDinnamuSObject(List<T> rsDados) {
        try {
            
            this.rs = rsDados;
            //this.rsmd = rs.getMetaData();


            
        } catch (Exception ex) {
            LogDinnamus.Log(ex);
        }

    }   
     private Class getTipoMetodoGET( T objeto ,String nomeColuna){
        Class Ret = null;
        try {
            
            if(rs!=null && !rs.isEmpty()){
                
                
                 
                 for (Method metodo : objeto.getClass().getDeclaredMethods()) {
                     
                     if(metodo.getName().contains(nomeColuna)){
                         
                         Ret  =   metodo.getReturnType();
                         
                         break;
                     }
                     
                 }
                 
                 
                 
             }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
     
     private Method getNomeMetodoSET(T objeto , String nomeColuna){
        Method Ret = null;
        try {
            
            if(rs!=null && !rs.isEmpty()){
                
                 for (Method metodo : objeto.getClass().getDeclaredMethods()) {
                     
                      String nomeMetodo=metodo.getName();
                     
                     if(nomeMetodo.equalsIgnoreCase("set"+nomeColuna)){
                         Ret  = metodo;
                         break;
                     }
                     
                 }
                 
                 
                 
             }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }  
    private Method getNomeMetodoGET(T objeto ,String nomeColuna){
        Method Ret = null;
        try {
            
            if(rs!=null && !rs.isEmpty()){
                
                 for (Method metodo : objeto.getClass().getDeclaredMethods()) {
                     
                     String nomeMetodo=metodo.getName();
                     
                     if(nomeMetodo.equalsIgnoreCase("get"+nomeColuna)){
                         Ret  = metodo;
                         break;
                     }
                 }
             }
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return Ret;
    }
    
    @Override
    public String getColumnName(int nColuna) {

        String cNomeCampo=null;
        try {
            
                cNomeCampo=Colunas.get(nColuna).toString();
                if(getColunasInvisiveis().contains(cNomeCampo)){
                  cNomeCampo="";
                }
                
            
        } catch (Exception exception) {
            LogDinnamus.Log(exception);
        }
        return cNomeCampo;
        
    }
   

    @Override
    public int getColumnCount() {
     
        int nIndice=-1;        
        try {            
             
                 nIndice = Colunas.size()  ;// - getColunasInvisiveis().size()  ;
            

        } catch (Exception exception) {
                LogDinnamus.Log(exception);
        }
        
        
        return nIndice ;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex ) {
         setValueAt(aValue, rowIndex, columnIndex, false);
    }
    
    
    public void setValueAt(Object aValue, int rowIndex, int columnIndex, boolean ignorarColecao) {
        try {
             String cNomeColuna="";
              
              cNomeColuna = Colunas.get(columnIndex).toString();
             
             if(getColunasNaoVinculadas().containsKey(cNomeColuna)){
                String TipoCampoNaoVinculado =  getColunasNaoVinculadas().get(cNomeColuna);
                if(TipoCampoNaoVinculado.equalsIgnoreCase("java.lang.Boolean")){
                   TreeMap<Integer, Object> vlr_nao_vinculados = getColunasNaoVinculadas_Valores().get(cNomeColuna);
                   vlr_nao_vinculados.put(rowIndex, (Boolean)aValue);
                }
             }else{
               
                Class TipoColuna = getTipoMetodoGET(rs.get(rowIndex),cNomeColuna);
               
                Method nomeMetodoSET = getNomeMetodoSET( rs.get(rowIndex),cNomeColuna);
                
                if(nomeMetodoSET!=null){
                    nomeMetodoSET.invoke(rs.get(rowIndex),aValue);
                }
             }
             this.fireTableCellUpdated(rowIndex, columnIndex);
           
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = null;
        try {
            
            setLinhaAtual(rowIndex);  
            setColunaAtual(columnIndex);
            String cNomeColuna="";
            
            cNomeColuna = Colunas.get(columnIndex).toString();   
            
            Method nomeMetodoGET = getNomeMetodoGET(rs.get(rowIndex),cNomeColuna);
         
            if(nomeMetodoGET!=null){
               obj = nomeMetodoGET.invoke(rs.get(rowIndex), null);
            }    
         
            
            if(getNumberFormat().containsKey(cNomeColuna.toLowerCase())){
                Double f = (obj==null ? 0 : new Double(obj.toString()));

                obj=getNumberFormat().get(cNomeColuna.toLowerCase()).format(f);

                //System.out.println(obj);
            }else if(getDateFormat().containsKey(cNomeColuna.toLowerCase())){
                   if(obj!=null){               
                     obj=getDateFormat().get(cNomeColuna.toLowerCase()).format(obj);
                   }                    
            }
             
        } catch (Exception ex) {
            Logger.getLogger(TbDinnamuSObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;

    }
    @Override
    public int getRowCount() {
        int nIndice=-1;
        try {
            //if(getRs()!=null){
               
                nIndice= rs.size();
            //}
        } catch (Exception exception) {
                LogDinnamus.Log(exception);
        }

        return nIndice;
    }

    /**
     * @return the rs
     */
    public List<T> getRs() {

        return rs;
    }

    /**
     * @param rs the rs to set
     */
    public void setRs(List<T> rs) {
        try {            
            this.rs = rs;
           // this.rsmd = rs.getMetaData();
        } catch (Exception ex) {
            Logger.getLogger(TbDinnamuSObject.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    /**
     * @return the clColunas
     */
    /*public HashMap<Object, Integer> getClColunas() {
        return clColunas;
    }*/

    /**
     * @param clColunas the clColunas to set
     */
    /*ublic void setClColunas(HashMap<Object, Integer> clColunas) {
        this.clColunas = clColunas;
    }*/

    /**
     * @return the clColunasEditaveis
     */
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
     * @return the ChavePrimaria
     */
    public void setChaveEstrangeira(String Campo){
        this.Edicao_ChaveEstrangeira  = Campo;
    }
    public String getChaveEstrangeira()
    {
        return Edicao_ChaveEstrangeira;
    }
    public String getChavePrimaria() {
        return Edicao_ChavePrimaria;
    }

    /**
     * @param ChavePrimaria the ChavePrimaria to set
     */
    public void setChavePrimaria(String ChavePrimaria) {
        this.Edicao_ChavePrimaria = ChavePrimaria;
    }

    /**
     * @return the Tabela_Edicao
     */
    public String getTabela_Edicao() {
        return Edicao_Tabela;
    }

    /**
     * @param Tabela_Edicao the Tabela_Edicao to set
     */
    public void setTabela_Edicao(String Tabela_Edicao) {
        this.Edicao_Tabela = Tabela_Edicao;
    }

    /**
     * @return the Edicao_Cnx
     */
    public Connection getEdicao_Cnx() {
        return Edicao_Cnx;
    }
    
    
    @Override
    public void fireTableRowsInserted(int firstRow, int lastRow) {
        if(isIgnorarProcedimento()){
            return;
        }
        fireTableChanged(new TableModelEvent(this, firstRow, lastRow,
                             TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
    }
    
    @Override
    public void fireTableRowsUpdated(int firstRow, int lastRow) {
        if(isIgnorarProcedimento()){
            return;
        }
        super.fireTableRowsUpdated(firstRow, lastRow);
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
    }

    /*
     * @return the LinhaAtual
     */
    public int getLinhaAtual() {
        return LinhaAtual;
    }

    /**
     * @param LinhaAtual the LinhaAtual to set
     */
    public void setLinhaAtual(int LinhaAtual) {
        this.LinhaAtual = LinhaAtual;
    }

    /**
     * @return the ColunaAtual
     */
    public int getColunaAtual() {
        return ColunaAtual;
    }

    /**
     * @param ColunaAtual the ColunaAtual to set
     */
    public void setColunaAtual(int ColunaAtual) {
        this.ColunaAtual = ColunaAtual;
    }

  public boolean RemoveRow(int Linha){
        try {
            
            getRs().remove(Linha+1);
              
            //getRs().absolute(Linha);
            fireTableRowsDeleted(Linha,Linha);
            //fire
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
   public boolean addRow(){
        try {
            ///rs.
           
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return false;
        
    }
    public boolean addRow(boolean GerarPK, Long ValorChaveEstrangeira, Integer Loja,Integer PDV,boolean online,boolean commit){
            try {
               
              
             
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    
    }
    
    public void tableChanged(TableModelEvent e) {
            
       
    }

    /**
     * @return the Editavel
     */
    public boolean isEditavel() {
        return Editavel;
    }

    /**
     * @param Editavel the Editavel to set
     */
    public void setEditavel(boolean Editavel) {
        this.Editavel = Editavel;
    }

    /**
     * @return the ModeloUsandoColecao
     */
    public boolean isModeloUsandoColecao() {
        return ModeloUsandoColecao;
    }

    /**
     * @param ModeloUsandoColecao the ModeloUsandoColecao to set
     */
    public void setModeloUsandoColecao(boolean ModeloUsandoColecao) {
        this.ModeloUsandoColecao = ModeloUsandoColecao;
    }

    /**
     * @return the Colunas
     */
    public ArrayList<Object> getColunas() {
        return Colunas;
    }

    /**
     * @param Colunas the Colunas to set
     */
    public void setColunas(ArrayList<Object> Colunas) {
        this.Colunas = Colunas;
    }

    /**
     * @return the ColunasNaoVinculadas_Valores
     */
    public TreeMap<String,TreeMap<Integer,Object>> getColunasNaoVinculadas_Valores() {
        return ColunasNaoVinculadas_Valores;
    }

    /**
     * @param ColunasNaoVinculadas_Valores the ColunasNaoVinculadas_Valores to set
     */
    public void setColunasNaoVinculadas_Valores(TreeMap<String,TreeMap<Integer,Object>> ColunasNaoVinculadas_Valores) {
        this.ColunasNaoVinculadas_Valores = ColunasNaoVinculadas_Valores;
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
     * @return the SubTabelas_ResultSet
     */
    public TreeMap<String, JTableDinnamuS_SubTabela> getSubTabelas() {
        return SubTabelas;
    }

    /**
     * @param SubTabelas_ResultSet the SubTabelas_ResultSet to set
     */
    public void setSubTabelas(TreeMap<String, JTableDinnamuS_SubTabela> SubTabelas) {
        this.SubTabelas = SubTabelas;
    }

    /**
     * @return the SubTabelas_ChavePrimarias
     */
    public TreeMap<String, String> getSubTabelas_ChavePrimarias() {
        return SubTabelas_ChavePrimarias;
    }

    /**
     * @param SubTabelas_ChavePrimarias the SubTabelas_ChavePrimarias to set
     */
    public void setSubTabelas_ChavePrimarias(TreeMap<String, String> SubTabelas_ChavePrimarias) {
        this.SubTabelas_ChavePrimarias = SubTabelas_ChavePrimarias;
    }
  
    /**
     * @return the IgnorarProcedimento
     */
    public boolean isIgnorarProcedimento() {
        return IgnorarProcedimento;
    }

    /**
     * @param IgnorarProcedimento the IgnorarProcedimento to set
     */
    public void setIgnorarProcedimento(boolean IgnorarProcedimento) {
        this.IgnorarProcedimento = IgnorarProcedimento;
    }

    /**
     * @return the ColunasBotoes
     */
    public ArrayList<String> getColunasBotoes() {
        return ColunasBotoes;
    }

    /**
     * @param ColunasBotoes the ColunasBotoes to set
     */
    public void setColunasBotoes(ArrayList<String> ColunasBotoes) {
        this.ColunasBotoes = ColunasBotoes;
    }

    /**
     * @return the ColunasNaoVinculadas
     */
   
}

