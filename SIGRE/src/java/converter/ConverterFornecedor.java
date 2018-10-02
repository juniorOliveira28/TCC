package converter;

import dao.DAOGenerico;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Fornecedor;

@FacesConverter("converterFornecedor")
public class ConverterFornecedor implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String id) {
        if (id != null && !id.isEmpty()) {
            DAOGenerico<Fornecedor> dao = new DAOGenerico<Fornecedor>(Fornecedor.class);
            return dao.buscarPorId(new Long(id));
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if (o != null) {
            Fornecedor fornecedor = (Fornecedor) o;
            return String.valueOf(fornecedor.getId());
        }
        return null;
    }
}