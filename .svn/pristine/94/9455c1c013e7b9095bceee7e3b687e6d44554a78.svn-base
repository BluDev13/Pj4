package admin.manager;

import entities.Products;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.ProductsFacade;

@ManagedBean
@RequestScoped
public class ProductStatiticManaged {

    @EJB
    private ProductsFacade productsFacade;

    public ProductStatiticManaged() {
    }
    
    public List<Products> listMonthly(){
        return productsFacade.findMonthly();
    }
    
    public List<Products> listSoldOut(){
        return productsFacade.findSoldOut();
    }

}
