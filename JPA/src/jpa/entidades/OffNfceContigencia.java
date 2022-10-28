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
@Table(name = "OFF_NFCE_CONTIGENCIA",schema = "DINNAMUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OffNfceContigencia.findAll", query = "SELECT o FROM OffNfceContigencia o"),
    @NamedQuery(name = "OffNfceContigencia.findById", query = "SELECT o FROM OffNfceContigencia o WHERE o.id = :id"),
    @NamedQuery(name = "OffNfceContigencia.findByPdv", query = "SELECT o FROM OffNfceContigencia o WHERE o.pdv = :pdv"),
    @NamedQuery(name = "OffNfceContigencia.findByEntrada", query = "SELECT o FROM OffNfceContigencia o WHERE o.entrada = :entrada"),
    @NamedQuery(name = "OffNfceContigencia.findBySaida", query = "SELECT o FROM OffNfceContigencia o WHERE o.saida = :saida"),
    @NamedQuery(name = "OffNfceContigencia.findByTipodeentrada", query = "SELECT o FROM OffNfceContigencia o WHERE o.tipodeentrada = :tipodeentrada"),
    @NamedQuery(name = "OffNfceContigencia.findByTipodesaida", query = "SELECT o FROM OffNfceContigencia o WHERE o.tipodesaida = :tipodesaida"),
    @NamedQuery(name = "OffNfceContigencia.findByUltimamodificacao", query = "SELECT o FROM OffNfceContigencia o WHERE o.ultimamodificacao = :ultimamodificacao")})
public class OffNfceContigencia implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PDV")
    private Integer pdv;
    @Column(name = "ENTRADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date entrada;
    @Column(name = "SAIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saida;
    @Column(name = "TIPODEENTRADA")
    private String tipodeentrada;
    @Column(name = "TIPODESAIDA")
    private String tipodesaida;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimamodificacao;

    public OffNfceContigencia() {
    }

    public OffNfceContigencia(Integer id) {
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

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public String getTipodeentrada() {
        return tipodeentrada;
    }

    public void setTipodeentrada(String tipodeentrada) {
        this.tipodeentrada = tipodeentrada;
    }

    public String getTipodesaida() {
        return tipodesaida;
    }

    public void setTipodesaida(String tipodesaida) {
        this.tipodesaida = tipodesaida;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OffNfceContigencia)) {
            return false;
        }
        OffNfceContigencia other = (OffNfceContigencia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entidades.OffNfceContigencia[ id=" + id + " ]";
    }
    
}
