package model;

import entities.Customers;
import entities.Orders;
import java.util.Calendar;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import manager.SessionManaged;

@Stateless
public class OrdersFacade extends AbstractFacade<Orders> {
    @PersistenceContext(unitName = "Project_Team4PU")
    private EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public OrdersFacade() {
        super(Orders.class);
    }

    public int createOrders(Orders entity) {
        if (!constraintValidationsDetected(entity)) {
            getEntityManager().persist(entity);
            getEntityManager().flush();
            getEntityManager().refresh(entity);
        }
        return entity.getOrderID();
    }
    
    public List<Orders> getListByUserID(Customers id) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Orders.class);
        cq.select(c);
        cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get("orderID")));
        cq.where(cb.equal(c.get("customerID"), id)
        );
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }
    
    public Long orderInYear(){
        long count = 0;
        Query q = em.createNativeQuery("SELECT COUNT(*) FROM Orders WHERE orderDate > CONVERT (DATETIME, ?) AND orderDate < CONVERT(DATETIME, ?)");
        q.setParameter(1, "01/01/" + Calendar.getInstance().get(Calendar.YEAR));
        q.setParameter(2, "12/30/" + Calendar.getInstance().get(Calendar.YEAR));
        try {
            count = (long) q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            count = 0;
        }
        return count;
    }
    
    public Long orderInMonth(){
        long count = 0;
        Query q = em.createNativeQuery("SELECT COUNT(*) FROM Orders a WHERE a.orderDate > CONVERT(DATETIME, ?) AND a.orderDate < CONVERT(DATETIME, ?)");
        q.setParameter(1, Calendar.getInstance().get(Calendar.MONTH) + 1 + "/01/" + Calendar.getInstance().get(Calendar.YEAR));
        q.setParameter(2, Calendar.getInstance().get(Calendar.MONTH) + 1 + "/30/" + Calendar.getInstance().get(Calendar.YEAR));
        try {
            count = (long) q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            count = 0;
        }
        return count;
    }
    public Long TotalOrderMonth(int month,int year){
        long totalPrice = 0;
        Query q = em.createNativeQuery("SELECT sum(b.Quantity*c.ProductPrice)" +
"                                       FROM Orders a join OrderDetails b on a.OrderID = b.OrderID join Products c on c.ProductID = b.ProductID" +
"                                       WHERE a.orderDate >= CONVERT(DATETIME, ?) AND a.orderDate < CONVERT(DATETIME, ?) and a.OrderStatus = 2");
        q.setParameter(1, month+"/01/" + year);
        q.setParameter(2, month+1+"/01/" + year);
        try {
            totalPrice = (long) q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            totalPrice = 0;
        }
        return totalPrice;
    }
//    
    public Long TotalOrderT12(int year){
        long totalPrice = 0;
        Query q = em.createNativeQuery("SELECT sum(b.Quantity*c.ProductPrice)" +
"                                       FROM Orders a join OrderDetails b on a.OrderID = b.OrderID join Products c on c.ProductID = b.ProductID" +
"                                       WHERE a.orderDate >= CONVERT(DATETIME, ?) AND a.orderDate < CONVERT(DATETIME, ?) and a.OrderStatus = 2");
        q.setParameter(1, "12/01/" + year);
        q.setParameter(2, "01/01/" + (year + 1));
        try {
            totalPrice = (long) q.getSingleResult();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            totalPrice = 0;
        }
        return totalPrice;
    }
    public Long orderCancel(){
        long count = 0;
        Query q = em.createQuery("SELECT COUNT(a) FROM Orders a WHERE a.orderStatus = -1");
        try {
            count = (Long) q.getSingleResult();
        } catch (Exception e) {
            count = 0;
        }
        return count;
    }
    
    public Long orderNewOrder(){
        long count = 0;
        Query q = em.createQuery("SELECT COUNT(a) FROM Orders a WHERE a.orderStatus = 0");
        try {
            count = (Long) q.getSingleResult();
        } catch (Exception e) {
            count = 0;
        }
        return count;
    }
    
    public Long orderProgress(){
        long count = 0;
        Query q = em.createQuery("SELECT COUNT(a) FROM Orders a WHERE a.orderStatus = 1");
        try {
            count = (Long) q.getSingleResult();
        } catch (Exception e) {
            count = 0;
        }
        return count;
    }
    
    public Long orderComplete(){
        long count = 0;
        Query q = em.createQuery("SELECT COUNT(a) FROM Orders a WHERE a.orderStatus = 2");
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
