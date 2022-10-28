/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.fincatto.documentofiscal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "OFF_NFCE_PDV_NOTAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OffNfcePdvNotas.findAll", query = "SELECT o FROM OffNfcePdvNotas o"),
    @NamedQuery(name = "OffNfcePdvNotas.findById", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.id = :id"),
    @NamedQuery(name = "OffNfcePdvNotas.findByPdv", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.pdv = :pdv"),
    @NamedQuery(name = "OffNfcePdvNotas.findByAmbiente", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.ambiente = :ambiente"),
    @NamedQuery(name = "OffNfcePdvNotas.findByNumero", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.numero = :numero"),
    @NamedQuery(name = "OffNfcePdvNotas.findBySerie", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.serie = :serie"),
    @NamedQuery(name = "OffNfcePdvNotas.findByChave", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.chave = :chave"),
    @NamedQuery(name = "OffNfcePdvNotas.findByChaveDv", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.chaveDv = :chaveDv"),
    @NamedQuery(name = "OffNfcePdvNotas.findByCodigorandomico", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.codigorandomico = :codigorandomico"),
    @NamedQuery(name = "OffNfcePdvNotas.findByFinalidade", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.finalidade = :finalidade"),
    @NamedQuery(name = "OffNfcePdvNotas.findByTipoemissao", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.tipoemissao = :tipoemissao"),
    @NamedQuery(name = "OffNfcePdvNotas.findByTipoimpressao", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.tipoimpressao = :tipoimpressao"),
    @NamedQuery(name = "OffNfcePdvNotas.findByIndicadordepresenca", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.indicadordepresenca = :indicadordepresenca"),
    @NamedQuery(name = "OffNfcePdvNotas.findByProtocolo", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.protocolo = :protocolo"),
    @NamedQuery(name = "OffNfcePdvNotas.findByDadosqrcode", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.dadosqrcode = :dadosqrcode"),
    @NamedQuery(name = "OffNfcePdvNotas.findByCodigovenda", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.codigovenda = :codigovenda"),
    @NamedQuery(name = "OffNfcePdvNotas.findByNumeroprotocolo", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.numeroprotocolo = :numeroprotocolo"),
    @NamedQuery(name = "OffNfcePdvNotas.findByUltimamodificacao", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.ultimamodificacao = :ultimamodificacao"),
    @NamedQuery(name = "OffNfcePdvNotas.findByEmissao", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.emissao = :emissao"),
    @NamedQuery(name = "OffNfcePdvNotas.findByDatahoraautorizacao", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.datahoraautorizacao = :datahoraautorizacao"),
    @NamedQuery(name = "OffNfcePdvNotas.findByModelo", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.modelo = :modelo"),
    @NamedQuery(name = "OffNfcePdvNotas.findByOperacaorigem", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.operacaorigem = :operacaorigem"),
    @NamedQuery(name = "OffNfcePdvNotas.findByTipooperacaorigem", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.tipooperacaorigem = :tipooperacaorigem"),
    @NamedQuery(name = "OffNfcePdvNotas.findByTiponfe", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.tiponfe = :tiponfe"),
    @NamedQuery(name = "OffNfcePdvNotas.findByOperacaoorigem", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.operacaoorigem = :operacaoorigem"),
    @NamedQuery(name = "OffNfcePdvNotas.findByTipooperacaoorigem", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.tipooperacaoorigem = :tipooperacaoorigem"),
    @NamedQuery(name = "OffNfcePdvNotas.findByVersao", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.versao = :versao"),
    @NamedQuery(name = "OffNfcePdvNotas.findByInfcpl", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.infcpl = :infcpl"),
    @NamedQuery(name = "OffNfcePdvNotas.findByUfembarq", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.ufembarq = :ufembarq"),
    @NamedQuery(name = "OffNfcePdvNotas.findByXlocembarq", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.xlocembarq = :xlocembarq"),
    @NamedQuery(name = "OffNfcePdvNotas.findByVtottrib", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.vtottrib = :vtottrib")})
public class OffNfcePdvNotas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PDV")
    private Integer pdv;
    @Column(name = "AMBIENTE")
    private Integer ambiente;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "SERIE")
    private String serie;
    @Column(name = "CHAVE")
    private String chave;
    @Column(name = "CHAVE_DV")
    private String chaveDv;
    @Column(name = "CODIGORANDOMICO")
    private String codigorandomico;
    @Column(name = "FINALIDADE")
    private Integer finalidade;
    @Column(name = "TIPOEMISSAO")
    private Integer tipoemissao;
    @Column(name = "TIPOIMPRESSAO")
    private Integer tipoimpressao;
    @Column(name = "INDICADORDEPRESENCA")
    private Integer indicadordepresenca;
    @Column(name = "PROTOCOLO")
    private String protocolo;
    @Column(name = "DADOSQRCODE")
    private String dadosqrcode;
    @Column(name = "CODIGOVENDA")
    private BigInteger codigovenda;
    @Column(name = "NUMEROPROTOCOLO")
    private String numeroprotocolo;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimamodificacao;
    @Column(name = "EMISSAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date emissao;
    @Column(name = "DATAHORAAUTORIZACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datahoraautorizacao;
    @Column(name = "MODELO")
    private Integer modelo;
    @Column(name = "OPERACAORIGEM")
    private BigInteger operacaorigem;
    @Column(name = "TIPOOPERACAORIGEM")
    private String tipooperacaorigem;
    @Column(name = "TIPONFE")
    private String tiponfe;
    @Column(name = "OPERACAOORIGEM")
    private BigInteger operacaoorigem;
    @Column(name = "TIPOOPERACAOORIGEM")
    private String tipooperacaoorigem;
    @Column(name = "VERSAO")
    private String versao;
    @Column(name = "INFCPL")
    private String infcpl;
    @Column(name = "UFEMBARQ")
    private String ufembarq;
    @Column(name = "XLOCEMBARQ")
    private String xlocembarq;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VTOTTRIB")
    private BigDecimal vtottrib;
    @OneToMany(mappedBy = "codigonfe")
    private Collection<OffNfeVol> offNfeVolCollection;
    @OneToMany(mappedBy = "codigonfe")
    private Collection<OffNfeCancelamento> offNfeCancelamentoCollection;
    @OneToMany(mappedBy = "codigonfe")
    private Collection<OffNfeCartacorrecao> offNfeCartacorrecaoCollection;
    @OneToMany(mappedBy = "codigonfe")
    private Collection<OffNfeDocFiscalRef> offNfeDocFiscalRefCollection;
    @OneToMany(mappedBy = "codigonfe")
    private Collection<OffNfeDest> offNfeDestCollection;
    @OneToMany(mappedBy = "codigonfe")
    private Collection<OffNfeProd> offNfeProdCollection;
    @JoinColumn(name = "ID_NFCE_PDV", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdv idNfcePdv;
    @OneToMany(mappedBy = "codigonfe")
    private Collection<OffNfeEmit> offNfeEmitCollection;
    @OneToMany(mappedBy = "codigonfe")
    private Collection<OffNfeIdeTotais> offNfeIdeTotaisCollection;
    @OneToMany(mappedBy = "codigonfe")
    private Collection<OffNfeTransp> offNfeTranspCollection;

    public OffNfcePdvNotas() {
    }

    public OffNfcePdvNotas(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPdv() {
        return pdv;
    }

    public void setPdv(Integer pdv) {
        this.pdv = pdv;
    }

    public Integer getAmbiente() {
        return ambiente;
    }

    public void setAmbiente(Integer ambiente) {
        this.ambiente = ambiente;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getChaveDv() {
        return chaveDv;
    }

    public void setChaveDv(String chaveDv) {
        this.chaveDv = chaveDv;
    }

    public String getCodigorandomico() {
        return codigorandomico;
    }

    public void setCodigorandomico(String codigorandomico) {
        this.codigorandomico = codigorandomico;
    }

    public Integer getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(Integer finalidade) {
        this.finalidade = finalidade;
    }

    public Integer getTipoemissao() {
        return tipoemissao;
    }

    public void setTipoemissao(Integer tipoemissao) {
        this.tipoemissao = tipoemissao;
    }

    public Integer getTipoimpressao() {
        return tipoimpressao;
    }

    public void setTipoimpressao(Integer tipoimpressao) {
        this.tipoimpressao = tipoimpressao;
    }

    public Integer getIndicadordepresenca() {
        return indicadordepresenca;
    }

    public void setIndicadordepresenca(Integer indicadordepresenca) {
        this.indicadordepresenca = indicadordepresenca;
    }

    public String getProtocolo() {
        return protocolo;
    }

    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }

    public String getDadosqrcode() {
        return dadosqrcode;
    }

    public void setDadosqrcode(String dadosqrcode) {
        this.dadosqrcode = dadosqrcode;
    }

    public BigInteger getCodigovenda() {
        return codigovenda;
    }

    public void setCodigovenda(BigInteger codigovenda) {
        this.codigovenda = codigovenda;
    }

    public String getNumeroprotocolo() {
        return numeroprotocolo;
    }

    public void setNumeroprotocolo(String numeroprotocolo) {
        this.numeroprotocolo = numeroprotocolo;
    }

    public Date getUltimamodificacao() {
        return ultimamodificacao;
    }

    public void setUltimamodificacao(Date ultimamodificacao) {
        this.ultimamodificacao = ultimamodificacao;
    }

    public Date getEmissao() {
        return emissao;
    }

    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    public Date getDatahoraautorizacao() {
        return datahoraautorizacao;
    }

    public void setDatahoraautorizacao(Date datahoraautorizacao) {
        this.datahoraautorizacao = datahoraautorizacao;
    }

    public Integer getModelo() {
        return modelo;
    }

    public void setModelo(Integer modelo) {
        this.modelo = modelo;
    }

    public BigInteger getOperacaorigem() {
        return operacaorigem;
    }

    public void setOperacaorigem(BigInteger operacaorigem) {
        this.operacaorigem = operacaorigem;
    }

    public String getTipooperacaorigem() {
        return tipooperacaorigem;
    }

    public void setTipooperacaorigem(String tipooperacaorigem) {
        this.tipooperacaorigem = tipooperacaorigem;
    }

    public String getTiponfe() {
        return tiponfe;
    }

    public void setTiponfe(String tiponfe) {
        this.tiponfe = tiponfe;
    }

    public BigInteger getOperacaoorigem() {
        return operacaoorigem;
    }

    public void setOperacaoorigem(BigInteger operacaoorigem) {
        this.operacaoorigem = operacaoorigem;
    }

    public String getTipooperacaoorigem() {
        return tipooperacaoorigem;
    }

    public void setTipooperacaoorigem(String tipooperacaoorigem) {
        this.tipooperacaoorigem = tipooperacaoorigem;
    }

    public String getVersao() {
        return versao;
    }

    public void setVersao(String versao) {
        this.versao = versao;
    }

    public String getInfcpl() {
        return infcpl;
    }

    public void setInfcpl(String infcpl) {
        this.infcpl = infcpl;
    }

    public String getUfembarq() {
        return ufembarq;
    }

    public void setUfembarq(String ufembarq) {
        this.ufembarq = ufembarq;
    }

    public String getXlocembarq() {
        return xlocembarq;
    }

    public void setXlocembarq(String xlocembarq) {
        this.xlocembarq = xlocembarq;
    }

    public BigDecimal getVtottrib() {
        return vtottrib;
    }

    public void setVtottrib(BigDecimal vtottrib) {
        this.vtottrib = vtottrib;
    }

    @XmlTransient
    public Collection<OffNfeVol> getOffNfeVolCollection() {
        return offNfeVolCollection;
    }

    public void setOffNfeVolCollection(Collection<OffNfeVol> offNfeVolCollection) {
        this.offNfeVolCollection = offNfeVolCollection;
    }

    @XmlTransient
    public Collection<OffNfeCancelamento> getOffNfeCancelamentoCollection() {
        return offNfeCancelamentoCollection;
    }

    public void setOffNfeCancelamentoCollection(Collection<OffNfeCancelamento> offNfeCancelamentoCollection) {
        this.offNfeCancelamentoCollection = offNfeCancelamentoCollection;
    }

    @XmlTransient
    public Collection<OffNfeCartacorrecao> getOffNfeCartacorrecaoCollection() {
        return offNfeCartacorrecaoCollection;
    }

    public void setOffNfeCartacorrecaoCollection(Collection<OffNfeCartacorrecao> offNfeCartacorrecaoCollection) {
        this.offNfeCartacorrecaoCollection = offNfeCartacorrecaoCollection;
    }

    @XmlTransient
    public Collection<OffNfeDocFiscalRef> getOffNfeDocFiscalRefCollection() {
        return offNfeDocFiscalRefCollection;
    }

    public void setOffNfeDocFiscalRefCollection(Collection<OffNfeDocFiscalRef> offNfeDocFiscalRefCollection) {
        this.offNfeDocFiscalRefCollection = offNfeDocFiscalRefCollection;
    }

    @XmlTransient
    public Collection<OffNfeDest> getOffNfeDestCollection() {
        return offNfeDestCollection;
    }

    public void setOffNfeDestCollection(Collection<OffNfeDest> offNfeDestCollection) {
        this.offNfeDestCollection = offNfeDestCollection;
    }

    @XmlTransient
    public Collection<OffNfeProd> getOffNfeProdCollection() {
        return offNfeProdCollection;
    }

    public void setOffNfeProdCollection(Collection<OffNfeProd> offNfeProdCollection) {
        this.offNfeProdCollection = offNfeProdCollection;
    }

    public OffNfcePdv getIdNfcePdv() {
        return idNfcePdv;
    }

    public void setIdNfcePdv(OffNfcePdv idNfcePdv) {
        this.idNfcePdv = idNfcePdv;
    }

    @XmlTransient
    public Collection<OffNfeEmit> getOffNfeEmitCollection() {
        return offNfeEmitCollection;
    }

    public void setOffNfeEmitCollection(Collection<OffNfeEmit> offNfeEmitCollection) {
        this.offNfeEmitCollection = offNfeEmitCollection;
    }

    @XmlTransient
    public Collection<OffNfeIdeTotais> getOffNfeIdeTotaisCollection() {
        return offNfeIdeTotaisCollection;
    }

    public void setOffNfeIdeTotaisCollection(Collection<OffNfeIdeTotais> offNfeIdeTotaisCollection) {
        this.offNfeIdeTotaisCollection = offNfeIdeTotaisCollection;
    }

    @XmlTransient
    public Collection<OffNfeTransp> getOffNfeTranspCollection() {
        return offNfeTranspCollection;
    }

    public void setOffNfeTranspCollection(Collection<OffNfeTransp> offNfeTranspCollection) {
        this.offNfeTranspCollection = offNfeTranspCollection;
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
        if (!(object instanceof OffNfcePdvNotas)) {
            return false;
        }
        OffNfcePdvNotas other = (OffNfcePdvNotas) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.java.com.fincatto.documentofiscal.OffNfcePdvNotas[ id=" + id + " ]";
    }
    
}
