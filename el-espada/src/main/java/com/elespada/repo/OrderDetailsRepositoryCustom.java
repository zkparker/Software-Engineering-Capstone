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

import com.elespada.model.OrderDetails;

/**
 * <b>OrderDetailsRepositoryCustom.java</b><br>
 * Custom Interface for CRUD operation findByOrderId on table ORDER_DETAILS
 */
public interface OrderDetailsRepositoryCustom {
	/**
	 * Custom query for finding orders by order id from ORDER_DETAILS <br>
	 * As the default CRUD repository provides only findById for primary key, we use
	 * this custom method <br>
	 * <b>Query</b> SELECT * FROM ORDER_DETAILS WHERE ORDERID=?
	 */
	public List<OrderDetails> findByOrderId(Long orderId);
}
