package classes.util;

import admin.manager.ProductManaged;
import entities.Category;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Category.class)
public class CategorysControllerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        ProductManaged controller = (ProductManaged) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "productAdminManaged");
        Category cate = controller.getCategoryConverter(getKey(value));
        return cate;
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
        if (object instanceof Category) {
            Category o = (Category) object;
            return getStringKey(o.getCategoryID());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Category.class.getName());
        }
    }

}
