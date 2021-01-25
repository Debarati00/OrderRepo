package com.pack.order.repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.pack.order.model.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile,String> {
	
	 public String findByProfileId (String profileId);

}
