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
import javax.validation.constraints.Size;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información del Producto")
@Entity
@Table(name = "producto") //, schema = ""
public class Producto {

	@Schema(description = "Identificador del Producto")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	
	@Schema(description = "Nombre del Producto")	
	@Size(min = 3, message = "Nombre debe tener mínimo 3 caracteres")
	@Column(name = "nombre", nullable = false, length = 70)
	private String nombre;
	
	@Schema(description = "Precio del Producto")	
	@Column(name = "precio", nullable = false)
	private Double precio = 0.00;

	@Schema(description = "Unidad del Producto")	
	@ManyToOne //FK
	@JoinColumn(name = "id_unidad", nullable = false, foreignKey = @ForeignKey(name ="FK_producto_unidad"))
	private UnidadMed unidadMed;
	
	
	public Integer getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public UnidadMed getUnidadMed() {
		return unidadMed;
	}

	public void setUnidadMed(UnidadMed unidadMed) {
		this.unidadMed = unidadMed;
	}
		
}
