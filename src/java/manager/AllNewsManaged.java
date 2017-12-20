
package manager;

import classes.util.PaginationHelper;
import entities.NewsCate;
import entities.NewsDetail;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import model.NewsCateFacade;
import model.NewsDetailFacade;

@ManagedBean
@SessionScoped
public class AllNewsManaged {
    @EJB
    private NewsCateFacade newsCateFacade;
    
    private NewsCate newsCate;
    private List<NewsDetail> items = null;
    private PaginationHelper pagination;
    private int newsCateID;

    public AllNewsManaged() {
    }

    public NewsCate getNewsCate() {
        return newsCate;
    }

    public void setNewsCate(NewsCate newsCate) {
        this.newsCate = newsCate;
    }

    public List<NewsDetail> getNews() {
        items = getPagination().getItems();
        return items;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(5) {

                @Override
                public int getItemsCount() {
                    return getTotalCount();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(newsCateFacade.findPaging(-1, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }

                public int getTotalCount(){
                    return newsCateFacade.findPagingSize(-1, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}).size();
                }
                
                @Override
                public List<NewsDetail> getItems() {
                    return newsCateFacade.findPaging(-1, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
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
        return "TinTuc";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "TinTuc";
    }
}
