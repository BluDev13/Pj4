package manager;

import entities.Customers;
import entities.OrderDetails;
import entities.Orders;
import entities.ProductCarts;
import entities.Products;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import java.util.Map.Entry;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import model.CustomersFacade;
import model.OrderDetailsFacade;
import model.OrdersFacade;
import model.ProductsFacade;

@ManagedBean
@SessionScoped
public class OrderManaged implements Serializable {

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

    public OrderManaged() {
        if (order == null) {
            order = new Orders();
        }
        if (orderDetails == null) {
            orderDetails = new OrderDetails();
        }
        if(customer == null){
            customer = new Customers();
        }
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }
    
    public String checkout(){
        customer = customerFacade.getByName(SessionManaged.getSession().getAttribute("username").toString());
        order = new Orders();
        HttpSession session = SessionManaged.getSession();
        
        CartManaged cart = new CartManaged();
        for (Entry<Integer, ProductCarts> item : cart.getCart().entrySet()) {
            Products product = productFacade.find(item.getKey());
            if(product.getQuantity() < item.getValue().getQuantity()){
                SessionManaged.getRequest().setAttribute("messages", "The product you buy is not sufficient quantity. Please update your cart");
                return "";
            }
        }
        
        if(customer.getUsername() != null){
            order.setOrderName(customer.getCustomerName());
            order.setOrderMobile(customer.getCustomerPhone());
            order.setOrderAddress(customer.getCustomerAddress());
            return "ThanhToan";
        }
        return "";
    }

    public String order() {
        int orderID;
        HttpSession session = SessionManaged.getSession();
        order.setOrderID(0);
        order.setCustomerID(customerFacade.getByName(session.getAttribute("username").toString()));
        order.setOrderDate(Calendar.getInstance().getTime());
        order.setOrderStatus(new Short("0"));
        orderID = orderFacede.createOrders(order);

        CartManaged cart = new CartManaged();
        for (Entry<Integer, ProductCarts> item : cart.getCart().entrySet()) {
            Products product = productFacade.find(item.getKey());
            if(product.getQuantity() < item.getValue().getQuantity()){
                SessionManaged.getRequest().setAttribute("messages", "The product you buy is not sufficient quantity. Please update your cart");
                return "";
            }
            int quantity = product.getQuantity() - item.getValue().getQuantity();
            product.setQuantity(quantity);
            product.setOrderCount(product.getOrderCount() + 1);
            productFacade.edit(product);
            orderDetails = new OrderDetails();
            orderDetails.setOrderID(orderFacede.find(orderID));
            orderDetails.setProductID(product);
            orderDetails.setQuantity((short) item.getValue().getQuantity());
            orderDetailFacade.create(orderDetails);
        }
        session.removeAttribute("CART");
        order = new Orders();
        orderDetails = new OrderDetails();
        return "index";
    }
    
    public List<Orders> getList() {
        return orderFacede.findAll();
    }
}
