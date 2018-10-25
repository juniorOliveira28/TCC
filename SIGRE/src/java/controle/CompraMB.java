package controle;

import dao.DAOGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Compra;
import modelo.ItensCompra;
import modelo.Produto;


@ManagedBean
@ViewScoped
public class CompraMB {

//    private Produto produto = new Produto();

    private Compra compra = new Compra();
    private ItensCompra itensCompra = new ItensCompra();
    private List<ItensCompra> listaItensCompra = new ArrayList<>();
    private List<Compra> listaCompras = new ArrayList<>();
    private List<Produto> listaProdutos = new ArrayList<>();

    private DAOGenerico<Produto> daoProduto = new DAOGenerico<>(Produto.class);
    private DAOGenerico<Compra> daoCompra = new DAOGenerico<>(Compra.class);
    private DAOGenerico<ItensCompra> daoItensCompra = new DAOGenerico<>(ItensCompra.class);

    public CompraMB() {
        listaCompras = daoCompra.buscarTodos();
    }

    public void adicionarItem() {
        System.out.println("Dentro do Método Adicionar Item");
        if (itensCompra.getProduto() != null) {

          // itensCompra.setProduto(itensCompra.getProduto());
            itensCompra.setCompra(compra);
            //itensCompra.setQuantidadeItens(itensCompra.getQuantidadeItens());
            itensCompra.setValorTotalItens(itensCompra.getQuantidadeItens() * itensCompra.getValorUnitarioItens());

//          Mostrando produto + quantidade no console
            System.out.println("Produto: " + itensCompra.getProduto().getNome()
                    + " Quantidade: " + getItensCompra().getQuantidadeItens());

//            daoProduto.alterar(produto);
            listaItensCompra.add(itensCompra);
            itensCompra = new ItensCompra();

            System.out.println("Fim do Método Adicionar Item");
        }
    }

    public void finalizarCompra() {
        System.out.println("Dentro do Método Finalizar Compra");
        Double valorFinalCompra = 0.0;
        double qtdItens = 0.0;

        daoCompra.salvar(compra);
//        daoProduto.alterar(produto);

        for (ItensCompra it : listaItensCompra) {
            qtdItens = 0.;
            
                    Produto produto = it.getProduto();
            valorFinalCompra += it.getValorTotalItens();
            qtdItens += it.getQuantidadeItens() ;

//            it.setProduto(produto);
            it.setCompra(compra);
            daoItensCompra.salvar(it);
            if (it.getProduto().getId() == null) {
                System.out.println("Vai gerar criar um novo produto");

            } else {
//             it.setProduto(produto);
//                  it.setProduto(produto);
//                produto.setQuantidadeEstoque(qtd);
                System.out.println("Setando em um produto ja cadastrado");
//                produto.setQuantidadeEstoque(qtd);
                System.out.println("ID do Produto = " + it.getProduto().getId() + it.getProduto().getNome());
                    produto.setQuantidadeEstoque(qtdItens + produto.getQuantidadeEstoque()) ;
                    daoProduto.alterar(produto);
            }
//            daoItensCompra.salvar(it);
//            compra.setValorTotalCompra(itensCompra.getValorTotalItens());
//            compra.setValorTotalCompra(itensCompra.getValorTotalItens());
//            it.setValorTotalItens(itensCompra.getValorTotalItens());
        }
        compra.setValorTotalCompra(valorFinalCompra);
        daoCompra.alterar(compra);
//        produto.setQuantidadeEstoque(qtdItens);
        listaCompras = daoCompra.buscarTodos();

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra Realizada Com Sucesso!!", ""));
        novaCompra();
        System.out.println("FIM do Método Finalizar Compra");

    }

    public void removerItem(ItensCompra itemRemover) {
        listaItensCompra.remove(itemRemover);
    }

    public void novaCompra() {
        compra = new Compra();
        listaItensCompra = new ArrayList<>();
        itensCompra = new ItensCompra();
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    public ItensCompra getItensCompra() {
        return itensCompra;
    }

    public void setItensCompra(ItensCompra itensCompra) {
        this.itensCompra = itensCompra;
    }

    public List<ItensCompra> getListaItensCompra() {
        return listaItensCompra;
    }

    public void setListaItensCompra(List<ItensCompra> listaItensCompra) {
        this.listaItensCompra = listaItensCompra;
    }

    public List<Compra> getListaCompras() {
        return listaCompras;
    }

    public void setListaCompras(List<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    }

//    public Produto getProduto() {
//        return produto;
//    }
//
//    public void setProduto(Produto produto) {
//        this.produto = produto;
//    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }

}
