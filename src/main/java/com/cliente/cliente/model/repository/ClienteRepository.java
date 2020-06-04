package com.cliente.cliente.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cliente.cliente.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
