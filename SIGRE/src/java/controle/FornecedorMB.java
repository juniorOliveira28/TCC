package controle;

import dao.DAOGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Fornecedor;

@ManagedBean
@ViewScoped
public class FornecedorMB {

    private Fornecedor fornecedor;
    private List<Fornecedor> listafornecedores;
    private DAOGenerico<Fornecedor> daoFornecedor;

    public FornecedorMB() {

        fornecedor = new Fornecedor();
        listafornecedores = new ArrayList<Fornecedor>();
        daoFornecedor = new DAOGenerico<Fornecedor>(Fornecedor.class);
        preencherListafornecedores();
    }

    public void preencherListafornecedores() {
        listafornecedores = daoFornecedor.buscarTodos();
    }

    public void inserir() {
        if (fornecedor.getId() == null) {
            daoFornecedor.salvar(fornecedor);
        } else {
            daoFornecedor.alterar(fornecedor);
        }preencherListafornecedores();
        fornecedor = new Fornecedor();
    }
    
    public void remover(Long id){
        daoFornecedor.excluir(id);
        preencherListafornecedores();
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public List<Fornecedor> getListafornecedores() {
        return listafornecedores;
    }

    public void setListafornecedores(List<Fornecedor> listafornecedores) {
        this.listafornecedores = listafornecedores;
    }
    
}
