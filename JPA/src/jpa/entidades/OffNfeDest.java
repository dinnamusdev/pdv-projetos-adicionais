/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entidades;

import com.fincatto.nfe310.validadores.StringValidador;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "OFF_NFE_DEST",schema = "DINNAMUS")
@XmlRootElement

public class OffNfeDest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGONFE_DEST")
    private Long codigonfeDest;
    @Column(name = "TIPODOCID")
    private String tipodocid;
    @Column(name = "IDENTIFICACAO")
    private String identificacao;
    @Column(name = "RAZAOSOCIAL")
    private String razaosocial;
    @Column(name = "NOMEFANTASIA")
    private String nomefantasia;
    @Column(name = "IE")
    private String ie;
    @Column(name = "IEST")
    private String iest;
    @Column(name = "IM")
    private String im;
    @Column(name = "CNAE")
    private String cnae;
    @Column(name = "ISUFRAMA")
    private String isuframa;
    @Column(name = "LOGRADOURO")
    private String logradouro;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "COMPLEMENTO")
    private String complemento;
    @Column(name = "BAIRRO")
    private String bairro;
    @Column(name = "CEP")
    private String cep;
    @Column(name = "PAIS")
    private Integer pais;
    @Column(name = "UF")
    private String uf;
    @Column(name = "MUNICIPIO")
    private Integer municipio;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "ISENTOICMS")
    private Short isentoicms;
    @Column(name = "FONE")
    private String fone;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.DATE)
    private Date ultimamodificacao;
    @JoinColumn(name = "CODIGONFE", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdvNotas codigonfe;

    public OffNfeDest() {
    }

    public OffNfeDest(Long codigonfeDest) {
        this.codigonfeDest = codigonfeDest;
    }

    public Long getCodigonfeDest() {
        return codigonfeDest;
    }

    public void setCodigonfeDest(Long codigonfeDest) {
        this.codigonfeDest = codigonfeDest;
    }

    public String getTipodocid() {
        return tipodocid;
    }

    public void setTipodocid(String tipodocid) {
        this.tipodocid = tipodocid;
    }

    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
       
        this.identificacao = identificacao;
    }

    public String getRazaosocial() {
        
        return razaosocial;
    }

    public void setRazaosocial(String razaosocial) {
         
        this.razaosocial = razaosocial;
    }

    public String getNomefantasia() {
        return nomefantasia;
    }

    public void setNomefantasia(String nomefantasia) {
        this.nomefantasia = nomefantasia;
    }

    public String getIe() {
        return ie;
    }

    public void setIe(String ie) {
        this.ie = ie;
    }

    public String getIest() {
        return iest;
    }

    public void setIest(String iest) {
        this.iest = iest;
    }

    public String getIm() {
        return im;
    }

    public void setIm(String im) {
        this.im = im;
    }

    public String getCnae() {
        return cnae;
    }

    public void setCnae(String cnae) {
        this.cnae = cnae;
    }

    public String getIsuframa() {
        return isuframa;
    }

    public void setIsuframa(String isuframa) {
        this.isuframa = isuframa;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getPais() {
        return pais;
    }

    public void setPais(Integer pais) {
        this.pais = pais;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Integer getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Integer municipio) {
        this.municipio = municipio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Short getIsentoicms() {
        return isentoicms;
    }

    public void setIsentoicms(Short isentoicms) {
        this.isentoicms = isentoicms;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public Date getUltimamodificacao() {
        return ultimamodificacao;
    }

    public void setUltimamodificacao(Date ultimamodificacao) {
        this.ultimamodificacao = ultimamodificacao;
    }

    public OffNfcePdvNotas getCodigonfe() {
        return codigonfe;
    }

    public void setCodigonfe(OffNfcePdvNotas codigonfe) {
        this.codigonfe = codigonfe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigonfeDest != null ? codigonfeDest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OffNfeDest)) {
            return false;
        }
        OffNfeDest other = (OffNfeDest) object;
        if ((this.codigonfeDest == null && other.codigonfeDest != null) || (this.codigonfeDest != null && !this.codigonfeDest.equals(other.codigonfeDest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.java.com.fincatto.documentofiscal.OffNfeDest[ codigonfeDest=" + codigonfeDest + " ]";
    }
    
}
