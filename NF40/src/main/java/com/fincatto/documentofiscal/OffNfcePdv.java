/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.fincatto.documentofiscal;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "OFF_NFCE_PDV")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OffNfcePdv.findAll", query = "SELECT o FROM OffNfcePdv o"),
    @NamedQuery(name = "OffNfcePdv.findById", query = "SELECT o FROM OffNfcePdv o WHERE o.id = :id"),
    @NamedQuery(name = "OffNfcePdv.findByPdv", query = "SELECT o FROM OffNfcePdv o WHERE o.pdv = :pdv"),
    @NamedQuery(name = "OffNfcePdv.findByAmbiente", query = "SELECT o FROM OffNfcePdv o WHERE o.ambiente = :ambiente"),
    @NamedQuery(name = "OffNfcePdv.findBySerie", query = "SELECT o FROM OffNfcePdv o WHERE o.serie = :serie"),
    @NamedQuery(name = "OffNfcePdv.findByNumero", query = "SELECT o FROM OffNfcePdv o WHERE o.numero = :numero"),
    @NamedQuery(name = "OffNfcePdv.findByVersao", query = "SELECT o FROM OffNfcePdv o WHERE o.versao = :versao"),
    @NamedQuery(name = "OffNfcePdv.findByContigencia", query = "SELECT o FROM OffNfcePdv o WHERE o.contigencia = :contigencia"),
    @NamedQuery(name = "OffNfcePdv.findByAtivada", query = "SELECT o FROM OffNfcePdv o WHERE o.ativada = :ativada"),
    @NamedQuery(name = "OffNfcePdv.findByUltimamodificacao", query = "SELECT o FROM OffNfcePdv o WHERE o.ultimamodificacao = :ultimamodificacao"),
    @NamedQuery(name = "OffNfcePdv.findByModelo", query = "SELECT o FROM OffNfcePdv o WHERE o.modelo = :modelo")})
public class OffNfcePdv implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PDV")
    private Integer pdv;
    @Column(name = "AMBIENTE")
    private Integer ambiente;
    @Column(name = "SERIE")
    private Integer serie;
    @Column(name = "NUMERO")
    private Integer numero;
    @Column(name = "VERSAO")
    private String versao;
    @Column(name = "CONTIGENCIA")
    private Short contigencia;
    @Column(name = "ATIVADA")
    private Short ativada;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimamodificacao;
    @Column(name = "MODELO")
    private Integer modelo;
    @OneToMany(mappedBy = "idNfcePdv")
    private Collection<OffNfcePdvNotas> offNfcePdvNotasCollection;
    @JoinColumn(name = "ID_NFCE_CONFIG", referencedColumnName = "ID")
    @ManyToOne
    private OffNfceConfig idNfceConfig;

    public OffNfcePdv() {
    }

    public OffNfcePdv(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPdv() {
        return pdv;
    }

    public void setPdv(Integer pdv) {
        this.pdv = pdv;
    }

    public Integer getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Integer ambiente) {
        this.ambiente = ambiente;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public Short getContigencia() {
        return contigencia;
    }

    public void setContigencia(Short contigencia) {
        this.contigencia = contigencia;
    }

    public Short getAtivada() {
        return ativada;
    }

    public void setAtivada(Short ativada) {
        this.ativada = ativada;
    }

    public Date getUltimamodificacao() {
        return ultimamodificacao;
    }

    public void setUltimamodificacao(Date ultimamodificacao) {
        this.ultimamodificacao = ultimamodificacao;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    @XmlTransient
    public Collection<OffNfcePdvNotas> getOffNfcePdvNotasCollection() {
        return offNfcePdvNotasCollection;
    }

    public void setOffNfcePdvNotasCollection(Collection<OffNfcePdvNotas> offNfcePdvNotasCollection) {
        this.offNfcePdvNotasCollection = offNfcePdvNotasCollection;
    }

    public OffNfceConfig getIdNfceConfig() {
        return idNfceConfig;
    }

    public void setIdNfceConfig(OffNfceConfig idNfceConfig) {
        this.idNfceConfig = idNfceConfig;
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
        if (!(object instanceof OffNfcePdv)) {
            return false;
        }
        OffNfcePdv other = (OffNfcePdv) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.java.com.fincatto.documentofiscal.OffNfcePdv[ id=" + id + " ]";
    }
    
}
