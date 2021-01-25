package com.pack.order.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Order Specifications Missing")
public class OrderSpecificationNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public OrderSpecificationNotFoundException() {
		super("Order Specifications Missing");
	}
}
