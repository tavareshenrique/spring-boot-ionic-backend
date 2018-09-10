package com.henriquetavares.cursomc.repositories;

import com.henriquetavares.cursomc.domain.Cliente;
import com.henriquetavares.cursomc.domain.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Transactional
    Page<Pedido> findByCliente(Cliente cliente, Pageable pageRequest);

}