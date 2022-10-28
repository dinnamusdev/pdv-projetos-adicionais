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
@Table(name = "OFF_NFCE_PDV_NOTAS_CPL",schema = "DINNAMUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OffNfcePdvNotasCpl.findAll", query = "SELECT o FROM OffNfcePdvNotasCpl o"),
    @NamedQuery(name = "OffNfcePdvNotasCpl.findById", query = "SELECT o FROM OffNfcePdvNotasCpl o WHERE o.id = :id"),
    @NamedQuery(name = "OffNfcePdvNotasCpl.findByUltimamodificacao", query = "SELECT o FROM OffNfcePdvNotasCpl o WHERE o.ultimamodificacao = :ultimamodificacao")})
public class OffNfcePdvNotasCpl implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Lob
    @Column(name = "XMLNFE")
    private byte[] xmlnfe;
    @Lob
    @Column(name = "QRCODE")
    private byte[] qrcode;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimamodificacao;
    @JoinColumn(name = "ID_NFCE_PDV_NOTAS", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdvNotas idNfcePdvNotas;

    public OffNfcePdvNotasCpl() {
    }

    public OffNfcePdvNotasCpl(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getXmlnfe() {
        return xmlnfe;
    }

    public void setXmlnfe(byte[] xmlnfe) {
        this.xmlnfe = xmlnfe;
    }

    public byte[] getQrcode() {
        return qrcode;
    }

    public void setQrcode(byte[] qrcode) {
        this.qrcode = qrcode;
    }

    public Date getUltimamodificacao() {
        return ultimamodificacao;
    }

    public void setUltimamodificacao(Date ultimamodificacao) {
        this.ultimamodificacao = ultimamodificacao;
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
        if (!(object instanceof OffNfcePdvNotasCpl)) {
            return false;
        }
        OffNfcePdvNotasCpl other = (OffNfcePdvNotasCpl) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entidades.OffNfcePdvNotasCpl[ id=" + id + " ]";
    }
    
}
