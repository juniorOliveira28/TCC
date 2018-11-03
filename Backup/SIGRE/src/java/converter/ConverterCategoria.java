package converter;

import dao.DAOGenerico;
import modelo.Categoria;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("converterCategoria")
public class ConverterCategoria implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String id) {
        if (id != null && !id.isEmpty()) {
            DAOGenerico<Categoria> dao = new DAOGenerico<Categoria>(Categoria.class);
            return dao.buscarPorId(new Long(id));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            Categoria categoria = (Categoria) o;
            return String.valueOf(categoria.getId());
        }
        return null;
    }
}