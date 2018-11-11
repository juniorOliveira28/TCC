package controle;

import dao.DAOGenerico;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Usuario;

@ManagedBean 
@ViewScoped
public class UsuarioMB {
    
    private Usuario usuario;
    private List<Usuario> listaUsuarios;
    private DAOGenerico<Usuario> daoUsuario;
    
    public UsuarioMB(){
        usuario = new Usuario();
        listaUsuarios = new ArrayList<Usuario>();
        daoUsuario = new DAOGenerico<Usuario>(Usuario.class);
        preencherListaUsuarios();
    }
    
    public void preencherListaUsuarios(){
        listaUsuarios = daoUsuario.buscarTodos();
    }
    
    public void inserir(){
        if(usuario.getId() == null){
            daoUsuario.salvar(usuario);
        }else{
            daoUsuario.alterar(usuario);
        }usuario = new Usuario();
        preencherListaUsuarios();
    }
    
    public void remover(Long id){
        daoUsuario.excluir(id);
        preencherListaUsuarios();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
}
