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
@Table(name = "OFF_NFE_IDE_TOTAIS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OffNfeIdeTotais.findAll", query = "SELECT o FROM OffNfeIdeTotais o"),
    @NamedQuery(name = "OffNfeIdeTotais.findByCodigonfetotais", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.codigonfetotais = :codigonfetotais"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVbc", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vbc = :vbc"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVicms", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vicms = :vicms"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVbcst", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vbcst = :vbcst"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVst", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vst = :vst"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVprod", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vprod = :vprod"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVfrete", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vfrete = :vfrete"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVseg", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vseg = :vseg"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVdesc", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vdesc = :vdesc"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVipi", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vipi = :vipi"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVpis", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vpis = :vpis"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVcofins", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vcofins = :vcofins"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVoutro", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.voutro = :voutro"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVnf", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vnf = :vnf"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVii", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vii = :vii"),
    @NamedQuery(name = "OffNfeIdeTotais.findByUltimamodificacao", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.ultimamodificacao = :ultimamodificacao"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVtottrib", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vtottrib = :vtottrib"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVicmsufdest", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vicmsufdest = :vicmsufdest"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVicmsufremet", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vicmsufremet = :vicmsufremet"),
    @NamedQuery(name = "OffNfeIdeTotais.findByVfcpufdest", query = "SELECT o FROM OffNfeIdeTotais o WHERE o.vfcpufdest = :vfcpufdest")})
public class OffNfeIdeTotais implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGONFETOTAIS")
    private Long codigonfetotais;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VBC")
    private BigDecimal vbc;
    @Column(name = "VICMS")
    private BigDecimal vicms;
    @Column(name = "VBCST")
    private BigDecimal vbcst;
    @Column(name = "VST")
    private BigDecimal vst;
    @Column(name = "VPROD")
    private BigDecimal vprod;
    @Column(name = "VFRETE")
    private BigDecimal vfrete;
    @Column(name = "VSEG")
    private BigDecimal vseg;
    @Column(name = "VDESC")
    private BigDecimal vdesc;
    @Column(name = "VIPI")
    private BigDecimal vipi;
    @Column(name = "VPIS")
    private BigDecimal vpis;
    @Column(name = "VCOFINS")
    private BigDecimal vcofins;
    @Column(name = "VOUTRO")
    private BigDecimal voutro;
    @Column(name = "VNF")
    private BigDecimal vnf;
    @Column(name = "VII")
    private BigDecimal vii;
    @Column(name = "ULTIMAMODIFICACAO")
    @Temporal(TemporalType.DATE)
    private Date ultimamodificacao;
    @Column(name = "VTOTTRIB")
    private BigDecimal vtottrib;
    @Column(name = "VICMSUFDEST")
    private BigDecimal vicmsufdest;
    @Column(name = "VICMSUFREMET")
    private BigDecimal vicmsufremet;
    @Column(name = "VFCPUFDEST")
    private BigDecimal vfcpufdest;
    @JoinColumn(name = "CODIGONFE", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdvNotas codigonfe;

    public OffNfeIdeTotais() {
    }

    public OffNfeIdeTotais(Long codigonfetotais) {
        this.codigonfetotais = codigonfetotais;
    }

    public Long getCodigonfetotais() {
        return codigonfetotais;
    }

    public void setCodigonfetotais(Long codigonfetotais) {
        this.codigonfetotais = codigonfetotais;
    }

    public BigDecimal getVbc() {
        return vbc;
    }

    public void setVbc(BigDecimal vbc) {
        this.vbc = vbc;
    }

    public BigDecimal getVicms() {
        return vicms;
    }

    public void setVicms(BigDecimal vicms) {
        this.vicms = vicms;
    }

    public BigDecimal getVbcst() {
        return vbcst;
    }

    public void setVbcst(BigDecimal vbcst) {
        this.vbcst = vbcst;
    }

    public BigDecimal getVst() {
        return vst;
    }

    public void setVst(BigDecimal vst) {
        this.vst = vst;
    }

    public BigDecimal getVprod() {
        return vprod;
    }

    public void setVprod(BigDecimal vprod) {
        this.vprod = vprod;
    }

    public BigDecimal getVfrete() {
        return vfrete;
    }

    public void setVfrete(BigDecimal vfrete) {
        this.vfrete = vfrete;
    }

    public BigDecimal getVseg() {
        return vseg;
    }

    public void setVseg(BigDecimal vseg) {
        this.vseg = vseg;
    }

    public BigDecimal getVdesc() {
        return vdesc;
    }

    public void setVdesc(BigDecimal vdesc) {
        this.vdesc = vdesc;
    }

    public BigDecimal getVipi() {
        return vipi;
    }

    public void setVipi(BigDecimal vipi) {
        this.vipi = vipi;
    }

    public BigDecimal getVpis() {
        return vpis;
    }

    public void setVpis(BigDecimal vpis) {
        this.vpis = vpis;
    }

    public BigDecimal getVcofins() {
        return vcofins;
    }

    public void setVcofins(BigDecimal vcofins) {
        this.vcofins = vcofins;
    }

    public BigDecimal getVoutro() {
        return voutro;
    }

    public void setVoutro(BigDecimal voutro) {
        this.voutro = voutro;
    }

    public BigDecimal getVnf() {
        return vnf;
    }

    public void setVnf(BigDecimal vnf) {
        this.vnf = vnf;
    }

    public BigDecimal getVii() {
        return vii;
    }

    public void setVii(BigDecimal vii) {
        this.vii = vii;
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

    public OffNfcePdvNotas getCodigonfe() {
        return codigonfe;
    }

    public void setCodigonfe(OffNfcePdvNotas codigonfe) {
        this.codigonfe = codigonfe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigonfetotais != null ? codigonfetotais.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OffNfeIdeTotais)) {
            return false;
        }
        OffNfeIdeTotais other = (OffNfeIdeTotais) object;
        if ((this.codigonfetotais == null && other.codigonfetotais != null) || (this.codigonfetotais != null && !this.codigonfetotais.equals(other.codigonfetotais))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.java.com.fincatto.documentofiscal.OffNfeIdeTotais[ codigonfetotais=" + codigonfetotais + " ]";
    }
    
}
