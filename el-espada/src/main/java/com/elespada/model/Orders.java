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
package com.elespada.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <b>Orders.java</b><blockquote>Model in MVC pattern<p>
 * Entity class that holds the Object Relational Mapping for table ORDERS. <br>
 * As we are using the spring JPA, this class takes care of creating the table
 * ORDERS in the in-memory database
 * <p>
 * <br>
 * <b>Table: ORDERS</b><br>
 * <p>
 * <b>Attributes:</b><br>
 * orderId - Primary Key <br>
 * customerName - this holds the name of the user/card <br>
 * address - this holds the address of the user/card <br>
 * paymentType - how the user opts to pay the order total, cash or card <br>
 * paymentDetails - we only store last 4 digits of credit/debit card; cash when
 * the user wishes to pay by cash instead <br>
 * total - the total price of the order timestamp - date and time when the
 * payment was made for the order
 */
@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long orderId;
	private String customerName;
	private String address;
	private String paymentType;
	private String paymentDetails;
	private Float total;
	private Timestamp timestamp;

	public Orders() {
		super();
	}

	public Orders(Long orderId, String customerName, String address, String paymentType, String paymentDetails,
			Float total, Timestamp timestamp) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.address = address;
		this.paymentType = paymentType;
		this.paymentDetails = paymentDetails;
		this.total = total;
		this.timestamp = timestamp;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentDetails() {
		return paymentDetails;
	}

	public void setPaymentDetails(String paymentDetails) {
		this.paymentDetails = paymentDetails;
	}

	public Float getTotal() {
		return total;
	}

	public void setTotal(Float total) {
		this.total = total;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((address == null) ? 0 : address.hashCode());
		result = (prime * result) + ((customerName == null) ? 0 : customerName.hashCode());
		result = (prime * result) + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = (prime * result) + ((orderId == null) ? 0 : orderId.hashCode());
		result = (prime * result) + ((paymentDetails == null) ? 0 : paymentDetails.hashCode());
		result = (prime * result) + ((paymentType == null) ? 0 : paymentType.hashCode());
		result = (prime * result) + ((total == null) ? 0 : total.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Orders other = (Orders) obj;
		if (address == null) {
			if (other.address != null) {
				return false;
			}
		} else if (!address.equals(other.address)) {
			return false;
		}
		if (customerName == null) {
			if (other.customerName != null) {
				return false;
			}
		} else if (!customerName.equals(other.customerName)) {
			return false;
		}
		if (timestamp == null) {
			if (other.timestamp != null) {
				return false;
			}
		} else if (!timestamp.equals(other.timestamp)) {
			return false;
		}
		if (orderId == null) {
			if (other.orderId != null) {
				return false;
			}
		} else if (!orderId.equals(other.orderId)) {
			return false;
		}
		if (paymentDetails == null) {
			if (other.paymentDetails != null) {
				return false;
			}
		} else if (!paymentDetails.equals(other.paymentDetails)) {
			return false;
		}
		if (paymentType == null) {
			if (other.paymentType != null) {
				return false;
			}
		} else if (!paymentType.equals(other.paymentType)) {
			return false;
		}
		if (total == null) {
			if (other.total != null) {
				return false;
			}
		} else if (!total.equals(other.total)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Orders [orderId=");
		builder.append(orderId);
		builder.append(", customerName=");
		builder.append(customerName);
		builder.append(", address=");
		builder.append(address);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", paymentDetails=");
		builder.append(paymentDetails);
		builder.append(", total=");
		builder.append(total);
		builder.append(", timestamp=");
		builder.append(timestamp);
		builder.append("]");
		return builder.toString();
	}

}
