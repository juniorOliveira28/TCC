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

@ManagedBean
@ViewScoped
public class CompraMB {

    private Compra compra = new Compra();
    private ItensCompra itensCompra = new ItensCompra();
    private List<ItensCompra> listaItensCompra = new ArrayList<>();
    private List<Compra> listaCompras = new ArrayList<>();

    private DAOGenerico<Compra> daoCompra = new DAOGenerico<>(Compra.class);
    private DAOGenerico<ItensCompra> daoItensCompra = new DAOGenerico<>(ItensCompra.class);

    public CompraMB() {
//        compra = new Compra();
//        itensCompra = new ItensCompra();
//        listaItensCompra = new ArrayList<>();
preencherListaItensCompra();
    }
    public void preencherListaItensCompra(){
        listaItensCompra = daoItensCompra.buscarTodos();
    }
//    public void inserirItensCompra() {
//        System.out.println("Quantidade" + itensCompra.getQuantidade());
//        listaItensCompra.add(itensCompra);
//        System.out.println("Tam lista" + listaItensCompra.size());
//        itensCompra = new ItensCompra();
//        System.out.println("Quantidade" + itensCompra.getQuantidade());
//    }
//
//    public void inserirCompra() {
//        if (compra.getId() == null) {
//            daoCompra.salvar(compra);
//            for (ItensCompra item : listaItensCompra) {
//                item.setCompra(compra);
//                daoItensCompra.salvar(item);
//            }
//        }else{
//            daoCompra.alterar(compra);
//        }
//    }
    
    public void adicionarItem() {
		System.out.println("DEntro do Método");
		if (itensCompra.getProduto() != null) {			
			itensCompra.setValorUnitario(itensCompra.getProduto().getValor());
			itensCompra.setValotTotal(itensCompra.getQuantidade() * itensCompra.getValorUnitario());
			listaItensCompra.add(itensCompra);
			itensCompra = new ItensCompra();
			System.out.println("QTDLista: "+listaItensCompra.size());
		}
	}
    	public void finalizarCompra() {
		daoCompra.salvar(compra);
		for (ItensCompra it : listaItensCompra) {
			it.setCompra(compra);
			daoItensCompra.salvar(it);
		}
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra Realizada Com Sucesso!!", ""));
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

}
