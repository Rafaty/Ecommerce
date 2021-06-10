package com.serratec.ecommerceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.ecommerceapi.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
