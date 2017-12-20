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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Orders")
@XmlRootElement
public class Orders implements Serializable {
    @Column(name = "OrderStatus")
    private Short orderStatus;
    @Column(name = "Status")
    private Short status;
    @Basic(optional = false)
    @Size(min = 0, max = 100)
    @Column(columnDefinition = "NVARCHAR(100) NULL", name = "OrderName", nullable = true)
    private String orderName;
    
    @Basic(optional = false)
    @Size(min = 0, max = 200)
    @Column(columnDefinition = "NVARCHAR(200) NULL", name = "OrderAddress", nullable = true)
    private String orderAddress;
    
    @Basic(optional = false)
    @Size(min = 0, max = 12)
    @Column(name = "OrderMobile", nullable = true)
    private String orderMobile;
    
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderID")
    private Integer orderID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "OrderDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "OrderID")
    private Collection<OrderDetails> orderDetailsCollection;
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    @ManyToOne(optional = false)
    private Customers customerID;

    public Orders() {
    }

    public Orders(Integer orderID) {
        this.orderID = orderID;
    }

    public Orders(Integer orderID, Date orderDate) {
        this.orderID = orderID;
        this.orderDate = orderDate;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @XmlTransient
    public Collection<OrderDetails> getOrderDetailsCollection() {
        return orderDetailsCollection;
    }

    public void setOrderDetailsCollection(Collection<OrderDetails> orderDetailsCollection) {
        this.orderDetailsCollection = orderDetailsCollection;
    }

    public Customers getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customers customerID) {
        this.customerID = customerID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderID != null ? orderID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        return (this.orderID != null || other.orderID == null) && (this.orderID == null || this.orderID.equals(other.orderID));
    }

    @Override
    public String toString() {
        return "entities.Orders[ orderID=" + orderID + " ]";
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getOrderAddress() {
        return orderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        this.orderAddress = orderAddress;
    }

    public String getOrderMobile() {
        return orderMobile;
    }

    public void setOrderMobile(String orderMobile) {
        this.orderMobile = orderMobile;
    }

    public Short getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Short orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

}
