package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itensCompra")
public class ItensCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double quantidadeItens;
    private Double valorUnitarioItens;
    private Double valorTotalItens;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Compra compra;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidadeItens() {
        if(quantidadeItens == null){
            quantidadeItens = 0.;
        }
        return quantidadeItens;
    }

    public void setQuantidadeItens(Double quantidadeItens) {
        this.quantidadeItens = quantidadeItens;
    }

    public Double getValorUnitarioItens() {
        return valorUnitarioItens;
    }

    public void setValorUnitarioItens(Double valorUnitarioItens) {
        this.valorUnitarioItens = valorUnitarioItens;
    }

    public Double getValorTotalItens() {
        return valorTotalItens;
    }

    public void setValorTotalItens(Double valorTotalItens) {
        this.valorTotalItens = valorTotalItens;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra compra) {
        this.compra = compra;
    }

    
        @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((quantidadeItens == null) ? 0 : quantidadeItens.hashCode());
        result = prime * result + ((valorUnitarioItens == null) ? 0 : valorUnitarioItens.hashCode());
        result = prime * result + ((valorTotalItens == null) ? 0 : valorTotalItens.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ItensCompra other = (ItensCompra) obj;
        if (id == null) {
            if (other.id != null) {
                return false;
            }
        } else if (!id.equals(other.id)) {
            return false;
        }
        if (quantidadeItens == null) {
            if (other.quantidadeItens != null) {
                return false;
            }
        } else if (!quantidadeItens.equals(other.quantidadeItens)) {
            return false;
        }
        if (valorUnitarioItens == null) {
            if (other.valorUnitarioItens != null) {
                return false;
            }
        } else if (!valorUnitarioItens.equals(other.valorUnitarioItens)) {
            return false;
        }
        if (valorTotalItens == null) {
            if (other.valorTotalItens != null) {
                return false;
            }
        } else if (!valorTotalItens.equals(other.valorTotalItens)) {
            return false;
        }
        return true;
    }
}