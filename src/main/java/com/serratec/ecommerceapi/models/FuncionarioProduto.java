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
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fucionario_produtos")
public class FuncionarioProduto implements Serializable {


	private static final long serialVersionUID = -2447257734526126182L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id", referencedColumnName = "id")
	private Funcionario funcionario;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id")
	private Produto produto;
	
	private String acao;
	@Past
	private Date dataAcao;
	
	public FuncionarioProduto() {
		super();
	}

	public FuncionarioProduto(Long id, Funcionario funcionario, Produto produto, String acao, Date dataAcao) {
		super();
		this.id = id;
		this.funcionario = funcionario;
		this.produto = produto;
		this.acao = acao;
		this.dataAcao = dataAcao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public Date getDataAcao() {
		return dataAcao;
	}

	public void setDataAcao(Date dataAcao) {
		this.dataAcao = dataAcao;
	}

}
