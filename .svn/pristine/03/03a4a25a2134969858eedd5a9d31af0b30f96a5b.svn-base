package manager;

import classes.util.PaginationHelper;
import entities.NewsCate;
import entities.NewsDetail;
import java.io.Serializable;
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

@ManagedBean(name = "userNewsManaged")
@SessionScoped
public class NewsManaged implements Serializable{
    @EJB
    private NewsDetailFacade newsDetailFacade;
    @EJB
    private NewsCateFacade newsCateFacade;
    
    private NewsCate newsCate;
    private NewsDetail newsDetail;
    private List<NewsDetail> items = null;
    private PaginationHelper pagination;
    private int newsCateID;

    public NewsManaged() {
    }

    public NewsCate getNewsCate() {
        return newsCate;
    }

    public void setNewsCate(NewsCate newsCate) {
        this.newsCate = newsCate;
    }

    public NewsDetail getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(NewsDetail newsDetail) {
        this.newsDetail = newsDetail;
    }

    public String showNewsCate() {
        int id;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            id = Integer.parseInt(params.get("newsCateID"));
        } catch (NumberFormatException e) {
            return "404";
        }

        if (id <= 0) {
            return "index";
        } else {
            recreatePagination();
            newsCateID = id;
            newsCate = newsCateFacade.find(id);
            return "NewsCate";
        }
    }
    
    public List<NewsDetail> getNews() {
        items = getPagination().getItems();
        return items;
    }
    
    public String showDetail() {
        int id;
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        try {
            id = Integer.parseInt(params.get("newsDetailID"));
        } catch (NumberFormatException e) {
            return "404";
        }

        if (id <= 0) {
            return "index";
        } else {
            newsDetail = newsDetailFacade.find(id);
            return "NewsDetail";
        }
    }
    
    public List<NewsDetail> findAll(){
        recreatePagination();
        newsCateID = -1;
        items = getPagination().getItems();
        return items;
    }
    
    public List<NewsDetail> findNew(){
        return newsDetailFacade.findNew();
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
                    return new ListDataModel(newsCateFacade.findPaging(newsCateID, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }

                public int getTotalCount(){
                    return newsCateFacade.findPagingSize(newsCateID, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}).size();
                }
                
                @Override
                public List<NewsDetail> getItems() {
                    return newsCateFacade.findPaging(newsCateID, new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()});
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
        return "NewsCate";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "NewsCate";
    }
    
}
