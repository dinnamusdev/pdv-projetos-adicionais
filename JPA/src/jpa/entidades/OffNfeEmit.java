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
@Table(name = "OFF_NFE_EMIT",schema = "DINNAMUS")
@XmlRootElement
public class OffNfeEmit implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGOEMIT")
    private Long codigoemit;
    @Column(name = "CNPJ")
    private String cnpj;
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
    @Column(name = "REGIMETRIBUTARIO")
    private Integer regimetributario;
    @Column(name = "ENDERECO")
    private String endereco;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "COMPLEMENTO")
    private String complemento;
    @Column(name = "BAIRRO")
    private String bairro;
    @Column(name = "CEP")
    private String cep;
    @Column(name = "CODIGOPAIS")
    private Integer codigopais;
    @Column(name = "NOMEPAIS")
    private String nomepais;
    @Column(name = "CODIGOMUNICIPIO")
    private Integer codigomunicipio;
    @Column(name = "FONE")
    private String fone;
    @Column(name = "UF")
    private String uf;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.DATE)
    private Date ultimamodificacao;
    @JoinColumn(name = "CODIGONFE", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdvNotas codigonfe;

    public OffNfeEmit() {
    }

    public OffNfeEmit(Long codigoemit) {
        this.codigoemit = codigoemit;
    }

    public Long getCodigoemit() {
        return codigoemit;
    }

    public void setCodigoemit(Long codigoemit) {
        this.codigoemit = codigoemit;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    public Integer getRegimetributario() {
        return regimetributario;
    }

    public void setRegimetributario(Integer regimetributario) {
        this.regimetributario = regimetributario;
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

    public Integer getCodigopais() {
        return codigopais;
    }

    public void setCodigopais(Integer codigopais) {
        this.codigopais = codigopais;
    }

    public String getNomepais() {
        return nomepais;
    }

    public void setNomepais(String nomepais) {
        this.nomepais = nomepais;
    }

    public Integer getCodigomunicipio() {
        return codigomunicipio;
    }

    public void setCodigomunicipio(Integer codigomunicipio) {
        this.codigomunicipio = codigomunicipio;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
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
        hash += (codigoemit != null ? codigoemit.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OffNfeEmit)) {
            return false;
        }
        OffNfeEmit other = (OffNfeEmit) object;
        if ((this.codigoemit == null && other.codigoemit != null) || (this.codigoemit != null && !this.codigoemit.equals(other.codigoemit))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.java.com.fincatto.documentofiscal.OffNfeEmit[ codigoemit=" + codigoemit + " ]";
    }
    
}
