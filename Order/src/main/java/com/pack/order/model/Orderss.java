package com.pack.order.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "Orderss")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orderss implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	private String orderProfileID;
	private String sourceChannel;
	private String orderLineType;
	private String orderFulfillmentType;
	private String orderDeliveryType;
	
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profileId")
	private Profile profile;

}