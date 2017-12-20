
package admin.manager;

import entities.Customers;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import manager.SessionManaged;
import model.CustomersFacade;

@ManagedBean(name = "changePassAdmin")
@SessionScoped
public class ChangePass implements Serializable{

    @EJB
    private CustomersFacade customersFacade;
    private String currentPass, newPass, confirmPass;
    private Customers customer;

    public ChangePass() {
        if(customer == null){
            customer = new Customers();
        }
    }

    public String getCurrentPass() {
        return currentPass;
    }

    public void setCurrentPass(String currentPass) {
        this.currentPass = currentPass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }
    
    public String preUpdate(String user){
        customer = customersFacade.getByName(user);
        return "EditMatKhau";
    }
    
    public String update(){
        if(newPass.equals(currentPass) && currentPass.equals(confirmPass)){
            SessionManaged.getRequest().setAttribute("message", "New password must be different");
            return "";
        }
        if(newPass.equals(confirmPass) && currentPass.equals(customer.getPassword())){
            customer.setPassword(newPass);
            customersFacade.edit(customer);
            return "index";
        } else {
            SessionManaged.getRequest().setAttribute("message", "Re password not match");
        }
        return "";
    }
    
    public void validateOldPass(FacesContext ctx, UIComponent comp, Object value) {
        String pass = (String) value;
        List<FacesMessage> msgs = new ArrayList<>();
        if (!pass.equals(customer.getPassword())) {
            msgs.add(new FacesMessage("Old password not true"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }
}
