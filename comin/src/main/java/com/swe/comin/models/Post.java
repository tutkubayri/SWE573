package com.swe.comin.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.Type;
import java.util.List;

@Entity
@Table(name = "posts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Post{

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }

    public PostType getPostType() {
        return postType;
    }

    public void setPostType(PostType postType) {
        this.postType = postType;
    }

    public String getSemanticTag() {
        return semanticTag;
    }

    public void setSemanticTag(String semanticTag) {
        this.semanticTag = semanticTag;
    }

    public String getSelectedTags() {
        return selectedTags;
    }

    public void setSelectedTags(String selectedTags) {
        this.selectedTags = selectedTags;
    }

    public Post() {
    }

    public Post(PostType postType, String postText, String semanticTag, String selectedTags) {
        this.postType = postType;
        this.postText = postText;
        this.semanticTag = semanticTag;
        this.selectedTags = selectedTags;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "text", name="postText")
    private String postText;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_type_id", nullable = false)
    @JsonIgnore
    private PostType postType;

    @Column(columnDefinition = "text")
    private String semanticTag;

    @Column(columnDefinition = "text")
    private String selectedTags;

}