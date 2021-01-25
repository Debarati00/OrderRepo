package com.pack.order.service;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import com.pack.order.client.ProfileProxy;
import com.pack.order.entity.Orders;
import com.pack.order.error.OrderListEmptyException;
import com.pack.order.error.OrderNotFoundException;
import com.pack.order.error.OrderSpecificationNotFoundException;
import com.pack.order.error.ProfileIdNotMatchedException;
import com.pack.order.model.Profile;
import com.pack.order.repository.OrderRepository;
import com.pack.order.repository.ProfileRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	ProfileProxy profileProxy;

	@Override
	@Transactional
	public List<Orders> getAllOrders() {
		List<Orders> order = (List<Orders>) orderRepository.findAll();
		if (order.size()==0) {
			throw new OrderListEmptyException();

		} else
		return order;

	}

	@Override
	@Transactional
	public Orders getOrderById(String orderId) {
		Orders order = orderRepository.findById(orderId).orElse(null);
		if (order == null) {
			throw new OrderNotFoundException();
		} else
		return order;
	}

	@Override
	@Transactional
	public Profile saveOrder(Orders orders) {
		List<Profile> profiles = profileProxy.getAllProfiles();
		for (Profile profile2 : profiles) {
			if (orders.getOrderProfileMatchingId().equalsIgnoreCase(profile2.getProfileId())) {
				log.info("PROFILE MATCHED AND ORDER SAVED");
				orders.setOrderStatus("Available");
				orderRepository.save(orders);
				return profile2;
		}
			else if ((orders.getOrderId().isEmpty()) || (orders.getOrderName().isEmpty()) || (orders.getOrderPrice().isEmpty()) || (orders.getOrderStatus().isEmpty())) {
				log.info("ORDER SPECIFICATIONS MISSING");
				throw new OrderSpecificationNotFoundException();
			}
		}
			orders.setOrderStatus("Not Available");
			orderRepository.save(orders);
		    log.info("ORDER NOT MATCHED WITH PROFILE");
		    throw new ProfileIdNotMatchedException();

	}

	@Override
	@Transactional
	public Orders updateOrderById(Orders orders, @PathVariable("orderId") String orderId) {
		if ((orders.getOrderId().isEmpty()) || (orders.getOrderName().isEmpty()) || (orders.getOrderPrice().isEmpty())
				|| (orders.getOrderStatus().isEmpty())) {
			log.info("ORDER SPECIFICATIONS MISSING");
			throw new OrderSpecificationNotFoundException();
		} else
		orderRepository.save(orders);
		return orders;

	}

	@Override
	@Transactional
	public void deleteOrderById(String orderId) {
		Orders orders = orderRepository.findById(orderId).orElse(null);
		if (orders == null) {
			throw new OrderNotFoundException();
		} else
		orderRepository.deleteById(orderId);
	}
}