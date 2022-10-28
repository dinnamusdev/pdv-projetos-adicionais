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
   public  enum StatusConexao{
    connected(1),
    authenticated(2),
    connectionClosed(3),
    connectionClosedOnError(4),
    reconnectionSuccessful(5),
    reconnectingIn(6),
    reconnectionFailed(7);
                        
    private final int codigo;

    private StatusConexao(int codigo) {
        this.codigo = codigo;
    }
    
    int codigo() { return codigo; }

    
    public  StatusConexao porCodigo(int codigo) {
        for (StatusConexao conexao: StatusConexao.values()) {
            if (codigo == conexao.codigo()) return conexao;
        }
        throw new IllegalArgumentException("codigo invalido");
    }
}