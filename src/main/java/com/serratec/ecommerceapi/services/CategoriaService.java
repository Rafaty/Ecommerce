package com.serratec.ecommerceapi.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.serratec.ecommerceapi.exceptions.DatabaseException;
import com.serratec.ecommerceapi.exceptions.ResourceNotFoundException;
import com.serratec.ecommerceapi.interfaces.IServiceCrud;
import com.serratec.ecommerceapi.models.Categoria;
import com.serratec.ecommerceapi.repositories.CategoriaRepository;

@Service
public class CategoriaService implements IServiceCrud<Categoria> {

	@Autowired
	CategoriaRepository repository;

	@Override
	public List<Categoria> list() {
		return repository.findAll();
	}

	@Override
	public Categoria create(Categoria obj) {
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public Categoria updateById(Long id, Categoria newObj) {

		try {
			Categoria oldObj = repository.getOne(id);
			updateObj(oldObj, newObj);
			return repository.save(oldObj);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	@Override
	public Categoria findById(Long id) {
		Optional<Categoria> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Override
	public void deleteById(Long id) {
		try {
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Essa categoria não pôde ser excluida!");
		}
	}

	@Override
	public void updateObj(Categoria oldObj, Categoria newObj) {
		oldObj.setDescricao(newObj.getDescricao());
		oldObj.setNome(newObj.getNome());
	}

}
