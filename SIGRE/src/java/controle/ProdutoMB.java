package controle;

import dao.DAOGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Produto;

@ManagedBean
@ViewScoped
public class ProdutoMB {

    private Produto produto;
    private List<Produto> listaProdutos;
    private DAOGenerico<Produto> daoProduto;

    public ProdutoMB() {

        produto = new Produto();
        listaProdutos = new ArrayList<Produto>();
        daoProduto = new DAOGenerico<Produto>(Produto.class);
        preencherListaProdutos();
    }

    public void preencherListaProdutos() {
        listaProdutos = daoProduto.buscarTodos();
    }

    public void inserir() {
        if (produto.getId() == null) {
            daoProduto.salvar(produto);
        } else {
            daoProduto.alterar(produto);
        }preencherListaProdutos();
        produto = new Produto();
    }
    
    public void remover(Long id){
        daoProduto.excluir(id);
        preencherListaProdutos();
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
    }
    
}
