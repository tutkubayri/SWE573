package com.swe.comin.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.swe.comin.models.Post;
import com.swe.comin.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(value = "/jdbc/{keys}/{values}")
    public List<Post> searchResults(@PathVariable (value="keys") String[] keys, @PathVariable (value="values") String[] values){
        return postService.searchResults(keys, values);
    }
    

    /*@RequestMapping(value = "/jdbc")

    public @ResponseBody String dailyStats(@RequestParam String[] entries) {
        String query = "SELECT * from posts where ";
        for(int k = 0; k< entries.length; k++){
            if(entries[k] != null){
                query += entries[k];
                if(k+1 != entries.length-1 && entries[k+1] != null){
                    query += " and ";
                }
            }
        }
        return jdbcTemplate.queryForObject(query, (resultSet, i) -> {
            System.out.println(resultSet.getLong(1)+","+ resultSet.getString(2)+","+ resultSet.getString(3));
            return (resultSet.getLong(1)+","+ resultSet.getString(2)+","+ resultSet.getString(3));
        });
    }*/
}