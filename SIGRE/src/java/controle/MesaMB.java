package controle;

import dao.DAOGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Mesa;

@ManagedBean
@ViewScoped
public class MesaMB {

    private Mesa mesa;
    private List<Mesa> listaMesas;
    private DAOGenerico<Mesa> daoMesa;
    
    public MesaMB(){
       
        mesa = new Mesa();
        listaMesas = new ArrayList<Mesa>();
        daoMesa = new DAOGenerico<Mesa>(Mesa.class);
        preencherListaMesas();
    }
    
    public void preencherListaMesas(){
        listaMesas = daoMesa.buscarTodos();
    }
    
    public void inserir(){
        if(mesa.getId() == null){
           daoMesa.salvar(mesa);
        }else{
            daoMesa.alterar(mesa);
        }
        preencherListaMesas();
        mesa = new Mesa();
    }
    
    public void remover(Long id){
        daoMesa.excluir(id);
        preencherListaMesas();
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public List<Mesa> getListaMesas() {
        return listaMesas;
    }

    public void setListaMesas(List<Mesa> listaMesas) {
        this.listaMesas = listaMesas;
    }
}
