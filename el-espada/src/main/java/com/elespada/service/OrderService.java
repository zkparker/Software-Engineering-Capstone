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
