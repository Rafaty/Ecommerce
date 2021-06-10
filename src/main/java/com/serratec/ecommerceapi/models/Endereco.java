package com.serratec.ecommerceapi.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="endereco")
public class Endereco implements Serializable {

	
	private static final long serialVersionUID = 5326926448384160773L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 5 ,max = 40)
	private String rua;
	
	@Size(min = 5 ,max = 40)
	private String bairro;
	
	@NotNull
	private Integer numero;
	
	@Size(min = 8 ,max = 9)
	private String cep;
	
	@Size(min = 3 ,max = 40)
	private String cidade;
	
	@NotNull
	@Size(min = 2 ,max = 2)
	private String estado;
	
	private String complemento;

	public Endereco() {
		super();
	}

	public Endereco(Long id, String rua, String bairro, Integer numero, String cep, String cidade, String estado,
			String complemento) {
		super();
		this.id = id;
		this.rua = rua;
		this.bairro = bairro;
		this.numero = numero;
		this.cep = cep;
		this.cidade = cidade;
		this.estado = estado;
		this.complemento = complemento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}	
	
}
