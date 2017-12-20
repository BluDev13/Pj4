package admin.manager;

import entities.OrderDetails;
import entities.Orders;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.OrderDetailsFacade;
import model.OrdersFacade;
import model.ProductsFacade;

@ManagedBean(name = "orderAdminManaged")
@SessionScoped
public class OrderManaged implements Serializable {

    @EJB
    private ProductsFacade productsFacade;
    @EJB
    private OrderDetailsFacade orderDetailsFacade;
    @EJB
    private OrdersFacade ordersFacade;

    private Orders order;

    public OrderManaged() {
        if (order == null) {
            order = new Orders();
        }
    }

    public Orders getOrder() {
        return order;
    }

    public void setOrder(Orders order) {
        this.order = order;
    }

    public List<Orders> getList() {
        return ordersFacade.findAll();
    }

    public String viewDetails(int id) {
        order = ordersFacade.find(id);
        return "ChiTietHoaDon";
    }

    public String remove(Orders order) {
//        int quantity;
//        for (OrderDetails item : order.getOrderDetailsCollection()) {
//            quantity = item.getProductID().getQuantity() + item.getQuantity();
//            item.getProductID().setQuantity((short) quantity);
//            productsFacade.edit(item.getProductID());
//        }
        ordersFacade.remove(order);
        return "";
    }

    public String changeStatus(int id, String status) {
        Orders or = ordersFacade.find(id);
        int quantity;
        if (or != null) {
            if (status.equals("-1")) {
                for (OrderDetails item : or.getOrderDetailsCollection()) {
                    quantity = item.getProductID().getQuantity() + item.getQuantity();
                    item.getProductID().setQuantity((short) quantity);
                    productsFacade.edit(item.getProductID());
                }
            }
            or.setOrderStatus(new Short(status));
            ordersFacade.edit(or);
            order = ordersFacade.find(id);
        }
        return "";
    }

    public String thanhtoan(boolean bit) {
        ordersFacade.edit(order);
        return "ListHoaDon";
    }
}
