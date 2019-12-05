package com.swe.comin.services;

import com.swe.comin.exceptions.ResourceNotFoundException;
import com.swe.comin.models.Post;
import com.swe.comin.repositories.PostRepository;
import com.swe.comin.repositories.PostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService{

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostTypeRepository postTypeRepository;

    public List<Post> getPostByPostTypeId(Long id){
        return postRepository.findByPostTypeId(id);
    }

    public Post savePost(Long postTypeId, Post post){
        return postTypeRepository.findById(postTypeId)
                .map(postType -> {
                    post.setPostType(postType);
                    return postRepository.save(post);
                }).orElseThrow(() -> new ResourceNotFoundException("Post type not found with id " + postTypeId));
    }

    public Post updatePost(Long postId, Post postRequest){
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post does not exist."));
        return postRepository.save(postRequest);
    }

    public void deletePostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post does not exist."));
        postRepository.delete(post);
    }
}