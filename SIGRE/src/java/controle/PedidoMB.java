package controle;

import dao.DAOGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.ItensPedido;
import modelo.Pedido;

@ManagedBean
@ViewScoped
public class PedidoMB {

    private Pedido pedido;
    private ItensPedido itensPedido;
    private List<ItensPedido> listaItensPedido;
    
    private DAOGenerico<Pedido> daoPedido = new DAOGenerico<Pedido>(Pedido.class);
    private DAOGenerico<ItensPedido> daoItensPedido = new DAOGenerico<ItensPedido>(ItensPedido.class);
    
    public PedidoMB(){
        
       pedido = new Pedido();
       itensPedido = new ItensPedido();
       listaItensPedido = new ArrayList<>();
    }
    
    public void InserirItensPedido(){
        listaItensPedido.add(itensPedido);
        itensPedido = new ItensPedido();
    }
   
    public void inserirPedido(){
        if(pedido.getId() == null){
            daoPedido.salvar(pedido);
            for(ItensPedido item: listaItensPedido){
                item.setPedido(pedido);
                daoItensPedido.salvar(item);
            }
        }else{
            daoPedido.alterar(pedido);
        }
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public ItensPedido getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(ItensPedido itensPedido) {
        this.itensPedido = itensPedido;
    }

    public List<ItensPedido> getListaItensPedido() {
        return listaItensPedido;
    }

    public void setListaItensPedido(List<ItensPedido> listaItensPedido) {
        this.listaItensPedido = listaItensPedido;
    }
    
    
}
