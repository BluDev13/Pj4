package manager;

import entities.Customers;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import model.CustomersFacade;

@ManagedBean
@SessionScoped
public class CustomerManaged implements Serializable {

    @EJB
    private CustomersFacade customersFacade;

    private Customers customer;
    private String repass;
    private String date;
    public Customers getCustomer() {
        return customer;
    }

    public CustomerManaged() {
        if (customer == null) {
            customer = new Customers();
        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

 

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public String login() {
        if (customersFacade.checkLogin(customer.getUsername(), customer.getPassword())) {
            HttpSession session = SessionManaged.getSession();
            session.setAttribute("username", customer.getUsername());
            return "index";
        }
        if (customersFacade.checkLoginAdmin(customer.getUsername(), customer.getPassword())) {
            SessionManaged.getSession().setAttribute("adminuser", customer.getUsername());
            try {
               ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
               ec.redirect(ec.getRequestContextPath() + "/" + "admin/index.xhtml"); 
            } catch (Exception e) {
            }
            
        }
        SessionManaged.getRequest().setAttribute("message", "Username or password is incorrect");
        return "";
    }

    public String register() {
        if (!repass.equals(customer.getPassword())) {
             SessionManaged.getRequest().setAttribute("message", "Re password not match");
             return "";
        }
        customer.setCustomerRole("User");
        try {
           SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd");
            Date regDate = sdm.parse(date); 
            customer.setRegDate(regDate);
        } catch (Exception e) {
        }
        
        
        customer.setBirthday(Calendar.getInstance().getTime());
        if (customersFacade.createCustomer(customer)) {
            return "index";
        }
        SessionManaged.getRequest().setAttribute("message", "Username can not be used");
        return "";
    }

    public String logout() {
        SessionManaged.getSession().invalidate();
        return "/index";
    }

    public Customers findUserByName(String username) {
        return customersFacade.getByName(username);
    }

    public String preUpdate(int id) {
        customer = customersFacade.find(id);
        return "SuaThongTin";
    }

    public String update() {
        customersFacade.edit(customer);
        return "ThongTin";
    }

}
