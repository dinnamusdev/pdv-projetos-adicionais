/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package smack.br.ui;

import java.util.Date;
import java.util.Objects;
import org.jivesoftware.smack.packet.Message;

/**
 *
 * @author DSWM
 */
public class SmackMensagem {

    private Integer id;
    private Message msg;
    private Date datahorarecebida;
    private Date datahoraEnviada;
    private Date datahoraLida;
    private String stanzaID;
    private String IDMensagem;
    
       

    @Override

    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SmackMensagem other = (SmackMensagem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    private String tipo;

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
     * @return the msg
     */
    public Message getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(Message msg) {
        this.msg = msg;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the datahorarecebida
     */
    public Date getDatahorarecebida() {
        return datahorarecebida;
    }

    /**
     * @param datahorarecebida the datahorarecebida to set
     */
    public void setDatahorarecebida(Date datahorarecebida) {
        this.datahorarecebida = datahorarecebida;
    }

    /**
     * @return the datahoraEnviada
     */
    public Date getDatahoraEnviada() {
        return datahoraEnviada;
    }

    /**
     * @param datahoraEnviada the datahoraEnviada to set
     */
    public void setDatahoraEnviada(Date datahoraEnviada) {
        this.datahoraEnviada = datahoraEnviada;
    }

    /**
     * @return the datahoraLida
     */
    public Date getDatahoraLida() {
        return datahoraLida;
    }

    /**
     * @param datahoraLida the datahoraLida to set
     */
    public void setDatahoraLida(Date datahoraLida) {
        this.datahoraLida = datahoraLida;
    }

    /**
     * @return the stanzaID
     */
    public String getStanzaID() {
        return stanzaID;
    }

    /**
     * @param stanzaID the stanzaID to set
     */
    public void setStanzaID(String stanzaID) {
        this.stanzaID = stanzaID;
    }

    /**
     * @return the IDMensagem
     */
    public String getIDMensagem() {
        return IDMensagem;
    }

    /**
     * @param IDMensagem the IDMensagem to set
     */
    public void setIDMensagem(String IDMensagem) {
        this.IDMensagem = IDMensagem;
    }

}
