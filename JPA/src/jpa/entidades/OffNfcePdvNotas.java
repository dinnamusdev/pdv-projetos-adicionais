/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "OFF_NFCE_PDV_NOTAS",schema = "DINNAMUS")
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
    @NamedQuery(name = "OffNfcePdvNotas.findByDatahoraautorizacao", query = "SELECT o FROM OffNfcePdvNotas o WHERE o.datahoraautorizacao = :datahoraautorizacao")})
public class OffNfcePdvNotas   implements Serializable   {
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
    @OneToMany(mappedBy = "idNfcePdvNotas")
    private Collection<OffNfcePdvNotasCanc> offNfcePdvNotasCancCollection;
    @JoinColumn(name = "ID_NFCE_PDV", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdv idNfcePdv;
    @OneToMany(mappedBy = "idNfcePdvNotas")
    private Collection<OffNfcePdvNotasCpl> offNfcePdvNotasCplCollection;
    @Column(name = "MODELO")
    private int modelo;
    @Column(name = "VERSAO")
    private String versao;
    @Column(name = "OPERACAOORIGEM")
    private Long operacaoorigem;
    @Column(name = "TIPOOPERACAOORIGEM")
    private String tipooperacaoorigem;
    @Column(name = "TIPONFE")
    private String tiponfe;
    
    @OneToMany(mappedBy = "codigonfe", fetch = FetchType.LAZY, cascade = CascadeType.ALL) 
    private Collection<OffNfeDest> OffNfeDestCollection;
    
    @OneToMany(mappedBy = "codigonfe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<OffNfeEmit> OffNfeEmitCollection;
    
    @OneToMany(mappedBy = "codigonfe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<OffNfeIdeTotais> OffNfeIdeTotaisCollection;
    
    @OneToMany(mappedBy = "codigonfe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<OffNfeProd> OffNfeProdCollection;
   
    @OneToMany(mappedBy = "codigonfe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<OffNfeTransp> OffNfeTranspCollection;
   
    @OneToMany(mappedBy = "codigonfe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<OffNfeVol> OffNfeVolCollection;
   
    @OneToMany(mappedBy = "codigonfe", fetch = FetchType.LAZY , cascade = CascadeType.ALL)
    private Collection<OffNfeDocFiscalRef> OffNfeDocFiscalRefCollection;
   
    @OneToMany(mappedBy = "codigonfe", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<OffNfeCartacorrecao> OffNfeCartacorrecaoCollection;
   
  
    
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

    @XmlTransient
    public Collection<OffNfcePdvNotasCanc> getOffNfcePdvNotasCancCollection() {
        return offNfcePdvNotasCancCollection;
    }

    public void setOffNfcePdvNotasCancCollection(Collection<OffNfcePdvNotasCanc> offNfcePdvNotasCancCollection) {
        this.offNfcePdvNotasCancCollection = offNfcePdvNotasCancCollection;
    }

    public OffNfcePdv getIdNfcePdv() {
        return idNfcePdv;
    }

    public void setIdNfcePdv(OffNfcePdv idNfcePdv) {
        this.idNfcePdv = idNfcePdv;
    }

    @XmlTransient
    public Collection<OffNfcePdvNotasCpl> getOffNfcePdvNotasCplCollection() {
        return offNfcePdvNotasCplCollection;
    }

    public void setOffNfcePdvNotasCplCollection(Collection<OffNfcePdvNotasCpl> offNfcePdvNotasCplCollection) {
        this.offNfcePdvNotasCplCollection = offNfcePdvNotasCplCollection;
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
        return "jpa.entidades.OffNfcePdvNotas[ id=" + id + " ]";
    }

    /**
     * @return the modelo
     */
    public int getModelo() {
        return modelo;
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the versao
     */
    public String getVersao() {
        return versao;
    }

    /**
     * @param versao the versao to set
     */
    public void setVersao(String versao) {
        this.versao = versao;
    }

    /**
     * @return the operacaoorigem
     */
    public Long getOperacaoorigem() {
        return operacaoorigem;
    }

    /**
     * @param operacaoorigem the operacaoorigem to set
     */
    public void setOperacaoorigem(Long operacaoorigem) {
        this.operacaoorigem = operacaoorigem;
    }

    /**
     * @return the tipooperacaoorigem
     */
    public String getTipooperacaoorigem() {
        return tipooperacaoorigem;
    }

    /**
     * @param tipooperacaoorigem the tipooperacaoorigem to set
     */
    public void setTipooperacaoorigem(String tipooperacaoorigem) {
        this.tipooperacaoorigem = tipooperacaoorigem;
    }

    /**
     * @return the tiponfe
     */
    public String getTiponfe() {
        return tiponfe;
    }

    /**
     * @param tiponfe the tiponfe to set
     */
    public void setTiponfe(String tiponfe) {
        this.tiponfe = tiponfe;
    }

    /**
     * @return the OffNfeDestCollection
     */
    public Collection<OffNfeDest> getOffNfeDestCollection() {
        return OffNfeDestCollection;
    }

    /**
     * @param OffNfeDestCollection the OffNfeDestCollection to set
     */
    public void setOffNfeDestCollection(Collection<OffNfeDest> OffNfeDestCollection) {
        this.OffNfeDestCollection = OffNfeDestCollection;
    }

    /**
     * @return the OffNfeEmitCollection
     */
    public Collection<OffNfeEmit> getOffNfeEmitCollection() {
        return OffNfeEmitCollection;
    }

    /**
     * @param OffNfeEmitCollection the OffNfeEmitCollection to set
     */
    public void setOffNfeEmitCollection(Collection<OffNfeEmit> OffNfeEmitCollection) {
        this.OffNfeEmitCollection = OffNfeEmitCollection;
    }

    /**
     * @return the OffNfeIdeTotaisCollection
     */
    public Collection<OffNfeIdeTotais> getOffNfeIdeTotaisCollection() {
        return OffNfeIdeTotaisCollection;
    }

    /**
     * @param OffNfeIdeTotaisCollection the OffNfeIdeTotaisCollection to set
     */
    public void setOffNfeIdeTotaisCollection(Collection<OffNfeIdeTotais> OffNfeIdeTotaisCollection) {
        this.OffNfeIdeTotaisCollection = OffNfeIdeTotaisCollection;
    }

    /**
     * @return the OffNfeProdCollection
     */
    public Collection<OffNfeProd> getOffNfeProdCollection() {
        return OffNfeProdCollection;
    }

    /**
     * @param OffNfeProdCollection the OffNfeProdCollection to set
     */
    public void setOffNfeProdCollection(Collection<OffNfeProd> OffNfeProdCollection) {
        this.OffNfeProdCollection = OffNfeProdCollection;
    }

    /**
     * @return the OffNfeTranspCollection
     */
    public Collection<OffNfeTransp> getOffNfeTranspCollection() {
        return OffNfeTranspCollection;
    }

    /**
     * @param OffNfeTranspCollection the OffNfeTranspCollection to set
     */
    public void setOffNfeTranspCollection(Collection<OffNfeTransp> OffNfeTranspCollection) {
        this.OffNfeTranspCollection = OffNfeTranspCollection;
    }

    /**
     * @return the OffNfeVolCollection
     */
    public Collection<OffNfeVol> getOffNfeVolCollection() {
        return OffNfeVolCollection;
    }

    /**
     * @param OffNfeVolCollection the OffNfeVolCollection to set
     */
    public void setOffNfeVolCollection(Collection<OffNfeVol> OffNfeVolCollection) {
        this.OffNfeVolCollection = OffNfeVolCollection;
    }

    /**
     * @return the OffNfeDocFiscalRefCollection
     */
    public Collection<OffNfeDocFiscalRef> getOffNfeDocFiscalRefCollection() {
        return OffNfeDocFiscalRefCollection;
    }

    /**
     * @param OffNfeDocFiscalRefCollection the OffNfeDocFiscalRefCollection to set
     */
    public void setOffNfeDocFiscalRefCollection(Collection<OffNfeDocFiscalRef> OffNfeDocFiscalRefCollection) {
        this.OffNfeDocFiscalRefCollection = OffNfeDocFiscalRefCollection;
    }

    /**
     * @return the OffNfeCartacorrecaoCollection
     */
    public Collection<OffNfeCartacorrecao> getOffNfeCartacorrecaoCollection() {
        return OffNfeCartacorrecaoCollection;
    }

    /**
     * @param OffNfeCartacorrecaoCollection the OffNfeCartacorrecaoCollection to set
     */
    public void setOffNfeCartacorrecaoCollection(Collection<OffNfeCartacorrecao> OffNfeCartacorrecaoCollection) {
        this.OffNfeCartacorrecaoCollection = OffNfeCartacorrecaoCollection;
    }
    
}
