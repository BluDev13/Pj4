package admin.manager;

import entities.Category;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import manager.SessionManaged;
import model.CategoryFacade;

@ManagedBean(name = "categoryAdminManaged")
@SessionScoped
public class CategoryManaged {

    @EJB
    private CategoryFacade categoryFacade;
    private Category category;

    public CategoryManaged() {
        if (category == null) {
            category = new Category();
        }
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void newCategory() {
        this.category = new Category();
    }

    public String addCategory() {
        categoryFacade.create(category);
        category = new Category();
        SessionManaged.getRequest().setAttribute("message", "Successful");
        SessionManaged.getRequest().setAttribute("messageDetails", "Add new category successful");
        return "ListChuyenMuc";
    }

    public String preUpdate(int id) {
        category = categoryFacade.find(id);
        return "EditChuyenMuc";
    }

    public String updateCategory() {
        categoryFacade.edit(category);
        category = new Category();
        SessionManaged.getRequest().setAttribute("message", "Successful");
        SessionManaged.getRequest().setAttribute("messageDetails", "Update infomation successful");
        return "ListChuyenMuc";
    }

    public String deleteCategory(Category cate) {
        if(cate.getCategorySub() == null){
            categoryFacade.removeRoot(cate.getCategoryID());
        }
        categoryFacade.remove(cate);
        SessionManaged.getRequest().setAttribute("message", "Successful");
        SessionManaged.getRequest().setAttribute("messageDetails", "Delete category successful");
        return "ListChuyenMuc";
    }

    public String details(int id) {
        category = categoryFacade.find(id);
        return "ChiTietDanhMuc";
    }

    public Category getParentCategory(int sub) {
        return categoryFacade.find(sub);
    }

    public List<Category> getSubCategory(int id) {
        return categoryFacade.getSubCategory(id);
    }

    public Long productCount(int categoryID) {
        return categoryFacade.productCount(categoryID);
    }
}
