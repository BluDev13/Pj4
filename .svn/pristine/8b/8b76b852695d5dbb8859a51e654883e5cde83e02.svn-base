package model;

import entities.NewsCate;
import entities.NewsDetail;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class NewsCateFacade extends AbstractFacade<NewsCate> {

    @PersistenceContext(unitName = "Project_Team4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewsCateFacade() {
        super(NewsCate.class);
    }

    public List<NewsDetail> findPaging(int newsCateID, int[] range) {
        Query q;
        if (newsCateID != -1) {
            NewsCate newscate = find(newsCateID);
            if (newscate == null) {
                return null;
            }
            
            q = em.createQuery("SELECT a FROM NewsDetail a WHERE a.newsCateID.newsCateID = :newsCateID");
            q.setParameter("newsCateID", newsCateID);
        } else {
            q = em.createQuery("SELECT a FROM NewsDetail a");
        }
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<NewsDetail> findPagingSize(int newsCateID, int[] range) {
        Query q;
        if (newsCateID != -1) {
            NewsCate newscate = find(newsCateID);
            if (newscate == null) {
                return null;
            }
            
            q = em.createQuery("SELECT a FROM NewsDetail a WHERE a.newsCateID.newsCateID = :newsCateID");
            q.setParameter("newsCateID", newsCateID);
        } else {
            q = em.createQuery("SELECT a FROM NewsDetail a");
        }
        return q.getResultList();
    }

}
