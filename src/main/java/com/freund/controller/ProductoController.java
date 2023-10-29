package com.freund.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import com.freund.exception.ModeloNotFoundException;
import com.freund.model.Producto;
import com.freund.service.IProductoService;

@RestController
@RequestMapping("/productos")
public class ProductoController {

	@Autowired
	private IProductoService service;
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Producto>> listarPageable(Pageable pageable) throws Exception {
		Page<Producto> lista = service.listarPageable(pageable);
		return new ResponseEntity<Page<Producto>>(lista, HttpStatus.OK);
	}
	
	
	@GetMapping
	public ResponseEntity<List<Producto>> listar() throws Exception {
		List<Producto> lista = service.listar();
		return new ResponseEntity<List<Producto>>(lista, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Producto obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " +id);
		}
		return new ResponseEntity<Producto>(obj, HttpStatus.OK);
	}	
	
	
	//https://docs.spring.io/spring-hateoas/docs/current/reference/html/
	//Hateoas 0.9 => Spring Boot 1.5 y 2.1
	//Hateoas 1.0 => Spring Boot 2.2
	//Hateoas 1.1 => Spring Boot 2.3
	@GetMapping("/hateoas/{id}")
	public EntityModel<Producto> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
		Producto obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " +id);
		}
		
		//localhost:8080/productos/{id}
		
		EntityModel<Producto> recurso =  EntityModel.of(obj);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(linkTo.withRel("producto-recurso"));
		return recurso;
	}
	
	/*
	@PostMapping
	public ResponseEntity<Producto> registrar(@Valid @RequestBody Producto producto) throws Exception {
		Producto obj = service.registrar(producto);		
		return new ResponseEntity<Producto>(obj, HttpStatus.CREATED);
	}
	*/
		
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody Producto producto) throws Exception {
		Producto obj = service.registrar(producto);
		
		//localhost:8080/productos/5
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getIdProducto()).toUri();
		
		return ResponseEntity.created(location).build();
	}	
	
	
	@PutMapping
	public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto producto) throws Exception {
		Producto obj = service.modificar(producto);
		return new ResponseEntity<Producto>(obj, HttpStatus.OK);
	}
		
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Producto obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
