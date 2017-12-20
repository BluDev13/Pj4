package classes.util;
import admin.manager.NewsDetailManaged;
import admin.manager.ProductManaged;
import entities.NewsCate;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = NewsCate.class)
public class NewsCateControllerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        NewsDetailManaged controller = (NewsDetailManaged) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "newsDetailManaged");
        NewsCate newsCate = controller.getNewsCateConverter(getKey(value));
        return newsCate;
    }

    java.lang.Integer getKey(String value) {
        java.lang.Integer key;
        key = Integer.valueOf(value);
        return key;
    }

    String getStringKey(java.lang.Integer value) {
        StringBuilder sb = new StringBuilder();
        sb.append(value);
        return sb.toString();
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof NewsCate) {
            NewsCate n = (NewsCate) object;
            return getStringKey(n.getNewsCateID());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + NewsCate.class.getName());
        }
    }

}
