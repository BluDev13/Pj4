package manager;

import entities.Customers;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import model.CustomersFacade;

@ManagedBean
@RequestScoped
public class ForgotPasswordManaged {

    @EJB
    private CustomersFacade customersFacade;
    private Customers cus;

    public ForgotPasswordManaged() {
        if(cus == null){
            cus = new Customers();
        }
    }

    public Customers getCus() {
        return cus;
    }

    public void setCus(Customers cus) {
        this.cus = cus;
    }
    
    public String send(){
        cus = customersFacade.getByName(cus.getUsername());
        String link;
        if(cus != null){
            cus.setPasswordForget(SendMail.MD5(SendMail.randomString()));
            customersFacade.edit(cus);
            link = SessionManaged.getRequest().getScheme() + "://" + SessionManaged.getRequest().getServerName() + ":" + SessionManaged.getRequest().getServerPort() + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath() + "/RecoveryPassword.xhtml?username=" + cus.getUsername() + "&string=" + cus.getPasswordForget();
            SendMail.sendRequest(cus.getCustomerEmail(), cus.getUsername(), cus.getCustomerName(), link);
            SessionManaged.getRequest().setAttribute("successful", cus.getCustomerEmail());
        } else {
            SessionManaged.getRequest().setAttribute("fail", "Username not found !");
        }
        return "";
    }

    public boolean authorize(String username, String password){
        Customers customer = customersFacade.getByName(username);
        String newPass;
        if(customer != null){
            if(customer.getPasswordForget().equals(password)){
                newPass = SendMail.randomString();
                customer.setPassword(newPass);
                customer.setPasswordForget("");
                SendMail.sendPassword(customer.getCustomerEmail(), username, newPass);
                customersFacade.edit(customer);
                return true;
            }
        }
        return false;
    }
}
