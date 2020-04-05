package com.elespada.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.elespada.model.Menu;
import com.elespada.model.OrderDetails;
import com.elespada.model.Orders;
import com.elespada.repo.MenuRepository;
import com.elespada.repo.OrderDetailsRepository;
import com.elespada.repo.OrdersRepository;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrdersRepository ordersRepository;
	
	@Autowired
	MenuRepository menuRepository;
	
	@Autowired
	OrderDetailsRepository orderDetailsRepository;
	
	@Override
	@Transactional
	public Orders createOrder() {
		Orders order = new Orders();
		order.setCustomerName("temp");
		return ordersRepository.save(order);
	}
	
	@Override
	@Transactional
	public void createOrderDetails(String menuIds, Long orderId) {
		List<Long> idList = Stream.of(menuIds.split(",")).
				map(Long::parseLong).collect(Collectors.toList());
		for(Long id: idList){
			OrderDetails orderDetail = new OrderDetails();
			orderDetail.setMenuId(id);
			orderDetail.setOrderId(orderId);
			orderDetail.setQuantity(1L);
			Optional<Menu> menuItem = menuRepository.findById(id);
			orderDetail.setUnitPrice(menuItem.get().getMenuPrice());
			orderDetailsRepository.save(orderDetail);
		}
	}
	
	@Override
	@Transactional
	public List<Long> deleteItemfromOrder(Long orderId, Long menuId) {
		List<OrderDetails> orderDetailsList = orderDetailsRepository.findByOrderId(orderId);
		Long odsId = null;
		List<Long> menuIds = new ArrayList<>();
		for(OrderDetails od : orderDetailsList) {
			if(od.getMenuId().equals(menuId)) {
				odsId = od.getId();
			}else {
				menuIds.add(od.getMenuId());
			}
		}
		orderDetailsRepository.deleteById(odsId);
		return menuIds;
	}

}
