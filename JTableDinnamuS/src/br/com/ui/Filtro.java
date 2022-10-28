/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ui;

import br.com.log.LogDinnamus;
import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.rowset.Predicate;

/**
 *
 * @author Fernando
 */
public class Filtro implements Predicate{
    private String CampoChaveEstrangeira;
    private Long ValorChavePrimaria ;

    public Filtro(String CampoChaveEstrangeira, Long ValorChavePrimaria) {
        this.CampoChaveEstrangeira=CampoChaveEstrangeira;
        this.ValorChavePrimaria=ValorChavePrimaria;
    }
    
    public boolean evaluate(RowSet rs) {
         boolean bRet = false;
        try {
            if(rs!=null){
               if(rs.getRow()>0){
                    Long ValorFK = rs.getLong(CampoChaveEstrangeira);
                    if(ValorFK.longValue()==ValorChavePrimaria.longValue()){
                       bRet=true; 
                       System.out.println("ChavePrimaria : " + ValorChavePrimaria.toString());
                    }
               }
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return bRet;
                
    }
    public boolean evaluate(Object value, int column) throws SQLException {
        return false;
    }
    public boolean evaluate(Object value, String columnName) throws SQLException {
        boolean bRet = false;
        try {
            Long ValorChaveEstrangeira = new Long(value.toString());

            if(columnName.equalsIgnoreCase(CampoChaveEstrangeira) && ValorChaveEstrangeira==ValorChavePrimaria){
               bRet=true;
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);

        }
        return true;
    }
    
}
