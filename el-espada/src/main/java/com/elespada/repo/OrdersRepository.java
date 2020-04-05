package com.elespada.repo;

import org.springframework.data.repository.CrudRepository;

import com.elespada.model.Orders;

public interface OrdersRepository extends CrudRepository<Orders, Long> {

}
