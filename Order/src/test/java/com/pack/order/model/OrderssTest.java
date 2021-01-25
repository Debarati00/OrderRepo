package com.pack.order.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class OrderssTest {

	Orderss orderss = new Orderss();
	Profile profile = new Profile();
	
	@Test
	void testGetSetOrderProfileId() {
		orderss.setOrderProfileID("orderProfileID");
		assertEquals("orderProfileID", orderss.getOrderProfileID());
	}
	
	@Test
	void testGetSetSourceChannel() {
		orderss.setSourceChannel("sourceChannel");
		assertEquals("sourceChannel", orderss.getSourceChannel());
	}
	
	@Test
	void testGetSetOrderLineType() {
		orderss.setOrderLineType("orderLineType");
		assertEquals("orderLineType", orderss.getOrderLineType());
	}
	
	@Test
	void testGetSetOrderFulfillmentType() {
		orderss.setOrderFulfillmentType("orderFulfillmentType");
		assertEquals("orderFulfillmentType", orderss.getOrderFulfillmentType());
	}
	
	@Test
	void testGetSetOrderDeliveryType() {
		orderss.setOrderDeliveryType("orderDeliveryType");
		assertEquals("orderDeliveryType", orderss.getOrderDeliveryType());
	}
	
	@Test
	void testGetSetProfile() {
		orderss.setProfile(profile);
		assertEquals(profile, orderss.getProfile());
	}
	
	@Test
	void testAllArgsConstructor() {
		Orderss orderss1 = new Orderss("orderProfileID", "sourceChannel", "orderLineType", "orderFulfillmentType", "orderDeliveryType", profile);
		assertEquals("orderDeliveryType", orderss1.getOrderDeliveryType());
	}

}
