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
@Table(name = "CEP", schema = "DINNAMUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cep.findAll", query = "SELECT c FROM Cep c"),
    @NamedQuery(name = "Cep.findByCep", query = "SELECT c FROM Cep c WHERE c.cep = :cep"),
    @NamedQuery(name = "Cep.findByCodmunicipio", query = "SELECT c FROM Cep c WHERE c.codmunicipio = :codmunicipio"),
    @NamedQuery(name = "Cep.findByUf", query = "SELECT c FROM Cep c WHERE c.uf = :uf"),
    @NamedQuery(name = "Cep.findByLogradouro", query = "SELECT c FROM Cep c WHERE c.logradouro = :logradouro"),
    @NamedQuery(name = "Cep.findByBairro", query = "SELECT c FROM Cep c WHERE c.bairro = :bairro"),
    @NamedQuery(name = "Cep.findByUltimamodificacao", query = "SELECT c FROM Cep c WHERE c.ultimamodificacao = :ultimamodificacao")})
public class Cep implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CEP")
    private String cep;
    @Column(name = "CODMUNICIPIO")
    private String codmunicipio;
    @Column(name = "UF")
    private String uf;
    @Column(name = "LOGRADOURO")
    private String logradouro;
    @Column(name = "BAIRRO")
    private String bairro;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.DATE)
    private Date ultimamodificacao;

    public Cep() {
    }

    public Cep(String cep) {
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCodmunicipio() {
        return codmunicipio;
    }

    public void setCodmunicipio(String codmunicipio) {
        this.codmunicipio = codmunicipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
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
        hash += (cep != null ? cep.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cep)) {
            return false;
        }
        Cep other = (Cep) object;
        if ((this.cep == null && other.cep != null) || (this.cep != null && !this.cep.equals(other.cep))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entidades.Cep[ cep=" + cep + " ]";
    }
    
}
