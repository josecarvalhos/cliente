package com.cliente.cliente.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cliente.cliente.model.entity.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

}
