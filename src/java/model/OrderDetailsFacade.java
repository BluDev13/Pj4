package model;

import entities.OrderDetails;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

@Stateless
public class OrderDetailsFacade extends AbstractFacade<OrderDetails> {
    @PersistenceContext(unitName = "Project_Team4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrderDetailsFacade() {
        super(OrderDetails.class);
    }
    
    public List<OrderDetails> findOrder(int orderID) {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        javax.persistence.criteria.CriteriaQuery cq = cb.createQuery();
        Root c = cq.from(OrderDetails.class);
        cq.select(c);
        cq.where(cb.equal(c.get("ORDERID"), orderID)
        );
        Query q = getEntityManager().createQuery(cq);
        return q.getResultList();
    }

    @PreDestroy
    public void destroy() {
        em.close();
    }
}
