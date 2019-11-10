package com.swe573.comin.controller;

import com.swe573.comin.model.ApiResponse;
import com.swe573.comin.model.Community;
import com.swe573.comin.service.CommunityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/community")
public class CommunityController {
    private CommunityService communityService;

    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<Community>> getAllCommunity(){
        return ResponseEntity.ok(communityService.getAllCommunity());
    }

    @GetMapping("/{communityId}")
    public ResponseEntity<Community> getCommunityById(@PathVariable Long communityId){
        return ResponseEntity.ok(communityService.getCommunityById(communityId));
    }

    @PostMapping({"", "/"})
    public ResponseEntity<Community> saveCommunity(@RequestBody Community communityRequest){
        return ResponseEntity.ok(communityService.saveCommunity(communityRequest));
    }

    @PutMapping("/{communityId}")
    public ResponseEntity<Community> updateCommunity(@PathVariable Long communityId, @RequestBody Community communityRequest){
        return ResponseEntity.ok(communityService.updateCommunity(communityId, communityRequest));
    }

    @DeleteMapping("/{communityId}")
    public ResponseEntity<ApiResponse> deleteCommunityById(@PathVariable Long communityId){
        communityService.deleteCommunityById(communityId);
        return ResponseEntity.ok(new ApiResponse("Community has deleted."));
    }
}
