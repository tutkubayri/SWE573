package com.swe573.comin.Services;

import com.swe573.comin.Models.Community;
import com.swe573.comin.Repositories.CommunityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityService {

    private CommunityRepository communityRepository;

    public CommunityService(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    public List<Community> getAllCommunities() {
        List<Community> communities = new ArrayList<>();
        communityRepository.findAll().forEach(communities::add);
        return communities;
    }

    public Community postCommunity(@RequestBody Community community) {
        Community _community = communityRepository.save(new Community(community.getName(), community.getDescription(), community.getSemanticTag(), community.getBannerUrl()));
        return _community;
    }

    public ResponseEntity<String> deleteCommunity(long id) {
        communityRepository.deleteById(id);
        return new ResponseEntity<>("Community has been deleted!", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteAllCommunities() {
        communityRepository.deleteAll();
        return new ResponseEntity<>("All communities have been deleted!", HttpStatus.OK);
    }

    public List<Community> findBySemanticTag(String semanticTag) {

        List<Community> communities = communityRepository.findBySemanticTag(semanticTag);
        return communities;
    }

    public List<Community> findByName(String name) {
        List<Community> communities = communityRepository.findByName(name);
        return communities;
    }

    public Optional<Community> findById( long id) {
        Optional<Community> community = communityRepository.findById(id);
        return community;
    }
}
