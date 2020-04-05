package com.elespada.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.elespada.model.OrderDetails;

public class OrderDetailsRepositoryImpl implements OrderDetailsRepositoryCustom {

	@Override
	@Query("select * from OrderDetails where orderId=:orderId")
	public List<OrderDetails> findByOrderId(@Param("orderId") Long orderId) {
		return null;
	}

}
