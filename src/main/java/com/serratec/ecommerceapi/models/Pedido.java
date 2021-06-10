package com.serratec.ecommerceapi.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name="pedido")
public class Pedido implements Serializable {

	private static final long serialVersionUID = -2390553894772640423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Past
	@NotNull
	private Date data;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	@OneToMany(mappedBy = "pedido", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@OnDelete(action = OnDeleteAction.CASCADE)
	List<PedidoItem> itens = new ArrayList<PedidoItem>();

	public Pedido() {
		super();
	}

	public Pedido(Long id, Date data, Cliente cliente) {
		super();
		this.id = id;
		this.data = data;
		this.cliente = cliente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<PedidoItem> getItens() {
		return itens;
	}

	public void setItens(List<PedidoItem> itens) {
		this.itens = itens;
	}

	public Double getTotal() {
		Double total = 0.0;

		for (PedidoItem item : itens) {
			total += item.getSubTotal();
		}
		return total;
	}
	
	public boolean itemExiste(Long itemId) {
		for(PedidoItem item: itens) {
			if(item.getId().equals(itemId)) {
				return true;
			}
		}
		return false;
	}

}
