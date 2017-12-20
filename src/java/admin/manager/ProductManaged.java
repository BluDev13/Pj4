package admin.manager;

import classes.util.JsfUtil;
import entities.Brands;
import entities.Category;
import entities.Products;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;
import manager.SessionManaged;
import model.BrandsFacade;
import model.CategoryFacade;
import model.ProductsFacade;

@ManagedBean(name = "productAdminManaged")
@SessionScoped
public class ProductManaged  implements Serializable{

    @EJB
    private CategoryFacade categoryFacade;
    @EJB
    private ProductsFacade productsFacade;
    @EJB
    private BrandsFacade brandsFacade;

    private Products product;
    private Brands brand;
    private Part file;
    private String fileImage;

    public ProductManaged() {
        if (product == null) {
            product = new Products();
        }
        if (brand == null) {
            brand = new Brands();
        }
    }

    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public void emptyProduct(){
        this.product = new Products();
    }
    
    public List<Products> getList() {
        return productsFacade.findAll();
    }
    
    public List<Products> getListAdmin() {
        return productsFacade.findAllAdmin();
    }

    public SelectItem[] getCategoryItems() {
        List<Category> categories = categoryFacade.findAll();
        List<Category> categoryItems = new ArrayList<>();
        for (Category cate : categories) {
            if (cate.getCategorySub() != null) {
                categoryItems.add(cate);
            }
        }
        return JsfUtil.getSelectItems(categoryItems, true);
        //return JsfUtil.getSelectItems(categoryFacade.findAll(), true);
    }

    public SelectItem[] getBrandItems() {
        return JsfUtil.getSelectItems(brandsFacade.findAll(), true);
    }

    public String createProduct() {
        String uploadRootPath = SessionManaged.getRequest().getServletContext().getRealPath("resources/product_images");
        File uploadRootDir = new File(uploadRootPath);
        if (!uploadRootDir.exists()) {
            uploadRootDir.mkdirs();
        }
        File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + file.getSubmittedFileName());
        try {
            InputStream is;
            OutputStream os;
            is = file.getInputStream();
            os = new FileOutputStream(serverFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            is.close();
            os.close();
        } catch (IOException e) {
        }
        
        product.setImportDate(Calendar.getInstance().getTime());
        product.setThumbImage(file.getSubmittedFileName());
        product.setOrderCount(0);
        productsFacade.create(product);
        product = new Products();
        SessionManaged.getRequest().setAttribute("message", "Success");
        SessionManaged.getRequest().setAttribute("messageDetails", "Add new product successful.");
        return "ListSanPham";
    }

    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
        Part fileValid = (Part) value;
        if (fileValid.getSize() != 0) {
            List<FacesMessage> msgs = new ArrayList<>();
            if (!"image/png".equals(fileValid.getContentType()) && !"image/jpeg".equals(fileValid.getContentType()) && !"image/gif".equals(fileValid.getContentType())) {
                msgs.add(new FacesMessage("Invalid Image"));
            }
            if (!msgs.isEmpty()) {
                throw new ValidatorException(msgs);
            }
        }
    }

    public String preUpdate(int id) {
        product = productsFacade.find(id);
        fileImage = product.getThumbImage();
        return "EditSanPham";
    }
        
    public String preIncrease(int id){
        product = productsFacade.find(id);
        if(product != null){
            return "IncreaseSanPham";
        }
        return "";
    }

    public String increaseSanPham(){
        if(product.getQuantity() > 0){
            productsFacade.edit(product);
            return "ProductSoldOut";
        }
        return "";
    }
    
    public String updateSanPham() {
        if (file.getSize() != 0) {
            String uploadRootPath = SessionManaged.getRequest().getServletContext().getRealPath("resources/product_images");
            File uploadRootDir = new File(uploadRootPath);
            if (!uploadRootDir.exists()) {
                uploadRootDir.mkdirs();
            }
            File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + file.getSubmittedFileName());
            try {
                InputStream is;
                OutputStream os;
                is = file.getInputStream();
                os = new FileOutputStream(serverFile);
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                is.close();
                os.close();
            } catch (IOException e) {
            }
            product.setThumbImage(file.getSubmittedFileName());
        }
        else {
            product.setThumbImage(fileImage);
        }
        productsFacade.edit(product);
        product = new Products();
        SessionManaged.getRequest().setAttribute("message", "Success");
        SessionManaged.getRequest().setAttribute("messageDetails", "Exist product was updated successful");
        return "ListSanPham";
    }

    public String deleteCategory(Products cate) {
        productsFacade.remove(cate);
        SessionManaged.getRequest().setAttribute("message", "Success");
        SessionManaged.getRequest().setAttribute("messageDetails", "Exist product was deleted successful");
        return "ListSanPham";
    }

    public String details(int id) {
        product = productsFacade.find(id);
        return "ChiTietSanPham";
    }

    public Category getCategoryConverter(int id) {
        return categoryFacade.find(id);
    }

    public Brands getBrandConverter(int id) {
        return brandsFacade.find(id);
    }

}