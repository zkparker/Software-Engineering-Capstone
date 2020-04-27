/*
 * Copyright [2020] [ElEspada - Avengers-UIS Force - Software Engineering Capstone - Springfield, IL]
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

import java.util.List;

import org.springframework.stereotype.Component;

import com.elespada.VO.PaymentVO;
import com.elespada.model.Orders;

@Component
public interface OrderService {

	/**
	 * This method starts a new ORDER and returns the ORDER with orderId
	 *
	 * @return ORDERS a single new order
	 * @throws Exception
	 */
	public Orders createOrder() throws Exception;

	/**
	 *
	 * This method creates new order details, adds the menu items to the order and
	 * persist items into ORDER_DETAILS table
	 *
	 * @param menuIds the menu items in the order
	 * @param orderId the order the menu items need to be tagged with
	 * @throws Exception
	 */
	public void createOrderDetails(String menuIds, Long orderId) throws Exception;

	/**
	 * Deletes an item selected by the user from the user's order in ORDER_DETAILS
	 * table
	 *
	 * @param orderId the order that menu item belongs to
	 * @param menuId  the menu item that needs to be deleted
	 * @return List<Long> the remaining items in the order
	 * @throws Exception
	 */
	public List<Long> deleteItemfromOrder(Long orderId, Long menuId) throws Exception;

	/**
	 * Populated the payment details from the VO to DB object Orders and saves in
	 * the ORDERS table
	 *
	 *
	 * @param orderId        the order for which the payment details need to be
	 *                       updated
	 * @param paymentDetails the VO that captured the form data
	 * @return updated Orders with payment details
	 * @throws Exception
	 */
	public Orders updatePaymentDetails(Long orderId, PaymentVO paymentDetails) throws Exception;

	/**
	 * Deletes an order by id
	 * @param orderId
	 */
	public void deleteOrder(Long orderId);

	/**
	 * Aggregates the order total by summing all the menu item prices
	 *
	 * @param order
	 * @return float sum
	 */
	public float computeOrderTotal(Orders order);

	/**
	 * Fetches an order from ORDERS table by the primary key orderId
	 *
	 * @param orderId primary key orderId
	 * @return Orders
	 */
	public Orders findOrderById(Long orderId);
}
