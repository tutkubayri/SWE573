package com.swe.comin.services;

import com.swe.comin.exceptions.ResourceNotFoundException;
import com.swe.comin.models.Community;
import com.swe.comin.models.PostType;
import com.swe.comin.repositories.CommunityRepository;
import com.swe.comin.repositories.PostTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Service
public class PostTypeService {
    private PostTypeRepository postTypeRepository;
    private CommunityRepository communityRepository;

    public PostTypeService(PostTypeRepository postTypeRepository, CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
        this.postTypeRepository = postTypeRepository;
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
/*
    public PostType getPostTypeById(Long id){
        return postTypeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post type does not exist."));
    }*/
}