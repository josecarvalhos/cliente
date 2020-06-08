package com.cliente.cliente.service;

import java.util.List;
import java.util.Optional;

import com.cliente.cliente.model.entity.Cliente;

public interface ClienteService {
	
	Cliente salvar(Cliente cliente);
	
	Cliente atualizar(Cliente cliente);
	
	void deletar(Cliente cliente);
	
	List<Cliente> buscar(Cliente cliente);
	
	void validar(Cliente cliente);
	
	Optional<Cliente> obterPorId(Long id);

}
