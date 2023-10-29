package com.freund.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.freund.dto.FiltroOrdenDTO;
import com.freund.dto.OrdenResumenDTO;
import com.freund.model.Orden;

public interface IOrdenService extends ICRUD<Orden, Integer>{

	Page<Orden> listarPageable(Pageable pageable);

	List<Orden> buscar(FiltroOrdenDTO filtro);
	
	List<Orden> buscarFecha(FiltroOrdenDTO filtro);
	
	List<OrdenResumenDTO> listarResumen();
	
	byte[] generarReporte();
	
}
