package manager;

import entities.Customers;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import model.CustomersFacade;

@ManagedBean
@SessionScoped
public class CustomerManaged implements Serializable {

    @EJB
    private CustomersFacade customersFacade;

    private Customers customer;
    private String repass;

    public Customers getCustomer() {
        return customer;
    }

    public CustomerManaged() {
        if (customer == null) {
            customer = new Customers();
        }
    }

    public String getRepass() {
        return repass;
    }

    public void setRepass(String repass) {
        this.repass = repass;
    }

    public String login() {
        if (customersFacade.checkLogin(customer.getUsername(), customer.getPassword(), "User")) {
            HttpSession session = SessionManaged.getSession();
            session.setAttribute("username", customer.getUsername());
            return "index";
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
        if (customersFacade.createCustomer(customer)) {
            return "index";
        }
        SessionManaged.getRequest().setAttribute("message", "Username can not be used");
        return "";
    }

    public String logout() {
        SessionManaged.getSession().invalidate();
        return "index";
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
