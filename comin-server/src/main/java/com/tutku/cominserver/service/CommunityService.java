package com.tutku.cominserver.service;

import com.tutku.cominserver.exception.ResourceNotFoundException;
import com.tutku.cominserver.model.Community;
import com.tutku.cominserver.repository.CommunityRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommunityService {
    private CommunityRepository communityRepository;

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
