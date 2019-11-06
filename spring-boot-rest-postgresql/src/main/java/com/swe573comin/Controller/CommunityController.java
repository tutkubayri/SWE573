package com.swe573comin.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.swe573comin.Model.*;
import com.swe573comin.Repository.CommunityRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CommunityController {

	@Autowired
	CommunityRepository repository;

	@GetMapping("/communities")
	public List<Community> getAllCommunities() {
		System.out.println("Get all Communities...");

		List<Community> communities = new ArrayList<>();
		repository.findAll().forEach(communities::add);

		return communities;
	}

	@PostMapping(value = "/communities/create")
	public Community postCommunity(@RequestBody Community community) {

		Community _community = repository.save(new Community(community.getName(), community.getDescription(), community.getSemanticTag(), community.getBannerUrl()));
		return _community;
	}

	@DeleteMapping("/communities/delete/{id}")
	public ResponseEntity<String> deleteCommunity(@PathVariable("id") long id) {
		System.out.println("Delete Community with ID = " + id + "...");

		repository.deleteById(id);
		return new ResponseEntity<>("Community has been deleted!", HttpStatus.OK);
	}

	@DeleteMapping("/communities/delete")
	public ResponseEntity<String> deleteAllCommunities() {
		System.out.println("Delete All Communities...");

		repository.deleteAll();
		return new ResponseEntity<>("All communities have been deleted!", HttpStatus.OK);
	}

	@GetMapping(value = "/semanticTag/{semanticTag}")
	public List<Community> findBySemanticTag(@PathVariable String semanticTag) {

		List<Community> communities = repository.findBySemanticTag(semanticTag);
		return communities;
	}
	
	@GetMapping(value = "/name/{name}")
	public List<Community> findByName(@PathVariable String name) {

		List<Community> communities = repository.findByName(name);
		return communities;
	}
	
	@GetMapping(value = "/id/{id}")
	public Optional<Community> findById(@PathVariable long id) {
		System.out.println("Get a Community...");
		Optional<Community> community = repository.findById(id);
		return community;
	}

	/*
	 * @PutMapping("/communities/{id}") public ResponseEntity<Community>
	 * updateCommunity(@PathVariable("id") long id, @RequestBody Community
	 * community) { System.out.println("Update Customer with ID = " + id + "...");
	 * 
	 * Optional<Community> communityData = repository.findById(id);
	 * 
	 * if (communityData.isPresent()) { Community _community = communityData.get();
	 * _community.setName(community.getName());
	 * _community.setDescription(community.getDescription());
	 * _community.setSemanticTag(community.getSemanticTag());
	 * _community.setBannerUrl(community.getBannerUrl()); return new
	 * ResponseEntity<>(repository.save(_community), HttpStatus.OK); } else { return
	 * new ResponseEntity<>(HttpStatus.NOT_FOUND); } }
	 */
}