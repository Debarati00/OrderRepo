package com.pack.order.error;

import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.pack.order.model.ExceptionResponse;

@RestControllerAdvice
public class OrderExceptionHandler {
	
	@ExceptionHandler(OrderNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleOrderNotFound(final OrderNotFoundException exception,final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setRequestedURI(request.getRequestURI());
		return error;
	}
	
	@ExceptionHandler(OrderListEmptyException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleOrderListEmptyException (final OrderListEmptyException exception, final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setRequestedURI(request.getRequestURI());
		return error;
	}
	
	@ExceptionHandler(OrderSpecificationNotFoundException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleOrderSpecificationNotFoundException (final OrderSpecificationNotFoundException exception, final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setRequestedURI(request.getRequestURI());
		return error;
	}
	
	@ExceptionHandler(ProfileIdNotMatchedException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	public @ResponseBody ExceptionResponse handleProfileIdNotMatchedException (final ProfileIdNotMatchedException exception, final HttpServletRequest request) {
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setRequestedURI(request.getRequestURI());
		return error;
	}

}
