package com.freund.service.impl;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.freund.dto.FiltroOrdenDTO;
import com.freund.dto.OrdenResumenDTO;
import com.freund.model.Orden;
import com.freund.repo.IGenericRepo;
import com.freund.repo.IOrdenRepo;
import com.freund.service.IOrdenService;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
		return repo.buscar(filtro.getDui(), filtro.getNombreCompleto());
	}

	@Override
	public List<Orden> buscarFecha(FiltroOrdenDTO filtro) {
		LocalDateTime fecha = LocalDateTime.parse(filtro.getFechaOrden());
		return repo.buscarFecha(fecha, fecha.plusDays(1));
	}

	@Override
	public List<OrdenResumenDTO> listarResumen() {
		//cantidad		fecha  List<Object[]>
		//[1,	"03/07/2020"]
		//[6,	"04/07/2020"]
		//[1,	"13/06/2020"]
		//[1,	"27/06/2020"]
		
		List<OrdenResumenDTO> consultas = new ArrayList<>();
		repo.listarResumen().forEach(x -> {
			OrdenResumenDTO cr = new OrdenResumenDTO();
			cr.setCantidad(Integer.parseInt(String.valueOf(x[0])));
			cr.setFecha(String.valueOf(x[1]));
			consultas.add(cr);
		});
		
		return consultas;
	}

	@Override
	public byte[] generarReporte() {
		byte[] data = null;
		
		try {
			File file = new ClassPathResource("/reports/orden.jasper").getFile();
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("txt_titulo", "Prueba de Titulo");
			JasperPrint print = JasperFillManager.fillReport(file.getPath(), parameters, new JRBeanCollectionDataSource(this.listarResumen()));
			data = JasperExportManager.exportReportToPdf(print);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
