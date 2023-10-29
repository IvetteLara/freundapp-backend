package com.freund.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "orden")
public class Orden {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idOrden;
		
	@Column(name ="num_orden", nullable = false)
	private String numOrden;

	@ManyToOne //FK
	@JoinColumn(name = "id_cliente", nullable = false, foreignKey = @ForeignKey(name ="FK_orden_cliente"))
	private Cliente cliente;
	
	@Column(name ="fecha", nullable = false)
	private LocalDateTime fecha;

	@Column(name = "monto", nullable = false)
	private Double monto = 0.00;

	@Column(name ="descripcion", nullable = false)
	private String descripcion;
	

	@OneToMany(mappedBy = "orden", cascade = {CascadeType.ALL}, orphanRemoval = true)	
	private List<OrdenDetalle> detalleOrden;

	
	public Integer getIdOrden() {
		return idOrden;
	}

	public void setIdOrden(Integer idOrden) {
		this.idOrden = idOrden;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getNumOrden() {
		return numOrden;
	}

	public void setNumOrden(String numOrden) {
		this.numOrden = numOrden;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Double getMonto() {
		return monto;
	}

	public void setMonto(Double monto) {
		this.monto = monto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<OrdenDetalle> getDetalleOrden() {
		return detalleOrden;
	}

	public void setDetalleOrden(List<OrdenDetalle> detalleOrden) {
		this.detalleOrden = detalleOrden;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idOrden == null) ? 0 : idOrden.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orden other = (Orden) obj;
		if (idOrden == null) {
			if (other.idOrden != null)
				return false;
		} else if (!idOrden.equals(other.idOrden))
			return false;
		return true;
	}
		
}