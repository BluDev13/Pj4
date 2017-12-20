
package model;

import entities.Contact;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class ContactFacade extends AbstractFacade<Contact> {
    @PersistenceContext(unitName = "Project_Team4PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ContactFacade() {
        super(Contact.class);
    }
    
    @PreDestroy
    public void destroy() {
        em.close();
    }
}
