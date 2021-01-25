package com.pack.order.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "orderId")
	private String orderId;
	private String orderName;
	private String orderPrice;
	private String orderStatus;
	private String orderProfileMatchingId;
	
}
