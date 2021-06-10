package com.serratec.ecommerceapi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.serratec.ecommerceapi.exceptions.DatabaseException;
import com.serratec.ecommerceapi.exceptions.ResourceNotFoundException;
import com.serratec.ecommerceapi.models.FuncionarioProduto;
import com.serratec.ecommerceapi.models.Produto;
import com.serratec.ecommerceapi.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	ProdutoRepository repository;

	@Autowired
	FuncionarioProdutoService funcionarioProdutoService;

	public Produto create(Produto obj) {
		try {

			Produto produto = repository.save(obj);
			FuncionarioProduto fp1 = new FuncionarioProduto(null, obj.getFuncionario(), produto, "Cadastrou",
					new Date());
			funcionarioProdutoService.create(fp1);

			return produto;

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	public Produto updateById(Long id, Produto newObj) {

		try {
			Produto oldObj = repository.getOne(id);
			updateObj(oldObj, newObj);

			Produto produto = repository.save(oldObj);
			FuncionarioProduto fp1 = new FuncionarioProduto(null, newObj.getFuncionario(), produto, "Editou",
					new Date());
			funcionarioProdutoService.create(fp1);
			return produto;

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	public Produto findById(Long id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public void deleteById(Long id) {
		try {
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Esse produto não pôde ser excluido!");
		}
	}

	public void updateObj(Produto oldObj, Produto newObj) {
		oldObj.setDescricao(newObj.getDescricao());
		oldObj.setNome(newObj.getNome());
		oldObj.setDataFabricacao(newObj.getDataFabricacao());
	}

	public List<FuncionarioProduto> historico(Long id) {

		List<FuncionarioProduto> listaDeHistorico = repository.getHistorico(id);

		if (listaDeHistorico.isEmpty()) {
			throw new ResourceNotFoundException(id);
		}

		return listaDeHistorico;

	}

	public Page<Produto> search(String searchTerm, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");

		return repository.search(searchTerm.toLowerCase(), pageRequest);
	}

	

}
