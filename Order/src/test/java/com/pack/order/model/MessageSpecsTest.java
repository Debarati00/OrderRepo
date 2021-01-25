package com.pack.order.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;

class MessageSpecsTest {

	MessageSpecs specs = new MessageSpecs();
	LocalTime lastupdated = LocalTime.of(10, 52, 41);

	@Test
	void testGetSetOrderId() {
		specs.setOrderId("orderId");
		assertEquals("orderId", specs.getOrderId());
	}

	@Test
	void testGetSetOperation() {
		specs.setOperation("operation");
		assertEquals("operation", specs.getOperation());
	}

	@Test
	void testGetSetLastupdated() {
		specs.setLastupdated(lastupdated);
		assertEquals(lastupdated, specs.getLastupdated());
	}

	@Test
	void testAllArgsConstructor() {
		MessageSpecs specs1 = new MessageSpecs("orderId", "operation", lastupdated);
		assertEquals("orderId", specs1.getOrderId());
	}

	@Test
	void testToString() {
		assertThat(specs.toString()).contains("operation");
	}
}