package com.vemcard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vemcard.dtos.CartaoDTO;
import com.vemcard.entities.CartaoEntity;
import com.vemcard.entities.UsuarioEntity;
import com.vemcard.repositories.CartaoRepository;
import com.vemcard.repositories.UsuarioRepository;

@Service
public class CartaoService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private CartaoRepository cartaoRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public void adicionarCartao(Integer idUsuario, CartaoDTO cartao) {
		UsuarioEntity usuarioEntity = buscarUsuario(idUsuario);
		usuarioEntity.getCartoes().add(mapper.map(cartao, CartaoEntity.class));
		usuarioRepository.save(usuarioEntity);
	}
	
	public void deletarCartao(Integer idCartao) {
		buscarCartao(idCartao);
		cartaoRepository.deleteById(idCartao);
	}
	
	public void mudarStatus(Integer idCartao, boolean status) {
		CartaoEntity cartaoEntity = buscarCartao(idCartao);
		
		if (status && cartaoEntity.isStatus()) {
			throw new RuntimeException("O cartão já está ativado!");
		}
		
		if (!status && !cartaoEntity.isStatus()) {
			throw new RuntimeException("O cartão já está desativado!");
		}
		
		cartaoEntity.setStatus(status);
		
		cartaoRepository.save(cartaoEntity);
	}
	
	public List<CartaoDTO> buscarCartoes() {
		Iterable<CartaoEntity> cartoesEntity = cartaoRepository.findAll();
		
		List<CartaoDTO> cartoesDTO = new ArrayList<>();
		
		cartoesEntity.forEach(cartaoEntity -> cartoesDTO.add(mapper.map(cartaoEntity, CartaoDTO.class)));
		
		return cartoesDTO;
	}
	
	private UsuarioEntity buscarUsuario(Integer idUsuario) throws RuntimeException {
		Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(idUsuario);
		
		if (!usuarioEntity.isPresent()) {
			throw new RuntimeException("O usuario de id " + idUsuario + " não existe!");
		}
		
		return usuarioEntity.get();
	}
	
	private CartaoEntity buscarCartao(Integer idCartao) throws RuntimeException {
		Optional<CartaoEntity> cartaoEntity = cartaoRepository.findById(idCartao);
		
		if (!cartaoEntity.isPresent()) {
			throw new RuntimeException("O usuario de id " + idCartao + " não existe!");
		}
		
		return cartaoEntity.get();
	}
	
}
