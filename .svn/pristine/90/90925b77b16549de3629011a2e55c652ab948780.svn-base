
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Contact")
@XmlRootElement
public class Contact implements Serializable {
    @Column(name = "Status")
    private Short status;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ContactID")
    private Integer contactID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ContactName")
    private String contactName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "ContactMail")
    private String contactMail;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1073741823)
    @Column(name = "ContactDetails")
    private String contactDetails;

    public Contact() {
    }

    public Contact(Integer contactID) {
        this.contactID = contactID;
    }

    public Contact(Integer contactID, String contactName, String contactMail, String contactDetails) {
        this.contactID = contactID;
        this.contactName = contactName;
        this.contactMail = contactMail;
        this.contactDetails = contactDetails;
    }

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMail() {
        return contactMail;
    }

    public void setContactMail(String contactMail) {
        this.contactMail = contactMail;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contactID != null ? contactID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Contact)) {
            return false;
        }
        Contact other = (Contact) object;
        return (this.contactID != null || other.contactID == null) && (this.contactID == null || this.contactID.equals(other.contactID));
    }

    @Override
    public String toString() {
        return "entities.Contact[ contactID=" + contactID + " ]";
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
    
}
