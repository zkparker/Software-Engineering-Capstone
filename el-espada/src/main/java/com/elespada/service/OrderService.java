package com.elespada.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.elespada.VO.PaymentVO;
import com.elespada.model.Orders;

@Component
public interface OrderService {
	public Orders createOrder() throws Exception;

	void createOrderDetails(String menuIds, Long orderId) throws Exception;

	List<Long> deleteItemfromOrder(Long orderId, Long menuId) throws Exception;

	Orders updatePaymentDetails(Long orderId, PaymentVO paymentDetails) throws Exception;

	void deleteOrder(Long orderId);

	float computeOrderTotal(Orders order);

	Orders findOrderById(Long orderId);
}
