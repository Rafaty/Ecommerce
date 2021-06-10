package com.serratec.ecommerceapi.controllers;

import java.net.URI;
import java.util.List;

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

import com.serratec.ecommerceapi.models.FuncionarioProduto;
import com.serratec.ecommerceapi.models.Produto;
import com.serratec.ecommerceapi.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	ProdutoService service;

	@GetMapping
	public ResponseEntity<Page<Produto>> search(@RequestParam(name = "filtro", defaultValue = "") String searchTerm,
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "10") int size) {
		return ResponseEntity.ok().body(service.search(searchTerm, page, size));

	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> selectById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}

	@GetMapping("/{id}/historico")
	public ResponseEntity<List<FuncionarioProduto>> getHistorico(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.historico(id));
	}

	@PostMapping("/criar")
	public ResponseEntity<Produto> insert(@Valid @RequestBody Produto obj) {

		obj = service.create(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/produtos/{id}")
				.buildAndExpand(obj.getId()).toUri();

		return ResponseEntity.created(uri).body(obj);
	}

	@PutMapping("/editar/{id}")
	public ResponseEntity<Produto> update(@PathVariable Long id, @Valid @RequestBody Produto newObj) {
		return ResponseEntity.ok().body(service.updateById(id, newObj));
	}
	

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.deleteById(id);

	}

}
