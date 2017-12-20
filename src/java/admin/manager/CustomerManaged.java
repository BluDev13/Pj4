package admin.manager;

import entities.Customers;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import manager.SessionManaged;
import model.CustomersFacade;

@ManagedBean(name = "customerAdminManaged")
@RequestScoped
public class CustomerManaged {

    @EJB
    private CustomersFacade customersFacade;
    private Customers customer;

    public CustomerManaged() {
        if (customer == null) {
            customer = new Customers();
        }
    }

    public List<Customers> getList() {
        return customersFacade.findAll();
    }
    
    public List<Customers> getListTiemNang() {
        return customersFacade.findTiemNang();
    }

    public List<Customers> getListPotential(){
        return customersFacade.findPotential();
    }
    
    public List<Customers> getListAdmin(){
        return customersFacade.findAdmin();
    }
    
    public Customers getCustomer() {
        return customer;
    }

    public void setCustomer(Customers customer) {
        this.customer = customer;
    }

//    public String login() {
//        if (customersFacade.checkLogin(customer.getUsername(), customer.getPassword(), "Admin") || customersFacade.checkLogin(customer.getUsername(), customer.getPassword(), "SysAdmin")) {
//            SessionManaged.getSession().setAttribute("adminuser", customer.getUsername());
//            return "index";
//        }
//        SessionManaged.getRequest().setAttribute("message", "Incorrect Username or password");
//        return null;
//    }

    public String deleteCustomer(Customers cate) {
        customersFacade.remove(cate);
        SessionManaged.getRequest().setAttribute("message", "Successful");
        SessionManaged.getRequest().setAttribute("messageDetails", "Delete customer successful");
        return "";
    }

    public String details(int id) {
        customer = customersFacade.find(id);
        return "ChiTietThanhVien";
    }

    public String setRole(String role, int id) {
        Customers roleCustomer = customersFacade.find(id);
        roleCustomer.setCustomerRole(role);
        customersFacade.edit(roleCustomer);
        return "";
    }
    
    //select count(*) from Customers where Reg_Date >= CONVERT(DATETIME, '08/30/2011') and  Reg_Date < DATEADD(day,1,(CONVERT(DATETIME, '08/30/2011')))
    
}
