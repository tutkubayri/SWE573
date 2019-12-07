package com.swe.comin.services;

import com.swe.comin.exceptions.ResourceNotFoundException;
import com.swe.comin.models.Community;
import com.swe.comin.models.PostType;
import com.swe.comin.repositories.CommunityRepository;
import com.swe.comin.repositories.PostTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CommunityService{

    private CommunityRepository communityRepository;

    public CommunityService() {
    }

    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public List<Community> getAllCommunity(){
        return communityRepository.findAll();
    }

    public Community getCommunityById(Long id){
        return communityRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Community does not exist."));
    }

    public Community saveCommunity(Community communityRequest){
        return communityRepository.save(communityRequest);
    }

    public Community updateCommunity(Long communityId, Community communityRequest){
        communityRepository.findById(communityId).orElseThrow(() -> new ResourceNotFoundException("Community does not exist."));
        return communityRepository.save(communityRequest);
    }

    public void deleteCommunityById(Long communityId) {
        Community community = communityRepository.findById(communityId).orElseThrow(() -> new ResourceNotFoundException("Community does not exist."));
        communityRepository.delete(community);
    }
}
