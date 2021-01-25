package com.pack.order.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;

import com.pack.order.client.ProfileProxy;
import com.pack.order.entity.Orders;
import com.pack.order.model.CollectionRules;
import com.pack.order.model.MessageSpecs;
import com.pack.order.model.Orderss;
import com.pack.order.model.Profile;
import com.pack.order.service.OrderService;

@SpringBootTest
class OrderControllerTest {

	@Mock
	OrderService orderService;

	@InjectMocks
	OrderController controller;

	@Mock
	MessageSpecs messageSpecs;

	@Mock
	Orders orders;

	@Mock
	Profile profile;

	@Mock
	ProfileProxy profileProxy;

	@Mock
	KafkaTemplate<String, Object> template;

	LocalTime time = LocalTime.now();

	List<String> messages = new ArrayList<>();

	List<CollectionRules> collectionRules;

	List<Orderss> orderss;

	@BeforeEach
	void setup() throws Exception {
		orders.setOrderId("orderId");
		orders.setOrderName("orderName");
		orders.setOrderPrice("orderPrice");
		orders.setOrderProfileMatchingId("profileId");
		orders.setOrderStatus("orderStatus");

		profile.setCreatedBy("createdBy");
		profile.setLastUpdatedBy("lastUpdatedBy");
		profile.setProfileId("profileId");
		profile.setProfileStatus("profileStatus");
		profile.setProfileType("profileType");
		profile.setCollectionRules(collectionRules);
		profile.setOrderss(orderss);
	}

	@Test
	void testGetAllOrders() {
		List<Orders> ordersList = new ArrayList<>();
		ordersList.add(orders);
		when(orderService.getAllOrders()).thenReturn(ordersList);
		when(template.send("topic", "string")).thenReturn(null);
		ResponseEntity<?> ordersList1 = controller.getAllOrders();
		assertEquals(200, ordersList1.getStatusCodeValue());
	}

	@Test
	void testGetOrderById() {
		when(orderService.getOrderById("orderId")).thenReturn(orders);
		when(template.send("topic", "toString")).thenReturn(null);
		ResponseEntity<?> order1 = controller.getOrderById("orderId");
		assertEquals(200, order1.getStatusCodeValue());
	}

	@Test
	void testUpdateOrder() {
		when(orderService.getOrderById("orderId")).thenReturn(orders);
		when(template.send("topic", "toString")).thenReturn(null);
		ResponseEntity<?> order2 = controller.updateOrder(orders, "orderId");
		assertEquals(200, order2.getStatusCodeValue());
	}

	@Test
	void testSaveOrder() {
		when(orderService.saveOrder(orders)).thenReturn(profile);
		when(template.send("topic", "toString")).thenReturn(null);
		ResponseEntity<?> profile1 = controller.saveOrder(orders);
		assertEquals(200, profile1.getStatusCodeValue());
	}

	@Test
	void testDeleteOrder() {
		when(orderService.getOrderById("orderId")).thenReturn(orders);
		when(template.send("topic", "messageSpecs.toString")).thenReturn(null);
		ResponseEntity<?> string = controller.deleteOrder("orderId");

	}

	@Test
	void testConsumeMsg() {
		assertEquals(controller.consumeMsg(), messages);
	}

	@Test
	void testGetMsgFromTopic() {
		messages.add("data");
		assertEquals(controller.getMsgFromTopic("data"), messages);
	}

	@Test
	void testGetMsgFromTopic1() {
		messages.add("data");
		assertEquals(controller.getMsgFromTopic1("data"), messages);
	}
}