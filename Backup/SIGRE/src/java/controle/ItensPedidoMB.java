package controle;

import dao.DAOGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.ItensPedido;

@ManagedBean
@ViewScoped
public class ItensPedidoMB {
    
    private ItensPedido itensPedido;
    private List<ItensPedido> listaItensPedido;
    private DAOGenerico<ItensPedido> daoItensPedido;
    
  
    public ItensPedidoMB(){
            itensPedido = new ItensPedido();
            listaItensPedido = new ArrayList<ItensPedido>();
            daoItensPedido = new DAOGenerico<ItensPedido>(ItensPedido.class);
    }
}
