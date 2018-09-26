package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ItensCompra implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double quantidade;
    private Double valorUnitario;
    private Double valorTotal;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Compra compra;

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
        result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
        result = prime * result + ((valorUnitario == null) ? 0 : valorUnitario.hashCode());
        result = prime * result + ((valorTotal == null) ? 0 : valorTotal.hashCode());
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
        if (quantidade == null) {
            if (other.quantidade != null) {
                return false;
            }
        } else if (!quantidade.equals(other.quantidade)) {
            return false;
        }
        if (valorUnitario == null) {
            if (other.valorUnitario != null) {
                return false;
            }
        } else if (!valorUnitario.equals(other.valorUnitario)) {
            return false;
        }
        if (valorTotal == null) {
            if (other.valorTotal != null) {
                return false;
            }
        } else if (!valorTotal.equals(other.valorTotal)) {
            return false;
        }
        return true;
    }

    public ItensCompra() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValotTotal() {
        return valorTotal;
    }

    public void setValotTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
