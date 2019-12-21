package com.swe.comin.controllers;

import com.swe.comin.models.Community;
import com.swe.comin.models.PostType;
import com.swe.comin.services.PostTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostTypeController {

    private PostTypeService postTypeService;

    public PostTypeController(PostTypeService postTypeService) {
        this.postTypeService = postTypeService;
    }

    @GetMapping("postTypes/{postTypeId}")
    public ResponseEntity<PostType> getPostTypeById(@PathVariable (value="postTypeId") Long postTypeId) {
        return ResponseEntity.ok(postTypeService.getPostTypeById(postTypeId));
    }

    @GetMapping({"/postTypes"})
    public ResponseEntity<List<PostType>> getAllPostTypes() {
        return ResponseEntity.ok(postTypeService.getAllPostTypes());
    }

    /*@GetMapping("postTypes/{postTypeName}")
    public ResponseEntity<PostType> getPostTypeByName(@PathVariable (value="postTypeName") String postTypeName) {
        return ResponseEntity.ok(postTypeService.getPostTypeByName(postTypeName));
    }*/

    /*@GetMapping("postTypes/{communityId}")
    public ResponseEntity<List<PostType>> getPostTypeByCommunityId(@PathVariable (value="communityId") Long communityId){
        return ResponseEntity.ok(postTypeService.getPostTypeByCommunityId(communityId));    }*/

    @PostMapping({"postTypes/add/{communityId}"})
    public ResponseEntity<PostType> savePostType(@PathVariable (value="communityId") Long communityId, @RequestBody PostType postType) {
        return ResponseEntity.ok(postTypeService.savePostType(communityId, postType));
    }
}