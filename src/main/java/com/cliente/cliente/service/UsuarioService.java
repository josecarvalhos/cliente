package com.cliente.cliente.service;

import com.cliente.cliente.model.entity.Usuario;

public interface UsuarioService {
	
	Usuario autenticar(String usuario, String senha);

}
