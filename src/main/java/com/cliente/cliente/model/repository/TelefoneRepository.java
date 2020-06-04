package com.cliente.cliente.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cliente.cliente.model.entity.Telefone;

public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

}
