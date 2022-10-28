/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ui;

import java.sql.ResultSet;
import javax.swing.JTable;

/**
 *
 * @author Fernando
 */
public interface JTableDinnamuS_SubTabela {
    
    
    public ResultSet getModel(Long PK);
    public JTable getUI();  
}
