
package admin.manager;

import entities.NewsCate;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import manager.SessionManaged;
import model.NewsCateFacade;

@ManagedBean
@SessionScoped
public class NewsManaged implements Serializable{
    @EJB
    private NewsCateFacade newsCateFacade;
    private NewsCate newsCate;

    public NewsCate getNewsCate() {
        return newsCate;
    }

    public void setNewsCate(NewsCate newsCate) {
        this.newsCate = newsCate;
    }

    
    public NewsManaged() {
        if(newsCate == null){
            newsCate = new NewsCate();
        }
    }
     public List<NewsCate> getList(){
        return newsCateFacade.findAll();
    }

    public String addNewsCate() {
        newsCateFacade.create(newsCate);
        return "ListChuyenMucTinTuc";
    }
    
    public String preUpdate(int id){
        newsCate = newsCateFacade.find(id);
        SessionManaged.getRequest().setAttribute("message", "Successful");
        SessionManaged.getRequest().setAttribute("messageDetails", "Add news successful");
        return "EditChuyenMucTinTuc";
    }
    
    public String updateNewsCate() {
        newsCateFacade.edit(newsCate);
        SessionManaged.getRequest().setAttribute("message", "Successful");
        SessionManaged.getRequest().setAttribute("messageDetails", "Update news information successful");
        return "ListChuyenMucTinTuc";
    }
    
    public String deleteNewsCate(NewsCate news){
        newsCateFacade.remove(news);
        SessionManaged.getRequest().setAttribute("message", "Successful");
        SessionManaged.getRequest().setAttribute("messageDetails", "Delete news successfull");
        return "ListChuyenMucTinTuc";
    }
    
    public String details(int id){
        newsCate = newsCateFacade.find(id);
        return "ChiTietChuyenMucTinTuc";
    }
}
