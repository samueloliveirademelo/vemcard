package com.vemcard.dtos;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class UsuarioDTO {

	private Integer id;
	
	@NotBlank(message = "Nome inválido")
	private String nome;
	
	@NotNull
	@Email(message = "Email inválido")
	private String email;
	
	@NotBlank(message = "Informe uma senha válida")
	private String senha;
	
	@Valid
	private List<CartaoDTO> cartoes = new ArrayList<>();
	
}
