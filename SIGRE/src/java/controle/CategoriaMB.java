package controle;

import dao.DAOGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Categoria;

@ViewScoped
@ManagedBean
public class CategoriaMB {

    private Categoria categoria;
    private List<Categoria> listaCategorias;
    private DAOGenerico<Categoria> daoCategoria;
    
    public CategoriaMB(){
        
        categoria = new Categoria();
        listaCategorias = new ArrayList<Categoria>();
        daoCategoria = new DAOGenerico<Categoria>(Categoria.class);
        preencherListaCategorias();
    }
    
    public void preencherListaCategorias(){
        listaCategorias = daoCategoria.buscarTodos();
    }
    
    public void inserir(){
        if(categoria.getId() == null){
           daoCategoria.salvar(categoria);
        }else{
            daoCategoria.alterar(categoria);
        }
        preencherListaCategorias();
        categoria = new Categoria();
    }
    
    public void remover(Long id){
        daoCategoria.excluir(id);
        preencherListaCategorias();
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<Categoria> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }
    
}