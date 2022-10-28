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
@Table(name = "LICENCA_DADOS_CONTRATO_FINANCEIRO")
@NamedQueries({
    @NamedQuery(name = "LicencaDadosContratoFinanceiro.findAll", query = "SELECT l FROM LicencaDadosContratoFinanceiro l")})
public class LicencaDadosContratoFinanceiro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDFINANCEIRO")
    private Integer idfinanceiro;
    @Column(name = "IDCONTRATO")
    private Integer idcontrato;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR")
    private BigDecimal valor;
    @Column(name = "EMISSAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emissao;
    @Column(name = "VENCIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vencimento;
    @Column(name = "PAGTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pagto;
    @Column(name = "VALORPAGO")
    private BigDecimal valorpago;
    @Column(name = "MEIOCOBRANCA")
    private String meiocobranca;

    public LicencaDadosContratoFinanceiro() {
    }

    public LicencaDadosContratoFinanceiro(Integer idfinanceiro) {
        this.idfinanceiro = idfinanceiro;
    }

    public Integer getIdfinanceiro() {
        return idfinanceiro;
    }

    public void setIdfinanceiro(Integer idfinanceiro) {
        this.idfinanceiro = idfinanceiro;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public Date getVencimento() {
        return vencimento;
    }

    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    public Date getPagto() {
        return pagto;
    }

    public void setPagto(Date pagto) {
        this.pagto = pagto;
    }

    public BigDecimal getValorpago() {
        return valorpago;
    }

    public void setValorpago(BigDecimal valorpago) {
        this.valorpago = valorpago;
    }

    public String getMeiocobranca() {
        return meiocobranca;
    }

    public void setMeiocobranca(String meiocobranca) {
        this.meiocobranca = meiocobranca;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idfinanceiro != null ? idfinanceiro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicencaDadosContratoFinanceiro)) {
            return false;
        }
        LicencaDadosContratoFinanceiro other = (LicencaDadosContratoFinanceiro) object;
        if ((this.idfinanceiro == null && other.idfinanceiro != null) || (this.idfinanceiro != null && !this.idfinanceiro.equals(other.idfinanceiro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.entidades.LicencaDadosContratoFinanceiro[ idfinanceiro=" + idfinanceiro + " ]";
    }
    
}
