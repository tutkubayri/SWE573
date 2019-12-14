package com.swe.comin.models;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "community")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Community{

    public Community() {
    }

    public Community(@NotBlank @Size(min = 3, max = 50) String name, String description, String semanticTag,
                     String bannerUrl, Set<PostType> postTypes, String selectedTags) {
        this.name = name;
        this.description = description;
        this.semanticTag = semanticTag;
        this.bannerUrl = bannerUrl;
        this.postTypes = postTypes;
        this.selectedTags = selectedTags;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSemanticTag() {
        return semanticTag;
    }

    public void setSemanticTag(String semanticTag) {
        this.semanticTag = semanticTag;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public Set<PostType> getPostTypes() {
        return postTypes;
    }

    public void setPostTypes(Set<PostType> postTypes) {
        this.postTypes = postTypes;
    }

    public String getSelectedTags() {
        return selectedTags;
    }

    public void setSelectedTags(String selectedTags) {
        this.selectedTags = selectedTags;
    }

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(columnDefinition = "text")
    private String semanticTag;

    @Column(columnDefinition = "text")
    private String selectedTags;

    @Column(columnDefinition = "text")
    private String bannerUrl;

    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PostType> postTypes;
}