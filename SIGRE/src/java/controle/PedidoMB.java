package controle;

import dao.DAOGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.ItensPedido;
import modelo.Pedido;
import modelo.Produto;

@ManagedBean
@ViewScoped
public class PedidoMB {

    private Pedido pedido = new Pedido();
    private ItensPedido itensPedido = new ItensPedido();
    private List<ItensPedido> listaItensPedidos = new ArrayList<>();
    private List<Pedido> listaPedidos = new ArrayList<>();
    private List<Produto> listaProdutos = new ArrayList<>();

    private DAOGenerico<Produto> daoProduto = new DAOGenerico<>(Produto.class);
    private DAOGenerico<Pedido> daoPedido = new DAOGenerico<>(Pedido.class);
    private DAOGenerico<ItensPedido> daoItensPedido = new DAOGenerico<>(ItensPedido.class);

    public PedidoMB() {

        pedido = new Pedido();
        itensPedido = new ItensPedido();
        listaItensPedidos = new ArrayList<>();
    }

    public void adicionarItem() {
        System.out.println("Dentro do Método Adicionar Item");
        Produto produto = itensPedido.getProduto();
        if (itensPedido.getProduto() != null && produto.getQuantidadeEstoque() > 0
                && itensPedido.getQuantidade() < produto.getQuantidadeEstoque()) {
            itensPedido.setProduto(itensPedido.getProduto());
            itensPedido.setPedido(pedido);
            itensPedido.setQuantidade(itensPedido.getQuantidade());
            itensPedido.setValorTotal(itensPedido.getQuantidade() * itensPedido.getProduto().getValorVenda());

//          Mostrando produto + quantidade no console
            System.out.println("Produto: " + itensPedido.getProduto().getNome()
                    + " Quantidade: " + getItensPedido().getQuantidade());

            listaItensPedidos.add(itensPedido);
            itensPedido = new ItensPedido();

            System.out.println("Fim do Método Adicionar Item");
        } else if (itensPedido.getProduto() != null && itensPedido.getProduto().getEstoque().equals("NÃO")) {
            itensPedido.setProduto(itensPedido.getProduto());
            itensPedido.setPedido(pedido);
            itensPedido.setQuantidade(itensPedido.getQuantidade());
            itensPedido.setValorTotal(itensPedido.getQuantidade() * itensPedido.getProduto().getValorVenda());

//          Mostrando produto + quantidade no console
            System.out.println("Produto: " + itensPedido.getProduto().getNome()
                    + " Quantidade: " + getItensPedido().getQuantidade());

            listaItensPedidos.add(itensPedido);
            itensPedido = new ItensPedido();

            System.out.println("Fim do Método Adicionar Item");
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Quantidade Indisponivel no estoque!!", ""));
            System.out.println("Quantidade indisponivel no estoque");
        }
    }

    public void finalizarPedido() {
        System.out.println("Dentro do Método Finalizar Compra");
        Double valorFinalPedido = 0.0;
        Double qtdItens = 0.0;

        daoPedido.salvar(pedido);

        for (ItensPedido it : listaItensPedidos) {
            if (itensPedido.getProduto().getEstoque().equals("SIM")) {
                valorFinalPedido += it.getValorTotal();
                it.setPedido(pedido);
                daoItensPedido.salvar(it);

                Produto produto = itensPedido.getProduto();
                qtdItens = produto.getQuantidadeEstoque() - it.getQuantidade();
                it.setProduto(produto);
                produto.setQuantidadeEstoque(qtdItens);
                daoProduto.alterar(produto);

            } else {
                valorFinalPedido += it.getValorTotal();
                it.setPedido(pedido);
                daoItensPedido.salvar(it);
            }
        }
        pedido.setValorTotal(valorFinalPedido);
        daoPedido.alterar(pedido);
        listaPedidos = daoPedido.buscarTodos();

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra Realizada Com Sucesso!!", ""));
        novoPedido();
        System.out.println("FIM do Método Finalizar Compra");

    }

    public void novoPedido() {
        pedido = new Pedido();
        listaItensPedidos = new ArrayList<>();
        itensPedido = new ItensPedido();
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

    public List<ItensPedido> getListaItensPedidos() {
        return listaItensPedidos;
    }

    public void setListaItensPedidos(List<ItensPedido> listaItensPedidos) {
        this.listaItensPedidos = listaItensPedidos;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

}
