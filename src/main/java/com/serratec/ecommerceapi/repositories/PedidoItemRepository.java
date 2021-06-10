package com.serratec.ecommerceapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serratec.ecommerceapi.models.PedidoItem;

public interface PedidoItemRepository extends JpaRepository<PedidoItem, Long>{

}
