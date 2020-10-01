package com.vemcard.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class CartaoDTO {
	
	private Integer id;
	
	@NotNull(message = "Informe o número do cartão")
	private Integer numero;
	
	@NotBlank(message = "Informe o nome do cartão")
	private String nome;
	
	@NotNull
	private boolean status;
	
	private TipoCartao tipo;

}
