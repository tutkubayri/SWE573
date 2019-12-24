package com.swe.comin;

import com.swe.comin.models.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TestUtils {

    public static Community createDummyCommunity() {
        final Community community = new Community();
        community.setId(0L);
        community.setName("randomName");
        community.setDescription("randomDescription");
        community.setSemanticTag("randomSemanticTag");
        community.setBannerUrl("randomUrl");
        community.setPostTypes(null);
        community.setSelectedTags("randomSelectedTags");
        return community;
    }

    public static List<Community> createDummyCommunityList() {
        final List<Community> communityList = new ArrayList<>();
        communityList.add(createDummyCommunity());
        return communityList;
    }

    public static PostType createDummyPostType() {
        final PostType postType = new PostType();
        postType.setId(0L);
        postType.setName("randomName");
        postType.setSemanticTag("randomSemanticTag");
        postType.setSelectedTags("randomSelectedTags");
        postType.setCommunity(null);
        postType.setFormAreas(null);
        postType.setPosts(null);
        postType.setUsage("randomUsage");
        return postType;
    }

    public static List<PostType> createDummyPostTypeList() {
        final List<PostType> postTypeList = new ArrayList<>();
        postTypeList.add(createDummyPostType());
        return postTypeList;
    }

    public static Post createDummyPost() {
        final Post post = new Post();
        post.setId(0L);
        post.setPostText("randomPostText");
        post.setPostType(null);
        post.setSemanticTag("randomSemanticTag");
        post.setSelectedTags("randomSelectedTags");
        return post;
    }

    public static List<Post> createDummyPostList() {
        final List<Post> postList = new ArrayList<>();
        postList.add(createDummyPost());
        return postList;
    }

    public static FormArea createDummyFormArea() {
        final FormArea formArea = new FormArea();
        formArea.setId(0L);
        formArea.setDataType("randomDataType");
        formArea.setRequirement(true);
        formArea.setPostType(null);
        formArea.setEnumTypeOfForm(null);
        return formArea;
    }

    public static List<FormArea> createDummyFormAreaList() {
        final List<FormArea> formAreaList = new ArrayList<>();
        formAreaList.add(createDummyFormArea());
        return formAreaList;
    }

    public static WikiData createDummyWikiData() {
        final WikiData wikiData = new WikiData();
        wikiData.setLabel("randomLabel");
        wikiData.setQcode("randomQcode");
        return wikiData;
    }

    public static List<WikiData> createDummyWikiDataList() {
        final List<WikiData> wikiDataList = new ArrayList<>();
        wikiDataList.add(createDummyWikiData());
        return wikiDataList;
    }
}
