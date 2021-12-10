package com.qbo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qbo.model.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	
}
