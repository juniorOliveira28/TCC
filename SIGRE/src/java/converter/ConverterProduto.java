package converter;

import dao.DAOGenerico;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Produto;

@FacesConverter("converterProduto")
public class ConverterProduto implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String id) {
        if (id != null && !id.isEmpty()) {
            DAOGenerico<Produto> dao = new DAOGenerico<Produto>(Produto.class);
            return dao.buscarPorId(new Long(id));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            Produto ob = (Produto) o;
            return String.valueOf(ob.getId());
        }
        return null;
    }
}
