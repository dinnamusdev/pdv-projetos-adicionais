package br.com.ui;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author dti
 */
public class ItemLista {
    private Object Indice;
    private String Descricao;

    /**
     * @return the Descricao
     */

    public String toString()
    {
        return getDescricao();
    }

    /**
     * @return the Indice
     */
    public Object getIndice() {
        return Indice;
    }

    /**
     * @param Indice the Indice to set
     */
    public void setIndice(Object Indice) {
        this.Indice = Indice;
    }

    /**
     * @return the Descricao
     */
    public String getDescricao() {
        return Descricao;
    }

    /**
     * @param Descricao the Descricao to set
     */
    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }
}
