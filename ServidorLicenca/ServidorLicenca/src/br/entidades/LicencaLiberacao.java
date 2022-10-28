/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "LICENCA_LIBERACAO")
@NamedQueries({
    @NamedQuery(name = "LicencaLiberacao.findAll", query = "SELECT l FROM LicencaLiberacao l")})
public class LicencaLiberacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDLIBERACAO")
    private Integer idliberacao;
    @Column(name = "IDCONTRATANTE")
    private Integer idcontratante;
    @Column(name = "IDCONTRATO")
    private Integer idcontrato;
    @Column(name = "DAT1")
    private String dat1;
    @Column(name = "DAT2")
    private String dat2;
    @Column(name = "IDFINANCEIRO")
    private Integer idfinanceiro;

    public LicencaLiberacao() {
    }

    public LicencaLiberacao(Integer idliberacao) {
        this.idliberacao = idliberacao;
    }

    public Integer getIdliberacao() {
        return idliberacao;
    }

    public void setIdliberacao(Integer idliberacao) {
        this.idliberacao = idliberacao;
    }

    public Integer getIdcontratante() {
        return idcontratante;
    }

    public void setIdcontratante(Integer idcontratante) {
        this.idcontratante = idcontratante;
    }

    public Integer getIdcontrato() {
        return idcontrato;
    }

    public void setIdcontrato(Integer idcontrato) {
        this.idcontrato = idcontrato;
    }

    public String getDat1() {
        return dat1;
    }

    public void setDat1(String dat1) {
        this.dat1 = dat1;
    }

    public String getDat2() {
        return dat2;
    }

    public void setDat2(String dat2) {
        this.dat2 = dat2;
    }

    public Integer getIdfinanceiro() {
        return idfinanceiro;
    }

    public void setIdfinanceiro(Integer idfinanceiro) {
        this.idfinanceiro = idfinanceiro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idliberacao != null ? idliberacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicencaLiberacao)) {
            return false;
        }
        LicencaLiberacao other = (LicencaLiberacao) object;
        if ((this.idliberacao == null && other.idliberacao != null) || (this.idliberacao != null && !this.idliberacao.equals(other.idliberacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.entidades.LicencaLiberacao[ idliberacao=" + idliberacao + " ]";
    }
    
}
