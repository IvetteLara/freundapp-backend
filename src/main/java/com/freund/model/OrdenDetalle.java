package com.freund.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name ="orden_detalle")
public class OrdenDetalle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idDetalle;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_orden", nullable = false, foreignKey = @ForeignKey(name ="FK_orden_detalle"))	
	private Orden orden;
	
	@ManyToOne //FK
	@JoinColumn(name = "id_producto", nullable = false, foreignKey = @ForeignKey(name ="FK_orden_producto"))
	private Producto producto;

	@Column(name = "cantidad", nullable = false)
	private Integer cantidad = 0;

	@Column(name = "precio", nullable = false)
	private Double precio = 0.00;


	public Integer getIdDetalle() {
		return idDetalle;
	}

	public void setIdDetalle(Integer idDetalle) {
		this.idDetalle = idDetalle;
	}

	public Orden getOrden() {
		return orden;
	}

	public void setOrden(Orden orden) {
		this.orden = orden;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

}
