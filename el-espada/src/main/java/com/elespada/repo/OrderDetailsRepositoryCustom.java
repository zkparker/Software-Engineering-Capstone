package com.elespada.repo;

import java.util.List;

import com.elespada.model.OrderDetails;

public interface OrderDetailsRepositoryCustom {
	public List<OrderDetails> findByOrderId(Long orderId);
}
