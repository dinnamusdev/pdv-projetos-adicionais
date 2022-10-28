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
@Table(name = "CEP_MUNICIPIO", schema = "DINNAMUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CepMunicipio.findAll", query = "SELECT c FROM CepMunicipio c"),
    @NamedQuery(name = "CepMunicipio.findByCodigo", query = "SELECT c FROM CepMunicipio c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CepMunicipio.findByMunicipio", query = "SELECT c FROM CepMunicipio c WHERE c.municipio = :municipio"),
    @NamedQuery(name = "CepMunicipio.findByUltimamodificacao", query = "SELECT c FROM CepMunicipio c WHERE c.ultimamodificacao = :ultimamodificacao")})
public class CepMunicipio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "MUNICIPIO")
    private String municipio;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.DATE)
    private Date ultimamodificacao;
    @JoinColumn(name = "UF", referencedColumnName = "CODIGO")
    @ManyToOne
    private CepUf uf;
    
    public CepMunicipio() {
    }

    public CepMunicipio(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public Date getUltimamodificacao() {
        return ultimamodificacao;
    }

    public void setUltimamodificacao(Date ultimamodificacao) {
        this.ultimamodificacao = ultimamodificacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigo != null ? codigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CepMunicipio)) {
            return false;
        }
        CepMunicipio other = (CepMunicipio) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entidades.CepMunicipio[ codigo=" + codigo + " ]";
    }

    /**
     * @return the uf
     */
    public CepUf getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(CepUf uf) {
        this.uf = uf;
    }

     

     
    
}
