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
@Table(name = "OFF_NFE_CARTACORRECAO",schema = "DINNAMUS")
@XmlRootElement

public class OffNfeCartacorrecao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "XCORRECAO")
    private String xcorrecao;
    @Column(name = "DATAHORAEVENTO")
    @Temporal(TemporalType.DATE)
    private Date datahoraevento;
    @Column(name = "PROTOCOLO")
    private String protocolo;
    @Column(name = "DATAREC")
    @Temporal(TemporalType.DATE)
    private Date datarec;
    @JoinColumn(name = "CODIGONFE", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdvNotas codigonfe;

    public OffNfeCartacorrecao() {
    }

    public OffNfeCartacorrecao(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getXcorrecao() {
        return xcorrecao;
    }

    public void setXcorrecao(String xcorrecao) {
        this.xcorrecao = xcorrecao;
    }

    public Date getDatahoraevento() {
        return datahoraevento;
    }

    public void setDatahoraevento(Date datahoraevento) {
        this.datahoraevento = datahoraevento;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public Date getDatarec() {
        return datarec;
    }

    public void setDatarec(Date datarec) {
        this.datarec = datarec;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OffNfeCartacorrecao)) {
            return false;
        }
        OffNfeCartacorrecao other = (OffNfeCartacorrecao) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.java.com.fincatto.documentofiscal.OffNfeCartacorrecao[ id=" + id + " ]";
    }
    
}
