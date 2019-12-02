package com.swe.comin.controllers;

import com.swe.comin.models.Post;
import com.swe.comin.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("posts/{postTypeId}")
    public ResponseEntity<List<Post>> getPostByPostTypeId(@PathVariable (value="postTypeId") Long postTypeId){
        return ResponseEntity.ok(postService.getPostByPostTypeId(postTypeId));    }

    @PostMapping({"posts/add/{postTypeId}"})
    public ResponseEntity<Post> savePost(@PathVariable (value="postTypeId") Long postTypeId, @RequestBody Post post) {
        return ResponseEntity.ok(postService.savePost(postTypeId, post));
    }
}