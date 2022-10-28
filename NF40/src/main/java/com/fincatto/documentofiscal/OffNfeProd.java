/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.fincatto.documentofiscal;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "OFF_NFE_PROD")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OffNfeProd.findAll", query = "SELECT o FROM OffNfeProd o"),
    @NamedQuery(name = "OffNfeProd.findByCodigonfeproduto", query = "SELECT o FROM OffNfeProd o WHERE o.codigonfeproduto = :codigonfeproduto"),
    @NamedQuery(name = "OffNfeProd.findByItem", query = "SELECT o FROM OffNfeProd o WHERE o.item = :item"),
    @NamedQuery(name = "OffNfeProd.findByCodigoproduto", query = "SELECT o FROM OffNfeProd o WHERE o.codigoproduto = :codigoproduto"),
    @NamedQuery(name = "OffNfeProd.findByNome", query = "SELECT o FROM OffNfeProd o WHERE o.nome = :nome"),
    @NamedQuery(name = "OffNfeProd.findByNcm", query = "SELECT o FROM OffNfeProd o WHERE o.ncm = :ncm"),
    @NamedQuery(name = "OffNfeProd.findByExttipi", query = "SELECT o FROM OffNfeProd o WHERE o.exttipi = :exttipi"),
    @NamedQuery(name = "OffNfeProd.findByCfop", query = "SELECT o FROM OffNfeProd o WHERE o.cfop = :cfop"),
    @NamedQuery(name = "OffNfeProd.findByUncomercial", query = "SELECT o FROM OffNfeProd o WHERE o.uncomercial = :uncomercial"),
    @NamedQuery(name = "OffNfeProd.findByQtdcomercial", query = "SELECT o FROM OffNfeProd o WHERE o.qtdcomercial = :qtdcomercial"),
    @NamedQuery(name = "OffNfeProd.findByValorunitcomercial", query = "SELECT o FROM OffNfeProd o WHERE o.valorunitcomercial = :valorunitcomercial"),
    @NamedQuery(name = "OffNfeProd.findByUntributaval", query = "SELECT o FROM OffNfeProd o WHERE o.untributaval = :untributaval"),
    @NamedQuery(name = "OffNfeProd.findByQtdtributaval", query = "SELECT o FROM OffNfeProd o WHERE o.qtdtributaval = :qtdtributaval"),
    @NamedQuery(name = "OffNfeProd.findByValorunittributario", query = "SELECT o FROM OffNfeProd o WHERE o.valorunittributario = :valorunittributario"),
    @NamedQuery(name = "OffNfeProd.findByTotal", query = "SELECT o FROM OffNfeProd o WHERE o.total = :total"),
    @NamedQuery(name = "OffNfeProd.findBySeguro", query = "SELECT o FROM OffNfeProd o WHERE o.seguro = :seguro"),
    @NamedQuery(name = "OffNfeProd.findByDesconto", query = "SELECT o FROM OffNfeProd o WHERE o.desconto = :desconto"),
    @NamedQuery(name = "OffNfeProd.findByFrete", query = "SELECT o FROM OffNfeProd o WHERE o.frete = :frete"),
    @NamedQuery(name = "OffNfeProd.findByEan", query = "SELECT o FROM OffNfeProd o WHERE o.ean = :ean"),
    @NamedQuery(name = "OffNfeProd.findByCst", query = "SELECT o FROM OffNfeProd o WHERE o.cst = :cst"),
    @NamedQuery(name = "OffNfeProd.findByBasecalcicms", query = "SELECT o FROM OffNfeProd o WHERE o.basecalcicms = :basecalcicms"),
    @NamedQuery(name = "OffNfeProd.findByValoricms", query = "SELECT o FROM OffNfeProd o WHERE o.valoricms = :valoricms"),
    @NamedQuery(name = "OffNfeProd.findByAliqoutaicms", query = "SELECT o FROM OffNfeProd o WHERE o.aliqoutaicms = :aliqoutaicms"),
    @NamedQuery(name = "OffNfeProd.findByTipoimposto", query = "SELECT o FROM OffNfeProd o WHERE o.tipoimposto = :tipoimposto"),
    @NamedQuery(name = "OffNfeProd.findByRegimetributario", query = "SELECT o FROM OffNfeProd o WHERE o.regimetributario = :regimetributario"),
    @NamedQuery(name = "OffNfeProd.findBySituacaotributaria", query = "SELECT o FROM OffNfeProd o WHERE o.situacaotributaria = :situacaotributaria"),
    @NamedQuery(name = "OffNfeProd.findByModalidadedetcalcbc", query = "SELECT o FROM OffNfeProd o WHERE o.modalidadedetcalcbc = :modalidadedetcalcbc"),
    @NamedQuery(name = "OffNfeProd.findByOrigemdoproduto", query = "SELECT o FROM OffNfeProd o WHERE o.origemdoproduto = :origemdoproduto"),
    @NamedQuery(name = "OffNfeProd.findByModalidadedetbasecalc", query = "SELECT o FROM OffNfeProd o WHERE o.modalidadedetbasecalc = :modalidadedetbasecalc"),
    @NamedQuery(name = "OffNfeProd.findByPercentualreducaobc", query = "SELECT o FROM OffNfeProd o WHERE o.percentualreducaobc = :percentualreducaobc"),
    @NamedQuery(name = "OffNfeProd.findByBasecalcicmsSt", query = "SELECT o FROM OffNfeProd o WHERE o.basecalcicmsSt = :basecalcicmsSt"),
    @NamedQuery(name = "OffNfeProd.findByValoricmsSt", query = "SELECT o FROM OffNfeProd o WHERE o.valoricmsSt = :valoricmsSt"),
    @NamedQuery(name = "OffNfeProd.findByModalidadetbasecalcSt", query = "SELECT o FROM OffNfeProd o WHERE o.modalidadetbasecalcSt = :modalidadetbasecalcSt"),
    @NamedQuery(name = "OffNfeProd.findByAliquotaicmsSt", query = "SELECT o FROM OffNfeProd o WHERE o.aliquotaicmsSt = :aliquotaicmsSt"),
    @NamedQuery(name = "OffNfeProd.findByPercReducaoBcSt", query = "SELECT o FROM OffNfeProd o WHERE o.percReducaoBcSt = :percReducaoBcSt"),
    @NamedQuery(name = "OffNfeProd.findByPercMargemValorAdicSt", query = "SELECT o FROM OffNfeProd o WHERE o.percMargemValorAdicSt = :percMargemValorAdicSt"),
    @NamedQuery(name = "OffNfeProd.findByOutrasdesp", query = "SELECT o FROM OffNfeProd o WHERE o.outrasdesp = :outrasdesp"),
    @NamedQuery(name = "OffNfeProd.findByVbcstret", query = "SELECT o FROM OffNfeProd o WHERE o.vbcstret = :vbcstret"),
    @NamedQuery(name = "OffNfeProd.findByVicmsstret", query = "SELECT o FROM OffNfeProd o WHERE o.vicmsstret = :vicmsstret"),
    @NamedQuery(name = "OffNfeProd.findByIcmsSn101Aliqaplic", query = "SELECT o FROM OffNfeProd o WHERE o.icmsSn101Aliqaplic = :icmsSn101Aliqaplic"),
    @NamedQuery(name = "OffNfeProd.findByIcmsSn101Vlrcred", query = "SELECT o FROM OffNfeProd o WHERE o.icmsSn101Vlrcred = :icmsSn101Vlrcred"),
    @NamedQuery(name = "OffNfeProd.findByAliquotaipi", query = "SELECT o FROM OffNfeProd o WHERE o.aliquotaipi = :aliquotaipi"),
    @NamedQuery(name = "OffNfeProd.findByValoripi", query = "SELECT o FROM OffNfeProd o WHERE o.valoripi = :valoripi"),
    @NamedQuery(name = "OffNfeProd.findBySituacaoipi", query = "SELECT o FROM OffNfeProd o WHERE o.situacaoipi = :situacaoipi"),
    @NamedQuery(name = "OffNfeProd.findByUltimamodificacao", query = "SELECT o FROM OffNfeProd o WHERE o.ultimamodificacao = :ultimamodificacao"),
    @NamedQuery(name = "OffNfeProd.findByVtottrib", query = "SELECT o FROM OffNfeProd o WHERE o.vtottrib = :vtottrib"),
    @NamedQuery(name = "OffNfeProd.findByCprodanp", query = "SELECT o FROM OffNfeProd o WHERE o.cprodanp = :cprodanp"),
    @NamedQuery(name = "OffNfeProd.findByUfcons", query = "SELECT o FROM OffNfeProd o WHERE o.ufcons = :ufcons"),
    @NamedQuery(name = "OffNfeProd.findByValorpis", query = "SELECT o FROM OffNfeProd o WHERE o.valorpis = :valorpis"),
    @NamedQuery(name = "OffNfeProd.findByValorcofins", query = "SELECT o FROM OffNfeProd o WHERE o.valorcofins = :valorcofins"),
    @NamedQuery(name = "OffNfeProd.findByPisCst", query = "SELECT o FROM OffNfeProd o WHERE o.pisCst = :pisCst"),
    @NamedQuery(name = "OffNfeProd.findByCofinsCst", query = "SELECT o FROM OffNfeProd o WHERE o.cofinsCst = :cofinsCst"),
    @NamedQuery(name = "OffNfeProd.findByPisAliquota", query = "SELECT o FROM OffNfeProd o WHERE o.pisAliquota = :pisAliquota"),
    @NamedQuery(name = "OffNfeProd.findByCofinsAliquota", query = "SELECT o FROM OffNfeProd o WHERE o.cofinsAliquota = :cofinsAliquota"),
    @NamedQuery(name = "OffNfeProd.findByVbcufdest", query = "SELECT o FROM OffNfeProd o WHERE o.vbcufdest = :vbcufdest"),
    @NamedQuery(name = "OffNfeProd.findByPicmsufdest", query = "SELECT o FROM OffNfeProd o WHERE o.picmsufdest = :picmsufdest"),
    @NamedQuery(name = "OffNfeProd.findByPicmsinter", query = "SELECT o FROM OffNfeProd o WHERE o.picmsinter = :picmsinter"),
    @NamedQuery(name = "OffNfeProd.findByPicmsinterpart", query = "SELECT o FROM OffNfeProd o WHERE o.picmsinterpart = :picmsinterpart"),
    @NamedQuery(name = "OffNfeProd.findByVicmsufdest", query = "SELECT o FROM OffNfeProd o WHERE o.vicmsufdest = :vicmsufdest"),
    @NamedQuery(name = "OffNfeProd.findByVicmsufremet", query = "SELECT o FROM OffNfeProd o WHERE o.vicmsufremet = :vicmsufremet"),
    @NamedQuery(name = "OffNfeProd.findByVfcpufdest", query = "SELECT o FROM OffNfeProd o WHERE o.vfcpufdest = :vfcpufdest"),
    @NamedQuery(name = "OffNfeProd.findByPfcpufdest", query = "SELECT o FROM OffNfeProd o WHERE o.pfcpufdest = :pfcpufdest"),
    @NamedQuery(name = "OffNfeProd.findByCest", query = "SELECT o FROM OffNfeProd o WHERE o.cest = :cest")})
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
