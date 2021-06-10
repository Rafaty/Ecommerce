package com.serratec.ecommerceapi.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.serratec.ecommerceapi.models.Cliente;
import com.serratec.ecommerceapi.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	ClienteService service;

	@GetMapping
	public ResponseEntity<Page<Cliente>> select(@RequestParam(name = "filtro", defaultValue = "") String searchTerm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return ResponseEntity.ok().body(service.search(searchTerm, page, size));

	}

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> selectById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@PostMapping("/criar")
	public ResponseEntity<Cliente> insert(@Valid @RequestBody Cliente obj) {
		obj = service.create(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/clientes/{id}")
				.buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<Cliente> update(@PathVariable Long id, @Valid @RequestBody Cliente newObj) {
		return ResponseEntity.ok().body(service.updateById(id, newObj));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);

	}

}
