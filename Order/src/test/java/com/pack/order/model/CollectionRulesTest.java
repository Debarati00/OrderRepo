package com.pack.order.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CollectionRulesTest {

	CollectionRules rules = new CollectionRules();
	Profile profile = new Profile();

	@Test
	void testGetSetDescription() {
		rules.setDescription("description");
		assertEquals("description", rules.getDescription());
	}

	@Test
	void testGetSetFraudCheckEnabled() {
		rules.setFraudCheckEnabled(true);
		assertEquals(true, rules.isFraudCheckEnabled());
	}

	@Test
	void testGetSetPaymentProcessingEnabled() {
		rules.setPaymentProcessingEnabled(true);
		assertEquals(true, rules.isPaymentProcessingEnabled());
	}

	@Test
	void testGetSetResourcingEnabled() {
		rules.setResourcingEnabled(false);
		assertEquals(false, rules.isResourcingEnabled());
	}

	@Test
	void testGetSetProfile() {
		rules.setProfile(profile);
		assertEquals(profile, rules.getProfile());
	}

	@Test
	void testAllArgsConstructor() {
		CollectionRules rules1 = new CollectionRules("description", true, true, true, profile);
		assertEquals(true, rules1.isFraudCheckEnabled());
	}
}
