package com.serratec.ecommerceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.ecommerceapi.models.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
