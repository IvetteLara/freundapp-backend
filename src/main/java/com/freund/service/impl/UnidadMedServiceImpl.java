package com.freund.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.freund.model.UnidadMed;
import com.freund.repo.IUnidadMedRepo;
import com.freund.repo.IGenericRepo;
import com.freund.service.IUnidadMedService;

@Service
public class UnidadMedServiceImpl extends CRUDImpl<UnidadMed, Integer> implements IUnidadMedService {

	@Autowired
	private IUnidadMedRepo repo;
	
	@Override
	protected IGenericRepo<UnidadMed, Integer> getRepo(){
		return repo;
	}

}
	