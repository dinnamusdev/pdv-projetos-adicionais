/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.config;

import br.com.log.LogDinnamus;
import java.io.IOException;
import org.jivesoftware.smack.AbstractConnectionListener;
import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.XMPPException.StreamErrorException;
import org.jivesoftware.smack.packet.StreamError;
import org.jivesoftware.smack.util.Async;

/**
 *
 * @author DSWM
 */
public class Reconectar {
    private StatusConexao conexao;
    AbstractXMPPConnection connection;
    int intervaloEntreTentativas;
    private Runnable reconnectionRunnable=null;
    private Thread reconnectionThread=null;
   
     private ConnectionListener _connectionListener;
    
    private boolean done; 
    public Reconectar( AbstractXMPPConnection connection, int intervaloEntreTentativas , ConnectionListener _connectionListener) {
        this.connection=connection;
        this.intervaloEntreTentativas=intervaloEntreTentativas;
        this.connection.addConnectionListener(connectionListener);
        this._connectionListener = _connectionListener;
       // this.connection.addConnectionListener(connectionListener2);
        
        iniciarRunnableReconectar();
       
    }
    
    private boolean isReconnectionPossible(XMPPConnection connection) {
        return !done && !connection.isConnected(); 
               
    }
    private int timeDelay(){
        return intervaloEntreTentativas;
    }
    
    private void iniciarRunnableReconectar(){
        reconnectionRunnable = new Runnable() {

            /**
             * Holds the current number of reconnection attempts
             */
            private int attempts = 0;

            /**
             * Returns the number of seconds until the next reconnection attempt.
             *
             * @return the number of seconds until the next reconnection attempt.
             */
          

            /**
             * The process will try the reconnection until the connection succeed or the user cancel it
             */
            @Override
            public void run() {
                System.out.println("Iniciando tarefa reconectar...");
                // Reset attempts to zero since a new reconnection cycle is started once this runs.
                attempts = 0;
                
                // The process will try to reconnect until the connection is established or
                // the user cancel the reconnection process AbstractXMPPConnection.disconnect().
                while (isReconnectionPossible(connection)) {
                    // Find how much time we should wait until the next reconnection
                    int remainingSeconds = timeDelay();
                    // Sleep until we're ready for the next reconnection attempt. Notify
                    // listeners once per second about how much time remains before the next
                    // reconnection attempt.
                    while (remainingSeconds > 0) {
                        if (!isReconnectionPossible(connection)) {
                             System.out.println("tarefa reconectar...nao foi possivel reconectar");
                            return;
                        }
                        try {
                            Thread.sleep(1000);
                            remainingSeconds--;
                            LogDinnamus.Informacao("Aguardando ...."+ remainingSeconds + " para reconectar");
                            
                            _connectionListener.reconnectingIn(remainingSeconds);
                            
                        }
                        catch (InterruptedException e) {
                             System.out.println("tarefa reconectar...interrompida");
                            LogDinnamus.Log(e,false);
                            // Exit the reconnection thread in case it was interrupted.
                            return;
                        }
                    }
                    _connectionListener.reconnectingIn(0);
                    

                    if (!isReconnectionPossible(connection)) {
                        return;
                    }
                    // Makes a reconnection attempt
                    try {
                        try {
                            LogDinnamus.Informacao("tentando reconectar....");
                            connection.connect();
                        }
                        catch (SmackException.AlreadyConnectedException e) {
                             LogDinnamus.Informacao("falhou tentando reconectar");
                             _connectionListener.reconnectionFailed(e);
                            LogDinnamus.Log(e,false);
                           
                        }
                        //LogDinnamus.Informacao("tentando fazer login....");
                        //connection.login();
                        _connectionListener.reconnectionSuccessful();
                    }
                    catch (SmackException.AlreadyLoggedInException e) {
                        // This can happen if another thread concurrently triggers a reconnection
                        // and/or login. Obviously it should not be handled as a reconnection
                        // failure. See also SMACK-725.
                         //LogDinnamus.Informacao("Usuário ja esta logado");
                           _connectionListener.reconnectionFailed(e);
                      
                    }
                    catch (SmackException | IOException | XMPPException e) {
                        //LogDinnamus.Informacao("Usuário ja esta logado");
                        LogDinnamus.Log(e, false);
                        _connectionListener.reconnectionFailed(e);
                    }
                     System.out.println("terminando tarefa reconectar...");
                    return;
                }
            }
        };

    }
     public synchronized void abortPossiblyRunningReconnection() {
        if (reconnectionThread == null) {
            return;
        }

        reconnectionThread.interrupt();
        reconnectionThread = null;
    }
     public boolean TarafeAtiva(){
         return (reconnectionThread != null && reconnectionThread.isAlive());
     }
     
     public synchronized void reconnect() {
       // XMPPConnection connection = this.weakRefConnection.get();
        if (connection == null) {
           LogDinnamus.getLogger().warn("Connection is null, will not reconnect");
            return;
        }
        // Since there is no thread running, creates a new one to attempt
        // the reconnection.
        // avoid to run duplicated reconnectionThread -- fd: 16/09/2010
        if (reconnectionThread != null && reconnectionThread.isAlive())
            return;

        reconnectionThread = Async.go(reconnectionRunnable,
                        "Smack Reconnection Manager (" + connection.getConnectionCounter() + ')');
    }

    
    private final ConnectionListener connectionListener = new AbstractConnectionListener() {

        @Override
        public void connectionClosed() {
            done = false;
        }

        @Override
        public void authenticated(XMPPConnection connection, boolean resumed) {
            done = false;
        }

        @Override
        public void connectionClosedOnError(Exception e) {
            done = false;
           
            if (e instanceof StreamErrorException) {
                StreamErrorException xmppEx = (StreamErrorException) e;
                StreamError error = xmppEx.getStreamError();

                if (StreamError.Condition.conflict == error.getCondition()) {
                    return;
                }
            }

            reconnect();
        }
};
     
}
