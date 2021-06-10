package com.serratec.ecommerceapi.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.serratec.ecommerceapi.interfaces.IControllerCrud;
import com.serratec.ecommerceapi.models.Pedido;
import com.serratec.ecommerceapi.models.PedidoItem;
import com.serratec.ecommerceapi.services.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoController implements IControllerCrud<Pedido> {

	@Autowired
	PedidoService service;

	@Override
	@GetMapping
	public ResponseEntity<List<Pedido>> select() {
		return ResponseEntity.ok().body(service.list());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Pedido> selectById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@Override
	@PostMapping("/criar")
	public ResponseEntity<Pedido> insert(@Valid @RequestBody Pedido obj) {
		obj = service.create(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/pedidos/{id}").buildAndExpand(obj.getId())
				.toUri();

		return ResponseEntity.created(uri).body(obj);
	}

	@Override
	@PutMapping("/editar/{id}")
	public ResponseEntity<Pedido> update(@PathVariable Long id, @Valid @RequestBody Pedido newObj) {
		return ResponseEntity.ok().body(service.updateById(id, newObj));
	}

	@PutMapping("/{pedidoId}/itens/{itemId}")
	public ResponseEntity<PedidoItem> updateItem(@PathVariable Long pedidoId, @Valid @RequestBody PedidoItem newObj,
			@PathVariable Long itemId) {
		return ResponseEntity.ok().body(service.updateItem(pedidoId, newObj, itemId));
	}


	@Override
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);

	}

}
