package admin.manager;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import model.BrandsFacade;
import model.CategoryFacade;
import model.CommentsFacade;
import model.CustomersFacade;
import model.NewsCateFacade;
import model.NewsDetailFacade;
import model.OrdersFacade;
import model.ProductsFacade;

@ManagedBean
@SessionScoped
public class SystemManaged {
    @EJB
    private CommentsFacade commentsFacade;
    @EJB
    private BrandsFacade brandsFacade;
    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private NewsDetailFacade newsDetailFacade;
    @EJB
    private NewsCateFacade newsCateFacade;
    @EJB
    private OrdersFacade ordersFacade;
    @EJB
    private ProductsFacade productsFacade;
    @EJB
    private CustomersFacade customersFacade;

    public SystemManaged() {
    }

    public int customerCount() {
        return customersFacade.count();
    }

    public int productCount() {
        return productsFacade.count();
    }

    public int orderCount() {
        return ordersFacade.count();
    }
    
    public int brandCount(){
        return brandsFacade.count();
    }
    
    public int categoryCount(){
        return categoryFacade.count();
    }
    
    public int newsCount(){
        return newsDetailFacade.count();
    }
    
    public int newsCateCount(){
        return newsCateFacade.count();
    }
    
    public int commentCount(){
        return commentsFacade.count();
    }
    
    public int orderInYear(){
        return ordersFacade.orderInYear();
    }
    
    public int orderInMonth(){
        return ordersFacade.orderInMonth();
    }
    
    public int orderTotal(){
        return ordersFacade.count();
    }
    
    public long orderCancel(){
        return ordersFacade.orderCancel();
    }
    
    public long orderNewOrder(){
        return ordersFacade.orderNewOrder();
    }
    
    public long orderProgress(){
        return ordersFacade.orderProgress();
    }
    
    public long orderComplete(){
        return ordersFacade.orderComplete();
    }
    
    public long productSoldOut(){
        return productsFacade.productSoldOut();
    }
    
    public long productTotal(){
        return productsFacade.count();
    }
}
