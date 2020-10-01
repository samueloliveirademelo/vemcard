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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vemcard.dtos.CartaoDTO;
import com.vemcard.service.CartaoService;

@RestController
@RequestMapping("vemcardapp")
public class CartaoController {

	@Autowired
	private CartaoService service;
	
	@PostMapping("/v1/cartao/{idUsuario}")
	public ResponseEntity<Void> adicionarCartao(@PathVariable("idUsuario") Integer idUsuario,
			@RequestBody @Valid CartaoDTO cartao) {
		service.adicionarCartao(idUsuario, cartao);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@DeleteMapping("/v1/cartao/{idCartao}")
	public ResponseEntity<Void> deletarCartao(@PathVariable("idCartao") Integer idCartao) {
		service.deletarCartao(idCartao);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/v1/cartao")
	public ResponseEntity<List<CartaoDTO>> buscarCartoes() {
		return ResponseEntity.ok().body(service.buscarCartoes());
	}
	
	@PostMapping("/v1/cartao/desativar/{idCartao}")
	public ResponseEntity<Void> desativarCartao(@PathVariable("idCartao") Integer idCartao) {
		service.mudarStatus(idCartao, false);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/v1/cartao/ativar/{idCartao}")
	public ResponseEntity<Void> ativarCartao(@PathVariable("idCartao") Integer idCartao) {
		service.mudarStatus(idCartao, true);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
