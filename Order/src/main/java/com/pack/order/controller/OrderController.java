
package com.pack.order.controller;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.pack.order.model.MessageSpecs;
import com.pack.order.client.ProfileProxy;
import com.pack.order.entity.Orders;
import com.pack.order.model.Profile;
import com.pack.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableHystrix
@Slf4j
public class OrderController {

	@Autowired
	OrderService orderService;
	@Autowired
	private ProfileProxy profileProxy;

	@Autowired
	private KafkaTemplate<String, Object> template;
	private String topic = "Test_Topics";

	MessageSpecs messageSpecs = new MessageSpecs();
	LocalTime time = LocalTime.now();

	List<String> messages = new ArrayList<>();

	@GetMapping("/consumeStringMessage")
	public List<String> consumeMsg() {
		return messages;
	}

	@KafkaListener(groupId = "Test_Topics", topics = "Test_Topics", containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMsgFromTopic(String data) {
		messages.add(data);
		return messages;
	}

	@KafkaListener(groupId = "Test_Topics1", topics = "Test_Topics1", containerFactory = "kafkaListenerContainerFactory")
	public List<String> getMsgFromTopic1(String data) {
		messages.add(data);
		return messages;
	}

	@GetMapping("/orders")
	public ResponseEntity<?> getAllOrders() {
		List<Orders> ordersList = orderService.getAllOrders();
		if (ordersList.size() > 0) {
			messageSpecs.setOrderId("ALL ORDER ID'S ARE DISPLAYED");
			messageSpecs.setOperation("SHOW ALL ORDERS");
			messageSpecs.setLastupdated(time);
			template.send(topic, messageSpecs.toString());
			log.info("ALL ORDERS DISPLAYED");
			return new ResponseEntity<>(ordersList, HttpStatus.OK);
		} else
			log.info("NO ORDERS PRESENT IN THE LIST");
		return new ResponseEntity<>("NO ORDER PRESENT", HttpStatus.BAD_REQUEST);
	}

	@GetMapping("/orders/{orderId}")
	public ResponseEntity<?> getOrderById(@PathVariable("orderId") String orderId) {
		Orders order = orderService.getOrderById(orderId);
		if (order != null) {
			messageSpecs.setOrderId(order.getOrderId());
			messageSpecs.setOperation("SHOW ORDER BY ID");
			messageSpecs.setLastupdated(time);
			template.send(topic, messageSpecs.toString());
			log.info("ORDER BY ID DISPLAYED");
			return new ResponseEntity<>(order, HttpStatus.OK);
		} else
			log.error("ORDER NOT FOUND");
		return new ResponseEntity<>("INVALID ORDER", HttpStatus.BAD_REQUEST);

	}

	@PutMapping("/orders/{orderId}")
	public ResponseEntity<?> updateOrder(@RequestBody Orders order, @PathVariable("orderId") String orderId) {

		Orders orders = orderService.getOrderById(orderId);
		if (orders != null) {
			messageSpecs.setOrderId(order.getOrderId());
			messageSpecs.setOperation("UPDATE");
			messageSpecs.setLastupdated(time);
			template.send(topic, messageSpecs.toString());
			log.info("ORDER UPDATED");
			Orders order1 = orderService.updateOrderById(order, orderId);
			return new ResponseEntity<>(order1, HttpStatus.OK);
		} else
			log.error("ORDER NOT FOUND");
		return new ResponseEntity<>("INVALID ORDER", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/orders")
	public ResponseEntity<?> saveOrder(@RequestBody Orders orders) {
		if (orders != null) {

			messageSpecs.setOrderId(orders.getOrderId());
			messageSpecs.setOperation("SAVE");
			messageSpecs.setLastupdated(time);
			template.send(topic, messageSpecs.toString());
			Profile order = orderService.saveOrder(orders);
			return new ResponseEntity<>(order, HttpStatus.OK);
		} else {
			orderService.saveOrder(orders);
			return new ResponseEntity<>("NO MATCHED PROFILE", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<?> deleteOrder(@PathVariable("orderId") String orderId) {
		Orders order = orderService.getOrderById(orderId);
		if (order != null) {
			messageSpecs.setOrderId(order.getOrderId());
			messageSpecs.setOperation("DELETE");
			messageSpecs.setLastupdated(time);
			template.send(topic, messageSpecs.toString());
			log.info("ORDER DELETED");
			orderService.deleteOrderById(orderId);
			return new ResponseEntity<>("ORDER DELETED", HttpStatus.BAD_REQUEST);
		} else
			log.error("ORDER NOT FOUND");
		return new ResponseEntity<>("INVALID ORDER", HttpStatus.BAD_REQUEST);
	}

	@HystrixCommand(fallbackMethod = "fallbackgetall")
	@GetMapping("/profile")
	public ResponseEntity<?> getProfile() {
		return new ResponseEntity<>(profileProxy.getAllProfiles(), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<?> fallbackgetall() {
		return new ResponseEntity<>("Profile Service Gateway Failed for showing all profiles", HttpStatus.BAD_REQUEST);
	}

	@HystrixCommand(fallbackMethod = "fallbackget")
	@GetMapping("/profile/{profileId}")
	public ResponseEntity<?> getProfileById(@PathVariable("profileId") String profileId) {
		return new ResponseEntity<>(profileProxy.getProfileById(profileId), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<?> fallbackget(@PathVariable("profileId") String profileId) {
		return new ResponseEntity<>("Profile Service Gateway Failed for finding profile by id", HttpStatus.BAD_REQUEST);
	}

	@HystrixCommand(fallbackMethod = "fallbacksave")
	@PostMapping("/profile")
	public ResponseEntity<?> addProfiles(@RequestBody Profile profile) {
		return new ResponseEntity<>(profileProxy.addProfiles(profile), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<?> fallbacksave(@RequestBody Profile profile) {
		return new ResponseEntity<>("Profile Service Gateway Failed for saving profiles", HttpStatus.BAD_REQUEST);
	}

	@HystrixCommand(fallbackMethod = "fallbackupdate")
	@PutMapping("/profile/{profileId}")
	public ResponseEntity<?> updateProfiles(@PathVariable("profileId") String profileId, @RequestBody Profile profile) {
		return new ResponseEntity<>(profileProxy.updateProfiles(profileId, profile), HttpStatus.ACCEPTED);
	}

	public ResponseEntity<?> fallbackupdate(@PathVariable("profileId") String profileId, @RequestBody Profile profile) {
		return new ResponseEntity<>("Profile Service Gateway Failed for updating profiles", HttpStatus.BAD_REQUEST);
	}

	@HystrixCommand(fallbackMethod = "fallbackdelete")
	@DeleteMapping("/profile/{profileId}")
	public ResponseEntity<?> deleteProfileById(@PathVariable("profileId") String profileId) {
		profileProxy.deleteProfileById(profileId);
		return new ResponseEntity<>("Profile deleted", HttpStatus.ACCEPTED);
	}

	public ResponseEntity<?> fallbackdelete(@PathVariable("profileId") String profileId) {
		return new ResponseEntity<>("Profile Service Gateway Failed for deleting profiles", HttpStatus.BAD_REQUEST);
	}

}