package model;

import entities.Products;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

@Stateless
public class ProductsFacade extends AbstractFacade<Products> {

    @PersistenceContext(unitName = "Project_Team4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductsFacade() {
        super(Products.class);
    }

    @Override
    public List<Products> findAll() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Products.class);
        cq.select(c);
        cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get("productID")));
        cq.where(cb.gt(c.get("quantity"), 0));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(12);
        return q.getResultList();
    }
    
    public List<Products> findAllAdmin() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Products.class);
        cq.select(c);
        cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get("productID")));
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    public List<Products> findRelated(int categoryID) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Products.class);
        cq.select(c);
        cq.where(cb.gt(c.get("quantity"), 0),
                cb.equal(c.get("categoryID").get("categoryID"), categoryID)
        );
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(8);
        return q.getResultList();
    }

    public List<Products> findAllOrder() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Products.class);
        cq.select(c);
        cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get("orderCount")));
        cq.where(cb.gt(c.get("quantity"), 0));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(8);
        return q.getResultList();
    }
    
    public List<Products> findListBanChay() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Products.class);
        cq.select(c);
        cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get("orderCount")));
        cq.where(cb.gt(c.get("orderCount"), 0));
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
    
    public List<Products> findMonthly() {
        Query q = em.createNativeQuery("SELECT a.ProductID, a.ProductName, a.CategoryID, a.BrandID, a.ProductPrice, a.ProductDetails, a.Quantity, a.ThumbImage, a.Status, b.OrderCount FROM Products a JOIN ( SELECT TOP 5 ProductID, COUNT (ProductID) AS OrderCount FROM OrderDetails WHERE OrderID IN ( SELECT a.OrderID FROM Orders a JOIN OrderDetails b ON a.OrderID = b.OrderID WHERE a.OrderDate > CONVERT (DATETIME, ?) AND a.OrderDate < CONVERT (DATETIME, ?) ) GROUP BY ProductID ORDER BY OrderCount DESC ) AS b ON a.ProductID = B.ProductID", Products.class);
        q.setParameter(1, Calendar.getInstance().get(Calendar.MONTH) + 1 + "/01/" + Calendar.getInstance().get(Calendar.YEAR));
        q.setParameter(2, Calendar.getInstance().get(Calendar.MONTH) + 1 + "/30/" + Calendar.getInstance().get(Calendar.YEAR));
        return q.getResultList();
    }
    
    public List<Products> findSoldOut(){
        Query q = em.createQuery("SELECT a FROM Products a WHERE a.quantity <= 3");
        return q.getResultList();
    }
    
    public Long productSoldOut(){
        long count = 0;
        Query q = em.createQuery("SELECT COUNT(a) FROM Products a WHERE a.quantity = 0");
        try {
            count = (Long) q.getSingleResult();
        } catch (Exception e) {
            count = 0;
        }
        return count;
    }

    @PreDestroy
    public void destroy() {
        em.close();
    }
}
