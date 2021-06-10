package com.serratec.ecommerceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.ecommerceapi.models.Funcionario;

public interface FuncioarioRepository extends JpaRepository<Funcionario,Long> {

}
