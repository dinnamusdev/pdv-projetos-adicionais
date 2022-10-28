/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.util;

/**
 *
 * @author dti
 */
public class DAO_Parametro_Generico<T> {
    private String cNomeParamentro;
    private T gValorParamentro;
    public DAO_Parametro_Generico(String cNomeParamentro, T gValorParametro )
    {
      this.cNomeParamentro=cNomeParamentro;
      this.gValorParamentro = gValorParametro;
    }
    public String getNomeParamentro() {
        return cNomeParamentro;
    }
    public void setNomeParamentro(String cNomeParamentro) {
        this.cNomeParamentro=cNomeParamentro;
    }

    public T getGValorParamentro() {
        return gValorParamentro;
    }

    public void setGValorParamentro(T gValorParamentro) {
        this.gValorParamentro=gValorParamentro;
    }

}
