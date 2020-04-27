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
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elespada.VO.PaymentVO;
import com.elespada.model.Menu;
import com.elespada.model.OrderDetails;
import com.elespada.model.Orders;
import com.elespada.repo.OrderDetailsRepository;
import com.elespada.repo.OrdersRepository;

/**
 * <b>OrderServiceImpl.java implements <b>OrderService</b>
 * interface</b><blockquote>Service Class that interacts with Model in MVC
 * pattern
 * <p>
 * This class handles all transaction that are necessary for interacting with
 * OrdersRepository and OrderDetailsRepository. Service class to create, update,
 * delete from tables ORDERS and ORDER_DETAILS. All the methods that interact
 * with DB are transactional
 *
 */
@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired
	OrdersRepository ordersRepository; // used for CRUD operations on ORDERS table

	@Autowired
	MenuService menuService; // used for MENU specific services

	@Autowired
	OrderDetailsRepository orderDetailsRepository; // used for CRUD operations on ORDER_DETAILS table

	public static final String DELIMITER = ", ";

	@Override
	@Transactional
	public Orders createOrder() throws Exception {
		logger.debug("Creating New Order start");

		// Create a new ORDER object
		Orders order = new Orders();

		// set name as customer name as "New Customer" until payment is done
		order.setCustomerName("New Customer");

		// save the new ORDER
		Orders newOrder = ordersRepository.save(order);

		logger.debug("New Order:" + newOrder);
		logger.debug("Creating New Order end");
		return newOrder;
	}

	@Override
	@Transactional
	public void createOrderDetails(String menuIds, Long orderId) throws Exception {
		logger.debug("Creating order details for order #:" + orderId);

		// convert comma separated menu id's to long list
		List<Long> idList = menuService.convertStringToList(menuIds);
		logger.debug("Adding items #:" + idList);

		// Traverse the list and add menu id and item price to ORDER_DETAILS table
		for (Long id : idList) {
			OrderDetails orderDetail = new OrderDetails();
			orderDetail.setMenuId(id);

			// set the order id the order details belong to
			orderDetail.setOrderId(orderId);

			// retrieve the menu by id for saving the menu item price
			Menu menuItem = menuService.retrieveMenuByMenuId(id);
			orderDetail.setUnitPrice(menuItem.getMenuPrice());

			// save the order details to DB
			orderDetailsRepository.save(orderDetail);
		}
		logger.debug("Creating order details end");
	}

	@Override
	@Transactional
	public List<Long> deleteItemfromOrder(Long orderId, Long menuId) throws Exception {
		logger.debug("Deleting item:" + menuId + ", from Order #:" + orderId);

		// Fetch all the order details by the order id from table ORDER_DETAILS
		List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderId(orderId);
		Long odsId = 0L;
		List<Long> menuIds = new ArrayList<>();

		// Traverse through the list to find the menu item by comparing the id
		Iterator<OrderDetails> iterator = orderDetailsList.iterator();

		while (iterator.hasNext()) {
			OrderDetails od = iterator.next();
			// find the match by comparing the menu primary key of type long
			if (od.getMenuId().equals(menuId)) {

				// once a match is found save the id in temporary variable
				odsId = od.getId();

				// remove from the list
				iterator.remove();

				// break from the loop as we do not need to look further
				// as of now we only allow the user to delete one item at a time
				break;
			}
		}

		// now delete the menu item from ORDER_DETAILS table by using the id saved in
		// the temporary variable
		orderDetailsRepository.deleteById(odsId);

		// Retrieve the menu list for the remaining items in the order and return
		for (OrderDetails od : orderDetailsList) {
			menuIds.add(od.getMenuId());
		}
		logger.debug("Deleting item end");
		return menuIds;
	}

	@Override
	@Transactional
	public Orders updatePaymentDetails(Long orderId, PaymentVO paymentDetails) throws Exception {
		logger.debug("Updating payment details of order # " + orderId + " start");

		// find the existing order based on orderId
		Orders existingOrder = findOrderById(orderId);

		// set all the payment details from the VO to the DB object Orders
		populatePaymentDetails(paymentDetails, existingOrder);
		logger.debug("Saving above details into Order_Details start");

		// save them into the ORDERS tables, save will do save or update
		// if there is a primary key it will update otherwise new entry is created
		Orders updatedOrder = ordersRepository.save(existingOrder);
		logger.debug("Saving above details into Order_Details end");

		logger.debug("Updating payment details end");
		return updatedOrder;
	}

	@Override
	@Transactional
	public Orders findOrderById(Long orderId) {
		logger.debug("findOrderById");

		// fetch single order record from table ORDERS
		return ordersRepository.findById(orderId).get();
	}

	/**
	 * This method copies the payment details from web form into DB related columns
	 * for an existing order
	 *
	 * @param paymentDetails
	 * @param existingOrder
	 * @throws Exception
	 */
	private void populatePaymentDetails(PaymentVO paymentDetails, Orders existingOrder) throws Exception {
		logger.debug("Populating Payment Details start");

		// payment type card or cash
		String paymentType = paymentDetails.getPaymentType();

		// Concatenate first name and last name as full name
		String fullName = paymentDetails.getFirstName() + DELIMITER + paymentDetails.getLastName();

		// append all the address fields into single full address field
		StringBuilder sb = new StringBuilder();
		sb.append(paymentDetails.getAddressStreet()).append(DELIMITER).append(paymentDetails.getAddressCity())
				.append(DELIMITER).append(paymentDetails.getAddressState()).append(DELIMITER)
				.append(paymentDetails.getAddressZip());
		String fullAddress = sb.toString();

		// get the timestamp when the payment was saved
		long millis = System.currentTimeMillis();
		Timestamp dateAndTime = new Timestamp(millis);

		// aggregate the sum of all the menu items in the order
		float orderTotal = computeOrderTotal(existingOrder);

		logger.debug("Customer Name:" + fullName);
		logger.debug("Customer Address:" + fullAddress);
		logger.debug("Payment Type:" + paymentType);
		logger.debug("Date and Time:" + dateAndTime);
		logger.debug("Order Total:" + orderTotal);
		logger.debug("Setting above details into order_details start");

		// now set all the above fields into the existing order entity
		existingOrder.setCustomerName(fullName);
		existingOrder.setAddress(fullAddress);
		existingOrder.setPaymentType(paymentType);
		existingOrder.setTimestamp(dateAndTime);
		existingOrder.setTotal(orderTotal);

		logger.debug("Setting Payment Detail start as payment type is :" + paymentType);
		if (paymentType.equalsIgnoreCase("CARD")) {
			// store only last 4 digits of the card due to security reasons
			String paymentDetail = paymentDetails.getCardNumber() != null ? paymentDetails.getCardNumber().substring(12)
					: "CARD";
			logger.debug("Payment Detail:" + paymentDetail);
			existingOrder.setPaymentDetails(paymentDetail);
		} else {
			logger.debug("Payment Detail: CASH");
			existingOrder.setPaymentDetails("CASH");
		}

		logger.debug("Order Details:" + existingOrder);
		logger.debug("Setting above details into Order_Details end");
		logger.debug("Populating Payment Details end");
	}

	@Override
	@Transactional
	public float computeOrderTotal(Orders order) {
		Float orderTotal = 0F;

		// fetch the order details by orderId
		List<OrderDetails> orderDetails = orderDetailsRepository.findByOrderId(order.getOrderId());

		for (OrderDetails item : orderDetails) {
			// sum all the menu prices in the ORDER_DETAILS table
			orderTotal += item.getUnitPrice();
		}

		// return the total
		return orderTotal;
	}

	/**
	 * Deletes an order by id
	 */
	@Override
	@Transactional
	public void deleteOrder(Long orderId) {
		logger.debug("Delete Order Start:" + orderId);

		// delete the order by primary key orderId from ORDERS table
		ordersRepository.deleteById(orderId);
		logger.debug("Delete Order End");
	}

}
