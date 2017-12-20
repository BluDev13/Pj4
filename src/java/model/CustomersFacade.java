package model;

import entities.Customers;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

@Stateless
public class CustomersFacade extends AbstractFacade<Customers> {

    @PersistenceContext(unitName = "Project_Team4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CustomersFacade() {
        super(Customers.class);
    }

    public boolean checkLogin(String username, String password) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Customers.class);
        cq.select(c);
        cq.where(cb.equal(c.get("username"), username),
                cb.equal(c.get("password"), password),
                cb.equal(c.get("customerRole"), "User")
        );
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList().size() > 0;
    }
    public boolean checkLoginAdmin(String username, String password) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Customers.class);
        cq.select(c);
        cq.where(cb.equal(c.get("username"), username),
                cb.equal(c.get("password"), password),
                cb.equal(c.get("customerRole"), "Admin")
        );
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList().size() > 0;
    }

    public boolean createCustomer(Customers entity) {
        if (findUser(entity.getUsername())) {
            return false;
        }
        if (!super.constraintValidationsDetected(entity)) {
            getEntityManager().persist(entity);
            return true;
        }
        return false;
    }

    public boolean findUser(String userName) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Customers.class);
        cq.select(c);
        cq.where(cb.equal(c.get("username"), userName)
        );
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList().size() > 0;
    }

    public Customers getByName(String name) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Customers.class);
        cq.select(c);
        cq.where(cb.equal(c.get("username"), name)
        );
        Query q = getEntityManager().createQuery(cq);
        Customers cus;
        try {
            cus = (Customers) q.getSingleResult();
        } catch (Exception e) {
            cus = null;
        }
        return cus;
    }

    public List<Customers> findTiemNang() {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(Customers.class);
        cq.select(c);
        cq.orderBy(getEntityManager().getCriteriaBuilder().desc(c.get("ordersCollection").get("size")));
        cq.where(cb.gt(c.get("quantity"), 0));
        Query q = getEntityManager().createQuery(cq);
        q.setMaxResults(10);
        return q.getResultList();
    }
    
    public List<Customers> findAdmin(){
        Query q = em.createQuery("SELECT a FROM Customers a WHERE a.customerRole = 'Admin' OR a.customerRole = 'SysAdmin'");
        return q.getResultList();
    }
    
    public List<Customers> findPotential() {
        Query q = em.createQuery("SELECT a FROM Customers a WHERE size(a.ordersCollection) > 3");
        return q.getResultList();
    }
    
    @PreDestroy
    public void destroy() {
        em.close();
    }
}
