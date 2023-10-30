package com.freund.repo;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.freund.model.Orden;

public interface IOrdenRepo extends IGenericRepo<Orden, Integer>{

	    @Query("FROM Orden c WHERE c.numOrden LIKE %:numOrden% OR c.cliente.numdoc LIKE %:numdoc% OR LOWER(c.cliente.nombres) LIKE %:nombreCompleto% OR LOWER(c.cliente.apellidos) LIKE %:nombreCompleto%")
		List<Orden> buscar(@Param("numOrden") String numOrden, @Param("numdoc") String numdoc, @Param("nombreCompleto") String nombreCompleto);
		
		@Query("FROM Orden c WHERE c.fecha BETWEEN :fechaOrden AND :fechaSgte")
		List<Orden> buscarFecha(@Param("fechaOrden") LocalDateTime fechaOrden, @Param("fechaSgte") LocalDateTime fechaSgte);

		@Query(value = "SELECT max(numOrden) FROM Orden")
		int getMaxOrden();
}
