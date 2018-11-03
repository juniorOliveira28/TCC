package modelo;

import java.io.Serializable;
import javax.persistence.Entity;

@Entity
public class Fornecedor extends Pessoa implements Serializable {

   private String cnpj;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
