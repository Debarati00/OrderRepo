package com.pack.order.model;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageSpecs {
	
	private String orderId;
	private String operation;
	private LocalTime lastupdated;
	@Override
	public String toString() {
		return "orderId=" + orderId + ", operation=" + operation + ", lastupdated=" + lastupdated;
	}
}