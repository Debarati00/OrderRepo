package com.pack.order.model;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;

class ProfileTest {

	Profile profile = new Profile();
	List<Orderss> orderss;
	List<CollectionRules> collectionRules;
	
	@Test
	void testGetSetProfileId() {
		profile.setProfileId("profileId");
		assertEquals("profileId", profile.getProfileId());
	}

	@Test
	void testGetSetProfileType() {
		profile.setProfileType("profileType");
		assertEquals("profileType", profile.getProfileType());
	}

	@Test
	void testGetSetCreatedBy() {
		profile.setCreatedBy("createdBy");
		assertEquals("createdBy", profile.getCreatedBy());
	}

	@Test
	void testGetSetLastUpdatedBy() {
		profile.setLastUpdatedBy("lastUpdatedBy");
		assertEquals("lastUpdatedBy", profile.getLastUpdatedBy());
	}

	@Test
	void testGetSetProfileStatus() {
		profile.setProfileStatus("profileStatus");
		assertEquals("profileStatus", profile.getProfileStatus());
	}

	@Test
	void testAllArgsConstructor() {
		Profile profile1 = new Profile("profileId", "createdBy", "lastUpdatedBy", "profileStatus", "profileType", orderss, collectionRules);
		assertEquals("profileId", profile1.getProfileId());
	}
	
	@Test
	void testGetSetOrderss() {
		profile.setOrderss(orderss);
		assertEquals(orderss, profile.getOrderss());
	}
	
	@Test
	void testGetSetCollectionRules() {
		profile.setCollectionRules(collectionRules);
		assertEquals(collectionRules, profile.getCollectionRules());
	}
}
