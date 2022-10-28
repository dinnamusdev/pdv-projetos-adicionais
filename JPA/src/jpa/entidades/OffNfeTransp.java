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
@Table(name = "OFF_NFE_TRANSP",schema = "DINNAMUS")
@XmlRootElement
public class OffNfeTransp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGONFE_TRANSP")
    private Long codigonfeTransp;
    @Column(name = "CODIGOTRANSP")
    private Integer codigotransp;
    @Column(name = "MODALIDADEFRETE")
    private Integer modalidadefrete;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.DATE)
    private Date ultimamodificacao;
    @JoinColumn(name = "CODIGONFE", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdvNotas codigonfe;

    public OffNfeTransp() {
    }

    public OffNfeTransp(Long codigonfeTransp) {
        this.codigonfeTransp = codigonfeTransp;
    }

    public Long getCodigonfeTransp() {
        return codigonfeTransp;
    }

    public void setCodigonfeTransp(Long codigonfeTransp) {
        this.codigonfeTransp = codigonfeTransp;
    }

    public Integer getCodigotransp() {
        return codigotransp;
    }

    public void setCodigotransp(Integer codigotransp) {
        this.codigotransp = codigotransp;
    }

    public Integer getModalidadefrete() {
        return modalidadefrete;
    }

    public void setModalidadefrete(Integer modalidadefrete) {
        this.modalidadefrete = modalidadefrete;
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
        hash += (codigonfeTransp != null ? codigonfeTransp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OffNfeTransp)) {
            return false;
        }
        OffNfeTransp other = (OffNfeTransp) object;
        if ((this.codigonfeTransp == null && other.codigonfeTransp != null) || (this.codigonfeTransp != null && !this.codigonfeTransp.equals(other.codigonfeTransp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.java.com.fincatto.documentofiscal.OffNfeTransp[ codigonfeTransp=" + codigonfeTransp + " ]";
    }
    
}
