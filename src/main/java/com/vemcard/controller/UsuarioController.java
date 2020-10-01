package com.vemcard.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vemcard.dtos.UsuarioDTO;
import com.vemcard.service.UsuarioService;

@RestController
@RequestMapping("vemcardapp")
public class UsuarioController {

	@Autowired
	private UsuarioService service;

	@PostMapping("/v1/usuario")
	public ResponseEntity<Void> criarUsuario(@RequestBody @Valid UsuarioDTO usuario) {
		service.criarUsuario(usuario);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@GetMapping("/v1/usuario")
	public ResponseEntity<List<UsuarioDTO>> buscarUsuarios() {
		return ResponseEntity.ok().body(service.buscarUsuario());
	}

	@PutMapping("/v1/usuario/{id}")
	public ResponseEntity<UsuarioDTO> alterarUsuario(@PathVariable("id") Integer id,
			@RequestBody @Valid UsuarioDTO usuarioDTO) {
		return ResponseEntity.ok().body(service.alterarUsuario(id, usuarioDTO));
	}

	@DeleteMapping("/v1/usuario/{id}")
	public ResponseEntity<Void> deletarUsuario(@PathVariable("id") Integer id) {
		service.deletarUsuario(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
