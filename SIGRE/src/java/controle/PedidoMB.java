package controle;

import dao.DAOGenerico;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.ItensPedido;
import modelo.Mesa;
import modelo.Pedido;
import modelo.Produto;

@ManagedBean
@ViewScoped
public class PedidoMB {

    private Mesa mesa;

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    private Pedido pedido = new Pedido();
    private ItensPedido itensPedido = new ItensPedido();
    private List<ItensPedido> listaItensPedidos = new ArrayList<>();
    private List<ItensPedido> listaItensPedidosMesa = new ArrayList<>();
    private List<Pedido> listaPedidos = new ArrayList<>();
    private List<Produto> listaProdutos = new ArrayList<>();

    private DAOGenerico<Mesa> daoMesa = new DAOGenerico<>(Mesa.class);
    private DAOGenerico<Produto> daoProduto = new DAOGenerico<>(Produto.class);
    private DAOGenerico<Pedido> daoPedido = new DAOGenerico<>(Pedido.class);
    private DAOGenerico<ItensPedido> daoItensPedido = new DAOGenerico<>(ItensPedido.class);

    public PedidoMB() {

        pedido = new Pedido();
        itensPedido = new ItensPedido();
        listaItensPedidos = new ArrayList<>();
        listaPedidos = daoPedido.buscarTodos();
        listaItensPedidosMesa = new ArrayList<>();
        preencherListaPedidosMesa();
    }

    public void preencherListaPedidosMesa() {
        listaItensPedidosMesa = daoItensPedido.buscarTodos();
    }

    public void adicionarItem() {
        System.out.println("Dentro do Método Adicionar Item");
        Produto produto = itensPedido.getProduto();
        for (Pedido it : listaPedidos) {
//------------------------------------------------------------------------------
            if (it.getMesa().getStatus().equals("OCUPADA")
                    && it.getStatus().equals("ABERTO")
                    && itensPedido.getId() == null) {
                System.out.println("MESA OCUPADA, PEDIDO ADICIONADO A LISTA");

            }
            if (it.getMesa().getStatus().equals("LIVRE")
                    && it.getStatus().equals("CONCLUIDO")
                    && itensPedido.getId() == null) {
                System.out.println("MESA LIVRE, NOVO PEDIDO ABERTO");
//------------------------------------------------------------------------------

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
                    listaItensPedidosMesa.add(itensPedido);
                    itensPedido = new ItensPedido();

                    System.out.println("Fim do Método Adicionar Item");
                } else {
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_INFO, "Quantidade Indisponivel no estoque!!", ""));
                    System.out.println("Quantidade indisponivel no estoque");
                }
            } else {
//            listaItensPedidos.replaceAll((UnaryOperator<ItensPedido>) itensPedido);
            }
        }
    }

    public void finalizarPedido() {
        System.out.println("Dentro do Método Finalizar pedido");
        Double valorFinalPedido = 0.0;
        double qtdItens = 0.0;

        daoPedido.salvar(pedido);
        for (ItensPedido it : listaItensPedidos) {
            Produto produto = it.getProduto();
            Mesa mesa = it.getPedido().getMesa();

            it.getPedido().setMesa(mesa);
            it.setPedido(pedido);
            daoItensPedido.salvar(it);

            if (it.getProduto().getEstoque().equals("SIM")) {
                it.setPedido(pedido);
                daoItensPedido.salvar(it);
                System.out.println("Produto Com estoque");
//
                System.out.println("Produto: " + it.getProduto().getNome()
                        + " Quantidade: " + it.getQuantidade());
                qtdItens = produto.getQuantidadeEstoque() - it.getQuantidade();
                it.setProduto(produto);
                produto.setQuantidadeEstoque(qtdItens);
                daoProduto.alterar(produto);

            } else if (it.getProduto().getEstoque().equals("NÃO")) {
                it.setPedido(pedido);
                daoItensPedido.salvar(it);
                System.out.println("Este produto não possui estoque ");
                System.out.println("Produto: " + it.getProduto().getNome()
                        + " Quantidade: " + it.getQuantidade());
            }
            valorFinalPedido += it.getValorTotal();
            mesa.setStatus("OCUPADA");
            it.getPedido().setMesa(mesa);
            daoMesa.alterar(mesa);
            it.setPedido(pedido);
            it.getPedido().setStatus("ABERTO");
            daoPedido.alterar(pedido);
        }
        System.out.println("Mesa: " + pedido.getMesa().getNumero());

        pedido.setValorTotal(valorFinalPedido);
        daoPedido.alterar(pedido);
        listaItensPedidosMesa.add(itensPedido);
        listaPedidos = daoPedido.buscarTodos();

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Pedido Realizado Com Sucesso!!", ""));
        novoPedido();
        System.out.println("FIM do Método Finalizar pedido");

    }

    public Long parNum;

    public void fecharMesa() {
        System.out.println("Dentro do metodo fechar mesa");

        for (Pedido it : listaPedidos) {
            if (it.getMesa().getNumero() == parNum
                    && it.getMesa().getStatus().equals("OCUPADA")
                    && it.getStatus().equals("ABERTO")) {
                it.getMesa().setStatus("LIVRE");
                it.setStatus("CONCLUIDO");
                daoMesa.alterar(it.getMesa());
                daoPedido.alterar(it);

                Pedido pedido = new Pedido();
            }
        }
        System.out.println("Fim do método fechar mesa");
    }

    public void novoPedido() {
        pedido = new Pedido();
        listaItensPedidos = new ArrayList<>();
        itensPedido = new ItensPedido();
    }

    public void abrirMesa(Long num) {
        parNum = num;
        Long a = null;
        a.toString(num);
        listaItensPedidosMesa = new ArrayList<>();
        for (Pedido it : listaPedidos) {
            if (it.getMesa().getNumero() == num
                    && it.getMesa().getStatus().equals("OCUPADA")
                    && it.getStatus().equals("ABERTO")) {
                System.out.println("| Mesa: " + num + " |"
                        + " " + "Pedido: " + it.getId() + " |"
                        + " " + "Quantidade: " + it.getValorUnitario() + " |"
                        + "Status " + it.getMesa().getStatus());
                daoPedido.alterar(it);

                listaItensPedidos = daoItensPedido.buscarTodos();
                for (ItensPedido itens : listaItensPedidos) {
                    if (itens.getPedido().getId() == it.getId()) {

                        System.out.println("Produto: " + itens.getProduto().getNome());
                        listaItensPedidosMesa.add(itens);
//                        itens = new ItensPedido();
                    }
//            if (itens.getPedido().getId() != null) {
//                System.out.println("Nenhum item encontrado");
//            } else {
//                System.out.println("Deu ruim");
//            }
                }
            }
        }
    }

    public void removerItem(ItensPedido itemRemover) {
        System.out.println("Dentro do metodo remover");
        listaItensPedidos.remove(itemRemover);
    }

    public void preencherListaPedidos(Long id) {
        daoItensPedido.buscarPorId(id);
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

    public List<ItensPedido> getListaItensPedidosMesa() {
        return listaItensPedidosMesa;
    }

    public void setListaItensPedidosMesa(List<ItensPedido> listaItensPedidosMesa) {
        this.listaItensPedidosMesa = listaItensPedidosMesa;
    }

}
