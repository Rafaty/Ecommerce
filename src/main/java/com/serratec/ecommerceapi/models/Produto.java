package com.serratec.ecommerceapi.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="produto")
public class Produto implements Serializable {
	

	private static final long serialVersionUID = -157229940208324961L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 4, max = 30)
	private String nome;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "funcionario_id", referencedColumnName = "id")
	private Funcionario funcionario;
	
	@Size(min = 4, max = 200)
	private String descricao;
	
	@Past
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyy", timezone = "America/Sao_Paulo")
	@Temporal(TemporalType.DATE)
	private Date dataFabricacao;
	@NotNull
	private Double valor;
	
	@ManyToOne
	@JoinColumn(name ="categoria_id", referencedColumnName = "id")
	private Categoria categoria;
	
	@NotNull
	private Integer qtd_estoque;
	

	public Produto() {
		super();
	}
	
	public Produto(Long id, String nome, String descricao, Date dataFabricacao, Double valor, Categoria categoria,Funcionario funcionario,Integer qtd_estoque) {
		super();
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataFabricacao = dataFabricacao;
		this.valor = valor;
		this.categoria = categoria;
		this.funcionario = funcionario;
		this.qtd_estoque = qtd_estoque;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataFabricacao() {
		return dataFabricacao;
	}
	public void setDataFabricacao(Date dataFabricacao) {
		this.dataFabricacao = dataFabricacao;
	}
	public Double getValor() {
		return valor;
	}
	public void setValor(Double valor) {
		this.valor = valor;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Integer getQtd_estoque() {
		return qtd_estoque;
	}

	public void setQtd_estoque(Integer qtd_estoque) {
		this.qtd_estoque = qtd_estoque;
	}
	
}
