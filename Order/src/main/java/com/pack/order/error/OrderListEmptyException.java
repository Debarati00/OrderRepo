package com.pack.order.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Order List is empty")
public class OrderListEmptyException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public OrderListEmptyException() {
		super("Order List is empty");
	}

}
