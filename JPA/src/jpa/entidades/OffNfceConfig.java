/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entidades;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "OFF_NFCE_CONFIG", schema = "DINNAMUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OffNfceConfig.findAll", query = "SELECT o FROM OffNfceConfig o"),
    @NamedQuery(name = "OffNfceConfig.findById", query = "SELECT o FROM OffNfceConfig o WHERE o.id = :id"),
    @NamedQuery(name = "OffNfceConfig.findByLoja", query = "SELECT o FROM OffNfceConfig o WHERE o.loja = :loja"),
    @NamedQuery(name = "OffNfceConfig.findByCertificadoNome", query = "SELECT o FROM OffNfceConfig o WHERE o.certificadoNome = :certificadoNome"),
    @NamedQuery(name = "OffNfceConfig.findByCertificadoCadeiaNome", query = "SELECT o FROM OffNfceConfig o WHERE o.certificadoCadeiaNome = :certificadoCadeiaNome"),
    @NamedQuery(name = "OffNfceConfig.findByCertificadoSenha", query = "SELECT o FROM OffNfceConfig o WHERE o.certificadoSenha = :certificadoSenha"),
    @NamedQuery(name = "OffNfceConfig.findByModelo", query = "SELECT o FROM OffNfceConfig o WHERE o.modelo = :modelo"),
    @NamedQuery(name = "OffNfceConfig.findByNaturezaoperacao", query = "SELECT o FROM OffNfceConfig o WHERE o.naturezaoperacao = :naturezaoperacao"),
    @NamedQuery(name = "OffNfceConfig.findByAtiva", query = "SELECT o FROM OffNfceConfig o WHERE o.ativa = :ativa"),
    @NamedQuery(name = "OffNfceConfig.findByValidada", query = "SELECT o FROM OffNfceConfig o WHERE o.validada = :validada"),
    @NamedQuery(name = "OffNfceConfig.findByCsc", query = "SELECT o FROM OffNfceConfig o WHERE o.csc = :csc"),
    @NamedQuery(name = "OffNfceConfig.findByIdtoken", query = "SELECT o FROM OffNfceConfig o WHERE o.idtoken = :idtoken"),
    @NamedQuery(name = "OffNfceConfig.findByUltimamodificacao", query = "SELECT o FROM OffNfceConfig o WHERE o.ultimamodificacao = :ultimamodificacao"),
    @NamedQuery(name = "OffNfceConfig.findByUrlQrcodeHomologacao", query = "SELECT o FROM OffNfceConfig o WHERE o.urlQrcodeHomologacao = :urlQrcodeHomologacao"),
    @NamedQuery(name = "OffNfceConfig.findByUrlQrcodeProducao", query = "SELECT o FROM OffNfceConfig o WHERE o.urlQrcodeProducao = :urlQrcodeProducao"),
    @NamedQuery(name = "OffNfceConfig.findByUrlConsultaProducao", query = "SELECT o FROM OffNfceConfig o WHERE o.urlConsultaProducao = :urlConsultaProducao"),
    @NamedQuery(name = "OffNfceConfig.findByUrlConsultaHomologacao", query = "SELECT o FROM OffNfceConfig o WHERE o.urlConsultaHomologacao = :urlConsultaHomologacao"),
    @NamedQuery(name = "OffNfceConfig.findByDanfeireport", query = "SELECT o FROM OffNfceConfig o WHERE o.danfeireport = :danfeireport")})
