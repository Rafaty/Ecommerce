package com.serratec.ecommerceapi.services;

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
import com.serratec.ecommerceapi.models.Cliente;
import com.serratec.ecommerceapi.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository repository;

	public Page<Cliente> search(String searchTerm, int page, int size) {
		PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");

		return repository.search(searchTerm.toLowerCase(), pageRequest);
	}

	public Cliente create(Cliente obj) {
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

	public Cliente updateById(Long id, Cliente newObj) {
		try {
			Cliente oldObj = repository.getOne(id);

			updateObj(oldObj, newObj);
			return repository.save(oldObj);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	public Cliente findById(Long id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public void deleteById(Long id) {
		try {
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Esse cliente não pôde ser excluido!");
		}
	}

	public void updateObj(Cliente oldObj, Cliente newObj) {
		oldObj.setEmail(newObj.getEmail());
		oldObj.setEndereco(newObj.getEndereco());
		oldObj.setNome(newObj.getNome());
		oldObj.setSenha(newObj.getSenha());
	}

}
