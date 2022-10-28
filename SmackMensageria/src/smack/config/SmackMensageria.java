/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.config;

import br.com.log.LogDinnamus;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.ConnectionListener;
import org.jivesoftware.smack.ReconnectionManager;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat.Chat;
import org.jivesoftware.smack.chat.ChatManager;
import org.jivesoftware.smack.chat.ChatManagerListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.provider.ProviderManager;
import org.jivesoftware.smack.roster.Roster;
import org.jivesoftware.smack.roster.RosterEntry;
import org.jivesoftware.smack.sasl.javax.SASLPlainMechanism;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jivesoftware.smackx.iqregister.AccountManager;
import org.jivesoftware.smackx.offline.OfflineMessageManager;
import org.jivesoftware.smackx.receipts.DeliveryReceipt;
import org.jivesoftware.smackx.receipts.DeliveryReceiptManager;
import org.jivesoftware.smackx.receipts.DeliveryReceiptRequest;
import org.jivesoftware.smackx.search.ReportedData;
import org.jivesoftware.smackx.search.ReportedData.Row;
import org.jivesoftware.smackx.search.UserSearchManager;
import org.jivesoftware.smackx.vcardtemp.VCardManager;
import org.jivesoftware.smackx.vcardtemp.packet.VCard;
import org.jivesoftware.smackx.xdata.Form;

/**
 *
 * @author DSWM
 */
public class SmackMensageria {

    /*
    1 - conectarServidor()
    2 - logarUsuario()
    3 - criarConversa()
    4 - enviarMensagem()
    
    */
    private String msg;
    private ConnectionConfiguration configuration;
    private XMPPTCPConnection connection;
    private AccountManager accountManager;
    private ChatManager chatManager; 
    private Chat createChat;
    private Roster roster;
    private String usuario;
    private ReconnectionManager reconnectionManager;
    private ChatManagerListener chatManagerListener;
    
    /*
     public void connected(XMPPConnection connection);

    public void authenticated(XMPPConnection connection, boolean resumed);

    public void connectionClosed();

    public void connectionClosedOnError(Exception e);

    public void reconnectionSuccessful();

    public void reconnectingIn(int seconds);

    public void reconnectionFailed(Exception e);
    */
    
