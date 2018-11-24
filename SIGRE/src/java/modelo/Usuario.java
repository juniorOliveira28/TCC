package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
//@Table(name = "usuario")
public class Usuario extends Pessoa implements Serializable {

    private String perfil;
    private String senha;
    private Boolean enable = true;

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }
}
