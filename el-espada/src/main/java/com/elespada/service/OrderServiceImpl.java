/*
 * Copyright [2020] [ElEspada - Software Engineering Capstone - Springfield, IL]
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.elespada.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elespada.VO.PaymentVO;
import com.elespada.model.Menu;
import com.elespada.model.OrderDetails;
import com.elespada.model.Orders;
import com.elespada.repo.MenuRepository;
import com.elespada.repo.OrderDetailsRepository;
import com.elespada.repo.OrdersRepository;

/**
 * Service class to create, update, delete orders and order_details
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	OrdersRepository ordersRepository;

	@Autowired
	MenuRepository menuRepository;

	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	public static final String DELIMITER = ", ";

	/**
	 * Creates a new order
	 */
	@Override
	@Transactional
	public Orders createOrder() throws Exception {
		logger.debug("Creating New Order start");
		Orders order = new Orders();
		order.setCustomerName("New Customer");
		Orders newOrder = ordersRepository.save(order);
		logger.debug("New Order:"+newOrder);
		logger.debug("Creating New Order end");
		return newOrder;
	}

	/**
	 * Create new order details and persist items into order details
	 * 
	 * @param menuIds
	 * @param orderId
	 */
	@Override
	@Transactional
	public void createOrderDetails(String menuIds, Long orderId) throws Exception {
		logger.debug("Creating order details for order #:" + orderId);
		List<Long> idList = Stream.of(menuIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
		logger.debug("Adding items #:" + idList);
		for (Long id : idList) {
			OrderDetails orderDetail = new OrderDetails();
			orderDetail.setMenuId(id);
			orderDetail.setOrderId(orderId);
			orderDetail.setQuantity(1L);
			Optional<Menu> menuItem = menuRepository.findById(id);
			orderDetail.setUnitPrice(menuItem.get().getMenuPrice());
			orderDetailsRepository.save(orderDetail);
		}
		logger.debug("Creating order details end");
	}

	/**
	 * Deletes an item from existing order by menu id
	 * 
	 * @param orderId
	 * @param menuId
	 * @return List<Long>
	 */
	@Override
	@Transactional
	public List<Long> deleteItemfromOrder(Long orderId, Long menuId) throws Exception {
		logger.debug("Deleting item:" + menuId + ", from Order #:" + orderId);
		List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderId(orderId);
		Long odsId = null;
		List<Long> menuIds = new ArrayList<>();
		for (OrderDetails od : orderDetailsList) {
			if (od.getMenuId().equals(menuId)) {
				odsId = od.getId();
			} else {
				menuIds.add(od.getMenuId());
			}
		}
		orderDetailsRepository.deleteById(odsId);
		logger.debug("Deleting item end");
		return menuIds;
	}
	
	/**
	 * Updates payment details
	 * 
	 * @param orderId
	 * @param paymentDetails
	 * @return Orders
	 */
	@Override
	@Transactional
	public Orders updatePaymentDetails(Long orderId, PaymentVO paymentDetails) throws Exception {
		logger.debug("Updating payment details of order # "+orderId+" start");
		Orders existingOrder = findOrderById(orderId);
		populatePaymentDetails(paymentDetails, existingOrder);
		logger.debug("Saving above details into Order_Details start");
		Orders updatedOrder = ordersRepository.save(existingOrder);
		logger.debug("Saving above details into Order_Details end");
		logger.debug("Updating payment details end");
		return updatedOrder;
	}

	/**
	 * Fetches an order by id
	 * 
	 * @param orderId
	 * @return Orders
	 */
	@Override
	public Orders findOrderById(Long orderId) {
		Optional<Orders> Order = ordersRepository.findById(orderId);
		Orders existingOrder = Order.get();
		return existingOrder;
	}

	/**
	 * Populates the payment details from web form into DB related columns for an existing order
	 * 
	 * @param paymentDetails
	 * @param existingOrder
	 * @throws Exception
	 */
	private void populatePaymentDetails(PaymentVO paymentDetails, Orders existingOrder) throws Exception{
		logger.debug("Populating Payment Details start");
		String paymentType = paymentDetails.getPaymentType();
		String fullName = paymentDetails.getFirstName()+DELIMITER+paymentDetails.getLastName();
		StringBuilder sb = new StringBuilder();
		sb.append(paymentDetails.getAddressStreet())
			.append(DELIMITER).append(paymentDetails.getAddressCity())
			.append(DELIMITER).append(paymentDetails.getAddressState())
			.append(DELIMITER).append(paymentDetails.getAddressZip());
		String fullAddress = sb.toString();
		long millis=System.currentTimeMillis();
		Timestamp dateAndTime = new Timestamp(millis);
		float orderTotal = computeOrderTotal(existingOrder);
		logger.debug("Customer Name:"+fullName);
		logger.debug("Customer Address:"+fullAddress);
		logger.debug("Payment Type:"+paymentType);
		logger.debug("Date and Time:"+dateAndTime);
		logger.debug("Order Total:"+orderTotal);
		logger.debug("Setting above details into order_details start");
		existingOrder.setCustomerName(fullName);
		existingOrder.setAddress(fullAddress);
		existingOrder.setPaymentType(paymentType);
		existingOrder.setTimestamp(dateAndTime);
		existingOrder.setTotal(orderTotal);
		if(paymentType.equalsIgnoreCase("CARD")) {
			logger.debug("Setting Payment Detail start as payment type is :"+paymentType);
			//store only last 4 digits of the card due to security reasons
			String paymentDetail = paymentDetails.getCardNumber() != null ? paymentDetails.getCardNumber().substring(12):"CASH";
			logger.debug("Payment Detail:"+paymentDetail);
			existingOrder.setPaymentDetails(paymentDetail);
		}
		logger.debug("Order Details:"+existingOrder);
		logger.debug("Setting above details into Order_Details end");
		logger.debug("Populating Payment Details end");
	}

	/**
	 * Computes the order total
	 * 
	 * @param order
	 * @return float sum
	 */
	@Override
	public float computeOrderTotal(Orders order) {
		Float orderTotal = 0f;
		List<OrderDetails> orderDetails = orderDetailsRepository.findByOrderId(order.getOrderId());
		for(OrderDetails item : orderDetails) {
			orderTotal += item.getUnitPrice();
		}
		return orderTotal;
	}
	
	/**
	 * Deletes an order by id
	 */
	@Override
	@Transactional
	public void deleteOrder(Long orderId) {
		logger.debug("Delete Order Start:"+orderId);
		ordersRepository.deleteById(orderId);
		logger.debug("Delete Order End");
	}

}
