package com.swe.comin.services;

import com.swe.comin.exceptions.ResourceNotFoundException;
import com.swe.comin.models.Post;
import com.swe.comin.repositories.PostRepository;
import com.swe.comin.repositories.PostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private List<Post> rArray;
    private List<Post> resultArray;
    private List<Post> postList;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostTypeRepository postTypeRepository;

    public PostService() {
    }

    public List<Post> getPostByPostTypeId(Long id) {
        return postRepository.findByPostTypeId(id);
    }

    public Post savePost(Long postTypeId, Post post) {
        return postTypeRepository.findById(postTypeId)
                .map(postType -> {
                    post.setPostType(postType);
                    return postRepository.save(post);
                }).orElseThrow(() -> new ResourceNotFoundException("Post type not found with id " + postTypeId));
    }

    public Post updatePost(Long postId, Post postRequest) {
        postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post does not exist."));
        return postRepository.save(postRequest);
    }

    public void deletePostById(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post does not exist."));
        postRepository.delete(post);
    }

    public List<Post> searchResults(String[] keys, String[] values) {
        rArray = postRepository.findBySelectedTagsContaining("...");
        for (int n = 0; n < rArray.size(); n++) {
            rArray.clear();
        }

        if(keys[0].contains("selectedTags")){
            String search = values[0];
            resultArray = postRepository.findBySelectedTagsContaining(search);
            for(int a = 0; a<resultArray.size(); a++){
                rArray.add(resultArray.get(a));
            }
        }else{
            String search = values[0];
            resultArray = postRepository.findByPostTextContaining(search);
            for(int b = 0; b<resultArray.size(); b++){
                rArray.add(resultArray.get(b));
            }
        }

        for (int i = 1; i < keys.length; i++) {
            if (!keys[i].contains("selectedTags")) {
                String search = values[i];
                resultArray = postRepository.findByPostTextContaining("...");
                resultArray = postRepository.findByPostTextContaining(search);
                if(resultArray.size() != 0){
                    for (int k = 0; k < resultArray.size(); k++) {
                        for (int n = 0; n < rArray.size(); n++) {
                            if (resultArray.get(k) != rArray.get(n)) {
                                rArray.remove(n);
                            }
                        }
                    }
                }else {
                    rArray.clear();
                }
            } else {
                String search = values[i];
                resultArray = postRepository.findBySelectedTagsContaining("...");
                resultArray = postRepository.findBySelectedTagsContaining(search);
                if(resultArray.size() != 0){
                    for (int d = 0; d < resultArray.size(); d++) {
                        for (int m = 0; m < rArray.size(); m++) {
                            if (resultArray.get(d) != rArray.get(m)) {
                                rArray.remove(m);
                            }
                        }
                    }
                }
                else{
                    rArray.clear();
                }
            }
        }
        return rArray;

        /*if(entries.length == 1) {
            for (int m = 0; m < postList.size(); m++) {
                resultArray.add(postList.get(m));
            }
        }
        else{
            for (int i = 1; i < entries.length; i++) {
                List<Post> pList = postRepository.getByPostTextContaining(entries[i]);
                for (int j = 0; j < postList.size(); j++) {
                    for(int k = 0; k<pList.size(); k++){
                        if(postList.get(j) == pList.get(k)){
                            resultArray.add(postList.get(j));
                        }
                    }
                }
            }
        }*/
    }
}