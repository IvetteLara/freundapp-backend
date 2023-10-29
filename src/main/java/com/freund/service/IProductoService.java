package com.freund.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.freund.model.Producto;

public interface IProductoService extends ICRUD<Producto, Integer>{

	Page<Producto> listarPageable(Pageable pageable);
	
}
