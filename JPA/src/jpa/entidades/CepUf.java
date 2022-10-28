/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "CEP_UF", schema = "DINNAMUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CepUf.findAll", query = "SELECT c FROM CepUf c"),
    @NamedQuery(name = "CepUf.findByCodigo", query = "SELECT c FROM CepUf c WHERE c.codigo = :codigo"),
    @NamedQuery(name = "CepUf.findByNome", query = "SELECT c FROM CepUf c WHERE c.nome = :nome"),
    @NamedQuery(name = "CepUf.findByUltimamodificacao", query = "SELECT c FROM CepUf c WHERE c.ultimamodificacao = :ultimamodificacao")})
public class CepUf implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.DATE)
    private Date ultimamodificacao;
    @OneToMany(mappedBy = "uf", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
    private Collection<Cep> municipios;
    
//    @JoinColumn(name = "PAIS", referencedColumnName = "CODIGO")
//    @ManyToOne
//    private NfePais pais;
    

    public CepUf() {
    }

    public CepUf(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        if (!(object instanceof CepUf)) {
            return false;
        }
        CepUf other = (CepUf) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entidades.CepUf[ codigo=" + codigo + " ]";
    }

    /**
     * @return the municipios
     */
    public Collection<Cep> getMunicipios() {
        return municipios;
    }

    /**
     * @param municipios the municipios to set
     */
    public void setMunicipios(Collection<Cep> municipios) {
        this.municipios = municipios;
    }

    /**
     * @return the pais
     */
//    public NfePais getPais() {
//        return pais;
//    }
//
//    /**
//     * @param pais the pais to set
//     */
//    public void setPais(NfePais pais) {
//        this.pais = pais;
//    }
    
}
