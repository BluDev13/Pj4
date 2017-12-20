package model;

import entities.Products;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

@Stateless
public class SearchFacade extends AbstractFacade<Products> {

    @PersistenceContext(unitName = "Project_Team4PU")
    private EntityManager em;

    public SearchFacade() {
        super(Products.class);
    }

    public SearchFacade(Class<Products> entityClass) {
        super(entityClass);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<Products> searchProduct(String searchType, String searchValue, int[] range) {
        CriteriaBuilder cb;
        javax.persistence.criteria.CriteriaQuery cq;
        Root c;
        Query q;
        cb = getEntityManager().getCriteriaBuilder();
        cq = cb.createQuery();
        c = cq.from(Products.class);
        cq.select(c);
        cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get("productID")));
        cq.where(cb.like(c.get("productName"), "%" + searchValue + "%"));
        q = getEntityManager().createQuery(cq);
        q.setMaxResults(range[1] - range[0]);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    public List<Products> findPagingSize(String searchType, String searchValue, int[] range) {
        CriteriaBuilder cb;
        javax.persistence.criteria.CriteriaQuery cq;
        Root c;
        Query q;
        cb = getEntityManager().getCriteriaBuilder();
        cq = cb.createQuery();
        c = cq.from(Products.class);
        cq.select(c);
        cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get("productID")));
        cq.where(cb.like(c.get("productName"), "%" + searchValue + "%"));
        q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    @PreDestroy
    public void destroy() {
        em.close();
    }
}
