package com.cliente.cliente;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cliente.cliente.api.dto.ClienteDTO;
import com.cliente.cliente.exception.RegraNegocioException;
import com.cliente.cliente.model.entity.Cliente;
import com.cliente.cliente.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteResource {
	
	private ClienteService service;
	
	
	public ClienteResource(ClienteService service) {
		this.service = service;
	}
	
	@GetMapping("/comParametro")
	public ResponseEntity buscarComPrametro(@RequestParam(value = "cpf") Long cpf) {
		
		Cliente clienteFiltro = new Cliente();
		clienteFiltro.setCpf(cpf);
		
		List<Cliente> clientes = service.buscar(clienteFiltro);
		
		return ResponseEntity.ok(clientes);

	}
	
	
	@GetMapping
	public ResponseEntity buscar() {
		Cliente clienteFiltro = new Cliente();
		
		List<Cliente> clientes = service.buscar(clienteFiltro);
		
		return ResponseEntity.ok(clientes);

	}
	
	@GetMapping("{id}")
	public ResponseEntity obterCliente(@PathVariable("id") Long id) {
		return service.obterPorId(id)
				.map(cliente -> new ResponseEntity(converter(cliente), HttpStatus.OK))
				.orElseGet( () -> new ResponseEntity(HttpStatus.NOT_FOUND));
	}

	@PostMapping
	public ResponseEntity salvar( @RequestBody ClienteDTO dto) {
		try {
			Cliente entidade = converter(dto);
			entidade = service.salvar(entidade);
			return new ResponseEntity(entidade, HttpStatus.CREATED);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("{id}")
	public ResponseEntity atualizar(@PathVariable("id") Long id, @RequestBody ClienteDTO dto) {
		return service.obterPorId(id).map(entity ->{
			try {
				Cliente cliente = converter(dto);
				cliente.setId(entity.getId());
				service.atualizar(cliente);
				return ResponseEntity.ok(cliente);
			} catch (RegraNegocioException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}).orElseGet( () -> 
			new ResponseEntity("Cliente não encontrado na base de dados", HttpStatus.BAD_REQUEST));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity deletar(@PathVariable("id") Long id) {
		return service.obterPorId(id).map(entidade ->{
			service.deletar(entidade);
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}).orElseGet( () -> 
			new ResponseEntity("Cliente não encontrado na base de dados", HttpStatus.BAD_REQUEST));
	}
	
	private ClienteDTO converter(Cliente cliente) {
		ClienteDTO dto = new ClienteDTO();
		
		dto.setId(cliente.getId());
		dto.setNome(cliente.getNome());
		dto.setLogradouro(cliente.getLogradouro());
		dto.setBairro(cliente.getBairro());
		dto.setCidade(cliente.getCidade());
		dto.setUf(cliente.getUf());
		dto.setComplemento(cliente.getComplemento());
		dto.setCpf(cliente.getCpf());
		dto.setCep(cliente.getCep());
		dto.setTelefones(cliente.getTelefones());
		dto.setEmails(cliente.getEmails());
		
		return dto;

	}
	
	private Cliente converter(ClienteDTO dto) {
		Cliente cliente = new Cliente();
		
		cliente.setId(dto.getId());
		cliente.setNome(dto.getNome());
		cliente.setLogradouro(dto.getLogradouro());
		cliente.setBairro(dto.getBairro());
		cliente.setCidade(dto.getCidade());
		cliente.setUf(dto.getUf());
		cliente.setComplemento(dto.getComplemento());
		cliente.setCpf(dto.getCpf());
		cliente.setCep(dto.getCep());
		cliente.setTelefones(dto.getTelefones());
		cliente.setEmails(dto.getEmails());
		
		return cliente;

	}

}
