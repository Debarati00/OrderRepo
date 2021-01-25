package com.pack.order.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Order Saved But No matching ProfileId found")
public class ProfileIdNotMatchedException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public ProfileIdNotMatchedException() {
		super("No matching ProfileId found");
	}
}
