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
import com.serratec.ecommerceapi.models.PedidoItem;
import com.serratec.ecommerceapi.repositories.PedidoItemRepository;

@Service
public class PedidoItemService implements IServiceCrud<PedidoItem> {

	@Autowired
	PedidoItemRepository repository;

	@Override
	public List<PedidoItem> list() {
		return repository.findAll();
	}

	@Override
	public PedidoItem create(PedidoItem obj) {
		try {
			return repository.save(obj);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public PedidoItem updateById(Long id, PedidoItem newObj) {

		try {
			
			PedidoItem oldObj = repository.getOne(id);
			updateObj(oldObj, newObj);
			return repository.save(oldObj);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	@Override
	public PedidoItem findById(Long id) {
		Optional<PedidoItem> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	@Override
	public void deleteById(Long id) {
		try {
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}

	@Override
	public void updateObj(PedidoItem oldObj, PedidoItem newObj) {
		oldObj.setPedido(newObj.getPedido());
		oldObj.setProduto(newObj.getProduto());
		oldObj.setQuantidade(newObj.getQuantidade());
		oldObj.setValor(newObj.getValor());
	}

}
