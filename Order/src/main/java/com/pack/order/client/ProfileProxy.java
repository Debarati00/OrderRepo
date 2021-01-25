package com.pack.order.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.pack.order.model.Profile;

@FeignClient(name="Profile-Service",url="localhost:8080")
public interface ProfileProxy {
	
	@GetMapping("/profile")
	public List<Profile> getAllProfiles();
	
	@GetMapping("/profile/{profileId}")
	public ResponseEntity<?> getProfileById(@PathVariable("profileId") String profileId);
	
	@PostMapping("/profile")
	public ResponseEntity<Profile> addProfiles(@RequestBody Profile profile) ;
	
	@PutMapping("/profile/{profileId}")
	public ResponseEntity<Profile> updateProfiles(@PathVariable("profileId") String profileId,@RequestBody Profile profile);
	
	@DeleteMapping("/profile/{profileId}")
	public void deleteProfileById(@PathVariable("profileId") String profileId);
	
}
