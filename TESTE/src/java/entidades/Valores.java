package entidades;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Fernando
 */
@Entity
@Table(name = "valores")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Valores.findAll", query = "SELECT v FROM Valores v"),
    @NamedQuery(name = "Valores.findById", query = "SELECT v FROM Valores v WHERE v.id = :id"),
    @NamedQuery(name = "Valores.findByPdv", query = "SELECT v FROM Valores v WHERE v.pdv = :pdv"),
    @NamedQuery(name = "Valores.findByRet", query = "SELECT v FROM Valores v WHERE v.ret = :ret"),
    @NamedQuery(name = "Valores.findByPre", query = "SELECT v FROM Valores v WHERE v.pre = :pre"),
    @NamedQuery(name = "Valores.findByDescricao", query = "SELECT v FROM Valores v WHERE v.descricao = :descricao")})
public class Valores implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    //@NotNull
    @Column(name = "id", nullable = true)
    private Integer id;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "pdv")
    private Double pdv;
    @Column(name = "ret")
    private Double ret;
    @Column(name = "pre")
    private Double pre;
    //@Size(max = 50)
    @Column(name = "descricao",  length = 50)
    private String descricao;

    public Valores() {
    }

    public Valores(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPdv() {
        return pdv;
    }

    public void setPdv(Double pdv) {
        this.pdv = pdv;
    }

    public Double getRet() {
        return ret;
    }

    public void setRet(Double ret) {
        this.ret = ret;
    }

    public Double getPre() {
        return pre;
    }

    public void setPre(Double pre) {
        this.pre = pre;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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
        if (!(object instanceof Valores)) {
            return false;
        }
        Valores other = (Valores) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.entidades.Valores[ id=" + id + " ]";
    }
    
}