public class OffNfceConfig implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "LOJA")
    private Integer loja;
    @Lob
    @Column(name = "CERTIFICADO")
    private byte[] certificado;
    @Column(name = "CERTIFICADO_NOME")
    private String certificadoNome;
    @Lob
    @Column(name = "CERTIFICADO_CADEIA")
    private byte[] certificadoCadeia;
    @Column(name = "CERTIFICADO_CADEIA_NOME")
    private String certificadoCadeiaNome;
    @Column(name = "CERTIFICADO_SENHA")
    private String certificadoSenha;
    @Column(name = "MODELO")
    private String modelo;
    @Column(name = "NATUREZAOPERACAO")
    private String naturezaoperacao;
    @Column(name = "ATIVA")
    private Short ativa;
    @Column(name = "VALIDADA")
    private Short validada;
    @Column(name = "CSC")
    private String csc;
    @Column(name = "IDTOKEN")
    private String idtoken;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimamodificacao;
    @Column(name = "URL_QRCODE_HOMOLOGACAO")
    private String urlQrcodeHomologacao;
    @Column(name = "URL_QRCODE_PRODUCAO")
    private String urlQrcodeProducao;
    @Column(name = "URL_CONSULTA_PRODUCAO")
    private String urlConsultaProducao;
    @Column(name = "URL_CONSULTA_HOMOLOGACAO")
    private String urlConsultaHomologacao;
    @Column(name = "DANFEIREPORT")
    private Short danfeireport;
    @OneToMany(mappedBy = "idNfceConfig")
    private Collection<OffNfcePdv> offNfcePdvCollection;

    public OffNfceConfig() {
    }

    public OffNfceConfig(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    public byte[] getCertificado() {
        return certificado;
    }

    public void setCertificado( byte[] certificado) {
        this.certificado = certificado;
    }

    public String getCertificadoNome() {
        return certificadoNome;
    }

    public void setCertificadoNome(String certificadoNome) {
        this.certificadoNome = certificadoNome;
    }

    public  byte[] getCertificadoCadeia() {
        return certificadoCadeia;
    }

    public void setCertificadoCadeia( byte[] certificadoCadeia) {
        this.certificadoCadeia = certificadoCadeia;
    }

    public String getCertificadoCadeiaNome() {
        return certificadoCadeiaNome;
    }

    public void setCertificadoCadeiaNome(String certificadoCadeiaNome) {
        this.certificadoCadeiaNome = certificadoCadeiaNome;
    }

    public String getCertificadoSenha() {
        return certificadoSenha;
    }

    public void setCertificadoSenha(String certificadoSenha) {
        this.certificadoSenha = certificadoSenha;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNaturezaoperacao() {
        return naturezaoperacao;
    }

    public void setNaturezaoperacao(String naturezaoperacao) {
        this.naturezaoperacao = naturezaoperacao;
    }

    public Short getAtiva() {
        return ativa;
    }

    public void setAtiva(Short ativa) {
        this.ativa = ativa;
    }

    public Short getValidada() {
        return validada;
    }

    public void setValidada(Short validada) {
        this.validada = validada;
    }

    public String getCsc() {
        return csc;
    }

    public void setCsc(String csc) {
        this.csc = csc;
    }

    public String getIdtoken() {
        return idtoken;
    }

    public void setIdtoken(String idtoken) {
        this.idtoken = idtoken;
    }

    public Date getUltimamodificacao() {
        return ultimamodificacao;
    }

    public void setUltimamodificacao(Date ultimamodificacao) {
        this.ultimamodificacao = ultimamodificacao;
    }

    public String getUrlQrcodeHomologacao() {
        return urlQrcodeHomologacao;
    }

    public void setUrlQrcodeHomologacao(String urlQrcodeHomologacao) {
        this.urlQrcodeHomologacao = urlQrcodeHomologacao;
    }

    public String getUrlQrcodeProducao() {
        return urlQrcodeProducao;
    }

    public void setUrlQrcodeProducao(String urlQrcodeProducao) {
        this.urlQrcodeProducao = urlQrcodeProducao;
    }

    public String getUrlConsultaProducao() {
        return urlConsultaProducao;
    }

    public void setUrlConsultaProducao(String urlConsultaProducao) {
        this.urlConsultaProducao = urlConsultaProducao;
    }

    public String getUrlConsultaHomologacao() {
        return urlConsultaHomologacao;
    }

    public void setUrlConsultaHomologacao(String urlConsultaHomologacao) {
        this.urlConsultaHomologacao = urlConsultaHomologacao;
    }

    public Short getDanfeireport() {
        return danfeireport;
    }

    public void setDanfeireport(Short danfeireport) {
        this.danfeireport = danfeireport;
    }

    @XmlTransient
    public Collection<OffNfcePdv> getOffNfcePdvCollection() {
        return offNfcePdvCollection;
    }

    public void setOffNfcePdvCollection(Collection<OffNfcePdv> offNfcePdvCollection) {
        this.offNfcePdvCollection = offNfcePdvCollection;
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
        if (!(object instanceof OffNfceConfig)) {
            return false;
        }
        OffNfceConfig other = (OffNfceConfig) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entidades.OffNfceConfig[ id=" + id + " ]";
    }
    
}
