package com.swe.comin.services;

import com.swe.comin.exceptions.ResourceNotFoundException;
import com.swe.comin.models.PostType;
import com.swe.comin.repositories.CommunityRepository;
import com.swe.comin.repositories.PostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostTypeService {

    @Autowired
    private PostTypeRepository postTypeRepository;

    @Autowired
    private CommunityRepository communityRepository;

    public PostType getPostTypeById(Long id){
        return postTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post type does not exist."));
    }

    public PostType getPostTypeByName(String name){
        return postTypeRepository.findByName(name);
    }

    public PostType savePostType(Long communityId, PostType postType){
        return communityRepository.findById(communityId)
                .map(community -> {
                    postType.setCommunity(community);
                    return postTypeRepository.save(postType);
                }).orElseThrow(() -> new ResourceNotFoundException("Community not found with id " + communityId));
    }

    public List<PostType> getPostTypeByCommunityId(Long id){
        return postTypeRepository.findByCommunityId(id);
    }
}