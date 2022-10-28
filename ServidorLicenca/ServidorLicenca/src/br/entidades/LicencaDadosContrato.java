/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "LICENCA_DADOS_CONTRATO")
@NamedQueries({
    @NamedQuery(name = "LicencaDadosContrato.findAll", query = "SELECT l FROM LicencaDadosContrato l")})
public class LicencaDadosContrato implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDCONTRATO")
    private Integer idcontrato;
    @Column(name = "IDCONTRATANTE")
    private Integer idcontratante;
    @Column(name = "LICRET")
    private Integer licret;
    @Column(name = "LICPDV")
    private Integer licpdv;
    @Column(name = "LICPRE")
    private Integer licpre;
    @Column(name = "INICIOCONTRATO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date iniciocontrato;
    @Column(name = "TERMIMNOCONTRATO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date termimnocontrato;
    @Column(name = "PERIODICIDADELIB")
    private Integer periodicidadelib;
    @Column(name = "PRAZOEXPIRARLIC")
    private Integer prazoexpirarlic;
    @Column(name = "DATAEMISSAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataemissao;
    @Column(name = "IDREPRESENTANTE")
    private Integer idrepresentante;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALORCONTRATO")
    private BigDecimal valorcontrato;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "VENCIMENTO")
    private Integer vencimento;
    @Column(name = "PRIMEIROVENCIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date primeirovencimento;
    @Column(name = "NUMEROPARCELAS")
    private Integer numeroparcelas;
    
    public LicencaDadosContrato() {
    }

    public LicencaDadosContrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public Integer getIdcontratante() {
        return idcontratante;
    }

    public void setIdcontratante(Integer idcontratante) {
        this.idcontratante = idcontratante;
    }

    public Integer getLicret() {
        return licret;
    }

    public void setLicret(Integer licret) {
        this.licret = licret;
    }

    public Integer getLicpdv() {
        return licpdv;
    }

    public void setLicpdv(Integer licpdv) {
        this.licpdv = licpdv;
    }

    public Integer getLicpre() {
        return licpre;
    }

    public void setLicpre(Integer licpre) {
        this.licpre = licpre;
    }

    public Date getIniciocontrato() {
        return iniciocontrato;
    }

    public void setIniciocontrato(Date iniciocontrato) {
        this.iniciocontrato = iniciocontrato;
    }

    public Date getTermimnocontrato() {
        return termimnocontrato;
    }

    public void setTermimnocontrato(Date termimnocontrato) {
        this.termimnocontrato = termimnocontrato;
    }

    public Integer getPeriodicidadelib() {
        return periodicidadelib;
    }

    public void setPeriodicidadelib(Integer periodicidadelib) {
        this.periodicidadelib = periodicidadelib;
    }

    public Integer getPrazoexpirarlic() {
        return prazoexpirarlic;
    }

    public void setPrazoexpirarlic(Integer prazoexpirarlic) {
        this.prazoexpirarlic = prazoexpirarlic;
    }

    public Date getDataemissao() {
        return dataemissao;
    }

    public void setDataemissao(Date dataemissao) {
        this.dataemissao = dataemissao;
    }

    public Integer getIdrepresentante() {
        return idrepresentante;
    }

    public void setIdrepresentante(Integer idrepresentante) {
        this.idrepresentante = idrepresentante;
    }

    public BigDecimal getValorcontrato() {
        return valorcontrato;
    }

    public void setValorcontrato(BigDecimal valorcontrato) {
        this.valorcontrato = valorcontrato;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontrato != null ? idcontrato.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicencaDadosContrato)) {
            return false;
        }
        LicencaDadosContrato other = (LicencaDadosContrato) object;
        if ((this.idcontrato == null && other.idcontrato != null) || (this.idcontrato != null && !this.idcontrato.equals(other.idcontrato))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.entidades.LicencaDadosContrato[ idcontrato=" + idcontrato + " ]";
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the vencimento
     */
    public Integer getVencimento() {
        return vencimento;
    }

    /**
     * @param vencimento the vencimento to set
     */
    public void setVencimento(Integer vencimento) {
        this.vencimento = vencimento;
    }

    /**
     * @return the primeirovencimento
     */
    public Date getPrimeirovencimento() {
        return primeirovencimento;
    }

    /**
     * @param primeirovencimento the primeirovencimento to set
     */
    public void setPrimeirovencimento(Date primeirovencimento) {
        this.primeirovencimento = primeirovencimento;
    }

    /**
     * @return the numeroparcelas
     */
    public Integer getNumeroparcelas() {
        return numeroparcelas;
    }

    /**
     * @param numeroparcelas the numeroparcelas to set
     */
    public void setNumeroparcelas(Integer numeroparcelas) {
        this.numeroparcelas = numeroparcelas;
    }
    
}
