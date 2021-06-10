package com.serratec.ecommerceapi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.serratec.ecommerceapi.exceptions.DatabaseException;
import com.serratec.ecommerceapi.models.FuncionarioProduto;
import com.serratec.ecommerceapi.repositories.FuncionarioProdutoRepository;

@Service
public class FuncionarioProdutoService {

	@Autowired
	FuncionarioProdutoRepository repository;

	
	
	public FuncionarioProduto create(FuncionarioProduto obj) {
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

}
