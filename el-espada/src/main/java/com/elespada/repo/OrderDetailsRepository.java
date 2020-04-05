package com.elespada.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.elespada.model.OrderDetails;

public interface OrderDetailsRepository extends CrudRepository<OrderDetails, Long>/* , OrderDetailsRepositoryCustom */ {
	
	@Query("select OD from OrderDetails OD where OD.orderId=:orderId")
	public List<OrderDetails> findByOrderId(@Param("orderId") Long orderId);

}
