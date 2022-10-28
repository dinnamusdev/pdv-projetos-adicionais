/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.javaDBExtensao;

/**
 *
 * @author Administrador
 */
public class Funcoes {

  public static String IsNull(String STR1, String STR2){
        if(STR1==null){
            return STR2.trim();
        }else{
            return STR1.trim();
        }
   }
}
