package classes.util;

import admin.manager.ProductManaged;
import entities.Brands;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Brands.class)
public class BrandsControllerConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
        if (value == null || value.length() == 0) {
            return null;
        }
        ProductManaged controller = (ProductManaged) facesContext.getApplication().getELResolver().
                getValue(facesContext.getELContext(), null, "productAdminManaged");
        Brands brand = controller.getBrandConverter(getKey(value));
        return brand;
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
        if (object instanceof Brands) {
            Brands o = (Brands) object;
            return getStringKey(o.getBrandID());
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Brands.class.getName());
        }
    }

}
