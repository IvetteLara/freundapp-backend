package com.freund.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freund.model.Orden;

public interface IOrdenRepo extends IGenericRepo<Orden, Integer>{

	    @Query("FROM Orden c WHERE c.cliente.dui =:dui OR LOWER(c.cliente.nombres) LIKE %:nombreCompleto% OR LOWER(c.cliente.apellidos) LIKE %:nombreCompleto%")
		List<Orden> buscar(@Param("dui") String dui, @Param("nombreCompleto") String nombreCompleto);
		
		@Query("FROM Orden c WHERE c.fecha BETWEEN :fechaOrden AND :fechaSgte")
		List<Orden> buscarFecha(@Param("fechaOrden") LocalDateTime fechaOrden, @Param("fechaSgte") LocalDateTime fechaSgte);
		
		@Query(value = "select * from fn_listarResumen()", nativeQuery = true)
		List<Object[]> listarResumen();
}
