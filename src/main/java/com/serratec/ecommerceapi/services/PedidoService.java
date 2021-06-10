package com.serratec.ecommerceapi.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.serratec.ecommerceapi.exceptions.DatabaseException;
import com.serratec.ecommerceapi.exceptions.ResourceNotFoundException;
import com.serratec.ecommerceapi.models.Pedido;
import com.serratec.ecommerceapi.models.PedidoItem;
import com.serratec.ecommerceapi.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	PedidoRepository repository;

	@Autowired
	PedidoItemService itemService;

	public List<Pedido> list() {
		return repository.findAll();
	}

	public Pedido create(Pedido obj) {
		try {
			Pedido pedido = repository.save(obj);

			for (PedidoItem item : obj.getItens()) {

				item.setPedido(pedido);

				// itemService.create(item);
			}

			return pedido;

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}

	}

	public Pedido updateById(Long id, Pedido newObj) {

		try {
			Pedido oldObj = repository.getOne(id);

			updateObj(oldObj, newObj);

			for (PedidoItem item : oldObj.getItens()) {
				item.setPedido(oldObj);
			}
			return repository.save(oldObj);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	public Pedido findById(Long id) {
		Optional<Pedido> obj = repository.findById(id);

		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public void deleteById(Long id) {
		try {
			repository.deleteById(id);

		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);

		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Esse pedido não pôde ser excluido!");
		}
	}

	public PedidoItem updateItem(Long pedidoId, @Valid PedidoItem newObj, Long itemId) {
		try {

			Pedido pedido = repository.getOne(pedidoId);
			newObj.setPedido(pedido);

			return itemService.updateById(itemId, newObj);

		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(pedidoId);
		}

	}

	public void updateObj(Pedido oldObj, Pedido newObj) {
		oldObj.setCliente(newObj.getCliente());
		oldObj.setData(newObj.getData());
		oldObj.setItens(newObj.getItens());
	}


}
