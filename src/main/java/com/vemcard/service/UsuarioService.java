package com.vemcard.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vemcard.dtos.UsuarioDTO;
import com.vemcard.entities.UsuarioEntity;
import com.vemcard.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public void criarUsuario(UsuarioDTO usuario) {
		usuarioRepository.save(mapper.map(usuario, UsuarioEntity.class));
	}
	
	public List<UsuarioDTO> buscarUsuario() {
		Iterable<UsuarioEntity> usuariosBD = usuarioRepository.findAll();
		
		List<UsuarioDTO> retornoUsuarios = new ArrayList<>();
		
//		for (UsuarioEntity usuarioBD : usuariosBD) {
//			retornoUsuarios.add(mapper.map(usuarioBD, UsuarioDTO.class));
//		}
		
		usuariosBD.forEach(usuarioBD -> retornoUsuarios.add(mapper.map(usuarioBD, UsuarioDTO.class)));
		
		return retornoUsuarios;
	}
	
	public UsuarioDTO alterarUsuario(Integer id, UsuarioDTO usuarioDTO) {
		Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
		
		if (usuarioEntity.isPresent()) {
			alterarUsuarioEntity(usuarioDTO, usuarioEntity.get());
			usuarioRepository.save(usuarioEntity.get());
		} else {
			throw new RuntimeException("O usuario de id " + id + " não existe!");
		}
		
		return mapper.map(usuarioEntity.get(), UsuarioDTO.class);
	}
	
	public void deletarUsuario(Integer id) {
		Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(id);
		
		if (usuarioEntity.isPresent()) {
			usuarioRepository.delete(usuarioEntity.get());
		} else {
			throw new RuntimeException("O usuario de id " + id + " não existe!");
		}
	}
	
	private void alterarUsuarioEntity(UsuarioDTO usuarioDTO, UsuarioEntity usuarioEntity) {
		usuarioEntity.setNome(usuarioDTO.getNome());
		usuarioEntity.setEmail(usuarioDTO.getEmail());
		usuarioEntity.setSenha(usuarioDTO.getSenha());
	}
}
