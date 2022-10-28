/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.br.ui;

import java.util.Map;
import javax.swing.ImageIcon;
import org.jivesoftware.smack.packet.Message;

/**
 *
 * @author DSWM
 */
public class SmackListItemContato {
    private Integer id;
    private String nome;
      private String nickname;
    private String JID;
    private ImageIcon imageIcon;
    private String usuario;
    private ImageIcon imageIconStatus;
    private Message ultimaMensagemNaoLida;
    private Integer qtMensagemNaoLidas;

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    
    /**
     * @return the imageIcon
     */
    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    /**
     * @param imageIcon the imageIcon to set
     */
    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the JID
     */
    public String getJID() {
        return JID;
    }

    /**
     * @param JID the JID to set
     */
    public void setJID(String JID) {
        this.JID = JID;
    }

    /**
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
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
     * @return the imageIconStatus
     */
    public ImageIcon getImageIconStatus() {
        return imageIconStatus;
    }

    /**
     * @param imageIconStatus the imageIconStatus to set
     */
    public void setImageIconStatus(ImageIcon imageIconStatus) {
        this.imageIconStatus = imageIconStatus;
    }

    /**
     * @return the ultimaMensagemNaoLida
     */
    public Message getUltimaMensagemNaoLida() {
        return ultimaMensagemNaoLida;
    }

    /**
     * @param ultimaMensagemNaoLida the ultimaMensagemNaoLida to set
     */
    public void setUltimaMensagemNaoLida(Message ultimaMensagemNaoLida) {
        this.ultimaMensagemNaoLida = ultimaMensagemNaoLida;
    }

    /**
     * @return the qtMensagemNaoLidas
     */
    public Integer getQtMensagemNaoLidas() {
        return qtMensagemNaoLidas;
    }

    /**
     * @param qtMensagemNaoLidas the qtMensagemNaoLidas to set
     */
    public void setQtMensagemNaoLidas(Integer qtMensagemNaoLidas) {
        this.qtMensagemNaoLidas = qtMensagemNaoLidas;
    }
    
}
