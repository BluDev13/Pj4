package manager;

import entities.Comments;
import entities.Customers;
import entities.Products;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.CommentsFacade;
import model.CustomersFacade;

@ManagedBean
@RequestScoped
public class CommentManaged {

    @EJB
    private CustomersFacade customersFacade;
    @EJB
    private CommentsFacade commentsFacade;

    private Comments comment;

    public CommentManaged() {
        if (comment == null) {
            comment = new Comments();
        }
    }

    public Comments getComment() {
        return comment;
    }

    public void setComment(Comments comment) {
        this.comment = comment;
    }

    public String addComment(String customerName, Products productID) {
        Customers customerID = customersFacade.getByName(customerName);
        comment.setCustomerID(customerID);
        comment.setProductID(productID);
        commentsFacade.create(comment);
        comment = null;
        return "";
    }
    public Integer totalRaing(int productId){
        return commentsFacade.getRating(productId);
    }
    public List<Comments> getList(int id) {
        return commentsFacade.findOnProduct(id);
    }

}
