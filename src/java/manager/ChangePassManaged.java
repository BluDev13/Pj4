package manager;

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
import model.CustomersFacade;

@ManagedBean
@SessionScoped
public class ChangePassManaged implements Serializable{

    @EJB
    private CustomersFacade customersFacade;
    
    private String currentPass, newPass, confirmPass;
    private Customers customer;

    public ChangePassManaged() {
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
    
    public String preUpdate(int id){
        customer = customersFacade.find(id);
        return "DoiMatKhau";
    }
    
    public String update(){
        if(newPass.equals(currentPass) && currentPass.equals(confirmPass)){
            SessionManaged.getRequest().setAttribute("message", "New password must be different");
            return "";
        }
        if(newPass.equals(confirmPass) && currentPass.equals(customer.getPassword())){
            customer.setPassword(newPass);
            customersFacade.edit(customer);
            return "ThongTin";
        } else {
            SessionManaged.getRequest().setAttribute("message", "Password unduplicated");
            SessionManaged.getRequest().setAttribute("message", "Re password not match");
        }
        return "";
    }
    
    public void validateOldPass(FacesContext ctx, UIComponent comp, Object value) {
        String pass = (String) value;
        List<FacesMessage> msgs = new ArrayList<>();
        if (!pass.equals(customer.getPassword())) {

            msgs.add(new FacesMessage("Old password incorrect"));

            msgs.add(new FacesMessage("Old password is incorrect"));

        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }
}
