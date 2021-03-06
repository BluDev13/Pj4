package admin.manager;

import entities.Comments;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.CommentsFacade;

@ManagedBean(name = "commentAdminManaged")
@RequestScoped
public class CommentManaged {

    @EJB
    private CommentsFacade commentsFacade;
    private Comments comment;

    public CommentManaged() {
        if(comment == null){
            comment = new Comments();
        }
    }

    public Comments getComment() {
        return comment;
    }

    public void setComment(Comments comment) {
        this.comment = comment;
    }

    public List<Comments> getList(){
        return commentsFacade.findAll();
    }
    
    public String remove(Comments comment){
        commentsFacade.remove(comment);
        return "ListBinhLuan";
    }
    
    public String changeStatus(int commentID, String status){
        Comments cmt = commentsFacade.find(commentID);
        if(cmt != null){
            cmt.setStatus(new Short(status));
            commentsFacade.edit(cmt);
        }
        return "";
    }
}
