package admin.manager;

import entities.Contact;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.ContactFacade;

@ManagedBean(name = "contactAdminManaged")
@RequestScoped
public class ContactManaged {

    @EJB
    private ContactFacade contactFacade;
    private Contact contact;

    public ContactManaged() {
        if (contact == null) {
            contact = new Contact();
        }
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public List<Contact> getList(){
        return contactFacade.findAll();
    }
    
    public String remove(Contact con){
        contactFacade.remove(con);
        return "ListLienHe";
    }
    
    public String contactDetails(int id){
        contact = contactFacade.find(id);
        return "ChiTietLienHe";
    }
}
