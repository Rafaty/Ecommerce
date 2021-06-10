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
import com.serratec.ecommerceapi.models.Funcionario;
import com.serratec.ecommerceapi.services.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController implements IControllerCrud<Funcionario> {

	@Autowired
	FuncionarioService service;

	@Override
	@GetMapping
	public ResponseEntity<List<Funcionario>> select() {
		return ResponseEntity.ok().body(service.list());
	}

	@Override
	@GetMapping("/{id}")
	public ResponseEntity<Funcionario> selectById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@Override
	@PostMapping("/criar")
	public ResponseEntity<Funcionario> insert(@Valid @RequestBody Funcionario obj) {

		obj = service.create(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/Funcionarios/{id}")
				.buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).body(obj);
	}

	@Override
	@PutMapping("/editar/{id}")
	public ResponseEntity<Funcionario> update(@PathVariable Long id, @Valid @RequestBody Funcionario newObj) {
		return ResponseEntity.ok().body(service.updateById(id, newObj));
	}

	@Override
	@DeleteMapping("{/id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);

	}

}
