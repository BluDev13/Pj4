
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "NewsCate")
@XmlRootElement
public class NewsCate implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NewsCateID")
    private Integer newsCateID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NewsCateName")
    private String newsCateName;
    @Column(name = "Status")
    private Short status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "newsCateID")
    private Collection<NewsDetail> newsDetailCollection;

    public NewsCate() {
    }

    public NewsCate(Integer newsCateID) {
        this.newsCateID = newsCateID;
    }

    public NewsCate(Integer newsCateID, String newsCateName) {
        this.newsCateID = newsCateID;
        this.newsCateName = newsCateName;
    }

    public Integer getNewsCateID() {
        return newsCateID;
    }

    public void setNewsCateID(Integer newsCateID) {
        this.newsCateID = newsCateID;
    }

    public String getNewsCateName() {
        return newsCateName;
    }

    public void setNewsCateName(String newsCateName) {
        this.newsCateName = newsCateName;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<NewsDetail> getNewsDetailCollection() {
        return newsDetailCollection;
    }

    public void setNewsDetailCollection(Collection<NewsDetail> newsDetailCollection) {
        this.newsDetailCollection = newsDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (newsCateID != null ? newsCateID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof NewsCate)) {
            return false;
        }
        NewsCate other = (NewsCate) object;
        return (this.newsCateID != null || other.newsCateID == null) && (this.newsCateID == null || this.newsCateID.equals(other.newsCateID));
    }

    @Override
    public String toString() {
        return newsCateName;
    }
    
}
