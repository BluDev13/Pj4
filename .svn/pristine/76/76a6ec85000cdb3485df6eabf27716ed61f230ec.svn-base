package admin.manager;

import classes.util.JsfUtil;
import entities.NewsCate;
import entities.NewsDetail;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
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
import model.NewsCateFacade;
import model.NewsDetailFacade;

@ManagedBean
@SessionScoped
public class NewsDetailManaged {

    @EJB
    private NewsCateFacade newsCateFacade;
    @EJB
    private NewsDetailFacade newsDetailFacade;
    private NewsDetail newsDetail;
    private Part file;

    public NewsDetail getNewsDetail() {
        return newsDetail;
    }

    public void setNewsDetail(NewsDetail newsDetail) {
        this.newsDetail = newsDetail;
    }
    private NewsCate newsCate;

    public NewsCate getNewsCate() {
        return newsCate;
    }

    public void setNewsCate(NewsCate newsCate) {
        this.newsCate = newsCate;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

    public NewsDetailManaged() {
        if (newsDetail == null) {
            newsDetail = new NewsDetail();
        }
        if (newsCate == null) {
            newsCate = new NewsCate();
        }
    }

    public void setDefault() {
        newsDetail = new NewsDetail();
        newsCate = new NewsCate();
    }

    public List<NewsDetail> getList() {
        return newsDetailFacade.findAll();
    }

    public SelectItem[] getNewsCateItems() {
        return JsfUtil.getSelectItems(newsCateFacade.findAll(), true);
    }

    public String createNews() {
        String uploadRootPath = SessionManaged.getRequest().getServletContext().getRealPath("resources/news_images");
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
        newsDetail.setNewsDate(Calendar.getInstance().getTime());
        newsDetail.setNewsImage(file.getSubmittedFileName());
        newsDetailFacade.create(newsDetail);
        SessionManaged.getRequest().setAttribute("message", "Successful");
        SessionManaged.getRequest().setAttribute("messageDetails", "Add news successful");
        return "ListTinTuc";
    }

    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
        Part fileValid = (Part) value;
        List<FacesMessage> msgs = new ArrayList<>();
        if (!"image/png".equals(fileValid.getContentType()) && !"image/jpeg".equals(fileValid.getContentType()) && !"image/gif".equals(fileValid.getContentType())) {
            msgs.add(new FacesMessage("The file is not valid"));
        }
        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public String preUpdate(int id) {
        newsDetail = newsDetailFacade.find(id);
        return "EditTinTuc";
    }

    public String updateNews() {
        String uploadRootPath = SessionManaged.getRequest().getServletContext().getRealPath("resources/news_images");
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

        newsDetail.setNewsImage(file.getSubmittedFileName());
        newsDetailFacade.edit(newsDetail);
        SessionManaged.getRequest().setAttribute("message", "Successful");
        SessionManaged.getRequest().setAttribute("messageDetails", "Update news information successful");
        return "ListTinTuc";
    }

    public String deleteNews(NewsDetail news) {
        newsDetailFacade.remove(news);
        SessionManaged.getRequest().setAttribute("message", "Successful");
        SessionManaged.getRequest().setAttribute("messageDetails", "Delete news successful");
        return "ListTinTuc";
    }

    public String details(int id) {
        newsDetail = newsDetailFacade.find(id);
        return "ChiTietTinTuc";
    }

    public String detailsChuyenMuc(int id) {
        newsCate = newsCateFacade.find(id);
        return "ChiTietChuyenMucTinTuc";
    }

    public NewsCate getNewsCateConverter(int id) {
        return newsCateFacade.find(id);
    }

}
