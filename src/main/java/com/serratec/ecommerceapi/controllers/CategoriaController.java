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
import com.serratec.ecommerceapi.models.Categoria;
import com.serratec.ecommerceapi.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController implements IControllerCrud<Categoria> {

	@Autowired
	CategoriaService service;

	@Override
	@GetMapping
	public ResponseEntity<List<Categoria>> select() {
		return ResponseEntity.ok().body(service.list());
	}
	
	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> selectById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@Override
	@PostMapping("/criar")
	public ResponseEntity<Categoria> insert(@Valid @RequestBody Categoria obj) {
		
		obj = service.create(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/categorias/{id}")
				.buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).body(obj);
	}

	@Override
	@PutMapping("/editar/{id}")
	public ResponseEntity<Categoria> update(@PathVariable Long id, @Valid @RequestBody Categoria newObj) {
		return ResponseEntity.ok().body(service.updateById(id, newObj));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);
	}


}
