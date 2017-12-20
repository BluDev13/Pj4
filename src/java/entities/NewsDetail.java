
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "NewsDetail")
@XmlRootElement
public class NewsDetail implements Serializable {
    @Size(max = 1073741823)
    @Column(name = "NewsDescription")
    private String newsDescription;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NewsID")
    private Integer newsID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "NewsTitle")
    private String newsTitle;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1073741823)
    @Column(name = "NewsContent")
    private String newsContent;
    @Size(max = 200)
    @Column(name = "NewsImage")
    private String newsImage;
    @Basic(optional = false)
    @NotNull
    @Column(name = "NewsDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date newsDate;
    @Column(name = "Status")
    private Short status;
    @JoinColumn(name = "NewsCateID", referencedColumnName = "NewsCateID")
    @ManyToOne(optional = false)
    private NewsCate newsCateID;

    public NewsDetail() {
    }

    public NewsDetail(Integer newsID) {
        this.newsID = newsID;
    }

    public NewsDetail(Integer newsID, String newsTitle, String newsContent, Date newsDate) {
        this.newsID = newsID;
        this.newsTitle = newsTitle;
        this.newsContent = newsContent;
        this.newsDate = newsDate;
    }

    public Integer getNewsID() {
        return newsID;
    }

    public void setNewsID(Integer newsID) {
        this.newsID = newsID;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public NewsCate getNewsCateID() {
        return newsCateID;
    }

    public void setNewsCateID(NewsCate newsCateID) {
        this.newsCateID = newsCateID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (newsID != null ? newsID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof NewsDetail)) {
            return false;
        }
        NewsDetail other = (NewsDetail) object;
        return (this.newsID != null || other.newsID == null) && (this.newsID == null || this.newsID.equals(other.newsID));
    }

    @Override
    public String toString() {
        return "entities.NewsDetail[ newsID=" + newsID + " ]";
    }

    public void setNewsCateID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNewsDescription() {
        return newsDescription;
    }

    public void setNewsDescription(String newsDescription) {
        this.newsDescription = newsDescription;
    }
    
}
