package com.freund.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.freund.dto.FiltroOrdenDTO;
import com.freund.model.Orden;
import com.freund.repo.IGenericRepo;
import com.freund.repo.IOrdenRepo;
import com.freund.service.IOrdenService;

@Service
public class OrdenServiceImpl extends CRUDImpl<Orden, Integer> implements IOrdenService {

	@Autowired
	private IOrdenRepo repo;
	

	@Override
	protected IGenericRepo<Orden, Integer> getRepo() {
		return repo;
	}

	@Override
	public Page<Orden> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}

	@Override
	public List<Orden> buscar(FiltroOrdenDTO filtro) {
		return repo.buscar(filtro.getNumOrden(), filtro.getNumdoc(), filtro.getNombreCompleto());
	}

	@Override
	public List<Orden> buscarFecha(FiltroOrdenDTO filtro) {
		LocalDateTime fecha = LocalDateTime.parse(filtro.getFechaOrden());
		return repo.buscarFecha(fecha, fecha.plusDays(1));
	}

}