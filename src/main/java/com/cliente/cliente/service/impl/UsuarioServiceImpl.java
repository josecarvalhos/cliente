package com.cliente.cliente.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cliente.cliente.exception.ErroAutenticacao;
import com.cliente.cliente.model.entity.Usuario;
import com.cliente.cliente.model.repository.UsuarioRepository;
import com.cliente.cliente.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	private UsuarioRepository repository;
	
	@Autowired
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String usuario, long senha) {
		Optional<Usuario> usuarioEncontrado = repository.findByUsuario(usuario);
		if (!usuarioEncontrado.isPresent()) {
			throw new ErroAutenticacao("usuário não encontrdo.");
		}
		if (usuarioEncontrado.get().getSenha() != senha) {
			throw new ErroAutenticacao("senha inválida.");
		}
		return usuarioEncontrado.get();
	}

}
