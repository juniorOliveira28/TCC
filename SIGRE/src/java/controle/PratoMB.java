package controle;

import dao.DAOGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Prato;

@ManagedBean
@ViewScoped
public class PratoMB {
    
    private Prato prato;
    private List<Prato> listaPratos;
    private DAOGenerico<Prato> daoPrato;
    
    public PratoMB(){
        
        prato = new Prato();
        listaPratos = new ArrayList<Prato>();
        daoPrato = new DAOGenerico<Prato>(Prato.class);
        preencherListaPratos();
    }
    
    public void preencherListaPratos(){
        listaPratos = daoPrato.buscarTodos();
    }
    
    public void inserir(){
        if(prato.getId() == null){
           daoPrato.salvar(prato);
        }else{
            daoPrato.alterar(prato);
        }
        prato = new Prato();
        preencherListaPratos();
    }

    public void remover(Long id){
        daoPrato.excluir(id);
        preencherListaPratos();
    }

    public Prato getPrato() {
        return prato;
    }

    public void setPrato(Prato prato) {
        this.prato = prato;
    }

    public List<Prato> getListaPratos() {
        return listaPratos;
    }

    public void setListaPratos(List<Prato> listaPratos) {
        this.listaPratos = listaPratos;
    }
    
}
