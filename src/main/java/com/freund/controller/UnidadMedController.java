package com.freund.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.freund.exception.ModeloNotFoundException;
import com.freund.model.UnidadMed;
import com.freund.service.IUnidadMedService;

@RestController
@RequestMapping("/unidades")
public class UnidadMedController {

	@Autowired
	private IUnidadMedService service;
	
	@PreAuthorize("@authServiceImpl.tieneAcceso('listar')")
	//@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@GetMapping
	public ResponseEntity<List<UnidadMed>> listar() throws Exception {
		List<UnidadMed> lista = service.listar();
		return new ResponseEntity<List<UnidadMed>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UnidadMed> listarPorId(@PathVariable Integer id) throws Exception {
		UnidadMed obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		}
		return new ResponseEntity<UnidadMed>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<UnidadMed> listarPorIdHateoas(@PathVariable Integer id) throws Exception {
		UnidadMed obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		}
		
		//localhost:8080/UnidadMeds/{id}
		
		EntityModel<UnidadMed> recurso = EntityModel.of(obj);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		recurso.add(linkTo.withRel("unidades-recurso"));
		
		return recurso;
	}
	
	/*
	@PostMapping
	public ResponseEntity<UnidadMed> registrar(@Valid @RequestBody UnidadMed unidadMed) throws Exception {
		UnidadMed obj = service.registrar(unidadMed);
		return new ResponseEntity<UnidadMed>(obj, HttpStatus.CREATED);
	}
	*/
	
	@PostMapping
	public ResponseEntity<Void> registrar(@Valid @RequestBody UnidadMed unidadMed) throws Exception {
		UnidadMed obj = service.registrar(unidadMed);

		//localhost:8080/unidades/5
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdUnidad()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<UnidadMed> modificar(@Valid @RequestBody UnidadMed unidadMed) throws Exception {
		UnidadMed obj = service.modificar(unidadMed);
		return new ResponseEntity<UnidadMed>(obj, HttpStatus.OK);
	}
		
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable Integer id) throws Exception {
		UnidadMed obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		}		
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
