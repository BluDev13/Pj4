
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Customers")
@XmlRootElement
public class Customers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private Integer customerID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "Username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "Password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "CustomerName")
    private String customerName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "CustomerAddress")
    private String customerAddress;
    @Size(max = 100)
    @Column(name = "CustomerEmail")
    private String customerEmail;
    @Size(max = 10)
    @Column(name = "CustomerRole")
    private String customerRole;
    @Size(max = 20)
    @Column(name = "CustomerPhone")
    private String customerPhone;
    @Column(name = "Status")
    private Short status;
    @Column(name = "Reg_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;
    @Size(max = 10)
    @Column(name = "Sex")
    private String sex;
    @Column(name = "Birthday")
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerID")
    private Collection<Comments> commentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerID")
    private Collection<Orders> ordersCollection;

    public Customers() {
    }

    public Customers(Integer customerID) {
        this.customerID = customerID;
    }

    public Customers(Integer customerID, String username, String password, String customerName, String customerAddress) {
        this.customerID = customerID;
        this.username = username;
        this.password = password;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }



    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }



    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerRole() {
        return customerRole;
    }

    public void setCustomerRole(String customerRole) {
        this.customerRole = customerRole;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    @XmlTransient
    public Collection<Orders> getOrdersCollection() {
        return ordersCollection;
    }

    public void setOrdersCollection(Collection<Orders> ordersCollection) {
        this.ordersCollection = ordersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerID != null ? customerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Customers)) {
            return false;
        }
        Customers other = (Customers) object;
        return (this.customerID != null || other.customerID == null) && (this.customerID == null || this.customerID.equals(other.customerID));
    }

    @Override
    public String toString() {
        return "entities.Customers[ customerID=" + customerID + " ]";
    }

    
    
}
