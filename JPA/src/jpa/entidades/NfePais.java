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
@Table(name = "NFE_PAIS", schema = "DINNAMUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NfePais.findAll", query = "SELECT n FROM NfePais n"),
    @NamedQuery(name = "NfePais.findByCodigo", query = "SELECT n FROM NfePais n WHERE n.codigo = :codigo"),
    @NamedQuery(name = "NfePais.findByNome", query = "SELECT n FROM NfePais n WHERE n.nome = :nome"),
    @NamedQuery(name = "NfePais.findByUltimamodificacao", query = "SELECT n FROM NfePais n WHERE n.ultimamodificacao = :ultimamodificacao")})
public class NfePais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO")
    private Integer codigo;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.DATE)
    private Date ultimamodificacao;
//    @OneToMany(mappedBy = "pais", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
//    private Collection<CepUf> ufs;

    public NfePais() {
    }

    public NfePais(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
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
        if (!(object instanceof NfePais)) {
            return false;
        }
        NfePais other = (NfePais) object;
        if ((this.codigo == null && other.codigo != null) || (this.codigo != null && !this.codigo.equals(other.codigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entidades.NfePais[ codigo=" + codigo + " ]";
    }

    /**
     * @return the ufs
     */
//    public Collection<CepUf> getUfs() {
//        return ufs;
//    }
//
//    /**
//     * @param ufs the ufs to set
//     */
//    public void setUfs(Collection<CepUf> ufs) {
//        this.ufs = ufs;
//    }
//    
}