    public  List<Message> getMensagensOffline(){
          List<Message> messages =null;
        try {
            OfflineMessageManager offlineMessageManager = new OfflineMessageManager(connection);
             messages = offlineMessageManager.getMessages();
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return messages;
        
    }
    private StatusConexao statusConexao;
                                
    public ReconnectionManager getReconnectionManager() {
        return reconnectionManager;
    }

    public void setReconnectionManager(ReconnectionManager reconnectionManager) {
        this.reconnectionManager = reconnectionManager;
    }
    public void reconectar(ConnectionListener connectionListener){
        
    }
    public void iniciarReconnectionManager(){
        reconnectionManager = ReconnectionManager.getInstanceFor(connection);
        reconnectionManager.disableAutomaticReconnection();
        reconnectionManager.setEnabledPerDefault(false);
        reconnectionManager.setReconnectionPolicy(ReconnectionManager.ReconnectionPolicy.FIXED_DELAY);
        reconnectionManager.setFixedDelay(5);
        
       // reconnectionManager.setFixedDelay(1);
        //reconnectionManager.setReconnectionPolicy(ReconnectionManager.ReconnectionPolicy.FIXED_DELAY);
    } 
    private ConnectionConfiguration getConfiguracao(String nomeServico, String servidorOpenFire, Integer porta, String usuario, String senha) {
        ConnectionConfiguration connectionConfiguration = null;
        try {
            connectionConfiguration = XMPPTCPConnectionConfiguration.builder()
                    .setSecurityMode(ConnectionConfiguration.SecurityMode.disabled)
                    .setDebuggerEnabled(true)
                    .setSendPresence(true)
                    .setUsernameAndPassword(usuario, senha)
                    .setServiceName(servidorOpenFire)
                    .setHost(servidorOpenFire)
                    //.setReconnectionAllowed
                    .setCompressionEnabled(false)
                    .build();

           // DeliveryReceiptManager.setDefaultAutoReceiptMode(DeliveryReceiptManager.AutoReceiptMode.always);
           // ProviderManager.addExtensionProvider(DeliveryReceipt.ELEMENT, DeliveryReceipt.NAMESPACE, new DeliveryReceipt.Provider());
           // ProviderManager.addExtensionProvider(DeliveryReceiptRequest.ELEMENT, DeliveryReceipt.NAMESPACE, new DeliveryReceiptRequest.Provider());

        } catch (Exception e) {
             msg = e.getMessage();
            LogDinnamus.Log(e, false);
        }
        return connectionConfiguration;
    }

    public boolean removerContato(String usuarioTo){
        try {
            RosterEntry entry = getRoster().getEntry(usuarioTo);
            if(entry!=null){
                
               enviarPresenca(usuarioTo,Presence.Mode.available,Presence.Type.unsubscribe );
            
               getRoster().removeEntry(entry);
                
               return true;
            }
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return false;
    }
      public boolean enviarPresenca(String usuarioTo ,Presence.Mode modo, Presence.Type tipo){
        try {
            
            Presence pres = new Presence(tipo);
            pres.setTo(usuarioTo);
            pres.setMode(modo);
            connection.sendStanza(pres);

      //      Roster roster = connection.getRoster();
         //   roster.createEntry(address, name, groups);
         
            
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
    public boolean enviarSolicitacaoPresenca(String usuarioTo, String usuarioFrom){
        try {
            
            
            Presence subscribe = new Presence(Presence.Type.subscribe);
            subscribe.setPriority(24);
            subscribe.setMode(Presence.Mode.available);
            subscribe.setTo(usuarioTo);
            subscribe.setFrom(usuarioFrom);

            connection.sendStanza(subscribe);
            
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
            return false;
        }
    }
    public boolean checkIfUserExists(String user) {
        try {
     
            UserSearchManager search = new UserSearchManager(getConnection());
            Form searchForm = search.getSearchForm("search." + getConnection().getServiceName());
            Form answerForm = searchForm.createAnswerForm();
            answerForm.setAnswer("Username", true);
            answerForm.setAnswer("search", user);
            ReportedData data = search.getSearchResults(answerForm, "search." + getConnection().getServiceName());
            if (data.getRows() != null) {
                Iterator<Row> it = data.getRows().iterator();
                while (it.hasNext()) {
                    Row row = it.next();
                    Iterator iterator = row.getValues("jid").iterator();
                    if (iterator.hasNext()) {
                       return true;
                    }
                }
                return false;
            }

        } catch (Exception e) {
            LogDinnamus.Log(e, true);

        }
        return false;
    }
    public boolean adicionarContato(String usuarioTo, String nome ){
        try {
            usuarioTo += "@"+connection.getHost();
           
            enviarPresenca(usuarioTo,Presence.Mode.available,Presence.Type.subscribe );
            
            getRoster().createEntry(usuarioTo, nome, null);
            
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return false;
    }
   
    public boolean logarUsuario(String usuario , String senha, Presence.Type typePresence, String nomePresenca,Map<String,String> atributos){
         boolean ok = false;
        try {
             accountManager = AccountManager.getInstance(getConnection());
             
            if (accountManager.supportsAccountCreation()) {

                accountManager.sensitiveOperationOverInsecureConnection(true);
                boolean usuarioLocalizado = false;
                try {
                    if(atributos!=null){
                       accountManager.createAccount(usuario, senha,atributos);
                    }else{
                       accountManager.createAccount(usuario, senha);
                    }
                } catch (SmackException.NoResponseException | XMPPException.XMPPErrorException | SmackException.NotConnectedException e) {
                    usuarioLocalizado = true;
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                
                 getConnection().login(usuario, senha);
                 Presence p = new Presence(typePresence);
                 p.setStatus(nomePresenca);
                 getConnection().sendStanza(p);
                 
                 this.usuario = usuario + "@" + connection.getHost() + "/Smack";
                 ok=true;
            }
        } catch (Exception e) {
             msg = e.getMessage();
            LogDinnamus.Log(e, false);
        }
        return ok;
    }
    public VCard getVCard( ){
        return getVCard(null);
    }
    public VCard getVCard(String bareJID ){
        VCard card =null;
        try {
            if(connection.isConnected()){
                VCardManager instanceFor = VCardManager.getInstanceFor(connection);
                if(bareJID!=null)
                   card = instanceFor.loadVCard(bareJID);
                else
                   card = instanceFor.loadVCard();
            }
                
        } catch (SmackException.NoResponseException | XMPPException.XMPPErrorException | SmackException.NotConnectedException e) {
            LogDinnamus.Log(e, true);
        }
        return card;
    }
    public boolean definirAvatar( String arquivo){
          try {
              
            VCardManager instanceFor = VCardManager.getInstanceFor(connection);
            
            VCard vCard = instanceFor.loadVCard();

            RandomAccessFile f = new RandomAccessFile(arquivo, "r");
            byte[] bytes = new byte[(int) f.length()];
            f.read(bytes);
            f.close();
            
            vCard.setAvatar(bytes, "avatar1/jpg");
            
            instanceFor.saveVCard(vCard);
            
          
            
              return true;
          } catch (SmackException.NoResponseException | XMPPException.XMPPErrorException | SmackException.NotConnectedException | IOException e) {
              LogDinnamus.Log(e, true);
          }
          return false;
      }
    
     public boolean modificarAtributos( HashMap<String,String> atributos){
        try {
            VCardManager instanceFor = VCardManager.getInstanceFor(connection);
            
            VCard vCard = instanceFor.loadVCard();
            
            for (Map.Entry<String, String> entry : atributos.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                
                vCard.setField(key, value);
                
            }
            
            instanceFor.saveVCard(vCard);
            
            
            return true;
        } catch (SmackException.NoResponseException | XMPPException.XMPPErrorException | SmackException.NotConnectedException e) {
            LogDinnamus.Log(e, true);
        }
        return false;
    }
    public boolean modificarSenha( String novaSenha){
        try {
            
            
            
            accountManager.changePassword(novaSenha);
            
            return true;
        } catch (Exception e) {
            LogDinnamus.Log(e, true);
        }
        return false;
    }
    public boolean removerChatListener(){
        try {
            
            chatManager.removeChatListener(chatManagerListener);
            
            return true;
        } catch (Exception e) {
              msg = e.getMessage();
            LogDinnamus.Log(e, false);
            return false;
        }
    }
    public boolean monitorarEntradaDeChat(ChatManagerListener chatManagerListener){
         boolean ok = false;
        try {
            if (chatManager == null) {
                chatManager = ChatManager.getInstanceFor(getConnection());
            }
            
            chatManager.addChatListener(chatManagerListener);
            this.chatManagerListener = chatManagerListener;
            
        } catch (Exception e) {
            msg = e.getMessage();
            LogDinnamus.Log(e, false);
        }
        return ok;
    }
    public Chat iniciarChat(String participante){
        Chat chat = null;
        try {
                if(chatManager==null){
                   chatManager = ChatManager.getInstanceFor(getConnection());
                }
                if(createChat==null){
                     chat = chatManager.createChat(participante);
                     
                }
               
        } catch (Exception e) {
            msg = e.getMessage();
            LogDinnamus.Log(e, false);
        }
         return chat;
    }
    public boolean enviarMensagem(String msg){
         boolean ok = false;
        try {
            
            createChat.sendMessage(msg);
            
        } catch (Exception e) {
             msg = e.getMessage();
             LogDinnamus.Log(e, false);
        }
        return ok;
    }
            
    
    public Roster getRoster(){
        if(roster==null){
            getUsuariosServidor();
        }
        return roster;
    }
    
      
    public Collection<RosterEntry> getUsuariosServidor(){
        Collection<RosterEntry> ret=null;
        try {
          
            roster = Roster.getInstanceFor(connection);
            
            roster.setRosterLoadedAtLogin(false);
            
            roster.reload();
            
            Thread.sleep(2000);
            
            ret = roster.getEntries();
        } catch (Exception e) {
             msg = e.getMessage();
             LogDinnamus.Log(e, false);
        }
        return ret;
    }
    public boolean conectarServidor(String nomeServico, String servidorOpenFire, Integer porta, String usuarioServidor, String senhaServidor, ConnectionListener connectionlistener) {
        boolean ok = false;
        try {

            //ProviderManager.addExtensionProvider(ReadReceipt.ELEMENT, ReadReceipt.NAMESPACE, new ReadReceipt.Provider());
            
            configuration = getConfiguracao(nomeServico, servidorOpenFire, porta, usuarioServidor, senhaServidor);
            
            setConnection(new XMPPTCPConnection((XMPPTCPConnectionConfiguration) configuration));
            try {
                
                SASLAuthentication.registerSASLMechanism(new SASLPlainMechanism());
                
                getConnection().setPacketReplyTimeout(30000);
                connection.setUseStreamManagement(false);
                if (connection.isConnected()) {
                    connection.disconnect();
                }
                
               

                 if(connectionlistener!=null){
                    getConnection().addConnectionListener(connectionlistener);
                }
                
                  getConnection().connect();
              
                  
                  //iniciarReconnectionManager();
                 
                
                 
                
                ok=true;
            } catch (SmackException ex) {
              LogDinnamus.Log(ex, false);
            } catch (IOException ex) {
              LogDinnamus.Log(ex, false);
            } catch (XMPPException ex) {
                LogDinnamus.Log(ex, false);
            }

        } catch (Exception e) {
             msg = e.getMessage();
            LogDinnamus.Log(e, false);
        }
        return ok;
    }

  
        
   
    
 
    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the chatManager
     */
    public ChatManager getChatManager() {
        return chatManager;
    }

    /**
     * @param chatManager the chatManager to set
     */
    public void setChatManager(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    /**
     * @return the connection
     */
    public XMPPTCPConnection getConnection() {
        return connection;
    }

    /**
     * @param connection the connection to set
     */
    public void setConnection(XMPPTCPConnection connection) {
        this.connection = connection;
    }

    /**
     * @return the usuario
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    /**
     * @return the statusConexao
     */
    public StatusConexao getStatusConexao() {
        return statusConexao;
    }

    /**
     * @param statusConexao the statusConexao to set
     */
    public void setStatusConexao(StatusConexao statusConexao) {
        this.statusConexao = statusConexao;
    }

    
}
