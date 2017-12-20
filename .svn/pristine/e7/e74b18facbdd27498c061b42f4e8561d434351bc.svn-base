package manager;

import entities.Contact;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.ContactFacade;

@ManagedBean
@RequestScoped
public class ContactManaged {

    @EJB
    private ContactFacade contactFacade;
    private Contact contact;
    
    public ContactManaged() {
        if(contact == null){
            contact = new Contact();
        }
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
    
    public String addContact(){
        contactFacade.create(contact);
        return "index";
    }
}
