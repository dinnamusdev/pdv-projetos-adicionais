/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.servidor;

/**
 *
 * @author DSWM
 */
public class Dao_Jdbc_1 {
    private static Dao_Jdbc_Base conexao =null;

    
    public static  Dao_Jdbc_Base getConexao() {
         if(conexao==null){
             conexao = new Dao_Jdbc_Base();
         }
        return conexao;
    }
}
