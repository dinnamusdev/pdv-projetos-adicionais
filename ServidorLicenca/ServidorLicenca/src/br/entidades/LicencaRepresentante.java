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
@Table(name = "LICENCA_REPRESENTANTE")
@NamedQueries({
    @NamedQuery(name = "LicencaRepresentante.findAll", query = "SELECT l FROM LicencaRepresentante l")})
public class LicencaRepresentante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDREPRESENTANTE")
    private Integer idrepresentante;
    @Column(name = "CNPJ")
    private String cnpj;
    @Column(name = "RG")
    private String rg;
    @Column(name = "RAZAO")
    private String razao;
    @Column(name = "ENDERECO")
    private String endereco;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "BAIRRO")
    private String bairro;
    @Column(name = "MUNICIPIO")
    private String municipio;
    @Column(name = "CODMUNICIPIO")
    private String codmunicipio;
    @Column(name = "CEP")
    private String cep;
    @Column(name = "UF")
    private String uf;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "COMISSAO")
    private BigDecimal comissao;
    @Column(name = "OBS")
    private String obs;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "DTCADASTRO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtcadastro;    
    @Column(name = "STATUS")
    private String status;

    public LicencaRepresentante() {
    }

    public LicencaRepresentante(Integer idrepresentante) {
        this.idrepresentante = idrepresentante;
    }

    public Integer getIdrepresentante() {
        return idrepresentante;
    }

    public void setIdrepresentante(Integer idrepresentante) {
        this.idrepresentante = idrepresentante;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getCodmunicipio() {
        return codmunicipio;
    }

    public void setCodmunicipio(String codmunicipio) {
        this.codmunicipio = codmunicipio;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public BigDecimal getComissao() {
        return comissao;
    }

    public void setComissao(BigDecimal comissao) {
        this.comissao = comissao;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDtcadastro() {
        return dtcadastro;
    }

    public void setDtcadastro(Date dtcadastro) {
        this.dtcadastro = dtcadastro;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrepresentante != null ? idrepresentante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicencaRepresentante)) {
            return false;
        }
        LicencaRepresentante other = (LicencaRepresentante) object;
        if ((this.idrepresentante == null && other.idrepresentante != null) || (this.idrepresentante != null && !this.idrepresentante.equals(other.idrepresentante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.entidades.LicencaRepresentante[ idrepresentante=" + idrepresentante + " ]";
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
    
}
