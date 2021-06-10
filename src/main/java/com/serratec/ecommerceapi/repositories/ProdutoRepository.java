package com.serratec.ecommerceapi.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.serratec.ecommerceapi.models.FuncionarioProduto;
import com.serratec.ecommerceapi.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

	@Query("select FP from FuncionarioProduto FP where FP.produto.id = :id ")
	List<FuncionarioProduto> getHistorico(@Param("id") Long id);

	@Query("FROM Produto p " + "WHERE LOWER(p.nome) like %:searchTerm% " + "OR LOWER(p.descricao) like %:searchTerm%")
	Page<Produto> search(@Param("searchTerm") String searchTerm, Pageable pageable);

}
