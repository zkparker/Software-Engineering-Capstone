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
package com.elespada.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.elespada.model.OrderDetails;

/**
 * <b>OrderDetailsRepository.java</b><br>
 * Interface for CRUD operations on table ORDER_DETAILS
 */
public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long>, OrderDetailsRepositoryCustom {

	// CRUD Repository for OrderDetails Entity

	@Override
	@Query("SELECT OD FROM OrderDetails OD WHERE OD.orderId=:orderId")
	public List<OrderDetails> findByOrderId(@Param("orderId") Long orderId);

}
