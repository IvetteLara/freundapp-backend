package com.freund.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.ArrayList;
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

import com.freund.dto.FiltroOrdenDTO;
import com.freund.exception.ModeloNotFoundException;
import com.freund.model.Orden;
import com.freund.service.IOrdenService;

@RestController
@RequestMapping("/ordenes")
public class OrdenController {

	@Autowired
	private IOrdenService service;
	
	//@Autowired
	//private IArchivoService serviceArchivo;
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Orden>> listarPageable(Pageable pageable) throws Exception {
		Page<Orden> lista = service.listarPageable(pageable);
		return new ResponseEntity<Page<Orden>>(lista, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<Orden>> listar() throws Exception {
		List<Orden> lista = service.listar();		
		return new ResponseEntity<List<Orden>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Orden> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Orden obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
		return new ResponseEntity<Orden>(obj, HttpStatus.OK);
	}
	
	@GetMapping("/hateoas/{id}")
	public EntityModel<Orden> listarPorIdHateoas(@PathVariable("id") Integer id) throws Exception {
		Orden obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
		
		EntityModel<Orden> recurso = EntityModel.of(obj);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(obj.getIdOrden()));
		recurso.add(linkTo.withRel("orden-recurso"));
		
		return recurso;
	}
	
	@PostMapping
	public ResponseEntity<Orden> registrar(@Valid @RequestBody Orden orden) throws Exception {
		orden.getDetalleOrden().forEach(det -> det.setOrden(orden));

		Orden obj = service.registrar(orden);
		return new ResponseEntity<Orden>(obj, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<Orden> modificar(@Valid @RequestBody Orden orden) throws Exception {
		orden.getDetalleOrden().forEach(det -> det.setOrden(orden));
 
		Orden obj = service.modificar(orden);
		return new ResponseEntity<Orden>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Orden obj = service.listarPorId(id);
		if(obj == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
		service.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping(value="/buscar")
	public ResponseEntity<List<Orden>> buscar(@RequestBody FiltroOrdenDTO filtro) throws Exception {
		List<Orden> lista = new ArrayList<>(); 
		if(filtro != null) {
			if(filtro.getFechaOrden() != null) {
				lista = service.buscarFecha(filtro);	
			} else {
				lista = service.buscar(filtro);	
			}
		}

		return new ResponseEntity<List<Orden>>(lista, HttpStatus.OK);
	}

}
