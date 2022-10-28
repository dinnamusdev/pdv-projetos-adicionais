/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "OFF_NFCE_INUTILIZAR",schema = "DINNAMUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OffNfceInutilizar.findAll", query = "SELECT o FROM OffNfceInutilizar o"),
    @NamedQuery(name = "OffNfceInutilizar.findById", query = "SELECT o FROM OffNfceInutilizar o WHERE o.id = :id"),
    @NamedQuery(name = "OffNfceInutilizar.findByPdv", query = "SELECT o FROM OffNfceInutilizar o WHERE o.pdv = :pdv"),
    @NamedQuery(name = "OffNfceInutilizar.findByAno", query = "SELECT o FROM OffNfceInutilizar o WHERE o.ano = :ano"),
    @NamedQuery(name = "OffNfceInutilizar.findByCnpj", query = "SELECT o FROM OffNfceInutilizar o WHERE o.cnpj = :cnpj"),
    @NamedQuery(name = "OffNfceInutilizar.findBySerie", query = "SELECT o FROM OffNfceInutilizar o WHERE o.serie = :serie"),
    @NamedQuery(name = "OffNfceInutilizar.findByNumeroinicial", query = "SELECT o FROM OffNfceInutilizar o WHERE o.numeroinicial = :numeroinicial"),
    @NamedQuery(name = "OffNfceInutilizar.findByNumerofinal", query = "SELECT o FROM OffNfceInutilizar o WHERE o.numerofinal = :numerofinal"),
    @NamedQuery(name = "OffNfceInutilizar.findByJustificativa", query = "SELECT o FROM OffNfceInutilizar o WHERE o.justificativa = :justificativa"),
    @NamedQuery(name = "OffNfceInutilizar.findByIdOffPdvNota", query = "SELECT o FROM OffNfceInutilizar o WHERE o.idOffPdvNota = :idOffPdvNota"),
    @NamedQuery(name = "OffNfceInutilizar.findByUltimamodificacao", query = "SELECT o FROM OffNfceInutilizar o WHERE o.ultimamodificacao = :ultimamodificacao")})
public class OffNfceInutilizar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "PDV")
    private Integer pdv;
    @Column(name = "ANO")
    private String ano;
    @Column(name = "CNPJ")
    private String cnpj;
    @Column(name = "SERIE")
    private String serie;
    @Column(name = "NUMEROINICIAL")
    private String numeroinicial;
    @Column(name = "NUMEROFINAL")
    private String numerofinal;
    @Column(name = "JUSTIFICATIVA")
    private String justificativa;
    @Column(name = "ID_OFF_PDV_NOTA")
    private BigInteger idOffPdvNota;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimamodificacao;

    public OffNfceInutilizar() {
    }

    public OffNfceInutilizar(Integer id) {
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

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getNumeroinicial() {
        return numeroinicial;
    }

    public void setNumeroinicial(String numeroinicial) {
        this.numeroinicial = numeroinicial;
    }

    public String getNumerofinal() {
        return numerofinal;
    }

    public void setNumerofinal(String numerofinal) {
        this.numerofinal = numerofinal;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public BigInteger getIdOffPdvNota() {
        return idOffPdvNota;
    }

    public void setIdOffPdvNota(BigInteger idOffPdvNota) {
        this.idOffPdvNota = idOffPdvNota;
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
        if (!(object instanceof OffNfceInutilizar)) {
            return false;
        }
        OffNfceInutilizar other = (OffNfceInutilizar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entidades.OffNfceInutilizar[ id=" + id + " ]";
    }
    
}
