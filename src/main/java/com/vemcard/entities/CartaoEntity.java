package com.vemcard.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.vemcard.dtos.TipoCartao;

import lombok.Data;

@Data
@Entity
@Table(name = "cartao")
public class CartaoEntity {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name = "numero", nullable = false)
	private Integer numero;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "status", nullable = false)
	private boolean status;
	
	@Column(name = "tipo", nullable = false)
	private TipoCartao tipo;
	
}
