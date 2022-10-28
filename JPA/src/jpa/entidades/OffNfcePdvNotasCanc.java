/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "OFF_NFCE_PDV_NOTAS_CANC",schema = "DINNAMUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OffNfcePdvNotasCanc.findAll", query = "SELECT o FROM OffNfcePdvNotasCanc o"),
    @NamedQuery(name = "OffNfcePdvNotasCanc.findById", query = "SELECT o FROM OffNfcePdvNotasCanc o WHERE o.id = :id"),
    @NamedQuery(name = "OffNfcePdvNotasCanc.findByProtocolo", query = "SELECT o FROM OffNfcePdvNotasCanc o WHERE o.protocolo = :protocolo"),
    @NamedQuery(name = "OffNfcePdvNotasCanc.findByMotivo", query = "SELECT o FROM OffNfcePdvNotasCanc o WHERE o.motivo = :motivo"),
    @NamedQuery(name = "OffNfcePdvNotasCanc.findByUltimamodificacao", query = "SELECT o FROM OffNfcePdvNotasCanc o WHERE o.ultimamodificacao = :ultimamodificacao"),
    @NamedQuery(name = "OffNfcePdvNotasCanc.findByDatahora", query = "SELECT o FROM OffNfcePdvNotasCanc o WHERE o.datahora = :datahora")})
public class OffNfcePdvNotasCanc implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PROTOCOLO")
    private String protocolo;
    @Column(name = "MOTIVO")
    private String motivo;
    @Lob
    @Column(name = "XMLCANCELAMENTO")
    private Serializable xmlcancelamento;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimamodificacao;
    @Column(name = "DATAHORA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datahora;
    @JoinColumn(name = "ID_NFCE_PDV_NOTAS", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdvNotas idNfcePdvNotas;

    public OffNfcePdvNotasCanc() {
    }

    public OffNfcePdvNotasCanc(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public Serializable getXmlcancelamento() {
        return xmlcancelamento;
    }

    public void setXmlcancelamento(Serializable xmlcancelamento) {
        this.xmlcancelamento = xmlcancelamento;
    }

    public Date getUltimamodificacao() {
        return ultimamodificacao;
    }

    public void setUltimamodificacao(Date ultimamodificacao) {
        this.ultimamodificacao = ultimamodificacao;
    }

    public Date getDatahora() {
        return datahora;
    }

    public void setDatahora(Date datahora) {
        this.datahora = datahora;
    }

    public OffNfcePdvNotas getIdNfcePdvNotas() {
        return idNfcePdvNotas;
    }

    public void setIdNfcePdvNotas(OffNfcePdvNotas idNfcePdvNotas) {
        this.idNfcePdvNotas = idNfcePdvNotas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OffNfcePdvNotasCanc)) {
            return false;
        }
        OffNfcePdvNotasCanc other = (OffNfcePdvNotasCanc) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entidades.OffNfcePdvNotasCanc[ id=" + id + " ]";
    }
    
}
