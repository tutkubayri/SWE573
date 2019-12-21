/*
package com.swe.comin.services;

import com.swe.comin.TestUtils;
import com.swe.comin.models.Community;
import com.swe.comin.repositories.CommunityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommunityServiceTest{

    @Mock
    private CommunityRepository communityRepository;

    @InjectMocks
    private final CommunityService communityService = new CommunityService(communityRepository);

    @Test
    public void testGetAllCommunities() {
        //Prepare
        final List<Community> communities = TestUtils.createDummyCommunityList();
        when(communityRepository.findAll()).thenReturn(communities);
        //Test
        final List<Community> responseEntity = communities;
        //Verify
        assertTrue(Objects.requireNonNull(responseEntity.size() > 0));
    }

    @Test
    public void testCreateCommunity() {
        //Prepare
        final Community community = TestUtils.createDummyCommunity();
        when(communityRepository.findById(community.getId())).thenReturn(Optional.of(community));
        //Test
        final Community responseEntity = communityService.saveCommunity(community);
        //Verify
        assertNotNull(responseEntity);
    }
}
*/
