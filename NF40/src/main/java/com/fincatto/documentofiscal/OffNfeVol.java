/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.fincatto.documentofiscal;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "OFF_NFE_VOL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OffNfeVol.findAll", query = "SELECT o FROM OffNfeVol o"),
    @NamedQuery(name = "OffNfeVol.findByCodigonfeVol", query = "SELECT o FROM OffNfeVol o WHERE o.codigonfeVol = :codigonfeVol"),
    @NamedQuery(name = "OffNfeVol.findByQuantidade", query = "SELECT o FROM OffNfeVol o WHERE o.quantidade = :quantidade"),
    @NamedQuery(name = "OffNfeVol.findByEspecie", query = "SELECT o FROM OffNfeVol o WHERE o.especie = :especie"),
    @NamedQuery(name = "OffNfeVol.findByMarca", query = "SELECT o FROM OffNfeVol o WHERE o.marca = :marca"),
    @NamedQuery(name = "OffNfeVol.findByNumeracao", query = "SELECT o FROM OffNfeVol o WHERE o.numeracao = :numeracao"),
    @NamedQuery(name = "OffNfeVol.findByPesoliq", query = "SELECT o FROM OffNfeVol o WHERE o.pesoliq = :pesoliq"),
    @NamedQuery(name = "OffNfeVol.findByPesobruto", query = "SELECT o FROM OffNfeVol o WHERE o.pesobruto = :pesobruto"),
    @NamedQuery(name = "OffNfeVol.findByUltimamodificacao", query = "SELECT o FROM OffNfeVol o WHERE o.ultimamodificacao = :ultimamodificacao")})
public class OffNfeVol implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGONFE_VOL")
    private Long codigonfeVol;
    @Column(name = "QUANTIDADE")
    private Integer quantidade;
    @Column(name = "ESPECIE")
    private String especie;
    @Column(name = "MARCA")
    private String marca;
    @Column(name = "NUMERACAO")
    private String numeracao;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PESOLIQ")
    private BigDecimal pesoliq;
    @Column(name = "PESOBRUTO")
    private BigDecimal pesobruto;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.DATE)
    private Date ultimamodificacao;
    @JoinColumn(name = "CODIGONFE", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdvNotas codigonfe;

    public OffNfeVol() {
    }

    public OffNfeVol(Long codigonfeVol) {
        this.codigonfeVol = codigonfeVol;
    }

    public Long getCodigonfeVol() {
        return codigonfeVol;
    }

    public void setCodigonfeVol(Long codigonfeVol) {
        this.codigonfeVol = codigonfeVol;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNumeracao() {
        return numeracao;
    }

    public void setNumeracao(String numeracao) {
        this.numeracao = numeracao;
    }

    public BigDecimal getPesoliq() {
        return pesoliq;
    }

    public void setPesoliq(BigDecimal pesoliq) {
        this.pesoliq = pesoliq;
    }

    public BigDecimal getPesobruto() {
        return pesobruto;
    }

    public void setPesobruto(BigDecimal pesobruto) {
        this.pesobruto = pesobruto;
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
        hash += (codigonfeVol != null ? codigonfeVol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OffNfeVol)) {
            return false;
        }
        OffNfeVol other = (OffNfeVol) object;
        if ((this.codigonfeVol == null && other.codigonfeVol != null) || (this.codigonfeVol != null && !this.codigonfeVol.equals(other.codigonfeVol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.java.com.fincatto.documentofiscal.OffNfeVol[ codigonfeVol=" + codigonfeVol + " ]";
    }
    
}
