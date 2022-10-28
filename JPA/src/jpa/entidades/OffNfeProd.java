/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entidades;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "OFF_NFE_PROD",schema = "DINNAMUS")
@XmlRootElement
public class OffNfeProd implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGONFEPRODUTO")
    private Long codigonfeproduto;
    @Column(name = "ITEM")
    private Integer item;
    @Column(name = "CODIGOPRODUTO")
    private Integer codigoproduto;
    @Column(name = "NOME")
    private String nome;
    @Column(name = "NCM")
    private String ncm;
    @Column(name = "EXTTIPI")
    private String exttipi;
    @Column(name = "CFOP")
    private String cfop;
    @Column(name = "UNCOMERCIAL")
    private String uncomercial;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "QTDCOMERCIAL")
    private BigDecimal qtdcomercial;
    @Column(name = "VALORUNITCOMERCIAL")
    private BigDecimal valorunitcomercial;
    @Column(name = "UNTRIBUTAVAL")
    private String untributaval;
    @Column(name = "QTDTRIBUTAVAL")
    private BigDecimal qtdtributaval;
    @Column(name = "VALORUNITTRIBUTARIO")
    private BigDecimal valorunittributario;
    @Column(name = "TOTAL")
    private BigDecimal total;
    @Column(name = "SEGURO")
    private BigDecimal seguro;
    @Column(name = "DESCONTO")
    private BigDecimal desconto;
    @Column(name = "FRETE")
    private BigDecimal frete;
    @Column(name = "EAN")
    private String ean;
    @Column(name = "CST")
    private Integer cst;
    @Column(name = "BASECALCICMS")
    private BigDecimal basecalcicms;
    @Column(name = "VALORICMS")
    private BigDecimal valoricms;
    @Column(name = "ALIQOUTAICMS")
    private BigDecimal aliqoutaicms;
    @Column(name = "TIPOIMPOSTO")
    private Integer tipoimposto;
    @Column(name = "REGIMETRIBUTARIO")
    private Integer regimetributario;
    @Column(name = "SITUACAOTRIBUTARIA")
    private String situacaotributaria;
    @Column(name = "MODALIDADEDETCALCBC")
    private Integer modalidadedetcalcbc;
    @Column(name = "ORIGEMDOPRODUTO")
    private Integer origemdoproduto;
    @Column(name = "MODALIDADEDETBASECALC")
    private Integer modalidadedetbasecalc;
    @Column(name = "PERCENTUALREDUCAOBC")
    private BigDecimal percentualreducaobc;
    @Column(name = "BASECALCICMS_ST")
    private BigDecimal basecalcicmsSt;
    @Column(name = "VALORICMS_ST")
    private BigDecimal valoricmsSt;
    @Column(name = "MODALIDADETBASECALC_ST")
    private Integer modalidadetbasecalcSt;
    @Column(name = "ALIQUOTAICMS_ST")
    private BigDecimal aliquotaicmsSt;
    @Column(name = "PERC_REDUCAO_BC_ST")
    private BigDecimal percReducaoBcSt;
    @Column(name = "PERC_MARGEM_VALOR_ADIC_ST")
    private BigDecimal percMargemValorAdicSt;
    @Column(name = "OUTRASDESP")
    private BigDecimal outrasdesp;
    @Column(name = "VBCSTRET")
    private BigDecimal vbcstret;
    @Column(name = "VICMSSTRET")
    private BigDecimal vicmsstret;
    @Column(name = "ICMS_SN_101_ALIQAPLIC")
    private BigDecimal icmsSn101Aliqaplic;
    @Column(name = "ICMS_SN_101_VLRCRED")
    private BigDecimal icmsSn101Vlrcred;
    @Column(name = "ALIQUOTAIPI")
    private BigDecimal aliquotaipi;
    @Column(name = "VALORIPI")
    private BigDecimal valoripi;
    @Column(name = "SITUACAOIPI")
    private String situacaoipi;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.DATE)
    private Date ultimamodificacao;
    @Column(name = "VTOTTRIB")
    private BigDecimal vtottrib;
    @Column(name = "CPRODANP")
    private String cprodanp;
    @Column(name = "UFCONS")
    private String ufcons;
    @Column(name = "VALORPIS")
    private BigDecimal valorpis;
    @Column(name = "VALORCOFINS")
    private BigDecimal valorcofins;
    @Column(name = "PIS_CST")
    private Integer pisCst;
    @Column(name = "COFINS_CST")
    private Integer cofinsCst;
    @Column(name = "PIS_ALIQUOTA")
    private BigDecimal pisAliquota;
    @Column(name = "COFINS_ALIQUOTA")
    private BigDecimal cofinsAliquota;
    @Column(name = "VBCUFDEST")
    private BigDecimal vbcufdest;
    @Column(name = "PICMSUFDEST")
    private BigDecimal picmsufdest;
    @Column(name = "PICMSINTER")
    private BigDecimal picmsinter;
    @Column(name = "PICMSINTERPART")
    private BigDecimal picmsinterpart;
    @Column(name = "VICMSUFDEST")
    private BigDecimal vicmsufdest;
    @Column(name = "VICMSUFREMET")
    private BigDecimal vicmsufremet;
    @Column(name = "VFCPUFDEST")
    private BigDecimal vfcpufdest;
    @Column(name = "PFCPUFDEST")
    private BigDecimal pfcpufdest;
    @Column(name = "CEST")
    private String cest;
    @JoinColumn(name = "CODIGONFE", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdvNotas codigonfe;

    public OffNfeProd() {
    }

    public OffNfeProd(Long codigonfeproduto) {
        this.codigonfeproduto = codigonfeproduto;
    }

    public Long getCodigonfeproduto() {
        return codigonfeproduto;
    }

    public void setCodigonfeproduto(Long codigonfeproduto) {
        this.codigonfeproduto = codigonfeproduto;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public Integer getCodigoproduto() {
        return codigoproduto;
    }

    public void setCodigoproduto(Integer codigoproduto) {
        this.codigoproduto = codigoproduto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNcm() {
        return ncm;
    }

    public void setNcm(String ncm) {
        this.ncm = ncm;
    }

    public String getExttipi() {
        return exttipi;
    }

    public void setExttipi(String exttipi) {
        this.exttipi = exttipi;
    }

    public String getCfop() {
        return cfop;
    }

    public void setCfop(String cfop) {
        this.cfop = cfop;
    }

    public String getUncomercial() {
        return uncomercial;
    }

    public void setUncomercial(String uncomercial) {
        this.uncomercial = uncomercial;
    }

    public BigDecimal getQtdcomercial() {
        return qtdcomercial;
    }

    public void setQtdcomercial(BigDecimal qtdcomercial) {
        this.qtdcomercial = qtdcomercial;
    }

    public BigDecimal getValorunitcomercial() {
        return valorunitcomercial;
    }

    public void setValorunitcomercial(BigDecimal valorunitcomercial) {
        this.valorunitcomercial = valorunitcomercial;
    }

    public String getUntributaval() {
        return untributaval;
    }

    public void setUntributaval(String untributaval) {
        this.untributaval = untributaval;
    }

    public BigDecimal getQtdtributaval() {
        return qtdtributaval;
    }

    public void setQtdtributaval(BigDecimal qtdtributaval) {
        this.qtdtributaval = qtdtributaval;
    }

    public BigDecimal getValorunittributario() {
        return valorunittributario;
    }

    public void setValorunittributario(BigDecimal valorunittributario) {
        this.valorunittributario = valorunittributario;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getSeguro() {
        return seguro;
    }

    public void setSeguro(BigDecimal seguro) {
        this.seguro = seguro;
    }

    public BigDecimal getDesconto() {
        return desconto;
    }

    public void setDesconto(BigDecimal desconto) {
        this.desconto = desconto;
    }

    public BigDecimal getFrete() {
        return frete;
    }

    public void setFrete(BigDecimal frete) {
        this.frete = frete;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Integer getCst() {
        return cst;
    }

    public void setCst(Integer cst) {
        this.cst = cst;
    }

    public BigDecimal getBasecalcicms() {
        return basecalcicms;
    }

    public void setBasecalcicms(BigDecimal basecalcicms) {
        this.basecalcicms = basecalcicms;
    }

    public BigDecimal getValoricms() {
        return valoricms;
    }

    public void setValoricms(BigDecimal valoricms) {
        this.valoricms = valoricms;
    }

    public BigDecimal getAliqoutaicms() {
        return aliqoutaicms;
    }

    public void setAliqoutaicms(BigDecimal aliqoutaicms) {
        this.aliqoutaicms = aliqoutaicms;
    }

    public Integer getTipoimposto() {
        return tipoimposto;
    }

    public void setTipoimposto(Integer tipoimposto) {
        this.tipoimposto = tipoimposto;
    }

    public Integer getRegimetributario() {
        return regimetributario;
    }

    public void setRegimetributario(Integer regimetributario) {
        this.regimetributario = regimetributario;
    }

    public String getSituacaotributaria() {
        return situacaotributaria;
    }

    public void setSituacaotributaria(String situacaotributaria) {
        this.situacaotributaria = situacaotributaria;
    }

    public Integer getModalidadedetcalcbc() {
        return modalidadedetcalcbc;
    }

    public void setModalidadedetcalcbc(Integer modalidadedetcalcbc) {
        this.modalidadedetcalcbc = modalidadedetcalcbc;
    }

    public Integer getOrigemdoproduto() {
        return origemdoproduto;
    }

    public void setOrigemdoproduto(Integer origemdoproduto) {
        this.origemdoproduto = origemdoproduto;
    }

    public Integer getModalidadedetbasecalc() {
        return modalidadedetbasecalc;
    }

    public void setModalidadedetbasecalc(Integer modalidadedetbasecalc) {
        this.modalidadedetbasecalc = modalidadedetbasecalc;
    }

    public BigDecimal getPercentualreducaobc() {
        return percentualreducaobc;
    }

    public void setPercentualreducaobc(BigDecimal percentualreducaobc) {
        this.percentualreducaobc = percentualreducaobc;
    }

    public BigDecimal getBasecalcicmsSt() {
        return basecalcicmsSt;
    }

    public void setBasecalcicmsSt(BigDecimal basecalcicmsSt) {
        this.basecalcicmsSt = basecalcicmsSt;
    }

    public BigDecimal getValoricmsSt() {
        return valoricmsSt;
    }

    public void setValoricmsSt(BigDecimal valoricmsSt) {
        this.valoricmsSt = valoricmsSt;
    }

    public Integer getModalidadetbasecalcSt() {
        return modalidadetbasecalcSt;
    }

    public void setModalidadetbasecalcSt(Integer modalidadetbasecalcSt) {
        this.modalidadetbasecalcSt = modalidadetbasecalcSt;
    }

    public BigDecimal getAliquotaicmsSt() {
        return aliquotaicmsSt;
    }

    public void setAliquotaicmsSt(BigDecimal aliquotaicmsSt) {
        this.aliquotaicmsSt = aliquotaicmsSt;
    }

    public BigDecimal getPercReducaoBcSt() {
        return percReducaoBcSt;
    }

    public void setPercReducaoBcSt(BigDecimal percReducaoBcSt) {
        this.percReducaoBcSt = percReducaoBcSt;
    }

    public BigDecimal getPercMargemValorAdicSt() {
        return percMargemValorAdicSt;
    }

    public void setPercMargemValorAdicSt(BigDecimal percMargemValorAdicSt) {
        this.percMargemValorAdicSt = percMargemValorAdicSt;
    }

    public BigDecimal getOutrasdesp() {
        return outrasdesp;
    }

    public void setOutrasdesp(BigDecimal outrasdesp) {
        this.outrasdesp = outrasdesp;
    }

    public BigDecimal getVbcstret() {
        return vbcstret;
    }

    public void setVbcstret(BigDecimal vbcstret) {
        this.vbcstret = vbcstret;
    }

    public BigDecimal getVicmsstret() {
        return vicmsstret;
    }

    public void setVicmsstret(BigDecimal vicmsstret) {
        this.vicmsstret = vicmsstret;
    }

    public BigDecimal getIcmsSn101Aliqaplic() {
        return icmsSn101Aliqaplic;
    }

    public void setIcmsSn101Aliqaplic(BigDecimal icmsSn101Aliqaplic) {
        this.icmsSn101Aliqaplic = icmsSn101Aliqaplic;
    }

    public BigDecimal getIcmsSn101Vlrcred() {
        return icmsSn101Vlrcred;
    }

    public void setIcmsSn101Vlrcred(BigDecimal icmsSn101Vlrcred) {
        this.icmsSn101Vlrcred = icmsSn101Vlrcred;
    }

    public BigDecimal getAliquotaipi() {
        return aliquotaipi;
    }

    public void setAliquotaipi(BigDecimal aliquotaipi) {
        this.aliquotaipi = aliquotaipi;
    }

    public BigDecimal getValoripi() {
        return valoripi;
    }

    public void setValoripi(BigDecimal valoripi) {
        this.valoripi = valoripi;
    }

    public String getSituacaoipi() {
        return situacaoipi;
    }

    public void setSituacaoipi(String situacaoipi) {
        this.situacaoipi = situacaoipi;
    }

    public Date getUltimamodificacao() {
        return ultimamodificacao;
    }

    public void setUltimamodificacao(Date ultimamodificacao) {
        this.ultimamodificacao = ultimamodificacao;
    }

    public BigDecimal getVtottrib() {
        return vtottrib;
    }

    public void setVtottrib(BigDecimal vtottrib) {
        this.vtottrib = vtottrib;
    }

    public String getCprodanp() {
        return cprodanp;
    }

    public void setCprodanp(String cprodanp) {
        this.cprodanp = cprodanp;
    }

    public String getUfcons() {
        return ufcons;
    }

    public void setUfcons(String ufcons) {
        this.ufcons = ufcons;
    }

    public BigDecimal getValorpis() {
        return valorpis;
    }

    public void setValorpis(BigDecimal valorpis) {
        this.valorpis = valorpis;
    }

    public BigDecimal getValorcofins() {
        return valorcofins;
    }

    public void setValorcofins(BigDecimal valorcofins) {
        this.valorcofins = valorcofins;
    }

    public Integer getPisCst() {
        return pisCst;
    }

    public void setPisCst(Integer pisCst) {
        this.pisCst = pisCst;
    }

    public Integer getCofinsCst() {
        return cofinsCst;
    }

    public void setCofinsCst(Integer cofinsCst) {
        this.cofinsCst = cofinsCst;
    }

    public BigDecimal getPisAliquota() {
        return pisAliquota;
    }

    public void setPisAliquota(BigDecimal pisAliquota) {
        this.pisAliquota = pisAliquota;
    }

    public BigDecimal getCofinsAliquota() {
        return cofinsAliquota;
    }

    public void setCofinsAliquota(BigDecimal cofinsAliquota) {
        this.cofinsAliquota = cofinsAliquota;
    }

    public BigDecimal getVbcufdest() {
        return vbcufdest;
    }

    public void setVbcufdest(BigDecimal vbcufdest) {
        this.vbcufdest = vbcufdest;
    }

    public BigDecimal getPicmsufdest() {
        return picmsufdest;
    }

    public void setPicmsufdest(BigDecimal picmsufdest) {
        this.picmsufdest = picmsufdest;
    }

    public BigDecimal getPicmsinter() {
        return picmsinter;
    }

    public void setPicmsinter(BigDecimal picmsinter) {
        this.picmsinter = picmsinter;
    }

    public BigDecimal getPicmsinterpart() {
        return picmsinterpart;
    }

    public void setPicmsinterpart(BigDecimal picmsinterpart) {
        this.picmsinterpart = picmsinterpart;
    }

    public BigDecimal getVicmsufdest() {
        return vicmsufdest;
    }

    public void setVicmsufdest(BigDecimal vicmsufdest) {
        this.vicmsufdest = vicmsufdest;
    }

    public BigDecimal getVicmsufremet() {
        return vicmsufremet;
    }

    public void setVicmsufremet(BigDecimal vicmsufremet) {
        this.vicmsufremet = vicmsufremet;
    }

    public BigDecimal getVfcpufdest() {
        return vfcpufdest;
    }

    public void setVfcpufdest(BigDecimal vfcpufdest) {
        this.vfcpufdest = vfcpufdest;
    }

    public BigDecimal getPfcpufdest() {
        return pfcpufdest;
    }

    public void setPfcpufdest(BigDecimal pfcpufdest) {
        this.pfcpufdest = pfcpufdest;
    }

    public String getCest() {
        return cest;
    }

    public void setCest(String cest) {
        this.cest = cest;
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
        hash += (codigonfeproduto != null ? codigonfeproduto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OffNfeProd)) {
            return false;
        }
        OffNfeProd other = (OffNfeProd) object;
        if ((this.codigonfeproduto == null && other.codigonfeproduto != null) || (this.codigonfeproduto != null && !this.codigonfeproduto.equals(other.codigonfeproduto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.java.com.fincatto.documentofiscal.OffNfeProd[ codigonfeproduto=" + codigonfeproduto + " ]";
    }
    
}
