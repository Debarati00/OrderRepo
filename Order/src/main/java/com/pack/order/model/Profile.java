package com.pack.order.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Profile")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	private String profileId;
	private String createdBy;
	private String lastUpdatedBy;
	private String profileStatus;
	private String profileType;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "profileId")
	private List<Orderss> orderss;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "profileId")
	private List<CollectionRules> collectionRules;

}
