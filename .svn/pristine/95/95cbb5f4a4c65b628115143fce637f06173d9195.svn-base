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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "Products")
@XmlRootElement
public class Products implements Serializable {
    @Column(name = "Status")
    private Short status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private Collection<Comments> commentsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productID")
    private Collection<OrderDetails> orderDetailsCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProductID")
    private Integer productID;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "ProductName")
    private String productName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ProductPrice")
    private long productPrice;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "ProductDetails")
    private String productDetails;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Quantity")
    private int quantity;
    @Size(max = 200)
    @Column(name = "ThumbImage")
    private String thumbImage;
    @Column(name = "OrderCount")
    private Integer orderCount;
    @JoinColumn(name = "CategoryID", referencedColumnName = "CategoryID")
    @ManyToOne(optional = false)
    private Category categoryID;
    @JoinColumn(name = "BrandID", referencedColumnName = "BrandID")
    @ManyToOne(optional = false)
    private Brands brandID;

    public Products() {
    }

    public Products(Integer productID) {
        this.productID = productID;
    }

    public Products(Integer productID, String productName, long productPrice, String productDetails, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDetails = productDetails;
        this.quantity = quantity;
    }

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(long productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getThumbImage() {
        return thumbImage;
    }

    public void setThumbImage(String thumbImage) {
        this.thumbImage = thumbImage;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Category getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Category categoryID) {
        this.categoryID = categoryID;
    }

    public Brands getBrandID() {
        return brandID;
    }

    public void setBrandID(Brands brandID) {
        this.brandID = brandID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (productID != null ? productID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        return (this.productID != null || other.productID == null) && (this.productID == null || this.productID.equals(other.productID));
    }

    @Override
    public String toString() {
        return "entities.Products[ productID=" + productID + " ]";
    }

    @XmlTransient
    public Collection<OrderDetails> getOrderDetailsCollection() {
        return orderDetailsCollection;
    }

    public void setOrderDetailsCollection(Collection<OrderDetails> orderDetailsCollection) {
        this.orderDetailsCollection = orderDetailsCollection;
    }

    @XmlTransient
    public Collection<Comments> getCommentsCollection() {
        return commentsCollection;
    }

    public void setCommentsCollection(Collection<Comments> commentsCollection) {
        this.commentsCollection = commentsCollection;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

}
