
package model;

import entities.NewsDetail;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.Root;

@Stateless
public class NewsDetailFacade extends AbstractFacade<NewsDetail> {
    @PersistenceContext(unitName = "Project_Team4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewsDetailFacade() {
        super(NewsDetail.class);
    }
    
    @Override
    public List<NewsDetail> findAll() {
        getEntityManager().getEntityManagerFactory().getCache().evictAll();
        javax.persistence.criteria.CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root c = cq.from(NewsDetail.class);
        cq.select(c);
        cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get("newsID")));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    public List<NewsDetail> findNew() {
        Query q = getEntityManager().createQuery("SELECT a FROM NewsDetail a ORDER BY a.newsID desc");
        q.setMaxResults(8);
        return q.getResultList();
    }
}
