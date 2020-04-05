package com.elespada.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.elespada.model.Orders;

@Component
public interface OrderService {
	public Orders createOrder();

	void createOrderDetails(String menuIds, Long orderId);

	List<Long> deleteItemfromOrder(Long orderId, Long menuId);
}
