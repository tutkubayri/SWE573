package com.swe573.comin.Controllers;

import java.util.List;
import java.util.Optional;
import com.swe573.comin.Services.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.swe573.comin.Models.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class CommunityController {

	@Autowired
	CommunityService communityService;

	@GetMapping("/communities")
	public List<Community> getAllCommunities() {
		return communityService.getAllCommunities();
	}

	@PostMapping(value = "/communities/create")
	public Community postCommunity(@RequestBody Community community) {
		return communityService.postCommunity(community);
	}

	@DeleteMapping("/communities/delete/{id}")
	public ResponseEntity<String> deleteCommunity(@PathVariable("id") long id) {
		return communityService.deleteCommunity(id);
	}

	@DeleteMapping("/communities/delete")
	public ResponseEntity<String> deleteAllCommunities() {
		return communityService.deleteAllCommunities();
	}

	@GetMapping(value = "/semanticTag/{semanticTag}")
	public List<Community> findBySemanticTag(@PathVariable String semanticTag) {
		return communityService.findBySemanticTag(semanticTag);
	}
	
	@GetMapping(value = "/name/{name}")
	public List<Community> findByName(@PathVariable String name) {
		return communityService.findByName(name);
	}
	
	@GetMapping(value = "/id/{id}")
	public Optional<Community> findById(@PathVariable long id) {
		return communityService.findById(id);
	}
}