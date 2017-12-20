package manager;

import classes.util.PaginationHelper;
import entities.Products;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.CategoryFacade;

@ManagedBean
@SessionScoped
public class FilterManaged implements Serializable{
    
    @EJB
    private CategoryFacade categoryFacade;
    private String name;
    private int from;
    private int to;
    private List<Products> list = new ArrayList<>();
    private PaginationHelper pagination;
    private String sql = "";

    public FilterManaged() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public List<Products> getList() {
        if("".equals(sql)){
            return null;
        }
        return getPagination().getItems();
    }

    public void setList(List<Products> list) {
        this.list = list;
    }

    public String search() {
        sql = "SELECT a FROM Products a WHERE ";

        if (from != 0 && to != 0) {
            if (from > to) {
                SessionManaged.getRequest().setAttribute("message", "Price From must be less than Price To");
                return "";
            }
        }
        if (from != 0 && to == 0 || from == 0 && to != 0) {
            SessionManaged.getRequest().setAttribute("message", "Price From and Price To can not be empty");
            return "";
        }
        
        sql += " a.productName LIKE :name";

        if (from != 0 && to != 0) {
            sql += " AND a.productPrice > :from AND a.productPrice < :to";
        }
        recreatePagination();
        return "Filter";
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
                    return new ListDataModel(categoryFacade.filter(sql, name, from, to, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }

                public int getTotalCount(){
                    return categoryFacade.filterSize(sql, name, from, to, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}).size();
                }
                
                @Override
                public List<Products> getItems() {
                    return categoryFacade.filter(sql, name, from, to, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
                }
            };
        }
        return pagination;
    }

    private void recreateModel() {
        list = null;
    }

    private void recreatePagination() {
        pagination = null;
    }
    
    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "Filter";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "Filter";
    }

}
