package admin.manager;

import entities.Brands;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import manager.SessionManaged;
import model.BrandsFacade;

@ManagedBean(name = "brandAdminManaged")
@RequestScoped
public class BrandManaged {

    @EJB
    private BrandsFacade brandsFacade;
    private Brands brand;
    
    public BrandManaged() {
        if(brand == null){
            brand = new Brands();
        }
    }

    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }
    
    public List<Brands> getList(){
        return brandsFacade.findAll();
    }

    public String addBrand() {
        brandsFacade.create(brand);
        return "ListNhanHieu";
    }
    
    public String preUpdate(int id){
        brand = brandsFacade.find(id);
        SessionManaged.getRequest().setAttribute("message", "Success");
        SessionManaged.getRequest().setAttribute("messageDetails", "Add new brands successful");
        return "EditNhanHieu";
    }

    
    public String updateBrand() {
        brandsFacade.edit(brand);
        SessionManaged.getRequest().setAttribute("message", "Success");
        SessionManaged.getRequest().setAttribute("messageDetails", "Edit brands successful");
        return "ListNhanHieu";
    }
    
    public String deleteBrand(Brands brand){
        brandsFacade.remove(brand);
        SessionManaged.getRequest().setAttribute("message", "Success");
        SessionManaged.getRequest().setAttribute("messageDetails", "Delete brand successful");
        return "ListNhanHieu";
    }
    
    public String details(int id){
        brand = brandsFacade.find(id);
        return "ChiTietNhanHieu";
    }
    
}
