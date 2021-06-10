package com.serratec.ecommerceapi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serratec.ecommerceapi.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente,Long> {

	@Query("FROM Cliente c " + "WHERE LOWER(c.nome) like %:searchTerm% " + "OR LOWER(c.email) like %:searchTerm%")
	Page<Cliente> search(@Param("searchTerm") String searchTerm, Pageable pageable);
	
}
