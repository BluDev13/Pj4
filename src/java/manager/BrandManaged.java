package manager;

import entities.Brands;
import entities.Products;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.BrandsFacade;

@ManagedBean
@SessionScoped
public class BrandManaged implements Serializable {

    @EJB
    private BrandsFacade brandsFacade;
    private Brands brands;
    private List<Products> listProduct;

    public BrandManaged() {
        if (brands == null) {
            brands = new Brands();
        }
    }

    public List<Products> getListProduct() {
        return listProduct;
    }

    public void setListProduct(List<Products> listProduct) {
        this.listProduct = listProduct;
    }

    public List<Brands> getList() {
        return brandsFacade.findAll();
    }

    public Brands getBrands() {
        return brands;
    }

    public void setBrands(Brands brands) {
        this.brands = brands;
    }

    public String showBrand() {
        int id;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            id = Integer.parseInt(params.get("brandID"));
        } catch (NumberFormatException e) {
            return "404";
        }
        
        if (id <= 0) {
            return "index";
        } else {
            brands = brandsFacade.find(id);
            return "Brands";
        }
    }
}
