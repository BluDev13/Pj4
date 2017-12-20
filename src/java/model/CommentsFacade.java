package model;

import entities.Comments;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

@Stateless
public class CommentsFacade extends AbstractFacade<Comments> {

    @PersistenceContext(unitName = "Project_Team4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentsFacade() {
        super(Comments.class);
    }

    public List<Comments> findOnProduct(int id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Comments.class);
        cq.select(c);
        cq.where(
                cb.equal(c.get("productID").get("productID"), id)
        );
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
     public Integer getRating(int productId){
         CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Comments.class);
        cq.select(c);
        cq.where(
                cb.equal(c.get("productID").get("productID"), productId)
        );
        Query q = getEntityManager().createQuery(cq);
        List items = q.getResultList();
        int returnValue = 0;
        int totalRating = 0;
        for(int i = 0; i< items.size();i++){
            Comments comment = (Comments) items.get(i);
            totalRating = totalRating + comment.getRating();
        }
        returnValue = totalRating / items.size();
        return returnValue;
    }
    @PreDestroy
    public void destroy() {
        em.close();
    }
}
