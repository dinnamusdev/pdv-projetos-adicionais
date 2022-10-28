/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */





import br.com.generica.Dao_Generica;
import br.com.log.LogDinnamus;
import com.sun.rowset.JdbcRowSetImpl;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author desenvolvedor
 */
public class TbDinnamuSCached extends AbstractTableModel implements TableModelListener{

    private ResultSet rs;
    private ResultSetMetaData rsmd;
    private HashMap<String, NumberFormat> numberFormat=new HashMap<String, NumberFormat>();
    private HashMap<String, DateFormat> dateFormat=new HashMap<String, DateFormat>();
    private HashMap<String, Integer> clColunas=new HashMap<String, Integer>();
    private ArrayList<String> clColunasEditaveis=new ArrayList<String>();
    //private Dao_Generica dao = new Dao_Generica();
    private String Edicao_ChavePrimaria ="";
    private String Edicao_ChaveEstrangeira ="";
    private String Edicao_Tabela="";
    private Connection Edicao_Cnx = null;
    private String Edicao_Query ="";
    private int LinhaAtual=-1;
    private int ColunaAtual=-1;
    private ArrayList<Integer> TiposNumericos = new ArrayList<Integer>();
    
    public TbDinnamuSCached() {
        
            //this.addTableModelListener(this);
            
            this.rs=null;
            this.rsmd=null;            
                   
    }
  
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
                return true;
            }else
            {
                return false;
            }
        }
    } 
    
    public TbDinnamuSCached(JdbcRowSetImpl rsDados) {
        try {
            
            this.rs = rsDados;
            this.rsmd = rs.getMetaData();


            
        } catch (SQLException ex) {
            LogDinnamus.Log(ex);
        }

    }   
    @Override
    public String getColumnName(int nColuna) {

        String cNomeCampo=null;
        try {
            
            cNomeCampo=rsmd.getColumnName(nColuna+1).toUpperCase();
           
            
        } catch (SQLException exception) {
            LogDinnamus.Log(exception);
        }
        return cNomeCampo;
        
    }
   

    @Override
    public int getColumnCount() {
     
        int nIndice=-1;        
        try {

            nIndice= rsmd.getColumnCount();

        } catch (Exception exception) {
                LogDinnamus.Log(exception);
        }
        return nIndice;
    }

    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        try {
            
            
             
             getRs().absolute(rowIndex + 1);
             String cNomeColuna=getRs().getMetaData().getColumnName(columnIndex+1).toUpperCase();            
             
             String TipoColuna = getRs().getMetaData().getColumnTypeName(columnIndex+1);
             if(getTiposNumericos().contains(getRs().getMetaData().getColumnType(columnIndex+1))){
                aValue =aValue.toString().replace(",", ".");
             } 
             getRs().updateObject(cNomeColuna, aValue);
             getRs().updateRow();
             this.fireTableCellUpdated(rowIndex, columnIndex);
           
            
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
    }
    public void AtualizarCelula(int LinhaAtual){
         this.fireTableRowsUpdated(LinhaAtual, LinhaAtual);
    }
    private boolean  AtualizarDados(Object aValue, int rowIndex, int columnIndex, String cNomeColuna){
        try {
           
        
           getRs().updateObject(columnIndex, aValue);
           getRs().updateRow();
           //this.fireTableRowsDeleted(rowIndex, rowIndex);
           this.fireTableCellUpdated(rowIndex, columnIndex);
           return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);           
            return false;
        }
    }
    public boolean setValorCelular(String cNomeColuna, Object aValue){
        try {
            int nColuna=-1;
            for (int columnIndex =0; columnIndex < getColumnCount(); columnIndex++) {
                if(getColumnName(columnIndex).equalsIgnoreCase(cNomeColuna)){
                    nColuna = columnIndex;
                    break;
                }
            }
            if(nColuna>=0){
                return AtualizarDados( aValue, getLinhaAtual(),  nColuna+1,  cNomeColuna);
            }else{
                return false;
            }            
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return false;
        }
    
    }
    public Object getValorCelula(String NomeCampo){
        return getValorCelula(NomeCampo,false);
    }
    public Long getValorCelulaLong(String NomeCampo){        
        Long obj = 0l;   
        try {
            obj = 0l;              
            getRs().absolute(getLinhaAtual()+1);
            obj = getRs().getLong(NomeCampo);                    
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             obj = 0l;
        }
        return obj;
        
    }
    public Object getValorCelula(String NomeCampo,boolean retorna_nulo){
            return getValorCelula(NomeCampo, retorna_nulo,0);
    }
    public Object getValorCelula(String NomeCampo,boolean retorna_nulo, int Linha){
        int nLinha =0;
        try {
            Object obj = null;              
            getRs().absolute(Linha ==0 ? Linha + 1 :getLinhaAtual()+1);
            obj = getRs().getObject(NomeCampo);            
            if(!retorna_nulo){
                 return (obj==null ? new Object() : obj);
            }else{
                return obj;
            }
             
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return null;
        }
    }
     public String getValorCelulaString(String NomeCampo){
        
        try {
            String obj = null;  
            
            getRs().absolute(getLinhaAtual()+1);
            
            return getRs().getString(NomeCampo);
             
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return null;
        }
    }
    
    public Float getValorCelulaFloat(String NomeCampo){
        
        try {
            Float obj = null;  
            
            getRs().absolute(getLinhaAtual()+1);
            
            return getRs().getFloat(NomeCampo);
             
        } catch (Exception e) {
             LogDinnamus.Log(e, true);
             return null;
        }
    }
    
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object obj = null;
        try {
              //System.out.println(rowIndex);
              setLinhaAtual(rowIndex);  
              setColunaAtual(columnIndex);
              String cNomeColuna=getRs().getMetaData().getColumnName(columnIndex+1).toUpperCase();            
              getRs().absolute(rowIndex + 1);
              obj = getRs().getObject(columnIndex + 1);
              
                if(getNumberFormat().containsKey(cNomeColuna.toLowerCase())){
                    Float f = (obj==null ? 0 : new Float(obj.toString()));
                    obj=getNumberFormat().get(cNomeColuna.toLowerCase()).format(f);
                }else if(getDateFormat().containsKey(cNomeColuna.toLowerCase())){
                       if(obj!=null){
                         //Date d = (new SimpleDateFormat("dd/MM/yyyy")).format(obj.toString());
                         obj=getDateFormat().get(cNomeColuna.toLowerCase()).format(obj);
                       }
                    
                }
        } catch (SQLException ex) {
            Logger.getLogger(TbDinnamuSCached.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;

    }

    public int getRowCount() {
        int nIndice=-1;
        try {
            //if(getRs()!=null){
                getRs().beforeFirst();
                getRs().last();
                nIndice= getRs().getRow();
            //}
        } catch (Exception exception) {
                LogDinnamus.Log(exception);
        }

        return nIndice;
    }

    /**
     * @return the rs
     */
    public ResultSet getRs() {

        return rs;
    }

    /**
     * @param rs the rs to set
     */
    public void setRs(ResultSet rs) {
        try {            
            this.rs = rs;
            this.rsmd = rs.getMetaData();
        } catch (SQLException ex) {
            Logger.getLogger(TbDinnamuSCached.class.getName()).log(Level.SEVERE, null, ex);
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
    public HashMap<String, Integer> getClColunas() {
        return clColunas;
    }

    /**
     * @param clColunas the clColunas to set
     */
    public void setClColunas(HashMap<String, Integer> clColunas) {
        this.clColunas = clColunas;
    }

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
    public boolean RemoveRow(int Linha){
        try {
            int nLinha = getRowCount();
            getRs().absolute(Linha+1);
            getRs().deleteRow();
            nLinha = getRowCount();
            fireTableRowsDeleted(1, getRowCount());
           
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
    public Connection getEdicao_Cnx() {
        return Edicao_Cnx;
    }
    
    public boolean addRow(){
        try {
            ///rs.
            rs.moveToInsertRow();
            rs.insertRow();                
            fireTableRowsInserted(getRowCount()-1, getRowCount()-1);
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return false;
        
    }
    public boolean addRow(boolean GerarPK, Long ValorChaveEstrangeira, Integer Loja,Integer PDV,boolean online){
            try {
               
               Long PK =0l;
               int r = rs.getRow();
               rs.moveToInsertRow();               
               if(GerarPK){
                   //PK = Dao_Generica.NovoValorPK(Edicao_Tabela, Loja, PDV,online);               
                   rs.updateLong(getChavePrimaria(), PK);                   
               }
               if(ValorChaveEstrangeira!=null){
                 rs.updateLong(getChaveEstrangeira(), ValorChaveEstrangeira);                     
                 
               }                
               rs.insertRow();                                  
               
               rs.moveToCurrentRow();
               
               fireTableRowsInserted(rs.getRow()+1, rs.getRow()+1);
              
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    
    }
    @Override
    public void fireTableRowsInserted(int firstRow, int lastRow) {
        fireTableChanged(new TableModelEvent(this, firstRow, lastRow,
                             TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT));
    }
    
    @Override
    public void fireTableRowsUpdated(int firstRow, int lastRow) {
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

    public void tableChanged(TableModelEvent e) {
        
       
    }

    /**
     * @return the clColunasExibir
     */
  

    
 

}

