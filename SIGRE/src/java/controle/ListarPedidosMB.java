package controle;

import dao.DAOGenerico;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Pedido;

@ManagedBean
@ViewScoped
public class ListarPedidosMB {
    private List<Pedido> listaPedidos;
    private DAOGenerico<Pedido> daoPedido = new DAOGenerico<>(Pedido.class);
    
    public ListarPedidosMB(){
        preencherListaPedidos();
    }
    
    public void preencherListaPedidos(){
        listaPedidos = daoPedido.buscarTodos();
    }
}
