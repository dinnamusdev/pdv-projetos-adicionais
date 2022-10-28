/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ui;

import javax.swing.AbstractListModel;
import javax.swing.JCheckBox;


public class ModelListCheckImpl extends AbstractListModel {

    private JCheckBox[] checks =new JCheckBox[5];
    public int getSize() {
        return getChecks().length;
    }

    public JCheckBox getElementAt(int index) {
        return getChecks()[index];
    }

    /**
     * @return the checks
     */
    public JCheckBox[] getChecks() {
        if(checks[0]==null){
            checks[0]= new JCheckBox("Op1", true);
            checks[1]=new JCheckBox("Op2", false);
            checks[2]= new JCheckBox("Op3", false);
            checks[3]=new JCheckBox("Op4", false);
            checks[4]=new JCheckBox("Op5", false);
        }
        return checks;
    }

    /**
     * @param checks the checks to set
     */
    public void setChecks(JCheckBox[] checks) {
        this.checks = checks;
    }
    
}
