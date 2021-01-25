package com.pack.order.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.pack.order.client.ProfileProxy;
import com.pack.order.controller.OrderController;
import com.pack.order.entity.Orders;
import com.pack.order.model.CollectionRules;
import com.pack.order.model.Orderss;
import com.pack.order.model.Profile;
import com.pack.order.repository.OrderRepository;
import com.pack.order.repository.ProfileRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
class OrderServiceTest {

	@InjectMocks
	OrderServiceImpl service;

	@Mock
	Orders orders;

	@Mock
	OrderController orderController;

	@Mock
	OrderRepository orderRepository;

	@Mock
	ProfileRepository profileRepository;

	@Mock
	ProfileProxy profileProxy;

	Profile profile = new Profile();
	CollectionRules rules = new CollectionRules();
	Orderss orders1 = new Orderss();

	List<CollectionRules> collectionRules;
	List<Orderss> orderss;

	@BeforeEach
	public void setUp() throws Exception {
		orders.setOrderId("orderId");
		orders.setOrderName("orderName");
		orders.setOrderPrice("orderPrice");
		orders.setOrderProfileMatchingId("profileId");
		orders.setOrderStatus("orderStatus");
		rules.setDescription("description");
		rules.setFraudCheckEnabled(true);
		rules.setPaymentProcessingEnabled(true);
		rules.setResourcingEnabled(true);
		rules.setProfile(profile);
		List<CollectionRules> collectionRules = new ArrayList<>();
		collectionRules.add(rules);

		orders1.setOrderProfileID("od1");
		orders1.setSourceChannel("sourceChannel");
		orders1.setOrderLineType("orderLineType");
		orders1.setOrderFulfillmentType("orderFulfillmentType");
		orders1.setOrderProfileID("o");
		orders1.setProfile(profile);
		List<Orderss> orderss = new ArrayList<>();
		orderss.add(orders1);

		profile.setCollectionRules(collectionRules);
		profile.setCreatedBy("createdBy");
		profile.setLastUpdatedBy("lastUpdatedBy");
		profile.setOrderss(orderss);
		profile.setProfileId("profileId");
		profile.setProfileStatus("profileStatus");
		profile.setProfileType("profileType");
	}

	@Test
	void testGetAllOrders() {
		List<Orders> order = new ArrayList<>();
		order.add(orders);
		when((List<Orders>) orderRepository.findAll()).thenReturn(order);
		assertThat(service.getAllOrders()).isEqualTo(order);

	}

	@Test
	void testGetOrderById() {
		when(orderRepository.findById("orderId")).thenReturn(Optional.of(orders));
		assertThat(service.getOrderById("orderId")).isEqualTo(orders);

	}

	@Test
	void testUpdateOrderById() {
		when(orders.getOrderId()).thenReturn("orderId");
		when(orders.getOrderName()).thenReturn("orderName");
		when(orders.getOrderPrice()).thenReturn("orderPrice");
		when(orders.getOrderProfileMatchingId()).thenReturn("profileId");
		when(orders.getOrderStatus()).thenReturn("orderStatus");
		when(orderRepository.save(orders)).thenReturn(orders);
		assertThat(service.updateOrderById(orders, "orderId")).isEqualTo(orders);
	}

	@Test
	void testDeleteOrderById() {
		when(orderRepository.findById("orderId")).thenReturn(Optional.of(orders));
		service.deleteOrderById("orderId");

	}

	@Test
	void testSaveOrder() {
		List<Profile> profiles = new ArrayList<>();
		profiles.add(profile);
		when(orders.getOrderProfileMatchingId()).thenReturn("profileId");
		when(profileProxy.getAllProfiles()).thenReturn(profiles);
		when(orderRepository.save(orders)).thenReturn(orders);
		assertThat(service.saveOrder(orders)).isEqualTo(profile);
	}
}