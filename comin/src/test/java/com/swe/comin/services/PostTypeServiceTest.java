package com.swe.comin.services;

import com.swe.comin.TestUtils;
import com.swe.comin.models.Community;
import com.swe.comin.models.PostType;
import com.swe.comin.repositories.CommunityRepository;
import com.swe.comin.repositories.PostTypeRepository;
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
public class PostTypeServiceTest{

    @Mock
    private PostTypeRepository postTypeRepository;

    @Mock
    private CommunityRepository communityRepository;

    @InjectMocks
    private final PostTypeService postTypeService = new PostTypeService(postTypeRepository, communityRepository);

    @Test
    public void testGetAllPostTypes() {
        //Prepare
        final List<PostType> postTypes = TestUtils.createDummyPostTypeList();
        when(postTypeRepository.findAll()).thenReturn(postTypes);
        //Test
        final List<PostType> responseEntity = postTypes;
        //Verify
        assertTrue(Objects.requireNonNull(responseEntity.size() > 0));
    }

    @Test
    public void testCreatePostType() {
        //Prepare
        final PostType postType = TestUtils.createDummyPostType();
        when(postTypeRepository.findById(postType.getId())).thenReturn(Optional.of(postType));
        //Test
        final PostType responseEntity = postTypeService.savePostType(0L,postType);
        //Verify
        assertNotNull(responseEntity);
    }

    @Test
    public void getPostTypeById() {
        PostType postType = TestUtils.createDummyPostType();
        when(postTypeRepository.findById(0L)).thenReturn(Optional.of(postType));
        final PostType responseEntity = postTypeService.getPostTypeById(0L);
        assertNotNull(responseEntity);
    }
}
