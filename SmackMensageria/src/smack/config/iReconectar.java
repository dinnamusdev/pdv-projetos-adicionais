/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.config;

/**
 *
 * @author DSWM
 */
public interface iReconectar {
    public void reconectando();
    public void reconectadoOK();
    public void reconectadoFalha(Exception e);
    
}
