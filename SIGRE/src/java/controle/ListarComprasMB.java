package controle;

import dao.DAOGenerico;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Compra;

@ManagedBean
@ViewScoped
public class ListarComprasMB {
    
    private List<Compra> listaCompras;
    private DAOGenerico<Compra> daoCompra = new DAOGenerico<>(Compra.class);

    public List<Compra> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

    public DAOGenerico<Compra> getDaoCompra() {
        return daoCompra;
    }

    public void setDaoCompra(DAOGenerico<Compra> daoCompra) {
        this.daoCompra = daoCompra;
    }
    
    
}
