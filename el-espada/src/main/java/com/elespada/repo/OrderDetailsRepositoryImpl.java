/*
 * Copyright [2020] [ElEspada - UIS Avengers-Force - Software Engineering Capstone - Springfield, IL]
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
package com.elespada.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.elespada.model.OrderDetails;

/**
 * <b>OrderDetailsRepositoryImpl.java</b><br>
 * Custom class implementing Custom Interface
 * <b>OrderDetailsRepositoryCustom.java</b> for CRUD operation findByOrderId on
 * table ORDER_DETAILS
 */
public class OrderDetailsRepositoryImpl implements OrderDetailsRepositoryCustom {

	@Override
	@Query("SELECT OD FROM OrderDetails OD WHERE OD.orderId=:orderId")
	public List<OrderDetails> findByOrderId(@Param("orderId") Long orderId) {
		return null;
	}

}
