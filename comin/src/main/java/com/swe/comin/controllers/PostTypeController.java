package com.swe.comin.controllers;

import com.swe.comin.exceptions.ResourceNotFoundException;
import com.swe.comin.models.PostType;
import com.swe.comin.services.PostTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PostTypeController {

    private PostTypeService postTypeService;

    public PostTypeController(PostTypeService postTypeService) {
        this.postTypeService = postTypeService;
    }

    @GetMapping("communities/{communityId}/postTypes/")
    public ResponseEntity<List<PostType>> getPostTypeByCommunityId(@PathVariable (value="communityId") Long communityId){
        return ResponseEntity.ok(postTypeService.getPostTypeByCommunityId(communityId));    }

    @PostMapping({"/communities/{communityId}/postTypes"})
    public ResponseEntity<PostType> savePostType(@PathVariable (value="communityId") Long communityId, @RequestBody PostType postType) {
        return ResponseEntity.ok(postTypeService.savePostType(communityId, postType));
    }
}