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
import com.serratec.ecommerceapi.models.Funcionario;
import com.serratec.ecommerceapi.repositories.FuncioarioRepository;

@Service
public class FuncionarioService implements IServiceCrud<Funcionario> {

	@Autowired
	FuncioarioRepository repository;

	@Override
	public List<Funcionario> list() {
		return repository.findAll();
	}

	@Override
	public Funcionario create(Funcionario obj) {
		try {
			return repository.save(obj);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public Funcionario updateById(Long id, Funcionario newObj) {

		try {
			Funcionario oldObj = repository.getOne(id);
			updateObj(oldObj, newObj);
			return repository.save(oldObj);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	@Override
	public Funcionario findById(Long id) {
		Optional<Funcionario> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Override
	public void deleteById(Long id) {
		try {
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Esse Funcionario não pode ser excluido!");
		}
	}

	@Override
	public void updateObj(Funcionario oldObj, Funcionario newObj) {
		oldObj.setCpf(newObj.getCpf());
		oldObj.setEndereco(newObj.getEndereco());
		oldObj.setNome(newObj.getNome());
		oldObj.setSenha(newObj.getSenha());
	}

}
