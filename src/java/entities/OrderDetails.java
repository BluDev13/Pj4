
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "OrderDetails")
@XmlRootElement
public class OrderDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity")
    private short quantity;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OrderDetailID")
    private Integer orderDetailID;
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    @ManyToOne(optional = false)
    private Products productID;
    @JoinColumn(name = "OrderID", referencedColumnName = "OrderID")
    @ManyToOne(optional = false)
    private Orders orderID;

    public OrderDetails() {
    }

    public OrderDetails(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public OrderDetails(Integer orderDetailID, short quantity) {
        this.orderDetailID = orderDetailID;
        this.quantity = quantity;
    }

    public short getQuantity() {
        return quantity;
    }

    public void setQuantity(short quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(Integer orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public Products getProductID() {
        return productID;
    }

    public void setProductID(Products productID) {
        this.productID = productID;
    }

    public Orders getOrderID() {
        return orderID;
    }

    public void setOrderID(Orders orderID) {
        this.orderID = orderID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orderDetailID != null ? orderDetailID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof OrderDetails)) {
            return false;
        }
        OrderDetails other = (OrderDetails) object;
        return (this.orderDetailID != null || other.orderDetailID == null) && (this.orderDetailID == null || this.orderDetailID.equals(other.orderDetailID));
    }

    @Override
    public String toString() {
        return "entities.OrderDetails[ orderDetailID=" + orderDetailID + " ]";
    }
    
}
