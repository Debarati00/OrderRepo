package com.pack.order.service;

import java.util.List;
import com.pack.order.entity.Orders;
import com.pack.order.model.Profile;

public interface OrderService {
	
	List<Orders> getAllOrders();
	Orders getOrderById(String orderId);
	Orders updateOrderById(Orders order, String orderId);
	void deleteOrderById(String orderId);
	Profile saveOrder(Orders orders);
	
	
}
