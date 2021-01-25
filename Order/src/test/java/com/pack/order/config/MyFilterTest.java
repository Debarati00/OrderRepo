package com.pack.order.config;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import javax.servlet.ServletException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

public class MyFilterTest {
	@Mock
	private MockHttpServletRequest httpServletRequest;

	@Mock
	private MockHttpServletResponse httpServletResponse;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	
	@Test
	void testDoFilter() throws IOException, ServletException {
		MockFilterChain filterChain = new MockFilterChain();
		Mockito.when(httpServletRequest.getRequestURI()).thenReturn("/orders/swagger");
		 MyFilter myswaggerFilter = new  MyFilter();
		 myswaggerFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
		filterChain.doFilter(httpServletRequest, httpServletResponse);
		Mockito.verify(httpServletResponse).sendRedirect("/swagger-ui.html");
	}

	@Test
	void testSwaggerTrue() throws Exception {
		Mockito.when(httpServletRequest.getRequestURI()).thenReturn("/orders/swagger");
		assertEquals("/orders/swagger", httpServletRequest.getRequestURI());

	}

}
