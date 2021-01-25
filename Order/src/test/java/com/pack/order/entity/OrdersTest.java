package com.pack.order.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class OrdersTest {

	Orders orders = new Orders();

	@Test
	void testGetSetOrderId() {
		orders.setOrderId("OR124");
		assertEquals("OR124", orders.getOrderId());
	}

	@Test
	void testGetSetOrderName() {
		orders.setOrderName("OrderName");
		assertEquals("OrderName", orders.getOrderName());
	}

	@Test
	void testGetSetOrderPrice() {
		orders.setOrderPrice("100");
		assertEquals("100", orders.getOrderPrice());
	}

	@Test
	void testGetSetOrderStatus() {
		orders.setOrderStatus("Processed");
		assertEquals("Processed", orders.getOrderStatus());
	}

	@Test
	void testGetSetOrderProfileMatchingId() {
		orders.setOrderProfileMatchingId("string");
		assertEquals("string", orders.getOrderProfileMatchingId());
	}

	@Test
	void testAllArgsConstructor() {
		Orders orders1 = new Orders("OR123", "orderName", "10000", "orderStatus", "orderProfileMatchingId");
		assertEquals("10000", orders1.getOrderPrice());
	}

}
