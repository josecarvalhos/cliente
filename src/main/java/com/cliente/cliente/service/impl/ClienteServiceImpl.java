package com.cliente.cliente.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cliente.cliente.exception.RegraNegocioException;
import com.cliente.cliente.model.entity.Cliente;
import com.cliente.cliente.model.repository.ClienteRepository;
import com.cliente.cliente.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	private ClienteRepository repository;
	
	public ClienteServiceImpl(ClienteRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Cliente salvar(Cliente cliente) {
		validar(cliente);
		return repository.save(cliente);
	}

	@Override
	@Transactional
	public Cliente atualizar(Cliente cliente) {
		Objects.requireNonNull(cliente.getId());
		return repository.save(cliente);
	}

	@Override
	@Transactional
	public void deletar(Cliente cliente) {
		Objects.requireNonNull(cliente.getId());
		repository.delete(cliente);
	}

	@Override
	@Transactional
	public List<Cliente> buscar() {
		return repository.findAll();
	}

	@Override
	public void validar(Cliente cliente) {
		if (cliente.getNome() == null || cliente.getNome().trim().equals("") 
				|| cliente.getNome().trim().length() < 3) {
			throw new RegraNegocioException("Informe um nome válido.");
			
		}
		
	}

	@Override
	public Optional<Cliente> obterPorId(Long id) {
		return repository.findById(id);
	}


}
