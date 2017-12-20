package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "Comments")
@XmlRootElement
public class Comments implements Serializable {
    @Column(name = "Status")
    private Short status;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CommentID")
    private Integer commentID;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "CommentDetails")
    private String commentDetails;
    @Column(name = "Rating")
    private int rating;
    @JoinColumn(name = "ProductID", referencedColumnName = "ProductID")
    @ManyToOne(optional = false)
    private Products productID;
    @JoinColumn(name = "CustomerID", referencedColumnName = "CustomerID")
    @ManyToOne(optional = false)
    private Customers customerID;

    public Comments() {
    }

    public Comments(Integer commentID) {
        this.commentID = commentID;
    }

    public Comments(Integer commentID, String commentDetails, Integer rating) {
        this.commentID = commentID;
        this.commentDetails = commentDetails;
        this.rating = rating;
        
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(Integer commentID) {
        this.commentID = commentID;
    }

    public String getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(String commentDetails) {
        this.commentDetails = commentDetails;
    }

    public Products getProductID() {
        return productID;
    }

    public void setProductID(Products productID) {
        this.productID = productID;
    }

    public Customers getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Customers customerID) {
        this.customerID = customerID;
    }
    
    public void setRating(Integer rating){
        this.rating = rating;
    }
    public Integer getRating(){
        return this.rating;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentID != null ? commentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Comments)) {
            return false;
        }
        Comments other = (Comments) object;
        return (this.commentID != null || other.commentID == null) && (this.commentID == null || this.commentID.equals(other.commentID));
    }

    @Override
    public String toString() {
        return "entities.Comments[ commentID=" + commentID + " ]";
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

}
