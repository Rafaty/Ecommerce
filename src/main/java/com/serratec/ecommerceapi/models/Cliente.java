package com.serratec.ecommerceapi.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="cliente")
public class Cliente implements Serializable {

	private static final long serialVersionUID = 2437032814197341551L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty
	@Size(min = 4, max = 30)
	private String nome;

	@NotEmpty
	@Size(min = 4, max = 30)
	private String nomeCompleto;

	@Email
	@Column(unique = true)
	private String email;

	@CPF
	@NotEmpty
	@Column(unique = true)
	private String cpf;

	@Past
	@Temporal(TemporalType.DATE)
	@NotNull
	@JsonFormat(pattern = "dd-MM-yyy", timezone = "America/Sao_Paulo")
	private Date dataNascimento;

	@Size(min = 6, max = 15)
	@JsonIgnore
	private String senha;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	@Valid
	private Endereco endereco;

	public Cliente() {
		super();
	}

	public Cliente(Long id, String nome, String nomeCompleto, String email, String cpf, Date dataNascimento,
			String senha, Endereco endereco) {
		super();
		this.id = id;
		this.nome = nome;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
		this.senha = senha;
		this.endereco = endereco;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}



	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
