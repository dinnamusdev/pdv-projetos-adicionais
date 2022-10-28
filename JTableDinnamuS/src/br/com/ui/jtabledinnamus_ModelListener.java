/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ui;

import br.com.log.LogDinnamus;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author Fernando
 */
public class jtabledinnamus_ModelListener  implements TableModelListener {
    private JTable table = null;
    public jtabledinnamus_ModelListener(JTable table) {
               this.table = table;
    }
    
    public void tableChanged(TableModelEvent evt) {
        try {
             if (evt.getType() == TableModelEvent.UPDATE) {
                 table = (JTable) evt.getSource();
                 int column = evt.getColumn();
                 int row = evt.getFirstRow();
                 //System.out.println("row: " + row + " column: " + column);
                 //table.setColumnSelectionInterval(column + 1, column + 1);
                 //table.setRowSelectionInterval(row, row);
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        
     }
}
