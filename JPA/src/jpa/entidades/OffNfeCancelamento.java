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
@Table(name = "OFF_NFE_CANCELAMENTO")
@XmlRootElement
public class OffNfeCancelamento implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDNFECANC")
    private Long idnfecanc;
    @Column(name = "TPAMB")
    private Integer tpamb;
    @Column(name = "XSERV")
    private String xserv;
    @Column(name = "CHNFE")
    private String chnfe;
    @Column(name = "NPROT")
    private String nprot;
    @Column(name = "XJUST")
    private String xjust;
    @Column(name = "PROTOCOLODECANCELAMENTO")
    private String protocolodecancelamento;
    @Column(name = "DATACANCELAMENTO")
    @Temporal(TemporalType.DATE)
    private Date datacancelamento;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.DATE)
    private Date ultimamodificacao;
    @JoinColumn(name = "CODIGONFE", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdvNotas codigonfe;

    public OffNfeCancelamento() {
    }

    public OffNfeCancelamento(Long idnfecanc) {
        this.idnfecanc = idnfecanc;
    }

    public Long getIdnfecanc() {
        return idnfecanc;
    }

    public void setIdnfecanc(Long idnfecanc) {
        this.idnfecanc = idnfecanc;
    }

    public Integer getTpamb() {
        return tpamb;
    }

    public void setTpamb(Integer tpamb) {
        this.tpamb = tpamb;
    }

    public String getXserv() {
        return xserv;
    }

    public void setXserv(String xserv) {
        this.xserv = xserv;
    }

    public String getChnfe() {
        return chnfe;
    }

    public void setChnfe(String chnfe) {
        this.chnfe = chnfe;
    }

    public String getNprot() {
        return nprot;
    }

    public void setNprot(String nprot) {
        this.nprot = nprot;
    }

    public String getXjust() {
        return xjust;
    }

    public void setXjust(String xjust) {
        this.xjust = xjust;
    }

    public String getProtocolodecancelamento() {
        return protocolodecancelamento;
    }

    public void setProtocolodecancelamento(String protocolodecancelamento) {
        this.protocolodecancelamento = protocolodecancelamento;
    }

    public Date getDatacancelamento() {
        return datacancelamento;
    }

    public void setDatacancelamento(Date datacancelamento) {
        this.datacancelamento = datacancelamento;
    }

    public Date getUltimamodificacao() {
        return ultimamodificacao;
    }

    public void setUltimamodificacao(Date ultimamodificacao) {
        this.ultimamodificacao = ultimamodificacao;
    }

    public OffNfcePdvNotas getCodigonfe() {
        return codigonfe;
    }

    public void setCodigonfe(OffNfcePdvNotas codigonfe) {
        this.codigonfe = codigonfe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idnfecanc != null ? idnfecanc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OffNfeCancelamento)) {
            return false;
        }
        OffNfeCancelamento other = (OffNfeCancelamento) object;
        if ((this.idnfecanc == null && other.idnfecanc != null) || (this.idnfecanc != null && !this.idnfecanc.equals(other.idnfecanc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.java.com.fincatto.documentofiscal.OffNfeCancelamento[ idnfecanc=" + idnfecanc + " ]";
    }
    
}
