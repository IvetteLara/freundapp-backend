package com.freund.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.freund.model.Cliente;

public interface IClienteService extends ICRUD<Cliente, Integer>{

	Page<Cliente> listarPageable(Pageable pageable);

}
