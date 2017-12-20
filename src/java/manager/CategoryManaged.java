package manager;

import classes.util.PaginationHelper;
import entities.Category;
import entities.Products;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.CategoryFacade;

@ManagedBean
@SessionScoped
public class CategoryManaged implements Serializable {

    @EJB
    private CategoryFacade categoryFacade;
    private List<Products> items = null;
    private PaginationHelper pagination;
    private int categoryID = 0;
    private Category category;
    
    public CategoryManaged() {
        if (category == null) {
            category = new Category();
        }
    }

    public List<Category> getList() {
        return categoryFacade.findAll();
    }
     
    public Category getCategory() {
        return category;
    }

    public String showCategory() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        int id;
        try {
            id = Integer.parseInt(params.get("categoryID"));
        } catch (NumberFormatException e) {
            return "404";
        }
        if (id <= 0) {
            return "index";
        } else {
            recreatePagination();
            this.categoryID = id;
            this.category = categoryFacade.find(id);
            return "ChuyenMuc";
        }
    }
  
    public List<Products> getProducts() {
        items = getPagination().getItems();
        return items;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getNameByID(String id) {
        return categoryFacade.getNameByID(Integer.parseInt(id));
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(8) {

                @Override
                public int getItemsCount() {
                    return getTotalCount();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(categoryFacade.findPaging(categoryID, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }

                public int getTotalCount(){
                    return categoryFacade.findPagingSize(categoryID, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}).size();
                }
                
                @Override
                public List<Products> getItems() {
                    return categoryFacade.findPaging(categoryID, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
                }
            };
        }
        return pagination;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }
    
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "ChuyenMuc";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "ChuyenMuc";
    }

}
