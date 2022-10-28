/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main.java.com.fincatto.documentofiscal;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "OFF_NFE_DOC_FISCAL_REF")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OffNfeDocFiscalRef.findAll", query = "SELECT o FROM OffNfeDocFiscalRef o"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByCodigonfedocref", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.codigonfedocref = :codigonfedocref"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByRefnfe", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.refnfe = :refnfe"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByCuf1a", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.cuf1a = :cuf1a"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByAamm1a", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.aamm1a = :aamm1a"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByCnpj1a", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.cnpj1a = :cnpj1a"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByMod1a", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.mod1a = :mod1a"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findBySerie1a", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.serie1a = :serie1a"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByNnf1a", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.nnf1a = :nnf1a"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByCufNfp", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.cufNfp = :cufNfp"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByAammNfp", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.aammNfp = :aammNfp"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByCnpjNfp", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.cnpjNfp = :cnpjNfp"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByCpfNfp", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.cpfNfp = :cpfNfp"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByIeNfp", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.ieNfp = :ieNfp"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByModNfp", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.modNfp = :modNfp"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findBySerieNfp", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.serieNfp = :serieNfp"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByNnfNfp", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.nnfNfp = :nnfNfp"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByRefcte", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.refcte = :refcte"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByModEcf", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.modEcf = :modEcf"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByNecfEcf", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.necfEcf = :necfEcf"),
    @NamedQuery(name = "OffNfeDocFiscalRef.findByNcooEcf", query = "SELECT o FROM OffNfeDocFiscalRef o WHERE o.ncooEcf = :ncooEcf")})
public class OffNfeDocFiscalRef implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CODIGONFEDOCREF")
    private Long codigonfedocref;
    @Column(name = "REFNFE")
    private String refnfe;
    @Column(name = "CUF_1A")
    private String cuf1a;
    @Column(name = "AAMM_1A")
    private String aamm1a;
    @Column(name = "CNPJ_1A")
    private String cnpj1a;
    @Column(name = "MOD_1A")
    private String mod1a;
    @Column(name = "SERIE_1A")
    private String serie1a;
    @Column(name = "NNF_1A")
    private String nnf1a;
    @Column(name = "CUF_NFP")
    private String cufNfp;
    @Column(name = "AAMM_NFP")
    private String aammNfp;
    @Column(name = "CNPJ_NFP")
    private String cnpjNfp;
    @Column(name = "CPF_NFP")
    private String cpfNfp;
    @Column(name = "IE_NFP")
    private String ieNfp;
    @Column(name = "MOD_NFP")
    private String modNfp;
    @Column(name = "SERIE_NFP")
    private String serieNfp;
    @Column(name = "NNF_NFP")
    private String nnfNfp;
    @Column(name = "REFCTE")
    private String refcte;
    @Column(name = "MOD_ECF")
    private String modEcf;
    @Column(name = "NECF_ECF")
    private String necfEcf;
    @Column(name = "NCOO_ECF")
    private String ncooEcf;
    @JoinColumn(name = "CODIGONFE", referencedColumnName = "ID")
    @ManyToOne
    private OffNfcePdvNotas codigonfe;

    public OffNfeDocFiscalRef() {
    }

    public OffNfeDocFiscalRef(Long codigonfedocref) {
        this.codigonfedocref = codigonfedocref;
    }

    public Long getCodigonfedocref() {
        return codigonfedocref;
    }

    public void setCodigonfedocref(Long codigonfedocref) {
        this.codigonfedocref = codigonfedocref;
    }

    public String getRefnfe() {
        return refnfe;
    }

    public void setRefnfe(String refnfe) {
        this.refnfe = refnfe;
    }

    public String getCuf1a() {
        return cuf1a;
    }

    public void setCuf1a(String cuf1a) {
        this.cuf1a = cuf1a;
    }

    public String getAamm1a() {
        return aamm1a;
    }

    public void setAamm1a(String aamm1a) {
        this.aamm1a = aamm1a;
    }

    public String getCnpj1a() {
        return cnpj1a;
    }

    public void setCnpj1a(String cnpj1a) {
        this.cnpj1a = cnpj1a;
    }

    public String getMod1a() {
        return mod1a;
    }

    public void setMod1a(String mod1a) {
        this.mod1a = mod1a;
    }

    public String getSerie1a() {
        return serie1a;
    }

    public void setSerie1a(String serie1a) {
        this.serie1a = serie1a;
    }

    public String getNnf1a() {
        return nnf1a;
    }

    public void setNnf1a(String nnf1a) {
        this.nnf1a = nnf1a;
    }

    public String getCufNfp() {
        return cufNfp;
    }

    public void setCufNfp(String cufNfp) {
        this.cufNfp = cufNfp;
    }

    public String getAammNfp() {
        return aammNfp;
    }

    public void setAammNfp(String aammNfp) {
        this.aammNfp = aammNfp;
    }

    public String getCnpjNfp() {
        return cnpjNfp;
    }

    public void setCnpjNfp(String cnpjNfp) {
        this.cnpjNfp = cnpjNfp;
    }

    public String getCpfNfp() {
        return cpfNfp;
    }

    public void setCpfNfp(String cpfNfp) {
        this.cpfNfp = cpfNfp;
    }

    public String getIeNfp() {
        return ieNfp;
    }

    public void setIeNfp(String ieNfp) {
        this.ieNfp = ieNfp;
    }

    public String getModNfp() {
        return modNfp;
    }

    public void setModNfp(String modNfp) {
        this.modNfp = modNfp;
    }

    public String getSerieNfp() {
        return serieNfp;
    }

    public void setSerieNfp(String serieNfp) {
        this.serieNfp = serieNfp;
    }

    public String getNnfNfp() {
        return nnfNfp;
    }

    public void setNnfNfp(String nnfNfp) {
        this.nnfNfp = nnfNfp;
    }

    public String getRefcte() {
        return refcte;
    }

    public void setRefcte(String refcte) {
        this.refcte = refcte;
    }

    public String getModEcf() {
        return modEcf;
    }

    public void setModEcf(String modEcf) {
        this.modEcf = modEcf;
    }

    public String getNecfEcf() {
        return necfEcf;
    }

    public void setNecfEcf(String necfEcf) {
        this.necfEcf = necfEcf;
    }

    public String getNcooEcf() {
        return ncooEcf;
    }

    public void setNcooEcf(String ncooEcf) {
        this.ncooEcf = ncooEcf;
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
        hash += (codigonfedocref != null ? codigonfedocref.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OffNfeDocFiscalRef)) {
            return false;
        }
        OffNfeDocFiscalRef other = (OffNfeDocFiscalRef) object;
        if ((this.codigonfedocref == null && other.codigonfedocref != null) || (this.codigonfedocref != null && !this.codigonfedocref.equals(other.codigonfedocref))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "main.java.com.fincatto.documentofiscal.OffNfeDocFiscalRef[ codigonfedocref=" + codigonfedocref + " ]";
    }
    
}
