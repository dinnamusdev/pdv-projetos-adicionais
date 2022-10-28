/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.entidades;

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

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "LICENCA_DADOS_CONTRATANTE")
@NamedQueries({
    @NamedQuery(name = "LicencaDadosContratante.findAll", query = "SELECT l FROM LicencaDadosContratante l")})
public class LicencaDadosContratante implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IDCONTRATANTE")
    private Integer idcontratante;
    @Column(name = "CNPJ")
    private String cnpj;
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
    @Column(name = "DATA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date data;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "UF")
    private String uf;
    @Column(name = "NOMEFANTASIA")
    private String nomefantasia;

    public LicencaDadosContratante() {
    }

    public LicencaDadosContratante(Integer idcontratante) {
        this.idcontratante = idcontratante;
    }

    public Integer getIdcontratante() {
        return idcontratante;
    }

    public void setIdcontratante(Integer idcontratante) {
        this.idcontratante = idcontratante;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcontratante != null ? idcontratante.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LicencaDadosContratante)) {
            return false;
        }
        LicencaDadosContratante other = (LicencaDadosContratante) object;
        if ((this.idcontratante == null && other.idcontratante != null) || (this.idcontratante != null && !this.idcontratante.equals(other.idcontratante))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.entidades.LicencaDadosContratante[ idcontratante=" + idcontratante + " ]";
    }

    /**
     * @return the nomefantasia
     */
    public String getNomefantasia() {
        return nomefantasia;
    }

    /**
     * @param nomefantasia the nomefantasia to set
     */
    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }
    
}
