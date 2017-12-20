package manager;

import entities.Customers;
import entities.OrderDetails;
import entities.Orders;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.CustomersFacade;
import model.OrderDetailsFacade;
import model.OrdersFacade;
import model.ProductsFacade;

@ManagedBean
@SessionScoped
public class OrderDetailManaged implements Serializable {

    @EJB
    private OrdersFacade orderFacede;
    @EJB
    private CustomersFacade customerFacade;
    @EJB
    private OrderDetailsFacade orderDetailFacade;
    @EJB
    private ProductsFacade productFacade;

    private Orders order;
    private OrderDetails orderDetails;
    private Customers customer;

    public OrderDetailManaged() {
        if (order == null) {
            order = new Orders();
        }
        if (orderDetails == null) {
            orderDetails = new OrderDetails();
        }
        if (customer == null) {
            customer = new Customers();
        }
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public String cancel(int id) {
        Orders or = orderFacede.find(id);
        int quantity;
        if (or != null) {
            or.setOrderStatus(new Short("-1"));
            for (OrderDetails item : or.getOrderDetailsCollection()) {
                quantity = item.getProductID().getQuantity() + item.getQuantity();
                item.getProductID().setQuantity((short) quantity);
                productFacade.edit(item.getProductID());
            }
            orderFacede.edit(or);
            order = or;
        }
        return "";
    }

    public String continueOrder(int id) {
        Orders or = orderFacede.find(id);
        if (or != null) {
            or.setOrderStatus(new Short("0"));
            orderFacede.edit(or);
            order = or;
        }
        return "";
    }

    public String detail(int id) {
        order = orderFacede.find(id);
        if (order != null) {
            return "DetailOrder";
        }
        return "";
    }

    public List<Orders> getList() {
        return orderFacede.findAll();
    }

    public List<Orders> getListByUserID(Customers id) {
        return orderFacede.getListByUserID(id);
    }

}
